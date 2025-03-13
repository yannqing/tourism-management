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

    /**
     * 查询所有的资源类型（商品除外）
     * @param queryResourcesTypeDto 查询 dto
     * @return 返回查询结果
     */
    Page<ResourcesType> getAllResourcesTypes(QueryResourcesTypeDto queryResourcesTypeDto);

    /**
     * 更新资源类型
     * @param updateResourcesTypeDto 更新 dto
     * @return 返回更新结果
     */
    boolean updateResourcesType(UpdateResourcesTypeDto updateResourcesTypeDto);

    /**
     * 新增资源类型
     * @param addResourcesTypeDto 新增 dto
     * @return 返回新增结果
     */
    boolean addResourcesType(AddResourcesTypeDto addResourcesTypeDto);

    /**
     * 删除资源类型
     * @param id 要删除的 id
     * @return 返回删除结果
     */
    boolean deleteResourcesType(Integer id);

    /**
     * 批量删除资源类型
     * @param resourcesTypeIds 要删除的 id 数组
     * @return 返回删除结果
     */
    boolean deleteBatchResourcesType(Integer... resourcesTypeIds);
}
