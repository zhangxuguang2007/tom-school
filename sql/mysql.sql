/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : tom-school

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2016-05-25 09:02:23
*/

CREATE DATABASE tomschool;
USE tomschool;

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(32) NOT NULL,
  `user_name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cb0fsvip6qow952a07et2k9xv` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `buttons` varchar(50) DEFAULT NULL,
  `checked` int(11) DEFAULT NULL,
  `expanded` int(11) NOT NULL DEFAULT '0',
  `icon_cls` varchar(20) DEFAULT NULL,
  `leaf` int(11) NOT NULL DEFAULT '0',
  `menu_code` varchar(50) NOT NULL,
  `menu_config` varchar(200) DEFAULT NULL,
  `menu_name` varchar(50) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `sort_order` int(11) NOT NULL,
  `url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Records of authority
-- ----------------------------

INSERT INTO `authority` VALUES (1, NULL, NULL, 1, NULL, 0, 'SchoolManagement', NULL, '学校管理', NULL, 100, NULL);
INSERT INTO `authority` VALUES (2, NULL, NULL, 1, NULL, 0, 'SystemManagement', NULL, '系统管理', NULL, 200, NULL);
INSERT INTO `authority` VALUES (11, 'Add,Edit,Delete,View', NULL, 1, NULL, 1, 'Course', NULL, '课程管理', 1, 1001, NULL);
INSERT INTO `authority` VALUES (12, 'Add,Edit,Delete,View', NULL, 1, NULL, 1, 'Teacher', NULL, '教师管理', 1, 1002, NULL);
INSERT INTO `authority` VALUES (13, 'Add,Edit,Delete,View', NULL, 1, NULL, 1, 'Classes', NULL, '班级管理', 1, 1003, NULL);
INSERT INTO `authority` VALUES (14, 'Add,Edit,Delete,View', NULL, 1, NULL, 1, 'Student', NULL, '学生管理', 1, 1004, NULL);
INSERT INTO `authority` VALUES (21, 'Add,Edit,Delete,View', NULL, 1, NULL, 1, 'Role', NULL, '角色管理', 2, 2001, NULL);
INSERT INTO `authority` VALUES (22, 'Add,Edit,Delete,View', NULL, 1, NULL, 1, 'User', NULL, '用户管理', 2, 2002, NULL);
