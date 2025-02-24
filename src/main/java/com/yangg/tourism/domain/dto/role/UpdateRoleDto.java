package com.yangg.tourism.domain.dto.role;

import com.yangg.tourism.domain.entity.Role;
import com.yangg.tourism.enums.ErrorType;
import com.yangg.tourism.exception.BusinessException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

/**
 * @description: 更新角色 dto
 * @author: yannqing
 * @create: 2025-01-16 10:00
 * @from: <更多资料：yannqing.com>
 **/
@Schema(name = "UpdateRoleDto", description = "更新角色请求参数")
@Data
public class UpdateRoleDto {

    /**
     * 角色 id
     */
    @Schema(description = "更新的角色id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    /**
     * 角色名
     */
    @Schema(description = "角色名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String roleName;

    /**
     * 角色含义
     */
    @Schema(description = "角色含义", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String remark;

    public static Role dtoToObj(UpdateRoleDto updateRoleDto) {
        Optional.ofNullable(updateRoleDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Role role = new Role();
        BeanUtils.copyProperties(updateRoleDto, role);

        return role;
    }
}
