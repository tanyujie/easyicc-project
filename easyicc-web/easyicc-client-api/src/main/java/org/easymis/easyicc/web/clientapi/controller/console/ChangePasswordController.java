package org.easymis.easyicc.web.clientapi.controller.console;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/changePassword", description = "修改密码")
@Controller
@RequestMapping("/changePassword")
public class ChangePasswordController {
	@ApiOperation(value = "修改密码")
	@RequestMapping(value = { "/index.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index(ModelMap model) {

		return "/console/changePassword/index";
	}
	@ApiOperation(value = "保存修改密码")
	@RequestMapping(value = { "/save.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String save(ModelMap model) {

		return "/console/changePassword/index";
	}
}
