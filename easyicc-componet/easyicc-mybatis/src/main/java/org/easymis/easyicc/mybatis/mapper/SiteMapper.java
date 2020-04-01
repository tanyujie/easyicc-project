package org.easymis.easyicc.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.easymis.easyicc.domain.entity.Site;

public interface SiteMapper {
	@Select("select * from site where org_id=#{orgId}")
	public List<Site> getList(Site params);

	@Select("select * from site where org_id=#{orgId}")
	public List<Site> findByOrgId(@Param("orgId") String orgId);

	@Insert("insert into site(id,org_id,name,depict)values(#{id},#{orgId},#{name},#{depict})")
	public boolean save(Site bean);

	@Insert("insert into site(id,org_id,name,depict)values(#{id},#{orgId},#{name},#{depict})")
	public void saveBatch(List<Site> beans);

	@Update("UPDATE site SET name= #{name},depict= #{depict} WHERE id= #{id}")
	public boolean update(Site bean);

	@Delete(" DELETE FROM site WHERE id = #{id}")
	public void delete(String id);

	public void removeBatch(List<String> list);

	public void restoreBatch(List<String> list);

	public void deleteBatch(List<String> list);

	@Select("select * from site t WHERE t.id = #{id}")
	@Results(value = { @Result(property = "id", column = "id"), @Result(property = "orgId", column = "org_id"),
			@Result(property = "name", column = "name"), @Result(property = "depict", column = "depict") })
	public Site findById(String id);

	@Select(" SELECT t.* FROM site t }")
	public List<Site> findByIds(List<String> list);
}