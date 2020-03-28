package org.easymis.easyicc.domain.entity;

import lombok.Data;
@Data
public class SkillGroup {
    
    private String id; 
    
    private String orgId; 
    //客服分组名称",column="group_name",length=255,isnull=true) 
    private String groupName; 
    //人员分配算法1随机2轮循3空闲率4队列5对话在线时间比6对话量权重在线时间比7等级优先分配算法",column="order_type",isnull=false) 
    private Integer orderType; 
    //是否启用排队0关闭1启用",column="use_queue",isnull=false) 
    private Integer useQueue; 
    //最大排队人数",column="max_queue_size",isnull=false) 
    private Integer maxQueueSize; 
    //排队访客分配规则1客服手动接入2系统自动分配 ",column="auto_distribute",isnull=true) 
    private Integer autoDistribute; 
    //忙线溢出组",column="overflow_group_id",length=255,isnull=true) 
    private String overflowGroupId; 
    //排队溢出时间",column="overflow_queue_time",isnull=false) 
    private Integer overflowQueueTime; 
    //排序号",column="priority",isnull=true) 
    private Integer priority; 
    //描述",column="depict",length=255,isnull=true) 
    private String depict; 
    //客服人员",column="staff_ids",length=4000,isnull=true) 
    private String staffIds; 
}
