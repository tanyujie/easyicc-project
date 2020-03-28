package org.easymis.easyicc.domain.entity;

import java.util.Date;

import lombok.Data;
@Data
public class VisitorFilter {
    private String id; 

    private String orgId; 
    //过滤条件",column="filter",length=255,isnull=true) 
    private String filter; 
    //过滤模式0访客ID1IP地址2地区屏蔽",column="type",length=255,isnull=true) 
    private String type; 
    //备注信息",column="note",length=255,isnull=true) 
    private String note; 
    //过期时间",column="expires",isnull=true) 
    private Date expires; 
    //创建用户",column="staff_id",isnull=true) 
    private String staffId; 
    //创建时间",column="create_time",isnull=true) 
    private Date createTime; 
}
