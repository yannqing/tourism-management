package com.yannqing.template.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yannqing.template.domain.dto.cost.AddCostDto;
import com.yannqing.template.domain.dto.cost.QueryCostDto;
import com.yannqing.template.domain.dto.cost.UpdateCostDto;
import com.yannqing.template.domain.entity.Cost;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
}
