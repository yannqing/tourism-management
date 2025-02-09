package com.yannqing.template.domain.dto.cost;

import com.yannqing.template.domain.entity.CostType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @description: 更新费用类型 dto
 * @author: yannqing
 * @create: 2025-02-08 17:44
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "UpdateCostTypeDto", description = "修改费用类型对象")
public class UpdateCostTypeDto {
    /**
     * 费用类型id
     */
    @Schema(description = "费用类型id", requiredMode = Schema.RequiredMode.REQUIRED)
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

    public static CostType objToCostType(UpdateCostTypeDto updateCostTypeDto) {
        if (updateCostTypeDto == null) {
            return null;
        }

        CostType costType = new CostType();
        BeanUtils.copyProperties(updateCostTypeDto, costType);
        return costType;
    }
}
