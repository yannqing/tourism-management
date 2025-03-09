package com.yangg.tourism.service;

import com.yangg.tourism.domain.entity.TouristProductRel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author yanqing
* @description 针对表【tourist_product_rel(产品和类型关系表)】的数据库操作Service
* @createDate 2025-03-09 17:38:24
*/
public interface TouristProductRelService extends IService<TouristProductRel> {

    /**
     * 根据商品 id 和类型 id，新增绑定关系
     * @param touristId 商品 id
     * @param productId 类型 id
     */
    void add(Integer touristId, Integer productId);

    /**
     * 根据商品 id 删除这个商品的类型
     * @param touristId 商品id
     */
    void deleteByTid(Integer touristId);

}
