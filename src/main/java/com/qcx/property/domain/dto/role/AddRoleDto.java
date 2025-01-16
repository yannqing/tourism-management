package com.qcx.property.domain.dto.role;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.qcx.property.domain.entity.Role;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Optional;

/**
 * @description: 新增角色 dto
 * @author: yannqing
 * @create: 2025-01-16 10:00
 * @from: <更多资料：yannqing.com>
 **/
@Data
public class AddRoleDto {

    /**
     * 角色名
     */
    private String roleName;


    /**
     * 角色含义
     */
    private String remark;

    public static Role dtoToObj(AddRoleDto addRoleDto) {
        Optional.ofNullable(addRoleDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Role role = new Role();
        BeanUtils.copyProperties(addRoleDto, role);

        return role;
    }
}
