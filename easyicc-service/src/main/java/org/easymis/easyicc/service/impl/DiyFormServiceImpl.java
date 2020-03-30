package org.easymis.easyicc.service.impl;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.DiyForm;
import org.easymis.easyicc.service.DiyFormService;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
@Service
public class DiyFormServiceImpl implements DiyFormService {

	@Override
	public boolean save(DiyForm bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(DiyForm bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DiyForm findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<?> find(DiyForm bean, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
