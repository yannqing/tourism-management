package com.yangg.tourism.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangg.tourism.common.Code;
import com.yangg.tourism.domain.dto.tourist.AddTouristResourcesDto;
import com.yangg.tourism.domain.dto.tourist.QueryTouristResourcesDto;
import com.yangg.tourism.domain.dto.tourist.UpdateTouristResourcesDto;
import com.yangg.tourism.domain.entity.TouristResources;
import com.yangg.tourism.domain.model.BaseResponse;
import com.yangg.tourism.service.TouristResourcesService;
import com.yangg.tourism.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 旅游资源管理
 * @author: yannqing
 * @create: 2025-02-09 10:03
 * @from: <更多资料：yannqing.com>
 **/
@Tag(name = "旅游资源管理")
@RestController
@RequestMapping("/touristResources")
public class TouristResourcesController {

    @Resource
    private TouristResourcesService touristResourcesService;

    @Operation(summary = "查询所有旅游资源")
    @GetMapping
    public BaseResponse<?> getAllTouristResources(QueryTouristResourcesDto queryTouristResourcesDto) {
        Page<TouristResources> costList = touristResourcesService.getAllTouristResources(queryTouristResourcesDto);
        return ResultUtils.success(Code.SUCCESS, costList, "查询全部旅游资源成功！");
    }

    @PutMapping
    @Operation(summary = "更新旅游资源")
    public BaseResponse<?> updateTouristResources(UpdateTouristResourcesDto updateTouristResourcesDto) {
        boolean result = touristResourcesService.updateTouristResources(updateTouristResourcesDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "修改旅游资源成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "修改旅游资源失败！");
        }
    }

    @Operation(summary = "添加新旅游资源")
    @PostMapping
    public BaseResponse<?> addTouristResources(AddTouristResourcesDto addTouristResourcesDto) {
        boolean result = touristResourcesService.addTouristResources(addTouristResourcesDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "新增旅游资源成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "新增旅游资源失败！");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除单个旅游资源")
    public BaseResponse<?> deleteTouristResources(@PathVariable Integer id) {
        boolean result = touristResourcesService.deleteTouristResources(id);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "删除旅游资源成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "删除旅游资源失败！");
        }
    }

    @DeleteMapping("/batch")
    @Operation(summary = "批量删除旅游资源")
    public BaseResponse<?> deleteBatchTouristResources(Integer... touristResourcesIds) {
        boolean result = touristResourcesService.deleteBatchTouristResources(touristResourcesIds);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "批量删除旅游资源成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "批量删除旅游资源失败！");
        }
    }


}
