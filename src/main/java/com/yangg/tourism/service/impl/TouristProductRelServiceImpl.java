package com.yangg.tourism.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangg.tourism.domain.entity.TouristProductRel;
import com.yangg.tourism.mapper.TouristProductRelMapper;
import com.yangg.tourism.service.TouristProductRelService;
import org.springframework.stereotype.Service;

/**
* @author yanqing
* @description 针对表【tourist_product_rel(产品和类型关系表)】的数据库操作Service实现
* @createDate 2025-03-09 17:38:24
*/
@Service
public class TouristProductRelServiceImpl extends ServiceImpl<TouristProductRelMapper, TouristProductRel>
    implements TouristProductRelService{

    @Override
    public void add(Integer touristId, Integer productId) {
        TouristProductRel touristProductRel = new TouristProductRel();
        touristProductRel.setTid(touristId);
        touristProductRel.setPid(productId);

        this.save(touristProductRel);
    }

    @Override
    public void deleteByTid(Integer touristId) {
        this.remove(new QueryWrapper<TouristProductRel>().eq("tid", touristId));
    }
}




