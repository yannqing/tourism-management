package com.yannqing.template.domain.dto.cost;

import com.yannqing.template.domain.entity.Cost;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 新增费用 dto
 * @author: yannqing
 * @create: 2025-02-08 18:10
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "AddCostDto", description = "新增费用请求参数")
public class AddCostDto implements Serializable {
    /**
     * 费用名称
     */
    @Schema(description = "费用名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    /**
     * 费用类型id
     */
    @Schema(description = "费用类型id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer type;

    /**
     * 支付方式（0现金，1支付宝，-1微信支付）
     */
    @Schema(description = "支付方式", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer paymentMethod;

    /**
     * 金额
     */
    @Schema(description = "金额", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal amount;

    /**
     * 消费者id
     */
    @Schema(description = "消费者id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer consumer;

    /**
     * 收款者id
     */
    @Schema(description = "收款者id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer payee;

    /**
     * 状态（0未支付，1已支付，-1待审批）
     */
    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer status;

    /**
     * 实际支付时间
     */
    @Schema(description = "实际支付时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date expenseTime;

    /**
     * 备注
     */
    @Schema(description = "备注", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    public static Cost objToCost(AddCostDto addCostDto) {
        if (addCostDto == null) {
            return null;
        }

        Cost cost = new Cost();
        BeanUtils.copyProperties(addCostDto, cost);
        return cost;
    }
}
