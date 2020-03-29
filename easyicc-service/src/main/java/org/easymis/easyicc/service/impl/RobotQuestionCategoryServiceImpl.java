package org.easymis.easyicc.service.impl;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.RobotQuestionCategory;
import org.easymis.easyicc.mybatis.mapper.RobotQuestionCategoryMapper;
import org.easymis.easyicc.service.RebotQuestionCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
@Service
public class RobotQuestionCategoryServiceImpl implements RebotQuestionCategoryService {
	@Autowired
	private RobotQuestionCategoryMapper mapper;
	@Override
	public boolean save(RobotQuestionCategory bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(RobotQuestionCategory bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RobotQuestionCategory findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RobotQuestionCategory> findByQuestion(RobotQuestionCategory bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<?> find(RobotQuestionCategory bean, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
