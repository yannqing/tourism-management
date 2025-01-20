package com.qcx.property.domain.dto.permissions;

import com.qcx.property.domain.entity.Permissions;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Optional;

/**
 * @description: 新增权限 dto
 * @author: yannqing
 * @create: 2025-01-16 15:22
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "AddPermissionDto", description = "新增权限信息请求参数")
public class AddPermissionDto implements Serializable {
    /**
     * 该权限的父id
     */
    @Schema(description = "该权限的父id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer pid;

    /**
     * 名称
     */
    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    /**
     * 权限编码
     */
    @Schema(description = "权限编码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;

    /**
     * 0代表菜单1权限
     */
    @Schema(description = "0代表菜单1权限", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer type;

    public static Permissions dtoToObj(AddPermissionDto addPermissionDto) {
        Optional.ofNullable(addPermissionDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Permissions permissions = new Permissions();
        BeanUtils.copyProperties(addPermissionDto, permissions);

        return permissions;
    }
}
