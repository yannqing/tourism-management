package com.yangg.tourism.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangg.tourism.common.Code;
import com.yangg.tourism.domain.dto.cost.AddProductCostTypeRelDto;
import com.yangg.tourism.domain.dto.cost.QueryProductCostTypeRelDto;
import com.yangg.tourism.domain.dto.cost.UpdateProductCostTypeRelDto;
import com.yangg.tourism.domain.model.BaseResponse;
import com.yangg.tourism.domain.vo.tourist.ProductCostTypeRelVo;
import com.yangg.tourism.service.ProductCostTypeRelService;
import com.yangg.tourism.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 订单类型与商品类型关系管理
 * @author: yannqing
 * @create: 2025-02-09 10:03
 * @from: <更多资料：yannqing.com>
 **/
@Deprecated
@Tag(name = "订单类型与商品类型关系管理")
@RestController
@RequestMapping("/productCostTypeRel")
public class ProductCostTypeRelController {

    @Resource
    private ProductCostTypeRelService productCostTypeRelService;

    @Operation(summary = "查询所有订单类型与商品类型关系")
    @GetMapping
    public BaseResponse<?> getAllProductCostTypeRel(QueryProductCostTypeRelDto queryProductCostTypeRelDto) {
        Page<ProductCostTypeRelVo> costList = productCostTypeRelService.getAllProductCostTypeRel(queryProductCostTypeRelDto);
        return ResultUtils.success(Code.SUCCESS, costList, "查询全部订单类型与商品类型关系成功！");
    }

    @PutMapping
    @Operation(summary = "更新订单类型与商品类型关系")
    public BaseResponse<?> updateProductCostTypeRel(UpdateProductCostTypeRelDto updateProductCostTypeRelDto) {
        boolean result = productCostTypeRelService.updateProductCostTypeRel(updateProductCostTypeRelDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "修改订单类型与商品类型关系成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "修改订单类型与商品类型关系失败！");
        }
    }

    @Operation(summary = "添加新订单类型与商品类型关系")
    @PostMapping
    public BaseResponse<?> addProductCostTypeRel(AddProductCostTypeRelDto addProductCostTypeRelDto) {
        boolean result = productCostTypeRelService.addProductCostTypeRel(addProductCostTypeRelDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "新增订单类型与商品类型关系成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "新增订单类型与商品类型关系失败！");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除单个订单类型与商品类型关系")
    public BaseResponse<?> deleteProductCostTypeRel(@PathVariable Integer id) {
        boolean result = productCostTypeRelService.deleteProductCostTypeRel(id);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "删除订单类型与商品类型关系成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "删除订单类型与商品类型关系失败！");
        }
    }

    @DeleteMapping("/batch")
    @Operation(summary = "批量删除订单类型与商品类型关系")
    public BaseResponse<?> deleteBatchProductCostTypeRel(Integer... productCostTypeRelIds) {
        boolean result = productCostTypeRelService.deleteBatchProductCostTypeRel(productCostTypeRelIds);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "批量删除订单类型与商品类型关系成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "批量删除订单类型与商品类型关系失败！");
        }
    }


}
