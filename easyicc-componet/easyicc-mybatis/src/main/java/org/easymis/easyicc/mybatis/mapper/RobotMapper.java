package org.easymis.easyicc.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.easymis.easyicc.domain.entity.Robot;

public interface RobotMapper {
	@Select("select * from robot WHERE org_id = #{orgId}")
	public List<Robot> getList(Robot params);

	@Insert("insert into robot(robot_id,org_id,name,clients,industry,scene,depict,avatar,sex,age,constellation,mother,father,unknown_reply,tail,keyword_flag,keyword,keyword_reply,api_key,secret_key)values(#{robotId},#{orgId},#{name},#{clients},#{industry},#{scene},#{depict},#{avatar},#{sex},#{age},#{constellation},#{mother},#{father},#{unknownReply},#{tail},#{keywordFlag},#{keyword},#{keywordReply},#{apiKey},#{secretKey})")
	public boolean save(Robot bean);

	@Insert("insert into robot(robot_id,org_id,name,clients,industry,scene,depict,avatar,sex,age,constellation,mother,father,unknown_reply,tail,keyword_flag,keyword,keyword_reply,api_key,secret_key)values(#{robotId},#{orgId},#{name},#{clients},#{industry},#{scene},#{depict},#{avatar},#{sex},#{age},#{constellation},#{mother},#{father},#{unknownReply},#{tail},#{keywordFlag},#{keyword},#{keywordReply},#{apiKey},#{secretKey})")
	public void saveBatch(List<Robot> beans);

	@Update("UPDATE robot SET robot_id= #{robotId},org_id= #{orgId},name= #{name},clients= #{clients},industry= #{industry},scene= #{scene},depict= #{depict},avatar= #{avatar},sex= #{sex},age= #{age},constellation= #{constellation},mother= #{mother},father= #{father},unknown_reply= #{unknownReply},tail= #{tail},keyword_flag= #{keywordFlag},keyword= #{keyword},keyword_reply= #{keywordReply},api_key= #{apiKey},secret_key= #{secretKey} WHERE robot_id= #{robotId}")
	public void update(Robot bean);

	@Delete(" DELETE FROM robot WHERE robot_id = #{robotId}")
	public void delete(String robot_id);

	public void removeBatch(List<String> list);

	public void restoreBatch(List<String> list);

	public void deleteBatch(List<String> list);

	@Select("select * from robot t WHERE t.robot_id = #{robotId}")
	public Robot findById(String robot_id);

	@Select(" SELECT t.* FROM robot t }")
	public List<Robot> findByIds(List<String> list);
}