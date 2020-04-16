package org.easymis.easyicc.web.clientapi.controller.console;
//客服账号管理

import org.easymis.easyicc.domain.entity.HrmStaffInfo;
import org.easymis.easyicc.service.HrmStaffInfoService;
import org.easymis.easyicc.web.clientapi.controller.IdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value = "/iccAaccount", description = "客服账号管理")
@Controller
@RequestMapping("/iccAaccount")
public class ICCAaccountController extends IdentityRepository {
	@Autowired
	private HrmStaffInfoService service;
	@ApiOperation(value = "客服账号管理首页")
	@RequestMapping(value = { "/index.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String find(String name,Integer pageNum, Integer pageSize,ModelMap model) {
		String orgId = getOrgId();
		 HrmStaffInfo bean = new  HrmStaffInfo();
		bean.setOrgId(orgId);
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		model.put("pageInfo", service.find(bean, pageNum, pageSize));
		return "/iccAaccount/index";
	}
}
