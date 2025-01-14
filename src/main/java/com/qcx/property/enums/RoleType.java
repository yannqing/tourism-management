package com.qcx.property.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * 系统角色枚举（对应数据库 role 表格，和 role 表数据一定要同步）
 */
@Getter
@AllArgsConstructor
public enum RoleType {
    USER(1, "user", "普通用户"),
    ADMIN(2, "admin", "管理员")
    ;

    private final int roleId;
    private final String roleCode;
    private final String remark;
}
