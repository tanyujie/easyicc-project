package org.easymis.easyicc.service.impl;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.VisitorFilter;
import org.easymis.easyicc.service.VisitorFilterService;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
@Service
public class VisitorFilterServiceImpl implements VisitorFilterService {

	@Override
	public boolean save(VisitorFilter bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(VisitorFilter bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public VisitorFilter findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<?> find(VisitorFilter bean, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
