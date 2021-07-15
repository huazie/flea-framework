/*
Navicat MySQL Data Transfer

Source Server         : Flea
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : fleaauth

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2021-07-15 20:45:06
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
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_account
-- ----------------------------
INSERT INTO `flea_account` VALUES ('1000', '1000', 'SYS_FLEA_MGMT', '31DAA1C5BA8F0874A2383145686D90CCD238483F', '1', '2020-09-07 18:14:29', null, '2020-09-07 18:14:29', '2999-12-31 23:59:59', '【跳蚤管家】');
INSERT INTO `flea_account` VALUES ('10000', '10000', 'huazie', '31DAA1C5BA8F0874A2383145686D90CCD238483F', '1', '2020-09-07 18:15:27', null, '2020-09-07 18:15:27', '2999-12-31 23:59:59', '用户自己注册时新增数据');

-- ----------------------------
-- Table structure for `flea_account_attr`
-- ----------------------------
DROP TABLE IF EXISTS `flea_account_attr`;
CREATE TABLE `flea_account_attr` (
  `attr_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '属性编号',
  `account_id` int(11) NOT NULL COMMENT '账户编号',
  `attr_code` varchar(50) NOT NULL COMMENT '属性码',
  `attr_value` varchar(1024) DEFAULT NULL COMMENT '属性值',
  `attr_desc` varchar(1024) DEFAULT NULL COMMENT '属性描述',
  `state` tinyint(4) NOT NULL COMMENT '属性状态(0: 删除 1: 正常）',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `effective_date` datetime DEFAULT NULL COMMENT '生效日期',
  `expiry_date` datetime DEFAULT NULL COMMENT '失效日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`attr_id`),
  KEY `INDEX_ACCOUNT_ID` (`account_id`) USING BTREE,
  KEY `INDEX_ATTR_CODE` (`attr_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_account_attr
-- ----------------------------
INSERT INTO `flea_account_attr` VALUES ('1', '1000', 'ACCOUNT_TYPE', 'SYSTEM', '系统账户', '1', '2020-09-07 18:14:29', null, '2020-09-07 18:14:29', '2999-12-31 23:59:59', '【跳蚤管家】');
INSERT INTO `flea_account_attr` VALUES ('2', '10000', 'ACCOUNT_TYPE', 'OPERATOR', '操作账户', '1', '2020-09-07 18:15:27', null, '2020-09-07 18:15:27', '2999-12-31 23:59:59', '用户自己注册时新增数据');

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
  `attr_desc` varchar(1024) DEFAULT NULL COMMENT '属性描述',
  `state` tinyint(4) NOT NULL COMMENT '属性状态(0: 删除 1: 正常）',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `effective_date` datetime DEFAULT NULL COMMENT '生效日期',
  `expiry_date` datetime DEFAULT NULL COMMENT '失效日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`attr_id`),
  KEY `INDEX_FUNCTION_ID` (`function_id`) USING BTREE,
  KEY `INDEX_ATTR_CODE` (`attr_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_function_attr
-- ----------------------------
INSERT INTO `flea_function_attr` VALUES ('1', '1000', 'MENU', 'SYSTEM_IN_USE', '1000', '菜单归属系统【跳蚤管家】', '1', '2020-08-16 23:43:13', null, '2020-08-16 23:43:13', '2999-12-31 23:59:59', '【跳蚤管家】正在使用中');
INSERT INTO `flea_function_attr` VALUES ('2', '1001', 'MENU', 'SYSTEM_IN_USE', '1000', '菜单归属系统【跳蚤管家】', '1', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '【跳蚤管家】正在使用中');
INSERT INTO `flea_function_attr` VALUES ('3', '1002', 'MENU', 'SYSTEM_IN_USE', '1000', '菜单归属系统【跳蚤管家】', '1', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '【跳蚤管家】正在使用中');
INSERT INTO `flea_function_attr` VALUES ('4', '1003', 'MENU', 'SYSTEM_IN_USE', '1000', '菜单归属系统【跳蚤管家】', '1', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '【跳蚤管家】正在使用中');
INSERT INTO `flea_function_attr` VALUES ('5', '1004', 'MENU', 'SYSTEM_IN_USE', '1000', '菜单归属系统【跳蚤管家】', '1', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '【跳蚤管家】正在使用中');
INSERT INTO `flea_function_attr` VALUES ('6', '1005', 'MENU', 'SYSTEM_IN_USE', '1000', '菜单归属系统【跳蚤管家】', '1', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '【跳蚤管家】正在使用中');
INSERT INTO `flea_function_attr` VALUES ('7', '1006', 'MENU', 'SYSTEM_IN_USE', '1000', '菜单归属系统【跳蚤管家】', '1', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '【跳蚤管家】正在使用中');
INSERT INTO `flea_function_attr` VALUES ('8', '1007', 'MENU', 'SYSTEM_IN_USE', '1000', '菜单归属系统【跳蚤管家】', '1', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '【跳蚤管家】正在使用中');
INSERT INTO `flea_function_attr` VALUES ('9', '1008', 'MENU', 'SYSTEM_IN_USE', '1000', '菜单归属系统【跳蚤管家】', '1', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '【跳蚤管家】正在使用中');
INSERT INTO `flea_function_attr` VALUES ('10', '1009', 'MENU', 'SYSTEM_IN_USE', '1000', '菜单归属系统【跳蚤管家】', '1', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '【跳蚤管家】正在使用中');
INSERT INTO `flea_function_attr` VALUES ('11', '1010', 'MENU', 'SYSTEM_IN_USE', '1000', '菜单归属系统【跳蚤管家】', '1', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '【跳蚤管家】正在使用中');
INSERT INTO `flea_function_attr` VALUES ('12', '1011', 'MENU', 'SYSTEM_IN_USE', '1000', '菜单归属系统【跳蚤管家】', '1', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '【跳蚤管家】正在使用中');
INSERT INTO `flea_function_attr` VALUES ('13', '1012', 'MENU', 'SYSTEM_IN_USE', '1000', '菜单归属系统【跳蚤管家】', '1', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '【跳蚤管家】正在使用中');
INSERT INTO `flea_function_attr` VALUES ('14', '1013', 'MENU', 'SYSTEM_IN_USE', '1000', '菜单归属系统【跳蚤管家】', '1', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '【跳蚤管家】正在使用中');
INSERT INTO `flea_function_attr` VALUES ('15', '1014', 'MENU', 'SYSTEM_IN_USE', '1000', '菜单归属系统【跳蚤管家】', '1', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '【跳蚤管家】正在使用中');
INSERT INTO `flea_function_attr` VALUES ('16', '1015', 'MENU', 'SYSTEM_IN_USE', '1000', '菜单归属系统【跳蚤管家】', '1', '2020-10-08 17:31:13', null, '2020-10-08 17:31:13', '2999-12-31 23:59:59', '【跳蚤管家】正在使用中');

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
INSERT INTO `flea_id_generator` VALUES ('pk_flea_account', '10000');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_account_attr', '2');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_element', '999');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_function_attr', '16');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_login_log_202012', '4');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_menu', '1015');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_operation', '999');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_privilege', '1015');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_privilege_group', '1000');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_privilege_group_rel', '16');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_privilege_rel', '16');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_real_name_info', '0');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_role', '1000');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_role_group', '999');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_role_group_rel', '0');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_role_rel', '1');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_user', '10000');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_user_attr', '2');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_user_group', '999');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_user_group_rel', '0');
INSERT INTO `flea_id_generator` VALUES ('pk_flea_user_rel', '1');

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
-- Table structure for `flea_login_log_202012`
-- ----------------------------
DROP TABLE IF EXISTS `flea_login_log_202012`;
CREATE TABLE `flea_login_log_202012` (
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_login_log_202012
-- ----------------------------
INSERT INTO `flea_login_log_202012` VALUES ('1', '10000', '1000', '127.0.0.1', '', '', '1', '2020-12-10 22:39:38', null, '2020-12-10 22:39:38', null, '', null, null);
INSERT INTO `flea_login_log_202012` VALUES ('2', '10000', '1000', '127.0.0.1', '', '', '2', '2020-12-10 23:22:19', '2020-12-10 23:52:13', '2020-12-10 23:22:19', '2020-12-10 23:22:19', '用户已退出', null, null);
INSERT INTO `flea_login_log_202012` VALUES ('3', '10000', '1000', '127.0.0.1', '', '', '1', '2020-12-13 00:33:39', null, '2020-12-13 00:33:39', null, '', null, null);
INSERT INTO `flea_login_log_202012` VALUES ('4', '10000', '1000', '127.0.0.1', '', '', '1', '2020-12-17 23:49:28', null, '2020-12-17 23:49:28', null, '', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=1016 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_menu
-- ----------------------------
INSERT INTO `flea_menu` VALUES ('1000', 'console', '控制台', 'dashboard', '1', 'mgmt/console.html', '1', '1', '-1', '2020-08-16 23:40:10', null, '2020-08-16 23:40:10', '2999-12-31 23:59:59', '控制台，展示收藏夹，快捷菜单入口');
INSERT INTO `flea_menu` VALUES ('1001', 'auth_mgmt', '授权管理', 'font', '2', null, '1', '1', '-1', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '授权管理，包含用户模块管理，角色模块管理，权限模块管理，功能模块管理');
INSERT INTO `flea_menu` VALUES ('1002', 'user_module_mgmt', '用户模块管理', 'user-circle', '1', null, '2', '1', '1001', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '用户模块管理，包含用户管理，用户组管理');
INSERT INTO `flea_menu` VALUES ('1003', 'role_module_mgmt', '角色模块管理', 'user-secret', '2', null, '2', '1', '1001', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '角色模块管理，包含角色管理，角色组管理');
INSERT INTO `flea_menu` VALUES ('1004', 'privilege_module_mgmt', '权限模块管理', 'lock', '3', null, '2', '1', '1001', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '权限模块管理，包含权限管理，权限组管理');
INSERT INTO `flea_menu` VALUES ('1005', 'function_module_mgmt', '功能模块管理', 'gears', '4', null, '2', '1', '1001', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '功能模块管理，包含菜单管理，操作管理，元素管理');
INSERT INTO `flea_menu` VALUES ('1006', 'user_mgmt', '用户管理', 'user', '1', null, '3', '1', '1002', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '用户管理，包含用户注册，用户变更，用户授权');
INSERT INTO `flea_menu` VALUES ('1007', 'user_group_mgmt', '用户组管理', 'users', '2', null, '3', '1', '1002', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '用户组管理，包含用户组新增，用户组变更，用户组授权');
INSERT INTO `flea_menu` VALUES ('1008', 'role_mgmt', '角色管理', 'user', '1', null, '3', '1', '1003', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '角色管理，包含角色新增，角色变更，角色授权');
INSERT INTO `flea_menu` VALUES ('1009', 'role_group_mgmt', '角色组管理', 'users', '2', null, '3', '1', '1003', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '角色组管理，包含角色组新增，角色组变更，角色组关联');
INSERT INTO `flea_menu` VALUES ('1010', 'privilege_mgmt', '权限管理', 'tag', '1', null, '3', '1', '1004', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '权限管理，包含权限新增，权限变更');
INSERT INTO `flea_menu` VALUES ('1011', 'privilege_group_mgmt', '权限组管理', 'tags', '2', null, '3', '1', '1004', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '权限组管理，包含权限组新增，权限组变更，权限组关联');
INSERT INTO `flea_menu` VALUES ('1012', 'menu_mgmt', '菜单管理', 'list-alt', '1', null, '3', '1', '1005', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '菜单管理，包含菜单新增，菜单变更，菜单授权');
INSERT INTO `flea_menu` VALUES ('1013', 'operation_mgmt', '操作管理', 'wrench', '2', null, '3', '1', '1005', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '操作管理，包含操作新增，操作变更，操作授权');
INSERT INTO `flea_menu` VALUES ('1014', 'element_mgmt', '元素管理', 'code', '3', null, '3', '1', '1005', '2020-09-12 23:57:48', null, '2020-09-12 23:57:48', '2999-12-31 23:59:59', '元素管理，包含元素新增，元素变更，元素授权');
INSERT INTO `flea_menu` VALUES ('1015', 'menu_add', '菜单新增', 'plus-circle', '1', 'auth/function/menu/addMenu.html', '4', '1', '1012', '2020-10-08 17:31:13', null, '2020-10-08 17:31:13', '2999-12-31 23:59:59', '菜单新增，用于系统前台新增菜单使用');

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
) ENGINE=InnoDB AUTO_INCREMENT=1016 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_privilege
-- ----------------------------
INSERT INTO `flea_privilege` VALUES ('1000', '访问《控制台》菜单', '拥有可以访问《控制台》菜单的权限', '1000', '1', '2020-08-16 23:45:44', null, '【访问《控制台》菜单】权限对应【控制台】菜单，新增菜单时自动生成');
INSERT INTO `flea_privilege` VALUES ('1001', '访问《授权管理》菜单', '拥有可以访问《授权管理》菜单的权限', '1000', '1', '2020-09-12 23:57:48', '2020-09-12 23:57:48', '【访问《授权管理》菜单】权限对应【授权管理】菜单，新增菜单时自动生成');
INSERT INTO `flea_privilege` VALUES ('1002', '访问《用户模块管理》菜单', '拥有可以访问《用户模块管理》菜单的权限', '1000', '1', '2020-09-12 23:57:48', '2020-09-12 23:57:48', '【访问《用户模块管理》菜单】权限对应【用户模块管理】菜单，新增菜单时自动生成');
INSERT INTO `flea_privilege` VALUES ('1003', '访问《角色模块管理》菜单', '拥有可以访问《角色模块管理》菜单的权限', '1000', '1', '2020-09-12 23:57:48', '2020-09-12 23:57:48', '【访问《角色模块管理》菜单】权限对应【角色模块管理】菜单，新增菜单时自动生成');
INSERT INTO `flea_privilege` VALUES ('1004', '访问《权限模块管理》菜单', '拥有可以访问《权限模块管理》菜单的权限', '1000', '1', '2020-09-12 23:57:48', '2020-09-12 23:57:48', '【访问《权限模块管理》菜单】权限对应【权限模块管理】菜单，新增菜单时自动生成');
INSERT INTO `flea_privilege` VALUES ('1005', '访问《功能模块管理》菜单', '拥有可以访问《功能模块管理》菜单的权限', '1000', '1', '2020-09-12 23:57:48', '2020-09-12 23:57:48', '【访问《功能模块管理》菜单】权限对应【功能模块管理】菜单，新增菜单时自动生成');
INSERT INTO `flea_privilege` VALUES ('1006', '访问《用户管理》菜单', '拥有可以访问《用户管理》菜单的权限', '1000', '1', '2020-09-12 23:57:48', '2020-09-12 23:57:48', '【访问《用户管理》菜单】权限对应【用户管理】菜单，新增菜单时自动生成');
INSERT INTO `flea_privilege` VALUES ('1007', '访问《用户组管理》菜单', '拥有可以访问《用户组管理》菜单的权限', '1000', '1', '2020-09-12 23:57:48', '2020-09-12 23:57:48', '【访问《用户组管理》菜单】权限对应【用户组管理】菜单，新增菜单时自动生成');
INSERT INTO `flea_privilege` VALUES ('1008', '访问《角色管理》菜单', '拥有可以访问《角色管理》菜单的权限', '1000', '1', '2020-09-12 23:57:48', '2020-09-12 23:57:48', '【访问《角色管理》菜单】权限对应【角色管理】菜单，新增菜单时自动生成');
INSERT INTO `flea_privilege` VALUES ('1009', '访问《角色组管理》菜单', '拥有可以访问《角色组管理》菜单的权限', '1000', '1', '2020-09-12 23:57:48', '2020-09-12 23:57:48', '【访问《角色组管理》菜单】权限对应【角色组管理】菜单，新增菜单时自动生成');
INSERT INTO `flea_privilege` VALUES ('1010', '访问《权限管理》菜单', '拥有可以访问《权限管理》菜单的权限', '1000', '1', '2020-09-12 23:57:48', '2020-09-12 23:57:48', '【访问《权限管理》菜单】权限对应【权限管理】菜单，新增菜单时自动生成');
INSERT INTO `flea_privilege` VALUES ('1011', '访问《权限组管理》菜单', '拥有可以访问《权限组管理》菜单的权限', '1000', '1', '2020-09-12 23:57:48', '2020-09-12 23:57:48', '【访问《权限组管理》菜单】权限对应【权限组管理】菜单，新增菜单时自动生成');
INSERT INTO `flea_privilege` VALUES ('1012', '访问《菜单管理》菜单', '拥有可以访问《菜单管理》菜单的权限', '1000', '1', '2020-09-12 23:57:48', '2020-09-12 23:57:48', '【访问《菜单管理》菜单】权限对应【菜单管理】菜单，新增菜单时自动生成');
INSERT INTO `flea_privilege` VALUES ('1013', '访问《操作管理》菜单', '拥有可以访问《操作管理》菜单的权限', '1000', '1', '2020-09-12 23:57:48', '2020-09-12 23:57:48', '【访问《操作管理》菜单】权限对应【操作管理】菜单，新增菜单时自动生成');
INSERT INTO `flea_privilege` VALUES ('1014', '访问《元素管理》菜单', '拥有可以访问《元素管理》菜单的权限', '1000', '1', '2020-09-12 23:57:48', '2020-09-12 23:57:48', '【访问《元素管理》菜单】权限对应【元素管理】菜单，新增菜单时自动生成');
INSERT INTO `flea_privilege` VALUES ('1015', '访问《菜单新增》菜单', '拥有可以访问《菜单新增》菜单的权限', '1000', '1', '2020-10-08 17:31:14', '2020-10-08 17:31:14', '【访问《菜单新增》菜单】权限对应【菜单新增】菜单，新增菜单时自动生成');

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
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_privilege_group
-- ----------------------------
INSERT INTO `flea_privilege_group` VALUES ('1000', '菜单访问', '与访问菜单相关的权限归属的权限组', '1', '2020-08-16 22:59:32', null, '该权限组包含了访问菜单相关的权限');

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_privilege_group_rel
-- ----------------------------
INSERT INTO `flea_privilege_group_rel` VALUES ('1', '1000', '1000', 'PRIVILEGE_GROUP_REL_PRIVILEGE', '1', '2020-08-16 23:49:12', null, '【菜单访问】权限组关联【访问《控制台》菜单】权限', null, null, null, null, null, null);
INSERT INTO `flea_privilege_group_rel` VALUES ('2', '1000', '1001', 'PRIVILEGE_GROUP_REL_PRIVILEGE', '1', '2020-09-12 23:57:48', null, '【菜单访问】权限组关联【访问《授权管理》菜单】权限', null, null, null, null, null, null);
INSERT INTO `flea_privilege_group_rel` VALUES ('3', '1000', '1002', 'PRIVILEGE_GROUP_REL_PRIVILEGE', '1', '2020-09-12 23:57:48', null, '【菜单访问】权限组关联【访问《用户模块管理》菜单】权限', null, null, null, null, null, null);
INSERT INTO `flea_privilege_group_rel` VALUES ('4', '1000', '1003', 'PRIVILEGE_GROUP_REL_PRIVILEGE', '1', '2020-09-12 23:57:48', null, '【菜单访问】权限组关联【访问《角色模块管理》菜单】权限', null, null, null, null, null, null);
INSERT INTO `flea_privilege_group_rel` VALUES ('5', '1000', '1004', 'PRIVILEGE_GROUP_REL_PRIVILEGE', '1', '2020-09-12 23:57:48', null, '【菜单访问】权限组关联【访问《权限模块管理》菜单】权限', null, null, null, null, null, null);
INSERT INTO `flea_privilege_group_rel` VALUES ('6', '1000', '1005', 'PRIVILEGE_GROUP_REL_PRIVILEGE', '1', '2020-09-12 23:57:48', null, '【菜单访问】权限组关联【访问《功能模块管理》菜单】权限', null, null, null, null, null, null);
INSERT INTO `flea_privilege_group_rel` VALUES ('7', '1000', '1006', 'PRIVILEGE_GROUP_REL_PRIVILEGE', '1', '2020-09-12 23:57:48', null, '【菜单访问】权限组关联【访问《用户管理》菜单】权限', null, null, null, null, null, null);
INSERT INTO `flea_privilege_group_rel` VALUES ('8', '1000', '1007', 'PRIVILEGE_GROUP_REL_PRIVILEGE', '1', '2020-09-12 23:57:48', null, '【菜单访问】权限组关联【访问《用户组管理》菜单】权限', null, null, null, null, null, null);
INSERT INTO `flea_privilege_group_rel` VALUES ('9', '1000', '1008', 'PRIVILEGE_GROUP_REL_PRIVILEGE', '1', '2020-09-12 23:57:48', null, '【菜单访问】权限组关联【访问《角色管理》菜单】权限', null, null, null, null, null, null);
INSERT INTO `flea_privilege_group_rel` VALUES ('10', '1000', '1009', 'PRIVILEGE_GROUP_REL_PRIVILEGE', '1', '2020-09-12 23:57:48', null, '【菜单访问】权限组关联【访问《角色组管理》菜单】权限', null, null, null, null, null, null);
INSERT INTO `flea_privilege_group_rel` VALUES ('11', '1000', '1010', 'PRIVILEGE_GROUP_REL_PRIVILEGE', '1', '2020-09-12 23:57:48', null, '【菜单访问】权限组关联【访问《权限管理》菜单】权限', null, null, null, null, null, null);
INSERT INTO `flea_privilege_group_rel` VALUES ('12', '1000', '1011', 'PRIVILEGE_GROUP_REL_PRIVILEGE', '1', '2020-09-12 23:57:48', null, '【菜单访问】权限组关联【访问《权限组管理》菜单】权限', null, null, null, null, null, null);
INSERT INTO `flea_privilege_group_rel` VALUES ('13', '1000', '1012', 'PRIVILEGE_GROUP_REL_PRIVILEGE', '1', '2020-09-12 23:57:48', null, '【菜单访问】权限组关联【访问《菜单管理》菜单】权限', null, null, null, null, null, null);
INSERT INTO `flea_privilege_group_rel` VALUES ('14', '1000', '1013', 'PRIVILEGE_GROUP_REL_PRIVILEGE', '1', '2020-09-12 23:57:48', null, '【菜单访问】权限组关联【访问《操作管理》菜单】权限', null, null, null, null, null, null);
INSERT INTO `flea_privilege_group_rel` VALUES ('15', '1000', '1014', 'PRIVILEGE_GROUP_REL_PRIVILEGE', '1', '2020-09-12 23:57:48', null, '【菜单访问】权限组关联【访问《元素管理》菜单】权限', null, null, null, null, null, null);
INSERT INTO `flea_privilege_group_rel` VALUES ('16', '1000', '1015', 'PRIVILEGE_GROUP_REL_PRIVILEGE', '1', '2020-10-08 17:31:14', null, '【菜单访问】权限组关联【访问《菜单新增》菜单】权限', null, null, null, null, null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_privilege_rel
-- ----------------------------
INSERT INTO `flea_privilege_rel` VALUES ('1', '1000', '1000', 'PRIVILEGE_REL_MENU', '1', '2020-08-16 23:46:42', null, '【控制台】菜单绑定【访问《控制台》菜单】权限, 新增菜单时自动生成', null, null, null, null, null, null);
INSERT INTO `flea_privilege_rel` VALUES ('2', '1001', '1001', 'PRIVILEGE_REL_MENU', '1', '2020-09-12 23:57:48', null, '【授权管理】菜单绑定【访问《授权管理》菜单】权限, 新增菜单时自动生成', null, null, null, null, null, null);
INSERT INTO `flea_privilege_rel` VALUES ('3', '1002', '1002', 'PRIVILEGE_REL_MENU', '1', '2020-09-12 23:57:48', null, '【用户模块管理】菜单绑定【访问《用户模块管理》菜单】权限, 新增菜单时自动生成', null, null, null, null, null, null);
INSERT INTO `flea_privilege_rel` VALUES ('4', '1003', '1003', 'PRIVILEGE_REL_MENU', '1', '2020-09-12 23:57:48', null, '【角色模块管理】菜单绑定【访问《角色模块管理》菜单】权限, 新增菜单时自动生成', null, null, null, null, null, null);
INSERT INTO `flea_privilege_rel` VALUES ('5', '1004', '1004', 'PRIVILEGE_REL_MENU', '1', '2020-09-12 23:57:48', null, '【权限模块管理】菜单绑定【访问《权限模块管理》菜单】权限, 新增菜单时自动生成', null, null, null, null, null, null);
INSERT INTO `flea_privilege_rel` VALUES ('6', '1005', '1005', 'PRIVILEGE_REL_MENU', '1', '2020-09-12 23:57:48', null, '【功能模块管理】菜单绑定【访问《功能模块管理》菜单】权限, 新增菜单时自动生成', null, null, null, null, null, null);
INSERT INTO `flea_privilege_rel` VALUES ('7', '1006', '1006', 'PRIVILEGE_REL_MENU', '1', '2020-09-12 23:57:48', null, '【用户管理】菜单绑定【访问《用户管理》菜单】权限, 新增菜单时自动生成', null, null, null, null, null, null);
INSERT INTO `flea_privilege_rel` VALUES ('8', '1007', '1007', 'PRIVILEGE_REL_MENU', '1', '2020-09-12 23:57:48', null, '【用户组管理】菜单绑定【访问《用户组管理》菜单】权限, 新增菜单时自动生成', null, null, null, null, null, null);
INSERT INTO `flea_privilege_rel` VALUES ('9', '1008', '1008', 'PRIVILEGE_REL_MENU', '1', '2020-09-12 23:57:48', null, '【角色管理】菜单绑定【访问《角色管理》菜单】权限, 新增菜单时自动生成', null, null, null, null, null, null);
INSERT INTO `flea_privilege_rel` VALUES ('10', '1009', '1009', 'PRIVILEGE_REL_MENU', '1', '2020-09-12 23:57:48', null, '【角色组管理】菜单绑定【访问《角色组管理》菜单】权限, 新增菜单时自动生成', null, null, null, null, null, null);
INSERT INTO `flea_privilege_rel` VALUES ('11', '1010', '1010', 'PRIVILEGE_REL_MENU', '1', '2020-09-12 23:57:48', null, '【权限管理】菜单绑定【访问《权限管理》菜单】权限, 新增菜单时自动生成', null, null, null, null, null, null);
INSERT INTO `flea_privilege_rel` VALUES ('12', '1011', '1011', 'PRIVILEGE_REL_MENU', '1', '2020-09-12 23:57:48', null, '【权限组管理】菜单绑定【访问《权限组管理》菜单】权限, 新增菜单时自动生成', null, null, null, null, null, null);
INSERT INTO `flea_privilege_rel` VALUES ('13', '1012', '1012', 'PRIVILEGE_REL_MENU', '1', '2020-09-12 23:57:48', null, '【菜单管理】菜单绑定【访问《菜单管理》菜单】权限, 新增菜单时自动生成', null, null, null, null, null, null);
INSERT INTO `flea_privilege_rel` VALUES ('14', '1013', '1013', 'PRIVILEGE_REL_MENU', '1', '2020-09-12 23:57:48', null, '【操作管理】菜单绑定【访问《操作管理》菜单】权限, 新增菜单时自动生成', null, null, null, null, null, null);
INSERT INTO `flea_privilege_rel` VALUES ('15', '1014', '1014', 'PRIVILEGE_REL_MENU', '1', '2020-09-12 23:57:48', null, '【元素管理】菜单绑定【访问《元素管理》菜单】权限, 新增菜单时自动生成', null, null, null, null, null, null);
INSERT INTO `flea_privilege_rel` VALUES ('16', '1015', '1015', 'PRIVILEGE_REL_MENU', '1', '2020-10-08 17:31:14', null, '【菜单新增】菜单绑定【访问《菜单新增》菜单】权限, 新增菜单时自动生成', null, null, null, null, null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_role
-- ----------------------------
INSERT INTO `flea_role` VALUES ('1000', '超级管理员', '系统最高权限拥有者', '-1', '1', '2020-08-16 20:27:26', null, '超级管理员拥有系统最高权限');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_role_rel
-- ----------------------------
INSERT INTO `flea_role_rel` VALUES ('1', '1000', '1000', 'ROLE_REL_PRIVILEGE_GROUP', '1', '2020-08-16 23:01:29', null, '【超级管理员】角色绑定【菜单访问】权限组', null, null, null, null, null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_user
-- ----------------------------
INSERT INTO `flea_user` VALUES ('1000', '跳蚤管家', null, null, null, null, null, '-1', '1', '2020-09-07 18:14:29', null, '2020-09-07 18:14:29', '2999-12-31 23:59:59', '【跳蚤管家】');
INSERT INTO `flea_user` VALUES ('10000', 'huazie', null, null, null, null, null, '-1', '1', '2020-09-07 18:15:27', null, '2020-09-07 18:15:27', '2999-12-31 23:59:59', '用户自己注册时新增数据');

-- ----------------------------
-- Table structure for `flea_user_attr`
-- ----------------------------
DROP TABLE IF EXISTS `flea_user_attr`;
CREATE TABLE `flea_user_attr` (
  `attr_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '属性编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `attr_code` varchar(50) NOT NULL COMMENT '属性码',
  `attr_value` varchar(1024) DEFAULT NULL COMMENT '属性值',
  `attr_desc` varchar(1024) DEFAULT NULL COMMENT '属性描述',
  `state` tinyint(4) NOT NULL COMMENT '属性状态(0: 删除 1: 正常)',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `effective_date` datetime DEFAULT NULL COMMENT '生效日期',
  `expiry_date` datetime DEFAULT NULL COMMENT '失效日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`attr_id`),
  KEY `INDEX_USER_ID` (`user_id`) USING BTREE,
  KEY `INDEX_ATTR_CODE` (`attr_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_user_attr
-- ----------------------------
INSERT INTO `flea_user_attr` VALUES ('1', '1000', 'USER_TYPE', 'SYSTEM', '系统用户', '1', '2020-09-07 18:14:29', null, '2020-09-07 18:14:29', '2999-12-31 23:59:59', '【跳蚤管家】');
INSERT INTO `flea_user_attr` VALUES ('2', '10000', 'USER_TYPE', 'OPERATOR', '操作用户', '1', '2020-09-07 18:15:27', null, '2020-09-07 18:15:27', '2999-12-31 23:59:59', '用户自己注册时新增数据');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_user_rel
-- ----------------------------
INSERT INTO `flea_user_rel` VALUES ('1', '10000', '1000', 'USER_REL_ROLE', '1', '2020-08-16 20:37:00', null, '用户【huazie】绑定【超级管理员】角色', null, null, null, null, null, null);
