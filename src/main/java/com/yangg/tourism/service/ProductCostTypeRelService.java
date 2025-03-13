package com.yangg.tourism.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yangg.tourism.domain.dto.cost.AddProductCostTypeRelDto;
import com.yangg.tourism.domain.dto.cost.QueryProductCostTypeRelDto;
import com.yangg.tourism.domain.dto.cost.UpdateProductCostTypeRelDto;
import com.yangg.tourism.domain.entity.ProductCostTypeRel;
import com.yangg.tourism.domain.vo.tourist.ProductCostTypeRelVo;

/**
* @author yanqing
* @description 针对表【product_cost_type_rel(订单类型与商品类型关系表)】的数据库操作Service
* @createDate 2025-03-13 16:50:24
*/
public interface ProductCostTypeRelService extends IService<ProductCostTypeRel> {

    /**
     * 查询所有的商品类型与订单类型的关系（封装后的结果）
     * @param queryProductCostTypeRelDto 查询 dto
     * @return 返回查询结果
     */
    Page<ProductCostTypeRelVo> getAllProductCostTypeRel(QueryProductCostTypeRelDto queryProductCostTypeRelDto);

    /**
     * 更新商品类型与订单类型的关系
     * @param updateProductCostTypeRelDto 更新 dto
     * @return 返回更新结果
     */
    boolean updateProductCostTypeRel(UpdateProductCostTypeRelDto updateProductCostTypeRelDto);

    /**
     * 新增商品类型与订单类型的关系
     * @param addProductCostTypeRelDto 新增 dto
     * @return 返回新增结果
     */
    boolean addProductCostTypeRel(AddProductCostTypeRelDto addProductCostTypeRelDto);

    /**
     * 删除商品类型与订单类型的关系
     * @param id 要删除的 id
     * @return 返回删除结果
     */
    boolean deleteProductCostTypeRel(Integer id);

    /**
     * 批量删除商品类型与订单类型的关系
     * @param productCostTypeRelIds 要删除的 id 数组
     * @return 返回删除的结果
     */
    boolean deleteBatchProductCostTypeRel(Integer... productCostTypeRelIds);
}
