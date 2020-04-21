package org.easymis.easyicc.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.easymis.easyicc.domain.entity.ChatStaffMessage;

public interface ChatStaffMessageMapper {
	@Select("select * from chat_staff_message")
	public List<ChatStaffMessage> getList(ChatStaffMessage params);

	@Insert("insert into chat_staff_message(id,org_id,send_id,accept_id,type,message,sign_flag,create_time)values(#{id},#{orgId},#{sendId},#{acceptId},#{type},#{message},#{signFlag},#{createTime})")
	public boolean save(ChatStaffMessage bean);

	@Insert("insert into chat_staff_message(id,org_id,send_id,accept_id,type,message,sign_flag,create_time)values(#{id},#{orgId},#{sendId},#{acceptId},#{type},#{message},#{signFlag},#{createTime})")
	public boolean saveBatch(List<ChatStaffMessage> beans);

	@Update("UPDATE chat_staff_message SET id= #{id},org_id= #{orgId},send_id= #{sendId},accept_id= #{acceptId},type= #{type},message= #{message},sign_flag= #{signFlag},create_time= #{createTime} WHERE id= #{id}")
	public boolean update(ChatStaffMessage bean);

	@Delete(" DELETE FROM chat_staff_message WHERE id = #{id}")
	public void delete(String id);

	public void removeBatch(List<String> list);

	public void restoreBatch(List<String> list);

	public void deleteBatch(List<String> list);

	@Select("select * from chat_staff_message t WHERE t.id = #{id}")
	public ChatStaffMessage findById(String id);

	@Select(" SELECT t.* FROM chat_staff_message t }")
	public List<ChatStaffMessage> findByIds(List<String> list);
}