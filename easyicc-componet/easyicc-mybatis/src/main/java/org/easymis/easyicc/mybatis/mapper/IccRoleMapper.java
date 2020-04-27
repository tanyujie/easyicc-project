package org.easymis.easyicc.mybatis.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.easymis.easyicc.domain.entity.IccRole;

public interface IccRoleMapper {
	@Select("select * from icc_role")
	public List<IccRole> getList(HashMap<String, Object> params);

	@Insert("insert into icc_role(role_id,org_id,role_name,depict,role_status)values(#{roleId},#{orgId},#{roleName},#{depict},#{roleStatus})")
	public boolean save(IccRole bean);

	@Insert("insert into icc_role(role_id,org_id,role_name,depict,role_status)values(#{roleId},#{orgId},#{roleName},#{depict},#{roleStatus})")
	public void saveBatch(List<IccRole> beans);

	@Update("UPDATE icc_role SET role_id= #{roleId},org_id= #{orgId},role_name= #{roleName},depict= #{depict},role_status= #{roleStatus} WHERE role_id= #{roleId}")
	public boolean update(IccRole bean);

	@Delete(" DELETE FROM icc_role WHERE role_name = #{roleName}")
	public void delete(String role_name);

	public void removeBatch(List<String> list);

	public void restoreBatch(List<String> list);
	@Delete({"<script>",
        "DELETE FROM icc_role",
        "WHERE role_id IN", 
          "<foreach item='item' index='index' collection='ids'",
            "open='(' separator=',' close=')'>",
            "#{item}",
          "</foreach>",
        "</script>"}) 
	public void deleteBatch(@Param("ids")List<String> ids);

	@Select("select * from icc_role t WHERE t.role_Id = #{roleId}")
	public IccRole findById(@Param("roleId")String roleId);
	
	@Select("select * from icc_role t WHERE t.org_id = #{orgId}")
	public List<IccRole> findByOrgId(@Param("orgId")String orgId);
	@Select(" SELECT t.* FROM icc_role t }")
	public List<IccRole> findByIds(List<String> list);
}