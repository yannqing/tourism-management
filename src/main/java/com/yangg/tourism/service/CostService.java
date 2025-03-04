package com.yangg.tourism.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.yangg.tourism.domain.dto.cost.AddCostDto;
import com.yangg.tourism.domain.dto.cost.CreateOrderDto;
import com.yangg.tourism.domain.dto.cost.QueryCostDto;
import com.yangg.tourism.domain.dto.cost.UpdateCostDto;
import com.yangg.tourism.domain.entity.Cost;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author 67121
* @description 针对表【cost】的数据库操作Service
* @createDate 2025-02-08 17:11:02
*/
public interface CostService extends IService<Cost> {

    Page<Cost> getAllCosts(QueryCostDto queryCostDto);

    boolean updateCost(UpdateCostDto updateCostDto);

    boolean addCost(AddCostDto addCostDto);

    boolean deleteCost(Integer costId);

    boolean deleteBatchCost(Integer... costIds);

    String createOrder(CreateOrderDto createOrderDto, HttpServletRequest request) throws JsonProcessingException;

    boolean payOrder(String orderNumber, HttpServletRequest request) throws JsonProcessingException;
}
