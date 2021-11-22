/*
Navicat MySQL Data Transfer

Source Server         : Flea
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : fleaconfig

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2020-10-13 23:12:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `flea_jersey_i18n_error_mapping`
-- ----------------------------
DROP TABLE IF EXISTS `flea_jersey_i18n_error_mapping`;
CREATE TABLE `flea_jersey_i18n_error_mapping` (
  `mapping_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '国际码和错误码映射编号',
  `resource_code` varchar(50) NOT NULL COMMENT '资源编码',
  `service_code` varchar(50) NOT NULL COMMENT '服务编码',
  `i18n_code` varchar(50) NOT NULL COMMENT '国际码',
  `error_code` varchar(50) NOT NULL COMMENT '错误码',
  `return_mess` varchar(2000) DEFAULT NULL COMMENT '响应公共报文返回信息',
  `state` tinyint(1) NOT NULL COMMENT '状态(0：删除 1：正常 ）',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改时间',
  `remarks` varchar(2000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`mapping_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_jersey_i18n_error_mapping
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_jersey_resource`
-- ----------------------------
DROP TABLE IF EXISTS `flea_jersey_resource`;
CREATE TABLE `flea_jersey_resource` (
  `resource_code` varchar(30) NOT NULL COMMENT '资源编码',
  `resource_name` varchar(300) NOT NULL COMMENT '资源名称',
  `resource_packages` varchar(2000) NOT NULL COMMENT '资源包名(如果存在多个，以逗号分隔)',
  `state` tinyint(1) NOT NULL COMMENT '状态(0：删除 1：正常 ）',
  `create_date` date DEFAULT NULL COMMENT '创建日期',
  `done_date` date DEFAULT NULL COMMENT '修改时间',
  `remarks` varchar(2000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`resource_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_jersey_resource
-- ----------------------------
INSERT INTO `flea_jersey_resource` VALUES ('download', '下载资源', 'com.huazie.ffs.module.download.web', '1', '2019-12-04', null, null);
INSERT INTO `flea_jersey_resource` VALUES ('upload', '上传资源', 'com.huazie.ffs.module.upload.web', '1', '2019-12-04', null, null);

-- ----------------------------
-- Table structure for `flea_jersey_res_client`
-- ----------------------------
DROP TABLE IF EXISTS `flea_jersey_res_client`;
CREATE TABLE `flea_jersey_res_client` (
  `client_code` varchar(25) NOT NULL COMMENT '客户端编码',
  `resource_url` varchar(150) NOT NULL COMMENT '资源地址',
  `resource_code` varchar(30) NOT NULL COMMENT '资源编码',
  `service_code` varchar(30) NOT NULL COMMENT '服务编码',
  `request_mode` varchar(10) NOT NULL COMMENT '请求方式',
  `media_type` varchar(20) NOT NULL COMMENT '媒体类型',
  `client_input` varchar(150) NOT NULL COMMENT '客户端业务入参',
  `client_output` varchar(150) DEFAULT NULL COMMENT '客户端业务出参',
  `state` tinyint(1) NOT NULL COMMENT '状态(0：删除 1：正常 ）',
  `create_date` date DEFAULT NULL COMMENT '创建日期',
  `done_date` date DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(2000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`client_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_jersey_res_client
-- ----------------------------
INSERT INTO `flea_jersey_res_client` VALUES ('FLEA_CLIENT_DOWNLOAD_AUTH', 'http://localhost:8080/fleafs', 'download', 'FLEA_SERVICE_DOWNLOAD_AUTH', 'post', 'application/xml', 'com.huazie.ffs.pojo.download.input.InputDownloadAuthInfo', 'com.huazie.ffs.pojo.download.output.OutputDownloadAuthInfo', '1', '2019-12-15', null, '下载鉴权服务');
INSERT INTO `flea_jersey_res_client` VALUES ('FLEA_CLIENT_FILE_DOWNLOAD', 'http://localhost:8080/fleafs', 'download', 'FLEA_SERVICE_FILE_DOWNLOAD', 'fget', 'multipart/form-data', 'com.huazie.ffs.pojo.download.input.InputFileDownloadInfo', 'com.huazie.ffs.pojo.download.output.OutputFileDownloadInfo', '1', '2019-12-21', null, '文件下载服务');
INSERT INTO `flea_jersey_res_client` VALUES ('FLEA_CLIENT_FILE_UPLOAD', 'http://localhost:8080/fleafs', 'upload', 'FLEA_SERVICE_FILE_UPLOAD', 'fpost', 'multipart/form-data', 'com.huazie.ffs.pojo.upload.input.InputFileUploadInfo', 'com.huazie.ffs.pojo.upload.output.OutputFileUploadInfo', '1', '2019-12-16', null, '文件上传服务');
INSERT INTO `flea_jersey_res_client` VALUES ('FLEA_CLIENT_UPLOAD_AUTH', 'http://localhost:8080/fleafs', 'upload', 'FLEA_SERVICE_UPLOAD_AUTH', 'post', 'application/xml', 'com.huazie.ffs.pojo.upload.input.InputUploadAuthInfo', 'com.huazie.ffs.pojo.upload.output.OutputUploadAuthInfo', '1', '2019-12-10', null, '上传鉴权服务');

-- ----------------------------
-- Table structure for `flea_jersey_res_service`
-- ----------------------------
DROP TABLE IF EXISTS `flea_jersey_res_service`;
CREATE TABLE `flea_jersey_res_service` (
  `service_code` varchar(30) NOT NULL COMMENT '服务编码',
  `resource_code` varchar(30) NOT NULL COMMENT '资源编码',
  `service_name` varchar(300) NOT NULL COMMENT '服务名称',
  `service_interfaces` varchar(150) NOT NULL COMMENT '服务接口类',
  `service_method` varchar(30) NOT NULL COMMENT '服务方法',
  `service_input` varchar(150) NOT NULL COMMENT '服务入参',
  `service_output` varchar(150) NOT NULL COMMENT '服务出参',
  `state` tinyint(1) NOT NULL COMMENT '状态(0：删除 1：正常 ）',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改时间',
  `remarks` varchar(2000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`service_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_jersey_res_service
-- ----------------------------
INSERT INTO `flea_jersey_res_service` VALUES ('FLEA_SERVICE_FILE_DOWNLOAD', 'download', '文件下载服务', 'com.huazie.ffs.module.download.service.interfaces.IFleaDownloadSV', 'fileDownload', 'com.huazie.ffs.pojo.download.input.InputFileDownloadInfo', 'com.huazie.ffs.pojo.download.output.OutputFileDownloadInfo', '1', '2019-12-20 00:07:28', null, null);
INSERT INTO `flea_jersey_res_service` VALUES ('FLEA_SERVICE_FILE_UPLOAD', 'upload', '文件上传服务', 'com.huazie.ffs.module.upload.service.interfaces.IFleaUploadSV', 'fileUpload', 'com.huazie.ffs.pojo.upload.input.InputFileUploadInfo', 'com.huazie.ffs.pojo.upload.output.OutputFileUploadInfo', '1', '2019-12-04 22:05:23', null, null);
INSERT INTO `flea_jersey_res_service` VALUES ('FLEA_SERVICE_UPLOAD_AUTH', 'upload', '上传鉴权服务', 'com.huazie.ffs.module.upload.service.interfaces.IFleaUploadSV', 'uploadAuth', 'com.huazie.ffs.pojo.upload.input.InputUploadAuthInfo', 'com.huazie.ffs.pojo.upload.output.OutputUploadAuthInfo', '1', '2019-12-04 22:04:46', null, null);

-- ----------------------------
-- Table structure for `flea_jersey_res_service_log`
-- ----------------------------
DROP TABLE IF EXISTS `flea_jersey_res_service_log`;
CREATE TABLE `flea_jersey_res_service_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源服务日志编号',
  `resource_code` varchar(30) NOT NULL COMMENT '资源编码',
  `service_code` varchar(30) NOT NULL COMMENT '服务编码',
  `input` text COMMENT '业务入参',
  `output` text COMMENT '业务出参',
  `result_code` varchar(50) DEFAULT NULL COMMENT '操作结果码',
  `result_mess` varchar(2048) DEFAULT NULL COMMENT '操作结果信息',
  `account_id` int(11) NOT NULL COMMENT '操作账户编号',
  `system_account_id` int(11) NOT NULL COMMENT '系统账户编号',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `done_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_jersey_res_service_log
-- ----------------------------

-- ----------------------------
-- Table structure for `flea_menu_favorites`
-- ----------------------------
DROP TABLE IF EXISTS `flea_menu_favorites`;
CREATE TABLE `flea_menu_favorites` (
  `favorites_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单收藏夹编号',
  `account_id` int(11) NOT NULL COMMENT '账户编号',
  `menu_code` varchar(50) NOT NULL COMMENT '菜单编码',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `menu_icon` varchar(30) NOT NULL COMMENT '菜单FontAwesome小图标',
  `favorites_state` tinyint(4) NOT NULL COMMENT '菜单收藏夹状态（1: 正常  0: 删除）',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `done_date` datetime DEFAULT NULL COMMENT '修改时间',
  `effective_date` datetime NOT NULL COMMENT '生效时间',
  `expiry_date` datetime NOT NULL COMMENT '失效时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `ext1` varchar(255) DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(255) DEFAULT NULL COMMENT '扩展字段2',
  `ext3` varchar(255) DEFAULT NULL COMMENT '扩展字段3',
  PRIMARY KEY (`favorites_id`),
  KEY `fk_favorite_account_id` (`account_id`) USING BTREE,
  KEY `fk_favorite_menu_code` (`menu_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `flea_para_detail`
-- ----------------------------
DROP TABLE IF EXISTS `flea_para_detail`;
CREATE TABLE `flea_para_detail` (
  `para_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '参数编号',
  `para_type` varchar(50) NOT NULL COMMENT '参数类型',
  `para_code` varchar(50) NOT NULL COMMENT '参数编码',
  `para_name` varchar(60) NOT NULL COMMENT '参数名称',
  `para1` varchar(255) DEFAULT NULL COMMENT '参数1',
  `para2` varchar(255) DEFAULT NULL COMMENT '参数2',
  `para3` varchar(255) DEFAULT NULL COMMENT '参数3',
  `para4` varchar(255) DEFAULT NULL COMMENT '参数4',
  `para5` varchar(255) DEFAULT NULL COMMENT '参数5',
  `para_state` tinyint(2) NOT NULL COMMENT '参数状态',
  `para_desc` varchar(200) DEFAULT NULL COMMENT '参数描述',
  PRIMARY KEY (`para_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flea_para_detail
-- ----------------------------
