package com.qcx.property.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcx.property.domain.entity.RoleUser;
import com.qcx.property.domain.entity.User;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.enums.RoleType;
import com.qcx.property.exception.BusinessException;
import com.qcx.property.mapper.RoleMapper;
import com.qcx.property.service.RoleService;
import com.qcx.property.service.RoleUserService;
import com.qcx.property.mapper.RoleUserMapper;
import com.qcx.property.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author 67121
* @description 针对表【role_user】的数据库操作Service实现
* @createDate 2025-01-10 15:47:32
*/
@Service
public class RoleUserServiceImpl extends ServiceImpl<RoleUserMapper, RoleUser>
    implements RoleUserService{

    @Resource
    private RoleUserMapper roleUserMapper;

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private RoleMapper roleMapper;

    /**
     * 根据用户名添加角色
     * @param username
     * @param roleType
     */
    @Override
    public void addRole(String username, RoleType roleType) {
        User addRoleUser = userService.getOne(new QueryWrapper<User>().eq("username", username));
        if (addRoleUser == null) {
            throw new BusinessException(ErrorType.ROLE_ADD_ERROR);
        }
        roleService.verifyRole(roleType.getRoleId(), ErrorType.ROLE_ADD_ERROR);

        RoleUser roleUser = new RoleUser();
        roleUser.setUid(addRoleUser.getUserId());
        roleUser.setRid(roleType.getRoleId());

        this.baseMapper.insert(roleUser);
    }

    /**
     * 根据用户 id 与 角色 id 给用户添加角色
     * @param userId
     * @param roleId
     */
    @Override
    public void addRole(int userId, Integer roleId) {
        // 参数有效性检验
        userService.verifyUserId(userId);
        roleService.verifyRole(roleId, ErrorType.ROLE_ADD_ERROR);

        // 添加角色
        RoleUser roleUser = new RoleUser();
        roleUser.setUid(userId);
        roleUser.setRid(roleId);

        this.baseMapper.insert(roleUser);
    }

    /**
     * 根据用户 id 添加角色
     * @param userId
     * @param roleType
     */
    @Override
    public void addRole(int userId, RoleType roleType) {
        // 参数有效性检验
        userService.verifyUserId(userId);
        roleService.verifyRole(roleType.getRoleId(), ErrorType.ROLE_ADD_ERROR);

        RoleUser roleUser = new RoleUser();
        roleUser.setUid(userId);
        roleUser.setRid(roleType.getRoleId());

        this.baseMapper.insert(roleUser);
    }
}




