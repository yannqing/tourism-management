package com.yangg.tourism.domain.model.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 客户端消息格式
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientMessage <T> {
    private String type; // 消息类型（如 HEARTBEAT）
    private T content;
    private String userId;
}
