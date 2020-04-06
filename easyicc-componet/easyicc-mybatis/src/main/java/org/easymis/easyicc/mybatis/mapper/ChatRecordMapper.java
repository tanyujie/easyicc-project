package org.easymis.easyicc.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.easymis.easyicc.domain.entity.ChatRecord;

public interface ChatRecordMapper {
	@Select({ "<script>", "SELECT * from chat_record",
			" <where> " + 
			" <if test=\"orgId != null\"> AND org_id=#{orgId}</if> " +

			" </where> " + 
			"</script>" })
	public List<ChatRecord> getList(ChatRecord params);

	@Insert("insert into chat_record(chat_id,org_id,site_id,create_time,end_time,visitor_id,staff_id,depict,visitor_static_id,invite_mode,effective,message_count,summarize,all_time,staff_message_count,visitor_message_count,visitor_location_city,visitor_location_province,visitor_location_country,visitor_ip,promotion_id,first_active_url,last_active_url,refer,keyword,view_page_count,leave_message_count,name,mobile,invite_total)values(#{chatId},#{orgId},#{siteId},#{createTime},#{endTime},#{visitorId},#{staffId},#{depict},#{visitorStaticId},#{inviteMode},#{effective},#{messageCount},#{summarize},#{allTime},#{staffMessageCount},#{visitorMessageCount},#{visitorLocationCity},#{visitorLocationProvince},#{visitorLocationCountry},#{visitorIp},#{promotionId},#{firstActiveUrl},#{lastActiveUrl},#{refer},#{keyword},#{viewPageCount},#{leaveMessageCount},#{name},#{mobile},#{inviteTotal})")
	public boolean save(ChatRecord bean);

	@Insert("insert into chat_record(chat_id,org_id,site_id,create_time,end_time,visitor_id,staff_id,depict,visitor_static_id,invite_mode,effective,message_count,summarize,all_time,staff_message_count,visitor_message_count,visitor_location_city,visitor_location_province,visitor_location_country,visitor_ip,promotion_id,first_active_url,last_active_url,refer,keyword,view_page_count,leave_message_count,name,mobile,invite_total)values(#{chatId},#{orgId},#{siteId},#{createTime},#{endTime},#{visitorId},#{staffId},#{depict},#{visitorStaticId},#{inviteMode},#{effective},#{messageCount},#{summarize},#{allTime},#{staffMessageCount},#{visitorMessageCount},#{visitorLocationCity},#{visitorLocationProvince},#{visitorLocationCountry},#{visitorIp},#{promotionId},#{firstActiveUrl},#{lastActiveUrl},#{refer},#{keyword},#{viewPageCount},#{leaveMessageCount},#{name},#{mobile},#{inviteTotal})")
	public void saveBatch(List<ChatRecord> beans);

	@Update("UPDATE chat_record SET chat_id= #{chatId},org_id= #{orgId},site_id= #{siteId},create_time= #{createTime},end_time= #{endTime},visitor_id= #{visitorId},staff_id= #{staffId},depict= #{depict},visitor_static_id= #{visitorStaticId},invite_mode= #{inviteMode},effective= #{effective},message_count= #{messageCount},summarize= #{summarize},all_time= #{allTime},staff_message_count= #{staffMessageCount},visitor_message_count= #{visitorMessageCount},visitor_location_city= #{visitorLocationCity},visitor_location_province= #{visitorLocationProvince},visitor_location_country= #{visitorLocationCountry},visitor_ip= #{visitorIp},promotion_id= #{promotionId},first_active_url= #{firstActiveUrl},last_active_url= #{lastActiveUrl},refer= #{refer},keyword= #{keyword},view_page_count= #{viewPageCount},leave_message_count= #{leaveMessageCount},name= #{name},mobile= #{mobile},invite_total= #{inviteTotal} WHERE chat_id= #{chatId}")
	public boolean update(ChatRecord bean);

	@Select("select * from chat_record t WHERE t.chat_id = #{chatId}")
	ChatRecord findById(@Param("chatId") String chatId);
	@Select("select * from chat_record t WHERE t.visitor_id = #{visitorId}")
	public List<ChatRecord> findByVisitorId(String visitorId);
	
	@Select("select * from chat_record t WHERE t.connected =1 and org_id= #{orgId}")
	public List<ChatRecord> findOnline(@Param("orgId")String orgId);
}
