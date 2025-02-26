package com.yangg.tourism.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class UserStatusWebSocketHandler extends TextWebSocketHandler {

    // 存储在线用户（userId -> WebSocketSession）
    private static final Map<String, WebSocketSession> onlineUsers = new ConcurrentHashMap<>();

    // JSON 工具
    @Resource
    private ObjectMapper objectMapper;

    /**
     * 处理客户端发送的消息
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        // 解析客户端消息
        ClientMessage clientMessage = objectMapper.readValue(message.getPayload(), ClientMessage.class);

        // 处理心跳消息
        if ("HEARTBEAT".equals(clientMessage.getType())) {
            String userId = clientMessage.getUserId();
            onlineUsers.put(userId, session); // 更新用户状态
            sendMessage(session, new ServerMessage<>("HEARTBEAT_ACK", "心跳已接收"));
        }

        if ("IS_ONLINE".equals(clientMessage.getType())) {
            String userId = clientMessage.getUserId();
            boolean userOnline = isUserOnline(userId);
            sendMessage(session, new ServerMessage<>("IS_ONLINE_ACK", userOnline));
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

    /**
     * 客户端消息格式
     */
    @Data
    private static class ClientMessage {
        private String type; // 消息类型（如 HEARTBEAT）
        private String userId; // 用户 ID
    }

    /**
     * 服务端消息格式
     */
    @Data
    private static class ServerMessage<T> {
        private String type; // 消息类型（如 HEARTBEAT_ACK）
        private T content; // 消息内容

        public ServerMessage(String type, T content) {
            this.type = type;
            this.content = content;
        }
    }
}