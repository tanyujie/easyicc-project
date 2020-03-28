package org.easymis.easyicc.service;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.RobotQuestion;

import com.github.pagehelper.PageInfo;

public interface RobotQuestionService {

	public boolean save(RobotQuestion bean);

	public boolean update(RobotQuestion bean);

	public RobotQuestion findById(String id);
	
	public List<RobotQuestion> findByQuestion(RobotQuestion bean);

	public PageInfo<?> find(RobotQuestion bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);

}
