package org.easymis.easyicc.service;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.CommonLanguageCategory;

import com.github.pagehelper.PageInfo;

public interface CommonLanguageCategoryService {
	public boolean save(CommonLanguageCategory bean);

	public boolean update(CommonLanguageCategory bean);

	public CommonLanguageCategory findById(String id);

	public PageInfo findByOrgId(CommonLanguageCategory bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);
}
