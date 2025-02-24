package com.yangg.tourism.enums.announcement;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 公告状态
 * @author: yannqing
 * @create: 2025-02-09 14:51
 * @from: <更多资料：yannqing.com>
 **/
@Getter
@AllArgsConstructor
public enum AnnouncementStatus {

    PUBLISH(1, "已发布"),
    NOT_PUBLISH(0, "未发布")
    ;


    // 公告状态值
    private final int value;
    // 公告状态描述
    private final String description;
}
