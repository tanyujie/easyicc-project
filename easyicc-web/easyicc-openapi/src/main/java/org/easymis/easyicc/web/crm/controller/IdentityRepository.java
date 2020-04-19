package org.easymis.easyicc.web.crm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

public class IdentityRepository {
	@Autowired
	private HttpServletRequest request;

	/**
	 * 获取 主要的身份特征;
	 *
	 * @return
	 */

	public String getStaffId() {
		if (request.getAttribute("staffId") != null)
			return request.getAttribute("staffId").toString();
		else
			return "1";
	}

	public String getOrgId() {
		if (request.getAttribute("orgId") != null)
			return request.getAttribute("orgId").toString();
		else
			return "2018020111070003";
	}

}
