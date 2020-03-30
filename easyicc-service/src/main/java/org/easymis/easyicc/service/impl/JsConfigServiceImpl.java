package org.easymis.easyicc.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.JsConfig;
import org.easymis.easyicc.mybatis.mapper.JsConfigMapper;
import org.easymis.easyicc.service.JsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class JsConfigServiceImpl implements JsConfigService{
	@Autowired
	private JsConfigMapper mapper;
	@Override
	public boolean save(JsConfig bean) {
		bean.setConfigId(UUID.randomUUID().toString().replace("-", ""));
		return mapper.save(bean);
	}

	@Override
	public boolean update(JsConfig bean) {
		// TODO Auto-generated method stub
		return mapper.update(bean);
	}

	@Override
	public JsConfig findById(String id) {
		// TODO Auto-generated method stub
		return mapper.findById(id);
	}

	@Override
	public PageInfo<?> find(JsConfig bean, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<JsConfig> dataList = mapper.getList(bean);
		PageInfo<JsConfig> p = new PageInfo<JsConfig>(dataList);
		return p;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		List<String> idsList = Arrays.asList(ids.split(","));
		mapper.deleteBatch(idsList);
		return RestResult.buildSuccess();
	}

}
