package org.easymis.easyicc.service;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.HtmlAlias;

import com.github.pagehelper.PageInfo;

public interface JsConfigService {
	public boolean save(HtmlAlias bean);

	public boolean update(HtmlAlias bean);

	public HtmlAlias findById(String id);


	public PageInfo<?> find(HtmlAlias bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);
}
