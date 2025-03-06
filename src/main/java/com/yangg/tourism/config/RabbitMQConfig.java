package com.yangg.tourism.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String DATABASE_QUEUE_NAME = "database.queue";
    public static final String IP_API_QUEUE_NAME = "ip.api.queue";

    public static final String DATABASE_EXCHANGE_NAME = "database.exchange";

    public static final String DATABASE_ROUTING_KEY = "database.routingKey";

    // 声明队列
//    @Bean
//    public List<Queue> queues() {
//        return List.of(
//                new Queue(DATABASE_QUEUE_NAME, true),
//                new Queue(IP_API_QUEUE_NAME, true)
//        );
//    }

    @Bean
    public Queue queue1() {
        return new Queue(DATABASE_QUEUE_NAME, true);
    }

    @Bean
    public Queue queue2() {
        return new Queue(IP_API_QUEUE_NAME, true);
    }

    // 声明交换机
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(DATABASE_EXCHANGE_NAME);
    }

    // 绑定队列到交换机
//    @Bean
//    public Binding binding(Queue queue, DirectExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with(DATABASE_ROUTING_KEY);
//    }
}