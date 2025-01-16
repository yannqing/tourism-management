create table role
(
    id         int auto_increment comment '角色id'
        primary key,
    roleName   varchar(255)                       null comment '角色名',
    remark     varchar(255)                       null comment '角色含义',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 null comment '逻辑删除'
)
    collate = utf8mb4_general_ci
    row_format = DYNAMIC;

INSERT INTO property_management.role (id, roleName, remark, createTime, updateTime, isDelete) VALUES (1, 'user', '普通用户', '2025-01-16 10:16:35', '2025-01-16 10:17:25', 0);
INSERT INTO property_management.role (id, roleName, remark, createTime, updateTime, isDelete) VALUES (2, 'admin', '管理员', '2025-01-16 10:16:35', '2025-01-16 10:17:25', 0);
