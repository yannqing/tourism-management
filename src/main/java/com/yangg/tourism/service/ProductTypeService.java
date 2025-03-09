package com.yangg.tourism.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangg.tourism.domain.dto.tourist.AddProductTypeDto;
import com.yangg.tourism.domain.dto.tourist.QueryProductTypeDto;
import com.yangg.tourism.domain.dto.tourist.UpdateProductTypeDto;
import com.yangg.tourism.domain.entity.ProductType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author yanqing
* @description 针对表【product_type(产品类型)】的数据库操作Service
* @createDate 2025-03-09 17:36:32
*/
public interface ProductTypeService extends IService<ProductType> {

    Page<ProductType> getAllProductTypes(QueryProductTypeDto queryProductTypeDto);

    boolean updateProductType(UpdateProductTypeDto updateProductTypeDto);

    boolean addProductType(AddProductTypeDto addProductTypeDto);

    boolean deleteProductType(Integer id);

    boolean deleteBatchProductType(Integer... productTypeIds);
}
