package org.easymis.easyicc.service;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.VisitorFilter;

import com.github.pagehelper.PageInfo;

public interface VisitorFilterService {
	public boolean save(VisitorFilter bean);

	public boolean update(VisitorFilter bean);

	public VisitorFilter findById(String id);


	public PageInfo<?> find(VisitorFilter bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);
}
