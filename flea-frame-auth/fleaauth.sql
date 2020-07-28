/*
Navicat MySQL Data Transfer

Source Server         : Flea
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : fleaauth

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2020-07-28 15:18:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `flea_account`
-- ----------------------------
DROP TABLE IF EXISTS `flea_account`;
CREATE TABLE `flea_account` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '账户编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `account_code` varchar(30) NOT NULL COMMENT '账号',
  `account_pwd` varchar(500) NOT NULL COMMENT '密码',
  `account_state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '账户状态（0：删除，1：正常 ，2：禁用，3：待审核）',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `effective_date` datetime NOT NULL COMMENT '生效日期',
  `expiry_date` datetime NOT NULL COMMENT '失效日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`account_id`),
  KEY `INDEX_CODE_PWD` (`account_code`,`account_pwd`(255)) USING BTREE,
  KEY `INDEX_USER_ID` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_account
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_account_attr`
-- ----------------------------
DROP TABLE IF EXISTS `flea_account_attr`;
CREATE TABLE `flea_account_attr` (
  `attr_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '属性编号',
  `account_id` int(11) NOT NULL COMMENT '账户编号',
  `attr_code` varchar(50) NOT NULL COMMENT '属性码',
  `attr_value` varchar(1024) DEFAULT NULL COMMENT '属性值',
  `state` tinyint(4) NOT NULL COMMENT '属性状态(0: 删除 1: 正常）',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `effective_date` datetime DEFAULT NULL COMMENT '生效日期',
  `expiry_date` datetime DEFAULT NULL COMMENT '失效日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`attr_id`),
  KEY `INDEX_ACCOUNT_ID` (`account_id`) USING BTREE,
  KEY `INDEX_ATTR_CODE` (`attr_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_account_attr
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_element`
-- ----------------------------
DROP TABLE IF EXISTS `flea_element`;
CREATE TABLE `flea_element` (
  `element_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '元素编号',
  `element_code` varchar(50) NOT NULL COMMENT '元素编码',
  `element_name` varchar(50) NOT NULL COMMENT '元素名称',
  `element_desc` varchar(255) DEFAULT NULL COMMENT '元素描述',
  `element_type` tinyint(4) NOT NULL COMMENT '元素类型',
  `element_content` varchar(2000) DEFAULT NULL COMMENT '元素内容',
  `element_state` tinyint(4) NOT NULL COMMENT '元素状态(0: 删除 1: 正常)',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`element_id`),
  UNIQUE KEY `UNIQUE_ELEMENT_CODE` (`element_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_element
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_function_attr`
-- ----------------------------
DROP TABLE IF EXISTS `flea_function_attr`;
CREATE TABLE `flea_function_attr` (
  `attr_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '属性编号',
  `function_id` int(11) NOT NULL COMMENT '功能编号',
  `function_type` varchar(25) NOT NULL COMMENT '功能类型(菜单、操作、元素) ',
  `attr_code` varchar(50) NOT NULL COMMENT '属性码',
  `attr_value` varchar(1024) DEFAULT NULL COMMENT '属性值',
  `state` tinyint(4) NOT NULL COMMENT '属性状态(0: 删除 1: 正常）',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `effective_date` datetime DEFAULT NULL COMMENT '生效日期',
  `expiry_date` datetime DEFAULT NULL COMMENT '失效日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`attr_id`),
  KEY `INDEX_FUNCTION_ID` (`function_id`) USING BTREE,
  KEY `INDEX_ATTR_CODE` (`attr_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_function_attr
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_id_generator`
-- ----------------------------
DROP TABLE IF EXISTS `flea_id_generator`;
CREATE TABLE `flea_id_generator` (
  `id_generator_key` varchar(50) NOT NULL COMMENT 'ID产生器的键【即主键生成策略的键值名称】',
  `id_generator_value` bigint(20) NOT NULL COMMENT 'ID产生器的值【即主键生成的值】',
  UNIQUE KEY `UNIQUE_KEY` (`id_generator_key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_id_generator
-- ----------------------------
INSERT INTO `flea_id_generator` VALUES ('pk_flea_account', '0');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_account_attr', '0');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_element', '0');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_function_attr', '0');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_login_log_202007', '0');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_menu', '0');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_operation', '0');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_privilege', '0');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_privilege_group', '0');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_privilege_group_rel', '0');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_privilege_rel', '0');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_real_name_info', '0');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_role', '0');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_role_group', '0');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_role_group_rel', '0');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_role_rel', '0');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_user', '0');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_user_attr', '0');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_user_group', '0');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_user_group_rel', '0');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_user_rel', '0');

-- ----------------------------
-- Table structure for `flea_login_log`
-- ----------------------------
DROP TABLE IF EXISTS `flea_login_log`;
CREATE TABLE `flea_login_log` (
  `login_log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '登录日志编号',
  `account_id` int(11) NOT NULL COMMENT '账户编号',
  `system_account_id` int(11) NOT NULL COMMENT '系统账户编号',
  `login_ip4` varchar(15) NOT NULL COMMENT 'ip4地址',
  `login_ip6` varchar(40) DEFAULT NULL COMMENT 'ip6地址',
  `login_area` varchar(15) DEFAULT NULL COMMENT '登录地区',
  `login_state` tinyint(4) NOT NULL COMMENT '登录状态（1：登录中，2：已退出）',
  `login_time` datetime NOT NULL COMMENT '登录时间',
  `logout_time` datetime DEFAULT NULL COMMENT '退出时间',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '描述信息',
  `ext1` varchar(1024) DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(1024) DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`login_log_id`),
  KEY `INDEX_ACCOUNT_ID` (`account_id`) USING BTREE,
  KEY `INDEX_SYS_ACCOUNT_ID` (`system_account_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_login_log_202007`
-- ----------------------------
DROP TABLE IF EXISTS `flea_login_log_202007`;
CREATE TABLE `flea_login_log_202007` (
  `login_log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '登录日志编号',
  `account_id` int(11) NOT NULL COMMENT '账户编号',
  `system_account_id` int(11) NOT NULL COMMENT '系统账户编号',
  `login_ip4` varchar(15) NOT NULL COMMENT 'ip4地址',
  `login_ip6` varchar(40) DEFAULT NULL COMMENT 'ip6地址',
  `login_area` varchar(15) DEFAULT NULL COMMENT '登录地区',
  `login_state` tinyint(4) NOT NULL COMMENT '登录状态（1：登录中，2：已退出）',
  `login_time` datetime NOT NULL COMMENT '登录时间',
  `logout_time` datetime DEFAULT NULL COMMENT '退出时间',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '描述信息',
  `ext1` varchar(1024) DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(1024) DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`login_log_id`),
  KEY `INDEX_ACCOUNT_ID` (`account_id`) USING BTREE,
  KEY `INDEX_SYS_ACCOUNT_ID` (`system_account_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_login_log_202007
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_menu`
-- ----------------------------
DROP TABLE IF EXISTS `flea_menu`;
CREATE TABLE `flea_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单编号',
  `menu_code` varchar(50) NOT NULL COMMENT '菜单编码',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `menu_icon` varchar(30) NOT NULL COMMENT '菜单FontAwesome小图标',
  `menu_sort` tinyint(4) NOT NULL COMMENT '菜单展示顺序(同一个父菜单下)',
  `menu_view` varchar(255) DEFAULT NULL COMMENT '菜单对应页面（非叶子菜单的可以为空）',
  `menu_level` tinyint(4) NOT NULL COMMENT '菜单层级（1：一级菜单 2；二级菜单 3：三级菜单 4：四级菜单）',
  `menu_state` tinyint(4) NOT NULL COMMENT '菜单状态（0:下线，1: 在用 ）',
  `parent_id` int(11) NOT NULL COMMENT '父菜单编号',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `effective_date` datetime NOT NULL COMMENT '生效日期',
  `expiry_date` datetime NOT NULL COMMENT '失效日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '菜单描述',
  PRIMARY KEY (`menu_id`),
  UNIQUE KEY `UNIQUE_MENU_CODE` (`menu_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_operation`
-- ----------------------------
DROP TABLE IF EXISTS `flea_operation`;
CREATE TABLE `flea_operation` (
  `operation_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '操作编号',
  `operation_code` varchar(50) NOT NULL COMMENT '操作编码',
  `operation_name` varchar(50) NOT NULL COMMENT '操作名称',
  `operation_desc` varchar(255) DEFAULT NULL COMMENT '操作描述',
  `operation_state` tinyint(4) NOT NULL COMMENT '操作状态(0: 删除 1: 正常)',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`operation_id`),
  UNIQUE KEY `UNIQUE_OPERATION_CODE` (`operation_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_operation
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_privilege`
-- ----------------------------
DROP TABLE IF EXISTS `flea_privilege`;
CREATE TABLE `flea_privilege` (
  `privilege_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限编号',
  `privilege_name` varchar(50) NOT NULL COMMENT '权限名称',
  `privilege_desc` varchar(1024) DEFAULT NULL COMMENT '权限描述',
  `group_id` int(11) NOT NULL DEFAULT '-1' COMMENT '权限组编号',
  `privilege_state` tinyint(4) NOT NULL COMMENT '权限状态(0: 删除 1: 正常)',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`privilege_id`),
  KEY `INDEX_GROUP_ID` (`group_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_privilege
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_privilege_group`
-- ----------------------------
DROP TABLE IF EXISTS `flea_privilege_group`;
CREATE TABLE `flea_privilege_group` (
  `privilege_group_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限组编号',
  `privilege_group_name` varchar(50) NOT NULL COMMENT '权限组名称',
  `privilege_group_desc` varchar(1024) DEFAULT NULL COMMENT '权限组描述',
  `privilege_group_state` tinyint(4) NOT NULL COMMENT '权限组状态(0: 删除 1: 正常)',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`privilege_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_privilege_group
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_privilege_group_rel`
-- ----------------------------
DROP TABLE IF EXISTS `flea_privilege_group_rel`;
CREATE TABLE `flea_privilege_group_rel` (
  `privilege_group_rel_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限组关联编号',
  `privilege_group_id` int(11) NOT NULL COMMENT '权限组编号',
  `rel_id` int(11) NOT NULL COMMENT '关联编号',
  `rel_type` varchar(50) NOT NULL COMMENT '关联类型',
  `rel_state` tinyint(4) NOT NULL COMMENT '关联状态(0: 删除 1: 正常)',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  `rel_ext_a` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段A',
  `rel_ext_b` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段B',
  `rel_ext_c` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段C',
  `rel_ext_x` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段X',
  `rel_ext_y` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段Y',
  `rel_ext_z` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段Z',
  PRIMARY KEY (`privilege_group_rel_id`),
  KEY `INDEX_PRIVILEGE_GROUP_ID` (`privilege_group_id`) USING BTREE,
  KEY `INDEX_REL_ID` (`rel_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_privilege_group_rel
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_privilege_rel`
-- ----------------------------
DROP TABLE IF EXISTS `flea_privilege_rel`;
CREATE TABLE `flea_privilege_rel` (
  `privilege_rel_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限关联编号',
  `privilege_id` int(11) NOT NULL COMMENT '权限编号',
  `rel_id` int(11) NOT NULL COMMENT '关联编号',
  `rel_type` varchar(50) NOT NULL COMMENT '关联类型',
  `rel_state` tinyint(4) NOT NULL COMMENT '关联状态(0: 删除 1: 正常)',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  `rel_ext_a` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段A',
  `rel_ext_b` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段B',
  `rel_ext_c` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段C',
  `rel_ext_x` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段X',
  `rel_ext_y` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段Y',
  `rel_ext_z` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段Z',
  PRIMARY KEY (`privilege_rel_id`),
  KEY `INDEX_PRIVILEGE_ID` (`privilege_id`) USING BTREE,
  KEY `INDEX_REL_ID` (`rel_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_privilege_rel
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_real_name_info`
-- ----------------------------
DROP TABLE IF EXISTS `flea_real_name_info`;
CREATE TABLE `flea_real_name_info` (
  `real_name_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '实名编号',
  `cert_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '证件类型（1：身份证）',
  `cert_code` varchar(30) NOT NULL COMMENT '证件号码',
  `cert_name` varchar(20) NOT NULL COMMENT '证件名称',
  `cert_address` varchar(80) DEFAULT NULL COMMENT '证件地址',
  `real_name_state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '实名信息状态(0: 删除 1: 正常)',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `effective_date` datetime NOT NULL COMMENT '生效日期',
  `expiry_date` datetime NOT NULL COMMENT '失效日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`real_name_id`),
  KEY `INDEX_CERT_CODE` (`cert_code`) USING BTREE,
  KEY `INDEX_CERT_NAME` (`cert_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_real_name_info
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_role`
-- ----------------------------
DROP TABLE IF EXISTS `flea_role`;
CREATE TABLE `flea_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  `role_desc` varchar(1024) DEFAULT NULL COMMENT '角色描述',
  `group_id` int(11) NOT NULL DEFAULT '-1' COMMENT '角色组编号',
  `role_state` tinyint(4) NOT NULL COMMENT '角色状态(0: 删除 1: 正常)',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`role_id`),
  KEY `INDEX_GROUP_ID` (`group_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_role
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_role_group`
-- ----------------------------
DROP TABLE IF EXISTS `flea_role_group`;
CREATE TABLE `flea_role_group` (
  `role_group_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色组编号',
  `role_group_name` varchar(50) NOT NULL COMMENT '角色组名称',
  `role_group_desc` varchar(1024) DEFAULT NULL COMMENT '角色组描述',
  `role_group_state` tinyint(4) NOT NULL COMMENT '角色组状态(0: 删除 1: 正常)',
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
  `role_group_rel_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色组关联编号',
  `role_group_id` int(11) NOT NULL COMMENT '角色组编号',
  `rel_id` int(11) NOT NULL COMMENT '关联编号',
  `rel_type` varchar(50) NOT NULL COMMENT '关联类型',
  `rel_state` tinyint(4) NOT NULL COMMENT '关联状态(0: 删除 1: 正常)',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  `rel_ext_a` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段A',
  `rel_ext_b` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段B',
  `rel_ext_c` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段C',
  `rel_ext_x` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段X',
  `rel_ext_y` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段Y',
  `rel_ext_z` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段Z',
  PRIMARY KEY (`role_group_rel_id`),
  KEY `INDEX_ROLE_GROUP_ID` (`role_group_id`) USING BTREE,
  KEY `INDEX_REL_ID` (`rel_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_role_group_rel
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_role_rel`
-- ----------------------------
DROP TABLE IF EXISTS `flea_role_rel`;
CREATE TABLE `flea_role_rel` (
  `role_rel_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色关联编号',
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  `rel_id` int(11) NOT NULL COMMENT '关联编号',
  `rel_type` varchar(50) NOT NULL COMMENT '关联类型',
  `rel_state` tinyint(4) NOT NULL COMMENT '关联状态(0: 删除 1: 正常)',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  `rel_ext_a` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段A',
  `rel_ext_b` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段B',
  `rel_ext_c` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段C',
  `rel_ext_x` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段X',
  `rel_ext_y` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段Y',
  `rel_ext_z` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段Z',
  PRIMARY KEY (`role_rel_id`),
  KEY `INDEX_ROLE_ID` (`role_id`) USING BTREE,
  KEY `INDEX_REL_ID` (`rel_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_role_rel
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_user`
-- ----------------------------
DROP TABLE IF EXISTS `flea_user`;
CREATE TABLE `flea_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `user_name` varchar(30) NOT NULL COMMENT '昵称',
  `user_sex` tinyint(4) DEFAULT '1' COMMENT '性别（1：男 2：女 3：其他）',
  `user_birthday` date DEFAULT NULL COMMENT '生日',
  `user_address` varchar(50) DEFAULT NULL COMMENT '住址',
  `user_email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `user_phone` varchar(11) DEFAULT NULL COMMENT '手机',
  `group_id` int(11) NOT NULL DEFAULT '-1' COMMENT '用户组编号',
  `user_state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '用户状态（0：删除，1：正常 ，2：禁用，3：待审核）',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `effective_date` datetime NOT NULL COMMENT '生效日期',
  `expiry_date` datetime NOT NULL COMMENT '失效日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`user_id`),
  KEY `INDEX_USER_NAME` (`user_name`) USING BTREE,
  KEY `INDEX_USER_BIRTHDAY` (`user_birthday`) USING BTREE,
  KEY `INDEX_USER_EMAIL` (`user_email`) USING BTREE,
  KEY `INDEX_USER_PHONE` (`user_phone`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_user
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_user_attr`
-- ----------------------------
DROP TABLE IF EXISTS `flea_user_attr`;
CREATE TABLE `flea_user_attr` (
  `attr_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '属性编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `attr_code` varchar(50) NOT NULL COMMENT '属性码',
  `attr_value` varchar(1024) DEFAULT NULL COMMENT '属性值',
  `state` tinyint(4) NOT NULL COMMENT '属性状态(0: 删除 1: 正常)',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `effective_date` datetime DEFAULT NULL COMMENT '生效日期',
  `expiry_date` datetime DEFAULT NULL COMMENT '失效日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`attr_id`),
  KEY `INDEX_USER_ID` (`user_id`) USING BTREE,
  KEY `INDEX_ATTR_CODE` (`attr_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_user_attr
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_user_group`
-- ----------------------------
DROP TABLE IF EXISTS `flea_user_group`;
CREATE TABLE `flea_user_group` (
  `user_group_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户组编号',
  `user_group_name` varchar(50) NOT NULL COMMENT '用户组名',
  `user_group_desc` varchar(1024) DEFAULT NULL COMMENT '用户组描述',
  `user_group_state` tinyint(4) NOT NULL COMMENT '用户组状态(0: 删除 1: 正常)',
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
  `user_group_rel_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户组关联编号',
  `user_group_id` int(11) NOT NULL COMMENT '用户组编号',
  `rel_id` int(11) NOT NULL COMMENT '关联编号',
  `rel_type` varchar(50) NOT NULL COMMENT '关联类型',
  `rel_state` tinyint(4) NOT NULL COMMENT '关联状态(0: 删除 1: 正常)',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  `rel_ext_a` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段A',
  `rel_ext_b` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段B',
  `rel_ext_c` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段C',
  `rel_ext_x` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段X',
  `rel_ext_y` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段Y',
  `rel_ext_z` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段Z',
  PRIMARY KEY (`user_group_rel_id`),
  KEY `INDEX_USER_GROUP_ID` (`user_group_id`) USING BTREE,
  KEY `INDEX_REL_ID` (`rel_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_user_group_rel
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_user_rel`
-- ----------------------------
DROP TABLE IF EXISTS `flea_user_rel`;
CREATE TABLE `flea_user_rel` (
  `user_rel_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户关联编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `rel_id` int(11) NOT NULL COMMENT '关联编号',
  `rel_type` varchar(50) NOT NULL COMMENT '关联类型',
  `rel_state` tinyint(4) NOT NULL COMMENT '关联状态(0: 删除 1: 正常)',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  `rel_ext_a` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段A',
  `rel_ext_b` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段B',
  `rel_ext_c` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段C',
  `rel_ext_x` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段X',
  `rel_ext_y` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段Y',
  `rel_ext_z` varchar(1024) DEFAULT NULL COMMENT '关联扩展字段Z',
  PRIMARY KEY (`user_rel_id`),
  KEY `INDEX_USER_ID` (`user_id`) USING BTREE,
  KEY `INDEX_REL_ID` (`rel_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_user_rel
-- ----------------------------
