package com.qcx.property.enums;

import com.qcx.property.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType implements BaseExceptionInterface {
    // common 一般异常
    ARGS_NOT_NULL(50001, "参数不能为空，请重试！"),

    // 认证异常
    USERNAME_ALREADY_EXIST(60001, "用户名已存在，请重试！"),
    USERNAME_LENGTH_ERROR(60002, "用户名长度错误，请重试！"),
    PASSWORD_LENGTH_ERROR(60003, "密码长度错误，请重试！"),
    USER_NOT_EXIST(60004, "用户不存在，请重试！"),
    ADD_ROLE_USER_NOT_EXIST(60004, "给用户添加角色失败，用户不存在，请重试！"),
    ;
    // 异常码
    private final int errorCode;
    // 错误信息
    private final String errorMessage;
}
