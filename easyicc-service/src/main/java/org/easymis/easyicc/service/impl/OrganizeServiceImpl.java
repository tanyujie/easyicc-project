package org.easymis.easyicc.service.impl;

import java.util.List;

import org.easymis.easyicc.domain.entity.Organize;
import org.easymis.easyicc.mybatis.mapper.OrganizeMapper;
import org.easymis.easyicc.service.OrganizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class OrganizeServiceImpl implements OrganizeService {
	@Autowired
	private OrganizeMapper mapper;
	@Override
	public List<Organize> findList() {
		// TODO Auto-generated method stub
		return null;
	}

}
