package com.qcx.property.exception;

import com.qcx.property.common.Code;
import com.qcx.property.enums.ErrorType;
import lombok.Getter;

/**
 * 自定义异常类
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
        this.code = Code.FAILURE;
    }

    public BusinessException(ErrorType errorType) {
        super(errorType.getErrorMessage());
        this.code = errorType.getErrorCode();
    }
}
