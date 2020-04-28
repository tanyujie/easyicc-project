package org.easymis.easyicc.service.impl;

import java.util.List;
import java.util.UUID;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.RobotQuestionCategory;
import org.easymis.easyicc.mybatis.mapper.RobotQuestionCategoryMapper;
import org.easymis.easyicc.service.RobotQuestionCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class RobotQuestionCategoryServiceImpl implements RobotQuestionCategoryService {
	@Autowired
	private RobotQuestionCategoryMapper mapper;
	@Override
	public boolean save(RobotQuestionCategory bean) {
		bean.setCategoryId(UUID.randomUUID().toString().replaceAll("-", ""));
		return mapper.save(bean);
	}

	@Override
	public boolean update(RobotQuestionCategory bean) {
		// TODO Auto-generated method stub
		return mapper.update(bean);
	}

	@Override
	public RobotQuestionCategory findById(String id) {
		// TODO Auto-generated method stub
		return mapper.findById(id);
	}

	@Override
	public List<RobotQuestionCategory> findByQuestion(RobotQuestionCategory bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<?> find(RobotQuestionCategory bean, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<RobotQuestionCategory> dataList = mapper.getList(bean);
		PageInfo<RobotQuestionCategory> p = new PageInfo<RobotQuestionCategory>(dataList);
		return p;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
