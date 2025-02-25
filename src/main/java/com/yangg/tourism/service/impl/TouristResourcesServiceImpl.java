package com.yangg.tourism.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangg.tourism.domain.dto.tourist.AddTouristResourcesDto;
import com.yangg.tourism.domain.dto.tourist.QueryTouristResourcesDto;
import com.yangg.tourism.domain.dto.tourist.UpdateTouristResourcesDto;
import com.yangg.tourism.domain.entity.TouristResources;
import com.yangg.tourism.enums.ErrorType;
import com.yangg.tourism.exception.BusinessException;
import com.yangg.tourism.mapper.TouristResourcesMapper;
import com.yangg.tourism.service.TouristResourcesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
* @author yanqing
* @description 针对表【tourist_resources】的数据库操作Service实现
* @createDate 2025-02-25 10:40:10
*/
@Slf4j
@Service
public class TouristResourcesServiceImpl extends ServiceImpl<TouristResourcesMapper, TouristResources>
    implements TouristResourcesService{

    @Override
    public Page<TouristResources> getAllTouristResources(QueryTouristResourcesDto queryTouristResourcesDto) {
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
        queryWrapper.eq(beginTime!= null, "beginTime", beginTime);
        queryWrapper.eq(endTime!= null, "endTime", endTime);
        queryWrapper.like(name!= null, "name", name);
        queryWrapper.like(description!= null, "description", description);
        log.info("查询所有旅游资源");

        return this.page(new Page<>(queryTouristResourcesDto.getCurrent(), queryTouristResourcesDto.getPageSize()), queryWrapper);
    }

    @Override
    public boolean updateTouristResources(UpdateTouristResourcesDto updateTouristResourcesDto) {
        // 判空
        Optional.ofNullable(updateTouristResourcesDto.getId())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 有效性判断
        Integer updateTouristResourcesDtoId = updateTouristResourcesDto.getId();
        Optional.ofNullable(this.getById(updateTouristResourcesDtoId))
                .orElseThrow(() -> new BusinessException(ErrorType.TOURIST_NOT_EXIST));

        TouristResources touristResources = UpdateTouristResourcesDto.objToTouristResources(updateTouristResourcesDto);

        boolean updateResult = this.updateById(touristResources);
        log.info("更新旅游资源");

        return updateResult;
    }

    @Override
    public boolean addTouristResources(AddTouristResourcesDto addTouristResourcesDto) {
        // 判空
        Optional.ofNullable(addTouristResourcesDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 添加旅游资源
        TouristResources addTouristResources = AddTouristResourcesDto.objToTouristResources(addTouristResourcesDto);
        boolean saveResult = this.save(addTouristResources);
        log.info("新增旅游资源");

        return saveResult;
    }

    @Override
    public boolean deleteTouristResources(Integer id) {
        Optional.ofNullable(id)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Optional.ofNullable(this.getById(id))
                .orElseThrow(() -> new BusinessException(ErrorType.TOURIST_NOT_EXIST));

        boolean deleteResult = this.removeById(id);
        log.info("删除旅游资源");

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

        return deleteResult > 0;
    }
}




