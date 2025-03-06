package com.yangg.tourism.rabbitmq;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yangg.tourism.config.RabbitMQConfig;
import com.yangg.tourism.domain.entity.IpApiStatistic;
import com.yangg.tourism.domain.model.QueueMessage;
import com.yangg.tourism.domain.model.QueueMessageType;
import com.yangg.tourism.mapper.IpApiStatisticMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQListener {

    @Resource
    private IpApiStatisticMapper ipApiStatisticMapper;

    @RabbitListener(queues = RabbitMQConfig.DATABASE_QUEUE_NAME)
    public void listenDatabaseQueue(String msg) {
        System.out.println("消费者接收到database.queue的消息：【" + msg + "】");
    }

    @RabbitListener(queues = RabbitMQConfig.IP_API_QUEUE_NAME)
    public void listenIpApiQueue(String msg) {
        QueueMessage<IpApiStatistic> queueMessage = JSON.parseObject(msg, new TypeReference<>() {});
        if (queueMessage != null) {
            if (queueMessage.getType().equals(QueueMessageType.QUEUE_INSERT_TYPE)) {
                ipApiStatisticMapper.insert(queueMessage.getMessage());
                log.info("insert request：{}", queueMessage.getMessage());
            } else if (queueMessage.getType().equals(QueueMessageType.QUEUE_UPDATE_TYPE)) {
                ipApiStatisticMapper.update(new UpdateWrapper<IpApiStatistic>()
                        .eq("requestId", queueMessage.getMessage().getRequestId())
                        .set("consumptionTime", queueMessage.getMessage().getConsumptionTime())
                );
                log.info("update request：{}", queueMessage.getMessage());
            } else {
                ipApiStatisticMapper.delete(new QueryWrapper<IpApiStatistic>().eq("requestId", queueMessage.getMessage().getRequestId()));
                log.info("delete request：{}", queueMessage.getMessage());
            }
        }
    }
}
