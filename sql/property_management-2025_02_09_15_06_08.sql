/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
DROP TABLE IF EXISTS `announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `announcement` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '公告id',
  `noticeTitle` varchar(1024) DEFAULT NULL COMMENT '公告标题',
  `noticeContent` text COMMENT '公告内容',
  `type` tinyint NOT NULL DEFAULT '0' COMMENT '公告类型（通知/新闻/紧急公告）',
  `publishTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `publishUser` int NOT NULL COMMENT '发布者id',
  `isTop` tinyint NOT NULL DEFAULT '0' COMMENT '是否置顶（0否，1是）',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态（0未发布，1已发布）',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint DEFAULT '0' COMMENT '逻辑删除',
  `description` varchar(1024) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `announcement` WRITE;
/*!40000 ALTER TABLE `announcement` DISABLE KEYS */;
INSERT INTO `announcement` VALUES (1,'第一个公告1','公告表建立完成',1,'2025-02-09 14:35:49',1,0,1,'2025-02-09 14:35:49','2025-02-09 14:50:18',0,'备注'),(2,'第二个公告','公告表建立完成',1,'2025-02-09 14:48:53',1,0,1,'2025-02-09 14:48:53','2025-02-09 14:50:18',0,'备注2');
/*!40000 ALTER TABLE `announcement` ENABLE KEYS */;
UNLOCK TABLES;
DROP TABLE IF EXISTS `cost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cost` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '费用id',
  `name` varchar(255) DEFAULT NULL COMMENT '费用名称',
  `type` int NOT NULL COMMENT '费用类型id',
  `paymentMethod` tinyint DEFAULT NULL COMMENT '支付方式（0现金，1支付宝，-1微信支付）',
  `amount` decimal(15,2) NOT NULL COMMENT '金额',
  `consumer` int NOT NULL COMMENT '消费者id',
  `payee` int DEFAULT NULL COMMENT '收款者id',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态（0未支付，1已支付，-1待审批）',
  `expenseTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '实际支付时间',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint DEFAULT '0' COMMENT '逻辑删除',
  `description` varchar(1024) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `cost` WRITE;
/*!40000 ALTER TABLE `cost` DISABLE KEYS */;
INSERT INTO `cost` VALUES (1,'测试新费用1',1,NULL,22.22,1,NULL,1,'2025-02-09 09:46:15','2025-02-09 09:46:15','2025-02-09 09:52:14',1,NULL),(2,'晚餐补贴',5,NULL,22.22,1,NULL,1,'2025-02-09 10:56:44','2025-02-09 10:56:44','2025-02-09 10:56:44',0,NULL),(3,'聚餐补贴',5,NULL,687.50,2,NULL,1,'2025-02-09 10:57:06','2025-02-09 10:57:06','2025-02-09 10:57:06',0,NULL),(4,'杭州出差',52,NULL,67.50,2,NULL,1,'2025-02-09 10:57:59','2025-02-09 10:57:59','2025-02-09 10:57:59',0,NULL);
/*!40000 ALTER TABLE `cost` ENABLE KEYS */;
UNLOCK TABLES;
DROP TABLE IF EXISTS `cost_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cost_type` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '费用类型id',
  `pid` int NOT NULL DEFAULT '0' COMMENT '费用类型父级id',
  `name` varchar(255) NOT NULL COMMENT '费用类型名称',
  `code` varchar(255) NOT NULL COMMENT '费用类型编码',
  `description` text COMMENT '费用类型描述',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `cost_type` WRITE;
/*!40000 ALTER TABLE `cost_type` DISABLE KEYS */;
INSERT INTO `cost_type` VALUES (1,0,'日常费用','DAILY_EXPENSES','日常支出费用','2025-02-09 10:41:58','2025-02-09 10:54:19',0),(2,0,'差旅费用','TRAVEL_EXPENSES','出差旅行费用','2025-02-09 10:44:06','2025-02-09 10:54:19',0),(3,0,'项目费用','PROJECT_EXPENSES','项目相关费用','2025-02-09 10:44:37','2025-02-09 10:54:19',0),(4,0,'财务费用','FINANCIAL_EXPENSES','财务相关费用','2025-02-09 10:45:58','2025-02-09 10:52:41',0),(5,1,'餐补费用','MEAL_ALLOWANCE','日常餐补费用','2025-02-09 10:47:26','2025-02-09 10:52:41',0),(6,1,'交通费用','TRANSPORTATION_COSTS','日常交通费用','2025-02-09 10:48:00','2025-02-09 10:51:44',0);
/*!40000 ALTER TABLE `cost_type` ENABLE KEYS */;
UNLOCK TABLES;
DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permissions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pid` int DEFAULT NULL COMMENT '该权限的父id',
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `code` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限编码',
  `type` int DEFAULT '1' COMMENT '0代表菜单1权限',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint DEFAULT '0' COMMENT '0代表未删除，1代表已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` VALUES (1,0,'用户认证','USER_AUTH',0,'2025-01-17 10:58:27','2025-01-17 16:05:15',0),(2,1,'用户登录','AUTH_LOGIN',1,'2025-01-17 14:33:43','2025-01-17 16:04:20',0),(3,1,'用户注册','AUTH_REGISTER',1,'2025-01-17 14:35:50','2025-01-17 16:04:20',0),(4,1,'用户登出','AUTH_LOGOUT',1,'2025-01-17 16:04:30','2025-01-17 16:06:57',0),(5,0,'用户管理','USER_MANAGEMENT',0,'2025-01-17 16:05:15','2025-01-17 16:14:32',0),(6,5,'查询所有用户','USER_GET_ALL',1,'2025-01-17 16:05:46','2025-01-17 16:14:32',0),(7,5,'新增用户','USER_ADD',1,'2025-01-17 16:06:15','2025-01-17 16:14:32',0),(8,5,'修改个人信息','USER_UPDATE_MYSELF',1,'2025-01-17 16:06:50','2025-01-17 16:06:57',0),(9,5,'修改用户信息','USER_UPDATE_USER_INFO',1,'2025-01-17 16:07:14','2025-01-17 16:10:20',0),(10,5,'修改个人密码','USER_UPDATE_MYSELF_PASSWORD',1,'2025-01-17 16:07:26','2025-01-17 16:10:20',0),(11,5,'重置用户密码','USER_RESET_PASSWORD',1,'2025-01-17 16:07:38','2025-01-17 16:10:20',0),(12,5,'导出所有用户信息','USER_EXPORT',1,'2025-01-17 16:07:50','2025-01-17 16:10:20',0),(13,5,'给用户添加角色','USER_ADD_ROLE_TO_USER',1,'2025-01-17 16:08:02','2025-01-17 16:10:20',0),(14,5,'查询单个用户','USER_GET_ONE',1,'2025-01-17 16:08:14','2025-01-17 16:10:20',0),(15,5,'删除用户','USER_DELETE_ONE',1,'2025-01-17 16:08:23','2025-01-17 16:10:20',0),(16,5,'查询用户角色','USER_GET_ROLE_BY_USER',1,'2025-01-17 16:08:33','2025-01-17 16:10:20',0),(17,5,'批量删除用户','USER_DELETE_BATCH',1,'2025-01-17 16:08:42','2025-01-17 16:10:20',0),(18,0,'角色管理','ROLE_MANAGEMENT',0,'2025-01-17 16:10:40','2025-01-17 16:14:32',0),(19,18,'获取所有角色','ROLE_GET_ALL',1,'2025-01-17 16:12:18','2025-01-17 16:14:32',0),(20,18,'新增角色','ROLE_ADD',1,'2025-01-17 16:12:18','2025-01-17 16:14:32',0),(21,18,'更新角色','ROLE_UPDATE',1,'2025-01-17 16:12:18','2025-01-17 16:14:32',0),(22,18,'给角色新增权限','ROLE_ADD_PERMISSION_TO_ROLE',1,'2025-01-17 16:12:18','2025-01-17 16:14:32',0),(23,18,'查询角色权限','ROLE_GET_PERMISSION',1,'2025-01-17 16:12:18','2025-01-17 16:14:32',0),(24,18,'删除角色','ROLE_DELETE_ONE',1,'2025-01-17 16:12:18','2025-01-17 16:14:32',0),(25,18,'批量删除角色','ROLE_DELETE_BATCH',1,'2025-01-17 16:12:18','2025-01-17 16:14:32',0),(26,0,'权限管理','PERMISSION_MANAGEMENT',0,'2025-01-17 16:15:11','2025-01-17 16:16:45',0),(27,26,'查询所有权限','PERMISSION_GET_ALL',1,'2025-01-17 16:16:01','2025-01-17 16:16:45',0),(28,26,'新增权限','PERMISSION_ADD',1,'2025-01-17 16:16:01','2025-01-17 16:16:45',0),(29,26,'编辑权限','PERMISSION_UPDATE',1,'2025-01-17 16:16:01','2025-01-17 16:16:45',0),(30,26,'删除权限','PERMISSION_DELETE_ONE',1,'2025-01-17 16:16:01','2025-01-17 16:16:45',0),(31,26,'批量删除权限','PERMISSION_DELETE_BATCH',1,'2025-01-17 16:16:01','2025-01-17 16:16:45',0),(32,5,'查询用户权限','USER_GET_PERMISSION_BY_USER',1,'2025-01-17 17:45:16','2025-01-17 17:45:16',0),(33,5,'获取个人信息','USER_GET_MYSELF_INFO',1,'2025-02-08 09:01:02','2025-02-08 09:01:02',0);
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;
DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `roleName` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色名',
  `remark` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色含义',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'user','普通用户','2025-01-16 10:16:35','2025-01-16 10:17:25',0),(2,'admin','管理员','2025-01-16 10:16:35','2025-01-17 10:13:25',0),(3,'updateRole','新角色','2025-01-17 09:50:22','2025-01-17 10:26:50',0);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;
DROP TABLE IF EXISTS `role_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_permissions` (
  `rid` int DEFAULT NULL,
  `pid` int DEFAULT NULL,
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `role_permissions` WRITE;
/*!40000 ALTER TABLE `role_permissions` DISABLE KEYS */;
INSERT INTO `role_permissions` VALUES (1,1,'2025-01-17 14:34:01','2025-01-17 14:34:01',0),(1,2,'2025-01-17 14:34:13','2025-01-17 14:34:13',0),(1,3,'2025-01-17 14:35:55','2025-01-17 14:35:55',0),(1,4,'2025-01-17 17:49:26','2025-01-17 17:49:26',0),(1,8,'2025-01-17 17:52:13','2025-01-17 17:52:13',0),(1,10,'2025-01-17 17:52:13','2025-01-17 17:52:13',0),(1,16,'2025-01-17 17:52:13','2025-01-17 17:52:13',0),(1,32,'2025-01-17 17:52:13','2025-01-17 17:52:13',0),(1,5,'2025-01-17 17:53:02','2025-01-17 17:53:02',0),(1,18,'2025-01-17 17:53:02','2025-01-17 17:53:02',0),(1,23,'2025-01-17 17:53:02','2025-01-17 17:53:02',0),(2,1,'2025-01-17 17:53:32','2025-01-17 17:53:32',0),(2,2,'2025-01-17 17:53:32','2025-01-17 17:53:32',0),(2,3,'2025-01-17 17:53:32','2025-01-17 17:53:32',0),(2,4,'2025-01-17 17:53:32','2025-01-17 17:53:32',0),(2,5,'2025-01-17 17:53:32','2025-01-17 17:53:32',0),(2,6,'2025-01-17 17:53:32','2025-01-17 17:53:32',0),(2,7,'2025-01-17 17:53:32','2025-01-17 17:53:32',0),(2,8,'2025-01-17 17:53:32','2025-01-17 17:53:32',0),(2,9,'2025-01-17 17:53:32','2025-01-17 17:53:32',0),(2,10,'2025-01-17 17:53:32','2025-01-17 17:53:32',0),(2,11,'2025-01-17 17:53:32','2025-01-17 17:53:32',0),(2,12,'2025-01-17 17:53:32','2025-01-17 17:53:32',0),(2,13,'2025-01-17 17:53:32','2025-01-17 17:53:32',0),(2,14,'2025-01-17 17:53:32','2025-01-17 17:53:32',0),(2,15,'2025-01-17 17:53:32','2025-01-17 17:53:32',0),(2,16,'2025-01-17 17:54:12','2025-01-17 17:54:12',0),(2,17,'2025-01-17 17:54:12','2025-01-17 17:54:12',0),(2,18,'2025-01-17 17:54:12','2025-01-17 17:54:12',0),(2,19,'2025-01-17 17:54:12','2025-01-17 17:54:12',0),(2,20,'2025-01-17 17:54:12','2025-01-17 17:54:12',0),(2,21,'2025-01-17 17:54:12','2025-01-17 17:54:12',0),(2,22,'2025-01-17 17:54:12','2025-01-17 17:54:12',0),(2,23,'2025-01-17 17:54:12','2025-01-17 17:54:12',0),(2,24,'2025-01-17 17:54:12','2025-01-17 17:54:12',0),(2,25,'2025-01-17 17:54:12','2025-01-17 17:54:12',0),(2,26,'2025-01-17 17:54:12','2025-01-17 17:54:12',0),(2,27,'2025-01-17 17:54:12','2025-01-17 17:54:12',0),(2,28,'2025-01-17 17:54:12','2025-01-17 17:54:12',0),(2,29,'2025-01-17 17:54:12','2025-01-17 17:54:12',0),(2,30,'2025-01-17 17:54:12','2025-01-17 17:54:12',0),(2,31,'2025-01-17 17:54:21','2025-01-17 17:54:21',0),(2,32,'2025-01-17 17:54:21','2025-01-17 17:54:21',0),(1,33,'2025-02-08 09:01:28','2025-02-08 09:01:28',0),(2,33,'2025-02-08 09:01:37','2025-02-08 09:01:37',0),(3,33,'2025-02-08 09:01:45','2025-02-08 09:01:45',0);
/*!40000 ALTER TABLE `role_permissions` ENABLE KEYS */;
UNLOCK TABLES;
DROP TABLE IF EXISTS `role_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_user` (
  `uid` int DEFAULT NULL,
  `rid` int DEFAULT NULL,
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `role_user` WRITE;
/*!40000 ALTER TABLE `role_user` DISABLE KEYS */;
INSERT INTO `role_user` VALUES (1,1,'2025-01-16 10:32:20','2025-01-16 10:32:20',0),(2,1,'2025-01-17 13:56:22','2025-01-17 13:56:22',0),(3,1,'2025-01-17 13:56:22','2025-01-17 13:57:45',0),(1,2,'2025-01-17 14:54:07','2025-01-17 14:54:07',0);
/*!40000 ALTER TABLE `role_user` ENABLE KEYS */;
UNLOCK TABLES;
DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userId` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
  `address` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '地址',
  `phone` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '电话',
  `email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `age` int DEFAULT NULL COMMENT '年龄',
  `signature` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '签名',
  `sex` tinyint DEFAULT NULL COMMENT '性别（1男，0女）',
  `avatar` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `nickName` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '昵称',
  `enabled` int DEFAULT '1' COMMENT '账户是否可用',
  `accountNoExpired` int DEFAULT '1' COMMENT '账户是否过期',
  `credentialsNoExpired` int DEFAULT '1' COMMENT '密码是否过期',
  `accountNoLocked` int DEFAULT '1' COMMENT '是否被锁定',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint DEFAULT '0' COMMENT '逻辑删除',
  `description` varchar(1024) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'yannqing','$2a$10$nz2z/yhYtDMQc.x0RbDiGO5ye1u8jgdzsBWy54YEeGkmc.xthezVS','地址','电话',NULL,0,NULL,0,NULL,'yannqingNickName',1,1,1,1,'2025-01-10 15:58:59','2025-02-08 09:02:40',0,NULL),(2,'testtest1','$2a$10$g9DYqJ3RNy/Hfd67XRHlAuNLpU6ngUXXTgvpLFYLA/6hqZz4GlKtO',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'newNickName',1,1,1,1,'2025-01-15 09:48:38','2025-01-17 13:55:57',0,NULL),(3,'test2test2','$2a$10$x98PDytpFNmjN1wr/gq35um1X9FoRDOk2PnwpAPSwfC8h8foSn1Fy',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'test2昵称xxxx',1,1,1,1,'2025-01-16 16:51:06','2025-01-17 13:55:57',0,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

