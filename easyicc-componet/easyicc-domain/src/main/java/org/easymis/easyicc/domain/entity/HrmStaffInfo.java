package org.easymis.easyicc.domain.entity;

import java.util.Date;

import lombok.Data;

@Data
public class HrmStaffInfo {
	//员工编号",column="staff_id",id=true,length=40,isnull=false) 
    private String staffId; 
    //员工姓名",column="name",length=50,isnull=false) 
    private String name; 
    //组织机构代码|注册公司id",column="org_id",length=40,isnull=false) 
    private String orgId; 
    //注册分子公司id",column="filiale_org_id",length=40,isnull=true) 
    private String filialeOrgId; 
    //角色ID",column="role_id",length=2000,isnull=true) 
    private String roleId; 
    //部门",column="department_id",length=40,isnull=true) 
    private String departmentId; 
    //职务|岗位id",column="position_id",length=40,isnull=true) 
    private String positionId; 
    //职称|职级级别id",column="grade_id",length=40,isnull=true) 
    private String gradeId; 
    //会员ID",column="member_id",length=40,isnull=true) 
    private String memberId; 
    //员工工号",column="work_no",length=20,isnull=true) 
    private String workNo; 
    //照片文件名",column="photo_name",length=254,isnull=true) 
    private String photoName; 
    //工种",column="work_type",length=254,isnull=true) 
    private String workType; 
    //英文名",column="ename",length=254,isnull=true) 
    private String ename; 
    //身份证号码",column="card_no",length=254,isnull=true) 
    private String cardNo; 
    //性别(1-男,2-女)",column="sex",length=4,isnull=true) 
    private String sex; 
    //出生日期",column="birth",isnull=true) 
    private Date birth; 
    //出生日期是否是农历(0-否,1-是)",column="is_lunar",isnull=true) 
    private Integer isLunar; 
    //年龄",column="age",length=8,isnull=true) 
    private String age; 
    //籍贯",column="native_place",length=254,isnull=true) 
    private String nativePlace; 
    //户口所在地",column="domicile_place",length=254,isnull=true) 
    private String domicilePlace; 
    //是否异地户口",column="is_other_place",isnull=true) 
    private Integer isOtherPlace; 
    //民族",column="nationality",length=16,isnull=true) 
    private String nationality; 
    //婚姻状况",column="marital_status",isnull=true) 
    private Integer maritalStatus; 
    //政治面貌",column="political_status",isnull=true) 
    private Integer politicalStatus; 
    //入党团时间",column="join_party_time",isnull=true) 
    private Date joinPartyTime; 
    //联系电话",column="phone",length=254,isnull=true) 
    private String phone; 
    //手机号码",column="mobile",length=254,isnull=true) 
    private String mobile; 
    //电子邮箱1",column="email",length=254,isnull=true) 
    private String email; 
    //电子邮箱2",column="email2",length=254,isnull=true) 
    private String email2; 
    //QQ",column="qq",length=254,isnull=true) 
    private String qq; 
    //家庭地址",column="home_address",length=254,isnull=true) 
    private String homeAddress; 
    //其它联系方式",column="other_contact",length=254,isnull=true) 
    private String otherContact; 
    //参加工作时间",column="job_start_date",isnull=true) 
    private Date jobStartDate; 
    //总工龄",column="job_age",length=16,isnull=true) 
    private String jobAge; 
    //健康状况",column="health",length=16,isnull=true) 
    private String health; 
    //最高学历",column="highest_school",length=32,isnull=true) 
    private String highestSchool; 
    //最高学位",column="highest_degree",length=32,isnull=true) 
    private String highestDegree; 
    //毕业时间",column="graduation_date",isnull=true) 
    private Date graduationDate; 
    //毕业学校",column="graduation_school",length=254,isnull=true) 
    private String graduationSchool; 
    //专业",column="major",length=32,isnull=true) 
    private String major; 
    //计算机水平",column="computer_level",length=32,isnull=true) 
    private String computerLevel; 
    //外语语种1",column="foreign_language1",length=32,isnull=true) 
    private String foreignLanguage1; 
    //外语水平1",column="foreign_level1",length=32,isnull=true) 
    private String foreignLevel1; 
    //外语语种2",column="foreign_language2",length=32,isnull=true) 
    private String foreignLanguage2; 
    //外语水平2",column="foreign_level2",length=32,isnull=true) 
    private String foreignLevel2; 
    //外语语种3",column="foreign_language3",length=32,isnull=true) 
    private String foreignLanguage3; 
    //外语水平3",column="foreign_level3",length=32,isnull=true) 
    private String foreignLevel3; 
    //特长",column="skills",length=254,isnull=true) 
    private String skills; 
    //员工类型",column="occupation",length=32,isnull=true) 
    private String occupation; 
    //行政等级",column="administration_level",length=32,isnull=true) 
    private String administrationLevel; 
    //职称",column="present_position",length=32,isnull=true) 
    private String presentPosition; 
    //入职时间",column="work_begin_date",isnull=true) 
    private Date workBeginDate; 
    //本单位工龄",column="work_age",length=32,isnull=true) 
    private String workAge; 
    //起薪时间",column="begin_salary_date",isnull=true) 
    private Date beginSalaryDate; 
    //合同签订时间",column="contract_begin_date",isnull=true) 
    private Date contractBeginDate; 
    //合同到期时间",column="contract_end_date",isnull=true) 
    private Date contractEndDate; 
    //所在单位",column="company",length=254,isnull=true) 
    private String company; 
    //简历",column="resume",length=65535,isnull=true) 
    private String resume; 
    //附件编号",column="attachment_id",length=65535,isnull=true) 
    private String attachmentId; 
    //附件名称",column="attachment_name",length=65535,isnull=true) 
    private String attachmentName; 
    //",column="creator_id",length=40,isnull=true) 
    private String creatorId; 
    //建档时间",column="create_time",isnull=true) 
    private Date createTime; 
    //",column="update_id",length=255,isnull=true) 
    private String updateId; 
    //最后修改时间",column="update_time",isnull=true) 
    private Date updateTime; 
    //年休假",column="leave_type",isnull=true) 
    private Integer leaveType; 
    //户口类别",column="staff_type",isnull=true) 
    private Integer staffType; 
    //OA登录权限(1-允许登录,0-不允许登录)",column="oa_status",isnull=true) 
    private Integer oaStatus; 
    //是否管理员1是管理员",column="is_admin",isnull=true) 
    private Integer isAdmin; 
    //职务情况",column="certificate",length=0,isnull=true) 
    private String certificate; 
    //担保记录",column="surety",length=0,isnull=true) 
    private String surety; 
    //体检记录",column="body_examine",length=0,isnull=true) 
    private String bodyExamine; 
    //社保缴纳情况",column="insure",length=0,isnull=true) 
    private String insure; 
    //自定义字段2",column="userdef2",length=0,isnull=true) 
    private String userdef2; 
    //自定义字段3",column="userdef3",length=0,isnull=true) 
    private String userdef3; 
    //自定义字段4",column="userdef4",length=0,isnull=true) 
    private String userdef4; 
    //自定义字段5",column="userdef5",length=0,isnull=true) 
    private String userdef5; 
    //记录日期",column="record_date",isnull=true) 
    private Date recordDate; 
    //职称|职级级别",column="grade_name",length=50,isnull=true) 
    private String gradeName; 
    //籍贯",column="native_place2",length=254,isnull=true) 
    private String nativePlace2; 
    //岗位",column="work_job",length=200,isnull=true) 
    private String workJob; 
    //曾用名",column="before_name",length=200,isnull=true) 
    private String beforeName; 
    //生日提醒日期",column="birth_remind_date",length=10,isnull=true) 
    private String birthRemindDate; 
    //开户行名1",column="bank1",length=254,isnull=true) 
    private String bank1; 
    //开户行的账号1",column="bank_account1",length=254,isnull=true) 
    private String bankAccount1; 
    //开户行名2",column="bank2",length=254,isnull=true) 
    private String bank2; 
    //开户行的账号2",column="bank_account2",length=254,isnull=true) 
    private String bankAccount2; 
    //血型(A-A型血,B-B型血,O-O型血,AB-AB型血)",column="blood_type",length=5,isnull=true) 
    private String bloodType; 
    //是否为专家",column="is_experts",isnull=true) 
    private Integer isExperts; 
    //专家特长信息",column="experts_info",length=65535,isnull=true) 
    private String expertsInfo; 
    //直属下级",column="directly_under",length=200,isnull=true) 
    private String directlyUnder; 
    //直属上级",column="directly_superior",length=200,isnull=true) 
    private String directlySuperior; 
    //兼职",column="part_time",length=200,isnull=true) 
    private String partTime; 
    //研究成果",column="research_results",length=65535,isnull=true) 
    private String researchResults; 
    //工作电话",column="work_phone",length=20,isnull=true) 
    private String workPhone; 
    //在职状态1在职2离职",column="work_status",isnull=true) 
    private Integer workStatus; 
    //备注",column="remark",length=65535,isnull=true) 
    private String remark; 
    //语言设置1简体中文2English3繁體中文",column="set_language",isnull=false) 
    private Integer setLanguage; 
}
