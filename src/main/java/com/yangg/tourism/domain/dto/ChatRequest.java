package com.yangg.tourism.domain.dto;

import com.yangg.tourism.domain.model.Message;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatRequest {

    private String model;
    private List<Message> messages;

    public ChatRequest(String model, String prompt) {
        this.model = model;
        
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
    }

    // 省略 get/set 方法
}