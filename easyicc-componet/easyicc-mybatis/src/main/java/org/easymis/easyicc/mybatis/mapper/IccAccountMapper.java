package org.easymis.easyicc.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.easymis.easyicc.domain.entity.IccAccount;

public interface IccAccountMapper {
	@Select("select * from icc_account")
	public List<IccAccount> getList(IccAccount params);

	@Insert("insert into icc_account(id,org_id,member_id,staff_id,code,nick,nick_name,login_start_time,login_end_time,filter_time,max_accept_count,max_chat_count,account_rank,account_type,login_ip,login_mac,head_url,depict)values(#{id},#{orgId},#{memberId},#{staffId},#{code},#{nick},#{nickName},#{loginStartTime},#{loginEndTime},#{filterTime},#{maxAcceptCount},#{maxChatCount},#{accountRank},#{accountType},#{loginIp},#{loginMac},#{headUrl},#{depict})")
	public boolean save(IccAccount bean);

	@Insert("insert into icc_account(id,org_id,member_id,staff_id,code,nick,nick_name,login_start_time,login_end_time,filter_time,max_accept_count,max_chat_count,account_rank,account_type,login_ip,login_mac,head_url,depict)values(#{id},#{orgId},#{memberId},#{staffId},#{code},#{nick},#{nickName},#{loginStartTime},#{loginEndTime},#{filterTime},#{maxAcceptCount},#{maxChatCount},#{accountRank},#{accountType},#{loginIp},#{loginMac},#{headUrl},#{depict})")
	public void saveBatch(List<IccAccount> beans);

	@Update("UPDATE icc_account SET id= #{id},org_id= #{orgId},member_id= #{memberId},staff_id= #{staffId},code= #{code},nick= #{nick},nick_name= #{nickName},login_start_time= #{loginStartTime},login_end_time= #{loginEndTime},filter_time= #{filterTime},max_accept_count= #{maxAcceptCount},max_chat_count= #{maxChatCount},account_rank= #{accountRank},account_type= #{accountType},login_ip= #{loginIp},login_mac= #{loginMac},head_url= #{headUrl},depict= #{depict} WHERE id= #{id}")
	public boolean update(IccAccount bean);

	@Delete(" DELETE FROM icc_account WHERE id = #{id}")
	public void delete(String id);

	public void removeBatch(List<String> list);

	public void restoreBatch(List<String> list);

	public void deleteBatch(List<String> list);

	@Select("select * from icc_account t WHERE t.id = #{id}")
	@Results(value = { @Result(property = "id", column = "id"), @Result(property = "orgId", column = "org_id"),
			@Result(property = "memberId", column = "member_id"), @Result(property = "staffId", column = "staff_id"),
			@Result(property = "code", column = "code"), @Result(property = "nick", column = "nick"),
			@Result(property = "nickName", column = "nick_name"),
			@Result(property = "loginStartTime", column = "login_start_time"),
			@Result(property = "loginEndTime", column = "login_end_time"),
			@Result(property = "filterTime", column = "filter_time"),
			@Result(property = "maxAcceptCount", column = "max_accept_count"),
			@Result(property = "maxChatCount", column = "max_chat_count"), @Result(property = "accountRank", column = "account_rank"),
			@Result(property = "accountType", column = "account_type"),
			@Result(property = "loginIp", column = "login_ip"), @Result(property = "loginMac", column = "login_mac"),
			@Result(property = "headUrl", column = "head_url"), @Result(property = "depict", column = "depict") })
	public IccAccount findById(String id);

	@Select(" SELECT t.* FROM icc_account t }")
	public List<IccAccount> findByIds(List<String> list);
}