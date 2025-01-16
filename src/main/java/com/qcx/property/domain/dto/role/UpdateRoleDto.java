package com.qcx.property.domain.dto.role;

import com.qcx.property.domain.entity.Role;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

/**
 * @description: 更新角色 dto
 * @author: yannqing
 * @create: 2025-01-16 10:00
 * @from: <更多资料：yannqing.com>
 **/
@Data
public class UpdateRoleDto {

    /**
     * 角色 id
     */
    private Integer id;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色含义
     */
    private String remark;

    public static Role dtoToObj(UpdateRoleDto updateRoleDto) {
        Optional.ofNullable(updateRoleDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Role role = new Role();
        BeanUtils.copyProperties(updateRoleDto, role);

        return role;
    }
}
