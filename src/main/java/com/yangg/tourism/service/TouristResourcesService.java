package com.yangg.tourism.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.yangg.tourism.domain.dto.tourist.AddTouristResourcesDto;
import com.yangg.tourism.domain.dto.tourist.QueryMerchantsResourcesDto;
import com.yangg.tourism.domain.dto.tourist.QueryTouristResourcesDto;
import com.yangg.tourism.domain.dto.tourist.UpdateTouristResourcesDto;
import com.yangg.tourism.domain.entity.TouristResources;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author yanqing
* @description 针对表【tourist_resources】的数据库操作Service
* @createDate 2025-02-25 10:40:10
*/
public interface TouristResourcesService extends IService<TouristResources> {

    Page<TouristResources> getAllTouristResources(QueryTouristResourcesDto queryTouristResourcesDto);

    Page<TouristResources> getAllTouristResourcesByUser(QueryTouristResourcesDto queryTouristResourcesDto);

    Page<TouristResources> getAllTouristResourcesByMerchants(QueryMerchantsResourcesDto queryMerchantsResourcesDto, HttpServletRequest request) throws JsonProcessingException;

    boolean updateTouristResources(UpdateTouristResourcesDto updateTouristResourcesDto, HttpServletRequest request) throws JsonProcessingException;

    boolean addTouristResources(AddTouristResourcesDto addTouristResourcesDto, HttpServletRequest request) throws JsonProcessingException;

    boolean deleteTouristResources(Integer id, HttpServletRequest request) throws JsonProcessingException;

    boolean deleteBatchTouristResources(Integer... touristResourcesIds);

    Page<TouristResources> getRecommendTouristResources();
}
