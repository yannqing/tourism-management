package com.yannqing.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yannqing.template.domain.model.SecurityUser;
import com.yannqing.template.domain.entity.Role;
import com.yannqing.template.domain.entity.RoleUser;
import com.yannqing.template.domain.entity.User;
import com.yannqing.template.mapper.RoleMapper;
import com.yannqing.template.mapper.RoleUserMapper;
import com.yannqing.template.mapper.UserMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SecurityUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleUserMapper roleUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //获取到用户的全部信息
        log.info("loading user by username: {}", username);
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        log.debug("login user: {}", user);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        //获取用户的角色
        List<RoleUser> roleUsers = roleUserMapper.selectList(new QueryWrapper<RoleUser>().eq("uid", user.getUserId()));
        List<Role> roles = roleMapper.selectBatchIds(roleUsers.stream().map(RoleUser::getRid).collect(Collectors.toList()));
        SecurityUser securityUser = new SecurityUser(user);
        securityUser.setRole(roles);

        return securityUser;
    }
}
