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

INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (1, 1, '2025-01-17 14:34:01', '2025-01-17 14:34:01', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (1, 2, '2025-01-17 14:34:13', '2025-01-17 14:34:13', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (1, 3, '2025-01-17 14:35:55', '2025-01-17 14:35:55', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (1, 4, '2025-01-17 17:49:26', '2025-01-17 17:49:26', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (1, 8, '2025-01-17 17:52:13', '2025-01-17 17:52:13', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (1, 10, '2025-01-17 17:52:13', '2025-01-17 17:52:13', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (1, 16, '2025-01-17 17:52:13', '2025-01-17 17:52:13', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (1, 32, '2025-01-17 17:52:13', '2025-01-17 17:52:13', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (1, 5, '2025-01-17 17:53:02', '2025-01-17 17:53:02', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (1, 18, '2025-01-17 17:53:02', '2025-01-17 17:53:02', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (1, 23, '2025-01-17 17:53:02', '2025-01-17 17:53:02', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 1, '2025-01-17 17:53:32', '2025-01-17 17:53:32', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 2, '2025-01-17 17:53:32', '2025-01-17 17:53:32', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 3, '2025-01-17 17:53:32', '2025-01-17 17:53:32', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 4, '2025-01-17 17:53:32', '2025-01-17 17:53:32', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 5, '2025-01-17 17:53:32', '2025-01-17 17:53:32', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 6, '2025-01-17 17:53:32', '2025-01-17 17:53:32', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 7, '2025-01-17 17:53:32', '2025-01-17 17:53:32', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 8, '2025-01-17 17:53:32', '2025-01-17 17:53:32', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 9, '2025-01-17 17:53:32', '2025-01-17 17:53:32', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 10, '2025-01-17 17:53:32', '2025-01-17 17:53:32', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 11, '2025-01-17 17:53:32', '2025-01-17 17:53:32', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 12, '2025-01-17 17:53:32', '2025-01-17 17:53:32', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 13, '2025-01-17 17:53:32', '2025-01-17 17:53:32', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 14, '2025-01-17 17:53:32', '2025-01-17 17:53:32', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 15, '2025-01-17 17:53:32', '2025-01-17 17:53:32', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 16, '2025-01-17 17:54:12', '2025-01-17 17:54:12', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 17, '2025-01-17 17:54:12', '2025-01-17 17:54:12', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 18, '2025-01-17 17:54:12', '2025-01-17 17:54:12', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 19, '2025-01-17 17:54:12', '2025-01-17 17:54:12', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 20, '2025-01-17 17:54:12', '2025-01-17 17:54:12', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 21, '2025-01-17 17:54:12', '2025-01-17 17:54:12', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 22, '2025-01-17 17:54:12', '2025-01-17 17:54:12', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 23, '2025-01-17 17:54:12', '2025-01-17 17:54:12', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 24, '2025-01-17 17:54:12', '2025-01-17 17:54:12', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 25, '2025-01-17 17:54:12', '2025-01-17 17:54:12', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 26, '2025-01-17 17:54:12', '2025-01-17 17:54:12', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 27, '2025-01-17 17:54:12', '2025-01-17 17:54:12', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 28, '2025-01-17 17:54:12', '2025-01-17 17:54:12', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 29, '2025-01-17 17:54:12', '2025-01-17 17:54:12', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 30, '2025-01-17 17:54:12', '2025-01-17 17:54:12', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 31, '2025-01-17 17:54:21', '2025-01-17 17:54:21', 0);
INSERT INTO property_management.role_permissions (rid, pid, createTime, updateTime, isDelete) VALUES (2, 32, '2025-01-17 17:54:21', '2025-01-17 17:54:21', 0);
