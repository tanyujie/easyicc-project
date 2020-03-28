package org.easymis.easyicc.service;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.RebotQuestionCategory;

import com.github.pagehelper.PageInfo;

public interface RebotQuestionCategoryService {
	public boolean save(RebotQuestionCategory bean);

	public boolean update(RebotQuestionCategory bean);

	public RebotQuestionCategory findById(String id);
	
	public List<RebotQuestionCategory> findByQuestion(RebotQuestionCategory bean);

	public PageInfo<?> find(RebotQuestionCategory bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);
}
