package com.yangg.tourism.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangg.tourism.domain.dto.tourist.AddTouristResourcesDto;
import com.yangg.tourism.domain.dto.tourist.QueryTouristResourcesDto;
import com.yangg.tourism.domain.dto.tourist.UpdateTouristResourcesDto;
import com.yangg.tourism.domain.entity.TouristResources;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author yanqing
* @description 针对表【tourist_resources】的数据库操作Service
* @createDate 2025-02-25 10:40:10
*/
public interface TouristResourcesService extends IService<TouristResources> {

    Page<TouristResources> getAllTouristResources(QueryTouristResourcesDto queryTouristResourcesDto);

    boolean updateTouristResources(UpdateTouristResourcesDto updateTouristResourcesDto);

    boolean addTouristResources(AddTouristResourcesDto addTouristResourcesDto);

    boolean deleteTouristResources(Integer id);

    boolean deleteBatchTouristResources(Integer... touristResourcesIds);
}
