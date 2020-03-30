package org.easymis.easyicc.service.impl;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.JsConfig;
import org.easymis.easyicc.mybatis.mapper.JsConfigMapper;
import org.easymis.easyicc.service.JsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

@Service
public class JsConfigServiceImpl implements JsConfigService{
	@Autowired
	private JsConfigMapper mapper;
	@Override
	public boolean save(JsConfig bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(JsConfig bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public JsConfig findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<?> find(JsConfig bean, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
