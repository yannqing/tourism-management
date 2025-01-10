create table role_user
(
    uid int null,
    rid int null
)
    collate = utf8mb4_general_ci
    row_format = DYNAMIC;

INSERT INTO property_management.role_user (uid, rid) VALUES (1, 1);
