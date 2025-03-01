package com.yangg.tourism.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangg.tourism.domain.dto.cost.*;
import com.yangg.tourism.domain.entity.CostType;
import com.yangg.tourism.enums.ErrorType;
import com.yangg.tourism.exception.BusinessException;
import com.yangg.tourism.service.CostTypeService;
import com.yangg.tourism.mapper.CostTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
* @author 67121
* @description 针对表【cost_type】的数据库操作Service实现
* @createDate 2025-02-08 17:11:15
*/
@Slf4j
@Service
public class CostTypeServiceImpl extends ServiceImpl<CostTypeMapper, CostType>
    implements CostTypeService{

    /**
     * 查询全部费用类型
     * @param queryCostTypeDto 要查询的请求参数 dto
     * @return 返回查询的结果
     */
    @Override
    public Page<CostType> getAllCostTypes(QueryCostTypeDto queryCostTypeDto) {
        // 判空
        Optional.ofNullable(queryCostTypeDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Integer id = queryCostTypeDto.getId();
        Integer pid = queryCostTypeDto.getPid();
        String name = queryCostTypeDto.getName();
        String code = queryCostTypeDto.getCode();
        String description = queryCostTypeDto.getDescription();


        QueryWrapper<CostType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id!= null, "id", id);
        queryWrapper.like(name!= null, "name", name);
        queryWrapper.eq(pid!= null, "pid", pid);
        queryWrapper.like(code!= null, "code", code);
        queryWrapper.like(description!= null, "description", description);
        log.info("查询所有费用类型");

        return this.page(new Page<>(queryCostTypeDto.getCurrent(), queryCostTypeDto.getPageSize()), queryWrapper);
    }

    /**
     * 更新费用类型
     * @param updateCostTypeDto 要更新的费用类型 dto
     * @return 返回更新结果
     */
    @Override
    public boolean updateCostType(UpdateCostTypeDto updateCostTypeDto) {
        // 判空
        Optional.ofNullable(updateCostTypeDto.getId())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 有效性判断
        Integer updateCostId = updateCostTypeDto.getId();
        Optional.ofNullable(this.getById(updateCostId))
                .orElseThrow(() -> new BusinessException(ErrorType.COST_NOT_EXIST));

        CostType updateCostType = UpdateCostTypeDto.objToCostType(updateCostTypeDto);

        boolean updateResult = this.updateById(updateCostType);
        log.info("更新费用类型");

        return updateResult;
    }

    /**
     * 新增费用类型
     * @param addCostTypeDto 新增费用类型 dto
     * @return 新增结果
     */
    @Override
    public boolean addCostType(AddCostTypeDto addCostTypeDto) {
        // 判空
        Optional.ofNullable(addCostTypeDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 费用类型编码不能重复
        CostType isExistCostType = this.getOne(new QueryWrapper<CostType>().eq("code", addCostTypeDto.getCode()));
        if (isExistCostType != null) {
            throw new BusinessException(ErrorType.COST_TYPE_CODE_REPEAT);
        }

        // 添加费用类型
        CostType addCostType = AddCostTypeDto.objToCostType(addCostTypeDto);
        boolean saveResult = this.save(addCostType);
        log.info("新增费用类型");

        return saveResult;
    }

    /**
     * 删除单个费用类型
     * @param id 要删除的 id
     * @return 返回删除结果
     */
    @Override
    public boolean deleteCostType(Integer id) {
        Optional.ofNullable(id)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Optional.ofNullable(this.getById(id))
                .orElseThrow(() -> new BusinessException(ErrorType.COST_NOT_EXIST));

        boolean deleteResult = this.removeById(id);
        log.info("删除费用类型");

        return deleteResult;
    }

    /**
     * 批量删除费用类型
     * @param costTypeIds 删除的 id 数组
     * @return 返回删除结果
     */
    @Override
    public boolean deleteBatchCostType(Integer... costTypeIds) {
        // 判空
        if (costTypeIds == null || costTypeIds.length == 0) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 有效性判断
        List<CostType> costList = this.listByIds(Arrays.asList(costTypeIds));
        if (costList.size()!= costTypeIds.length) {
            throw new BusinessException(ErrorType.COST_NOT_EXIST);
        }

        // 批量删除
        int deleteResult = this.baseMapper.deleteBatchIds(Arrays.asList(costTypeIds));
        log.info("批量删除费用类型");

        return deleteResult > 0;
    }
}




