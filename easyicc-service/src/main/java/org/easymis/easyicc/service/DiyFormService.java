package org.easymis.easyicc.service;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.DiyForm;

import com.github.pagehelper.PageInfo;

public interface DiyFormService {
	public boolean save(DiyForm bean);

	public boolean update(DiyForm bean);

	public DiyForm findById(String id);


	public PageInfo<?> find(DiyForm bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);
}
