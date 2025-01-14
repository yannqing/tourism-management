package com.qcx.property.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcx.property.domain.entity.RoleUser;
import com.qcx.property.domain.entity.User;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.enums.RoleType;
import com.qcx.property.exception.BusinessException;
import com.qcx.property.mapper.UserMapper;
import com.qcx.property.service.RoleUserService;
import com.qcx.property.mapper.RoleUserMapper;
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
    private UserMapper userMapper;

    /**
     * 根据用户名添加角色
     * @param username
     * @param roleType
     */
    @Override
    public void addRole(String username, RoleType roleType) {
        User addRoleUser = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        if (addRoleUser == null) {
            throw new BusinessException(ErrorType.ADD_ROLE_USER_NOT_EXIST);
        }

        RoleUser roleUser = new RoleUser();
        roleUser.setUid(addRoleUser.getUserId());
        roleUser.setRid(roleType.getRoleId());

        this.baseMapper.insert(roleUser);
    }

    /**
     * 根据用户 id 添加角色
     * @param userId
     * @param roleType
     */
    @Override
    public void addRole(int userId, RoleType roleType) {
        User addRoleUser = userMapper.selectById(userId);
        if (addRoleUser == null) {
            throw new BusinessException(ErrorType.ADD_ROLE_USER_NOT_EXIST);
        }

        RoleUser roleUser = new RoleUser();
        roleUser.setUid(addRoleUser.getUserId());
        roleUser.setRid(roleType.getRoleId());

        this.baseMapper.insert(roleUser);
    }
}




