/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : springbok

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2024-02-28 16:24:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_app
-- ----------------------------
DROP TABLE IF EXISTS `sys_app`;
CREATE TABLE `sys_app` (
  `sys_app_id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` varchar(50) DEFAULT NULL,
  `app_secret` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sys_app_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `sys_config_id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `config_options` varchar(255) DEFAULT NULL,
  `config_key` varchar(255) DEFAULT NULL,
  `config_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sys_config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', '登录验证方式', 'radio', '[{\"label\":\"随机验证码\",\"value\":\"captcha\"},{\"label\":\"滑块验证\",\"value\":\"slider\"}]', 'VERIFY_METHOD', 'captcha');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `dict_id` int(11) NOT NULL AUTO_INCREMENT,
  `dict_code` varchar(255) DEFAULT NULL,
  `dict_name` varchar(255) DEFAULT NULL,
  `dict_type` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `dict_sort` varchar(255) DEFAULT NULL,
  `created_name` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_name` varchar(255) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`dict_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', 'coupon_type', '券类型', '1', '优惠券类型', '0', 'admin', '2024-01-01 19:02:07', null, '2024-02-26 10:05:51');
INSERT INTO `sys_dict` VALUES ('2', 'sale_channel', '销售渠道', '1', '销售渠道', '0', 'admin', '2024-01-01 20:43:23', null, null);
INSERT INTO `sys_dict` VALUES ('3', 'branch_status', '门店状态', '1', '门店状态', '0', 'admin', '2024-01-02 11:40:11', null, null);
INSERT INTO `sys_dict` VALUES ('4', 'branch_type', '门店类型', '1', '门店类型', '0', 'admin', '2024-01-02 11:40:43', null, null);
INSERT INTO `sys_dict` VALUES ('5', 'bill_status', '单据状态', '1', '单据状态', '0', 'admin', '2024-01-02 11:43:28', null, '2024-02-02 11:51:30');
INSERT INTO `sys_dict` VALUES ('7', 'coupon_status', '券状态', '1', '券状态', '0', 'admin', '2024-01-02 11:45:17', null, '2024-01-02 11:48:18');
INSERT INTO `sys_dict` VALUES ('8', 'bill_type', '单据类型', '1', '单据类型', '0', 'admin', '2024-01-02 11:47:20', null, null);
INSERT INTO `sys_dict` VALUES ('9', 'tag_type', '标签类型', '1', '标签类型', '0', 'admin', '2024-01-02 11:49:14', null, null);
INSERT INTO `sys_dict` VALUES ('10', 'dict_type', '字典类型', '1', '字典类型', '0', 'admin', '2024-01-02 11:50:02', null, null);
INSERT INTO `sys_dict` VALUES ('11', 'order_status', '订单状态', '1', '订单状态', '0', 'admin', '2024-02-02 11:52:33', null, null);

-- ----------------------------
-- Table structure for sys_dict_value
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_value`;
CREATE TABLE `sys_dict_value` (
  `dict_value_id` int(11) NOT NULL AUTO_INCREMENT,
  `dict_id` int(11) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `dict_sort` varchar(255) DEFAULT NULL,
  `created_name` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_name` varchar(255) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`dict_value_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_dict_value
-- ----------------------------
INSERT INTO `sys_dict_value` VALUES ('1', '1', '1', '满减', null, '0', 'admin', '2024-01-01 20:59:57', null, '2024-01-01 20:59:57');
INSERT INTO `sys_dict_value` VALUES ('2', '1', '2', '折扣', null, '0', 'admin', '2024-01-01 20:59:59', null, '2024-01-01 20:59:59');
INSERT INTO `sys_dict_value` VALUES ('4', '2', '1', '门店收银机', null, '0', 'admin', '2024-01-01 21:30:59', null, null);
INSERT INTO `sys_dict_value` VALUES ('5', '2', '2', '小程序', null, '0', 'admin', '2024-01-01 21:32:13', null, '2024-01-01 21:32:13');
INSERT INTO `sys_dict_value` VALUES ('6', '2', '3', '美团', null, '0', 'admin', '2024-01-01 21:32:33', null, null);
INSERT INTO `sys_dict_value` VALUES ('7', '2', '4', '饿了么', null, '0', 'admin', '2024-01-01 21:32:40', null, null);
INSERT INTO `sys_dict_value` VALUES ('8', '3', '1', '营业', null, '0', 'admin', '2024-01-02 11:40:22', null, null);
INSERT INTO `sys_dict_value` VALUES ('9', '3', '2', '闭店', null, '0', 'admin', '2024-01-02 11:40:27', null, null);
INSERT INTO `sys_dict_value` VALUES ('10', '4', '1', '门店', null, '0', 'admin', '2024-01-02 11:40:56', null, null);
INSERT INTO `sys_dict_value` VALUES ('11', '4', '2', '仓库', null, '0', 'admin', '2024-01-02 11:40:58', null, null);
INSERT INTO `sys_dict_value` VALUES ('12', '5', '1', '制单', null, '0', 'admin', '2024-01-02 11:43:36', null, null);
INSERT INTO `sys_dict_value` VALUES ('13', '5', '2', '审核', null, '0', 'admin', '2024-01-02 11:43:40', null, null);
INSERT INTO `sys_dict_value` VALUES ('16', '7', '1', '未使用', null, '0', 'admin', '2024-01-02 11:45:26', null, '2024-01-02 11:46:04');
INSERT INTO `sys_dict_value` VALUES ('17', '7', '2', '已使用', null, '0', 'admin', '2024-01-02 11:45:36', null, '2024-01-02 11:46:09');
INSERT INTO `sys_dict_value` VALUES ('18', '8', '1', '销售出库单', null, '0', 'admin', '2024-01-02 11:47:37', null, '2024-02-26 10:19:41');
INSERT INTO `sys_dict_value` VALUES ('19', '8', '2', '销售退货单', null, '0', 'admin', '2024-01-02 11:47:43', null, '2024-02-26 10:19:36');
INSERT INTO `sys_dict_value` VALUES ('20', '8', '3', '采购入库单', null, '0', 'admin', '2024-01-02 11:47:51', null, '2024-02-26 10:19:46');
INSERT INTO `sys_dict_value` VALUES ('23', '9', '1', '商品', null, '0', 'admin', '2024-01-02 11:49:21', null, null);
INSERT INTO `sys_dict_value` VALUES ('24', '9', '2', '用户', null, '0', 'admin', '2024-01-02 11:49:25', null, null);
INSERT INTO `sys_dict_value` VALUES ('25', '10', '1', '系统字典', null, '0', 'admin', '2024-01-02 11:50:10', null, '2024-01-05 11:49:46');
INSERT INTO `sys_dict_value` VALUES ('28', '10', '2', '业务字典', null, '0', 'admin', '2024-01-05 12:35:54', null, null);
INSERT INTO `sys_dict_value` VALUES ('29', '11', '1', '待付款', null, '0', 'admin', '2024-02-02 11:52:54', null, null);
INSERT INTO `sys_dict_value` VALUES ('30', '11', '2', '待发货', null, '0', 'admin', '2024-02-02 11:53:01', null, null);
INSERT INTO `sys_dict_value` VALUES ('31', '11', '3', '待收货', null, '0', 'admin', '2024-02-02 11:53:06', null, null);
INSERT INTO `sys_dict_value` VALUES ('32', '11', '4', '待评价', null, '0', 'admin', '2024-02-02 11:53:14', null, null);
INSERT INTO `sys_dict_value` VALUES ('33', '11', '5', '已完成', null, '0', 'admin', '2024-02-02 11:53:22', null, null);
INSERT INTO `sys_dict_value` VALUES ('35', '8', '4', '采购出库单', null, '0', 'admin', '2024-02-03 16:25:11', null, '2024-02-26 10:19:55');
INSERT INTO `sys_dict_value` VALUES ('36', '8', '5', '盘点单', null, '0', 'admin', '2024-02-03 16:25:18', null, '2024-02-26 10:20:02');
INSERT INTO `sys_dict_value` VALUES ('37', '11', '6', '已取消', null, '0', 'admin', '2024-02-26 10:12:17', null, null);

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job` (
  `sys_job_id` int(11) NOT NULL AUTO_INCREMENT,
  `job_key` varchar(255) DEFAULT NULL,
  `job_group` varchar(255) DEFAULT NULL,
  `schedule_type` int(1) DEFAULT NULL,
  `cron` varchar(30) DEFAULT NULL,
  `bean` varchar(255) DEFAULT NULL,
  `start_now` tinyint(1) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `params` varchar(1024) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sys_job_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES ('7', 'coupon', 'couponGroup', '1', '0/2 * * * * ? *', 'couponJob', '0', '2', '999', '456');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log` (
  `sys_job_log_id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_job_id` int(11) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` int(1) DEFAULT NULL,
  `error_msg` text,
  `execute_spend` bigint(10) DEFAULT NULL,
  PRIMARY KEY (`sys_job_log_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=561 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `sys_log_id` int(11) NOT NULL AUTO_INCREMENT,
  `request_url` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `request_param` text,
  `request_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `request_spend` int(10) DEFAULT NULL,
  `request_ip` varchar(255) DEFAULT NULL,
  `error_msg` text,
  PRIMARY KEY (`sys_log_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=266 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `sys_menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(255) DEFAULT NULL,
  `router_name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `router_path` varchar(255) DEFAULT NULL,
  `router_component` varchar(255) DEFAULT NULL,
  `menu_icon` varchar(255) DEFAULT NULL,
  `permission_code` varchar(255) DEFAULT NULL,
  `menu_sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`sys_menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('1', '首页', 'index', '0', '/home/index', '/views/index.vue', 'House', 'index', '99');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('2', '系统管理', NULL, '0', NULL, NULL, 'Monitor', NULL, '3');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('3', '基础资料', NULL, '1', NULL, NULL, 'Files', NULL, '99');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('4', '菜单管理', 'menu', '4', '/home/menu', '/views/system/base/menu/index.vue', NULL, 'menu', '99');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('5', '角色管理', 'role', '4', '/home/role', '/views/system/base/role/index.vue', NULL, 'role', '3');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('6', '用户管理', 'user', '4', '/home/user', '/views/system/base/user/index.vue', NULL, 'user', '2');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('7', '应用管理', 'app', '4', '/home/app', '/views/system/base/app/index.vue', NULL, 'app', '1');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('8', '开发配置', NULL, '1', NULL, NULL, 'Setting', NULL, '98');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('9', '字典管理', 'dict', '5', '/home/dict', '/views/system/setting/dict/index.vue', NULL, 'dict', '0');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('10', '操作日志', 'log', '5', '/home/log', '/views/system/setting/log/index.vue', NULL, 'log', '0');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('11', '定时任务', 'job', '5', '/home/job', '/views/system/setting/job/index.vue', NULL, 'job', '0');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('12', '系统配置', 'config', '5', '/home/config', '/views/system/setting/config/index.vue', NULL, 'config', '0');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('13', '数据库监控', 'sql', '5', '/home/sql', '/views/system/setting/sql/index.vue', NULL, 'sql', '0');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('14', '接口文档', 'doc', '5', '/home/doc', '/views/system/setting/doc/index.vue', NULL, 'doc', '0');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('15', '应用监控', 'health', '5', '/home/health', '/views/system/setting/health/index.vue', NULL, 'health', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `sys_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  `role_type` int(1) DEFAULT NULL,
  `created_name` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_name` varchar(255) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`sys_role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('3', 'admin', '1', 'admin', '2023-12-23 09:53:07', 'springbok', '2024-02-26 15:49:11');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `sys_role_menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_role_id` int(11) DEFAULT NULL,
  `sys_menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`sys_role_menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=405 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '3', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '3', '2');
INSERT INTO `sys_role_menu` VALUES ('3', '3', '3');
INSERT INTO `sys_role_menu` VALUES ('4', '3', '4');
INSERT INTO `sys_role_menu` VALUES ('5', '3', '5');
INSERT INTO `sys_role_menu` VALUES ('6', '3', '6');
INSERT INTO `sys_role_menu` VALUES ('7', '3', '7');
INSERT INTO `sys_role_menu` VALUES ('8', '3', '8');
INSERT INTO `sys_role_menu` VALUES ('9', '3', '9');
INSERT INTO `sys_role_menu` VALUES ('10', '3', '10');
INSERT INTO `sys_role_menu` VALUES ('11', '3', '11');
INSERT INTO `sys_role_menu` VALUES ('12', '3', '12');
INSERT INTO `sys_role_menu` VALUES ('13', '3', '13');
INSERT INTO `sys_role_menu` VALUES ('14', '3', '14');
INSERT INTO `sys_role_menu` VALUES ('15', '3', '15');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `sys_user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `sys_role_id` int(11) DEFAULT NULL,
  `created_name` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_name` varchar(255) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sys_user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'f22324b1c595901c9d6c86772af916cf', 'springbok', '15600000000', '3', 'admin', '2024-01-01 01:58:19', 'admin', '2024-01-12 09:39:25', '8db1bb79-aa88-4277-8fbf-ab836697a6b5');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `sys_user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_user_id` int(11) DEFAULT NULL,
  `sys_role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`sys_user_role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '3');
