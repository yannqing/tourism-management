package com.yangg.tourism.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangg.tourism.domain.dto.cost.AddCostDto;
import com.yangg.tourism.domain.dto.cost.QueryCostDto;
import com.yangg.tourism.domain.dto.cost.UpdateCostDto;
import com.yangg.tourism.domain.entity.Cost;
import com.yangg.tourism.enums.ErrorType;
import com.yangg.tourism.exception.BusinessException;
import com.yangg.tourism.service.CostService;
import com.yangg.tourism.mapper.CostMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
* @author 67121
* @description 针对表【cost】的数据库操作Service实现
* @createDate 2025-02-08 17:11:02
*/
@Slf4j
@Service
public class CostServiceImpl extends ServiceImpl<CostMapper, Cost>
    implements CostService{

    /**
     * 查询所有费用
     * @param queryCostDto 查询所有费用的 dto
     * @return 返回分页查询结果
     */
    @Override
    public Page<Cost> getAllCosts(QueryCostDto queryCostDto) {
        // 判空
        Optional.ofNullable(queryCostDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Integer id = queryCostDto.getId();
        String name = queryCostDto.getName();
        Integer type = queryCostDto.getType();
        Integer paymentMethod = queryCostDto.getPaymentMethod();
        BigDecimal amount = queryCostDto.getAmount();
        Integer consumer = queryCostDto.getConsumer();
        Integer payee = queryCostDto.getPayee();
        Integer status = queryCostDto.getStatus();
        Date expenseTime = queryCostDto.getExpenseTime();

        QueryWrapper<Cost> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id!= null, "id", id);
        queryWrapper.like(name!= null, "name", name);
        queryWrapper.eq(type!= null, "type", type);
        queryWrapper.eq(paymentMethod!= null, "paymentMethod", paymentMethod);
        queryWrapper.eq(amount!= null, "amount", amount);
        queryWrapper.eq(consumer!= null, "consumer", consumer);
        queryWrapper.eq(payee!= null, "payee", payee);
        queryWrapper.eq(status!= null, "status", status);
        queryWrapper.eq(expenseTime!= null, "expenseTime", expenseTime);
        log.info("查询所有费用");

        return this.page(new Page<>(queryCostDto.getCurrent(), queryCostDto.getPageSize()), queryWrapper);
    }

    /**
     * 更新费用信息
     * @param updateCostDto 更新费用信息的 dto
     * @return 返回更新的结果
     */
    @Override
    public boolean updateCost(UpdateCostDto updateCostDto) {
        // 判空
        Optional.ofNullable(updateCostDto.getId())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 有效性判断
        Integer updateCostId = updateCostDto.getId();
        Optional.ofNullable(this.getById(updateCostId))
                .orElseThrow(() -> new BusinessException(ErrorType.COST_NOT_EXIST));

        Cost updateCost = UpdateCostDto.objToCost(updateCostDto);

        boolean updateResult = this.updateById(updateCost);
        log.info("更新费用信息");
        
        return updateResult;
    }

    /**
     * 新增费用
     * @param addCostDto 新增费用
     * @return
     */
    @Override
    public boolean addCost(AddCostDto addCostDto) {
        // 判空
        Optional.ofNullable(addCostDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Cost addCost = AddCostDto.objToCost(addCostDto);

        boolean saveResult = this.save(addCost);
        log.info("新增费用");

        return saveResult;
    }

    /**
     * 删除费用信息
     * @param costId 删除费用信息的id
     * @return 返回删除结果
     */
    @Override
    public boolean deleteCost(Integer costId) {
        Optional.ofNullable(costId)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Optional.ofNullable(this.getById(costId))
                .orElseThrow(() -> new BusinessException(ErrorType.COST_NOT_EXIST));

        boolean deleteResult = this.removeById(costId);
        log.info("删除费用信息");

        return deleteResult;
    }

    @Override
    public boolean deleteBatchCost(Integer... costIds) {
        // 判空
        if (costIds == null || costIds.length == 0) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

       // 有效性判断
        List<Cost> costList = this.listByIds(Arrays.asList(costIds));
        if (costList.size()!= costIds.length) {
            throw new BusinessException(ErrorType.COST_NOT_EXIST);
        }

        // 批量删除
        int deleteResult = this.baseMapper.deleteBatchIds(Arrays.asList(costIds));
        log.info("批量删除费用信息");

        return deleteResult > 0;
    }
}




