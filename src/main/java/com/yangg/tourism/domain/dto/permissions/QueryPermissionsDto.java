package com.yangg.tourism.domain.dto.permissions;

import com.yangg.tourism.domain.model.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @description: 管理员查询所有权限 dto
 * @author: yannqing
 * @create: 2025-01-16 15:44
 * @from: <更多资料：yannqing.com>
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "QueryPermissionsDto", description = "查询所有权限请求参数")
public class QueryPermissionsDto extends PageRequest implements Serializable {


    /**
     * 父id
     */
    @Schema(description = "权限父id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer pid;

    /**
     * id
     */
    @Schema(description = "权限id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer id;

    /**
     * 名称
     */
    @Schema(description = "名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String name;

    /**
     * 权限编码
     */
    @Schema(description = "权限编码", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String code;

    /**
     * 0代表菜单1权限
     */
    @Schema(description = "0代表菜单1权限", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer type;
}
