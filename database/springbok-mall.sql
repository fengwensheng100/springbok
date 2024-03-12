-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('16', '商城系统', NULL, '0', NULL, NULL, 'ShoppingBag', NULL, '2');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('17', '商品中心', NULL, '3', NULL, NULL, 'Goods', NULL, '99');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('18', '营销分类', 'itemSaleClass', '18', '/home/itemsaleclass', '/views/mall/items/itemSaleClass/index.vue', NULL, 'itemSaleClass', '0');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('19', '规格管理', 'spec', '18', '/home/spec', '/views/mall/items/spec/index.vue', NULL, 'spec', '0');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('20', '销售商品', 'itemSale', '18', '/home/itemsale', '/views/mall/items/itemSale/index.vue', NULL, 'itemSale', '0');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('21', '订单中心', NULL, '3', NULL, NULL, 'Tickets', NULL, '98');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('22', '销售订单', 'saleOrder', '19', '/home/saleorder', '/views/mall/order/saleOrder/index.vue', NULL, 'saleOrder', '0');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('23', '会员中心', NULL, '3', NULL, NULL, 'User', NULL, '97');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('24', '会员管理', 'member', '20', '/home/member', '/views/mall/member/member/index.vue', NULL, 'member', '0');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('25', '营销中心', NULL, '3', NULL, NULL, 'DataAnalysis', NULL, '96');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('26', '标签管理', 'tag', '21', '/home/tag', '/views/mall/marketing/tag/index.vue', NULL, 'tag', '0');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('27', '优惠券管理', 'coupon', '21', '/home/coupon', '/views/mall/marketing/coupon/index.vue', NULL, 'coupon', '0');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('28', '专题管理', 'subject', '21', '/home/subject', '/views/mall/marketing/subject/index.vue', NULL, 'subject', '0');
INSERT INTO `sys_menu` (`sys_menu_id`, `menu_name`, `router_name`, `parent_id`, `router_path`, `router_component`, `menu_icon`, `permission_code`, `menu_sort`) VALUES ('29', '广告管理', 'banner', '21', '/home/banner', '/views/mall/marketing/banner/index.vue', NULL, 'banner', '0');

-- ----------------------------
-- Table structure for mall_banner
-- ----------------------------
DROP TABLE IF EXISTS `mall_banner`;
CREATE TABLE `mall_banner` (
  `banner_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `to_page` varchar(255) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `created_name` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_name` varchar(255) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`banner_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for mall_coupon
-- ----------------------------
DROP TABLE IF EXISTS `mall_coupon`;
CREATE TABLE `mall_coupon` (
  `coupon_id` int(11) NOT NULL AUTO_INCREMENT,
  `coupon_name` varchar(255) DEFAULT NULL,
  `coupon_description` varchar(255) DEFAULT NULL,
  `coupon_image` varchar(255) DEFAULT NULL,
  `coupon_type` int(1) DEFAULT NULL,
  `coupon_limit` decimal(10,2) DEFAULT NULL,
  `coupon_amount` decimal(10,2) DEFAULT NULL,
  `used_item_sale_type` int(1) DEFAULT NULL,
  `created_name` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_name` varchar(255) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`coupon_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for mall_coupon_item_sale
-- ----------------------------
DROP TABLE IF EXISTS `mall_coupon_item_sale`;
CREATE TABLE `mall_coupon_item_sale` (
  `coupon_item_sale_id` int(11) NOT NULL AUTO_INCREMENT,
  `coupon_id` int(11) DEFAULT NULL,
  `item_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`coupon_item_sale_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for mall_item_sale
-- ----------------------------
DROP TABLE IF EXISTS `mall_item_sale`;
CREATE TABLE `mall_item_sale` (
  `item_sale_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_code` varchar(255) DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  `item_sale_name` varchar(255) DEFAULT NULL,
  `main_image` varchar(4096) DEFAULT NULL,
  `item_image` varchar(1020) DEFAULT NULL,
  `item_sale_description` text,
  `item_sale_price` decimal(10,2) DEFAULT NULL,
  `item_sale_class_code` int(11) DEFAULT NULL,
  `spec_json` varchar(4096) DEFAULT NULL,
  `property_json` varchar(4096) DEFAULT NULL,
  `tag_ids` varchar(255) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `created_name` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_name` varchar(255) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`item_sale_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=648 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for mall_item_sale_class
-- ----------------------------
DROP TABLE IF EXISTS `mall_item_sale_class`;
CREATE TABLE `mall_item_sale_class` (
  `item_sale_class_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_sale_class_code` varchar(255) DEFAULT NULL,
  `item_sale_class_name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT '0',
  `class_icon` varchar(255) DEFAULT NULL,
  `show_index` tinyint(1) DEFAULT NULL,
  `created_name` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_name` varchar(255) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`item_sale_class_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=310 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for mall_item_sale_sku
-- ----------------------------
DROP TABLE IF EXISTS `mall_item_sale_sku`;
CREATE TABLE `mall_item_sale_sku` (
  `item_sale_sku_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_sale_id` int(11) DEFAULT NULL,
  `item_code` varchar(255) DEFAULT NULL,
  `sku_code` varchar(255) DEFAULT NULL,
  `item_name` varchar(1024) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `source_price` decimal(10,2) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `spec` varchar(1024) DEFAULT NULL,
  `spec_map_json` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`item_sale_sku_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5437 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for mall_member
-- ----------------------------
DROP TABLE IF EXISTS `mall_member`;
CREATE TABLE `mall_member` (
  `member_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `gender` varchar(2) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `created_name` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_name` varchar(255) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`member_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for mall_member_address
-- ----------------------------
DROP TABLE IF EXISTS `mall_member_address`;
CREATE TABLE `mall_member_address` (
  `member_address_id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT NULL,
  `receiver` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `province_code` varchar(255) DEFAULT NULL,
  `city_code` varchar(255) DEFAULT NULL,
  `county_code` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `default_address` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`member_address_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for mall_member_cart
-- ----------------------------
DROP TABLE IF EXISTS `mall_member_cart`;
CREATE TABLE `mall_member_cart` (
  `member_cart_id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT NULL,
  `item_sale_id` int(11) DEFAULT NULL,
  `item_sale_sku_id` int(11) DEFAULT NULL,
  `item_sale_name` varchar(255) DEFAULT NULL,
  `attrs_text` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `add_price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`member_cart_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for mall_member_coupon
-- ----------------------------
DROP TABLE IF EXISTS `mall_member_coupon`;
CREATE TABLE `mall_member_coupon` (
  `member_coupon_id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT NULL,
  `coupon_id` int(11) DEFAULT NULL,
  `coupon_no` varchar(255) DEFAULT NULL,
  `coupon_status` int(1) DEFAULT NULL,
  `created_name` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_name` varchar(255) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`member_coupon_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for mall_sale_order
-- ----------------------------
DROP TABLE IF EXISTS `mall_sale_order`;
CREATE TABLE `mall_sale_order` (
  `sale_order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(20) DEFAULT NULL,
  `member_id` int(11) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `pay_amount` decimal(10,2) DEFAULT NULL,
  `discount_amount` decimal(10,2) DEFAULT NULL,
  `post_fee` decimal(10,2) DEFAULT NULL,
  `receiver_name` varchar(255) DEFAULT NULL,
  `receiver_tel` varchar(255) DEFAULT NULL,
  `receiver_address` varchar(255) DEFAULT NULL,
  `sale_channel` int(11) DEFAULT NULL,
  `order_status` int(2) DEFAULT NULL,
  `pay_status` int(1) DEFAULT NULL,
  `pay_channel` int(1) DEFAULT NULL,
  `pay_type` int(1) DEFAULT NULL,
  `pay_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `total_quantity` int(11) DEFAULT NULL,
  `member_remark` varchar(255) DEFAULT NULL,
  `cancel_reason` varchar(255) DEFAULT NULL,
  `created_name` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_name` varchar(255) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`sale_order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for mall_sale_order_line
-- ----------------------------
DROP TABLE IF EXISTS `mall_sale_order_line`;
CREATE TABLE `mall_sale_order_line` (
  `sale_order_line_id` int(11) NOT NULL AUTO_INCREMENT,
  `sale_order_id` int(11) DEFAULT NULL,
  `item_sale_id` int(11) DEFAULT NULL,
  `item_sale_sku_id` int(11) DEFAULT NULL,
  `sku_code` varchar(255) DEFAULT NULL,
  `item_sale_name` varchar(255) DEFAULT NULL,
  `attrs_text` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `quantity` decimal(10,0) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `pay_price` decimal(10,2) DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `pay_amount` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`sale_order_line_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for mall_spec
-- ----------------------------
DROP TABLE IF EXISTS `mall_spec`;
CREATE TABLE `mall_spec` (
  `spec_id` int(11) NOT NULL AUTO_INCREMENT,
  `spec_name` varchar(255) DEFAULT NULL,
  `sku_str` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `created_name` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_name` varchar(255) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`spec_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for mall_spec_value
-- ----------------------------
DROP TABLE IF EXISTS `mall_spec_value`;
CREATE TABLE `mall_spec_value` (
  `spec_value_id` int(11) NOT NULL AUTO_INCREMENT,
  `spec_value_name` varchar(255) DEFAULT NULL,
  `spec_id` int(11) DEFAULT NULL,
  `created_name` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_name` varchar(255) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`spec_value_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for mall_subject
-- ----------------------------
DROP TABLE IF EXISTS `mall_subject`;
CREATE TABLE `mall_subject` (
  `subject_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `subject_description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `created_name` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_name` varchar(255) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`subject_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for mall_subject_item_sale
-- ----------------------------
DROP TABLE IF EXISTS `mall_subject_item_sale`;
CREATE TABLE `mall_subject_item_sale` (
  `subject_item_sale_id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_id` int(11) DEFAULT NULL,
  `item_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`subject_item_sale_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for mall_tag
-- ----------------------------
DROP TABLE IF EXISTS `mall_tag`;
CREATE TABLE `mall_tag` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_type` int(11) DEFAULT NULL,
  `tag_name` varchar(255) DEFAULT NULL,
  `created_name` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_name` varchar(255) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`tag_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
