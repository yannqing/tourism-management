package com.yannqing.template.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yannqing.template.domain.dto.cost.AddCostTypeDto;
import com.yannqing.template.domain.dto.cost.QueryCostTypeDto;
import com.yannqing.template.domain.dto.cost.UpdateCostTypeDto;
import com.yannqing.template.domain.entity.CostType;
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
