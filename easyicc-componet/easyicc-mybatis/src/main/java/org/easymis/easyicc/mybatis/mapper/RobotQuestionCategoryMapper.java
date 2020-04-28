package org.easymis.easyicc.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.easymis.easyicc.domain.entity.RobotQuestionCategory;

public interface RobotQuestionCategoryMapper {

	@Select("select * from robot_question_category")
	public List<RobotQuestionCategory> getList(RobotQuestionCategory params);

	@Insert("insert into robot_question_category(category_id,org_id,rebot_id,category_name,depict,create_time,update_time)values(#{categoryId},#{orgId},#{rebotId},#{categoryName},#{depict},#{createTime},#{updateTime})")
	public boolean save(RobotQuestionCategory bean);

	@Insert("insert into robot_question_category(category_id,org_id,rebot_id,category_name,depict,create_time,update_time)values(#{categoryId},#{orgId},#{rebotId},#{categoryName},#{depict},#{createTime},#{updateTime})")
	public void saveBatch(List<RobotQuestionCategory> beans);

	@Update("UPDATE robot_question_category SET category_id= #{categoryId},org_id= #{orgId},rebot_id= #{rebotId},category_name= #{categoryName},depict= #{depict},create_time= #{createTime},update_time= #{updateTime} WHERE category_id= #{categoryId}")
	public boolean update(RobotQuestionCategory bean);

	@Delete(" DELETE FROM robot_question_category WHERE category_id = #{categoryId}")
	public void delete(String category_id);

	public void removeBatch(List<String> list);

	public void restoreBatch(List<String> list);

	public void deleteBatch(List<String> list);

	@Select("select * from robot_question_category t WHERE t.category_id = #{categoryId}")
	public RobotQuestionCategory findById(String categoryId);

	@Select(" SELECT t.* FROM robot_question_category t }")
	public List<RobotQuestionCategory> findByIds(List<String> list);

}
