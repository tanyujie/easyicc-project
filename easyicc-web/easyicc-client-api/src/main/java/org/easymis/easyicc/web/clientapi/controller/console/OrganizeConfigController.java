package org.easymis.easyicc.web.clientapi.controller.console;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.OrganizeConfig;
import org.easymis.easyicc.domain.entity.Site;
import org.easymis.easyicc.service.OrganizeConfigService;
import org.easymis.easyicc.web.clientapi.controller.IdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "/organizeConfig", description = "组织全局配置")
@Controller
@RequestMapping("/organizeConfig")
public class OrganizeConfigController extends IdentityRepository{
	@Autowired
	private OrganizeConfigService service;
	@ApiOperation(value = "公司全局配置页面")
	@RequestMapping(value = { "/edit.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String find(String name,ModelMap model) {
		model.put("config", service.findOrgId(getOrgId()));
		return "/console/organize/config";
	}

	@ApiOperation(value = "保存组织全局配置")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "分类名称", dataType = "string", required = false),
		@ApiImplicitParam(name = "priority", value = "排序", dataType = "int", required = false),
		@ApiImplicitParam(name = "parentId", value = "parentId", dataType = "string", required = false),
		@ApiImplicitParam(name = "depict", value = "备注", dataType = "string", required = false),})
	@RequestMapping(value = { "/save.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult add(OrganizeConfig bean) {
		if (service.save(bean))
			return RestResult.buildSuccess();
		else
			return RestResult.buildFail();
	}

	@ApiOperation(value = "修改组织全局配置信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "分类名称", dataType = "string", required = false),
		@ApiImplicitParam(name = "priority", value = "排序", dataType = "int", required = false),
		@ApiImplicitParam(name = "parentId", value = "parentId", dataType = "string", required = false),
		@ApiImplicitParam(name = "depict", value = "备注", dataType = "string", required = false),})
	@RequestMapping(value = { "/update.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult update(OrganizeConfig bean) {
		if (service.update(bean))
			return RestResult.buildSuccess();
		else
			return RestResult.buildFail();
	}

	@ApiOperation(value = "查看组织全局配置信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "ids", value = "组织全局配置id", dataType = "string", required = false), })
	@RequestMapping(value = { "/read.json" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult read(String id) {
		return RestResult.buildSuccess(service.findById(id));
	}


}
