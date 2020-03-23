/*
 Navicat Premium Data Transfer

 Source Server         : easycrm
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 127.0.0.1:3306
 Source Schema         : easyicc

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 23/03/2020 13:52:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for skill_group
-- ----------------------------
DROP TABLE IF EXISTS `skill_group`;
CREATE TABLE `skill_group`  (
  `id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `org_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客服分组名称',
  `order_type` int(0) NOT NULL COMMENT '人员分配算法1随机2轮循3空闲率4队列5对话在线时间比6对话量权重在线时间比7等级优先分配算法',
  `use_queue` int(0) NOT NULL DEFAULT 1 COMMENT '是否启用排队0关闭1启用',
  `max_queue_size` int(0) NOT NULL COMMENT '最大排队人数',
  `auto_distribute` int(0) NULL DEFAULT NULL COMMENT '排队访客分配规则1客服手动接入2系统自动分配 ',
  `overflow_group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '忙线溢出组',
  `overflow_queue_time` int(0) NOT NULL COMMENT '排队溢出时间',
  `priority` int(0) NULL DEFAULT NULL COMMENT '排序号',
  `depict` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `staff_ids` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客服人员',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客服分组管理' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
