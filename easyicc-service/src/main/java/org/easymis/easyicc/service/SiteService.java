package org.easymis.easyicc.service;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.Site;

import com.github.pagehelper.PageInfo;

public interface SiteService {
	public boolean save(Site bean);

	public boolean update(Site bean);

	public Site findById(String id);
	public List findByOrgId(String orgId);

	public PageInfo<?> find(Site bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);
}
