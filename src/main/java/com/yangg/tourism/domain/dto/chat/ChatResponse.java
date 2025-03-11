package com.yangg.tourism.domain.dto.chat;

import com.yangg.tourism.domain.model.Message;
import lombok.Data;

import java.util.List;

@Data
public class ChatResponse {

    private List<Choice> choices;

    // 忽略构造函数、get、set 方法
    @Data
    public static class Choice {

        private int index;
        private Message message;

        // 忽略构造函数、get、set 方法
    }
}