package com.qcx.property.service;

import com.qcx.property.domain.dto.RegisterDto;

/**
 * @description: 认证接口
 * @author: yannqing
 * @create: 2025-01-14 16:33
 **/
public interface AuthService {

    boolean register(RegisterDto registerDto);
}
