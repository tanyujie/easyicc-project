package org.easymis.easyicc.service;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.MiniHtml;

import com.github.pagehelper.PageInfo;

public interface MiniHtmlService {
	public boolean save(MiniHtml bean);

	public boolean update(MiniHtml bean);

	public MiniHtml findById(String id);


	public PageInfo<?> find(MiniHtml bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);
}
