create table permissions
(
    id         int auto_increment
        primary key,
    pid        int                                null comment '该权限的父id',
    name       varchar(255)                       null comment '名称',
    code       varchar(255)                       null comment '权限编码',
    type       int      default 1                 null comment '0代表菜单1权限',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 null comment '0代表未删除，1代表已删除'
)
    collate = utf8mb4_general_ci
    row_format = DYNAMIC;

INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (1, 0, '用户认证', 'USER_AUTH', 0, '2025-01-17 10:58:27', '2025-01-17 16:05:15', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (2, 1, '用户登录', 'AUTH_LOGIN', 1, '2025-01-17 14:33:43', '2025-01-17 16:04:20', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (3, 1, '用户注册', 'AUTH_REGISTER', 1, '2025-01-17 14:35:50', '2025-01-17 16:04:20', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (4, 1, '用户登出', 'AUTH_LOGOUT', 1, '2025-01-17 16:04:30', '2025-01-17 16:06:57', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (5, 0, '用户管理', 'USER_MANAGEMENT', 0, '2025-01-17 16:05:15', '2025-01-17 16:14:32', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (6, 5, '查询所有用户', 'USER_GET_ALL', 1, '2025-01-17 16:05:46', '2025-01-17 16:14:32', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (7, 5, '新增用户', 'USER_ADD', 1, '2025-01-17 16:06:15', '2025-01-17 16:14:32', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (8, 5, '修改个人信息', 'USER_UPDATE_MYSELF', 1, '2025-01-17 16:06:50', '2025-01-17 16:06:57', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (9, 5, '修改用户信息', 'USER_UPDATE_USER_INFO', 1, '2025-01-17 16:07:14', '2025-01-17 16:10:20', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (10, 5, '修改个人密码', 'USER_UPDATE_MYSELF_PASSWORD', 1, '2025-01-17 16:07:26', '2025-01-17 16:10:20', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (11, 5, '重置用户密码', 'USER_RESET_PASSWORD', 1, '2025-01-17 16:07:38', '2025-01-17 16:10:20', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (12, 5, '导出所有用户信息', 'USER_EXPORT', 1, '2025-01-17 16:07:50', '2025-01-17 16:10:20', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (13, 5, '给用户添加角色', 'USER_ADD_ROLE_TO_USER', 1, '2025-01-17 16:08:02', '2025-01-17 16:10:20', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (14, 5, '查询单个用户', 'USER_GET_ONE', 1, '2025-01-17 16:08:14', '2025-01-17 16:10:20', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (15, 5, '删除用户', 'USER_DELETE_ONE', 1, '2025-01-17 16:08:23', '2025-01-17 16:10:20', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (16, 5, '查询用户角色', 'USER_GET_ROLE_BY_USER', 1, '2025-01-17 16:08:33', '2025-01-17 16:10:20', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (17, 5, '批量删除用户', 'USER_DELETE_BATCH', 1, '2025-01-17 16:08:42', '2025-01-17 16:10:20', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (18, 0, '角色管理', 'ROLE_MANAGEMENT', 0, '2025-01-17 16:10:40', '2025-01-17 16:14:32', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (19, 18, '获取所有角色', 'ROLE_GET_ALL', 1, '2025-01-17 16:12:18', '2025-01-17 16:14:32', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (20, 18, '新增角色', 'ROLE_ADD', 1, '2025-01-17 16:12:18', '2025-01-17 16:14:32', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (21, 18, '更新角色', 'ROLE_UPDATE', 1, '2025-01-17 16:12:18', '2025-01-17 16:14:32', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (22, 18, '给角色新增权限', 'ROLE_ADD_PERMISSION_TO_ROLE', 1, '2025-01-17 16:12:18', '2025-01-17 16:14:32', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (23, 18, '查询角色权限', 'ROLE_GET_PERMISSION', 1, '2025-01-17 16:12:18', '2025-01-17 16:14:32', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (24, 18, '删除角色', 'ROLE_DELETE_ONE', 1, '2025-01-17 16:12:18', '2025-01-17 16:14:32', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (25, 18, '批量删除角色', 'ROLE_DELETE_BATCH', 1, '2025-01-17 16:12:18', '2025-01-17 16:14:32', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (26, 0, '权限管理', 'PERMISSION_MANAGEMENT', 0, '2025-01-17 16:15:11', '2025-01-17 16:16:45', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (27, 26, '查询所有权限', 'PERMISSION_GET_ALL', 1, '2025-01-17 16:16:01', '2025-01-17 16:16:45', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (28, 26, '新增权限', 'PERMISSION_ADD', 1, '2025-01-17 16:16:01', '2025-01-17 16:16:45', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (29, 26, '编辑权限', 'PERMISSION_UPDATE', 1, '2025-01-17 16:16:01', '2025-01-17 16:16:45', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (30, 26, '删除权限', 'PERMISSION_DELETE_ONE', 1, '2025-01-17 16:16:01', '2025-01-17 16:16:45', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (31, 26, '批量删除权限', 'PERMISSION_DELETE_BATCH', 1, '2025-01-17 16:16:01', '2025-01-17 16:16:45', 0);
INSERT INTO property_management.permissions (id, pid, name, code, type, createTime, updateTime, isDelete) VALUES (32, 5, '查询用户权限', 'USER_GET_PERMISSION_BY_USER', 1, '2025-01-17 17:45:16', '2025-01-17 17:45:16', 0);
