package com.yangg.tourism.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangg.tourism.common.Code;
import com.yangg.tourism.domain.dto.tourist.AddResourcesTypeDto;
import com.yangg.tourism.domain.dto.tourist.QueryResourcesTypeDto;
import com.yangg.tourism.domain.dto.tourist.UpdateResourcesTypeDto;
import com.yangg.tourism.domain.entity.ResourcesType;
import com.yangg.tourism.domain.model.BaseResponse;
import com.yangg.tourism.service.ResourcesTypeService;
import com.yangg.tourism.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 旅游资源类型管理
 * @author: yannqing
 * @create: 2025-02-09 10:03
 * @from: <更多资料：yannqing.com>
 **/
@Tag(name = "旅游资源类型管理")
@RestController
@RequestMapping("/resourcesType")
public class ResourcesTypeController {

    @Resource
    private ResourcesTypeService resourcesTypeService;

    @Operation(summary = "查询所有旅游资源类型")
    @GetMapping
    public BaseResponse<?> getAllResourcesTypes(QueryResourcesTypeDto queryResourcesTypeDto) {
        Page<ResourcesType> costList = resourcesTypeService.getAllResourcesTypes(queryResourcesTypeDto);
        return ResultUtils.success(Code.SUCCESS, costList, "查询全部旅游资源类型成功！");
    }

    @PutMapping
    @Operation(summary = "更新旅游资源类型")
    public BaseResponse<?> updateResourcesType(UpdateResourcesTypeDto updateResourcesTypeDto) {
        boolean result = resourcesTypeService.updateResourcesType(updateResourcesTypeDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "修改旅游资源类型成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "修改旅游资源类型失败！");
        }
    }

    @Operation(summary = "添加新旅游资源类型")
    @PostMapping
    public BaseResponse<?> addResourcesType(AddResourcesTypeDto addResourcesTypeDto) {
        boolean result = resourcesTypeService.addResourcesType(addResourcesTypeDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "新增旅游资源类型成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "新增旅游资源类型失败！");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除单个旅游资源类型")
    public BaseResponse<?> deleteResourcesType(@PathVariable Integer id) {
        boolean result = resourcesTypeService.deleteResourcesType(id);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "删除旅游资源类型成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "删除旅游资源类型失败！");
        }
    }

    @DeleteMapping("/batch")
    @Operation(summary = "批量删除旅游资源类型")
    public BaseResponse<?> deleteBatchResourcesType(Integer... resourcesTypeIds) {
        boolean result = resourcesTypeService.deleteBatchResourcesType(resourcesTypeIds);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "批量删除旅游资源类型成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "批量删除旅游资源类型失败！");
        }
    }


}
