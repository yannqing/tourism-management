package com.yannqing.template.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yannqing.template.domain.dto.permissions.AddPermissionDto;
import com.yannqing.template.domain.dto.permissions.QueryPermissionsDto;
import com.yannqing.template.domain.dto.permissions.UpdatePermissionsDto;
import com.yannqing.template.domain.entity.Permissions;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yannqing.template.domain.entity.Role;

import java.util.List;

/**
* @author 67121
* @description 针对表【permissions】的数据库操作Service
* @createDate 2025-01-10 15:47:25
*/
public interface PermissionsService extends IService<Permissions> {

    boolean addPermission(AddPermissionDto addPermissionDto);

    boolean deletePermission(Integer id);

    int deleteBatchPermissions(Integer... permissionIds);

    Page<Permissions> getAllPermissions(QueryPermissionsDto queryPermissionsDto);

    boolean updatePermissions(UpdatePermissionsDto updatePermissionsDto);
}
