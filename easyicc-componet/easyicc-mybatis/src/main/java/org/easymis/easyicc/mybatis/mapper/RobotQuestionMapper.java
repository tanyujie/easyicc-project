package org.easymis.easyicc.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.easymis.easyicc.domain.entity.RobotQuestion;

public interface RobotQuestionMapper {
	@Select("select * from robot_question")
	@Results(value = { @Result(property = "id", column = "id"), @Result(property = "orgId", column = "org_id"),
			@Result(property = "robotId", column = "robot_id"), @Result(property = "sortId", column = "sort_id"),
			@Result(property = "question", column = "question"),
			@Result(property = "resultType", column = "result_type"), @Result(property = "answer", column = "answer"),
			@Result(property = "lockedFlag", column = "locked_flag"),
			@Result(property = "createId", column = "create_id"),
			@Result(property = "createTime", column = "create_time"),
			@Result(property = "updateId", column = "update_id"),
			@Result(property = "updateTime", column = "update_time") })
	public List<RobotQuestion> getList(RobotQuestion params);

	@Insert("insert into robot_question(id,org_id,robot_id,sort_id,question,result_type,answer,locked_flag,create_id,create_time,update_id,update_time)values(#{id},#{orgId},#{robotId},#{sortId},#{question},#{resultType},#{answer},#{lockedFlag},#{createId},#{createTime},#{updateId},#{updateTime})")
	public boolean save(RobotQuestion bean);

	@Insert("insert into robot_question(id,org_id,robot_id,sort_id,question,result_type,answer,locked_flag,create_id,create_time,update_id,update_time)values(#{id},#{orgId},#{robotId},#{sortId},#{question},#{resultType},#{answer},#{lockedFlag},#{createId},#{createTime},#{updateId},#{updateTime})")
	public void saveBatch(List<RobotQuestion> beans);

	@Update("UPDATE robot_question SET id= #{id},org_id= #{orgId},robot_id= #{robotId},sort_id= #{sortId},question= #{question},result_type= #{resultType},answer= #{answer},locked_flag= #{lockedFlag},create_id= #{createId},create_time= #{createTime},update_id= #{updateId},update_time= #{updateTime} WHERE id= #{id}")
	public void update(RobotQuestion bean);

	@Delete(" DELETE FROM robot_question WHERE id = #{id}")
	public void delete(String id);

	public void removeBatch(List<String> list);

	public void restoreBatch(List<String> list);
	@Delete({"<script>",
        "DELETE FROM robot_question",
        "WHERE id IN", 
          "<foreach item='item' index='index' collection='ids'",
            "open='(' separator=',' close=')'>",
            "#{item}",
          "</foreach>",
        "</script>"}) 
	public void deleteBatch(@Param("ids")List<String> ids);
	@Select("select * from robot_question t WHERE t.question like '%'||#{question}||'%' and t.org_id=#{orgId} ")
	public List<RobotQuestion> findByQuestion(RobotQuestion bean);
	@Select("select * from robot_question t WHERE t.id = #{id}")
	public RobotQuestion findById(@Param("id")String id);

	@Select(" SELECT t.* FROM robot_question t }")
	public List<RobotQuestion> findByIds(List<String> list);
}