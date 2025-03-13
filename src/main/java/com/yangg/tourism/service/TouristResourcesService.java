package com.yangg.tourism.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.yangg.tourism.domain.dto.tourist.AddTouristResourcesDto;
import com.yangg.tourism.domain.dto.tourist.QueryMerchantsResourcesDto;
import com.yangg.tourism.domain.dto.tourist.QueryTouristResourcesDto;
import com.yangg.tourism.domain.dto.tourist.UpdateTouristResourcesDto;
import com.yangg.tourism.domain.entity.TouristResources;
import com.yangg.tourism.domain.vo.tourist.TourismResourcesVo2;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author yanqing
* @description 针对表【tourist_resources】的数据库操作Service
* @createDate 2025-02-25 10:40:10
*/
public interface TouristResourcesService extends IService<TouristResources> {

    /**
     * 查询所有旅游资源（管理员）
     * @param queryTouristResourcesDto 查询 dto
     * @return 返回封装结果
     */
    Page<TourismResourcesVo2> getAllTouristResources(QueryTouristResourcesDto queryTouristResourcesDto);

    /**
     * 查询所有旅游资源（普通用户）
     * @param queryTouristResourcesDto 查询 dto
     * @return 返回封装结果
     */
    Page<TourismResourcesVo2> getAllTouristResourcesByUser(QueryTouristResourcesDto queryTouristResourcesDto);

    /**
     * 查询自己负责的旅游资源（商户）
     * @param queryMerchantsResourcesDto 查询dto
     * @param request 会话 session
     * @return 返回结果
     * @throws JsonProcessingException json 解析异常
     */
    Page<TourismResourcesVo2> getAllTouristResourcesByMerchants(QueryMerchantsResourcesDto queryMerchantsResourcesDto, HttpServletRequest request) throws JsonProcessingException;

    /**
     * 更新旅游资源（商户，管理员）
     * @param updateTouristResourcesDto 更新 dto
     * @param request 会话 session
     * @return 返回更新结果
     * @throws JsonProcessingException json 解析异常
     */
    boolean updateTouristResources(UpdateTouristResourcesDto updateTouristResourcesDto, HttpServletRequest request) throws JsonProcessingException;

    /**
     * 新增旅游资源（管理员，商户）
     * @param addTouristResourcesDto 新增 dto
     * @param request 会话 session
     * @return 返回新增结果
     * @throws JsonProcessingException json 解析异常
     */
    boolean addTouristResources(AddTouristResourcesDto addTouristResourcesDto, HttpServletRequest request) throws JsonProcessingException;

    /**
     * 删除单个旅游资源
     * @param id 删除的id
     * @param request 会话session
     * @return 返回删除结果
     * @throws JsonProcessingException json 解析异常
     */
    boolean deleteTouristResources(Integer id, HttpServletRequest request) throws JsonProcessingException;

    /**
     * 批量删除旅游资源
     * @param touristResourcesIds 旅游资源数组 id
     * @return 返回删除结果
     */
    boolean deleteBatchTouristResources(Integer... touristResourcesIds);

    /**
     * 查询推荐旅游资源（根据评分）
     * @return 返回封装结果
     */
    Page<TourismResourcesVo2> getRecommendTouristResources();
}
