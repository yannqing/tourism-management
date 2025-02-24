package com.yangg.tourism.enums.announcement;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 公告类型
 * @author: yannqing
 * @create: 2025-02-09 14:51
 * @from: <更多资料：yannqing.com>
 **/
@Getter
@AllArgsConstructor
public enum AnnouncementType {

    NOTICE(1, "通知"),
    NEWS(2, "新闻"),
    EMERGENCY_ANNOUNCEMENT(3, "紧急公告")
    ;


    // 公告类型值
    private final int value;
    // 公告类型描述
    private final String description;
}
