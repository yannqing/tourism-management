package com.yangg.tourism.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangg.tourism.domain.dto.tourist.AddResourcesTypeDto;
import com.yangg.tourism.domain.dto.tourist.QueryResourcesTypeDto;
import com.yangg.tourism.domain.dto.tourist.UpdateResourcesTypeDto;
import com.yangg.tourism.domain.entity.ResourcesType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author yanqing
* @description 针对表【resources_type(旅游资源类型)】的数据库操作Service
* @createDate 2025-02-25 10:40:17
*/
public interface ResourcesTypeService extends IService<ResourcesType> {

    Page<ResourcesType> getAllResourcesTypes(QueryResourcesTypeDto queryResourcesTypeDto);

    boolean updateResourcesType(UpdateResourcesTypeDto updateResourcesTypeDto);

    boolean addResourcesType(AddResourcesTypeDto addResourcesTypeDto);

    boolean deleteResourcesType(Integer id);

    boolean deleteBatchResourcesType(Integer... resourcesTypeIds);
}
