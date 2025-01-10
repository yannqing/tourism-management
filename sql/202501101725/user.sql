create table user
(
    userId               int auto_increment comment '用户id'
        primary key,
    username             varchar(255)                       null comment '用户名',
    password             varchar(255)                       null comment '密码',
    address              varchar(255)                       null comment '地址',
    phone                varchar(255)                       null comment '电话',
    email                varchar(255)                       null comment '邮箱',
    age                  int                                null comment '年龄',
    signature            varchar(255)                       null comment '签名',
    sex                  varchar(255)                       null comment '性别',
    avatar               varchar(255)                       null comment '头像',
    nickName             varchar(255)                       null comment '昵称',
    enabled              int      default 1                 null comment '账户是否可用',
    accountNoExpired     int      default 1                 null comment '账户是否过期',
    credentialsNoExpired int      default 1                 null comment '密码是否过期',
    accountNoLocked      int      default 1                 null comment '是否被锁定',
    createTime           datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime           datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete             tinyint  default 0                 null comment '逻辑删除',
    description          varchar(1024)                      null comment '备注'
)
    collate = utf8mb4_general_ci
    row_format = DYNAMIC;

INSERT INTO property_management.user (userId, username, password, address, phone, email, age, signature, sex, avatar, nickName, enabled, accountNoExpired, credentialsNoExpired, accountNoLocked, createTime, updateTime, isDelete, description) VALUES (1, 'yannqing', '$2a$10$uvE5CLUEB1XMqEjhRdAj8ew6xsPBD9Ubw3e.v4M3IEa0Shn4ZJgRm', null, null, null, null, null, null, null, null, 1, 1, 1, 1, '2025-01-10 15:58:59', '2025-01-10 15:58:59', 0, null);
