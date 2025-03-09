package com.yangg.tourism.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangg.tourism.common.Code;
import com.yangg.tourism.domain.dto.tourist.AddProductTypeDto;
import com.yangg.tourism.domain.dto.tourist.QueryProductTypeDto;
import com.yangg.tourism.domain.dto.tourist.UpdateProductTypeDto;
import com.yangg.tourism.domain.entity.ProductType;
import com.yangg.tourism.domain.model.BaseResponse;
import com.yangg.tourism.service.ProductTypeService;
import com.yangg.tourism.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 产品类型管理
 * @author: yannqing
 * @create: 2025-02-09 10:03
 * @from: <更多资料：yannqing.com>
 **/
@Tag(name = "产品类型管理")
@RestController
@RequestMapping("/product-type")
public class ProductTypeController {

    @Resource
    private ProductTypeService productTypeService;

    @Operation(summary = "查询所有产品类型")
    @GetMapping
    public BaseResponse<?> getAllProductTypes(QueryProductTypeDto queryProductTypeDto) {
        Page<ProductType> costList = productTypeService.getAllProductTypes(queryProductTypeDto);
        return ResultUtils.success(Code.SUCCESS, costList, "查询全部产品类型成功！");
    }

    @PutMapping
    @Operation(summary = "更新产品类型")
    public BaseResponse<?> updateProductType(UpdateProductTypeDto updateProductTypeDto) {
        boolean result = productTypeService.updateProductType(updateProductTypeDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "修改产品类型成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "修改产品类型失败！");
        }
    }

    @Operation(summary = "添加新产品类型")
    @PostMapping
    public BaseResponse<?> addProductType(AddProductTypeDto addProductTypeDto) {
        boolean result = productTypeService.addProductType(addProductTypeDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "新增产品类型成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "新增产品类型失败！");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除单个产品类型")
    public BaseResponse<?> deleteProductType(@PathVariable Integer id) {
        boolean result = productTypeService.deleteProductType(id);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "删除产品类型成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "删除产品类型失败！");
        }
    }

    @DeleteMapping("/batch")
    @Operation(summary = "批量删除产品类型")
    public BaseResponse<?> deleteBatchProductType(Integer... productTypeIds) {
        boolean result = productTypeService.deleteBatchProductType(productTypeIds);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "批量删除产品类型成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "批量删除产品类型失败！");
        }
    }
}
