package org.easymis.easyicc.domain.vo;

import java.util.List;

import lombok.Data;
@Data
public class StaffOnlineTreeVo {
	private String staffId;//员工Id
	private String name;//员工姓名
	private Integer status;//1上线2忙碌3离开4离线
	private List<ChatVisitorVo> chatOnlineList;//对话中访客
	private List<ChatVisitorVo> chatInviteList;//邀请中访客
}
