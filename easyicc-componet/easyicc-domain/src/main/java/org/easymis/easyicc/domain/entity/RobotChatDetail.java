package org.easymis.easyicc.domain.entity;
import java.io.Serializable;
import java.util.Date;

import lombok.Data; 
 
  
  
@Data
public class RobotChatDetail implements Serializable {

	private String id;

	private String chatId;

	private String orgId;

	private String robotId;
	// 语料库id",column="question_id",length=40,isnull=true)
	private String questionId;
	// 问题",column="question",length=255,isnull=true)
	private String question;
	// 回答",column="answer",length=255,isnull=true)
	private String answer;
	// 响应功能类型",column="system_category_id",length=40,isnull=true)
	private String systemCategoryId;
	// 创建时间",column="create_time",isnull=true)
	private Date createTime;

}