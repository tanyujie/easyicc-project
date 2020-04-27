package org.easymis.easyicc.mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.easymis.easyicc.domain.entity.Organize;

public interface OrganizeMapper {
	@Insert("insert into organize(org_id,org_no,org_name,parent_id,owner_id,contact,mobile,create_time,start_date,trial_finish_time,country_id,province_id,city_id,district_id,register_address,office_address,phone,fax,zip,total_staff,used_staff,status,depict,credit_code,legal_person,registered_capital,business_scope,email,ipo,stock_code,bank_no,bank_name,url,blog,level1_industry_id,level2_industry_id,level3_industry_id,level4_industry_id,industry_id,qq,we_chat,update_id,update_time,delete_flag,delete_id,delete_time,lock_flag,ip_gateway)values(#{orgId},#{orgNo},#{orgName},#{parentId},#{ownerId},#{contact},#{mobile},#{createTime},#{startDate},#{trialFinishTime},#{countryId},#{provinceId},#{cityId},#{districtId},#{registerAddress},#{officeAddress},#{phone},#{fax},#{zip},#{totalStaff},#{usedStaff},#{status},#{depict},#{creditCode},#{legalPerson},#{registeredCapital},#{businessScope},#{email},#{ipo},#{stockCode},#{bankNo},#{bankName},#{url},#{blog},#{level1IndustryId},#{level2IndustryId},#{level3IndustryId},#{level4IndustryId},#{industryId},#{qq},#{weChat},#{updateId},#{updateTime},#{deleteFlag},#{deleteId},#{deleteTime},#{lockFlag},#{ipGateway})")
	public void save(Organize bean);

	@Update("UPDATE organize SET org_id= #{orgId},org_no= #{orgNo},org_name= #{orgName},parent_id= #{parentId},owner_id= #{ownerId},contact= #{contact},mobile= #{mobile},create_time= #{createTime},start_date= #{startDate},trial_finish_time= #{trialFinishTime},country_id= #{countryId},province_id= #{provinceId},city_id= #{cityId},district_id= #{districtId},register_address= #{registerAddress},office_address= #{officeAddress},phone= #{phone},fax= #{fax},zip= #{zip},total_staff= #{totalStaff},used_staff= #{usedStaff},status= #{status},depict= #{depict},credit_code= #{creditCode},legal_person= #{legalPerson},registered_capital= #{registeredCapital},business_scope= #{businessScope},email= #{email},ipo= #{ipo},stock_code= #{stockCode},bank_no= #{bankNo},bank_name= #{bankName},url= #{url},blog= #{blog},level1_industry_id= #{level1IndustryId},level2_industry_id= #{level2IndustryId},level3_industry_id= #{level3IndustryId},level4_industry_id= #{level4IndustryId},industry_id= #{industryId},qq= #{qq},we_chat= #{weChat},update_id= #{updateId},update_time= #{updateTime},delete_flag= #{deleteFlag},delete_id= #{deleteId},delete_time= #{deleteTime},lock_flag= #{lockFlag},ip_gateway= #{ipGateway} WHERE org_id= #{orgId}")
	public boolean update(Organize bean);

	@Select("select * from organize t WHERE t.org_id = #{orgId}")
	public Organize findById(@Param("orgId") String orgId);
}
