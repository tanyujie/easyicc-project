package org.easymis.easyicc.service;

import java.util.List;

import org.easymis.easyicc.domain.entity.SaleType;

public interface SaleTypeService {
	List<SaleType> findByOrgId(String orgId);
}
