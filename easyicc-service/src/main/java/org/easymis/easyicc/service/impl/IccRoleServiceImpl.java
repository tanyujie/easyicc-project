package org.easymis.easyicc.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.IccRole;
import org.easymis.easyicc.mybatis.mapper.IccRoleMapper;
import org.easymis.easyicc.service.IccRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
@Service
public class IccRoleServiceImpl implements IccRoleService {
	@Autowired
	private IccRoleMapper mapper;
	@Override
	public boolean save(IccRole bean) {
		bean.setRoleId(UUID.randomUUID().toString().replace("-", ""));
		return mapper.save(bean);
	}

	@Override
	public boolean update(IccRole bean) {
		// TODO Auto-generated method stub
		return mapper.update(bean);
	}

	@Override
	public IccRole findById(String id) {
		// TODO Auto-generated method stub
		return mapper.findById(id);
	}

	@Override
	public List findByOrgId(String orgId) {
		// TODO Auto-generated method stub
		return mapper.findByOrgId(orgId);
	}

	@Override
	public PageInfo<?> find(IccRole bean, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		List<String> idsList = Arrays.asList(ids.split(","));
		mapper.deleteBatch(idsList);
		return RestResult.buildSuccess();
	}

}
