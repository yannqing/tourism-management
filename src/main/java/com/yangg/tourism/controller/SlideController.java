package com.yangg.tourism.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangg.tourism.annotation.AuthCheck;
import com.yangg.tourism.common.Code;
import com.yangg.tourism.domain.dto.slide.AddSlideDto;
import com.yangg.tourism.domain.dto.slide.QuerySlideDto;
import com.yangg.tourism.domain.dto.slide.UpdateSlideDto;
import com.yangg.tourism.domain.entity.Slide;
import com.yangg.tourism.domain.model.BaseResponse;
import com.yangg.tourism.service.SlideService;
import com.yangg.tourism.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 轮播图管理
 * @author: yannqing
 * @create: 2025-02-09 10:03
 * @from: <更多资料：yannqing.com>
 **/
@Tag(name = "轮播图管理")
@RestController
@RequestMapping("/slide")
public class SlideController {

    @Resource
    private SlideService slideService;

    @AuthCheck(code = "SLIDE_GET")
    @Operation(summary = "查询所有轮播图")
    @GetMapping
    public BaseResponse<?> getAllSlides(QuerySlideDto querySlideDto) {
        Page<Slide> costList = slideService.getAllSlides(querySlideDto);
        return ResultUtils.success(Code.SUCCESS, costList, "查询全部轮播图成功！");
    }

    @AuthCheck(code = "SLIDE_UPDATE")
    @PutMapping
    @Operation(summary = "更新轮播图")
    public BaseResponse<?> updateSlide(UpdateSlideDto updateSlideDto) {
        boolean result = slideService.updateSlide(updateSlideDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "修改轮播图成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "修改轮播图失败！");
        }
    }

    @AuthCheck(code = "SLIDE_ADD")
    @Operation(summary = "添加新轮播图")
    @PostMapping
    public BaseResponse<?> addSlide(AddSlideDto addSlideDto) {
        boolean result = slideService.addSlide(addSlideDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "新增轮播图成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "新增轮播图失败！");
        }
    }

    @AuthCheck(code = "SLIDE_DELETE_ONE")
    @DeleteMapping("/{id}")
    @Operation(summary = "删除单个轮播图")
    public BaseResponse<?> deleteSlide(@PathVariable Integer id) {
        boolean result = slideService.deleteSlide(id);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "删除轮播图成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "删除轮播图失败！");
        }
    }

    @AuthCheck(code = "SLIDE_DELETE_BATCH")
    @DeleteMapping("/batch")
    @Operation(summary = "批量删除轮播图")
    public BaseResponse<?> deleteBatchSlide(Integer... slideIds) {
        boolean result = slideService.deleteBatchSlide(slideIds);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "批量删除轮播图成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "批量删除轮播图失败！");
        }
    }


}
