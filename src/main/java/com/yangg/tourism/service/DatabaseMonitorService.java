package com.yangg.tourism.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yangg.tourism.domain.entity.DatabaseMonitor;

/**
* @author yanqing
* @description 针对表【database_monitor】的数据库操作Service
* @createDate 2025-03-03 11:33:30
*/
public interface DatabaseMonitorService extends IService<DatabaseMonitor> {

    /**
     * 数据库监控信息查询
     * @param current 当前页码
     * @param pageSize 一页数量大小
     * @return 返回查询结果
     */
    Page<DatabaseMonitor> databaseMonitor(Integer current, Integer pageSize);
}
