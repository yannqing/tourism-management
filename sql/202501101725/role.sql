create table role
(
    id       int auto_increment comment '角色id'
        primary key,
    roleName varchar(255)      null comment '角色名',
    remark   varchar(255)      null comment '角色含义',
    isDelete tinyint default 0 null comment '逻辑删除'
)
    collate = utf8mb4_general_ci
    row_format = DYNAMIC;

INSERT INTO property_management.role (id, roleName, remark, isDelete) VALUES (1, 'user', '普通用户', 0);
