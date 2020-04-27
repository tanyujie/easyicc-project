package org.easymis.easyicc.web.clientapi.controller.console;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.CommonLanguage;
import org.easymis.easyicc.domain.entity.Robot;
import org.easymis.easyicc.service.RobotService;
import org.easymis.easyicc.web.clientapi.controller.IdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/rewriteRule", description = "知识库分类")
@Controller
@RequestMapping("/robot")
public class RobotController  extends IdentityRepository {
	@Autowired
	private RobotService service;
	@ApiOperation(value = "机器人配置首页")
	@RequestMapping(value = { "/index.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String find(String name, Integer pageNum, Integer pageSize, ModelMap model) {
		String orgId = getOrgId();
		Robot bean = new Robot();
		bean.setOrgId(orgId);
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		model.put("pageInfo", service.find(bean, pageNum, pageSize));
		return "/console/robot/index";
	}

	@ApiOperation(value = "机器人问候语配置查询")
	@RequestMapping(value = { "/queryGreeting.json" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String queryGreeting(ModelMap model) {

		return "/console/changePassword/index";
	}
	@ApiOperation(value = "保存机器人")
	@RequestMapping(value = { "/save.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult save(Robot bean) {
		bean.setOrgId(getOrgId());
		if (service.save(bean))
			return RestResult.buildSuccess();
		else
			return RestResult.buildFail();
	}
}
