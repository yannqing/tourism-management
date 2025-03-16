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
    SYSTEM_ERROR(50000, "系统错误"),
    PERMISSION_NOT(50002, "无权限"),

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

    TOURIST_NOT_EXIST(60014, "旅游资源不存在，请重试"),
    TOURIST_TYPE_NOT_EXIST(60015, "旅游资源类型不存在，请重试"),

    MESSAGE_NOT_EXIST(60016, "消息通知不存在，请重试"),
    USER_TOURIST_NOT_EXIST(60017, "该用户下面不存在旅游资源，请重试"),
    USER_UPDATE_TOURIST_ERROR(60018, "商户无法非自己所属的资源"),
    SLIDE_NOT_EXIST(60019, "轮播图不存在，请重试"),
    ORDER_NOT_EXIST(60020, "订单不存在，请重试"),
    ORDER_INVALID(60021, "订单已过期"),
    TOURIST_ALREADY_EXIST(60022, "旅游资源已存在，无法添加"),
    TOURIST_COMMODITY_ADD_ERROR_NO_PID(60023, "无法新增没有 pid 的商品"),
    PRODUCT_NOT_EXIST(60024, "产品不存在，请重试"),
    PRODUCT_TYPE_NOT_EXIST(60025, "产品类型不存在，请重试"),
    PRODUCT_TYPE_CODE_REPEAT(60026, "产品类型重复，请重试"),

    TOURIST_NOT_BELONG_PRODUCT_NOT_UPDATE(60027, "此资源非商品，不能修改其商品类型"),
    TOURIST_NOT_IN_UPDATE_BY_MERCHANTS(60028, "此资源不在您的修改范围内，请重试"),

    PRODUCT_COST_TYPE_REL_NOT_EXIST(60029, "商品类型与订单类型关系不存在，请重试"),
    PRODUCT_COST_TYPE_REL_ALREADY_EXIST(60030, "商品类型与订单类型关系已存在存在，请重试"),
    PRODUCT_HAVE_NOT_USER(60031, "此商品的父资源没有负责人，系统错误！"),
    COST_TYPE_NOT_EXIST(60032, "费用类型不存在，请重试"),
    COST_TYPE_NOT_NULL(60033, "费用类型不能为空，请重试"),
    PRODUCT_TYPE_NOT_NULL(60034, "商品类型不能为空，请重试"),

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
