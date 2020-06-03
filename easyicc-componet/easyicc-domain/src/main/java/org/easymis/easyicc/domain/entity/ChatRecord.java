package org.easymis.easyicc.domain.entity;

import java.util.Date;

import lombok.Data;

@Data
public class ChatRecord {

	 private String chatId; 
     //组织id",column="org_id",length=40,isnull=true) 
     private String orgId; 
     //站点id",column="site_id",length=0,isnull=true) 
     private String siteId; 
     //创建日期",column="create_time",isnull=true) 
     private Date createTime; 
     //结束时间",column="end_time",isnull=true) 
     private Date endTime; 
     //停留时间",column="span",isnull=true) 
     private Integer span; 
     //访客id",column="visitor_id",length=40,isnull=true) 
     private String visitorId; 
     //客服id",column="staff_id",length=40,isnull=true) 
     private String staffId; 
     //备注",column="depict",length=255,isnull=true) 
     private String depict; 
     //",column="visitor_static_id",length=40,isnull=true) 
     private String visitorStaticId; 
     //邀请模式",column="invite_mode",isnull=true) 
     private Integer inviteMode; 
     //",column="effective",isnull=true) 
     private Integer effective; 
     //消息记录条数",column="message_count",isnull=true) 
     private Integer messageCount; 
     //",column="summarize",length=255,isnull=true) Schc123456.
     private String summarize; 
     //",column="all_time",isnull=true) 
     private Integer allTime; 
     //员工消息条数",column="staff_message_count",isnull=true) 
     private Integer staffMessageCount; 
     //访客消息条数",column="visitor_message_count",isnull=true) 
     private Integer visitorMessageCount; 
     //访客城市",column="visitor_location_city",length=255,isnull=true) 
     private String visitorLocationCity; 
     //访客省",column="visitor_location_province",length=255,isnull=true) 
     private String visitorLocationProvince; 
     //访客国家",column="visitor_location_country",length=255,isnull=true) 
     private String visitorLocationCountry; 
     //访客ip",column="visitor_ip",length=255,isnull=true) 
     private String visitorIp; 
     //访客电话",column="visitor_phone",length=255,isnull=true) 
     private String visitorPhone; 
     //访客qq",column="visitor_qq",length=255,isnull=true) 
     private String visitorQq; 
     //接入渠道id",column="promotion_id",length=40,isnull=true) 
     private String promotionId; 
     //最初访问页面",column="first_active_url",length=255,isnull=true) 
     private String firstActiveUrl; 
     //离开页面",column="last_active_url",length=255,isnull=true) 
     private String lastActiveUrl; 
     //访问来源",column="refer",length=255,isnull=true) 
     private String refer; 
     //搜索引擎",column="search_engine",length=255,isnull=true) 
     private String searchEngine; 
     //搜索词",column="search_word",length=255,isnull=true) 
     private String searchWord; 
     //关键词",column="keyword",length=255,isnull=true) 
     private String keyword; 
     //来访总次数",column="view_page_count",isnull=true) 
     private Integer viewPageCount; 
     //留言",column="leave_message_count",isnull=true) 
     private Integer leaveMessageCount; 
     //访客姓名",column="name",length=255,isnull=true) 
     private String name; 
     //访客手机",column="mobile",length=255,isnull=true) 
     private String mobile; 
     //邀请次数",column="invite_total",isnull=true) 
     private Integer inviteTotal; 
     //会话转移来源会话ID",column="trans_from_chat_id",length=50,isnull=true) 
     private String transFromChatId; 
     //客服首次回复时间",column="staff_first_reply_time",isnull=true) 
     private Date staffFirstReplyTime; 
     //最后一条消息",column="last_message",length=255,isnull=true) 
     private String lastMessage; 
     //会话设备类型",column="chat_type",isnull=true) 
     private Integer chatType; 
     //浏览器",column="browser",length=255,isnull=true) 
     private String browser; 
     //发起方式",column="open_way",isnull=true) 
     private Integer openWay; 
     //结束方式",column="end_way",isnull=true) 
     private Integer endWay; 
     //连接状态",column="connected",isnull=true) 
     private Boolean connected; 
}
