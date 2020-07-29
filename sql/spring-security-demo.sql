/*
Navicat MySQL Data Transfer

Source Server         : D
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : spring-security-demo

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2020-07-29 16:48:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `cratetime` datetime DEFAULT CURRENT_TIMESTAMP,
  `is_delete` int(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('10', '1', '修改', '2020-07-23 14:03:55', '1');
INSERT INTO `sys_log` VALUES ('11', '1', '修改', '2020-07-23 14:05:03', '1');
INSERT INTO `sys_log` VALUES ('12', '1', '修改', '2020-07-29 15:12:22', '1');

-- ----------------------------
-- Table structure for sys_log_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_detail`;
CREATE TABLE `sys_log_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `log_id` int(11) DEFAULT NULL,
  `table_name` varchar(255) DEFAULT NULL,
  `operation_action` varchar(255) DEFAULT NULL,
  `column_name` varchar(255) DEFAULT NULL,
  `old_column_content` varchar(255) DEFAULT NULL,
  `new_column_content` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT CURRENT_TIMESTAMP,
  `is_delete` int(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_log_detail
-- ----------------------------
INSERT INTO `sys_log_detail` VALUES ('7', '10', null, null, '密码', '$2a$10$qaN3cKLOxFmWMx/1jFt6gOtV6DGw6MHz32tySBdoTuEZeTDvrCu0.', '$2a$10$ikoKOlJxyWpWSQ5RsPlPeOQ2hHJBclZfk0UVPY0qY5Tc9jO5/vTZq', '2020-07-23 14:03:55', '1');
INSERT INTO `sys_log_detail` VALUES ('8', '11', null, '修改', '密码', '$2a$10$ikoKOlJxyWpWSQ5RsPlPeOQ2hHJBclZfk0UVPY0qY5Tc9jO5/vTZq', '$2a$10$bP7R54NPP6lzTBMHRurEDOsVZOyYuxCbWIgayfdn494F.A3CzxJx.', '2020-07-23 14:05:03', '1');
INSERT INTO `sys_log_detail` VALUES ('9', '12', null, '修改', '密码', '$2a$10$bP7R54NPP6lzTBMHRurEDOsVZOyYuxCbWIgayfdn494F.A3CzxJx.', '$2a$10$SVZCyZg5I23j/Bq7DUNnXOl3kMDsJ9/MWfBTEMn5Ya0rAYGbZOldq', '2020-07-29 15:12:22', '1');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `css` varchar(30) DEFAULT NULL,
  `href` varchar(1000) DEFAULT NULL,
  `type` tinyint(1) NOT NULL,
  `permission` varchar(50) DEFAULT NULL,
  `sort` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '0', '用户管理', 'fa-users', '', '1', 'sys:user:manager', '1');
INSERT INTO `sys_permission` VALUES ('2', '1', '用户查询', 'fa-user', 'pages/user/userList.html', '1', 'sys:user:query', '2');
INSERT INTO `sys_permission` VALUES ('3', '2', '查询', '', '', '2', 'sys:user:query', '100');
INSERT INTO `sys_permission` VALUES ('4', '2', '新增', '', '', '2', 'sys:user:add', '100');
INSERT INTO `sys_permission` VALUES ('6', '0', '修改密码', 'fa-pencil-square-o', 'pages/user/changePassword.html', '1', 'sys:user:password', '4');
INSERT INTO `sys_permission` VALUES ('7', '0', '系统设置', 'fa-gears', '', '1', '', '5');
INSERT INTO `sys_permission` VALUES ('8', '7', '菜单', 'fa-cog', 'pages/menu/menuList.html', '1', '', '6');
INSERT INTO `sys_permission` VALUES ('9', '8', '查询', '', '', '2', 'sys:menu:query', '100');
INSERT INTO `sys_permission` VALUES ('10', '8', '新增', '', '', '2', 'sys:menu:add', '100');
INSERT INTO `sys_permission` VALUES ('11', '8', '删除', '', '', '2', 'sys:menu:del', '100');
INSERT INTO `sys_permission` VALUES ('12', '7', '角色', 'fa-user-secret', 'pages/role/roleList.html', '1', '', '7');
INSERT INTO `sys_permission` VALUES ('13', '12', '查询', '', '', '2', 'sys:role:query', '100');
INSERT INTO `sys_permission` VALUES ('14', '12', '新增', '', '', '2', 'sys:role:add', '100');
INSERT INTO `sys_permission` VALUES ('15', '12', '删除', '', '', '2', 'sys:role:del', '100');
INSERT INTO `sys_permission` VALUES ('16', '0', '文件管理', 'fa-folder-open', 'pages/file/fileList.html', '1', '', '8');
INSERT INTO `sys_permission` VALUES ('17', '16', '查询', '', '', '2', 'sys:file:query', '100');
INSERT INTO `sys_permission` VALUES ('18', '16', '删除', '', '', '2', 'sys:file:del', '100');
INSERT INTO `sys_permission` VALUES ('19', '0', '数据源监控', 'fa-eye', 'druid/index.html', '1', '', '9');
INSERT INTO `sys_permission` VALUES ('20', '0', '接口swagger', 'fa-file-pdf-o', 'swagger-ui.html', '1', '', '10');
INSERT INTO `sys_permission` VALUES ('21', '0', '代码生成', 'fa-wrench', 'pages/generate/edit.html', '1', 'generate:edit', '11');
INSERT INTO `sys_permission` VALUES ('22', '0', '公告管理', 'fa-book', 'pages/notice/noticeList.html', '1', '', '12');
INSERT INTO `sys_permission` VALUES ('23', '22', '查询', '', '', '2', 'notice:query', '100');
INSERT INTO `sys_permission` VALUES ('24', '22', '添加', '', '', '2', 'notice:add', '100');
INSERT INTO `sys_permission` VALUES ('25', '22', '删除', '', '', '2', 'notice:del', '100');
INSERT INTO `sys_permission` VALUES ('26', '0', '日志查询', 'fa-reorder', 'pages/log/logList.html', '1', 'sys:log:query', '13');
INSERT INTO `sys_permission` VALUES ('27', '0', '邮件管理', 'fa-envelope', 'pages/mail/mailList.html', '1', '', '14');
INSERT INTO `sys_permission` VALUES ('28', '27', '发送邮件', '', '', '2', 'mail:send', '100');
INSERT INTO `sys_permission` VALUES ('29', '27', '查询', '', '', '2', 'mail:all:query', '100');
INSERT INTO `sys_permission` VALUES ('30', '0', '定时任务管理', 'fa-tasks', 'pages/job/jobList.html', '1', '', '15');
INSERT INTO `sys_permission` VALUES ('31', '30', '查询', '', '', '2', 'job:query', '100');
INSERT INTO `sys_permission` VALUES ('32', '30', '新增', '', '', '2', 'job:add', '100');
INSERT INTO `sys_permission` VALUES ('33', '30', '删除', '', '', '2', 'job:del', '100');
INSERT INTO `sys_permission` VALUES ('34', '0', 'excel导出', 'fa-arrow-circle-down', 'pages/excel/sql.html', '1', '', '16');
INSERT INTO `sys_permission` VALUES ('35', '34', '导出', '', '', '2', 'excel:down', '100');
INSERT INTO `sys_permission` VALUES ('36', '34', '页面显示数据', '', '', '2', 'excel:show:datas', '100');
INSERT INTO `sys_permission` VALUES ('37', '0', '字典管理', 'fa-reddit', 'pages/dict/dictList.html', '1', '', '17');
INSERT INTO `sys_permission` VALUES ('38', '37', '查询', '', '', '2', 'dict:query', '100');
INSERT INTO `sys_permission` VALUES ('39', '37', '新增', '', '', '2', 'dict:add', '100');
INSERT INTO `sys_permission` VALUES ('40', '37', '删除', '', '', '2', 'dict:del', '100');
INSERT INTO `sys_permission` VALUES ('41', '0', '修改', null, null, '1', 'sys:user:modify', '122');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ADMIN', '管理员', '2017-05-01 13:25:39', '2017-10-05 21:59:18');
INSERT INTO `sys_role` VALUES ('2', 'USER', '普通用户', '2017-08-01 21:47:31', '2017-10-05 21:59:26');
INSERT INTO `sys_role` VALUES ('3', 'SALE', '銷售', '2017-08-01 21:47:31', '2017-10-05 21:59:26');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `roleId` int(11) NOT NULL,
  `permissionId` int(11) NOT NULL,
  PRIMARY KEY (`roleId`,`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` VALUES ('1', '2');
INSERT INTO `sys_role_permission` VALUES ('1', '3');
INSERT INTO `sys_role_permission` VALUES ('1', '4');
INSERT INTO `sys_role_permission` VALUES ('1', '41');
INSERT INTO `sys_role_permission` VALUES ('2', '1');
INSERT INTO `sys_role_permission` VALUES ('2', '2');
INSERT INTO `sys_role_permission` VALUES ('2', '3');
INSERT INTO `sys_role_permission` VALUES ('2', '4');
INSERT INTO `sys_role_permission` VALUES ('2', '6');
INSERT INTO `sys_role_permission` VALUES ('2', '7');
INSERT INTO `sys_role_permission` VALUES ('2', '8');
INSERT INTO `sys_role_permission` VALUES ('2', '9');
INSERT INTO `sys_role_permission` VALUES ('3', '18');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('1', '1');
INSERT INTO `sys_role_user` VALUES ('2', '2');
INSERT INTO `sys_role_user` VALUES ('5', '3');

-- ----------------------------
-- Table structure for sys_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_token`;
CREATE TABLE `sys_token` (
  `id` varchar(36) NOT NULL COMMENT 'token',
  `val` text NOT NULL COMMENT 'LoginUser的json串',
  `expireTime` datetime NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_token
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(60) NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `headImgUrl` varchar(255) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `telephone` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'zs', '$2a$10$SVZCyZg5I23j/Bq7DUNnXOl3kMDsJ9/MWfBTEMn5Ya0rAYGbZOldq', '小张', null, '12312312311', '13125037532', '', '2020-06-02', null, '1', '2020-06-24 12:01:00', '2020-07-29 15:12:22');
INSERT INTO `sys_user` VALUES ('4', 'ls', '$2a$10$TUccIf/C3EvhZ6CEqXIvYezZjDuORxFAX9iMLJr6XeLXGx9wuUKI.', '小李', null, '12312312312', '13125037532', null, '2020-06-24', null, '1', '2020-06-24 14:55:24', '2020-06-24 14:55:27');
INSERT INTO `sys_user` VALUES ('5', 'ww', '$2a$10$TUccIf/C3EvhZ6CEqXIvYezZjDuORxFAX9iMLJr6XeLXGx9wuUKI.', '王五', null, '12312312313', '13125037532', null, '2020-06-24', null, '1', '2020-06-24 14:55:24', '2020-06-24 14:55:27');

-- ----------------------------
-- Table structure for tests
-- ----------------------------
DROP TABLE IF EXISTS `tests`;
CREATE TABLE `tests` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `phone` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tests
-- ----------------------------

-- ----------------------------
-- Procedure structure for add_column
-- ----------------------------
DROP PROCEDURE IF EXISTS `add_column`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_column`()
BEGIN

	IF NOT EXISTS (SELECT 1 
                FROM INFORMATION_SCHEMA.COLUMNS 
                WHERE TABLE_NAME = 'test' 
                AND COLUMN_NAME = 'phone')
         THEN 
 ALTER TABLE tests ADD COLUMN phone int(1);
	
         END IF;#Routine body goes here...

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for drop_column
-- ----------------------------
DROP PROCEDURE IF EXISTS `drop_column`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `drop_column`()
BEGIN

	IF EXISTS (SELECT 1 
                FROM INFORMATION_SCHEMA.COLUMNS 
                WHERE TABLE_NAME = 'tests' 
                AND COLUMN_NAME = 'phone')
         THEN 
 ALTER TABLE tests drop COLUMN phone;
	
         END IF;#Routine body goes here...

END
;;
DELIMITER ;
