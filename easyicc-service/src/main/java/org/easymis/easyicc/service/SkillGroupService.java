package org.easymis.easyicc.service;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.SkillGroup;

public interface SkillGroupService {
	boolean save(SkillGroup bean);
	boolean update(SkillGroup bean);
	SkillGroup findById(String id);
	public List findByOrgId(String orgId);
	RestResult deleteByIds(String ids);
}
