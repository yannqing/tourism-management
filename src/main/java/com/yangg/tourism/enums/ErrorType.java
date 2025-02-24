package com.yangg.tourism.enums;

import com.yangg.tourism.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType implements BaseExceptionInterface {
    // TODO 类型统一摆放
    // common 一般异常
    ARGS_NOT_NULL(50001, "参数不能为空，请重试！"),

    // 认证异常
    USERNAME_ALREADY_EXIST(60001, "用户名已存在，请重试！"),
    USERNAME_LENGTH_ERROR(60002, "用户名必须大于 6 个字符，小于等于 15 个字符，请重试！"),
    PASSWORD_LENGTH_ERROR(60003, "密码必须大于等于 8 位，小于 15 位，请重试！"),
    USER_NOT_EXIST(60004, "用户不存在，请重试！"),
    ROLE_ADD_ERROR(60005, "给用户添加角色失败"),
    ROLE_NOT_EXIST(60008, "角色不存在"),
    ROLE_ADMIN_CANNOT_DELETE(60008, "存在角色无法删除"),
    ROLE_ALREADY_EXIST(60010, "角色已经存在，无法添加"),
    PASSWORD_NOT_MATCH(60006, "输入的密码与原来密码不一致，请重试"),
    PASSWORD_NOT_EQUALS(60007, "两次输入的密码不一致，请重试"),
    PERMISSION_NOT_EXIST(60009, "权限不存在"),
    PERMISSION_ALREADY_EXIST(60011, "权限已存在，无法添加"),

    COST_NOT_EXIST(60012, "费用不存在，请重试"),
    COST_TYPE_CODE_REPEAT(60013, "费用类型编码重复！"),

    NO_AUTH_ERROR(60201, "没有权限，请重试"),

    //token 异常
    TOKEN_NOT_EXIST(60101, "token 不存在"),
    TOKEN_INVALID(60102, "token 无效")
    ;
    // 异常码
    private final int errorCode;
    // 错误信息
    private final String errorMessage;
}
