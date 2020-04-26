package org.easymis.easyicc.domain.entity;
import java.io.Serializable;
import java.util.Date;

import lombok.Data; 
 
  
  
@Data 
 public class ChatStaffMessage implements Serializable{  

      private String id; 

      private String orgId; 

      private String sendId; 

      private String acceptId; 
 
      private String type; 

      private String message; 

      private Integer signFlag; 

      private Date createTime; 
     
}