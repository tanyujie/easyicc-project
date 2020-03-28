package org.easymis.easyicc.service;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.SkillGroup;

public interface SkillGroupService {
	boolean save(SkillGroup bean);
	boolean update(SkillGroup bean);
	SkillGroup findById(String id);
	RestResult deleteByIds(String ids);
}
