package org.easymis.easyicc.service;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.VisitorInfo;

public interface VisitorInfoService {
	boolean save(VisitorInfo bean);

	boolean update(VisitorInfo bean);

	boolean saveOrUpdate(VisitorInfo bean);

	VisitorInfo findById(String id);

	public List findByOrgId(String orgId);

	RestResult deleteByIds(String ids);
}
