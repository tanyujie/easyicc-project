package org.easymis.easyicc.service;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.RewriteRule;

public interface RewriteRuleService {
	boolean save(RewriteRule bean);
	boolean update(RewriteRule bean);
	RewriteRule findById(String id);
	RestResult deleteByIds(String ids);
}
