package org.easymis.easyicc.service;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.JsConfig;

import com.github.pagehelper.PageInfo;

public interface JsConfigService {
	public boolean save(JsConfig bean);

	public boolean update(JsConfig bean);

	public JsConfig findById(String id);


	public PageInfo<?> find(JsConfig bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);
}
