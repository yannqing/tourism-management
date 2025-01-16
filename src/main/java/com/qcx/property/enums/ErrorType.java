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
    USERNAME_LENGTH_ERROR(60002, "用户名必须大于 6 个字符，小于等于 15 个字符，请重试！"),
    PASSWORD_LENGTH_ERROR(60003, "密码必须大于等于 8 位，小于 15 位，请重试！"),
    USER_NOT_EXIST(60004, "用户不存在，请重试！"),
    ADD_ROLE_ERROR(60005, "给用户添加角色失败"),
    PASSWORD_NOT_MATCH(60006, "输入的密码与原来密码不一致，请重试"),
    PASSWORD_NOT_EQUALS(60006, "两次输入的密码不一致，请重试"),

    //token 异常
    TOKEN_NOT_EXIST(60101, "token 不存在"),
    TOKEN_INVALID(60102, "token 无效")
    ;
    // 异常码
    private final int errorCode;
    // 错误信息
    private final String errorMessage;
}
