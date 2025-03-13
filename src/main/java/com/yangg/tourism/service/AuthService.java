package com.yangg.tourism.service;

import com.yangg.tourism.domain.dto.auth.RegisterDto;

/**
 * @description: 认证接口
 * @author: yannqing
 * @create: 2025-01-14 16:33
 **/
public interface AuthService {

    /**
     * 注册
     * @param registerDto 注册 dto
     * @return 返回注册结果
     */
    boolean register(RegisterDto registerDto);

    /**
     * 管理员通过商户的注册
     * @param messageId 消息 id
     * @return 返回结果
     */
    boolean registerPass(Integer messageId);
}
