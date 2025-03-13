package com.yangg.tourism.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangg.tourism.domain.entity.IpApiStatistic;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author yanqing
* @description 针对表【ip_api_statistic】的数据库操作Service
* @createDate 2025-03-06 10:40:29
*/
public interface IpApiStatisticService extends IService<IpApiStatistic> {

    /**
     * id 和 api 的监控
     * @param current 当前页码
     * @param pageSize 一页的数量大小
     * @return 返回结果
     */
    Page<IpApiStatistic> ipApiMonitor(Integer current, Integer pageSize);
}
