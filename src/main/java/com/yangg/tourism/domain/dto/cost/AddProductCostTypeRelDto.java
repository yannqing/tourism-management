package com.yangg.tourism.domain.dto.cost;

import com.yangg.tourism.domain.entity.ProductCostTypeRel;
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
@Schema(name = "AddProductCostTypeRelDto", description = "新增费用类型请求参数")
public class AddProductCostTypeRelDto implements Serializable {

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

    public static ProductCostTypeRel objToProductCostTypeRel(AddProductCostTypeRelDto addProductCostTypeRelDto) {
        if (addProductCostTypeRelDto == null) {
            return null;
        }

        ProductCostTypeRel productCostTypeRel = new ProductCostTypeRel();
        BeanUtils.copyProperties(addProductCostTypeRelDto, productCostTypeRel);
        return productCostTypeRel;
    }
}
