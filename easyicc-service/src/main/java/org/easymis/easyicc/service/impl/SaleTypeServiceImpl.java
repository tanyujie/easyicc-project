package org.easymis.easyicc.service.impl;

import java.util.List;

import org.easymis.easyicc.domain.entity.SaleType;
import org.easymis.easyicc.mybatis.mapper.SaleTypeMapper;
import org.easymis.easyicc.service.SaleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SaleTypeServiceImpl implements SaleTypeService {
	@Autowired
	private SaleTypeMapper mapper;
	@Override
	public List<SaleType> findByOrgId(String orgId) {
		// TODO Auto-generated method stub
		return null;
	}

}
