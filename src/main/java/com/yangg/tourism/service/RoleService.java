package com.yangg.tourism.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangg.tourism.domain.dto.role.AddRoleDto;
import com.yangg.tourism.domain.dto.role.QueryRoleDto;
import com.yangg.tourism.domain.dto.role.UpdateRoleDto;
import com.yangg.tourism.domain.entity.Permissions;
import com.yangg.tourism.domain.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yangg.tourism.enums.ErrorType;

import java.util.List;

/**
* @author 67121
* @description 针对表【role】的数据库操作Service
* @createDate 2025-01-10 15:47:20
*/
public interface RoleService extends IService<Role> {
    Role verifyRole(Integer roleId, ErrorType errorType);

    boolean addRole(AddRoleDto addRoleDto);

    boolean deleteRole(Integer id);

    int deleteBatchRoles(Integer... roleIds);

    Page<Role> getAllRoles(QueryRoleDto queryRoleDto);

    boolean updateRole(UpdateRoleDto updateRoleDto);

    List<Permissions> getAllPermissionsByRoleId(Integer id);

    boolean addPermissionToRole(Integer roleId, Integer... permissionIds);
}
