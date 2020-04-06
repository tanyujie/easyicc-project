package org.easymis.easyicc.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.VisitorInfo;
import org.easymis.easyicc.mybatis.mapper.VisitorInfoMapper;
import org.easymis.easyicc.service.VisitorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class VisitorInfoServiceImpl implements VisitorInfoService {
	@Autowired
	private VisitorInfoMapper mapper;
	@Override
	public boolean save(VisitorInfo bean) {
		bean.setId(UUID.randomUUID().toString().replace("-", ""));
		bean.setCreateTime(new Date());
		return mapper.save(bean);
	}

	@Override
	public boolean update(VisitorInfo bean) {
		bean.setUpdateTime(new Date());
		return mapper.update(bean);
	}

	@Override
	public VisitorInfo findById(String id) {
		// TODO Auto-generated method stub
		return mapper.findById(id);
	}

	@Override
	public List findByOrgId(String orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveOrUpdate(VisitorInfo bean) {
		if (null != findById(bean.getChatId())) {
			bean.setUpdateTime(new Date());
			return update(bean);
		} else {
			bean.setCreateTime(new Date());
			return save(bean);
		}

	}

}
