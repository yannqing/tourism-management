package com.qcx.property.domain.dto.permissions;

import com.qcx.property.domain.entity.Permissions;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
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
public class AddPermissionDto implements Serializable {
    /**
     * 该权限的父id
     */
    private Integer pid;

    /**
     * 名称
     */
    private String name;

    /**
     * 权限编码
     */
    private String code;

    /**
     * 0代表菜单1权限
     */
    private Integer type;

    public static Permissions dtoToObj(AddPermissionDto addPermissionDto) {
        Optional.ofNullable(addPermissionDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Permissions permissions = new Permissions();
        BeanUtils.copyProperties(addPermissionDto, permissions);

        return permissions;
    }
}
