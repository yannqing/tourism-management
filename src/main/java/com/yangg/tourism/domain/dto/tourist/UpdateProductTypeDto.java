package com.yangg.tourism.domain.dto.tourist;

import com.yangg.tourism.domain.entity.ProductType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @description: 更新产品类型 dto
 * @author: yannqing
 * @create: 2025-02-08 17:44
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "UpdateProductTypeDto", description = "修改产品类型对象")
public class UpdateProductTypeDto {

    /**
     * 主键id
     */
    @Schema(description = "主键id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    /**
     * 名称
     */
    @Schema(description = "名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String name;

    /**
     * 代码
     */
    @Schema(description = "代码", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String code;

    /**
     * 订单类型id
     */
    @Schema(description = "订单类型id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String costTypeId;

    /**
     * 单位
     */
    @Schema(description = "单位", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String unit;

    /**
     * 描述/内容
     */
    @Schema(description = "描述/内容", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    public static ProductType objToProductType(UpdateProductTypeDto updateProductTypeDto) {
        if (updateProductTypeDto == null) {
            return null;
        }

        ProductType productType = new ProductType();
        BeanUtils.copyProperties(updateProductTypeDto, productType);
        return productType;
    }
}
