/*
 Navicat Premium Data Transfer

 Source Server         : easycompany
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3307
 Source Schema         : easysaas

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 26/03/2020 22:24:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for card_rule
-- ----------------------------
DROP TABLE IF EXISTS `card_rule`;
CREATE TABLE `card_rule`  (
  `org_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `use_allocation` int(11) DEFAULT NULL COMMENT '是否启用分配',
  `time_interval` int(11) DEFAULT NULL COMMENT '分配时间间隔',
  `allocation_size` int(11) DEFAULT NULL COMMENT '间隔时间内分配数量',
  `max_allocation_size` int(11) DEFAULT NULL COMMENT '每天最多分配数量',
  `expired_recover` int(11) DEFAULT NULL COMMENT '超期回收开关',
  `expired_hour` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '超时回收时间',
  `reset_time` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '重置时间',
  `mobile_hide_time` int(11) DEFAULT NULL COMMENT '手机号码隐藏时间',
  `server_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `allocation_delay` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '分配延迟(分)',
  `default_subject_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '默认项目',
  `default_school_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '默认校区',
  `need_online` int(11) DEFAULT NULL COMMENT '必须在线才能分配',
  `need_scheeduling` int(11) DEFAULT NULL COMMENT '必须排班才能分配',
  `allocation_model` int(11) DEFAULT NULL COMMENT '分配模式 0按校区/项目 1 分配给创建者',
  `algorithms` int(11) DEFAULT NULL,
  `is_wechat_open` int(11) DEFAULT NULL COMMENT '微信推送是否开启 0开启 1关闭',
  `is_online_allocation` int(11) DEFAULT NULL COMMENT '在线分配模式 0 在线分配(只有在线的状态可以进行名片的分配) 1 在线分配(在线.忙碌.离开的状态可以进行名片的分配)',
  `need_sumbit_toutiao` int(11) DEFAULT NULL COMMENT '是否开启上报头条功能（默认开启）',
  PRIMARY KEY (`org_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for visitor_info
-- ----------------------------
DROP TABLE IF EXISTS `visitor_info`;
CREATE TABLE `visitor_info`  (
  `id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `visitor_static_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `org_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '公司ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '笔记',
  `reserve_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密钥',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别',
  `rename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '代表',
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `qq` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'QQ',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '公司名称',
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `staff_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户ID',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `crm_state` int(11) DEFAULT NULL COMMENT 'crm状态',
  `crm_depict` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'crm备注',
  `search_engine` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `keyword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `chat_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `search_host` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `spread_flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `first_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `promotion_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类型',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `back_depict` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `finish_depict` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `valid_flag` int(11) DEFAULT NULL COMMENT '是否有效',
  `back_flag` int(11) DEFAULT NULL COMMENT '是否退回',
  `allocation_status` int(11) DEFAULT NULL COMMENT '分配状态',
  `modify_IDENTITY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_staff_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `back_type` int(11) DEFAULT NULL COMMENT '退回类型',
  `allocation_time` datetime(0) DEFAULT NULL,
  `EXPIRED_flag` int(11) DEFAULT NULL COMMENT '是否过期',
  `GLOBAL_STATIC_ID` int(11) DEFAULT NULL COMMENT '全局静态ID',
  `update_staff_id` int(11) DEFAULT NULL COMMENT '编辑用户ID',
  `update_time` datetime(0) DEFAULT NULL COMMENT '编辑时间',
  `back_staff_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '退回用户ID',
  `back_time` datetime(0) DEFAULT NULL COMMENT '退回时间',
  `auto_flag` int(11) DEFAULT NULL,
  `chat_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '对话ID',
  `refer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `country` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `PROVINCE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `site_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `DEVICE_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_agent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备类型',
  `CLICK_TEXT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bd_USER_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bd_CAMPAIGIN_id` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bd_ADGROUND_id` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bd_KEYWORD_id` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bd_CREATIVE_id` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `NTAG_id` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
