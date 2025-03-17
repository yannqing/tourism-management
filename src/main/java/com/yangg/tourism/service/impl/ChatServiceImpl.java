package com.yangg.tourism.service.impl;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.yangg.tourism.domain.dto.chat.ChatRequest;
import com.yangg.tourism.domain.dto.chat.ChatResponse;
import com.yangg.tourism.domain.entity.User;
import com.yangg.tourism.domain.model.Message;
import com.yangg.tourism.enums.ErrorType;
import com.yangg.tourism.exception.BusinessException;
import com.yangg.tourism.service.ChatService;
import com.yangg.tourism.utils.JwtUtils;
import com.yangg.tourism.utils.RedisCache;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class ChatServiceImpl implements ChatService {

    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private RedisCache redisCache;

    @Value("${spring.ai.openai.chat.options.model}")
    private String model;

    @Value("${spring.ai.openai.base-url}")
    private String apiUrl;

    @Override
    public String sendMessage(String message, HttpServletRequest request) throws JsonProcessingException {
        String token = request.getHeader("token");
        User loginUser = JwtUtils.getUserFromToken(token);
        if (loginUser == null) {
            throw new BusinessException(ErrorType.SYSTEM_ERROR);
        }
        String messageInfo = redisCache.getCacheObject(loginUser.getUserId() + ":" + model);
        List<Message> messages = null;
        if (messageInfo == null) {
            messages = new ArrayList<>();
            Message initMessage = new Message("user", "你是一个城市旅游管理信息系统的智能 ai 客服，下面只能回答和此相关的问题，不能回答其他问题");
            messages.add(initMessage);
        } else {
            messages = JSON.parseArray(messageInfo, Message.class);
        }
        Message myMessage = new Message("user", message);
        // 新增一条消息，并重新存入 redis
        messages.add(myMessage);
        redisCache.setCacheObject(loginUser.getUserId() + ":" + model, JSON.toJSONString(messages), 60*60*3, TimeUnit.SECONDS);

        // 创建 request
        ChatRequest chatRequest = new ChatRequest(model, messages);

        // 调用 API
        ChatResponse response = restTemplate.postForObject(apiUrl, chatRequest, ChatResponse.class);

        String content = "";

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            content =  "No response";
        } else {
            content = response.getChoices().get(0).getMessage().getContent();
        }
        return content;
    }
}
