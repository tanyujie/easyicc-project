package org.easymis.easyicc.service;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.IccFile;

import com.github.pagehelper.PageInfo;

public interface IccFileService {
	public boolean save(IccFile bean);

	public boolean update(IccFile bean);

	public IccFile findById(String id);


	public PageInfo<?> find(IccFile bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);
}
