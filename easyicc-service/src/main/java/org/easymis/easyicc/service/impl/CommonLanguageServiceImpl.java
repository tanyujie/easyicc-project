package org.easymis.easyicc.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.CommonLanguage;
import org.easymis.easyicc.mybatis.mapper.CommonLanguageMapper;
import org.easymis.easyicc.service.CommonLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CommonLanguageServiceImpl implements CommonLanguageService {
	@Autowired
	private CommonLanguageMapper mapper;

	@Override
	public boolean save(CommonLanguage bean) {
		bean.setId(UUID.randomUUID().toString().replace("-", ""));
		return mapper.save(bean);
	}

	@Override
	public boolean update(CommonLanguage bean) {
		// TODO Auto-generated method stub
		return mapper.update(bean);
	}

	@Override
	public CommonLanguage findById(String id) {
		// TODO Auto-generated method stub
		return mapper.findById(id);
	}

	@Override
	public RestResult deleteByIds(String ids) {
		List<String> idsList = Arrays.asList(ids.split(","));
		mapper.deleteBatch(idsList);
		return RestResult.buildSuccess();
	}

	@Override
	public PageInfo<?> findByOrgId(CommonLanguage bean, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<CommonLanguage> dataList = mapper.getList(bean);
		PageInfo<CommonLanguage> p = new PageInfo<CommonLanguage>(dataList);
		return p;
	}

}
