package com.yangg.tourism.enums.cost;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 费用支付方式
 * @author: yannqing
 * @create: 2025-02-09 14:51
 * @from: <更多资料：yannqing.com>
 **/
@Getter
@AllArgsConstructor
public enum CostMethod {
    CASH(0, "现金"),
    ALIPAY(1, "支付宝"),
    WECHAT_PAY(-1, "微信支付")
    ;


    // 费用支付方式值
    private final int value;
    // 费用支付方式描述
    private final String description;

}
