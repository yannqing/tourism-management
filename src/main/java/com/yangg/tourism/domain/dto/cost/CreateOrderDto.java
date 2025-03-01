package com.yangg.tourism.domain.dto.cost;

import com.yangg.tourism.domain.entity.Cost;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 创建订单 dto
 * @author: yannqing
 * @create: 2025-02-08 18:10
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "CreateOrderDto", description = "创建订单请求参数")
public class CreateOrderDto implements Serializable {
    /**
     * 费用名称
     */
    @Schema(description = "费用名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    /**
     * 商品id
     */
    @Schema(description = "商品id（必须是商品，typeId = 5）", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer commodityId;

    /**
     * 费用类型id
     */
    @Schema(description = "费用类型id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer type;

    /**
     * 金额
     */
    @Schema(description = "金额", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal amount;

    /**
     * 备注
     */
    @Schema(description = "备注", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    @Serial
    private static final long serialVersionUID = 1L;

    public static Cost objToCost(CreateOrderDto createOrderDto) {
        if (createOrderDto == null) {
            return null;
        }

        Cost cost = new Cost();
        BeanUtils.copyProperties(createOrderDto, cost);
        return cost;
    }
}
