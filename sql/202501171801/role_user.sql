create table role_user
(
    uid        int                                null,
    rid        int                                null,
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '逻辑删除'
)
    collate = utf8mb4_general_ci
    row_format = DYNAMIC;

INSERT INTO property_management.role_user (uid, rid, createTime, updateTime, isDelete) VALUES (1, 1, '2025-01-16 10:32:20', '2025-01-16 10:32:20', 0);
INSERT INTO property_management.role_user (uid, rid, createTime, updateTime, isDelete) VALUES (2, 1, '2025-01-17 13:56:22', '2025-01-17 13:56:22', 0);
INSERT INTO property_management.role_user (uid, rid, createTime, updateTime, isDelete) VALUES (3, 1, '2025-01-17 13:56:22', '2025-01-17 13:57:45', 0);
INSERT INTO property_management.role_user (uid, rid, createTime, updateTime, isDelete) VALUES (1, 2, '2025-01-17 14:54:07', '2025-01-17 14:54:07', 0);
