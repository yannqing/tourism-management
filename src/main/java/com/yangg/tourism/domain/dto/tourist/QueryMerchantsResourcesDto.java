package com.yangg.tourism.domain.dto.tourist;

import com.yangg.tourism.domain.model.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * @description: 查询商户旅游资源 dto
 * @author: yannqing
 * @create: 2025-02-08 17:18
 * @from: <更多资料：yannqing.com>
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "QueryMerchantsResourcesDto", description = "查询商户旅游资源请求参数")
public class QueryMerchantsResourcesDto extends PageRequest implements Serializable {

    /**
     * 旅游资源类型 id
     */
    @Schema(description = "旅游资源类型id（0是查全部商铺，除商品之外）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer typeId;

    @Serial
    private static final long serialVersionUID = 1L;
}
