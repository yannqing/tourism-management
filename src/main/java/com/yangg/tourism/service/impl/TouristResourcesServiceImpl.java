package com.yangg.tourism.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.yangg.tourism.domain.dto.tourist.AddTouristResourcesDto;
import com.yangg.tourism.domain.dto.tourist.QueryMerchantsResourcesDto;
import com.yangg.tourism.domain.dto.tourist.QueryTouristResourcesDto;
import com.yangg.tourism.domain.dto.tourist.UpdateTouristResourcesDto;
import com.yangg.tourism.domain.entity.*;
import com.yangg.tourism.domain.vo.tourist.TourismResourcesVo2;
import com.yangg.tourism.enums.ErrorType;
import com.yangg.tourism.enums.RoleType;
import com.yangg.tourism.exception.BusinessException;
import com.yangg.tourism.mapper.ProductTypeMapper;
import com.yangg.tourism.mapper.TouristProductRelMapper;
import com.yangg.tourism.mapper.TouristResourcesMapper;
import com.yangg.tourism.service.TouristProductRelService;
import com.yangg.tourism.service.TouristResourcesService;
import com.yangg.tourism.service.UserService;
import com.yangg.tourism.service.UserTouristService;
import com.yangg.tourism.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.*;

/**
* @author yanqing
* @description 针对表【tourist_resources】的数据库操作Service实现
* @createDate 2025-02-25 10:40:10
*/
@Slf4j
@Service
public class TouristResourcesServiceImpl extends ServiceImpl<TouristResourcesMapper, TouristResources>
    implements TouristResourcesService{

    @Resource
    private UserTouristService userTouristService;

    @Resource
    private ProductTypeMapper productTypeMapper;

    @Resource
    private TouristProductRelService touristProductRelService;

    @Resource
    private TouristProductRelMapper touristProductRelMapper;

    @Resource
    private UserService userService;

    @Override
    public Page<TourismResourcesVo2> getAllTouristResources(QueryTouristResourcesDto queryTouristResourcesDto) {
        // 判空
        Optional.ofNullable(queryTouristResourcesDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Integer id = queryTouristResourcesDto.getId();
        Integer pid = queryTouristResourcesDto.getPid();
        Integer typeId = queryTouristResourcesDto.getTypeId();
        String name = queryTouristResourcesDto.getName();
        String description = queryTouristResourcesDto.getDescription();
        String location = queryTouristResourcesDto.getLocation();
        BigDecimal rating = queryTouristResourcesDto.getRating();
        BigDecimal price = queryTouristResourcesDto.getPrice();
        Integer status = queryTouristResourcesDto.getStatus();
        String phone = queryTouristResourcesDto.getPhone();
        String images = queryTouristResourcesDto.getImages();
        LocalTime openingTime = queryTouristResourcesDto.getOpeningTime();
        LocalTime closingTime = queryTouristResourcesDto.getClosingTime();
        Date beginTime = queryTouristResourcesDto.getBeginTime();
        Date endTime = queryTouristResourcesDto.getEndTime();



        QueryWrapper<TouristResources> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id!= null, "id", id);
        queryWrapper.eq(pid!= null, "pid", pid);
        queryWrapper.eq(typeId!= null, "typeId", typeId);
        queryWrapper.like(location!= null, "location", location);
        queryWrapper.eq(rating!= null, "rating", rating);
        queryWrapper.eq(price!= null, "price", price);
        queryWrapper.eq(status!= null, "status", status);
        queryWrapper.like(phone!= null, "phone", phone);
        queryWrapper.like(images!= null, "images", images);
        queryWrapper.eq(openingTime!= null, "openingTime", openingTime);
        queryWrapper.eq(closingTime!= null, "closingTime", closingTime);
        queryWrapper.eq(beginTime!= null, "beginTime", beginTime);
        queryWrapper.eq(endTime!= null, "endTime", endTime);
        queryWrapper.like(name!= null, "name", name);
        queryWrapper.like(description!= null, "description", description);
        log.info("查询所有旅游资源");

        Page<TouristResources> page = this.page(new Page<>(queryTouristResourcesDto.getCurrent(), queryTouristResourcesDto.getPageSize()), queryWrapper);

        return tourismResourcesToTourismResourcesVo2(page);
    }

    @Override
    public Page<TourismResourcesVo2> getAllTouristResourcesByUser(QueryTouristResourcesDto queryTouristResourcesDto) {
        // 判空
        Optional.ofNullable(queryTouristResourcesDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Integer id = queryTouristResourcesDto.getId();
        Integer pid = queryTouristResourcesDto.getPid();
        Integer typeId = queryTouristResourcesDto.getTypeId();
        String name = queryTouristResourcesDto.getName();
        String description = queryTouristResourcesDto.getDescription();
        String location = queryTouristResourcesDto.getLocation();
        BigDecimal rating = queryTouristResourcesDto.getRating();
        BigDecimal price = queryTouristResourcesDto.getPrice();
        Integer status = queryTouristResourcesDto.getStatus();
        String phone = queryTouristResourcesDto.getPhone();
        String images = queryTouristResourcesDto.getImages();
        LocalTime openingTime = queryTouristResourcesDto.getOpeningTime();
        LocalTime closingTime = queryTouristResourcesDto.getClosingTime();
        Date beginTime = queryTouristResourcesDto.getBeginTime();
        Date endTime = queryTouristResourcesDto.getEndTime();

        QueryWrapper<TouristResources> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id!= null, "id", id);
        queryWrapper.eq(pid!= null, "pid", pid);
        queryWrapper.eq(typeId!= null, "typeId", typeId);
        queryWrapper.like(location!= null, "location", location);
        queryWrapper.eq(rating!= null, "rating", rating);
        queryWrapper.eq(price!= null, "price", price);
        queryWrapper.eq(status!= null, "status", status);
        queryWrapper.like(phone!= null, "phone", phone);
        queryWrapper.like(images!= null, "images", images);
        queryWrapper.eq(openingTime!= null, "openingTime", openingTime);
        queryWrapper.eq(closingTime!= null, "closingTime", closingTime);
        queryWrapper.eq(beginTime!= null, "beginTime", beginTime);
        queryWrapper.eq(endTime!= null, "endTime", endTime);
        queryWrapper.like(name!= null, "name", name);
        queryWrapper.like(description!= null, "description", description);
        log.info("查询所有旅游资源");
        Page<TouristResources> page = this.page(new Page<>(queryTouristResourcesDto.getCurrent(), queryTouristResourcesDto.getPageSize()), queryWrapper);

        return tourismResourcesToTourismResourcesVo2(page);
    }

    @Override
    public boolean updateTouristResources(UpdateTouristResourcesDto updateTouristResourcesDto, HttpServletRequest request) throws JsonProcessingException {
        // 判空
        Optional.ofNullable(updateTouristResourcesDto.getId())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 有效性判断
        Integer updateTouristResourcesDtoId = updateTouristResourcesDto.getId();
        Optional.ofNullable(this.getById(updateTouristResourcesDtoId))
                .orElseThrow(() -> new BusinessException(ErrorType.TOURIST_NOT_EXIST));

        // 判断商户
        User loginUser = JwtUtils.getUserFromToken(request.getHeader("token"));
        if (loginUser == null) {
            throw new BusinessException(ErrorType.TOKEN_INVALID);
        }
        List<Role> roles = userService.getRoleByUser(loginUser.getUserId());
        if (roles.isEmpty()) {
            throw new BusinessException(ErrorType.SYSTEM_ERROR);
        }
        if (roles.get(0).getId().equals(RoleType.OTHER.getRoleId())) {
            // 获取要修改的资源 id
            Integer resourcesDtoId = updateTouristResourcesDto.getId();
            // 获取商户可以修改的资源 id，存入 list 集合
            UserTourist userTourist = userTouristService.getBaseMapper().selectOne(new QueryWrapper<UserTourist>().eq("uid", loginUser.getUserId()));
            List<Integer> canUpdate = new ArrayList<>();
            canUpdate.add(userTourist.getTid());
            List<TouristResources> touristResourcesList = this.getBaseMapper().selectList(new QueryWrapper<TouristResources>().eq("pid", userTourist.getTid()));
            touristResourcesList.forEach(touristResources -> {
                canUpdate.add(touristResources.getId());
            });
            // 查看 list 集合中是否包含要修改的资源 id
            if (!canUpdate.contains(resourcesDtoId)) {
                throw new BusinessException(ErrorType.SYSTEM_ERROR);
            }
        }


        TouristResources touristResources = UpdateTouristResourcesDto.objToTouristResources(updateTouristResourcesDto);

        boolean updateResult = this.updateById(touristResources);
        log.info("更新旅游资源");

        return updateResult;
    }

    @Override
    public boolean addTouristResources(AddTouristResourcesDto addTouristResourcesDto, HttpServletRequest request) throws JsonProcessingException {
        // 判空
        Optional.ofNullable(addTouristResourcesDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        User loginUser = JwtUtils.getUserFromToken(request.getHeader("token"));
        if (loginUser == null) {
            throw new BusinessException(ErrorType.TOKEN_INVALID);
        }

        List<Role> roles = userService.getRoleByUser(loginUser.getUserId());
        if (roles.isEmpty()) {
            throw new BusinessException(ErrorType.SYSTEM_ERROR);
        }

        TouristResources addTouristResources = AddTouristResourcesDto.objToTouristResources(addTouristResourcesDto);

        // 商户新增
        if (roles.get(0).getId().equals(RoleType.OTHER.getRoleId())) {
            Integer touristId = getTouristIdByUser(loginUser.getUserId());
            addTouristResources.setPid(touristId);
        } else {
            // 添加旅游资源(管理员）
            if (addTouristResourcesDto.getPid() == null || addTouristResourcesDto.getTypeId() == 5) {
                throw new BusinessException(ErrorType.TOURIST_COMMODITY_ADD_ERROR_NO_PID);
            }
        }

        boolean saveResult;


        // 新增非商品的资源
        if (addTouristResourcesDto.getTypeId() != 5) {
            // 必须指定 userId
            if (addTouristResourcesDto.getUserId() != null) {
                // 指定的 userId 必须有效
                User owner = userService.getById(addTouristResourcesDto.getUserId());
                if (owner == null) {
                    throw new BusinessException(ErrorType.SYSTEM_ERROR);
                }
                // 新增资源
                saveResult = this.save(addTouristResources);
                // 给资源指定管理者（即 userId）
                UserTourist userTourist = new UserTourist();
                userTourist.setUid(owner.getUserId());
                userTourist.setTid(addTouristResources.getId());

                userTouristService.save(userTourist);
                log.info("新增旅游资源（非商品） id: {}，负责人 id：{}", addTouristResources.getId(), addTouristResourcesDto.getUserId());
            } else {
                throw new BusinessException(ErrorType.SYSTEM_ERROR);
            }
        } else {
            // 新增商品的资源，则必须指定类型
            if (addTouristResourcesDto.getProductTypeId() == null) {
                throw new BusinessException(ErrorType.SYSTEM_ERROR);
            }
            // 产品类型 id 有效性判断
            ProductType productType = productTypeMapper.selectById(addTouristResourcesDto.getProductTypeId());
            if (productType == null) {
                throw new BusinessException(ErrorType.PRODUCT_TYPE_NOT_EXIST);
            }
            // 新增资源
            saveResult = this.save(addTouristResources);
            // 新增商品的类型
            touristProductRelService.add(addTouristResources.getId(), productType.getId());
            log.info("新增旅游资源（商品） id: {}, 商品类型 id：{}", addTouristResources.getId(), productType.getId());

        }
        return saveResult;
    }

    @Override
    public boolean deleteTouristResources(Integer id, HttpServletRequest request) throws JsonProcessingException {
        Optional.ofNullable(id)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        TouristResources deleteTourist = this.getById(id);
        if (deleteTourist == null) {
            throw new BusinessException(ErrorType.TOURIST_NOT_EXIST);
        }

        User loginUser = JwtUtils.getUserFromToken(request.getHeader("token"));
        if (loginUser == null) {
            throw new BusinessException(ErrorType.TOKEN_INVALID);
        }

        List<Role> roles = userService.getRoleByUser(loginUser.getUserId());
        if (roles.isEmpty()) {
            throw new BusinessException(ErrorType.SYSTEM_ERROR);
        }
        if (roles.get(0).getId().equals(RoleType.OTHER.getRoleId())) {
            Integer touristIdByUser = getTouristIdByUser(loginUser.getUserId());
            List<Integer> canUpdate = new ArrayList<>();
            canUpdate.add(touristIdByUser);
            List<TouristResources> touristResourcesList = this.getBaseMapper().selectList(new QueryWrapper<TouristResources>().eq("pid", touristIdByUser));
            touristResourcesList.forEach(touristResources -> {
                canUpdate.add(touristResources.getId());
            });
            if (!canUpdate.contains(touristIdByUser)) {
                throw new BusinessException(ErrorType.SYSTEM_ERROR);
            }
        }


        boolean deleteResult = this.removeById(id);
        log.info("删除旅游资源");

        // 如果删除的是商品，则需要删除商品类型
        if (deleteTourist.getTypeId().equals(5)) {
            touristProductRelService.deleteByTid(deleteTourist.getId());
        }

        List<TouristResources> touristResourcesList = this.getBaseMapper().selectList(new QueryWrapper<TouristResources>().eq("pid", id));
        // 删除次子资源以及关系
        touristResourcesList.forEach(touristResources -> {
            // 删除资源
            this.remove(new QueryWrapper<TouristResources>().eq("pid", touristResources.getId()));
            // 删除关系
            userTouristService.remove(new QueryWrapper<UserTourist>().eq("tid", touristResources.getId()));
        });
        // 删除子资源以及关系
        this.remove(new QueryWrapper<TouristResources>().eq("pid", id));
        userTouristService.remove(new QueryWrapper<UserTourist>().eq("tid", id));

        return deleteResult;
    }

    @Override
    public boolean deleteBatchTouristResources(Integer... touristResourcesIds) {
        // 判空
        if (touristResourcesIds == null || touristResourcesIds.length == 0) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 有效性判断
        List<TouristResources> touristResourcesList = this.listByIds(Arrays.asList(touristResourcesIds));
        if (touristResourcesList.size()!= touristResourcesIds.length) {
            throw new BusinessException(ErrorType.TOURIST_NOT_EXIST);
        }

        // 批量删除
        int deleteResult = this.baseMapper.deleteBatchIds(Arrays.asList(touristResourcesIds));
        log.info("批量删除旅游资源");

        Arrays.asList(touristResourcesIds).forEach(touristResource -> {
            Integer id = touristResource;
            List<TouristResources> touristResourcesList1 = this.getBaseMapper().selectList(new QueryWrapper<TouristResources>().eq("pid", id));
            // 删除次子资源以及关系
            touristResourcesList1.forEach(touristResources -> {
                // 删除资源
                this.remove(new QueryWrapper<TouristResources>().eq("pid", touristResources.getId()));
                // 删除关系
                userTouristService.remove(new QueryWrapper<UserTourist>().eq("tid", touristResources.getId()));
            });
            // 删除子资源以及关系
            this.remove(new QueryWrapper<TouristResources>().eq("pid", id));
            userTouristService.remove(new QueryWrapper<UserTourist>().eq("tid", id));

            // 如果要删除的是商品，则需要删除对应的商品类型关系
            TouristResources touristResources = this.getById(id);
            if (touristResources.getTypeId().equals(5)) {
                touristProductRelService.deleteByTid(touristResources.getId());
            }
        });

        return deleteResult > 0;
    }

    @Override
    public Page<TourismResourcesVo2> getRecommendTouristResources() {
        QueryWrapper<TouristResources> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", 0);      //只推荐顶级资源
        queryWrapper.orderBy(true, false, "rating");    //倒序，评分最高的在上面
        Page<TouristResources> page = this.page(new Page<>(1, 10), queryWrapper);
        return tourismResourcesToTourismResourcesVo2(page);
    }

    @Override
    public Page<TourismResourcesVo2> getAllTouristResourcesByMerchants(QueryMerchantsResourcesDto queryMerchantsResourcesDto, HttpServletRequest request) throws JsonProcessingException {
        // 判空
        if (queryMerchantsResourcesDto == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        User loginUser = JwtUtils.getUserFromToken(request.getHeader("token"));
        if (loginUser == null) {
            throw new BusinessException(ErrorType.TOKEN_INVALID);
        }

        // 并获取商户资源 id
        UserTourist userTourist = userTouristService.getBaseMapper().selectOne(new QueryWrapper<UserTourist>().eq("uid", loginUser.getUserId()));
        if (userTourist == null) {
            throw new BusinessException(ErrorType.USER_TOURIST_NOT_EXIST);
        }
        Integer touristResourcesId = userTourist.getTid();

        Integer typeId = queryMerchantsResourcesDto.getTypeId();

        // 构造查询条件
        QueryWrapper<TouristResources> queryWrapper = new QueryWrapper<>();
        if (typeId != null && typeId == 0) {
            queryWrapper.ne("typeId", 5);
        } else if (typeId != null) {
            queryWrapper.eq("typeId", typeId);
        }
        queryWrapper.eq("pid", touristResourcesId);

        Page<TouristResources> page = this.page(new Page<>(queryMerchantsResourcesDto.getCurrent(), queryMerchantsResourcesDto.getPageSize()), queryWrapper);
        log.info("商户 id：{} 查询旅游资源", loginUser.getUserId());
        return tourismResourcesToTourismResourcesVo2(page);
    }

    private Integer getTouristIdByUser(Integer userId) {
        UserTourist userTourist = userTouristService.getBaseMapper().selectOne(new QueryWrapper<UserTourist>().eq("uid", userId));
        if (userTourist == null) {
            throw new BusinessException(ErrorType.USER_TOURIST_NOT_EXIST);
        }
        return userTourist.getTid();
    }

    private Page<TourismResourcesVo2> tourismResourcesToTourismResourcesVo2(Page<TouristResources> page) {

        List<TourismResourcesVo2> tourismResourcesVo2List = page.getRecords().stream().map(touristResources -> {
            TourismResourcesVo2 tourismResourcesVo2 = TourismResourcesVo2.touristResourcesToVo2(touristResources);
            if (tourismResourcesVo2.getTypeId().equals(5)) {
                TouristProductRel touristProductRel = touristProductRelMapper.selectOne(new QueryWrapper<TouristProductRel>().eq("tid", touristResources.getId()));
                tourismResourcesVo2.setProductTypeId(touristProductRel.getPid());
            }
            return tourismResourcesVo2;
        }).toList();
        return new Page<TourismResourcesVo2>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(tourismResourcesVo2List);
    }
}




