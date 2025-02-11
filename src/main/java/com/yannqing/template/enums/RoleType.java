package com.yannqing.template.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统角色枚举（对应数据库 role 表格，和 role 表数据一定要同步）
 */
@Getter
@AllArgsConstructor
public enum RoleType {
    USER(1, "user", "普通用户"),
    ADMIN(0, "admin", "管理员"),
    OTHER(2, "other", "其他")
    ;

    private final int roleId;
    private final String roleCode;
    private final String remark;
}
