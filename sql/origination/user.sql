create table user
(
    userId                int auto_increment comment '用户id'
        primary key,
    username               varchar(255)                     null comment '用户名',
    password               varchar(255)                     null comment '密码',
    address                varchar(255)                     null comment '地址',
    phone                  varchar(255)                     null comment '电话',
    email                  varchar(255)                     null comment '邮箱',
    age                    int                              null comment '年龄',
    signature              varchar(255)                     null comment '签名',
    sex                    varchar(255)                     null comment '性别',
    avatar                 varchar(255)                     null comment '头像',
    nickName              varchar(255)                     null comment '昵称',
    enabled                int                              null comment '账户是否可用',
    accountNoExpired     int                              null comment '账户是否过期',
    credentialsNoExpired int                              null comment '密码是否过期',
    accountNoLocked      int                              null comment '是否被锁定',
    createTime            datetime                         null default CURRENT_TIMESTAMP comment '创建时间',
    updateTime            datetime                         null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete               tinyint                         null default 0 comment '逻辑删除',
    description            varchar(1024)                    null comment '备注'
);