package com.yannqing.template.enums.announcement;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 公告是否置顶
 * @author: yannqing
 * @create: 2025-02-09 14:51
 * @from: <更多资料：yannqing.com>
 **/
@Getter
@AllArgsConstructor
public enum AnnouncementIsTop {

    TOP(1, "置顶"),
    NOT_TOP(0, "不置顶")
    ;


    // 公告是否置顶的值
    private final int value;
    // 公告是否置顶的描述
    private final String description;
}
