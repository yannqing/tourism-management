package com.yannqing.template.exception;

/**
 * @description: 基本异常状态码
 * @author: yannqing
 * @create: 2025-01-14 16:58
 **/
public interface BaseExceptionInterface {
    // 获取异常码
    int getErrorCode();

    // 获取异常信息
    String getErrorMessage();
}
