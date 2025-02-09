package com.yannqing.template.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yannqing.template.common.Code;
import com.yannqing.template.domain.dto.cost.*;
import com.yannqing.template.domain.entity.CostType;
import com.yannqing.template.domain.model.BaseResponse;
import com.yannqing.template.service.CostTypeService;
import com.yannqing.template.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 费用类型管理
 * @author: yannqing
 * @create: 2025-02-09 10:03
 * @from: <更多资料：yannqing.com>
 **/
@Tag(name = "费用类型管理")
@RestController
@RequestMapping("/costType")
public class CostTypeController {

    @Resource
    private CostTypeService costTypeService;

    @Operation(summary = "查询所有费用类型")
    @GetMapping
    public BaseResponse<?> getAllCostTypes(QueryCostTypeDto queryCostTypeDto) {
        Page<CostType> costList = costTypeService.getAllCostTypes(queryCostTypeDto);
        return ResultUtils.success(Code.SUCCESS, costList, "查询全部费用类型成功！");
    }

    @PutMapping
    @Operation(summary = "更新费用类型")
    public BaseResponse<?> updateCostType(UpdateCostTypeDto updateCostTypeDto) {
        boolean result = costTypeService.updateCostType(updateCostTypeDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "修改费用信息成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "修改费用信息失败！");
        }
    }

    @Operation(summary = "添加新费用类型")
    @PostMapping
    public BaseResponse<?> addCostType(AddCostTypeDto addCostTypeDto) {
        boolean result = costTypeService.addCostType(addCostTypeDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "新增费用类型成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "新增费用类型失败！");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除单个费用类型")
    public BaseResponse<?> deleteCostType(@PathVariable Integer id) {
        boolean result = costTypeService.deleteCostType(id);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "删除费用类型成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "删除费用类型失败！");
        }
    }

    @DeleteMapping("/batch")
    @Operation(summary = "批量删除费用信息")
    public BaseResponse<?> deleteBatchCostType(Integer... costTypeIds) {
        boolean result = costTypeService.deleteBatchCostType(costTypeIds);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "批量删除费用类型成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "批量删除费用类型失败！");
        }
    }


}
