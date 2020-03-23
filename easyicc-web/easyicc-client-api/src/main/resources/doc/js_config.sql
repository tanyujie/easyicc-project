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

 Date: 23/03/2020 15:58:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for js_config
-- ----------------------------
DROP TABLE IF EXISTS `js_config`;
CREATE TABLE `js_config`  (
  `configId` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '代码描述',
  `bindHosts` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '绑定域名',
  `嵌入类型` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '嵌入类型1PC端2移动端',
  `siteId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属子站点',
  `promotionId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '推广渠道',
  `showIcon` int(0) NULL DEFAULT NULL COMMENT '开启模块-网页图标1开启0否',
  `with_monitor` int(0) NULL DEFAULT NULL COMMENT '开启模块-访客监测1开启0否',
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '面板类型',
  `floatPos` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '面板位置0不浮动1屏幕左上边2屏幕右上边3屏幕左下边4屏幕右下边',
  `marginY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上(下)边距',
  `marginX` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '侧边距',
  `panelWhenInvite` int(0) NULL DEFAULT NULL COMMENT '弹出邀请框时隐藏浮动面板 1弹出0否',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '默认图标{iconOnlinePath:在线图片URL,iconOfflinePath:离线图片URL,图片宽度:iconWidth;iconHeight}',
  `iconClose` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关闭按钮{iconCloseWidth,iconCloseHeight,iconCloseTop,iconCloseLeft}',
  `relGroupId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '响应客服分组',
  `monitorType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邀请框类型',
  `invitePos` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邀请框位置',
  `monIndex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '样式选择',
  `inviteText` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邀请框内容',
  `mask` int(0) NULL DEFAULT NULL COMMENT '启用遮罩网页1启用0不启用',
  `autoInvite` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自动弹出邀请框\"-1\"表示不自动弹出邀请框',
  `showInviteAgain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关闭邀请框后弹出\"-1\"表示不再弹出',
  `autoInviteTimes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '弹出邀请框次数\"-1\"表示不限制',
  `autoInviteData` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自动邀请时段autoInviteStart-autoInviteEnd',
  `autoInviteGroupId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '响应客服',
  `phoneDefine` int(0) NULL DEFAULT NULL COMMENT '自定义电话号码',
  `phonePosition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号位置 left左中右',
  `lang` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '语言1浏览器适配2中文3英文',
  `minChat` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对话窗口模式0弹出窗1小窗口',
  `popStyle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'PC弹出窗口风格0自适应尺寸1标准尺寸',
  `autochatBgColor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对话窗口颜色代码',
  `PC小窗口框宽度` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'PC小窗口框宽度{forceChatWidth：宽,forceChatHeight:高}',
  `工具栏设置` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工具栏设置{toolEmoticons表情，toolScreen截图，toolOpinion评价，toolFile文件，toolQuiet静音}',
  `PC展示信息` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'PC展示信息（showCustomerInfo：客服信息，showAboutUs：关于我们}',
  `topImage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '插图(PC大窗口)',
  `topImageMin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '插图(小窗口或移动端)',
  `pageTitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '页面标题',
  `sendkeyWord` int(0) NULL DEFAULT NULL COMMENT '竞价效果优化1启用',
  `searchMode` int(0) NULL DEFAULT NULL COMMENT '竞价智能优化模式',
  `PC小窗口位置` int(0) NULL DEFAULT NULL COMMENT 'PC小窗口位置',
  `phoneMinChatSize` int(0) NULL DEFAULT NULL COMMENT '手机小窗口高度(%)',
  `closeChatBtn` int(0) NULL DEFAULT NULL COMMENT 'PC小窗口关闭按钮1启用',
  `minChatMinBtn` int(0) NULL DEFAULT NULL COMMENT 'PC小窗口最小化按钮1启用',
  `minChatMaskBtn` int(0) NULL DEFAULT NULL COMMENT 'PC小窗口遮罩1启用',
  `autoPopMWinTime` int(0) NULL DEFAULT NULL COMMENT '移动端对话自动弹出次数',
  `autoPopMWinPeroid` int(0) NULL DEFAULT NULL COMMENT '移动端对话自动弹出间隔',
  `firstToRebot` int(0) NULL DEFAULT NULL COMMENT '知识库接待1开启0否',
  `chatStyle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '弹出对话框样式',
  `phoneChatStyle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机对话框样式',
  `userHead` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客服头像(36*36)',
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机端电话',
  `canBackPage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机端允许返回1允许2不允许',
  `phoneInputTop` int(0) NULL DEFAULT NULL COMMENT '手机输入框置顶1置顶0否',
  `offConnectType` int(0) NULL DEFAULT NULL COMMENT '非在线接通类型1留言2机器人',
  `手机端新消息提醒` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机端新消息提醒{popMsgBgColor：背景颜色，popMsgColor：字体颜色，popMsgHead：图标}',
  `popMsgTitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `popMsgOpacity` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '不透明度',
  `autoChat` int(0) NULL DEFAULT NULL COMMENT '启用自动对话1启用0否',
  `autoConnect` int(0) NULL DEFAULT NULL COMMENT '直接建立对话1建立0否',
  `autoChatDelay` int(0) NULL DEFAULT NULL COMMENT '自动对话延迟',
  `autoChatData` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自动对话时段{autoChatStart,autoChatEnd}',
  `autoChatTimes` int(0) NULL DEFAULT NULL COMMENT '自动对话次数\"-1\"表示不限制',
  `autoChatHideMonitor` int(0) NULL DEFAULT NULL COMMENT '弹出小对话框时隐藏邀请框1开启0否',
  `接通欢迎语-对话接通欢迎语(PC大窗口)` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接通欢迎语-对话接通欢迎语(PC大窗口)',
  `对话接通欢迎语(小窗口或移动端)` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对话接通欢迎语(小窗口或移动端)',
  `waitText` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '排队文字',
  `logImageURL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'PC大窗口logo(600*50)',
  `pcMinLogo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'PC小窗口logo(36*36)',
  `logImageLink` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'logo链接',
  `ad2ImageURL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对话界面-下(200*140)',
  `ad2ImageLink` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对话界面-下链接',
  `客服字体` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客服字体{vistFontFamily,vist-font-weight,vist-font-style,vistFontColor}',
  `vist_font` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访客字体{vistFontFamily,vist-font-weight,vist-font-style,vistFontColor}',
  `countCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对话页面统计代码',
  `extCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展代码',
  `probe_text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '匹配文字',
  `probe_group` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接入分组',
  `ocpc_platform` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上报平台-1不上报1自动识别2广点通3今日头条4知乎',
  `ocpc_condition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上报条件',
  `ocpc_config_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广点通配置',
  `最后修改人` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `最后修改时间` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`configId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '网页样式配置' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
