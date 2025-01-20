package com.yannqing.template.service;

import com.yannqing.template.domain.dto.auth.RegisterDto;

/**
 * @description: 认证接口
 * @author: yannqing
 * @create: 2025-01-14 16:33
 **/
public interface AuthService {

    boolean register(RegisterDto registerDto);
}
