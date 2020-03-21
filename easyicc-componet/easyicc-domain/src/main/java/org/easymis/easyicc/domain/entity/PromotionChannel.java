package org.easymis.easyicc.domain.entity;

import lombok.Data;

@Data
public class PromotionChannel {
    private String id; 

    private String orgId; 
    //"渠道名称",column="channel_name",length=255,isnull=true) 
    private String channelName; 
    //"匹配方式0根据嵌入代码配置1根据来源地址2根据落地页地址",column="match_type",isnull=true) 
    private Integer matchType; 
    //"渠道描述",column="depict",length=255,isnull=true) 
    private String depict; 
    //"匹配算法-1无0包含1以...开头2正则表达式",column="match_rule",isnull=true) 
    private Integer matchRule; 
    //"匹配内容",column="match_content",length=255,isnull=true) 
    private String matchContent; 
    //"状态0停用1启用2删除",column="status",isnull=true) 
    private Integer status; 
}
