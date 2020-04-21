package org.easymis.easyicc.service.impl;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.SkillGroup;
import org.easymis.easyicc.mybatis.mapper.SkillGroupMapper;
import org.easymis.easyicc.service.SkillGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class SkillGroupServiceImpl implements SkillGroupService {
	@Autowired
	private SkillGroupMapper mapper;

	@Override
	public boolean save(SkillGroup bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(SkillGroup bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SkillGroup findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findByOrgId(String orgId) {
		// TODO Auto-generated method stub
		return mapper.findByOrgId(orgId);
	}

	@Override
	public PageInfo<?> find(SkillGroup bean, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<SkillGroup> dataList = mapper.getList(bean);
		PageInfo<SkillGroup> p = new PageInfo<SkillGroup>(dataList);
		return p;
	}
}
