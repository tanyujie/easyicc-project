package org.easymis.easyicc.authorization.service.impl;

import org.easymis.easyicc.authorization.entity.User;
import org.easymis.easyicc.authorization.provider.OrganizationProvider;
import org.easymis.easyicc.authorization.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private OrganizationProvider organizationProvider;

    @Override
    public User getByUniqueId(String uniqueId) {
        return organizationProvider.getUserByUniqueId(uniqueId).getData();
    }
}
