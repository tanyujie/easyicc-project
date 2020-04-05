package org.easymis.easyicc.service;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.CommonLanguageCategory;

import com.github.pagehelper.PageInfo;

public interface CommonLanguageCategoryService {
	public boolean save(CommonLanguageCategory bean);

	public boolean update(CommonLanguageCategory bean);

	public CommonLanguageCategory findById(String id);

	public PageInfo find(CommonLanguageCategory bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);

	List<CommonLanguageCategory> findByOrgId(String orgId);
}
