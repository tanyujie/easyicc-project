package org.easymis.easyicc.service.impl;

import java.util.List;

import org.easymis.easyicc.domain.entity.CardRule;
import org.easymis.easyicc.mybatis.mapper.CardRuleMapper;
import org.easymis.easyicc.service.CardRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CardRuleServiceImpl implements CardRuleService {
	@Autowired
	private CardRuleMapper mapper;
	@Override
	public CardRule findByOrgId(String orgId) {
		// TODO Auto-generated method stub
		return mapper.findByOrgId(orgId);
	}

	@Override
	public void saveOrUpdate(CardRule bean) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(CardRule bean) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(CardRule bean) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CardRule> findByServerName(String serverName) {
		// TODO Auto-generated method stub
		return mapper.findByServerName(serverName);
	}

	@Override
	public List<CardRule> findExpiredCards(String serverName) {
		// TODO Auto-generated method stub
		return mapper.findExpiredCards(serverName);
	}

}
