package org.easymis.easyicc.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.easymis.easyicc.domain.entity.SkillGroup;

public interface SkillGroupMapper {
	@Select("select * from skill_group where org_id=#{orgId}")
	public List<SkillGroup> findByOrgId(@Param("orgId") String orgId);
}
