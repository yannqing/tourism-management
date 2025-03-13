package com.yangg.tourism.domain.vo.tourist;

import com.yangg.tourism.domain.entity.CostType;
import com.yangg.tourism.domain.entity.ProductCostTypeRel;
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
public class ProductCostTypeRelVo {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 产品类型id
     */
    private ProductType productType;

    /**
     * 订单类型id
     */
    private CostType costType;

    public static ProductCostTypeRelVo productCostTypeRelToVo(ProductCostTypeRel productCostTypeRel) {
        if (productCostTypeRel == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }
        ProductCostTypeRelVo productCostTypeRelVo = new ProductCostTypeRelVo();
        BeanUtils.copyProperties(productCostTypeRel, productCostTypeRelVo);
        return productCostTypeRelVo;
    }

}
