package com.yangg.tourism.controller;

import com.yangg.tourism.annotation.AuthCheck;
import com.yangg.tourism.common.Code;
import com.yangg.tourism.common.PermissionConstant;
import com.yangg.tourism.domain.dto.auth.RegisterDto;
import com.yangg.tourism.domain.model.BaseResponse;
import com.yangg.tourism.enums.RoleType;
import com.yangg.tourism.service.AuthService;
import com.yangg.tourism.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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

    @AuthCheck(code = PermissionConstant.AUTH_REGISTER)
    @Operation(summary = "注册")
    @PostMapping("/register")
    public BaseResponse<?> register(RegisterDto registerDto) {
        boolean result = authService.register(registerDto);
        if (result && registerDto.getRoleId() != RoleType.OTHER.getRoleId()) {
            return ResultUtils.success(String.format("用户%s注册成功", registerDto.getUsername()));
        } else if (!result && registerDto.getRoleId() == RoleType.OTHER.getRoleId()) {
            return ResultUtils.success(Code.REGISTER_SUCCESS, null, "商户申请成功，待审批");
        } else {
            return ResultUtils.failure(String.format("用户%s注册失败", registerDto.getUsername()));
        }
    }

    @Operation(summary = "管理员通过商户注册")
    @PostMapping("/register/pass")
    @Parameters(@Parameter(name = "messageId", description = "消息 id", required = true))
    public BaseResponse<?> register(Integer messageId) {
        boolean result = authService.registerPass(messageId);
        if (result) {
            return ResultUtils.success("商户注册成功");
        } else {
            return ResultUtils.failure("商户注册失败");
        }
    }
}
