package org.easymis.easyicc.service.impl;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.RobotQuestion;
import org.easymis.easyicc.mybatis.mapper.RobotQuestionMapper;
import org.easymis.easyicc.service.RobotQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
@Service
public class RobotQuestionServiceImpl implements RobotQuestionService {
	@Autowired
	private RobotQuestionMapper mapper;

	@Override
	public boolean save(RobotQuestion bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(RobotQuestion bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RobotQuestion findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<?> find(RobotQuestion bean, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RobotQuestion>  findByQuestion(RobotQuestion bean) {
		// TODO Auto-generated method stub
		return mapper.findByQuestion(bean);
	}
	
}
