-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('30', 'ERP', NULL, '0', NULL, NULL, 'OfficeBuilding', NULL, '1');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('31', '库存中心', NULL, '2', NULL, NULL, 'Box', NULL, '99');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('32', '库存查询', 'stock', '12', '/home/stock', '/views/erp/stock/stock/index.vue', NULL, 'stock', '0');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('33', '盘点订单', 'inventoryOrder', '12', '/home/inventoryorder', '/views/erp/stock/inventoryOrder/index.vue', NULL, 'inventoryOrder', '0');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('34', '采购中心', NULL, '2', NULL, NULL, 'ShoppingTrolley', NULL, '98');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('35', '采购入库单', 'purchaseInOrder', '13', '/home/purchaseinorder', '/views/erp/purchase/purchaseInOrder/index.vue', NULL, 'purchaseInOrder', '0');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('36', '采购出库单', 'purchaseOutOrder', '13', '/home/purchaseoutorder', '/views/erp/purchase/purchaseOutOrder/index.vue', NULL, 'purchaseOutOrder', '0');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('37', '测试模块', '', '0', '', '', NULL, '', '0');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('38', '测试管理', 'testTable', '35', '/home/testTable', '/views/test/table/index.vue', NULL, 'test', '0');

-- ----------------------------
-- Table structure for erp_inventory_order
-- ----------------------------
DROP TABLE IF EXISTS `erp_inventory_order`;
CREATE TABLE `erp_inventory_order` (
  `inventory_order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(255) DEFAULT NULL,
  `order_status` int(11) DEFAULT NULL,
  `audit_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) DEFAULT NULL,
  `created_name` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_name` varchar(255) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`inventory_order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for erp_inventory_order_line
-- ----------------------------
DROP TABLE IF EXISTS `erp_inventory_order_line`;
CREATE TABLE `erp_inventory_order_line` (
  `inventory_order_line_id` int(11) NOT NULL AUTO_INCREMENT,
  `inventory_order_id` int(11) DEFAULT NULL,
  `sku_code` varchar(255) DEFAULT NULL,
  `real_quantity` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`inventory_order_line_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for erp_purchase_in_order
-- ----------------------------
DROP TABLE IF EXISTS `erp_purchase_in_order`;
CREATE TABLE `erp_purchase_in_order` (
  `purchase_in_order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(255) DEFAULT NULL,
  `order_amount` decimal(10,2) DEFAULT NULL,
  `order_status` int(11) DEFAULT NULL,
  `audit_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) DEFAULT NULL,
  `created_name` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_name` varchar(255) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`purchase_in_order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for erp_purchase_in_order_line
-- ----------------------------
DROP TABLE IF EXISTS `erp_purchase_in_order_line`;
CREATE TABLE `erp_purchase_in_order_line` (
  `purchase_in_order_line_id` int(11) NOT NULL AUTO_INCREMENT,
  `purchase_in_order_id` int(11) DEFAULT NULL,
  `sku_code` varchar(255) DEFAULT NULL,
  `quantity` decimal(10,0) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`purchase_in_order_line_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for erp_purchase_out_order
-- ----------------------------
DROP TABLE IF EXISTS `erp_purchase_out_order`;
CREATE TABLE `erp_purchase_out_order` (
  `purchase_out_order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(255) DEFAULT NULL,
  `order_amount` decimal(10,2) DEFAULT NULL,
  `order_status` int(11) DEFAULT NULL,
  `audit_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) DEFAULT NULL,
  `created_name` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_name` varchar(255) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`purchase_out_order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for erp_purchase_out_order_line
-- ----------------------------
DROP TABLE IF EXISTS `erp_purchase_out_order_line`;
CREATE TABLE `erp_purchase_out_order_line` (
  `purchase_out_order_line_id` int(11) NOT NULL AUTO_INCREMENT,
  `purchase_out_order_id` int(11) DEFAULT NULL,
  `sku_code` varchar(255) DEFAULT NULL,
  `quantity` decimal(10,0) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`purchase_out_order_line_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for erp_stock
-- ----------------------------
DROP TABLE IF EXISTS `erp_stock`;
CREATE TABLE `erp_stock` (
  `stock_id` int(11) NOT NULL AUTO_INCREMENT,
  `sku_code` varchar(255) DEFAULT NULL,
  `quantity` decimal(10,0) DEFAULT NULL,
  `cost_amount` decimal(10,2) DEFAULT NULL,
  `created_name` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_name` varchar(255) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`stock_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for erp_stock_line
-- ----------------------------
DROP TABLE IF EXISTS `erp_stock_line`;
CREATE TABLE `erp_stock_line` (
  `stock_line_id` int(11) NOT NULL AUTO_INCREMENT,
  `stock_id` int(11) DEFAULT NULL,
  `order_no` varchar(255) DEFAULT NULL,
  `branch_id` int(11) DEFAULT NULL,
  `sku_code` varchar(255) DEFAULT NULL,
  `quantity` decimal(10,0) DEFAULT NULL,
  `order_type` int(11) DEFAULT NULL,
  `created_name` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_name` varchar(255) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`stock_line_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
