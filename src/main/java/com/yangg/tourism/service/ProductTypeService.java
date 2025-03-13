package com.yangg.tourism.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangg.tourism.domain.dto.tourist.AddProductTypeDto;
import com.yangg.tourism.domain.dto.tourist.QueryProductTypeDto;
import com.yangg.tourism.domain.dto.tourist.UpdateProductTypeDto;
import com.yangg.tourism.domain.entity.ProductType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yangg.tourism.domain.vo.tourist.ProductTypeVo;

/**
* @author yanqing
* @description 针对表【product_type(产品类型)】的数据库操作Service
* @createDate 2025-03-09 17:36:32
*/
public interface ProductTypeService extends IService<ProductType> {

    /**
     * 查询所有的商品类型
     * @param queryProductTypeDto 查询 dto
     * @return 返回查询结果
     */
    Page<ProductTypeVo> getAllProductTypes(QueryProductTypeDto queryProductTypeDto);

    /**
     * 更新商品类型
     * @param updateProductTypeDto 更新 dto
     * @return 返回更新结果
     */
    boolean updateProductType(UpdateProductTypeDto updateProductTypeDto);

    /**
     * 新增商品类型
     * @param addProductTypeDto 新增 dto
     * @return 新增结果
     */
    boolean addProductType(AddProductTypeDto addProductTypeDto);

    /**
     * 删除商品类型
     * @param id 要删除的 id
     * @return 返回删除结果
     */
    boolean deleteProductType(Integer id);

    /**
     * 批量删除商品类型
     * @param productTypeIds 要删除的 id 数组
     * @return 返回删除结果
     */
    boolean deleteBatchProductType(Integer... productTypeIds);
}
