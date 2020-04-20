package org.easymis.easyicc.domain.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class IccAccount implements Serializable {
	// id",id=true,length=40,isnull=false)
	private String id;
	// org_id",length=40,isnull=true)
	private String orgId;
	// member_id",length=40,isnull=true)
	private String memberId;
	// 对应员工id",column="staff_id",length=40,isnull=true)
	private String staffId;
	// 客服ID",column="code",length=40,isnull=true)
	private String code;
	// 对内显示名称",column="nick",length=255,isnull=true)
	private String nick;
	// 对外显示名称",column="nick_name",length=255,isnull=true)
	private String nickName;
	// 登录开始时间",column="login_start_time",isnull=true)
	private Date loginStartTime;
	// 登录结束时间",column="login_end_time",isnull=true)
	private Date loginEndTime;
	// 登录时间限制0不限制1限制",column="filter_time",isnull=true)
	private Boolean filterTime;
	// 客服最大接待量",column="max_accept_count",isnull=true)
	private Integer maxAcceptCount;
	// 备注信息",column="max_chat_count",isnull=true)
	private Integer maxChatCount;
	// 客服等级",column="rank",length=255,isnull=true)
	private String accountRank;
	// 账号类型",column="account_type",isnull=true)
	private Integer accountType;
	// 客服IP",column="login_ip",length=255,isnull=true)
	private String loginIp;
	// 客服MAC地址",column="login_mac",length=255,isnull=true)
	private String loginMac;
	// 客服头像URL",column="head_url",length=255,isnull=true)
	private String headUrl;
	// 备注信息",column="depict",length=255,isnull=true)
	private String depict;

}