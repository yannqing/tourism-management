package com.yangg.tourism.domain.dto.tourist;

import com.yangg.tourism.domain.entity.ProductType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;

/**
 * @description: 新增产品类型 dto
 * @author: yannqing
 * @create: 2025-02-08 18:10
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "AddProductTypeDto", description = "新增产品类型请求参数")
public class AddProductTypeDto implements Serializable {

    /**
     * 名称
     */
    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    /**
     * 代码
     */
    @Schema(description = "代码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;

    /**
     * 单位
     */
    @Schema(description = "单位", requiredMode = Schema.RequiredMode.REQUIRED)
    private String unit;

    /**
     * 描述/内容
     */
    @Schema(description = "描述/内容", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    @Serial
    private static final long serialVersionUID = 1L;

    public static ProductType objToProductType(AddProductTypeDto addProductTypeDto) {
        if (addProductTypeDto == null) {
            return null;
        }

        ProductType productType = new ProductType();
        BeanUtils.copyProperties(addProductTypeDto, productType);
        return productType;
    }
}
