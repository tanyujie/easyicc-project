package org.easymis.easyicc.web.robot.bean;

import lombok.Data;

@Data
public class ChatVo {
	private Integer requestType;
	private Perception perception;
	private UserInfo userInfo;
}
