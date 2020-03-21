package org.easymis.easyicc.web.clientapi.controller;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.CommonLanguage;
import org.easymis.easyicc.domain.entity.School;
import org.easymis.easyicc.service.CommonLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "/commonLanguage", description = "常用语")
@Controller
@RequestMapping("/commonLanguage")
public class CommonLanguageController extends IdentityRepository {
	@Autowired
	private CommonLanguageService service;

	@ApiOperation(value = "查询接口", response = School.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "content", value = "常用语内容", dataType = "string", required = false),
			@ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int", required = false),
			@ApiImplicitParam(name = "pageSize", value = "每页显示记录", dataType = "int", required = false), })
	@RequestMapping(value = { "/findByOrgId.json" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult findByOrgId(String content, Integer pageNum, Integer pageSize) {
		String orgId = getOrgId();
		CommonLanguage bean = new CommonLanguage();
		bean.setOrgId(orgId);
		bean.setContent(content);
		return RestResult.buildSuccess(service.findByOrgId(bean, pageNum, pageSize));
	}

	@ApiOperation(value = "保存个人常用语")
	@ApiImplicitParams({ @ApiImplicitParam(name = "content", value = "常用语内容", dataType = "string", required = false),
			@ApiImplicitParam(name = "type", value = "类型", dataType = "string", required = false),
			@ApiImplicitParam(name = "priority", value = "排序", dataType = "int", required = false),
			@ApiImplicitParam(name = "title", value = "常用语标题", dataType = "string", required = false),
			@ApiImplicitParam(name = "hotKey", value = "hotKey", dataType = "string", required = false), })
	@RequestMapping(value = { "/save.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult add(CommonLanguage bean) {
		bean.setOrgId(getOrgId());
		bean.setStaffId(getStaffId());
		if (service.save(bean))
			return RestResult.buildSuccess();
		else
			return RestResult.buildFail();
	}

	@ApiOperation(value = "修改个人常用语信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "content", value = "常用语内容", dataType = "string", required = false),
			@ApiImplicitParam(name = "type", value = "类型", dataType = "string", required = false),
			@ApiImplicitParam(name = "priority", value = "排序", dataType = "int", required = false),
			@ApiImplicitParam(name = "title", value = "常用语标题", dataType = "string", required = false),
			@ApiImplicitParam(name = "hotKey", value = "hotKey", dataType = "string", required = false), })
	@RequestMapping(value = { "/update.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult update(CommonLanguage bean) {
		if (service.update(bean))
			return RestResult.buildSuccess();
		else
			return RestResult.buildFail();
	}

	@ApiOperation(value = "查看个人常用语信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "ids", value = "个人常用语id", dataType = "string", required = false), })
	@RequestMapping(value = { "/read.json" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult read(String schoolId) {
		return RestResult.buildSuccess(service.findById(schoolId));
	}

	@ApiOperation(value = "删除个人常用语信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "ids", value = "个人常用语id列表", dataType = "string", required = false), })
	@RequestMapping(value = { "/delete.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult delete(String ids) {
		return service.deleteByIds(ids);
	}
}
