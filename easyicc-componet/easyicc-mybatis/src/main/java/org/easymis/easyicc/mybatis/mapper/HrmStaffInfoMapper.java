package org.easymis.easyicc.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.easymis.easyicc.domain.entity.HrmStaffInfo;
import org.easymis.easyicc.domain.vo.StaffOnlineTreeVo;

public interface HrmStaffInfoMapper {
	@Select(" SELECT staff_id,name,org_id,department_id FROM hrm_staff_info t where org_id=#{orgId}")
	public List<HrmStaffInfo> findByOrgId(@Param("orgId")String orgId);
	
	@Select(" SELECT staff_id,name,org_id,department_id FROM hrm_staff_info t where org_id=#{orgId}")
	public List<StaffOnlineTreeVo> findByOnlineTree(@Param("orgId")String orgId);
	
	 @Insert("insert into hrm_staff_info(staff_id,name,org_id,filiale_org_id,role_id,department_id,position_id,grade_id,member_id,work_no,photo_name,work_type,ename,card_no,sex,birth,is_lunar,age,native_place,domicile_place,is_other_place,nationality,marital_status,political_status,join_party_time,phone,mobile,email,email2,qq,home_address,other_contact,job_start_date,job_age,health,highest_school,highest_degree,graduation_date,graduation_school,major,computer_level,foreign_language1,foreign_level1,foreign_language2,foreign_level2,foreign_language3,foreign_level3,skills,occupation,administration_level,present_position,work_begin_date,work_age,begin_salary_date,contract_begin_date,contract_end_date,company,resume,attachment_id,attachment_name,creator_id,create_time,update_id,update_time,leave_type,staff_type,oa_status,is_admin,certificate,surety,body_examine,insure,userdef2,userdef3,userdef4,userdef5,record_date,grade_name,native_place2,work_job,before_name,birth_remind_date,bank1,bank_account1,bank2,bank_account2,blood_type,is_experts,experts_info,directly_under,directly_superior,part_time,research_results,work_phone,work_status,remark,set_language)values(#{staffId},#{name},#{orgId},#{filialeOrgId},#{roleId},#{departmentId},#{positionId},#{gradeId},#{memberId},#{workNo},#{photoName},#{workType},#{ename},#{cardNo},#{sex},#{birth},#{isLunar},#{age},#{nativePlace},#{domicilePlace},#{isOtherPlace},#{nationality},#{maritalStatus},#{politicalStatus},#{joinPartyTime},#{phone},#{mobile},#{email},#{email2},#{qq},#{homeAddress},#{otherContact},#{jobStartDate},#{jobAge},#{health},#{highestSchool},#{highestDegree},#{graduationDate},#{graduationSchool},#{major},#{computerLevel},#{foreignLanguage1},#{foreignLevel1},#{foreignLanguage2},#{foreignLevel2},#{foreignLanguage3},#{foreignLevel3},#{skills},#{occupation},#{administrationLevel},#{presentPosition},#{workBeginDate},#{workAge},#{beginSalaryDate},#{contractBeginDate},#{contractEndDate},#{company},#{resume},#{attachmentId},#{attachmentName},#{creatorId},#{createTime},#{updateId},#{updateTime},#{leaveType},#{staffType},#{oaStatus},#{isAdmin},#{certificate},#{surety},#{bodyExamine},#{insure},#{userdef2},#{userdef3},#{userdef4},#{userdef5},#{recordDate},#{gradeName},#{nativePlace2},#{workJob},#{beforeName},#{birthRemindDate},#{bank1},#{bankAccount1},#{bank2},#{bankAccount2},#{bloodType},#{isExperts},#{expertsInfo},#{directlyUnder},#{directlySuperior},#{partTime},#{researchResults},#{workPhone},#{workStatus},#{remark},#{setLanguage})")  
	 public boolean save(HrmStaffInfo bean); 
}
