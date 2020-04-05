package org.easymis.easyicc.service;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.CommonLanguage;

import com.github.pagehelper.PageInfo;

public interface CommonLanguageService {
	public boolean save(CommonLanguage bean);

	public boolean update(CommonLanguage bean);

	public CommonLanguage findById(String id);

	public List<CommonLanguage> findByStaffId(String staffId);

	public List<CommonLanguage> findList(CommonLanguage bean);

	public PageInfo<?> find(CommonLanguage bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);

	/**
	 * 
	 * <p>
	 * Title: 按组织获取获取商用语
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param orgId
	 * 			@return
	 */
	public RestResult getListByTree(String orgId);
}
