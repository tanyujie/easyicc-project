package org.easymis.easyicc.domain.entity;

import java.util.Date;

import lombok.Data;
@Data
public class RobotQuestionCategory {
    private String categoryId; 

    private String orgId; 
    //机器人id",column="rebot_id",length=40,isnull=true) 
    private String rebotId; 
    //语料库名称",column="category_name",length=255,isnull=true) 
    private String categoryName; 
    //分类描述",column="depict",length=255,isnull=true) 
    private String depict; 
    //创建时间",column="create_time",isnull=true) 
    private Date createTime; 
    //修改时间",column="update_time",isnull=true) 
    private Date updateTime; 
}
