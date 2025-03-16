package com.yangg.tourism.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.yangg.tourism.common.Code;
import com.yangg.tourism.domain.dto.cost.AddCostDto;
import com.yangg.tourism.domain.dto.cost.CreateOrderDto;
import com.yangg.tourism.domain.dto.cost.QueryCostDto;
import com.yangg.tourism.domain.dto.cost.UpdateCostDto;
import com.yangg.tourism.domain.entity.Cost;
import com.yangg.tourism.domain.model.BaseResponse;
import com.yangg.tourism.service.CostService;
import com.yangg.tourism.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 费用管理
 * @author: yannqing
 * @create: 2025-02-08 17:14
 * @from: <更多资料：yannqing.com>
 **/
@Tag(name = "费用管理")
@RestController
@RequestMapping("/cost")
public class CostController {

    @Resource
    private CostService costService;

    @Operation(summary = "查询所有费用信息（管理员）")
    @GetMapping
    public BaseResponse<?> getAllCosts(QueryCostDto queryCostDto) {
        Page<Cost> costList = costService.getAllCosts(queryCostDto);
        return ResultUtils.success(Code.SUCCESS, costList, "查询全部费用成功！");
    }

    @Operation(summary = "查询所有费用信息（游客）")
    @GetMapping("/user")
    public BaseResponse<?> getAllCostsByUser(QueryCostDto queryCostDto, Integer userId) {
        Page<Cost> costList = costService.getAllCostsByUser(queryCostDto, userId);
        return ResultUtils.success(Code.SUCCESS, costList, "查询全部费用成功！");
    }

    @PutMapping
    @Operation(summary = "更新费用信息")
    public BaseResponse<?> updateCost(UpdateCostDto updateCostDto) {
        boolean result = costService.updateCost(updateCostDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "修改费用信息成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "修改费用信息失败！");
        }
    }

    @Operation(summary = "添加新费用")
    @PostMapping
    public BaseResponse<?> addCost(AddCostDto addCostDto) {
        boolean result = costService.addCost(addCostDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "新增费用信息成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "新增费用信息失败！");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除单个费用信息")
    public BaseResponse<?> deleteCost(@PathVariable Integer id) {
        boolean result = costService.deleteCost(id);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "删除费用信息成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "删除费用信息失败！");
        }
    }

    @DeleteMapping("/batch")
    @Operation(summary = "批量删除费用信息")
    public BaseResponse<?> deleteBatchCost(Integer... costIds) {
        boolean result = costService.deleteBatchCost(costIds);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "批量删除费用信息成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "批量删除费用信息失败！");
        }
    }

    @Operation(summary = "创建订单")
    @PostMapping("/create-order")
    public BaseResponse<?> createOrder(CreateOrderDto createOrderDto, HttpServletRequest request) throws JsonProcessingException {
        String order = costService.createOrder(createOrderDto, request);
        return ResultUtils.success(Code.SUCCESS, order, "创建订单成功！");
    }

    @Operation(summary = "支付订单")
    @PostMapping("/pay-order")
    public BaseResponse<?> payOrder(String orderNumber, HttpServletRequest request) throws JsonProcessingException {
        boolean result = costService.payOrder(orderNumber, request);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "支付订单成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "支付订单失败！");
        }
    }

}
