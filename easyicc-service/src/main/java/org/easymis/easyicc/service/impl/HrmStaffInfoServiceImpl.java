package org.easymis.easyicc.service.impl;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.HrmStaffInfo;
import org.easymis.easyicc.service.HrmStaffInfoService;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
@Service
public class HrmStaffInfoServiceImpl implements HrmStaffInfoService {

	@Override
	public boolean save(HrmStaffInfo bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(HrmStaffInfo bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HrmStaffInfo findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<?> find(HrmStaffInfo bean, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
