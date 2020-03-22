package org.easymis.easyicc.auth.authentication.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public interface IAuthenticationService {
    /**
     * 校验权限
     *
     * @param authRequest
     * @return 是否有权限
     */
    boolean decide(HttpServletRequest authRequest);

}
