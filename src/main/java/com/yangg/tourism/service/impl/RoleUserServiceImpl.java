package com.yangg.tourism.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangg.tourism.domain.entity.RoleUser;
import com.yangg.tourism.domain.entity.User;
import com.yangg.tourism.enums.ErrorType;
import com.yangg.tourism.enums.RoleType;
import com.yangg.tourism.exception.BusinessException;
import com.yangg.tourism.mapper.RoleMapper;
import com.yangg.tourism.mapper.UserMapper;
import com.yangg.tourism.service.RoleService;
import com.yangg.tourism.service.RoleUserService;
import com.yangg.tourism.mapper.RoleUserMapper;
import com.yangg.tourism.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
* @author 67121
* @description 针对表【role_user】的数据库操作Service实现
* @createDate 2025-01-10 15:47:32
*/
@Service
public class RoleUserServiceImpl extends ServiceImpl<RoleUserMapper, RoleUser>
    implements RoleUserService{

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleService roleService;

    /**
     * 根据用户名添加角色
     * @param username
     * @param roleType
     */
    @Override
    public void addRole(String username, RoleType roleType) {
        User addRoleUser = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        Optional.ofNullable(addRoleUser)
                        .orElseThrow(() -> new BusinessException(ErrorType.ROLE_ADD_ERROR));

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
        verifyUserId(userId);
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
        verifyUserId(userId);
        roleService.verifyRole(roleType.getRoleId(), ErrorType.ROLE_ADD_ERROR);

        RoleUser roleUser = new RoleUser();
        roleUser.setUid(userId);
        roleUser.setRid(roleType.getRoleId());

        this.baseMapper.insert(roleUser);
    }

    /**
     * 验证用户 id 的有效性
     * @param userId
     * @return
     */
    public User verifyUserId(Integer userId) {
        User verifyUser = userMapper.selectById(userId);
        Optional.ofNullable(verifyUser)
                .orElseThrow(() -> new BusinessException(ErrorType.USER_NOT_EXIST));
        return verifyUser;
    }
}




