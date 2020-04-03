package org.easymis.easyicc.domain.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ChatRecordDetail implements Serializable {

	private String recorderId;//RECORDER_ID
	private Date createTime; 
	private String chatId;
	private String message;
	private String fromId;
	private Integer type;
	private String orgId;

}