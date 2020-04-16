package org.easymis.easyicc.web.clientapi.controller.console;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/monitor", description = "分组信息监控")
@Controller
@RequestMapping("/monitor")
public class MonitorController {
	@ApiOperation(value = "分组信息监控查询")
	@RequestMapping(value = { "/groupIndex.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String groupIndex(ModelMap model) {

		return "/console/monitor/groupIndex";
	}
	
	@ApiOperation(value = "分组信息监控查询")
	@RequestMapping(value = { "/pageGroup.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String pageGroup(ModelMap model) {

		return "/console/monitor/loginLogSearch";
	}
}
