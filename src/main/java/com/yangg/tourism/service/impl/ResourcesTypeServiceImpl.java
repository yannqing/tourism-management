package com.yangg.tourism.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangg.tourism.domain.dto.tourist.*;
import com.yangg.tourism.domain.entity.ResourcesType;
import com.yangg.tourism.domain.entity.TouristResources;
import com.yangg.tourism.enums.ErrorType;
import com.yangg.tourism.exception.BusinessException;
import com.yangg.tourism.service.ResourcesTypeService;
import com.yangg.tourism.mapper.ResourcesTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
* @author yanqing
* @description 针对表【resources_type(旅游资源类型)】的数据库操作Service实现
* @createDate 2025-02-25 10:40:17
*/
@Slf4j
@Service
public class ResourcesTypeServiceImpl extends ServiceImpl<ResourcesTypeMapper, ResourcesType>
    implements ResourcesTypeService{

    @Override
    public Page<ResourcesType> getAllResourcesTypes(QueryResourcesTypeDto queryResourcesTypeDto) {
        // 判空
        Optional.ofNullable(queryResourcesTypeDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Integer id = queryResourcesTypeDto.getId();
        String name = queryResourcesTypeDto.getName();
        String description = queryResourcesTypeDto.getDescription();



        QueryWrapper<ResourcesType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id!= null, "id", id);
        queryWrapper.like(name!= null, "name", name);
        queryWrapper.like(description!= null, "description", description);
        log.info("查询所有旅游资源类型");

        return this.page(new Page<>(queryResourcesTypeDto.getCurrent(), queryResourcesTypeDto.getPageSize()), queryWrapper);
    }

    @Override
    public boolean updateResourcesType(UpdateResourcesTypeDto updateResourcesTypeDto) {
        // 判空
        Optional.ofNullable(updateResourcesTypeDto.getId())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 有效性判断
        Integer updateTouristResourcesDtoId = updateResourcesTypeDto.getId();
        Optional.ofNullable(this.getById(updateTouristResourcesDtoId))
                .orElseThrow(() -> new BusinessException(ErrorType.TOURIST_TYPE_NOT_EXIST));

        ResourcesType resourcesType = UpdateResourcesTypeDto.objToResourcesType(updateResourcesTypeDto);

        boolean updateResult = this.updateById(resourcesType);
        log.info("更新旅游资源类型");

        return updateResult;
    }

    @Override
    public boolean addResourcesType(AddResourcesTypeDto addResourcesTypeDto) {
        // 判空
        Optional.ofNullable(addResourcesTypeDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 添加旅游资源
        ResourcesType addResourcesType = AddResourcesTypeDto.objToResourcesType(addResourcesTypeDto);
        boolean saveResult = this.save(addResourcesType);
        log.info("新增旅游资源类型");

        return saveResult;
    }

    @Override
    public boolean deleteResourcesType(Integer id) {
        Optional.ofNullable(id)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Optional.ofNullable(this.getById(id))
                .orElseThrow(() -> new BusinessException(ErrorType.TOURIST_TYPE_NOT_EXIST));

        boolean deleteResult = this.removeById(id);
        log.info("删除旅游资源类型");

        return deleteResult;
    }

    @Override
    public boolean deleteBatchResourcesType(Integer... resourcesTypeIds) {
        // 判空
        if (resourcesTypeIds == null || resourcesTypeIds.length == 0) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 有效性判断
        List<ResourcesType> resourcesTypes = this.listByIds(Arrays.asList(resourcesTypeIds));
        if (resourcesTypes.size()!= resourcesTypeIds.length) {
            throw new BusinessException(ErrorType.TOURIST_TYPE_NOT_EXIST);
        }

        // 批量删除
        int deleteResult = this.baseMapper.deleteBatchIds(Arrays.asList(resourcesTypeIds));
        log.info("批量删除旅游资源类型");

        return deleteResult > 0;
    }
}




