package org.easymis.easyicc.auth.authentication.service.impl;

import java.util.Set;

import org.easymis.easyicc.auth.authentication.service.IResourceService;
import org.easymis.easyicc.sysadmin.organization.entity.po.Resource;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ResourceService implements IResourceService {@Override
	public void saveResource(Resource resource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeResource(Resource resource) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public Set<Resource> queryByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}}