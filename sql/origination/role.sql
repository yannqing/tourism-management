-- auto-generated definition
create table role
(
    id       int          not null comment '角色id'
        primary key,
    roleName varchar(255) null comment '角色名',
    remark   varchar(255) null comment '角色含义',
    isDelete tinyint default 0 comment '逻辑删除'
)