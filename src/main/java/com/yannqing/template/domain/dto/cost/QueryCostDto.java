package com.yannqing.template.domain.dto.cost;

import com.yannqing.template.domain.model.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 查询所有费用 dto
 * @author: yannqing
 * @create: 2025-02-08 17:18
 * @from: <更多资料：yannqing.com>
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "QueryCostDto", description = "查询所有费用信息请求参数")
public class QueryCostDto extends PageRequest implements Serializable {
    /**
     * 费用id
     */
    @Schema(description = "费用id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer id;

    /**
     * 费用名称
     */
    @Schema(description = "费用名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
    @Schema(description = "金额", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private BigDecimal amount;

    /**
     * 消费者id
     */
    @Schema(description = "消费者id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer consumer;

    /**
     * 收款者id
     */
    @Schema(description = "收款者id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer payee;

    /**
     * 状态（0未支付，1已支付，-1待审批）
     */
    @Schema(description = "状态", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer status;

    /**
     * 实际支付时间
     */
    @Schema(description = "实际支付时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date expenseTime;
}
