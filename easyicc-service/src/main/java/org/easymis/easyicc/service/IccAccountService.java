package org.easymis.easyicc.service;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.IccAccount;

import com.github.pagehelper.PageInfo;

public interface IccAccountService {
	public boolean save(IccAccount bean);

	public boolean update(IccAccount bean);

	public IccAccount findById(String id);


	public PageInfo<?> find(IccAccount bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);
}
