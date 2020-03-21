package org.easymis.easyicc.service;

import org.easymis.easyicc.domain.entity.OrganizeConfig;

public interface OrganizeConfigService {
	public boolean save(OrganizeConfig bean);

	public boolean update(OrganizeConfig bean);

	public OrganizeConfig findById(String id);

}
