package com.yangg.tourism.service.impl;

import com.yangg.tourism.config.RabbitMQConfig;
import com.yangg.tourism.service.RabbitMQService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitMQServiceImpl implements RabbitMQService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessageToIpApiQueue(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.IP_API_QUEUE_NAME, message);
    }

    @Override
    public void sendMessageToDatabaseQueue(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.DATABASE_QUEUE_NAME, message);
    }

    @Override
    public void sendMessageToUserActionsQueue(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.USER_ACTIONS_QUEUE_NAME, message);
    }
}
