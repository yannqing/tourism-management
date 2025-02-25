package com.yangg.tourism.domain.dto;

import com.yangg.tourism.domain.model.Message;
import lombok.Data;

import java.util.List;

@Data
public class ChatRequest {

    private String model;
    private List<Message> messages;

    public ChatRequest(String model, List<Message> messages) {
        this.model = model;
        
        this.messages = messages;
    }

    // 省略 get/set 方法
}