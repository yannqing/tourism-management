package com.yangg.tourism.service;

public interface RabbitMQService {
    /**
     * 发送消息到 ip.api.queue 队列
     * @param message 发送的消息内容
     */
    void sendMessageToIpApiQueue(String message) ;

    /**
     * 发送消息到 database.queue 队列
     * @param message 发送的消息内容
     */
    void sendMessageToDatabaseQueue(String message);

}
