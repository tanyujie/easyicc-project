package org.easymis.easyicc.authorization.provider;

import com.springboot.cloud.common.core.entity.vo.Result;

import org.easymis.easyicc.authorization.entity.Role;
import org.easymis.easyicc.authorization.entity.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class OrganizationProviderFallback implements OrganizationProvider {

    @Override
    public Result<User> getUserByUniqueId(String uniqueId) {
        return Result.success(new User());
    }

    @Override
    public Result<Set<Role>> queryRolesByUserId(String userId) {
        return Result.success(new HashSet<Role>());
    }
}
