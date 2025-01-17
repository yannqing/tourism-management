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
    sex                  tinyint                            null comment '性别（1男，0女）',
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

INSERT INTO property_management.user (userId, username, password, address, phone, email, age, signature, sex, avatar, nickName, enabled, accountNoExpired, credentialsNoExpired, accountNoLocked, createTime, updateTime, isDelete, description) VALUES (1, 'yannqing', '$2a$10$nz2z/yhYtDMQc.x0RbDiGO5ye1u8jgdzsBWy54YEeGkmc.xthezVS', null, null, null, 0, null, 0, null, 'yannqingNickName', 1, 1, 1, 1, '2025-01-10 15:58:59', '2025-01-16 18:09:11', 0, null);
INSERT INTO property_management.user (userId, username, password, address, phone, email, age, signature, sex, avatar, nickName, enabled, accountNoExpired, credentialsNoExpired, accountNoLocked, createTime, updateTime, isDelete, description) VALUES (2, 'testtest1', '$2a$10$g9DYqJ3RNy/Hfd67XRHlAuNLpU6ngUXXTgvpLFYLA/6hqZz4GlKtO', null, null, null, null, null, null, null, 'newNickName', 1, 1, 1, 1, '2025-01-15 09:48:38', '2025-01-17 13:55:57', 0, null);
INSERT INTO property_management.user (userId, username, password, address, phone, email, age, signature, sex, avatar, nickName, enabled, accountNoExpired, credentialsNoExpired, accountNoLocked, createTime, updateTime, isDelete, description) VALUES (3, 'test2test2', '$2a$10$x98PDytpFNmjN1wr/gq35um1X9FoRDOk2PnwpAPSwfC8h8foSn1Fy', null, null, null, null, null, null, null, 'test2昵称xxxx', 1, 1, 1, 1, '2025-01-16 16:51:06', '2025-01-17 13:55:57', 0, null);
