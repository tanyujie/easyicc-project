package org.easymis.easyicc.service.impl;

import org.easymis.easyicc.domain.entity.OrganizeConfig;
import org.easymis.easyicc.mybatis.mapper.OrganizeConfigMapper;
import org.easymis.easyicc.service.OrganizeConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizeConfigServiceImpl implements OrganizeConfigService{
	@Autowired
	private OrganizeConfigMapper mapper;
	@Override
	public boolean save(OrganizeConfig bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(OrganizeConfig bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public OrganizeConfig findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrganizeConfig findOrgId(String orgId) {
		// TODO Auto-generated method stub
		return mapper.findById(orgId);
	}

}
