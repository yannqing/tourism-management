package com.yangg.tourism.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangg.tourism.domain.dto.cost.AddProductCostTypeRelDto;
import com.yangg.tourism.domain.dto.cost.QueryProductCostTypeRelDto;
import com.yangg.tourism.domain.dto.cost.UpdateProductCostTypeRelDto;
import com.yangg.tourism.domain.entity.ProductCostTypeRel;
import com.yangg.tourism.domain.vo.tourist.ProductCostTypeRelVo;
import com.yangg.tourism.enums.ErrorType;
import com.yangg.tourism.exception.BusinessException;
import com.yangg.tourism.mapper.CostTypeMapper;
import com.yangg.tourism.mapper.ProductCostTypeRelMapper;
import com.yangg.tourism.mapper.ProductTypeMapper;
import com.yangg.tourism.service.ProductCostTypeRelService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
* @author yanqing
* @description 针对表【product_cost_type_rel(订单类型与商品类型关系表)】的数据库操作Service实现
* @createDate 2025-03-13 16:50:24
*/
@Slf4j
@Service
public class ProductCostTypeRelServiceImpl extends ServiceImpl<ProductCostTypeRelMapper, ProductCostTypeRel>
    implements ProductCostTypeRelService{

    @Resource
    private ProductTypeMapper productTypeMapper;

    @Resource
    private CostTypeMapper costTypeMapper;

    @Override
    public Page<ProductCostTypeRelVo> getAllProductCostTypeRel(QueryProductCostTypeRelDto queryProductCostTypeRelDto) {
        // 判空
        Optional.ofNullable(queryProductCostTypeRelDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Integer id = queryProductCostTypeRelDto.getId();
        Integer pid = queryProductCostTypeRelDto.getPid();
        Integer cid = queryProductCostTypeRelDto.getCid();

        QueryWrapper<ProductCostTypeRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id!= null, "id", id);
        queryWrapper.eq(pid!= null, "pid", pid);
        queryWrapper.eq(cid!= null, "cid", cid);
        log.info("查询所有商品类型与订单类型的关系");

        Page<ProductCostTypeRel> page = this.page(new Page<>(queryProductCostTypeRelDto.getCurrent(), queryProductCostTypeRelDto.getPageSize()), queryWrapper);

        List<ProductCostTypeRelVo> productCostTypeRelVoList = page.getRecords().stream().map(productCostTypeRel -> {
            ProductCostTypeRelVo productCostTypeRelVo = ProductCostTypeRelVo.productCostTypeRelToVo(productCostTypeRel);
            productCostTypeRelVo.setProductType(productTypeMapper.selectById(productCostTypeRel.getPid()));
            productCostTypeRelVo.setCostType(costTypeMapper.selectById(productCostTypeRel.getCid()));
            return productCostTypeRelVo;
        }).toList();

        return new Page<ProductCostTypeRelVo>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(productCostTypeRelVoList);
    }

    @Override
    public boolean updateProductCostTypeRel(UpdateProductCostTypeRelDto updateProductCostTypeRelDto) {
        // 判空
        Optional.ofNullable(updateProductCostTypeRelDto.getId())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 有效性判断
        Integer updateCostId = updateProductCostTypeRelDto.getId();
        Optional.ofNullable(this.getById(updateCostId))
                .orElseThrow(() -> new BusinessException(ErrorType.PRODUCT_COST_TYPE_REL_NOT_EXIST));

        ProductCostTypeRel updateCostType = UpdateProductCostTypeRelDto.objToProductCostTypeRel(updateProductCostTypeRelDto);

        boolean updateResult = this.updateById(updateCostType);
        log.info("更新商品类型与订单类型关系");

        return updateResult;
    }

    @Override
    public boolean addProductCostTypeRel(AddProductCostTypeRelDto addProductCostTypeRelDto) {
        // 判空
        Optional.ofNullable(addProductCostTypeRelDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        //
        ProductCostTypeRel isExistCostType = this.getOne(new QueryWrapper<ProductCostTypeRel>().eq("cid", addProductCostTypeRelDto.getCid()).eq("pid", addProductCostTypeRelDto.getPid()));
        if (isExistCostType != null) {
            throw new BusinessException(ErrorType.PRODUCT_COST_TYPE_REL_ALREADY_EXIST);
        }

        // 添加
        ProductCostTypeRel addCostType = AddProductCostTypeRelDto.objToProductCostTypeRel(addProductCostTypeRelDto);
        boolean saveResult = this.save(addCostType);
        log.info("新增商品类型与订单类型关系");

        return saveResult;
    }

    @Override
    public boolean deleteProductCostTypeRel(Integer id) {
        Optional.ofNullable(id)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Optional.ofNullable(this.getById(id))
                .orElseThrow(() -> new BusinessException(ErrorType.PRODUCT_COST_TYPE_REL_NOT_EXIST));

        boolean deleteResult = this.removeById(id);
        log.info("删除商品类型与订单类型关系");

        return deleteResult;
    }

    @Override
    public boolean deleteBatchProductCostTypeRel(Integer... productCostTypeRelIds) {
        // 判空
        if (productCostTypeRelIds == null || productCostTypeRelIds.length == 0) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 有效性判断
        List<ProductCostTypeRel> costList = this.listByIds(Arrays.asList(productCostTypeRelIds));
        if (costList.size()!= productCostTypeRelIds.length) {
            throw new BusinessException(ErrorType.PRODUCT_COST_TYPE_REL_NOT_EXIST);
        }

        // 批量删除
        int deleteResult = this.baseMapper.deleteBatchIds(Arrays.asList(productCostTypeRelIds));
        log.info("批量删除商品类型与订单类型关系");

        return deleteResult > 0;
    }
}




