package com.yangg.tourism.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangg.tourism.annotation.AuthCheck;
import com.yangg.tourism.common.Code;
import com.yangg.tourism.domain.entity.DatabaseMonitor;
import com.yangg.tourism.domain.entity.IpApiStatistic;
import com.yangg.tourism.domain.model.BaseResponse;
import com.yangg.tourism.domain.vo.user.UserActionsVo;
import com.yangg.tourism.service.DatabaseMonitorService;
import com.yangg.tourism.service.IpApiStatisticService;
import com.yangg.tourism.service.UserActionsService;
import com.yangg.tourism.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "监控管理")
@Slf4j
@RestController
@RequestMapping("/monitor")
public class MonitorController {

    @Resource
    private DatabaseMonitorService databaseMonitorService;

    @Resource
    private IpApiStatisticService ipApiStatisticService;

    @Resource
    private UserActionsService userActionsService;

    @AuthCheck(code = "SYSTEM_MONITOR_DATABASE")
    @Operation(summary = "数据库监控")
    @GetMapping("/database")
    public BaseResponse<?> databaseMonitor(Integer current, Integer pageSize) {
        Page<DatabaseMonitor> data = databaseMonitorService.databaseMonitor(current, pageSize);
        return ResultUtils.success(Code.SUCCESS, data);
    }

    @AuthCheck(code = "SYSTEM_MONITOR_IP_API")
    @Operation(summary = "ip 和 后端 api 监控")
    @GetMapping("/ip-api")
    public BaseResponse<?> ipMonitor(Integer current, Integer pageSize) {
        Page<IpApiStatistic> data = ipApiStatisticService.ipApiMonitor(current, pageSize);
        return ResultUtils.success(Code.SUCCESS, data);
    }

    @AuthCheck(code = "SYSTEM_USER_ACTIONS")
    @Operation(summary = "用户行为分析")
    @GetMapping("/user-actions")
    public BaseResponse<?> userActions() {
        List<UserActionsVo> data = userActionsService.getUserActions();
        return ResultUtils.success(Code.SUCCESS, data);
    }

}
