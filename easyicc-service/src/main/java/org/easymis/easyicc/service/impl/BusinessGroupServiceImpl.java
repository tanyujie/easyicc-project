package org.easymis.easyicc.service.impl;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.BusinessGroup;
import org.easymis.easyicc.service.BusinessGroupService;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
@Service
public class BusinessGroupServiceImpl implements BusinessGroupService {

	@Override
	public boolean save(BusinessGroup bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(BusinessGroup bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BusinessGroup findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findByOrgId(String orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo findByOrgId(String orgId, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
