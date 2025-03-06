package com.yangg.tourism.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangg.tourism.domain.entity.IpApiStatistic;
import com.yangg.tourism.enums.ErrorType;
import com.yangg.tourism.exception.BusinessException;
import com.yangg.tourism.service.IpApiStatisticService;
import com.yangg.tourism.mapper.IpApiStatisticMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* @author yanqing
* @description 针对表【ip_api_statistic】的数据库操作Service实现
* @createDate 2025-03-06 10:40:29
*/
@Slf4j
@Service
public class IpApiStatisticServiceImpl extends ServiceImpl<IpApiStatisticMapper, IpApiStatistic>
    implements IpApiStatisticService{

    @Override
    public Page<IpApiStatistic> ipApiMonitor(Integer current, Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        if (pageSize > 10) {
            throw new BusinessException(ErrorType.SYSTEM_ERROR);
        }

        Page<IpApiStatistic> page = this.page(new Page<>(current, pageSize));
        log.info("查询 ip-api 监控数据");

        return page;
    }
}




