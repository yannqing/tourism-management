package com.yangg.tourism.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangg.tourism.domain.dto.cost.AddCostTypeDto;
import com.yangg.tourism.domain.dto.cost.QueryCostTypeDto;
import com.yangg.tourism.domain.dto.cost.UpdateCostTypeDto;
import com.yangg.tourism.domain.entity.CostType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 67121
* @description 针对表【cost_type】的数据库操作Service
* @createDate 2025-02-08 17:11:15
*/
public interface CostTypeService extends IService<CostType> {

    Page<CostType> getAllCostTypes(QueryCostTypeDto queryCostTypeDto);

    boolean updateCostType(UpdateCostTypeDto updateCostTypeDto);

    boolean addCostType(AddCostTypeDto addCostTypeDto);

    boolean deleteCostType(Integer id);

    boolean deleteBatchCostType(Integer... costTypeIds);
}
