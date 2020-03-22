package org.easymis.easyicc.authorization.service.impl;

import org.easymis.easyicc.authorization.entity.Role;
import org.easymis.easyicc.authorization.provider.OrganizationProvider;
import org.easymis.easyicc.authorization.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private OrganizationProvider organizationProvider;

    @Override
    public Set<Role> queryUserRolesByUserId(String userId) {
        return organizationProvider.queryRolesByUserId(userId).getData();
    }

}
