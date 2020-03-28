package org.easymis.easyicc.service.impl;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.RewriteRule;
import org.easymis.easyicc.mybatis.mapper.RewriteRuleMapper;
import org.easymis.easyicc.service.RewriteRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RewriteRuleServiceImpl implements RewriteRuleService {
	@Autowired
	private RewriteRuleMapper mapper;

	@Override
	public boolean save(RewriteRule bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(RewriteRule bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RewriteRule findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}
}
