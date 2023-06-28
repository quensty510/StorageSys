/*
Navicat MySQL Data Transfer

Source Server         : mysql8
Source Server Version : 80033
Source Host           : localhost:3308
Source Database       : storage_sys

Target Server Type    : MYSQL
Target Server Version : 80033
File Encoding         : 65001

Date: 2023-06-28 14:43:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for export_record
-- ----------------------------
DROP TABLE IF EXISTS `export_record`;
CREATE TABLE `export_record` (
  `record_id` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '出库台账ID',
  `store_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '门店id',
  `rfid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品标识',
  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商品名称',
  `amount` int NOT NULL DEFAULT '0' COMMENT '商品数量',
  `create_time` datetime DEFAULT NULL COMMENT '出库时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `rfid` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品唯一标识',
  `goods_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商品名称',
  `goods_price` decimal(10,4) DEFAULT NULL COMMENT '商品价格',
  `goods_spec` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商品规格',
  `goods_amount` int DEFAULT '0' COMMENT '商品库存数量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`rfid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for import_record
-- ----------------------------
DROP TABLE IF EXISTS `import_record`;
CREATE TABLE `import_record` (
  `record_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '入库台账ID',
  `store_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '门店id',
  `rfid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品标识',
  `goods_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商品名称',
  `amount` int NOT NULL DEFAULT '0' COMMENT '商品数量',
  `create_time` datetime DEFAULT NULL COMMENT '入库时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `message_id` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息ID',
  `message_content` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '消息正文',
  `message_status` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '消息阅读状态0-未阅读，1-已阅读',
  `export_time` datetime DEFAULT NULL COMMENT '出库时间',
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
  `store_id` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '门店ID',
  `store_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '门店名称',
  `store_address` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '门店地址',
  PRIMARY KEY (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
