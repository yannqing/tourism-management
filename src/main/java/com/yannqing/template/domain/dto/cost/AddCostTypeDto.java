package com.yannqing.template.domain.dto.cost;

import com.yannqing.template.domain.entity.CostType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;

/**
 * @description: 新增费用类型 dto
 * @author: yannqing
 * @create: 2025-02-08 18:10
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "AddCostTypeDto", description = "新增费用类型请求参数")
public class AddCostTypeDto implements Serializable {
    /**
     * 费用类型父级id
     */
    @Schema(description = "费用类型父级id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer pid;

    /**
     * 费用类型名称
     */
    @Schema(description = "费用类型名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    /**
     * 费用类型编码
     */
    @Schema(description = "费用类型编码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;

    /**
     * 费用类型描述
     */
    @Schema(description = "费用类型描述", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    @Serial
    private static final long serialVersionUID = 1L;

    public static CostType objToCostType(AddCostTypeDto addCostTypeDto) {
        if (addCostTypeDto == null) {
            return null;
        }

        CostType costType = new CostType();
        BeanUtils.copyProperties(addCostTypeDto, costType);
        return costType;
    }
}
