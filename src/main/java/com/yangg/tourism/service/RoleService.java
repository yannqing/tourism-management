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
    /**
     * 校验角色是否有效
     * @param roleId 角色id
     * @param errorType 错误类型
     * @return
     */
    Role verifyRole(Integer roleId, ErrorType errorType);

    /**
     * 新增角色
     * @param addRoleDto 新增 dto
     * @return
     */
    boolean addRole(AddRoleDto addRoleDto);

    /**
     * 删除角色
     * @param id 要删除的 id
     * @return 返回删除结果
     */
    boolean deleteRole(Integer id);

    /**
     * 批量删除角色
     * @param roleIds 要删除的 id 数组
     * @return 返回删除结果
     */
    int deleteBatchRoles(Integer... roleIds);

    /**
     * 查询所有的角色
     * @param queryRoleDto 查询 dto
     * @return 返回查询结果
     */
    Page<Role> getAllRoles(QueryRoleDto queryRoleDto);

    /**
     * 更新角色
     * @param updateRoleDto 更新dto
     * @return 返回更新结果
     */
    boolean updateRole(UpdateRoleDto updateRoleDto);

    /**
     * 根据角色 id 获取所有的权限
     * @param id 角色 id
     * @return 返回权限集合
     */
    List<Permissions> getAllPermissionsByRoleId(Integer id);

    /**
     * 给角色新增权限
     * @param roleId 角色 id
     * @param permissionIds 要新增的权限数组
     * @return 返回新增结果
     */
    boolean addPermissionToRole(Integer roleId, Integer... permissionIds);
}
