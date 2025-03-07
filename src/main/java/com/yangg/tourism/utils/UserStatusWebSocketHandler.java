package com.yangg.tourism.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yangg.tourism.domain.entity.UserActions;
import com.yangg.tourism.domain.model.QueueMessage;
import com.yangg.tourism.domain.model.websocket.ClientMessage;
import com.yangg.tourism.domain.model.websocket.ClientMessageType;
import com.yangg.tourism.domain.model.websocket.ServerMessage;
import com.yangg.tourism.domain.model.websocket.ServerMessageType;
import com.yangg.tourism.service.RabbitMQService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static com.yangg.tourism.domain.model.QueueMessageType.QUEUE_INSERT_TYPE;

@Slf4j
@Component
public class UserStatusWebSocketHandler extends TextWebSocketHandler {

    // 存储在线用户（userId -> WebSocketSession）
    private static final Map<String, WebSocketSession> onlineUsers = new ConcurrentHashMap<>();

    // JSON 工具
    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private RedisCache redisCache;

    @Resource
    private RabbitMQService rabbitMQService;

    /**
     * 处理客户端发送的消息
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        // 解析客户端消息
        ClientMessage clientMessage = objectMapper.readValue(message.getPayload(), new TypeReference<>() {});

        // 处理心跳消息
        if (ClientMessageType.HEARTBEAT.equals(clientMessage.getType())) {
            String userId = extractUserIdFromSession(session);
            onlineUsers.put(userId, session); // 更新用户状态
            sendMessage(session, new ServerMessage<>(ServerMessageType.HEARTBEAT_ACK, "心跳已接收"));
        }

        // 判断是否在线
        if (ClientMessageType.IS_ONLINE.equals(clientMessage.getType())) {
            String userId = clientMessage.getUserId();
            boolean userOnline = isUserOnline(userId);
            sendMessage(session, new ServerMessage<>(ServerMessageType.IS_ONLINE_ACK, userOnline));
        }

        // 进入页面
        if (ClientMessageType.INTO_PAGE.equals(clientMessage.getType())) {
            // 将前端传入参数转为 UserActions 类型的数据
            String content = objectMapper.writeValueAsString(clientMessage.getContent());
            UserActions userActions = objectMapper.readValue(content, UserActions.class);
            // 获取到 userId
            String userId = extractUserIdFromSession(session);
            // 构造数据（前端参数转化）
            userActions.setUniqueIndex(UUID.randomUUID().toString());
            userActions.setAccessTime(new Date());
            // 序列化后存入 redis
            redisCache.setCacheObject(userId + ":" + userActions.getUniqueIndex(), objectMapper.writeValueAsString(userActions));
            // 返回给前端一个唯一 id
            sendMessage(session, new ServerMessage<>(ServerMessageType.INTO_PAGE_ACK, userActions.getUniqueIndex()));
        }

        // 退出页面
        if (ClientMessageType.DEPARTURE_PAGE.equals(clientMessage.getType())) {
            // 将前端传入参数转为 UserActions 类型的数据
            String content = objectMapper.writeValueAsString(clientMessage.getContent());
            UserActions userActions = objectMapper.readValue(content, UserActions.class);
            // 获取到 userId
            String userId = extractUserIdFromSession(session);
            // 从 redis 中获取到进入页面的数据，并反序列化
            String intoPage = redisCache.getCacheObject(userId + ":" + userActions.getUniqueIndex());
            UserActions userActionsOld = objectMapper.readValue(intoPage, userActions.getClass());
            // 构造新数据
            userActionsOld.setDepartureTime(new Date());
            userActionsOld.setStayTime(String.valueOf(userActionsOld.getDepartureTime().getTime() - userActionsOld.getAccessTime().getTime()));
            // 发送给消息队列，通知插入数据
            QueueMessage<UserActions> objectQueueMessage = new QueueMessage<>(userActionsOld, QUEUE_INSERT_TYPE);
            rabbitMQService.sendMessageToUserActionsQueue(JSON.toJSONString(objectQueueMessage));
        }
    }

    /**
     * 客户端连接成功后调用
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        // 获取 URI 中的参数
        String userId = Objects.requireNonNull(session.getUri()).getQuery();
        if (userId != null && userId.contains("userId=")) {
            userId = userId.split("userId=")[1];
            session.getAttributes().put("userId", userId);
        }

        onlineUsers.put(userId, session); // 将用户添加到在线列表
        log.info("用户 userId：{}, 已上线(已登录)", userId);
        sendMessage(session, new ServerMessage<>("CONNECT_SUCCESS", "连接成功"));
    }

    /**
     * 客户端连接关闭后调用
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws IOException {
        String userId = extractUserIdFromSession(session); // 从 session 中提取用户 ID
        log.info("用户 userId：{}, 已下线(已退出)", userId);
        onlineUsers.remove(userId); // 将用户从在线列表中移除
    }

    /**
     * 发送消息给客户端
     */
    private void sendMessage(WebSocketSession session, ServerMessage message) throws IOException {
        String jsonMessage = objectMapper.writeValueAsString(message);
        session.sendMessage(new TextMessage(jsonMessage));
    }

    /**
     * 从 WebSocketSession 中提取用户 ID
     */
    private String extractUserIdFromSession(WebSocketSession session) {
        // 这里可以根据业务逻辑从 session 中提取用户 ID
        // 例如，从 session 的 URI 参数或属性中获取
        return session.getAttributes().get("userId").toString();
    }

    /**
     * 判断用户是否在线
     */
    public static boolean isUserOnline(String userId) {
        return onlineUsers.containsKey(userId);
    }


}