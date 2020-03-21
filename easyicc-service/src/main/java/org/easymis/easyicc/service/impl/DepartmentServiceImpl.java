package org.easymis.easyicc.service.impl;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.Department;
import org.easymis.easyicc.service.DepartmentService;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Override
	public boolean save(Department bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Department bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Department findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<?> find(Department bean, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
