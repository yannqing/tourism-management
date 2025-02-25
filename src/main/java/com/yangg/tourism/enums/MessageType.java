package com.yangg.tourism.enums;

import com.yangg.tourism.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageType {
    REGISTER(1, "商户注册通知"),
    ;

    private final int id;
    private final String remark;

    public static String getRemarkById(int id) {
        for (MessageType type : MessageType.values()) {
            if (type.getId() == id) {
                return type.getRemark();
            }
        }
        throw new BusinessException(ErrorType.SYSTEM_ERROR);
    }
}
