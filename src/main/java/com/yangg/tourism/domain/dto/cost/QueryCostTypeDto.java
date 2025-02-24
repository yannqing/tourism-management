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
public class QueryCostTypeDto extends PageRequest implements Serializable {
    /**
     * 费用类型id
     */
    @Schema(description = "费用类型id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer id;

    /**
     * 费用类型父级id
     */
    @Schema(description = "费用类型父级id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer pid;

    /**
     * 费用类型名称
     */
    @Schema(description = "费用类型名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String name;

    /**
     * 费用类型编码
     */
    @Schema(description = "费用类型编码", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String code;

    /**
     * 费用类型描述
     */
    @Schema(description = "费用类型描述", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    @Serial
    private static final long serialVersionUID = 1L;
}
