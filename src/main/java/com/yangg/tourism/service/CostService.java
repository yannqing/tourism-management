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

    /**
     * 查询所有费用
     * @param queryCostDto 查询所有费用的 dto
     * @return 返回分页查询结果
     */
    Page<Cost> getAllCosts(QueryCostDto queryCostDto);

    /**
     * 更新费用信息
     * @param updateCostDto 更新费用信息的 dto
     * @return 返回更新的结果
     */
    boolean updateCost(UpdateCostDto updateCostDto);

    /**
     * 新增费用
     * @param addCostDto 新增费用
     * @return 返回新增结果
     */
    boolean addCost(AddCostDto addCostDto);

    /**
     * 删除费用信息
     * @param costId 删除费用信息的id
     * @return 返回删除结果
     */
    boolean deleteCost(Integer costId);

    /**
     * 批量删除订单信息
     * @param costIds 要删除的 id 数组
     * @return 返回删除结果
     */
    boolean deleteBatchCost(Integer... costIds);

    /**
     * 创建订单
     * @param createOrderDto 创建订单的 dto
     * @param request 会话 session
     * @return 返回订单id
     * @throws JsonProcessingException json 解析异常
     */
    String createOrder(CreateOrderDto createOrderDto, HttpServletRequest request) throws JsonProcessingException;

    /**
     * 支付订单
     * @param orderNumber 订单号（创建订单时生成的）
     * @param request 会话 session
     * @return 返回支付结果
     * @throws JsonProcessingException json 解析异常
     */
    boolean payOrder(String orderNumber, HttpServletRequest request) throws JsonProcessingException;
}
