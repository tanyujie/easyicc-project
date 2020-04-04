package org.easymis.easyicc.domain.vo;

import java.util.Date;

import lombok.Data;

@Data
public class LeaveMessageVo {
	private String REALNAME;//客服名称
	private Date createTime;//创建时间
	private String CRM_STATE;//我的CRM
	private String VST_NAME;//访客姓名
	private String VST_PHONE;//手机
	private String VST_Email;//访客邮件
	private String message;//留言内容
	private String SEARCH_HOST;//来源页面
	private String chatUrl;//对话页面
	private String firstUrl;//落地页面
}
