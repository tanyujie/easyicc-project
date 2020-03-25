package org.easymis.easyicc.service;

import java.util.List;

import org.easymis.easyicc.domain.entity.CardRule;

public interface CardRuleService {
	CardRule findByOrgId(String orgId);

	List<CardRule> findByServerName(String serverName);

	void saveOrUpdate(CardRule bean);

	void save(CardRule bean);

	void update(CardRule bean);
}
