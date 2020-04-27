package org.easymis.easyicc.domain.entity;

import java.util.Date;

import lombok.Data;

@Data
public class OrganizeConfig {
	private String orgId;
	// 展示转接信息给访客", column = "show_transfer_message", isnull = false)
	private Integer showTransferMessage;
	// 预知访客输入1 显示 0不显示", column = "spending_type", isnull = false)
	private Integer spendingType;
	// 结束对话弹出访客评价1 弹出0不弹出", column = "server_type", length = 255, isnull = true)
	private String serverType;
	// 显示机器人", column = "show_robot", length = 255, isnull = true)
	private String showRobot;
	// 聊天窗口保存记录", column = "save_record", length = 255, isnull = true)
	private String saveRecord;
	// 机器人显示名称", column = "robot_name", length = 255, isnull = true)
	private String robotName;
	// 机器人欢迎语", column = "robot_welcome_message", length = 255, isnull = true)
	private String robotWelcomeMessage;
	// 机器人不懂提示语", column = "robot_unknown_message", length = 255, isnull = true)
	private String robotUnknownMessage;
	// 修改时间", column = "modify_time", isnull = true)
	private Date modifyTime;
	// 客户超时响应时间", column = "customer_timeout", isnull = true)
	private Integer customerTimeout;
	// 对话结束语", column = "chat_close_message", length = 255, isnull = true)
	private String chatCloseMessage;
	// 对话转移语", column = "chat_transfer_message", length = 255, isnull = true)
	private String chatTransferMessage;
	// 访客消息关键字过滤", column = "chat_keyword_filter", length = 255, isnull = true)
	private String chatKeywordFilter;
	// 手机号超时隐藏1 开启 0 关闭 ", column = "phone_overtime_switch", isnull = true)
	private Integer phoneOvertimeSwitch;
	// 对话是否使用验证码1 使用 0不使用", column = "validate", isnull = true)
	private Integer validate;
	// 同一访客建立一通对话：1使用0 不使用 ", column = "only_chat", isnull = true)
	private Integer onlyChat;
	// 留言分配方式1 分配到座席 ", column = "leave_message_type", length = 255, isnull = true)
	private String leaveMessageType;
	// 访客电话验证", column = "visitor_phone_verification", length = 255, isnull = true)
	private String visitorPhoneVerification;
}
