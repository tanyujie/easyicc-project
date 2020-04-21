package org.easymis.easyicc.service;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.SkillGroup;

import com.github.pagehelper.PageInfo;

public interface SkillGroupService {
	boolean save(SkillGroup bean);

	boolean update(SkillGroup bean);

	SkillGroup findById(String id);

	public PageInfo<?> find(SkillGroup bean, Integer pageNum, Integer pageSize);

	public List findByOrgId(String orgId);

	RestResult deleteByIds(String ids);
}
