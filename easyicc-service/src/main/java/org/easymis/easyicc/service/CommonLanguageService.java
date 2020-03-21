package org.easymis.easyicc.service;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.CommonLanguage;

import com.github.pagehelper.PageInfo;

public interface CommonLanguageService {
	public boolean save(CommonLanguage bean);

	public boolean update(CommonLanguage bean);

	public CommonLanguage findById(String id);


	public PageInfo<?> findByOrgId(CommonLanguage bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);
}
