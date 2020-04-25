package org.easymis.easyicc.web.clientapi.controller.console;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/rewriteRule", description = "知识库分类")
@Controller
@RequestMapping("/robot")
public class RobotController {
	@ApiOperation(value = "机器人问候语配置")
	@RequestMapping(value = { "/greeting.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String greeting(ModelMap model) {

		return "/console/robot/greeting";
	}
	@ApiOperation(value = "机器人问候语配置查询")
	@RequestMapping(value = { "/queryGreeting.json" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String queryGreeting(ModelMap model) {

		return "/console/changePassword/index";
	}
	@ApiOperation(value = "机器人问候语配置查询")
	@RequestMapping(value = { "/save.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String save(ModelMap model) {

		return "/console/changePassword/index";
	}
}
