create table permissions
(
    id         int auto_increment
        primary key,
    pid        int                                null comment '该权限的父id',
    name       varchar(255)                       null comment '名称',
    code       varchar(255)                       null comment '权限编码',
    type       int                                null comment '0代表菜单1权限',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 null comment '0代表未删除，1代表已删除'
)
    collate = utf8mb4_general_ci
    row_format = DYNAMIC;

