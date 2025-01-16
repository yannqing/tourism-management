create table role_permissions
(
    rid        int                                null,
    pid        int                                null,
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '逻辑删除'
)
    collate = utf8mb4_general_ci
    row_format = DYNAMIC;

