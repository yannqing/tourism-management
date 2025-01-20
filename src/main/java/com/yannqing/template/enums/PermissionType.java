package com.yannqing.template.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

/**
 * 系统权限枚举（对应数据库 permission 表格，和 permission 表数据一定要同步）
 */
@Getter
@AllArgsConstructor
public enum PermissionType {
    USER_AUTH("用户认证", "USER_AUTH", 0),
    USER_MANAGEMENT("用户管理", "USER_MANAGEMENT", 0),
    ROLE_MANAGEMENT("角色管理", "ROLE_MANAGEMENT", 0),
    PERMISSION_MANAGEMENT( "权限管理", "PERMISSION_MANAGEMENT", 0),
    AUTH_LOGIN("用户登录", "AUTH_LOGIN", 1),
    AUTH_REGISTER("用户注册", "AUTH_REGISTER", 1),
    AUTH_LOGOUT("用户登出", "AUTH_LOGOUT", 1),
    USER_GET_ALL( "查询所有用户", "USER_GET_ALL", 1),
    USER_ADD("新增用户", "USER_ADD", 1),
    USER_UPDATE_MYSELF("修改个人信息", "USER_UPDATE_MYSELF", 1),
    USER_UPDATE_USER_INFO( "修改用户信息", "USER_UPDATE_USER_INFO", 1),
    USER_UPDATE_MYSELF_PASSWORD( "修改个人密码", "USER_UPDATE_MYSELF_PASSWORD", 1),
    USER_RESET_PASSWORD("重置用户密码", "USER_RESET_PASSWORD", 1),
    USER_EXPORT("导出用户数据", "USER_EXPORT", 1),
    USER_ADD_ROLE_TO_USER("给用户新增角色", "USER_ADD_ROLE_TO_USER", 1),
    USER_GET_ONE("查询单个用户", "USER_GET_ONE", 1),
    USER_DELETE_ONE("删除单个用户", "USER_DELETE_ONE", 1),
    USER_GET_ROLE_BY_USER("查询用户角色", "USER_GET_ROLE_BY_USER", 1),
    USER_DELETE_BATCH("批量删除用户", "USER_DELETE_BATCH", 1),
    USER_GET_PERMISSION_BY_USER("查询用户权限", "USER_GET_PERMISSION_BY_USER", 1),

    ROLE_GET_ALL("查询所有角色", "ROLE_GET_ALL", 1),
    ROLE_ADD("新增角色", "ROLE_ADD", 1),
    ROLE_UPDATE("更新角色", "ROLE_UPDATE", 1),
    ROLE_ADD_PERMISSION_TO_ROLE("给角色新增权限", "ROLE_ADD_PERMISSION_TO_ROLE", 1),
    ROLE_GET_PERMISSION("查询角色权限", "ROLE_GET_PERMISSION", 1),
    ROLE_DELETE_ONE("删除单个角色", "ROLE_DELETE_ONE", 1),
    ROLE_DELETE_BATCH("批量删除角色", "ROLE_DELETE_BATCH", 1),
    PERMISSION_ADD("新增权限", "PERMISSION_ADD", 1),
    PERMISSION_GET_ALL("查询全部权限", "PERMISSION_GET_ALL", 1),
    PERMISSION_UPDATE("更新权限", "PERMISSION_UPDATE", 1),
    PERMISSION_DELETE_ONE( "删除单个权限", "PERMISSION_DELETE_ONE", 1),
    PERMISSION_DELETE_BATCH("批量删除权限", "PERMISSION_DELETE_BATCH", 1),
    ;

    private final String name;
    @Getter
    private final String code;
    private final int type;

    /**
     * 根据 value 获取枚举
     *
     * @param code
     * @return
     */
    public static PermissionType getEnumByValue(String code) {
        if (ObjectUtils.isEmpty(code)) {
            return null;
        }
        for (PermissionType anEnum : PermissionType.values()) {
            if (anEnum.getCode().equals(code)) {
                return anEnum;
            }
        }
        return null;
    }

}
