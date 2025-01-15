package com.qcx.property.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qcx.property.domain.dto.RegisterDto;
import com.qcx.property.domain.entity.User;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.enums.RoleType;
import com.qcx.property.exception.BusinessException;
import com.qcx.property.mapper.UserMapper;
import com.qcx.property.service.AuthService;
import com.qcx.property.service.RoleUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @description: 认证业务逻辑
 * @author: yannqing
 * @create: 2025-01-14 16:33
 * @from: <更多资料：yannqing.com>
 **/
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private UserMapper usermapper;

    @Resource
    private RoleUserService roleUserService;

    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 注册
     * @param registerDto
     */
    @Override
    public boolean register(RegisterDto registerDto) {
        String username = registerDto.getUsername();
        String password = registerDto.getPassword();
        String nickName = registerDto.getNickName();

        // 判空
        if (username == null || password == null || nickName == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }
        // 判空字符串
        if (username.isEmpty() || password.isEmpty() || nickName.isEmpty()) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // username 必须不能重复，且大于 6 个字符，小于等于 15 个字符
        if (username.length() <= 6 || username.length() > 15) {
            throw new BusinessException(ErrorType.USERNAME_LENGTH_ERROR);
        }
        boolean isUsernameExist = usermapper.exists(new QueryWrapper<User>().eq("username", username));
        if (isUsernameExist) {
            throw new BusinessException(ErrorType.USERNAME_ALREADY_EXIST);
        }

        // password 必须大于等于 8 位，小于 15 位
        if (password.length() < 8 || password.length() >= 15) {
            throw new BusinessException(ErrorType.PASSWORD_LENGTH_ERROR);
        }

        User registerUser = RegisterDto.dtoToUser(registerDto);
        registerUser.setPassword(passwordEncoder.encode(password));

        // 插入新注册用户
        int result = usermapper.insert(registerUser);
        log.info("用户user{}注册成功", registerUser.getUsername());

        // 给用户添加角色
        roleUserService.addRole(username, RoleType.USER);
        log.info("用户{}添加角色{}成功", registerUser.getUsername(), RoleType.USER.getRoleCode());

        return result > 0;
    }
}
