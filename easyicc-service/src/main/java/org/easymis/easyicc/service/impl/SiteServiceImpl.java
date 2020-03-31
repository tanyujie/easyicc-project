package org.easymis.easyicc.service.impl;

import java.util.List;
import java.util.UUID;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.JsConfig;
import org.easymis.easyicc.domain.entity.Site;
import org.easymis.easyicc.mybatis.mapper.SiteMapper;
import org.easymis.easyicc.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class SiteServiceImpl implements SiteService {
	@Autowired
	private SiteMapper mapper;
	@Override
	public boolean save(Site bean) {
		bean.setId(UUID.randomUUID().toString().replace("-", ""));
		return mapper.save(bean);
	}

	@Override
	public boolean update(Site bean) {
		// TODO Auto-generated method stub
		return mapper.update(bean);
	}

	@Override
	public Site findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<?> find(Site bean, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Site> dataList = mapper.getList(bean);
		PageInfo<Site> p = new PageInfo<Site>(dataList);
		return p;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findByOrgId(String orgId) {
		// TODO Auto-generated method stub
		return mapper.findByOrgId(orgId);
	}

}
