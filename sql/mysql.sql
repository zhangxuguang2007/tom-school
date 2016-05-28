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
