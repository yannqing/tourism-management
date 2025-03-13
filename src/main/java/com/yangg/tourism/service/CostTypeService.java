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


    /**
     * 查询全部费用类型
     * @param queryCostTypeDto 要查询的请求参数 dto
     * @return 返回查询的结果
     */
    Page<CostType> getAllCostTypes(QueryCostTypeDto queryCostTypeDto);

    /**
     * 更新费用类型
     * @param updateCostTypeDto 要更新的费用类型 dto
     * @return 返回更新结果
     */
    boolean updateCostType(UpdateCostTypeDto updateCostTypeDto);

    /**
     * 新增费用类型
     * @param addCostTypeDto 新增费用类型 dto
     * @return 新增结果
     */
    boolean addCostType(AddCostTypeDto addCostTypeDto);

    /**
     * 删除单个费用类型
     * @param id 要删除的 id
     * @return 返回删除结果
     */
    boolean deleteCostType(Integer id);

    /**
     * 批量删除费用类型
     * @param costTypeIds 删除的 id 数组
     * @return 返回删除结果
     */
    boolean deleteBatchCostType(Integer... costTypeIds);
}
