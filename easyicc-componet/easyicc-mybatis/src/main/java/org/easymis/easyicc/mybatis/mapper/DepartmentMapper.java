package org.easymis.easyicc.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.easymis.easyicc.domain.entity.Department;

public interface DepartmentMapper {
	@Select(" SELECT * FROM department t where org_id=#{orgId} order by priority")
	public List<Department> findByOrgId(@Param("orgId")String orgId);
	
	@Insert("insert into Member(id, Member_no, sex, age, company_name, department, position, password, head_url, phone_number, email, modify_time, create_time, name, enabled)"
			+ "values"
			+ "(#{id},#{MemberNo},#{sex},#{age},#{companyName},#{department},#{position},#{password},#{headUrl},#{phoneNumber},#{email},#{modifyTime},#{createTime},#{name},#{enabled})")
	boolean save(Department bean);
}
