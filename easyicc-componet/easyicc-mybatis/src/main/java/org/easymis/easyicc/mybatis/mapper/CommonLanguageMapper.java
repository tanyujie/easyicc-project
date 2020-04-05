package org.easymis.easyicc.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.easymis.easyicc.domain.entity.CommonLanguage;

public interface CommonLanguageMapper {
	@Select({"<script>",
        "SELECT * from common_language ",
        " <where> " +
        " <if test=\"orgId != null\"> AND org_id=#{orgId}</if> " +
        " <if test=\"staffId != null\"> AND staff_id=#{staffId}</if> " +
        " <if test=\"content != null\"> AND content LIKE '%${content}%'</if> " +
        " </where> " +
        "</script>"}) 
	public List<CommonLanguage> getList(
			CommonLanguage params);

	@Insert("insert into common_language(id,org_id,category_id,staff_id,content,type,priority,title,hot_key)values(#{id},#{orgId},#{categoryId},#{staffId},#{content},#{type},#{priority},#{title},#{hotKey})")
	public boolean save(CommonLanguage bean);

	@Insert("insert into common_language(id,org_id,category_id,staff_id,content,type,priority,title,hot_key)values(#{id},#{orgId},#{categoryId},#{staffId},#{content},#{type},#{priority},#{title},#{hotKey})")
	public boolean saveBatch(List<CommonLanguage> beans);

	@Update("UPDATE common_language SET category_id= #{categoryId},staff_id= #{staffId},content= #{content},type= #{type},priority= #{priority},title= #{title},hot_key= #{hotKey} WHERE id= #{id}")
	public boolean update(CommonLanguage bean);

	@Delete(" DELETE FROM common_language WHERE id = #{id}")
	public boolean delete(String id);

	public void removeBatch(List<String> list);

	public void restoreBatch(List<String> list);
	@Delete({"<script>",
        "DELETE FROM common_language",
        "WHERE id IN", 
          "<foreach item='item' index='index' collection='ids'",
            "open='(' separator=',' close=')'>",
            "#{item}",
          "</foreach>",
        "</script>"}) 
	public void deleteBatch(@Param("ids")List<String> ids);

	@Select("select * from common_language t WHERE t.id = #{id}")
	public CommonLanguage findById(@Param("id")String id);
	@Select("select * from common_language t WHERE t.org_id = #{orgId} and staff_id is null")
	public List<CommonLanguage> findByOrgId(@Param("orgId")String orgId);
	
	@Select("select * from common_language t WHERE t.staff_id = #{staffId}")
	public List<CommonLanguage> findByStaffId(@Param("staffId")String staffId);
	
	@Select(" SELECT t.* FROM common_language t ")
	public List<CommonLanguage> findByIds(List<String> list);
}
