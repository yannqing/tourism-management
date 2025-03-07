package com.yangg.tourism.domain.model.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 服务端消息格式
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerMessage <T> {
    private String type; // 消息类型（如 HEARTBEAT_ACK）
    private T content; // 消息内容
}
