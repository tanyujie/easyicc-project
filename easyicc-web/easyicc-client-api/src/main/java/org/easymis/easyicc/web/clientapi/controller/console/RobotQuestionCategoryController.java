package org.easymis.easyicc.web.clientapi.controller.console;

import org.easymis.easyicc.domain.entity.RobotQuestionCategory;
import org.easymis.easyicc.service.RobotQuestionCategoryService;
import org.easymis.easyicc.service.RobotService;
import org.easymis.easyicc.web.clientapi.controller.IdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/rewriteRule", description = "知识库分类")
@Controller
@RequestMapping("/robotQuestionCategory")
public class RobotQuestionCategoryController extends IdentityRepository {
	@Autowired
	private RobotQuestionCategoryService service;
	@Autowired
	private RobotService robotService;
	@ApiOperation(value = "知识库分类首页")
	@RequestMapping(value = { "/index.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index(String name, Integer pageNum, Integer pageSize, ModelMap model) {
		String orgId = getOrgId();
		RobotQuestionCategory bean = new RobotQuestionCategory();
		bean.setOrgId(orgId);
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		
		model.put("robotList", robotService.findByOrgId(orgId));
		model.put("pageInfo", service.find(bean, pageNum, pageSize));
		return "/console/robotQuestionCategory/index";
	}
}
