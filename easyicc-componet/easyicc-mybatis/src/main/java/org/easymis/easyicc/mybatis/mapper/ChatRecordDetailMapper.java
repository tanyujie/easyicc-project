package org.easymis.easyicc.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.easymis.easyicc.domain.entity.ChatRecordDetail;
import org.easymis.easyicc.domain.vo.ChatRecordDetailVo;

public interface ChatRecordDetailMapper {
	@Select({ "<script>", "SELECT * from chat_record_detail",
		" <where> " + 
		" <if test=\"orgId != null\"> AND org_id=#{orgId}</if> " +

		" </where> " + 
		"</script>" })
	public List<ChatRecordDetail> getList(ChatRecordDetail params);
	@Insert("insert into chat_record_detail(org_id, chat_id, recorder_id, create_Time, type, message, from_user_id,to_user_id)"
			+ "values"
			+ "(#{orgId},#{chatId},#{recorderId},#{createTime},#{type},#{message},#{fromUserId},#{toUserId})")
	boolean insertByBean(ChatRecordDetail bean);
	@Select("<script>" + "SELECT * FROM chat_record_detail "+
            " <where> " +
            " <if test=\"orgId != null\">org_id=#{orgId}</if> " +
            " <if test=\"chatId != null\"> AND chat_id=#{chatId}</if> " +
            " <if test=\"fromUserId != null\"> AND from_user_id=#{fromUserId}</if> " +
            " <if test=\"toUserId != null\"> AND to_user_id=#{toUserId}</if> " +
            " </where> " +
            "<if test='start!=null'>" +
            " LIMIT #{start},1000" +
            "</if>" +
			"</script>")
	public List<ChatRecordDetail> findList(ChatRecordDetailVo bean);
}
