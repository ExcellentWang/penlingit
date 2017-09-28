/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 100030
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 100030
File Encoding         : 65001

Date: 2017-06-16 20:38:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_s_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_s_detail`;
CREATE TABLE `t_s_detail` (
  `detail_id` int(16) NOT NULL AUTO_INCREMENT COMMENT '明细ID',
  `dict_id` int(16) DEFAULT NULL COMMENT '对应字典ID',
  `detail_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '明细名称',
  `detail_value` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '明细值',
  `detail_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '明细描述',
  `detail_status` int(16) DEFAULT NULL COMMENT '1启用，2禁用',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_s_detail
-- ----------------------------
INSERT INTO `t_s_detail` VALUES ('1', '1', '启用', '1', '启用状态', '1', '2015-01-04 14:04:28', '2015-01-06 11:02:33');
INSERT INTO `t_s_detail` VALUES ('2', '1', '禁止', '2', '禁止状态', '1', '2015-01-04 14:04:39', '2015-01-04 14:04:39');
INSERT INTO `t_s_detail` VALUES ('3', '2', '男', '1', '男', '1', '2015-03-23 15:52:05', '2015-03-23 15:52:05');
INSERT INTO `t_s_detail` VALUES ('4', '2', '女', '0', '女', '1', '2015-03-23 15:52:20', '2015-03-23 15:52:20');
INSERT INTO `t_s_detail` VALUES ('5', '3', '菜单文件夹', '1', '', '1', '2015-03-23 15:53:10', '2015-03-23 15:53:10');
INSERT INTO `t_s_detail` VALUES ('6', '3', '页面菜单', '2', '', '1', '2015-03-23 15:53:26', '2015-03-23 15:53:26');
INSERT INTO `t_s_detail` VALUES ('7', '3', '功能按钮', '3', '', '1', '2015-03-23 15:53:42', '2015-03-23 15:53:42');

-- ----------------------------
-- Table structure for t_s_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_s_dict`;
CREATE TABLE `t_s_dict` (
  `dict_id` int(16) NOT NULL AUTO_INCREMENT COMMENT '字典ID',
  `dict_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '字典名称',
  `dict_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '字典描述',
  `dict_status` int(8) DEFAULT NULL COMMENT '1启用，2禁用',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_s_dict
-- ----------------------------
INSERT INTO `t_s_dict` VALUES ('1', '状态', '1启用2禁止', '1', '2015-01-04 11:07:56', '2015-01-13 14:07:08');
INSERT INTO `t_s_dict` VALUES ('2', '性别', '性别:1男 2女', '1', '2015-03-23 15:51:34', '2015-03-23 15:51:34');
INSERT INTO `t_s_dict` VALUES ('3', '菜单类型', '1：菜单文件夹，2：页面菜单，3：功能按钮', '1', '2015-03-23 15:52:46', '2015-03-23 15:52:46');

-- ----------------------------
-- Table structure for t_s_log
-- ----------------------------
DROP TABLE IF EXISTS `t_s_log`;
CREATE TABLE `t_s_log` (
  `log_id` int(16) NOT NULL AUTO_INCREMENT,
  `action_url` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '访问URL',
  `log_time` datetime DEFAULT NULL COMMENT '访问时间',
  `user_ip` varchar(15) COLLATE utf8_bin DEFAULT '用户IP' COMMENT '用户IP',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `log_desc` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '日志描述',
  `user_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名称',
  `process_time` int(8) DEFAULT NULL COMMENT '操作响应时间',
  `controller_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '控制器名称',
  `controller_method` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '控制器方法名称',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=59708 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_s_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_s_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_s_menu`;
CREATE TABLE `t_s_menu` (
  `menu_id` int(16) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `menu_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `menu_url` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `menu_pid` int(8) DEFAULT NULL,
  `menu_type` int(8) DEFAULT NULL COMMENT '资源类型，1：文件夹菜单，2：功能菜单，3：功能按钮',
  `menu_status` int(8) DEFAULT NULL,
  `menu_level` int(8) DEFAULT NULL,
  `menu_icon` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `menu_order` int(8) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_s_menu
-- ----------------------------
INSERT INTO `t_s_menu` VALUES ('1', '资源树', '资源树', null, '1', '1', '1', '0', null, null, null, '1');
INSERT INTO `t_s_menu` VALUES ('2', '系统管理', '系统管理', null, '1', '1', '1', '1', null, null, '2017-06-01 09:51:41', '7');
INSERT INTO `t_s_menu` VALUES ('3', '系统管理员管理', '用户管理', '/system/prg/user/init', '2', '2', '1', '2', null, null, null, '1');
INSERT INTO `t_s_menu` VALUES ('4', '系统角色管理', '角色管理', '/system/prg/role/init', '2', '2', '1', '2', null, null, null, '2');
INSERT INTO `t_s_menu` VALUES ('5', '系统菜单管理', '菜单管理', '/system/prg/menu/init', '2', '2', '1', '2', null, null, null, '3');
INSERT INTO `t_s_menu` VALUES ('6', '系统日志管理', null, '/system/prg/log/init', '2', '2', '1', '2', null, null, null, '5');
INSERT INTO `t_s_menu` VALUES ('7', '系统参数管理', null, '/system/prg/dict/init', '2', '2', '1', '2', null, null, null, '4');

-- ----------------------------
-- Table structure for t_s_role
-- ----------------------------
DROP TABLE IF EXISTS `t_s_role`;
CREATE TABLE `t_s_role` (
  `role_id` int(16) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `role_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `role_order` int(8) DEFAULT NULL,
  `role_type` int(8) DEFAULT NULL,
  `role_status` int(8) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_s_role
-- ----------------------------
INSERT INTO `t_s_role` VALUES ('1', '超级管理员', '最高权限管理员', '1', null, '1', '2014-12-30 13:22:52', '2014-12-30 13:22:52');
INSERT INTO `t_s_role` VALUES ('7', '测试', '测试人员', '2', null, '1', '2017-02-28 09:05:33', '2017-02-28 09:06:01');
INSERT INTO `t_s_role` VALUES ('8', 'developer', '', null, null, null, '2017-03-17 16:54:03', '2017-03-17 16:54:03');

-- ----------------------------
-- Table structure for t_s_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_s_role_menu`;
CREATE TABLE `t_s_role_menu` (
  `role_menu_id` int(16) NOT NULL AUTO_INCREMENT,
  `role_id` int(16) DEFAULT NULL,
  `menu_id` int(16) DEFAULT NULL,
  PRIMARY KEY (`role_menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2370 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of t_s_role_menu
-- ----------------------------
INSERT INTO `t_s_role_menu` VALUES ('1', '1', '1');
INSERT INTO `t_s_role_menu` VALUES ('2', '1', '2');
INSERT INTO `t_s_role_menu` VALUES ('3', '1', '3');
INSERT INTO `t_s_role_menu` VALUES ('4', '1', '4');
INSERT INTO `t_s_role_menu` VALUES ('5', '1', '5');
INSERT INTO `t_s_role_menu` VALUES ('6', '1', '6');
INSERT INTO `t_s_role_menu` VALUES ('7', '1', '7');

-- ----------------------------
-- Table structure for t_s_role_user
-- ----------------------------
DROP TABLE IF EXISTS `t_s_role_user`;
CREATE TABLE `t_s_role_user` (
  `role_user_id` int(16) NOT NULL AUTO_INCREMENT,
  `user_id` int(16) DEFAULT NULL,
  `role_id` int(16) DEFAULT NULL,
  PRIMARY KEY (`role_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of t_s_role_user
-- ----------------------------
INSERT INTO `t_s_role_user` VALUES ('66', '162', '1');
INSERT INTO `t_s_role_user` VALUES ('67', '169', '1');

-- ----------------------------
-- Table structure for t_s_user
-- ----------------------------
DROP TABLE IF EXISTS `t_s_user`;
CREATE TABLE `t_s_user` (
  `user_id` int(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `user_order` int(8) DEFAULT NULL,
  `user_type` int(8) DEFAULT NULL,
  `user_status` int(8) DEFAULT NULL COMMENT '1启用2禁止',
  `user_pwd` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `real_name` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `MOBILE` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `SEX` int(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=170 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_s_user
-- ----------------------------
INSERT INTO `t_s_user` VALUES ('162', 'super', '1', '1', '1', 'E10ADC3949BA59ABBE56E057F20F883E', '2015-02-27 00:00:00', '2017-06-16 20:29:31', 'super', '5@qq.com', '13212341234', '1');
INSERT INTO `t_s_user` VALUES ('169', 'kedong', null, '1', '1', '9EA01F4A1AC12193B497CF574FDDBC6B', '2017-03-17 16:53:41', '2017-03-21 10:41:25', 'kedong', '', '13277918809', '1');
