/*
Navicat MySQL Data Transfer

Source Server         : Flea
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : fleaauth

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2019-09-24 17:43:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `flea_account`
-- ----------------------------
DROP TABLE IF EXISTS `flea_account`;
CREATE TABLE `flea_account` (
  `account_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '帐户编号',
  `user_id` int(12) NOT NULL COMMENT '用户编号',
  `account_code` varchar(30) NOT NULL COMMENT '帐号',
  `account_pwd` varchar(16) NOT NULL COMMENT '密码',
  `account_state` tinyint(2) NOT NULL DEFAULT '1' COMMENT '帐户状态（0：删除，1：正常 ，2：禁用，3：待审核）',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `effective_date` datetime NOT NULL COMMENT '生效日期',
  `expiry_date` datetime NOT NULL COMMENT '失效日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`account_id`),
  KEY `index_name_psw` (`account_code`,`account_pwd`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of flea_account
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_account_attr`
-- ----------------------------
DROP TABLE IF EXISTS `flea_account_attr`;
CREATE TABLE `flea_account_attr` (
  `attr_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '属性编号',
  `account_id` int(12) NOT NULL COMMENT '帐户编号',
  `attr_code` varchar(50) NOT NULL COMMENT '属性码',
  `attr_value` varchar(1024) DEFAULT NULL COMMENT '属性值',
  `state` tinyint(1) NOT NULL COMMENT '属性状态(0：删除 1：在用）',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `effective_date` datetime DEFAULT NULL COMMENT '生效日期',
  `expiry_date` datetime DEFAULT NULL COMMENT '失效日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`attr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_account_attr
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_login_log`
-- ----------------------------
DROP TABLE IF EXISTS `flea_login_log`;
CREATE TABLE `flea_login_log` (
  `login_log_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '登录日志编号',
  `account_id` int(12) NOT NULL COMMENT '帐户编号',
  `system_account_id` int(12) NOT NULL COMMENT '系统帐户编号',
  `login_ip4` varchar(15) CHARACTER SET utf8 NOT NULL COMMENT 'ip4地址',
  `login_ip6` varchar(40) CHARACTER SET utf8 DEFAULT NULL COMMENT 'ip6地址',
  `login_area` varchar(15) CHARACTER SET utf8 DEFAULT NULL COMMENT '登录地区',
  `login_state` tinyint(2) NOT NULL COMMENT '登录状态（1：登录中，2：已退出）',
  `login_time` datetime NOT NULL COMMENT '登录时间',
  `logout_time` datetime DEFAULT NULL COMMENT '退出时间',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述信息',
  `ext1` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`login_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of flea_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_login_log_201909`
-- ----------------------------
DROP TABLE IF EXISTS `flea_login_log_201909`;
CREATE TABLE `flea_login_log_201909` (
  `login_log_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '登录日志编号',
  `account_id` int(12) NOT NULL COMMENT '帐户编号',
  `system_account_id` int(12) NOT NULL COMMENT '系统帐户编号',
  `login_ip4` varchar(15) CHARACTER SET utf8 NOT NULL COMMENT 'ip4地址',
  `login_ip6` varchar(40) CHARACTER SET utf8 DEFAULT NULL COMMENT 'ip6地址',
  `login_area` varchar(15) CHARACTER SET utf8 DEFAULT NULL COMMENT '登录地区',
  `login_state` tinyint(2) NOT NULL COMMENT '登录状态（1：登录中，2：已退出）',
  `login_time` datetime NOT NULL COMMENT '登录时间',
  `logout_time` datetime DEFAULT NULL COMMENT '退出时间',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述信息',
  `ext1` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`login_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of flea_login_log_201909
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_user`
-- ----------------------------
DROP TABLE IF EXISTS `flea_user`;
CREATE TABLE `flea_user` (
  `user_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `user_name` varchar(30) NOT NULL COMMENT '昵称',
  `user_sex` tinyint(2) DEFAULT '1' COMMENT '性别（1：男 2：女 3：其他）',
  `user_birthday` date DEFAULT NULL COMMENT '生日',
  `user_address` varchar(50) DEFAULT NULL COMMENT '住址',
  `user_email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `user_phone` varchar(11) DEFAULT NULL COMMENT '手机',
  `user_state` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态（1：正常，0：禁用）',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `effective_date` datetime NOT NULL COMMENT '生效日期',
  `expiry_date` datetime NOT NULL COMMENT '失效日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_user
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_user_attr`
-- ----------------------------
DROP TABLE IF EXISTS `flea_user_attr`;
CREATE TABLE `flea_user_attr` (
  `attr_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '属性编号',
  `user_id` int(12) NOT NULL COMMENT '用户编号',
  `attr_code` varchar(50) NOT NULL COMMENT '属性码',
  `attr_value` varchar(1024) DEFAULT NULL COMMENT '属性值',
  `state` tinyint(1) NOT NULL COMMENT '属性状态(0：删除 1：正常 ）',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `effective_date` datetime DEFAULT NULL COMMENT '生效日期',
  `expiry_date` datetime DEFAULT NULL COMMENT '失效日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`attr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_user_attr
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_user_real_name_info`
-- ----------------------------
DROP TABLE IF EXISTS `flea_user_real_name_info`;
CREATE TABLE `flea_user_real_name_info` (
  `real_name_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '实名编号',
  `user_id` int(12) NOT NULL COMMENT '用户编号',
  `cert_type` tinyint(2) NOT NULL DEFAULT '1' COMMENT '证件类型（1：身份证）',
  `cert_code` varchar(30) NOT NULL COMMENT '证件号码',
  `cert_name` varchar(20) NOT NULL COMMENT '证件名称',
  `cert_address` varchar(80) DEFAULT NULL COMMENT '证件地址',
  `real_name_state` tinyint(2) NOT NULL DEFAULT '1' COMMENT '实名信息状态（0：删除 1：在用）',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `effective_date` datetime NOT NULL COMMENT '生效日期',
  `expiry_date` datetime NOT NULL COMMENT '失效日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`real_name_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_user_real_name_info
-- ----------------------------
