package org.easymis.easyicc.domain.entity;
import java.io.Serializable;

import lombok.Data; 
 
  
  
@Data  
public class Robot implements Serializable {

    private String robotId; 

    private String orgId; 
    //名字",column="name",length=255,isnull=true) 
    private String name; 
    //应用终端",column="clients",length=255,isnull=true) 
    private String clients; 
    //应用行业",column="industry",length=255,isnull=true) 
    private String industry; 
    //应用场景",column="scene",length=255,isnull=true) 
    private String scene; 
    //机器人简介",column="depict",length=255,isnull=true) 
    private String depict; 
    //照片",column="avatar",length=255,isnull=true) 
    private String avatar; 
    //性别1男2女",column="sex",isnull=true) 
    private Integer sex; 
    //年龄",column="age",isnull=true) 
    private Integer age; 
    //星座",column="constellation",length=255,isnull=true) 
    private String constellation; 
    //妈妈是谁",column="mother",length=255,isnull=true) 
    private String mother; 
    //爸爸谁",column="father",length=255,isnull=true) 
    private String father; 
    //万金油回复",column="unknown_reply",length=255,isnull=true) 
    private String unknownReply; 
    //小尾巴",column="tail",length=255,isnull=true) 
    private String tail; 
    //关键词过滤1启用0否",column="keyword_flag",isnull=true) 
    private Boolean keywordFlag; 
    //关键词",column="keyword",length=255,isnull=true) 
    private String keyword; 
    //关键词回复",column="keyword_reply",length=255,isnull=true) 
    private String keywordReply; 
    //apiKey",column="api_key",length=255,isnull=true) 
    private String apiKey; 
    //密钥",column="secret_key",length=255,isnull=true) 
    private String secretKey; 
    //机器人问候语",column="welcome_message",length=100,isnull=true) 
    private String welcomeMessage; 
    //机器人未知问题回答语",column="unknow_message",length=100,isnull=true) 
    private String unknowMessage; 

}