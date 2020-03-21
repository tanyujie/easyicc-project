package org.easymis.easyicc.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.easymis.easyicc.domain.entity.CommonLanguageCategory;

public interface CommonLanguageCategoryMapper {
	@Select({"<script>",
        "SELECT * from common_language_category",
        " <where> " +
        " <if test=\"orgId != null\"> AND org_id=#{orgId}</if> " +
        " <if test=\"name != null\"> AND name LIKE '%${name}%'</if> " +
        " </where> " +
        "</script>"}) 
	public List<CommonLanguageCategory> getList(CommonLanguageCategory params);

	@Insert("insert into common_language_category(id,org_id,name,depict,parent_id,priority)values(#{id},#{orgId},#{name},#{depict},#{parentId},#{priority})")
	public boolean save(CommonLanguageCategory bean);

	@Insert("insert into common_language_category(id,org_id,name,depict,parent_id,priority)values(#{id},#{orgId},#{name},#{depict},#{parentId},#{priority})")
	public void saveBatch(List<CommonLanguageCategory> beans);

	@Update("UPDATE common_language_category SET name= #{name},depict= #{depict},parent_id= #{parentId},priority= #{priority} WHERE id= #{id}")
	public boolean update(CommonLanguageCategory bean);

	@Delete(" DELETE FROM common_language_category WHERE id = #{id}")
	public void delete(String id);

	public void removeBatch(List<String> list);

	public void restoreBatch(List<String> list);
	@Delete({"<script>",
        "DELETE FROM common_language_category",
        "WHERE id IN", 
          "<foreach item='item' index='index' collection='ids'",
            "open='(' separator=',' close=')'>",
            "#{item}",
          "</foreach>",
        "</script>"}) 
	public void deleteBatch(@Param("ids")List<String> ids);

	@Select("select * from common_language_category t WHERE t.id = #{id}")
	public CommonLanguageCategory findById(String id);

	@Select(" SELECT t.* FROM common_language_category t }")
	public List<CommonLanguageCategory> findByIds(List<String> list);
}
