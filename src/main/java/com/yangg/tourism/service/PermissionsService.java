package com.yangg.tourism.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yangg.tourism.domain.dto.permissions.AddPermissionDto;
import com.yangg.tourism.domain.dto.permissions.QueryPermissionsDto;
import com.yangg.tourism.domain.dto.permissions.UpdatePermissionsDto;
import com.yangg.tourism.domain.entity.Permissions;

/**
* @author 67121
* @description 针对表【permissions】的数据库操作Service
* @createDate 2025-01-10 15:47:25
*/
public interface PermissionsService extends IService<Permissions> {

    /**
     * 新增权限
     * @param addPermissionDto 新增 dto
     * @return 新增结果
     */
    boolean addPermission(AddPermissionDto addPermissionDto);

    /**
     * 删除权限
     * @param id 要删除的 id
     * @return 返回删除结果
     */
    boolean deletePermission(Integer id);

    /**
     * 批量删除权限
     * @param permissionIds 要删除的 id 数组
     * @return 返回删除结果
     */
    int deleteBatchPermissions(Integer... permissionIds);

    /**
     * 查询所有的权限
     * @param queryPermissionsDto 查询 dto
     * @return 返回查询结果
     */
    Page<Permissions> getAllPermissions(QueryPermissionsDto queryPermissionsDto);

    /**
     * 更新权限信息
     * @param updatePermissionsDto 更新 dto
     * @return 返回更新的结果
     */
    boolean updatePermissions(UpdatePermissionsDto updatePermissionsDto);
}
