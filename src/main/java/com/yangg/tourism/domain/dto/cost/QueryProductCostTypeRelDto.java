package com.yangg.tourism.domain.dto.cost;

import com.yangg.tourism.domain.model.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * @description: 查询所有费用类型 dto
 * @author: yannqing
 * @create: 2025-02-08 17:18
 * @from: <更多资料：yannqing.com>
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "QueryCostTypeDto", description = "查询所有费用类型请求参数")
public class QueryProductCostTypeRelDto extends PageRequest implements Serializable {

    /**
     * 主键id
     */
    @Schema(description = "主键id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer id;

    /**
     * 产品类型id
     */
    @Schema(description = "产品类型id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer pid;

    /**
     * 订单类型id
     */
    @Schema(description = "订单类型id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer cid;

    @Serial
    private static final long serialVersionUID = 1L;
}
