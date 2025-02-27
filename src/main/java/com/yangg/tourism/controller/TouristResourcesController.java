package com.yangg.tourism.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.yangg.tourism.common.Code;
import com.yangg.tourism.domain.dto.tourist.AddTouristResourcesDto;
import com.yangg.tourism.domain.dto.tourist.QueryMerchantsResourcesDto;
import com.yangg.tourism.domain.dto.tourist.QueryTouristResourcesDto;
import com.yangg.tourism.domain.dto.tourist.UpdateTouristResourcesDto;
import com.yangg.tourism.domain.entity.TouristResources;
import com.yangg.tourism.domain.model.BaseResponse;
import com.yangg.tourism.domain.vo.tourist.TourismResourcesVo;
import com.yangg.tourism.service.TouristResourcesService;
import com.yangg.tourism.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
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

    @Operation(summary = "查询所有旅游资源（管理员）")
    @GetMapping
    public BaseResponse<?> getAllTouristResources(QueryTouristResourcesDto queryTouristResourcesDto) {
        Page<TouristResources> costList = touristResourcesService.getAllTouristResources(queryTouristResourcesDto);
        return ResultUtils.success(Code.SUCCESS, costList, "查询全部旅游资源成功！");
    }

    @Operation(summary = "查询自己的旅游资源（商户）")
    @GetMapping("/merchants")
    public BaseResponse<?> getAllTouristResources(QueryMerchantsResourcesDto queryMerchantsResourcesDto, HttpServletRequest request) throws JsonProcessingException {
        Page<TourismResourcesVo> costList = touristResourcesService.getAllTouristResourcesByMerchants(queryMerchantsResourcesDto, request);
        return ResultUtils.success(Code.SUCCESS, costList, "查询全部旅游资源成功！");
    }

    @PutMapping
    @Operation(summary = "更新旅游资源（管理员，商户）")
    public BaseResponse<?> updateTouristResources(UpdateTouristResourcesDto updateTouristResourcesDto, HttpServletRequest request) throws JsonProcessingException {
        boolean result = touristResourcesService.updateTouristResources(updateTouristResourcesDto, request);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "修改旅游资源成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "修改旅游资源失败！");
        }
    }

    @Operation(summary = "添加新旅游资源（管理员，商户）")
    @PostMapping
    public BaseResponse<?> addTouristResources(AddTouristResourcesDto addTouristResourcesDto, HttpServletRequest request) throws JsonProcessingException {
        boolean result = touristResourcesService.addTouristResources(addTouristResourcesDto, request);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "新增旅游资源成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "新增旅游资源失败！");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除单个旅游资源（管理员）")
    public BaseResponse<?> deleteTouristResources(@PathVariable Integer id, HttpServletRequest request) throws JsonProcessingException {
        boolean result = touristResourcesService.deleteTouristResources(id, request);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "删除旅游资源成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "删除旅游资源失败！");
        }
    }

    @DeleteMapping("/batch")
    @Operation(summary = "批量删除旅游资源（管理员）")
    public BaseResponse<?> deleteBatchTouristResources(Integer... touristResourcesIds) {
        boolean result = touristResourcesService.deleteBatchTouristResources(touristResourcesIds);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "批量删除旅游资源成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "批量删除旅游资源失败！");
        }
    }




}
