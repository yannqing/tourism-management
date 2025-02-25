package com.yangg.tourism.domain.dto.tourist;

import com.yangg.tourism.domain.model.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * @description: 查询所有旅游资源类型 dto
 * @author: yannqing
 * @create: 2025-02-08 17:18
 * @from: <更多资料：yannqing.com>
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "QueryResourcesTypeDto", description = "查询所有旅游资源类型请求参数")
public class QueryResourcesTypeDto extends PageRequest implements Serializable {

    /**
     * 资源类型id
     */
    @Schema(description = "资源类型id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer id;

    /**
     * 类型名称
     */
    @Schema(description = "类型名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String name;

    /**
     * 类型描述
     */
    @Schema(description = "类型描述", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    @Serial
    private static final long serialVersionUID = 1L;
}
