package org.easymis.easyicc.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.easymis.easyicc.domain.entity.Card;
import org.easymis.easyicc.domain.entity.VisitorInfo;

public interface VisitorInfoMapper {

    @Select("<script>" +
            "select a.* from visitor_info a where  a.allocation_status > 0\n" +
            "        <where>\n" +
            "            <if test=\"status != null and id=1\">\n" +//退回
            "                and a.allocation_status = 2 and a.is_back = 1\n" +
            "            </if>\n" +
            "            <if test=\"status != null and id=2\">\n" +//超期回收
            "                and a.allocation_status = 2 and a.is_expired = 1\n" +
            "            </if>\n" +
            "            <if test=\"status != null and id=3\">\n" +//等待分配
            "                and a.allocation_status = 2 and a.is_expired = 0 and a.is_back = 0 and a.back_user_id is null\n" +
            "            </if>\n" +
            "            <if test=\"status != null and id=4\">\n" +//已处理
            "                and a.allocation_status = 4 and a.is_valid = 1 and a.back_user_id is null\n" +
            "            </if>\n" +
            "            <if test=\"status != null and id=5\">\n" +//未处理
            "                and (a.allocation_status = 1 or a.allocation_status = 3 ) and a.back_user_id is null\n" +
            "            </if>\n" +
            "            <if test=\"status != null and id=6\">\n" +//设置无效
            "                and a.allocation_status = 4 and a.is_valid = 0\n" +
            "            </if>\n" +
            "            <if test=\"status != null and id=7\">\n" +//再分配未处理
            "                and a.allocation_status = 3 and and  a.back_user_id is not null\n" +
            "            </if>\n" +
            "            <if test=\"status != null and id=8\">\n" +//再分配已处理
            "                and a.allocation_status = 4 and a.is_valid = 1 and a.back_user_id is not null\n" +
            "            </if>\n" +
            "            <if test=\"status != null and id=9\">\n" +//
            "                and a.allocation_status = 5 and a.is_expired = 1\n" +
            "            </if>\n" +
            "            <if test=\"orgId != null and orgId!=''\">\n" +//
            "                and a.orgId = #{orgId}\n" +
            "            </if>\n" +
            "            <if test=\"name != null and name!=''\">\n" +//
            "                and a.name = #{name}\n" +
            "            </if>\n" +    
            "            <if test=\"tel != null and tel!=''\">\n" +//
            "                and a.tel like #{tel}\n" +
            "            </if>\n" + 
            "            <if test=\"email != null and email!=''\">\n" +//
            "                and a.email like #{email}\n" +
            "            </if>\n" + 
            
            "        </where>" +
            "</script>")
	List<Card> getList(Map<String, Object> map);
    @Insert("insert into visitor_info(id,visitor_static_id,org_id,name,phone,email,note,reserve_key,sex,rename,mobile,qq,url,company_name,area,staff_id,create_time,crm_state,crm_depict,search_engine,keyword,chat_url,search_host,spread_flag,first_url,promotion_id,type,back_depict,finish_depict,valid_flag,back_flag,allocation_status,modify_identity,create_staff_id,back_type,allocation_time,expired_flag,global_static_id,update_staff_id,update_time,back_staff_id,back_time,auto_flag,chat_id,refer,country,province,city,site_id,device_type,user_agent,click_text,bd_staff_id,bd_campaign_id,bd_adground_id,bd_keyword_id,bd_creative_id,ntag_id)values(#{id},#{visitorStaticId},#{orgId},#{name},#{phone},#{email},#{note},#{reserveKey},#{sex},#{rename},#{mobile},#{qq},#{url},#{companyName},#{area},#{staffId},#{createTime},#{crmState},#{crmDepict},#{searchEngine},#{keyword},#{chatUrl},#{searchHost},#{spreadFlag},#{firstUrl},#{promotionId},#{type},#{backDepict},#{finishDepict},#{validFlag},#{backFlag},#{allocationStatus},#{modifyIdentity},#{createStaffId},#{backType},#{allocationTime},#{expiredFlag},#{globalStaticId},#{updateStaffId},#{updateTime},#{backStaffId},#{backTime},#{autoFlag},#{chatId},#{refer},#{country},#{province},#{city},#{siteId},#{deviceType},#{userAgent},#{clickText},#{bdStaffId},#{bdCampaignId},#{bdAdgroundId},#{bdKeywordId},#{bdCreativeId},#{ntagId})")  
    public boolean save(VisitorInfo bean); 	
	@Select("select * from visitor_info where id =#{id}")
	VisitorInfo findById(@Param("id") String id);
	@Select("select * from visitor_info where id =#{cardId}")
	List<Card> findByCardId(@Param("cardId") String cardId);

	@Update("update js_visitor_info set is_valid = 0, is_expired = 0, is_back=0,  allocation_status = #{STATUS_FINISHED} where id = #{id} and modify_identity = #{modifyIdentity}")	 
	int updateStatusFinished(VisitorInfo bean);
}
