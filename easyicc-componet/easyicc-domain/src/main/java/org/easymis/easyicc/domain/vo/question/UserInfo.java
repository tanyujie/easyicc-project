package org.easymis.easyicc.domain.vo.question;

import lombok.Data;

@Data
public class UserInfo {
	private String apiKey;//机器人标识
	private String userId;//用户唯一标识
	//群聊唯一标识
	private String groupId;
	//群内用户昵称
	private String userIdName;

}
