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

    Page<IpApiStatistic> ipApiMonitor(Integer current, Integer pageSize);
}
