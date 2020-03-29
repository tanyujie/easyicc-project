package org.easymis.easyicc.mybatis.mapper;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.easymis.easyicc.domain.entity.RobotChatDetail; 

public interface RobotChatDetailMapper {
	@Select("select * from robot_chat_detail")
	public List<RobotChatDetail> getList(HashMap<String, Object> params);

	@Insert("insert into robot_chat_detail(id,chat_id,org_id,robot_id,question_id,question,answer,system_category_id,create_time)values(#{id},#{chatId},#{orgId},#{robotId},#{questionId},#{question},#{answer},#{systemCategoryId},#{createTime})")
	public boolean save(RobotChatDetail bean);

	@Insert("insert into robot_chat_detail(id,chat_id,org_id,robot_id,question_id,question,answer,system_category_id,create_time)values(#{id},#{chatId},#{orgId},#{robotId},#{questionId},#{question},#{answer},#{systemCategoryId},#{createTime})")
	public void saveBatch(List<RobotChatDetail> beans);

	@Update("UPDATE robot_chat_detail SET id= #{id},chat_id= #{chatId},org_id= #{orgId},robot_id= #{robotId},question_id= #{questionId},question= #{question},answer= #{answer},system_category_id= #{systemCategoryId},create_time= #{createTime} WHERE id= #{id}")
	public void update(RobotChatDetail bean);

	@Delete(" DELETE FROM robot_chat_detail WHERE id = #{id}")
	public void delete(String id);

	public void removeBatch(List<String> list);

	public void restoreBatch(List<String> list);

	public void deleteBatch(List<String> list);

	@Select("select * from robot_chat_detail t WHERE t.id = #{id}")
	public RobotChatDetail findById(String id);

	@Select(" SELECT t.* FROM robot_chat_detail t }")
	public List<RobotChatDetail> findByIds(List<String> list);
}