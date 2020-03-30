package org.easymis.easyicc.domain.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class JsConfig implements Serializable {

	private String configId;

	private String orgId;
	// 代码描述",column="description",length=255,isnull=true)
	private String description;
	// 绑定域名",column="bind_hosts",length=255,isnull=true)
	private String bindHosts;
	// 嵌入类型1PC端2移动端",column="device_type",length=255,isnull=true)
	private String deviceType;
	// 所属子站点",column="site_id",length=255,isnull=true)
	private String siteId;
	// 推广渠道",column="promotion_id",length=255,isnull=true)
	private String promotionId;
	// 开启模块-网页图标1开启0否",column="showIcon",isnull=true)
	private Integer showIcon;
	// 开启模块-访客监测1开启0否",column="with_monitor",isnull=true)
	private Integer withMonitor;
	// 面板类型",column="category",length=255,isnull=true)
	private String category;
	// 面板位置0不浮动1屏幕左上边2屏幕右上边3屏幕左下边4屏幕右下边",column="floatPos",length=255,isnull=true)
	private String floatPos;
	// 上(下)边距",column="margin_y",length=255,isnull=true)
	private String marginY;
	// 侧边距",column="margin_x",length=255,isnull=true)
	private String marginX;
	// 弹出邀请框时隐藏浮动面板 1弹出0否",column="panel_when_invite",isnull=true)
	private Integer panelWhenInvite;
	// 默认图标{iconOnlinePath:在线图片URL,iconOfflinePath:离线图片URL,图片宽度:iconWidth;iconHeight}",column="icon",length=255,isnull=true)
	private String icon;
	// 关闭按钮{iconCloseWidth,iconCloseHeight,iconCloseTop,iconCloseLeft}",column="icon_close",length=255,isnull=true)
	private String iconClose;
	// 响应客服分组",column="rel_group_id",length=255,isnull=true)
	private String relGroupId;
	// 邀请框类型",column="monitor_type",length=255,isnull=true)
	private String monitorType;
	// 邀请框位置",column="invite_pos",length=255,isnull=true)
	private String invitePos;
	// 样式选择",column="mon_index",length=255,isnull=true)
	private String monIndex;
	// 邀请框内容",column="invite_text",length=255,isnull=true)
	private String inviteText;
	// 启用遮罩网页1启用0不启用",column="mask",isnull=true)
	private Integer mask;
	// 自动弹出邀请框-1表示不自动弹出邀请框",column="auto_invite",length=255,isnull=true)
	private String autoInvite;
	// 关闭邀请框后弹出-1表示不再弹出",column="show_invite_again",length=255,isnull=true)
	private String showInviteAgain;
	// 弹出邀请框次数-1表示不限制",column="auto_invite_times",length=255,isnull=true)
	private String autoInviteTimes;
	// 自动邀请时段autoInviteStart-autoInviteEnd",column="auto_invite_data",length=255,isnull=true)
	private String autoInviteData;
	// 响应客服",column="auto_invite_group_id",length=255,isnull=true)
	private String autoInviteGroupId;
	// 自定义电话号码",column="phone_define",isnull=true)
	private Integer phoneDefine;
	// 手机号位置 left左中右",column="phone_position",length=255,isnull=true)
	private String phonePosition;
	// 语言1浏览器适配2中文3英文",column="lang",length=255,isnull=true)
	private String lang;
	// 对话窗口模式0弹出窗1小窗口",column="min_chat",length=255,isnull=true)
	private String minChat;
	// PC弹出窗口风格0自适应尺寸1标准尺寸",column="pop_style",length=255,isnull=true)
	private String popStyle;
	// 对话窗口颜色代码",column="autochat_bg_color",length=255,isnull=true)
	private String autochatBgColor;
	// PC小窗口框宽度{forceChatWidth：宽,forceChatHeight:高}",column="pc_mini_window",length=255,isnull=true)
	private String pcMiniWindow;
	// 工具栏设置{toolEmoticons表情，toolScreen截图，toolOpinion评价，toolFile文件，toolQuiet静音}",column="toolbar_set",length=255,isnull=true)
	private String toolbarSet;
	// PC展示信息（showCustomerInfo：客服信息，showAboutUs：关于我们}",column="pc_message",length=255,isnull=true)
	private String pcMessage;
	// 插图(PC大窗口)",column="top_image",length=255,isnull=true)
	private String topImage;
	// 插图(小窗口或移动端)",column="top_image_min",length=255,isnull=true)
	private String topImageMin;
	// 页面标题",column="page_title",length=255,isnull=true)
	private String pageTitle;
	// 竞价效果优化1启用",column="send_keyword",isnull=true)
	private Integer sendKeyword;
	// 竞价智能优化模式",column="search_mode",isnull=true)
	private Integer searchMode;
	// PC小窗口位置",column="pc_mini_window_position",isnull=true)
	private Integer pcMiniWindowPosition;
	// 手机小窗口高度(%)",column="phone_minchat_size",isnull=true)
	private Integer phoneMinchatSize;
	// PC小窗口关闭按钮1启用",column="min_chat_close_button",isnull=true)
	private Integer minChatCloseButton;
	// PC小窗口最小化按钮1启用",column="min_chat_min_button",isnull=true)
	private Integer minChatMinButton;
	// PC小窗口遮罩1启用",column="min_chat_mask_button",isnull=true)
	private Integer minChatMaskButton;
	// 移动端对话自动弹出次数",column="auto_pop_Win_Time",isnull=true)
	private Integer autoPopWinTime;
	// 移动端对话自动弹出间隔",column="auto_pop_Win_Peroid",isnull=true)
	private Integer autoPopWinPeroid;
	// 知识库接待1开启0否",column="first_to_rebot",isnull=true)
	private Integer firstToRebot;
	// 弹出对话框样式",column="chat_style",length=255,isnull=true)
	private String chatStyle;
	// 手机对话框样式",column="phone_chat_style",length=255,isnull=true)
	private String phoneChatStyle;
	// 客服头像(36*36)",column="staff_head",length=255,isnull=true)
	private String staffHead;
	// 手机端电话",column="mobile",length=255,isnull=true)
	private String mobile;
	// 手机端允许返回1允许2不允许",column="can_back_page",length=255,isnull=true)
	private String canBackPage;
	// 手机输入框置顶1置顶0否",column="phone_input_top",isnull=true)
	private Integer phoneInputTop;
	// 非在线接通类型1留言2机器人",column="off_connect_type",isnull=true)
	private Integer offConnectType;
	// 手机端新消息提醒{popMsgBgColor：背景颜色，popMsgColor：字体颜色，popMsgHead：图标}",column="mobile_message_remind",length=255,isnull=true)
	private String mobileMessageRemind;
	// 标题",column="pop_msg_title",length=255,isnull=true)
	private String popMsgTitle;
	// 不透明度",column="pop_msg_opacity",length=255,isnull=true)
	private String popMsgOpacity;
	// 启用自动对话1启用0否",column="auto_chat",isnull=true)
	private Integer autoChat;
	// 直接建立对话1建立0否",column="auto_connect",isnull=true)
	private Integer autoConnect;
	// 自动对话延迟",column="auto_chatDelay",isnull=true)
	private Integer autoChatDelay;
	// 自动对话时段{autoChatStart,autoChatEnd}",column="auto_chatData",length=255,isnull=true)
	private String autoChatData;
	// 自动对话次数-1表示不限制",column="auto_chat_times",isnull=true)
	private Integer autoChatTimes;
	// 弹出小对话框时隐藏邀请框1开启0否",column="auto_chat_hide_monitor",isnull=true)
	private Integer autoChatHideMonitor;
	// 接通欢迎语-对话接通欢迎语(PC大窗口)",column="pc_welcome_message",length=255,isnull=true)
	private String pcWelcomeMessage;
	// 对话接通欢迎语(小窗口或移动端)",column="mobile_welcome_message",length=255,isnull=true)
	private String mobileWelcomeMessage;
	// 排队文字",column="wait_text",length=255,isnull=true)
	private String waitText;
	// PC大窗口logo(600*50)",column="log_image_url",length=255,isnull=true)
	private String logImageUrl;
	// PC小窗口logo(36*36)",column="pc_min_logo",length=255,isnull=true)
	private String pcMinLogo;
	// logo链接",column="log_image_link",length=255,isnull=true)
	private String logImageLink;
	// 对话界面-下(200*140)",column="ad2_image_url",length=255,isnull=true)
	private String ad2ImageUrl;
	// 对话界面-下链接",column="ad2_image_link",length=255,isnull=true)
	private String ad2ImageLink;
	// 客服字体{vistFontFamily,vist-font-weight,vist-font-style,vistFontColor}",column="staff_font",length=255,isnull=true)
	private String staffFont;
	// 访客字体{vistFontFamily,vist-font-weight,vist-font-style,vistFontColor}",column="vist_font",length=255,isnull=true)
	private String vistFont;
	// 对话页面统计代码",column="count_code",length=255,isnull=true)
	private String countCode;
	// 扩展代码",column="ext_code",length=255,isnull=true)
	private String extCode;
	// 匹配文字",column="probe_text",length=255,isnull=true)
	private String probeText;
	// 接入分组",column="probe_group",length=255,isnull=true)
	private String probeGroup;
	// 上报平台-1不上报1自动识别2广点通3今日头条4知乎",column="ocpc_platform",length=255,isnull=true)
	private String ocpcPlatform;
	// 上报条件",column="ocpc_condition",length=255,isnull=true)
	private String ocpcCondition;
	// 广点通配置",column="ocpc_config_id",length=255,isnull=true)
	private String ocpcConfigId;
	// 修改人",column="update_id",length=255,isnull=true)
	private String updateId;
	// 修改时间",column="update_time",isnull=true)
	private Date updateTime;

}