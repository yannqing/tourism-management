package com.yangg.tourism.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangg.tourism.domain.dto.tourist.AddProductTypeDto;
import com.yangg.tourism.domain.dto.tourist.QueryProductTypeDto;
import com.yangg.tourism.domain.dto.tourist.UpdateProductTypeDto;
import com.yangg.tourism.domain.entity.CostType;
import com.yangg.tourism.domain.entity.ProductCostTypeRel;
import com.yangg.tourism.domain.entity.ProductType;
import com.yangg.tourism.enums.ErrorType;
import com.yangg.tourism.exception.BusinessException;
import com.yangg.tourism.mapper.CostTypeMapper;
import com.yangg.tourism.mapper.ProductCostTypeRelMapper;
import com.yangg.tourism.mapper.ProductTypeMapper;
import com.yangg.tourism.service.ProductTypeService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
* @author yanqing
* @description 针对表【product_type(产品类型)】的数据库操作Service实现
* @createDate 2025-03-09 17:36:32
*/
@Slf4j
@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType>
    implements ProductTypeService{

    @Resource
    private CostTypeMapper costTypeMapper;

    @Resource
    private ProductCostTypeRelMapper productCostTypeRelMapper;

    @Override
    public Page<ProductType> getAllProductTypes(QueryProductTypeDto queryProductTypeDto) {
        // 判空
        Optional.ofNullable(queryProductTypeDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Integer id = queryProductTypeDto.getId();
        String name = queryProductTypeDto.getName();
        String code = queryProductTypeDto.getCode();
        String unit = queryProductTypeDto.getUnit();
        String description = queryProductTypeDto.getDescription();


        QueryWrapper<ProductType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id!= null, "id", id);
        queryWrapper.like(name!= null, "name", name);
        queryWrapper.like(unit!= null, "unit", unit);
        queryWrapper.like(code!= null, "code", code);
        queryWrapper.like(description!= null, "description", description);
        log.info("查询所有产品类型");

        return this.page(new Page<>(queryProductTypeDto.getCurrent(), queryProductTypeDto.getPageSize()), queryWrapper);
    }

    @Override
    public boolean updateProductType(UpdateProductTypeDto updateProductTypeDto) {
        // 判空
        Optional.ofNullable(updateProductTypeDto.getId())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 有效性判断
        Integer updateProductTypeDtoId = updateProductTypeDto.getId();
        Optional.ofNullable(this.getById(updateProductTypeDtoId))
                .orElseThrow(() -> new BusinessException(ErrorType.PRODUCT_NOT_EXIST));

        ProductType updateProductType = UpdateProductTypeDto.objToProductType(updateProductTypeDto);

        boolean updateResult = this.updateById(updateProductType);
        log.info("更新产品类型");

        return updateResult;
    }

    @Override
    public boolean addProductType(AddProductTypeDto addProductTypeDto) {
        // 判空
        Optional.ofNullable(addProductTypeDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 产品类型编码不能重复
        ProductType isExistProductType = this.getOne(new QueryWrapper<ProductType>().eq("code", addProductTypeDto.getCode()));
        if (isExistProductType != null) {
            throw new BusinessException(ErrorType.PRODUCT_TYPE_CODE_REPEAT);
        }

        // 费用类型不能为空
        if (addProductTypeDto.getCostTypeId() == null) {
            throw new BusinessException(ErrorType.COST_TYPE_NOT_NULL);
        }

        // 费用类型 id 有效性判断
        CostType costType = costTypeMapper.selectById(addProductTypeDto.getCostTypeId());
        if (costType == null) {
            throw new BusinessException(ErrorType.COST_TYPE_NOT_EXIST);
        }

        // 添加产品类型
        ProductType addProductType = AddProductTypeDto.objToProductType(addProductTypeDto);
        boolean saveResult = this.save(addProductType);
        log.info("新增产品类型");

        // 新增与订单类型的关系
        ProductCostTypeRel productCostTypeRel = new ProductCostTypeRel();
        productCostTypeRel.setPid(addProductType.getId());
        productCostTypeRel.setCid(addProductTypeDto.getCostTypeId());
        productCostTypeRelMapper.insert(productCostTypeRel);
        log.info("新增产品类型与订单类型的关系");

        return saveResult;
    }

    @Override
    public boolean deleteProductType(Integer id) {
        Optional.ofNullable(id)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Optional.ofNullable(this.getById(id))
                .orElseThrow(() -> new BusinessException(ErrorType.PRODUCT_NOT_EXIST));

        boolean deleteResult = this.removeById(id);
        log.info("删除产品类型");

        return deleteResult;
    }

    @Override
    public boolean deleteBatchProductType(Integer... productTypeIds) {
        // 判空
        if (productTypeIds == null || productTypeIds.length == 0) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 有效性判断
        List<ProductType> productTypeList = this.listByIds(Arrays.asList(productTypeIds));
        if (productTypeList.size()!= productTypeIds.length) {
            throw new BusinessException(ErrorType.PRODUCT_NOT_EXIST);
        }

        // 批量删除
        int deleteResult = this.baseMapper.deleteBatchIds(Arrays.asList(productTypeIds));
        log.info("批量删除产品类型");

        return deleteResult > 0;
    }
}




