package org.easymis.easyicc.domain.entity;

import lombok.Data;

//@Table(name = "js_card_weixin_info")
@Data
public class WechatInfo {
	private String id;
	private String userId;
	
	/**
	 * openid
	 */
	//@Column(name = "open_id", nullable = true)
	private String openid;
	
	/**
	 * 用户名
	 */
	//@Column(name = "user_name", nullable = true)
	private String userName;
	
	/**
	 * 微信昵称
	 */
	//@Column(name = "nick_name", nullable = true)
	private String nickName;
	
	/**
	 * 状态0：关闭,1：启用
	 */
	//@Column(name = "status", nullable = false)
	private Integer status;
	
	/**
	 * 发送时机: allocation_after,名片分配后，allocation_before，名片分配前
	 */
	//@Column(name = "send_opportunity", nullable = false)
	private String sendOpportunity;
	
	/**
	 * 公司id
	 */
	//@Column(name = "company_id", nullable = false)
	private String orgId;
	
	/**
	 * 公司name
	 */
	//@Column(name = "company_name", nullable = false)
	private String companyName;
	
	/**
	 * 是否删除
	 */
	//@Column(name = "is_delete", nullable = false)
	private int deleteFlag;
}
