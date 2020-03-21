package org.easymis.easyicc.web.clientapi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

public class IdentityRepository{
	@Autowired
	private HttpServletRequest request;

    /**
     * 获取 主要的身份特征;
     *
     * @return
     */

    public String getStaffId() {
        return request.getAttribute("userId").toString();
    }
    public String getOrgId() {
        return request.getAttribute("companyId").toString();
    }

}
