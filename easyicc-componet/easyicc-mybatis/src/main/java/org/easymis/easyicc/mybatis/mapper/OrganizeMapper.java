package org.easymis.easyicc.mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.easymis.easyicc.domain.entity.Organize;

public interface OrganizeMapper {
	 @Insert("insert into organize(org_id,org_no,org_name,parent_id,owner_id,contact,mobile,create_time,start_date,trial_finish_time,country_id,province_id,city_id,district_id,register_address,office_address,phone,fax,zip,total_staff,used_staff,status,depict,credit_code,legal_person,registered_capital,business_scope,email,ipo,stock_code,bank_no,bank_name,url,blog,level1_industry_id,level2_industry_id,level3_industry_id,level4_industry_id,industry_id,qq,we_chat,update_id,update_time,delete_flag,delete_id,delete_time,lock_flag,ip_gateway)values(#{orgId},#{orgNo},#{orgName},#{parentId},#{ownerId},#{contact},#{mobile},#{createTime},#{startDate},#{trialFinishTime},#{countryId},#{provinceId},#{cityId},#{districtId},#{registerAddress},#{officeAddress},#{phone},#{fax},#{zip},#{totalStaff},#{usedStaff},#{status},#{depict},#{creditCode},#{legalPerson},#{registeredCapital},#{businessScope},#{email},#{ipo},#{stockCode},#{bankNo},#{bankName},#{url},#{blog},#{level1IndustryId},#{level2IndustryId},#{level3IndustryId},#{level4IndustryId},#{industryId},#{qq},#{weChat},#{updateId},#{updateTime},#{deleteFlag},#{deleteId},#{deleteTime},#{lockFlag},#{ipGateway})")  
	 public void save(Organize bean); 
}
