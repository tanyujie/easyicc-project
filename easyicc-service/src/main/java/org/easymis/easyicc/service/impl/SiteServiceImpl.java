package org.easymis.easyicc.service.impl;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.Site;
import org.easymis.easyicc.service.SiteService;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
@Service
public class SiteServiceImpl implements SiteService {

	@Override
	public boolean save(Site bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Site bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Site findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<?> find(Site bean, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
