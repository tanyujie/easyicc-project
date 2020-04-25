package org.easymis.easyicc.web.clientapi.controller.console;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/rewriteRule", description = "知识库配置")
@Controller
@RequestMapping("/robotQuestion")
public class RobotQuestionController {
	@ApiOperation(value = "知识库配置首页")
	@RequestMapping(value = { "/index.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index(ModelMap model) {

		return "/console/robotQuestion/index";
	}
}
