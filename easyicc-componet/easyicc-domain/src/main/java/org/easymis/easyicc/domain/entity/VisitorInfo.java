package org.easymis.easyicc.domain.entity;

import java.util.Date;

import lombok.Data;

@Data
public class VisitorInfo {
    private String id; 

    private String visitorStaticId; 
    //公司ID",column="org_id",length=40,isnull=true) 
    private String orgId; 
    //名称",column="name",length=255,isnull=true) 
    private String name; 
    //电话",column="phone",length=255,isnull=true) 
    private String phone; 
    //邮箱",column="email",length=255,isnull=true) 
    private String email; 
    //笔记",column="note",length=255,isnull=true) 
    private String note; 
    //密钥",column="reserve_key",length=255,isnull=true) 
    private String reserveKey; 
    //性别",column="sex",length=255,isnull=true) 
    private String sex; 
    //代表",column="represent",length=255,isnull=true) 
    private String represent; 
    //电话",column="mobile",length=255,isnull=true) 
    private String mobile; 
    //QQ",column="qq",length=255,isnull=true) 
    private String qq; 
    //微信",column="wechat",length=255,isnull=true) 
    private String wechat; 
    //",column="url",length=255,isnull=true) 
    private String url; 
    //公司名称",column="company_name",length=255,isnull=true) 
    private String companyName; 
    //地址",column="area",length=255,isnull=true) 
    private String area; 
    //创建者",column="create_id",length=40,isnull=true) 
    private String createId; 
    //创建时间",column="create_time",isnull=true) 
    private Date createTime; 
    //crm状态",column="crm_state",isnull=true) 
    private Integer crmState; 
    //crm备注",column="crm_depict",length=255,isnull=true) 
    private String crmDepict; 
    //搜索引擎",column="search_engine",length=255,isnull=true) 
    private String searchEngine; 
    //关键字",column="keyword",length=255,isnull=true) 
    private String keyword; 
    //对话来源url",column="chat_url",length=255,isnull=true) 
    private String chatUrl; 
    //",column="search_host",length=255,isnull=true) 
    private String searchHost; 
    //",column="spread_flag",length=255,isnull=true) 
    private String spreadFlag; 
    //",column="first_url",length=255,isnull=true) 
    private String firstUrl; 
    //类型",column="promotion_id",length=40,isnull=true) 
    private String promotionId; 
    //",column="type",length=255,isnull=true) 
    private String type; 
    //退回描述",column="back_depict",length=255,isnull=true) 
    private String backDepict; 
    //",column="finish_depict",length=255,isnull=true) 
    private String finishDepict; 
    //是否有效",column="valid_flag",isnull=true) 
    private Integer validFlag; 
    //是否退回",column="back_flag",isnull=true) 
    private Integer backFlag; 
    //分配状态",column="allocation_status",isnull=true) 
    private Integer allocationStatus; 
    //",column="modify_identity",length=40,isnull=true) 
    private String modifyIdentity; 
    //退回类型",column="back_type",isnull=true) 
    private Integer backType; 
    //",column="allocation_time",isnull=true) 
    private Date allocationTime; 
    //是否过期",column="expired_flag",isnull=true) 
    private Integer expiredFlag; 
    //全局静态ID",column="global_static_id",length=40,isnull=true) 
    private String globalStaticId; 
    //",column="update_id",length=0,isnull=true) 
    private String updateId; 
    //编辑时间",column="update_time",isnull=true) 
    private Date updateTime; 
    //退回用户ID",column="back_staff_id",length=40,isnull=true) 
    private String backStaffId; 
    //退回时间",column="back_time",isnull=true) 
    private Date backTime; 
    //",column="auto_flag",isnull=true) 
    private Integer autoFlag; 
    //对话ID",column="chat_id",length=40,isnull=true) 
    private String chatId; 
    //",column="refer",length=255,isnull=true) 
    private String refer; 
    //国家",column="country",length=255,isnull=true) 
    private String country; 
    //省",column="province",length=255,isnull=true) 
    private String province; 
    //城市",column="city",length=255,isnull=true) 
    private String city; 
    //站点",column="site_id",length=40,isnull=true) 
    private String siteId; 
    //",column="device_type",length=255,isnull=true) 
    private String deviceType; 
    //设备类型",column="user_agent",length=255,isnull=true) 
    private String userAgent; 
    //",column="click_text",length=255,isnull=true) 
    private String clickText; 
    //",column="bd_staff_id",length=40,isnull=true) 
    private String bdStaffId; 
    //",column="bd_campaign_id",length=0,isnull=true) 
    private String bdCampaignId; 
    //",column="bd_adground_id",length=0,isnull=true) 
    private String bdAdgroundId; 
    //",column="bd_keyword_id",length=0,isnull=true) 
    private String bdKeywordId; 
    //",column="bd_creative_id",length=0,isnull=true) 
    private String bdCreativeId; 
    //",column="ntag_id",length=0,isnull=true) 
    private String ntagId; 
}
