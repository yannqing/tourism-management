package com.yangg.tourism.domain.dto.cost;

import com.yangg.tourism.domain.entity.ProductCostTypeRel;
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
@Schema(name = "UpdateProductCostTypeRelDto", description = "修改费用类型对象")
public class UpdateProductCostTypeRelDto {

    /**
     * 主键id
     */
    @Schema(description = "主键id", requiredMode = Schema.RequiredMode.REQUIRED)
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

    public static ProductCostTypeRel objToProductCostTypeRel(UpdateProductCostTypeRelDto updateProductCostTypeRelDto) {
        if (updateProductCostTypeRelDto == null) {
            return null;
        }

        ProductCostTypeRel productCostTypeRel = new ProductCostTypeRel();
        BeanUtils.copyProperties(updateProductCostTypeRelDto, productCostTypeRel);
        return productCostTypeRel;
    }
}
