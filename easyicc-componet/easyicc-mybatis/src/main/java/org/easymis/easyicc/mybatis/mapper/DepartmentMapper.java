package org.easymis.easyicc.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.easymis.easyicc.domain.entity.Department;

public interface DepartmentMapper {
	@Select(" SELECT * FROM department t where org_id=#{orgId} order by priority")
	public List<Department> findByOrgId(@Param("orgId")String orgId);
	
	 @Insert("insert into department(id,org_id,name,no,leader_id,assist_id,invisible_ids,parent_id,priority,level,is_leaf,depict,status)values(#{id},#{orgId},#{name},#{no},#{leaderId},#{assistId},#{invisibleIds},#{parentId},#{priority},#{level},#{isLeaf},#{depict},#{status})")  
	 public void save(Department bean); 
}
