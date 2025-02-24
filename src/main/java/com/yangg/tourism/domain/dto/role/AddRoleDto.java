package com.yangg.tourism.domain.dto.role;

import com.yangg.tourism.domain.entity.Role;
import com.yangg.tourism.enums.ErrorType;
import com.yangg.tourism.exception.BusinessException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;

/**
 * @description: 新增角色 dto
 * @author: yannqing
 * @create: 2025-01-16 10:00
 * @from: <更多资料：yannqing.com>
 **/
@Schema(name = "AddRoleDto", description = "新增角色请求参数")
@Data
public class AddRoleDto implements Serializable {

    /**
     * 角色名
     */
    @Schema(description = "角色名", requiredMode = Schema.RequiredMode.REQUIRED)
    private String roleName;

    /**
     * 角色含义
     */
    @Schema(description = "角色含义", requiredMode = Schema.RequiredMode.REQUIRED)
    private String remark;

    @Serial
    private static final long serialVersionUID = 1L;

    public static Role dtoToObj(AddRoleDto addRoleDto) {
        Optional.ofNullable(addRoleDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Role role = new Role();
        BeanUtils.copyProperties(addRoleDto, role);

        return role;
    }
}
