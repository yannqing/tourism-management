package com.yangg.tourism.service;

import com.yangg.tourism.domain.entity.RoleUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yangg.tourism.enums.RoleType;

/**
* @author 67121
* @description 针对表【role_user】的数据库操作Service
* @createDate 2025-01-10 15:47:32
*/
public interface RoleUserService extends IService<RoleUser> {

    /**
     * 新增角色
     * @param username 用户名
     * @param roleType 角色类型枚举
     */
    void addRole(String username, RoleType roleType);

    /**
     * 新增角色
     * @param username 用户名
     * @param roleId 角色 id
     */
    void addRole(String username, Integer roleId);

    /**
     * 新增角色
     * @param userId 用户 id
     * @param roleId 角色 id
     */
    void addRole(int userId, Integer roleId);

    /**
     * 新增角色
     * @param userId 用户id
     * @param roleType 枚举类型
     */
    void addRole(int userId, RoleType roleType);
}
