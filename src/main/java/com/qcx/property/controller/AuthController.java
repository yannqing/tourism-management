package com.qcx.property.controller;

import com.qcx.property.domain.dto.auth.RegisterDto;
import com.qcx.property.domain.model.BaseResponse;
import com.qcx.property.service.AuthService;
import com.qcx.property.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 认证
 * @author: yannqing
 * @create: 2025-01-10 15:18
 * @from: <更多资料：yannqing.com>
 **/
@Tag(name = "用户认证")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private AuthService authService;

    @Operation(summary = "注册")
    @PostMapping("/register")
    public BaseResponse<?> register(RegisterDto registerDto) {
        boolean result = authService.register(registerDto);
        if (result) {
            return ResultUtils.success(String.format("用户%s注册成功", registerDto.getUsername()));
        } else {
            return ResultUtils.failure(String.format("用户%s注册失败", registerDto.getUsername()));
        }
    }
}
