package org.easymis.easyicc.web.clientapi.controller;

import org.easymis.easyicc.domain.entity.JsConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/console/index", description = "设置中心")
@Controller
@RequestMapping("/console")
public class ServiceConsoleIndexController  extends IdentityRepository{
	@ApiOperation(value = "设置中心首页")
	@RequestMapping(value = { "/index.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String find(String name,Integer pageNum, Integer pageSize,ModelMap model) {
		String orgId = getOrgId();
		JsConfig bean = new JsConfig();
		bean.setOrgId(orgId);
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		//model.put("pageInfo", service.find(bean, pageNum, pageSize));
		return "/console/index";
	}
}
