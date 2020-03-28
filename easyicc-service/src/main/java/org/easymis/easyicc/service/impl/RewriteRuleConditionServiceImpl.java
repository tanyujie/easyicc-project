package org.easymis.easyicc.service.impl;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.RewriteRuleCondition;
import org.easymis.easyicc.mybatis.mapper.RewriteRuleConditionMapper;
import org.easymis.easyicc.service.RewriteRuleConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RewriteRuleConditionServiceImpl implements RewriteRuleConditionService {
	@Autowired
	private RewriteRuleConditionMapper mapper;
	@Override
	public boolean save(RewriteRuleCondition bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(RewriteRuleCondition bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RewriteRuleCondition findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
