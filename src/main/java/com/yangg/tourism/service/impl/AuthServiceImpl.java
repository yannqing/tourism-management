package com.yangg.tourism.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yangg.tourism.common.Constant;
import com.yangg.tourism.domain.dto.auth.RegisterDto;
import com.yangg.tourism.domain.dto.message.AddMessageDto;
import com.yangg.tourism.domain.entity.Message;
import com.yangg.tourism.domain.entity.User;
import com.yangg.tourism.enums.ErrorType;
import com.yangg.tourism.enums.MessageType;
import com.yangg.tourism.enums.RoleType;
import com.yangg.tourism.exception.BusinessException;
import com.yangg.tourism.mapper.UserMapper;
import com.yangg.tourism.service.AuthService;
import com.yangg.tourism.service.MessageService;
import com.yangg.tourism.service.RoleUserService;
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
    private UserMapper userMapper;

    @Resource
    private RoleUserService roleUserService;

    @Resource
    private MessageService messageService;

    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 注册
     * @param registerDto
     */
    @Override
    public boolean register(RegisterDto registerDto) {
        String username = Optional.ofNullable(registerDto.getUsername())
                .filter(s -> !s.isEmpty())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));
        String password = Optional.ofNullable(registerDto.getPassword())
                .filter(s -> !s.isEmpty())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));
        String nickName = Optional.ofNullable(registerDto.getNickName())
                .filter(s -> !s.isEmpty())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // username 必须不能重复，且大于 6 个字符，小于等于 15 个字符
        if (username.length() <= 6 || username.length() > 15) {
            throw new BusinessException(ErrorType.USERNAME_LENGTH_ERROR);
        }
        boolean isUsernameExist = userMapper.exists(new QueryWrapper<User>().eq("username", username));
        if (isUsernameExist) {
            throw new BusinessException(ErrorType.USERNAME_ALREADY_EXIST);
        }

        // password 必须大于等于 8 位，小于 15 位
        if (password.length() < 8 || password.length() >= 15) {
            throw new BusinessException(ErrorType.PASSWORD_LENGTH_ERROR);
        }

        User registerUser = RegisterDto.dtoToUser(registerDto);
        registerUser.setPassword(passwordEncoder.encode(password));

        int result = 0;

        if (!registerDto.getRoleId().equals(RoleType.OTHER.getRoleId())) {
            // 插入新注册用户
            result = userMapper.insert(registerUser);
            log.info("用户user{}注册成功", registerUser.getUsername());
        }

        // 给用户添加角色
        if (registerDto.getRoleId() == null) {
            roleUserService.addRole(username, RoleType.USER);
        } else if (registerDto.getRoleId().equals(RoleType.OTHER.getRoleId())) {
            AddMessageDto addMessageDto = new AddMessageDto();
            addMessageDto.setType(MessageType.REGISTER.getId());
            addMessageDto.setContent("新的商户请求注册，请处理！");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("registerInfo", JSON.toJSONString(registerDto));
            addMessageDto.setArgs(jsonObject.toJSONString());
            addMessageDto.setReceiveUser(Constant.adminUserId);

            messageService.addMessage(addMessageDto);
        } else {
            roleUserService.addRole(username, registerDto.getRoleId());
        }

        log.info("用户 username: {}添加角色 id: {}成功", registerUser.getUsername(),registerDto.getRoleId() == null ? RoleType.USER : registerDto.getRoleId());
        return result > 0;
    }

    @Override
    public boolean registerPass(Integer messageId) {
        if (messageId == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        Message message = messageService.getById(messageId);
        if (message == null) {
            throw new BusinessException(ErrorType.MESSAGE_NOT_EXIST);
        }

        String messageArgs = message.getArgs();
        JSONObject jsonObject = JSON.parseObject(messageArgs, JSONObject.class);
        RegisterDto registerDto = (RegisterDto)jsonObject.get("registerInfo");

        User registerUser = RegisterDto.dtoToUser(registerDto);
        registerUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        // 插入新注册用户
        int result = userMapper.insert(registerUser);
        log.info("商户 username：{} 注册成功", registerUser.getUsername());

        roleUserService.addRole(registerUser.getUsername(), registerDto.getRoleId());

        // 给新注册的商户发送消息通知
        User newUser = userMapper.selectOne(new QueryWrapper<User>().eq("username", registerUser.getUsername()));

        AddMessageDto addMessageDto = new AddMessageDto();
        addMessageDto.setType(MessageType.REGISTER.getId());
        addMessageDto.setContent("恭喜您通过注册，成为一名旅游商业管理员！");
        addMessageDto.setReceiveUser(newUser.getUserId());

        messageService.addMessage(addMessageDto);

        return result > 0;
    }
}
