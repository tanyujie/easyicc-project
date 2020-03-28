package org.easymis.easyicc.domain.entity;

import java.util.Date;

import lombok.Data;

@Data
public class RobotQuestion {
    //",column="id",id=true,length=40,isnull=false) 
    private String id; 
    //组织id",column="org_id",length=40,isnull=false) 
    private String orgId; 
    //机器人id",column="robot_id",length=40,isnull=false) 
    private String robotId; 
    //语料库",column="sort_id",length=40,isnull=true) 
    private String sortId; 
    //问题",column="question",length=255,isnull=true) 
    private String question; 
    //答案类型|文本(text);连接(url);音频(voice);视频(video);图片(image);图文(news)",column="result_type",length=255,isnull=true) 
    private String resultType; 
    //答案",column="answer",length=255,isnull=true) 
    private String answer; 
    //状态1停用0启用",column="locked_flag",isnull=true) 
    private Boolean lockedFlag; 
    //创建人",column="create_id",length=0,isnull=true) 
    private String createId; 
    //创建时间",column="create_time",isnull=true) 
    private Date createTime; 
    //修改人",column="update_id",length=0,isnull=true) 
    private String updateId; 
    //修改时间",column="update_time",isnull=true) 
    private Date updateTime; 
}
