package org.easymis.easyicc.service.impl;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.RebotQuestionCategory;
import org.easymis.easyicc.service.RebotQuestionCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
@Service
public class RebotQuestionCategoryServiceImpl implements RebotQuestionCategoryService {
	@Autowired
	private RebotQuestionCategoryMapper mapper;
	@Override
	public boolean save(RebotQuestionCategory bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(RebotQuestionCategory bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RebotQuestionCategory findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RebotQuestionCategory> findByQuestion(RebotQuestionCategory bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<?> find(RebotQuestionCategory bean, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
