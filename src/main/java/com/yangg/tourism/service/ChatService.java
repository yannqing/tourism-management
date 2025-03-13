package com.yangg.tourism.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;

public interface ChatService {
    /**
     * 发送消息
     * @param message 消息内容
     * @param request 会话 session
     * @return 返回 ai 对话结果
     * @throws JsonProcessingException json 解析异常
     */
    String sendMessage(String message, HttpServletRequest request) throws JsonProcessingException;
}
