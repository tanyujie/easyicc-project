package org.easymis.easyicc.service.impl;

import java.util.Arrays;
import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.CommonLanguageCategory;
import org.easymis.easyicc.mybatis.mapper.CommonLanguageCategoryMapper;
import org.easymis.easyicc.service.CommonLanguageCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CommonLanguageCategoryServiceImpl implements CommonLanguageCategoryService{
	@Autowired
	private CommonLanguageCategoryMapper mapper;
	@Override
	public boolean save(CommonLanguageCategory bean) {
		// TODO Auto-generated method stub
		return mapper.save(bean);
	}

	@Override
	public boolean update(CommonLanguageCategory bean) {
		// TODO Auto-generated method stub
		return mapper.update(bean);
	}

	@Override
	public CommonLanguageCategory findById(String id) {
		// TODO Auto-generated method stub
		return mapper.findById(id);
	}


	@Override
	public PageInfo find(CommonLanguageCategory bean, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<CommonLanguageCategory> dataList = mapper.getList(bean);
		PageInfo<CommonLanguageCategory> p = new PageInfo<CommonLanguageCategory>(dataList);
		return p;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		List<String> idsList = Arrays.asList(ids.split(","));
		mapper.deleteBatch(idsList);
		return RestResult.buildSuccess();
	}

}
