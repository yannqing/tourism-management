package com.yangg.tourism.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;

public interface ChatService {
    String sendMessage(String message, HttpServletRequest request) throws JsonProcessingException;
}
