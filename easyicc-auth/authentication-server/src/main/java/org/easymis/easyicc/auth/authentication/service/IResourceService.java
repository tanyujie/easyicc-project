package org.easymis.easyicc.auth.authentication.service;

import java.util.Set;

import org.easymis.easyicc.sysadmin.organization.entity.po.Resource;
import org.springframework.stereotype.Service;

@Service
public interface IResourceService {

    /**
     * 动态新增更新权限
     *
     * @param resource
     */
    void saveResource(Resource resource);

    /**
     * 动态删除权限
     *
     * @param resource
     */
    void removeResource(Resource resource);


    /**
     * 根据用户名查询 该用户所拥有的角色对应的资源信息
     *
     * @param username
     * @return
     */
    Set<Resource> queryByUsername(String username);
}
