package com.yangg.tourism.aop;

import com.alibaba.fastjson.JSON;
import com.yangg.tourism.domain.entity.IpApiStatistic;
import com.yangg.tourism.domain.model.QueueMessage;
import com.yangg.tourism.service.RabbitMQService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.UUID;

import static com.yangg.tourism.domain.model.QueueMessageType.QUEUE_INSERT_TYPE;
import static com.yangg.tourism.domain.model.QueueMessageType.QUEUE_UPDATE_TYPE;

/**
 * 请求响应日志 AOP
 *
 **/
@Aspect
@Component
@Slf4j
public class LogInterceptor {

    @Resource
    private RabbitMQService rabbitMQService;

    /**
     * 执行拦截
     */
    @Around("execution(* com.yangg.tourism.controller.*.*(..))")
    public Object doInterceptor(ProceedingJoinPoint point) throws Throwable {
        // 计时
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // 获取请求路径
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
        // 生成请求唯一 id
        String requestId = UUID.randomUUID().toString();
        String url = httpServletRequest.getRequestURI();
        // 获取请求参数
        Object[] args = point.getArgs();
        String reqParam = "[" + StringUtils.join(args, ", ") + "]";
        // 记录数据
        IpApiStatistic ipApiStatistic = new IpApiStatistic();
        ipApiStatistic.setRequestId(requestId);
        ipApiStatistic.setIp(httpServletRequest.getRemoteHost());
        ipApiStatistic.setAccessTime(new Date());
        ipApiStatistic.setApi(url);
        ipApiStatistic.setMethod(httpServletRequest.getMethod());
        QueueMessage<IpApiStatistic> ipApiStatisticQueueMessageInsert = new QueueMessage<>(ipApiStatistic, QUEUE_INSERT_TYPE);
        rabbitMQService.sendMessageToIpApiQueue(JSON.toJSONString(ipApiStatisticQueueMessageInsert));

        // 输出请求日志
        log.info("request start，id: {}, path: {}, ip: {}, params: {}", requestId, url,
                httpServletRequest.getRemoteHost(), reqParam);
        // 执行原方法
        Object result = point.proceed();
        // 输出响应日志
        stopWatch.stop();
        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        // 设置消耗时间
        IpApiStatistic ipApiStatistic1 = new IpApiStatistic();
        ipApiStatistic1.setRequestId(requestId);
        ipApiStatistic1.setConsumptionTime(totalTimeMillis);
        QueueMessage<IpApiStatistic> ipApiStatisticQueueMessageUpdate = new QueueMessage<>(ipApiStatistic1, QUEUE_UPDATE_TYPE);
        rabbitMQService.sendMessageToIpApiQueue(JSON.toJSONString(ipApiStatisticQueueMessageUpdate));

        log.info("request end, id: {}, cost: {}ms", requestId, totalTimeMillis);
        return result;
    }
}

