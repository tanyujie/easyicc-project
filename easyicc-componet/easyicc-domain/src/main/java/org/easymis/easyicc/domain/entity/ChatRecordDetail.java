package org.easymis.easyicc.domain.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ChatRecordDetail implements Serializable {

	  //组织id",column="org_id",length=40,isnull=false) 
      private String orgId; 
      //对话id",column="chat_id",length=40,isnull=false) 
      private String chatId; 
      //客服id",column="recorder_id",length=40,isnull=true) 
      private String recorderId; 
      //发送时间",column="create_time",isnull=false) 
      private Date createTime; 
      //",column="type",isnull=true) 
      private String type; 
      //消息内容",column="message",length=255,isnull=true) 
      private String message; 
      //客服id",column="from_staff_id",length=40,isnull=true) 
      private String fromUserId; 
      //访客id",column="to_user_id",length=40,isnull=true) 
      private String toUserId; 

}