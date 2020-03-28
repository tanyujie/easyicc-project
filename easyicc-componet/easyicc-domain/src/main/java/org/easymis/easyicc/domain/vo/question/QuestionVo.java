package org.easymis.easyicc.domain.vo.question;

import lombok.Data;

@Data
public class QuestionVo {
	//输入类型:0-文本(默认)、1-图片、2-音频
	private Integer requestType;
	private Perception perception;
	private UserInfo userInfo;
}
