package org.easymis.easyicc.web.clientapi.controller.console;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/loginLogSearch", description = "客服状态日志查询")
@Controller
@RequestMapping("/log")
public class LogController {
	@ApiOperation(value = "客服状态日志查询")
	@RequestMapping(value = { "/statusChange.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String statusChange(ModelMap model) {

		return "/console/log/loginLogSearch";
	}
	
	@ApiOperation(value = "客服状态日志查询")
	@RequestMapping(value = { "/loginLogSearch.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String loginLogSearch(ModelMap model) {

		return "/console/log/loginLogSearch";
	}
	
	@ApiOperation(value = "客服登录日志查询")
	@RequestMapping(value = { "/login.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String login(ModelMap model) {

		return "/console/log/loginLogSearch";
	}
	
	@ApiOperation(value = "客服登录日志查询")
	@RequestMapping(value = { "/loginRecordSearch.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String loginRecordSearch(ModelMap model) {

		return "/console/log/loginLogSearch";
	}
	
	@ApiOperation(value = "系统操作日志查询")
	@RequestMapping(value = { "/opration.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String opration(ModelMap model) {

		return "/console/log/loginLogSearch";
	}
	
	@ApiOperation(value = "系统操作日志查询")
	@RequestMapping(value = { "/oprationSearch.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String oprationSearch(ModelMap model) {

		return "/console/log/loginLogSearch";
	}	
}
