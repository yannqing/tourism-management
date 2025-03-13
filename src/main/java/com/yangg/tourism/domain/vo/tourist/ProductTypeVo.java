package com.yangg.tourism.domain.vo.tourist;

import com.yangg.tourism.domain.entity.CostType;
import com.yangg.tourism.domain.entity.ProductType;
import com.yangg.tourism.enums.ErrorType;
import com.yangg.tourism.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTypeVo {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 代码
     */
    private String code;

    /**
     * 单位
     */
    private String unit;

    /**
     * 描述/内容
     */
    private String description;

    /**
     * 描述/内容
     */
    private CostType costType;


    public static ProductTypeVo productTypeToVo(ProductType productType) {
        if (productType == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }
        ProductTypeVo productTypeVo = new ProductTypeVo();
        BeanUtils.copyProperties(productType, productTypeVo);
        return productTypeVo;
    }
}
