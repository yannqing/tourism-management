package com.yangg.tourism.domain.dto.tourist;

import com.yangg.tourism.domain.model.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * @description: 查询所有产品类型 dto
 * @author: yannqing
 * @create: 2025-02-08 17:18
 * @from: <更多资料：yannqing.com>
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "QueryProductTypeDto", description = "查询所有产品类型请求参数")
public class QueryProductTypeDto extends PageRequest implements Serializable {
    /**
     * 主键id
     */
    @Schema(description = "主键id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
     * 单位
     */
    @Schema(description = "单位", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String unit;

    /**
     * 描述/内容
     */
    @Schema(description = "描述/内容", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    @Serial
    private static final long serialVersionUID = 1L;
}
