package com.yannqing.template.controller;

import com.yannqing.template.utils.RedisCache;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @description: 系统启动自动执行
 * @author: yannqing
 * @create: 2025-01-14 16:16
 * @from: <更多资料：yannqing.com>
 **/
@Slf4j
@Component
public class SystemController {

    @Resource
    private RedisCache redisCache;

    /**
     * 连接 redis
     */
    @PostConstruct
    public void initRedis() {
        // 如果失败，尝试重连 redis
        int attempts = 5; // 最大重试次数
        while (attempts > 0) {
            try {
                redisCache.setCacheObject("key", "value", 20, TimeUnit.SECONDS);
                log.info("--------redis 连接成功！--------");
                break; // 连接成功，退出重试循环
            } catch (Exception e) {
                attempts--;
                log.info("连接Redis失败，重试中... ({}次尝试剩余)", attempts);
                try {
                    Thread.sleep(2000); // 等待2秒后重试
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
