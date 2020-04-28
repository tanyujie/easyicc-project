package org.easymis.easyicc.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.RobotQuestion;
import org.easymis.easyicc.mybatis.mapper.RobotQuestionMapper;
import org.easymis.easyicc.service.RobotQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class RobotQuestionServiceImpl implements RobotQuestionService {
	@Autowired
	private RobotQuestionMapper mapper;

	@Override
	public boolean save(RobotQuestion bean) {
		bean.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		return mapper.save(bean);
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
		PageHelper.startPage(pageNum, pageSize);
		List<RobotQuestion> dataList = mapper.getList(bean);
		PageInfo<RobotQuestion> p = new PageInfo<RobotQuestion>(dataList);
		return p;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		List<String> idsList = Arrays.asList(ids.split(","));
		mapper.deleteBatch(idsList);
		return RestResult.buildSuccess();
	}

	@Override
	public List<RobotQuestion>  findByQuestion(RobotQuestion bean) {
		// TODO Auto-generated method stub
		return mapper.findByQuestion(bean);
	}
	
}
