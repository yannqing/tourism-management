package com.yangg.tourism.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangg.tourism.common.Code;
import com.yangg.tourism.domain.entity.DatabaseMonitor;
import com.yangg.tourism.domain.model.BaseResponse;
import com.yangg.tourism.service.DatabaseMonitorService;
import com.yangg.tourism.utils.ResultUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "监控管理")
@Slf4j
@RestController
@RequestMapping("/monitor")
public class MonitorController {

    @Resource
    private DatabaseMonitorService databaseMonitorService;

    @GetMapping("/database")
    public BaseResponse<?> databaseMonitor(Integer current, Integer pageSize) {
        Page<DatabaseMonitor> data = databaseMonitorService.databaseMonitor(current, pageSize);
        return ResultUtils.success(Code.SUCCESS, data);
    }

}
