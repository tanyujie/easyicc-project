package org.easymis.easyicc.web.report.controller;

import org.easymis.easyicc.domain.entity.HrmStaffInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/index", description = "数据统计")
@Controller
public class IndexController extends IdentityRepository {
	@ApiOperation(value = "数据统计管理首页")
	@RequestMapping(value = { "/index.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String find(String name,Integer pageNum, Integer pageSize,ModelMap model) {
		String orgId = getOrgId();
		 HrmStaffInfo bean = new  HrmStaffInfo();
		bean.setOrgId(orgId);
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		//model.put("pageInfo", service.find(bean, pageNum, pageSize));
		return "/hrmStaff/index";
	}
}
