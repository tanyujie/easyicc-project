package org.easymis.easyicc.service.impl;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.MiniHtml;
import org.easymis.easyicc.service.MiniHtmlService;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
@Service
public class MiniHtmlServiceImpl implements MiniHtmlService {

	@Override
	public boolean save(MiniHtml bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(MiniHtml bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MiniHtml findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<?> find(MiniHtml bean, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
