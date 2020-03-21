package org.easymis.easyicc.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.easymis.easyicc.domain.entity.ChatMsg;

public interface ChatMsgMapper {
	@Insert("insert into chat_msg(id,org_id,send_member_id,accept_member_id,msg,sign_flag,create_time)values(#{id},#{orgId},#{sendMemberId},#{acceptMemberId},#{msg},#{signFlag},#{createTime})")
	int insertByBean(ChatMsg bean);

	List<ChatMsg> find(Integer signFlag, String acceptUserId);
}
