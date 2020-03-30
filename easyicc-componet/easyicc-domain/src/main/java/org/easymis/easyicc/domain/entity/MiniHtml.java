package org.easymis.easyicc.domain.entity;
import java.io.Serializable;
import java.util.Date;

import lombok.Data; 
 
  
  
@Data 
 public class MiniHtml implements Serializable{  
     
      private String id; 
      
      private String orgId; 
      //监测代码",column="js_config_id",length=40,isnull=true) 
      private String jsConfigId; 
      //注册表单",column="form_id",length=40,isnull=true) 
      private String formId; 
      //",column="template_name",length=255,isnull=true) 
      private String templateName; 
      //标题",column="title",length=255,isnull=true) 
      private String title; 
      //免费电话",column="free_phone",length=255,isnull=true) 
      private String freePhone; 
      //内容",column="content",length=255,isnull=true) 
      private String content; 
      //二维码图片地址",column="quick_mark_url",length=255,isnull=true) 
      private String quickMarkUrl; 
      //对话分组",column="group_id",length=40,isnull=true) 
      private String groupId; 
      //创建人",column="create_id",length=40,isnull=true) 
      private String createId; 
      //",column="create_time",isnull=true) 
      private Date createTime; 
     
}