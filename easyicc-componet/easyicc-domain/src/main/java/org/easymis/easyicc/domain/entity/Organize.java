package org.easymis.easyicc.domain.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Organize implements Serializable {

    //",column="org_id",id=true,length=40,isnull=false) 
    private String orgId; 
    //组织编号",column="org_no",length=40,isnull=false) 
    private String orgNo; 
    //组织名称",column="org_name",length=255,isnull=false) 
    private String orgName; 
    //上级组织",column="parent_id",length=40,isnull=true) 
    private String parentId; 
    //联系人|拥有者|负责人id|关联会员id",column="owner_id",length=40,isnull=true) 
    private String ownerId; 
    //",column="contact",length=50,isnull=true) 
    private String contact; 
    //联系手机",column="mobile",length=20,isnull=true) 
    private String mobile; 
    //创建时间|注册时间",column="create_time",isnull=false) 
    private Date createTime; 
    //启用时间",column="start_date",isnull=true) 
    private Date startDate; 
    //试用结束日期",column="trial_finish_time",isnull=true) 
    private Date trialFinishTime; 
    //",column="country_id",length=40,isnull=true) 
    private String countryId; 
    //",column="province_id",length=40,isnull=true) 
    private String provinceId; 
    //",column="city_id",length=40,isnull=true) 
    private String cityId; 
    //",column="district_id",length=40,isnull=true) 
    private String districtId; 
    //注册地址",column="register_address",length=255,isnull=true) 
    private String registerAddress; 
    //办公地址",column="office_address",length=255,isnull=true) 
    private String officeAddress; 
    //联系电话",column="phone",length=255,isnull=true) 
    private String phone; 
    //",column="fax",length=255,isnull=true) 
    private String fax; 
    //",column="zip",length=255,isnull=true) 
    private String zip; 
    //该组织最多支持用户",column="total_staff",isnull=true) 
    private Integer totalStaff; 
    //该组织已共享用户数|已使用用户数",column="used_staff",isnull=true) 
    private Integer usedStaff; 
    //启用状态1启用0停用",column="status",isnull=false) 
    private Boolean status; 
    //",column="depict",length=1000,isnull=true) 
    private String depict; 
    //统一社会信用代码",column="credit_code",length=40,isnull=true) 
    private String creditCode; 
    //企业法人",column="legal_person",length=40,isnull=true) 
    private String legalPerson; 
    //注册资本",column="registered_capital",length=255,isnull=true) 
    private String registeredCapital; 
    //经营范围",column="business_scope",length=255,isnull=true) 
    private String businessScope; 
    //联系人邮箱",column="email",length=50,isnull=true) 
    private String email; 
    //是否上市",column="ipo",length=255,isnull=true) 
    private String ipo; 
    //股票代码",column="stock_code",length=255,isnull=true) 
    private String stockCode; 
    //银行账号",column="bank_no",length=40,isnull=true) 
    private String bankNo; 
    //开户行",column="bank_name",length=255,isnull=true) 
    private String bankName; 
    //网站",column="url",length=255,isnull=true) 
    private String url; 
    //博客",column="blog",length=255,isnull=true) 
    private String blog; 
    //",column="level1_industry_id",length=40,isnull=true) 
    private String level1IndustryId; 
    //",column="level2_industry_id",length=40,isnull=true) 
    private String level2IndustryId; 
    //",column="level3_industry_id",length=40,isnull=true) 
    private String level3IndustryId; 
    //",column="level4_industry_id",length=40,isnull=true) 
    private String level4IndustryId; 
    //",column="industry_id",length=255,isnull=true) 
    private String industryId; 
    //",column="qq",length=20,isnull=true) 
    private String qq; 
    //",column="we_chat",length=40,isnull=true) 
    private String weChat; 
    //",column="update_id",length=40,isnull=true) 
    private String updateId; 
    //",column="update_time",isnull=true) 
    private Date updateTime; 
    //删除状态1正常2删除|作废",column="delete_flag",isnull=true) 
    private Integer deleteFlag; 
    //",column="delete_id",length=40,isnull=true) 
    private String deleteId; 
    //",column="delete_time",isnull=true) 
    private Date deleteTime; 
    //锁定状态1锁定2解锁",column="lock_flag",isnull=false) 
    private Integer lockFlag; 
    //IP黑白名单设置1关闭2开启白名单3开启黑名单",column="ip_gateway",isnull=false) 
    private Integer ipGateway; 

}