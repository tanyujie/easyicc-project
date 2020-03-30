/*
 Navicat Premium Data Transfer

 Source Server         : easyicc
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 118.31.43.2:3307
 Source Schema         : easysaas

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 30/03/2020 21:55:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for diy_form
-- ----------------------------
DROP TABLE IF EXISTS `diy_form`;
CREATE TABLE `diy_form`  (
  `form_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `org_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '注册表单名称',
  `depict` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `template_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `success_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '提交成功页面',
  `create` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `js_config_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '注册页面嵌入代码',
  PRIMARY KEY (`form_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '注册表单管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of diy_form
-- ----------------------------
INSERT INTO `diy_form` VALUES ('1251', '1', '777', NULL, 'form', '777', 'ceshi', '2018-10-09 11:39:29', '21642');
INSERT INTO `diy_form` VALUES ('1345', '1', '1', NULL, 'form', NULL, 'ceshi', '2018-11-28 15:34:12', NULL);
INSERT INTO `diy_form` VALUES ('2', '2', 'yue', 'yue', 'yue', 'yue', 'yue', '2018-07-03 11:54:44', '1');

SET FOREIGN_KEY_CHECKS = 1;
