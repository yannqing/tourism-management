create table permissions
(
    id       int auto_increment
        primary key,
    pid      int               null comment '该权限的父id',
    name     varchar(255)      null comment '名称',
    code     varchar(255)      null comment '权限编码',
    type     int               null comment '0代表菜单1权限',
    isDelete tinyint default 0 null comment '0代表未删除，1代表已删除'
)