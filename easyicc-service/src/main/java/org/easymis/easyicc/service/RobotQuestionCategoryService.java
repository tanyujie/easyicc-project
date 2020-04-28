package org.easymis.easyicc.service;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.RobotQuestionCategory;

import com.github.pagehelper.PageInfo;

public interface RobotQuestionCategoryService {
	public boolean save(RobotQuestionCategory bean);

	public boolean update(RobotQuestionCategory bean);

	public RobotQuestionCategory findById(String id);
	
	public List<RobotQuestionCategory> findByQuestion(RobotQuestionCategory bean);

	public PageInfo<?> find(RobotQuestionCategory bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);
}
