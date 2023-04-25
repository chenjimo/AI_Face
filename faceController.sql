/*
MySQL Backup
Source Server Version: 8.0.24
Source Database: demo
Date: 2023/4/24 00:56:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `x_face_log`
-- ----------------------------
DROP TABLE IF EXISTS `x_face_log`;
CREATE TABLE `x_face_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL DEFAULT '-1',
  `log_time` timestamp NOT NULL,
  `face_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
--  Table structure for `x_menu`
-- ----------------------------
DROP TABLE IF EXISTS `x_menu`;
CREATE TABLE `x_menu` (
  `menu_id` int NOT NULL AUTO_INCREMENT,
  `component` varchar(100) DEFAULT NULL,
  `path` varchar(100) DEFAULT NULL,
  `redirect` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `icon` varchar(100) DEFAULT NULL,
  `parent_id` int DEFAULT NULL,
  `is_leaf` varchar(1) DEFAULT NULL,
  `hidden` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
--  Table structure for `x_role`
-- ----------------------------
DROP TABLE IF EXISTS `x_role`;
CREATE TABLE `x_role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL,
  `role_desc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
--  Table structure for `x_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `x_role_menu`;
CREATE TABLE `x_role_menu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int DEFAULT NULL,
  `menu_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
--  Table structure for `x_user`
-- ----------------------------
DROP TABLE IF EXISTS `x_user`;
CREATE TABLE `x_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `avatar` varchar(200) DEFAULT NULL,
  `deleted` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
--  Table structure for `x_user_face`
-- ----------------------------
DROP TABLE IF EXISTS `x_user_face`;
CREATE TABLE `x_user_face` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `face_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
--  Table structure for `x_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `x_user_role`;
CREATE TABLE `x_user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `x_face_log` VALUES ('1','1','2023-04-22 10:05:05','/static/img/user/8380264480df4f71ad46d5a107e8f298.png'), ('2','1','2023-04-23 15:36:51','/static/img/log/a585e3d057b747edbce7fe8239d7b599.png'), ('3','1','2023-04-23 16:21:43','/static/img/log/9dddea0ff520429f902989e29579a23a.png'), ('4','1','2023-04-23 16:23:19','/static/img/log/fdaaf9c58dd54fff84d3e86150b7a841.png'), ('5','1','2023-04-23 16:33:01','/static/img/log/c16a350bf95c4e9ba4487b3f934cf396.png'), ('6','1','2023-04-23 16:40:48','/static/img/log/482d499ef59746af9c5442ba06f10dd6.png'), ('7','1','2023-04-23 16:46:21','/static/img/log/869278589ed54250b2d3d222db09fe2a.png'), ('8','1','2023-04-23 16:47:41','/static/img/log/021f22e0077642c5b2a5ea7b0191225a.png');
INSERT INTO `x_role_menu` VALUES ('11','1','1'), ('12','1','2'), ('13','1','3'), ('14','1','4'), ('15','1','5'), ('16','1','6'), ('17','1','7'), ('18','3','4'), ('19','3','5'), ('20','3','6'), ('21','3','7');
INSERT INTO `x_user` VALUES ('1','admin','$2a$10$3ZDHAfyo/P7tSOabB8X.DuVM1DN92YWgSd46ecVY.5WUAQNef7iUC','super@aliyun.com','18677778888','1','/static/img/user/bbed5c17e48440598ff746555cae7b46.png','0'), ('2','zhangsan','$2a$10$3ZDHAfyo/P7tSOabB8X.DuVM1DN92YWgSd46ecVY.5WUAQNef7iUC','zhangsan@gmail.com','13966667777','1','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif','0'), ('3','lisi','$2a$10$3ZDHAfyo/P7tSOabB8X.DuVM1DN92YWgSd46ecVY.5WUAQNef7iUC','lisi@gmail.com','13966667778','1','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif','0'), ('4','wangwu','$2a$10$3ZDHAfyo/P7tSOabB8X.DuVM1DN92YWgSd46ecVY.5WUAQNef7iUC','wangwu@gmail.com','13966667772','1','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif','0'), ('5','zhaoer','$2a$10$3ZDHAfyo/P7tSOabB8X.DuVM1DN92YWgSd46ecVY.5WUAQNef7iUC','zhaoer@gmail.com','13966667776','1','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif','0'), ('6','songliu','$2a$10$3ZDHAfyo/P7tSOabB8X.DuVM1DN92YWgSd46ecVY.5WUAQNef7iUC','songliu@gmail.com','13966667771','1','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif','0'), ('7','aaa','$2a$10$3ZDHAfyo/P7tSOabB8X.DuVM1DN92YWgSd46ecVY.5WUAQNef7iUC','aaa@aliyun.com','18899998888','1',NULL,'1'), ('8','bbb','666666','bbb@qq.com','188888889999','1',NULL,'1'), ('9','ccc','$2a$10$3ZDHAfyo/P7tSOabB8X.DuVM1DN92YWgSd46ecVY.5WUAQNef7iUC','1111@ali.com','11111111','1',NULL,'0'), ('10','xxx222','$2a$10$dQ1n957Q3qP2D0lHBSqdEeRHym9kAauXgxnIqAv9KDhnsJOC0Kooi','1122@qq.com','111111122','0',NULL,'1'), ('11','yyy','$2a$10$hX03AhH9Yt//V.crSdqGP.nhj3EQi.cMwIEglT9Eqd.5KDkIGaCQC','1111111@ali.com','1111111','1',NULL,'0'), ('12','xxx6','$2a$10$LTtlLYIPtDelUtP01FhcSe9JZkNuJmeXf66YMJyZee.04Q1yzNoj.','xxx6@aliyun.com','18677778886','1',NULL,'1'), ('13','test','$2a$10$jWewP9UNr.6AhrP2ofza1OaTDBq7nBL23C9zHnwBXh8Q/9Vd2zNSC','test@ali.com','11111111111','1',NULL,'0'), ('14','xxx','$2a$10$QpmItVrXHa5YWTfH25yCEuTu69VFjJbcxBxMYQTWb8qVNBSp0laxq','xxx@ali.com','1111111111111','1',NULL,'1');
INSERT INTO `x_user_face` VALUES ('2','1','470a39af7fa9c56e8a6a9022427abced');
INSERT INTO `x_user_role` VALUES ('1','1','1'), ('2','1','3'), ('6','3','2'), ('7','4','2'), ('8','4','3'), ('11','2','3');
