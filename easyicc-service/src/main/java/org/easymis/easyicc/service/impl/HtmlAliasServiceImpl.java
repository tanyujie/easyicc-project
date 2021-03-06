package org.easymis.easyicc.service.impl;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.HtmlAlias;
import org.easymis.easyicc.mybatis.mapper.HtmlAliasMapper;
import org.easymis.easyicc.service.HtmlAliasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class HtmlAliasServiceImpl implements HtmlAliasService {
	@Autowired
	private HtmlAliasMapper mapper;
	@Override
	public boolean save(HtmlAlias bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(HtmlAlias bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HtmlAlias findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<?> find(HtmlAlias bean, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<HtmlAlias> dataList = mapper.getList(bean);
		PageInfo<HtmlAlias> p = new PageInfo<HtmlAlias>(dataList);
		return p;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
