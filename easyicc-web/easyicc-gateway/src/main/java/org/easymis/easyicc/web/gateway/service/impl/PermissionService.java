package org.easymis.easyicc.web.gateway.service.impl;

import java.util.concurrent.TimeUnit;

import org.easymis.easyicc.web.gateway.service.IAuthService;
import org.easymis.easyicc.web.gateway.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;

@Service
public class PermissionService implements IPermissionService {
    /**
     * 由authentication-client模块提供签权的feign客户端
     */
    @Autowired
    private IAuthService authService;
    @Override
    @Cached(name = "gateway_auth::", key = "#authentication+#method+#url",
            cacheType = CacheType.LOCAL, expire = 10, timeUnit = TimeUnit.SECONDS, localLimit = 10000)
	public boolean permission(String authentication, String url, String method) {
    	return authService.hasPermission(authentication, url, method);
	}
}
