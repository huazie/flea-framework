/*
Navicat MySQL Data Transfer

Source Server         : Flea
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : fleaauth

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2019-11-05 17:54:27
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of flea_login_log_201909
-- ----------------------------
INSERT INTO `flea_login_log_201909` VALUES ('1', '1000000', '2000', '127.0.0.1', null, null, '1', '2019-09-27 00:03:47', null, '2019-09-27 00:03:47', null, null, null, null);

-- ----------------------------
-- Table structure for `flea_menu`
-- ----------------------------
DROP TABLE IF EXISTS `flea_menu`;
CREATE TABLE `flea_menu` (
  `menu_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '菜单编号',
  `menu_code` varchar(50) NOT NULL COMMENT '菜单编码',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `menu_icon` varchar(30) NOT NULL COMMENT '菜单FontAwesome小图标',
  `menu_sort` tinyint(3) unsigned NOT NULL COMMENT '菜单展示顺序(同一个父菜单下)',
  `menu_view` varchar(255) DEFAULT NULL COMMENT '菜单对应页面（非叶子菜单的可以为空）',
  `menu_level` tinyint(2) NOT NULL COMMENT '菜单层级（1：一级菜单 2；二级菜单 3：三级菜单 4：四级菜单）',
  `menu_state` tinyint(2) NOT NULL COMMENT '菜单状态（0:下线，1: 在用 ）',
  `parent_id` int(12) NOT NULL COMMENT '父菜单编号',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `effective_date` datetime NOT NULL COMMENT '生效日期',
  `expiry_date` datetime NOT NULL COMMENT '失效日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '菜单描述',
  PRIMARY KEY (`menu_id`),
  UNIQUE KEY `check_menu_code` (`menu_code`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_menu_attr`
-- ----------------------------
DROP TABLE IF EXISTS `flea_menu_attr`;
CREATE TABLE `flea_menu_attr` (
  `attr_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '属性编号',
  `menu_id` int(12) NOT NULL COMMENT '菜单编号',
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
-- Records of flea_menu_attr
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_operation`
-- ----------------------------
DROP TABLE IF EXISTS `flea_operation`;
CREATE TABLE `flea_operation` (
  `operation_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '操作编号',
  `operation_code` varchar(50) NOT NULL COMMENT '操作编码',
  `operation_name` varchar(50) NOT NULL COMMENT '操作名称',
  `operation_desc` varchar(255) DEFAULT NULL COMMENT '操作描述',
  `operation_state` tinyint(2) NOT NULL COMMENT '操作状态',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`operation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_operation
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_privilege`
-- ----------------------------
DROP TABLE IF EXISTS `flea_privilege`;
CREATE TABLE `flea_privilege` (
  `privilege_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '权限编号',
  `privilege_name` varchar(50) NOT NULL COMMENT '权限名称',
  `privilege_desc` varchar(1024) DEFAULT NULL COMMENT '权限描述',
  `privilege_state` tinyint(2) NOT NULL COMMENT '权限状态',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`privilege_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_privilege
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_privilege_group`
-- ----------------------------
DROP TABLE IF EXISTS `flea_privilege_group`;
CREATE TABLE `flea_privilege_group` (
  `privilege_group_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '权限组编号',
  `privilege_group_name` varchar(50) NOT NULL COMMENT '权限组名称',
  `privilege_group_desc` varchar(1024) DEFAULT NULL COMMENT '权限组描述',
  `privilege_group_state` tinyint(2) NOT NULL COMMENT '权限组状态',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`privilege_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_privilege_group
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_privilege_rel`
-- ----------------------------
DROP TABLE IF EXISTS `flea_privilege_rel`;
CREATE TABLE `flea_privilege_rel` (
  `privilege_rel_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '权限关联编号',
  `privilege_id` int(12) NOT NULL COMMENT '权限编号',
  `relat_id` int(12) NOT NULL COMMENT '关联编号',
  `relat_type` varchar(50) NOT NULL COMMENT '关联类型',
  `relat_state` tinyint(2) NOT NULL COMMENT '关联状态',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  `relat_ext_a` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段A',
  `relat_ext_b` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段B',
  `relat_ext_c` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段C',
  `relat_ext_x` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段X',
  `relat_ext_y` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段Y',
  `relat_ext_z` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段Z',
  PRIMARY KEY (`privilege_rel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_privilege_rel
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_real_name_info`
-- ----------------------------
DROP TABLE IF EXISTS `flea_real_name_info`;
CREATE TABLE `flea_real_name_info` (
  `real_name_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '实名编号',
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
-- Records of flea_real_name_info
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_role`
-- ----------------------------
DROP TABLE IF EXISTS `flea_role`;
CREATE TABLE `flea_role` (
  `role_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  `role_desc` varchar(1024) DEFAULT NULL COMMENT '角色描述',
  `role_state` tinyint(2) NOT NULL COMMENT '角色状态（1: 正常  0: 删除）',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_role
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_role_group`
-- ----------------------------
DROP TABLE IF EXISTS `flea_role_group`;
CREATE TABLE `flea_role_group` (
  `role_group_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '角色组编号',
  `role_group_name` varchar(50) NOT NULL COMMENT '角色组名称',
  `role_group_desc` varchar(1024) DEFAULT NULL COMMENT '角色组描述',
  `role_group_state` tinyint(2) NOT NULL COMMENT '角色组状态',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`role_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_role_group
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_role_group_rel`
-- ----------------------------
DROP TABLE IF EXISTS `flea_role_group_rel`;
CREATE TABLE `flea_role_group_rel` (
  `role_group_rel_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '角色组关联编号',
  `role_group_id` int(12) NOT NULL COMMENT '角色组编号',
  `relat_id` int(12) NOT NULL COMMENT '关联编号',
  `relat_type` varchar(50) NOT NULL COMMENT '关联类型',
  `relat_state` tinyint(2) NOT NULL COMMENT '关联状态',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  `relat_ext_a` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段A',
  `relat_ext_b` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段B',
  `relat_ext_c` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段C',
  `relat_ext_x` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段X',
  `relat_ext_y` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段Y',
  `relat_ext_z` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段Z',
  PRIMARY KEY (`role_group_rel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_role_group_rel
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_role_rel`
-- ----------------------------
DROP TABLE IF EXISTS `flea_role_rel`;
CREATE TABLE `flea_role_rel` (
  `role_rel_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '角色关联编号',
  `role_id` int(12) NOT NULL COMMENT '角色编号',
  `relat_id` int(12) NOT NULL COMMENT '关联编号',
  `relat_type` varchar(50) NOT NULL COMMENT '关联类型',
  `relat_state` tinyint(2) NOT NULL COMMENT '关联状态',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  `relat_ext_a` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段A',
  `relat_ext_b` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段B',
  `relat_ext_c` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段C',
  `relat_ext_x` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段X',
  `relat_ext_y` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段Y',
  `relat_ext_z` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段Z',
  PRIMARY KEY (`role_rel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_role_rel
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
-- Table structure for `flea_user_group`
-- ----------------------------
DROP TABLE IF EXISTS `flea_user_group`;
CREATE TABLE `flea_user_group` (
  `user_group_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '用户组编号',
  `user_group_name` varchar(50) NOT NULL COMMENT '用户组名',
  `user_group_desc` varchar(1024) DEFAULT NULL COMMENT '用户组描述',
  `user_group_state` tinyint(2) NOT NULL COMMENT '用户组状态（1: 正常  0: 删除）',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`user_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_user_group
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_user_group_rel`
-- ----------------------------
DROP TABLE IF EXISTS `flea_user_group_rel`;
CREATE TABLE `flea_user_group_rel` (
  `user_group_relat_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '用户组关联编号',
  `user_group_id` int(12) NOT NULL COMMENT '用户组编号',
  `relat_id` int(12) NOT NULL COMMENT '关联编号',
  `relat_type` varchar(50) NOT NULL COMMENT '关联类型',
  `relat_state` tinyint(2) NOT NULL COMMENT '关联状态',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  `relat_ext_a` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段A',
  `relat_ext_b` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段B',
  `relat_ext_c` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段C',
  `relat_ext_x` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段X',
  `relat_ext_y` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段Y',
  `relat_ext_z` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段Z',
  PRIMARY KEY (`user_group_relat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_user_group_rel
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_user_role_rel`
-- ----------------------------
DROP TABLE IF EXISTS `flea_user_role_rel`;
CREATE TABLE `flea_user_role_rel` (
  `user_role_rel_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '用户角色关联编号',
  `user_id` int(12) NOT NULL COMMENT '用户编号',
  `role_id` int(12) NOT NULL COMMENT '角色编号',
  `user_role_rel_state` tinyint(2) NOT NULL COMMENT '用户角色关联关系状态（1: 正常  0: 删除）',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`user_role_rel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_user_role_rel
-- ----------------------------
