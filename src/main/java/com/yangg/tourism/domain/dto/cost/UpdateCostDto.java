package com.yangg.tourism.domain.dto.cost;

import com.yangg.tourism.domain.entity.Cost;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 更新费用 dto
 * @author: yannqing
 * @create: 2025-02-08 17:44
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "UpdateCostDto", description = "修改费用信息对象")
public class UpdateCostDto {
    /**
     * 费用id
     */
    @Schema(description = "费用id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    /**
     * 费用名称
     */
    @Schema(description = "昵称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String name;

    /**
     * 费用类型
     */
    @Schema(description = "费用类型", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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

    public static Cost objToCost(UpdateCostDto updateCostDto) {
        if (updateCostDto == null) {
            return null;
        }

        Cost cost = new Cost();
        BeanUtils.copyProperties(updateCostDto, cost);
        return cost;
    }
}
