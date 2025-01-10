package com.qcx.property.controller;

import com.qcx.property.domain.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @PostMapping("/register")
    public BaseResponse<?> register() {
        return null;
    }
}
