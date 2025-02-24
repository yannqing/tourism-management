package com.yangg.tourism.domain.dto.permissions;

import com.yangg.tourism.domain.entity.Permissions;
import com.yangg.tourism.enums.ErrorType;
import com.yangg.tourism.exception.BusinessException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Optional;

/**
 * @description: 更新权限 Dto
 * @author: yannqing
 * @create: 2025-01-16 15:48
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "UpdatePermissionsDto", description = "更新角色请求参数")
public class UpdatePermissionsDto implements Serializable {
    /**
     *
     */
    @Schema(description = "更新的权限id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    /**
     * 该权限的父id
     */
    @Schema(description = "该权限的父id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer pid;

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

    public static Permissions dtoToObj(UpdatePermissionsDto updatePermissionsDto) {
        Optional.ofNullable(updatePermissionsDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Permissions permissions = new Permissions();
        BeanUtils.copyProperties(updatePermissionsDto, permissions);

        return permissions;
    }
}
