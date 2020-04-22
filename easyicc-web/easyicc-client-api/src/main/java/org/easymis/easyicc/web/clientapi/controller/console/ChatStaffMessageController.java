package org.easymis.easyicc.web.clientapi.controller.console;

import org.easymis.easyicc.domain.entity.ChatStaffMessage;
import org.easymis.easyicc.service.ChatStaffMessageService;
import org.easymis.easyicc.web.clientapi.controller.IdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value = "/chatRecord", description = "内部聊天记录")
@Controller
@RequestMapping("/chatStaffMessage")
public class ChatStaffMessageController extends IdentityRepository{
	@Autowired
	private ChatStaffMessageService service;
	@ApiOperation(value = "内部聊天记录首页")
	@RequestMapping(value = { "/index.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String find(String name,Integer pageNum, Integer pageSize,ModelMap model) {
		String orgId = getOrgId();
		ChatStaffMessage bean = new ChatStaffMessage();
		bean.setOrgId(orgId);
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		model.put("pageInfo", service.find(bean, pageNum, pageSize));
		return "/console/chatStaffMessage/index";
	}
}
