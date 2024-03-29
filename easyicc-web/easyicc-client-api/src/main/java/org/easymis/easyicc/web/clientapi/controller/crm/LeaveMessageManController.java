package org.easymis.easyicc.web.clientapi.controller.crm;

import org.easymis.easyicc.domain.entity.ChatRecordDetail;
import org.easymis.easyicc.service.ChatRecordDetailService;
import org.easymis.easyicc.web.clientapi.controller.IdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value = "/leaveMessageMan", description = "im客户端访客留言")
@Controller
@RequestMapping("/im/leaveMessage")
public class LeaveMessageManController extends IdentityRepository {
	@Autowired
	private ChatRecordDetailService service;
	@ApiOperation(value = "im客户端访客留言")
	@RequestMapping(value = { "/index.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String find(String name,Integer pageNum, Integer pageSize,ModelMap model) {
		String orgId = getOrgId();
		ChatRecordDetail bean = new ChatRecordDetail();
		bean.setOrgId(orgId);
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		model.put("pageInfo", service.find(bean, pageNum, pageSize));
		return "/im/leaveMessage";
	}
}
