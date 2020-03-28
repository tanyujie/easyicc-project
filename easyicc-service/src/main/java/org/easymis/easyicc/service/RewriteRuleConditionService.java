package org.easymis.easyicc.service;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.RewriteRuleCondition;

public interface RewriteRuleConditionService {
	boolean save(RewriteRuleCondition bean);

	boolean update(RewriteRuleCondition bean);

	RewriteRuleCondition findById(String id);

	RestResult deleteByIds(String ids);

}
