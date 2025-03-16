package com.yangg.tourism.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.yangg.tourism.domain.dto.cost.AddCostDto;
import com.yangg.tourism.domain.dto.cost.CreateOrderDto;
import com.yangg.tourism.domain.dto.cost.QueryCostDto;
import com.yangg.tourism.domain.dto.cost.UpdateCostDto;
import com.yangg.tourism.domain.entity.*;
import com.yangg.tourism.enums.ErrorType;
import com.yangg.tourism.enums.RoleType;
import com.yangg.tourism.exception.BusinessException;
import com.yangg.tourism.mapper.*;
import com.yangg.tourism.service.CostService;
import com.yangg.tourism.service.UserService;
import com.yangg.tourism.utils.JwtUtils;
import com.yangg.tourism.utils.RedisCache;
import com.yangg.tourism.utils.Tools;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
* @author 67121
* @description 针对表【cost】的数据库操作Service实现
* @createDate 2025-02-08 17:11:02
*/
@Slf4j
@Service
public class CostServiceImpl extends ServiceImpl<CostMapper, Cost>
    implements CostService{

    @Resource
    private TouristResourcesMapper touristResourcesMapper;

    @Resource
    private UserTouristMapper userTouristMapper;

    @Resource
    private RedisCache redisCache;

    @Resource
    private UserService userService;

    @Resource
    private TouristProductRelMapper touristProductRelMapper;

    @Resource
    private ProductCostTypeRelMapper productCostTypeRelMapper;

    @Override
    public Page<Cost> getAllCosts(QueryCostDto queryCostDto) {
        // 判空
        Optional.ofNullable(queryCostDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Integer id = queryCostDto.getId();
        String name = queryCostDto.getName();
        Integer type = queryCostDto.getType();
        Integer commodityId = queryCostDto.getCommodityId();
        String orderNumber = queryCostDto.getOrderNumber();
        Integer paymentMethod = queryCostDto.getPaymentMethod();
        BigDecimal amount = queryCostDto.getAmount();
        Integer consumer = queryCostDto.getConsumer();
        Integer payee = queryCostDto.getPayee();
        Integer status = queryCostDto.getStatus();
        Date expenseTime = queryCostDto.getExpenseTime();

        QueryWrapper<Cost> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id!= null, "id", id);
        queryWrapper.eq(commodityId!= null, "commodityId", commodityId);
        queryWrapper.like(name!= null, "name", name);
        queryWrapper.like(orderNumber!= null, "orderNumber", orderNumber);
        queryWrapper.eq(type!= null, "type", type);
        queryWrapper.eq(paymentMethod!= null, "paymentMethod", paymentMethod);
        queryWrapper.eq(amount!= null, "amount", amount);
        queryWrapper.eq(consumer!= null, "consumer", consumer);
        queryWrapper.eq(payee!= null, "payee", payee);
        queryWrapper.eq(status!= null, "status", status);
        queryWrapper.eq(expenseTime!= null, "expenseTime", expenseTime);
        log.info("查询所有费用（管理员）");

        return this.page(new Page<>(queryCostDto.getCurrent(), queryCostDto.getPageSize()), queryWrapper);
    }

    @Override
    public Page<Cost> getAllCostsByUser(QueryCostDto queryCostDto, Integer userId) {
        // 判空
        Optional.ofNullable(queryCostDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        if (userId == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        User loginUser = userService.getBaseMapper().selectById(userId);
        if (loginUser == null) {
            throw new BusinessException(ErrorType.USER_NOT_EXIST);
        }

        // 构建查询条件
        Integer id = queryCostDto.getId();
        String name = queryCostDto.getName();
        Integer type = queryCostDto.getType();
        Integer commodityId = queryCostDto.getCommodityId();
        String orderNumber = queryCostDto.getOrderNumber();
        Integer paymentMethod = queryCostDto.getPaymentMethod();
        BigDecimal amount = queryCostDto.getAmount();
        Integer status = queryCostDto.getStatus();
        Date expenseTime = queryCostDto.getExpenseTime();

        QueryWrapper<Cost> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id!= null, "id", id);
        queryWrapper.eq(commodityId!= null, "commodityId", commodityId);
        queryWrapper.like(name!= null, "name", name);
        queryWrapper.like(orderNumber!= null, "orderNumber", orderNumber);
        queryWrapper.eq(type!= null, "type", type);
        queryWrapper.eq(paymentMethod!= null, "paymentMethod", paymentMethod);
        queryWrapper.eq(amount!= null, "amount", amount);
        // 只能查询自己的订单
        queryWrapper.eq("consumer", userId);
        queryWrapper.eq(status!= null, "status", status);
        queryWrapper.eq(expenseTime!= null, "expenseTime", expenseTime);
        queryWrapper.orderByDesc("createTime");

        log.info("查询所有费用（普通用户）");
        return this.page(new Page<>(queryCostDto.getCurrent(), queryCostDto.getPageSize()), queryWrapper);
    }

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

    @Override
    public String createOrder(CreateOrderDto createOrderDto, HttpServletRequest request) throws JsonProcessingException {
        if (createOrderDto == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        User loginUser = JwtUtils.getUserFromToken(request.getHeader("token"));
        if (loginUser == null) {
            throw new BusinessException(ErrorType.TOKEN_INVALID);
        }

        Integer commodityId = createOrderDto.getCommodityId();
        if (commodityId == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }
        TouristResources touristResources = touristResourcesMapper.selectOne(new QueryWrapper<TouristResources>().eq("id", commodityId).eq("typeId", 5));
        if (touristResources == null) {
            throw new BusinessException(ErrorType.TOURIST_NOT_EXIST);
        }

        UserTourist userTourist = userTouristMapper.selectOne(new QueryWrapper<UserTourist>().eq("tid", touristResources.getPid()));
        if (userTourist == null) {
            throw new BusinessException(ErrorType.PRODUCT_HAVE_NOT_USER);
        }

        // 根据商品的类型来判断订单类型
        TouristProductRel touristProductRel = touristProductRelMapper.selectOne(new QueryWrapper<TouristProductRel>().eq("tid", touristResources.getId()));
        if (touristProductRel == null) {
            throw new BusinessException(ErrorType.PRODUCT_TYPE_NOT_EXIST);
        }
        ProductCostTypeRel productCostTypeRel = productCostTypeRelMapper.selectOne(new QueryWrapper<ProductCostTypeRel>().eq("pid", touristProductRel.getPid()));
        if (productCostTypeRel == null) {
            throw new BusinessException(ErrorType.COST_TYPE_NOT_EXIST);
        }

        Cost addCost = CreateOrderDto.objToCost(createOrderDto);
        addCost.setConsumer(loginUser.getUserId());
        addCost.setPayee(userTourist.getUid());
        addCost.setType(productCostTypeRel.getCid());
        String orderNumber = Tools.generateOrderNumber();
        addCost.setOrderNumber(orderNumber);
        addCost.setStatus(0);
        boolean saveResult = this.save(addCost);
        log.info("创建订单 商品 id：{} 用户 id：{} 订单 id：{}", commodityId, loginUser.getUserId(), addCost.getId());

        // 创建的订单只存在 15min
        redisCache.setCacheObject(loginUser.getUserId() + ":" + orderNumber, loginUser.getUserId() , 15 * 60, TimeUnit.SECONDS);

        return orderNumber;
    }

    @Override
    public boolean payOrder(String orderNumber, HttpServletRequest request) throws JsonProcessingException {
        User loginUser = JwtUtils.getUserFromToken(request.getHeader("token"));
        if (loginUser == null) {
            throw new BusinessException(ErrorType.TOKEN_INVALID);
        }

        if (orderNumber == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        Cost order = this.getOne(new QueryWrapper<Cost>().eq("orderNumber", orderNumber));
        if (order == null) {
            throw new BusinessException(ErrorType.ORDER_NOT_EXIST);
        }

        Object cacheObject = redisCache.getCacheObject(loginUser.getUserId() + ":" + orderNumber);
        if (cacheObject == null) {
            order.setStatus(-1);
            this.updateById(order);
            throw new BusinessException(ErrorType.ORDER_INVALID);
        }

        order.setExpenseTime(new Date());
        order.setStatus(1);
        order.setPaymentMethod(0);

        boolean payResult = this.updateById(order);
        log.info("用户 id：{} 支付订单 id：{} ", loginUser.getUserId(), orderNumber);

        return payResult;
    }

    @Override
    public Page<Cost> getAllCostsByMerchants(QueryCostDto queryCostDto, Integer userId) {
        // 判空
        Optional.ofNullable(queryCostDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        if (userId == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        User loginUser = userService.getBaseMapper().selectById(userId);
        if (loginUser == null) {
            throw new BusinessException(ErrorType.USER_NOT_EXIST);
        }

        List<Role> roles = userService.getRoleByUser(userId);
        Role role = roles.get(0);
        if (role == null || role.getId() != RoleType.OTHER.getRoleId()) {
            throw new BusinessException(ErrorType.PERMISSION_NOT);
        }

        // 构建查询条件
        Integer id = queryCostDto.getId();
        String name = queryCostDto.getName();
        Integer type = queryCostDto.getType();
        Integer commodityId = queryCostDto.getCommodityId();
        String orderNumber = queryCostDto.getOrderNumber();
        Integer paymentMethod = queryCostDto.getPaymentMethod();
        BigDecimal amount = queryCostDto.getAmount();
        Integer status = queryCostDto.getStatus();
        Date expenseTime = queryCostDto.getExpenseTime();

        QueryWrapper<Cost> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id!= null, "id", id);
        queryWrapper.eq(commodityId!= null, "commodityId", commodityId);
        queryWrapper.like(name!= null, "name", name);
        queryWrapper.like(orderNumber!= null, "orderNumber", orderNumber);
        queryWrapper.eq(type!= null, "type", type);
        queryWrapper.eq(paymentMethod!= null, "paymentMethod", paymentMethod);
        queryWrapper.eq(amount!= null, "amount", amount);
        // 只能查询自己的订单
        queryWrapper.eq("payee", userId);
        queryWrapper.eq(status!= null, "status", status);
        queryWrapper.eq(expenseTime!= null, "expenseTime", expenseTime);
        queryWrapper.orderByDesc("createTime");

        log.info("查询所有费用（商户）");
        return this.page(new Page<>(queryCostDto.getCurrent(), queryCostDto.getPageSize()), queryWrapper);
    }
}




