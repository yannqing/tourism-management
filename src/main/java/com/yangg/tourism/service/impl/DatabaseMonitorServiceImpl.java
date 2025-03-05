package com.yangg.tourism.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangg.tourism.domain.entity.DatabaseMonitor;
import com.yangg.tourism.enums.ErrorType;
import com.yangg.tourism.exception.BusinessException;
import com.yangg.tourism.mapper.DatabaseMonitorMapper;
import com.yangg.tourism.service.DatabaseMonitorService;
import com.yangg.tourism.utils.RedisCache;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author yanqing
* @description 针对表【database_monitor】的数据库操作Service实现
* @createDate 2025-03-03 11:33:30
*/
@Service
public class DatabaseMonitorServiceImpl extends ServiceImpl<DatabaseMonitorMapper, DatabaseMonitor>
    implements DatabaseMonitorService{

    @Resource
    private RedisCache redisCache;

    @Override
    public Page<DatabaseMonitor> databaseMonitor(Integer current, Integer size) {
        if (current == null) {
            current = 1;
        }
        if (size == null) {
            size = 10;
        }
        if (size > 10) {
            throw new BusinessException(ErrorType.SYSTEM_ERROR);
        }
        String databaseMonitorPageJsonString = redisCache.getCacheObject("databaseMonitor:" + current + ":" + size);
        if (databaseMonitorPageJsonString != null) {
            return JSON.parseObject(databaseMonitorPageJsonString, new TypeReference<Page<DatabaseMonitor>>() {});
        }

        Page<DatabaseMonitor> newMonitorPage = this.page(new Page<>(current, size));
        redisCache.setCacheObject("databaseMonitor:" + current + ":" + size, JSON.toJSONString(newMonitorPage));

        return newMonitorPage;
    }
}




