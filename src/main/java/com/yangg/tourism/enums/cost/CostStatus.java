package com.yangg.tourism.enums.cost;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 费用状态
 * @author: yannqing
 * @create: 2025-02-09 14:51
 * @from: <更多资料：yannqing.com>
 **/
@Getter
@AllArgsConstructor
public enum CostStatus {
    NOT_PAY(0, "未支付"),
    ALREADY_PAY(1, "已支付"),
    WAIT_APPROVAL(-1, "待审批")
    ;


    // 费用状态值
    private final int value;
    // 费用状态描述
    private final String description;

}
