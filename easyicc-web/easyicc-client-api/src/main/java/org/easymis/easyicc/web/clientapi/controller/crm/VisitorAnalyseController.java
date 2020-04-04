package org.easymis.easyicc.web.clientapi.controller.crm;

import org.easymis.easyicc.domain.entity.ChatRecord;
import org.easymis.easyicc.service.ChatRecordService;
import org.easymis.easyicc.service.VisitorInfoService;
import org.easymis.easyicc.web.clientapi.controller.IdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value = "/visitor/analyse", description = "im客户端营销诊断")
@Controller
@RequestMapping("/visitor/analyse")
public class VisitorAnalyseController extends IdentityRepository {
	@Autowired
	private ChatRecordService service;
	@Autowired
	private VisitorInfoService visitorInfoService;
	@ApiOperation(value = "im客户端营销诊断")
	@RequestMapping(value = { "/index.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String find(String name,Integer pageNum, Integer pageSize,ModelMap model) {
		String orgId = getOrgId();
		ChatRecord bean = new ChatRecord();
		bean.setOrgId(orgId);
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		model.put("pageInfo", service.find(bean, pageNum, pageSize));
		return "/im/visitor/analyse/index";
	}
}
