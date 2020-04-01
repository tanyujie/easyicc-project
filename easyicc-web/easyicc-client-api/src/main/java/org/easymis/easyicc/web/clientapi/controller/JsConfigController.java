package org.easymis.easyicc.web.clientapi.controller;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.JsConfig;
import org.easymis.easyicc.domain.entity.PromotionChannel;
import org.easymis.easyicc.service.JsConfigService;
import org.easymis.easyicc.service.PromotionChannelService;
import org.easymis.easyicc.service.SiteService;
import org.easymis.easyicc.service.SkillGroupService;
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

@Api(value = "/jsConfig", description = "网页样式配置")
@Controller
@RequestMapping("/jsConfig")
public class JsConfigController extends IdentityRepository{
	@Autowired
	private JsConfigService service;
	@Autowired
	private SiteService siteService;
	@Autowired
	private PromotionChannelService promotionChannelService;
	@Autowired
	private SkillGroupService skillGroupService;
	@ApiOperation(value = "网页样式配置首页")
	@RequestMapping(value = { "/index.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String find(String name,Integer pageNum, Integer pageSize,ModelMap model) {
		String orgId = getOrgId();
		JsConfig bean = new JsConfig();
		bean.setOrgId(orgId);
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		model.put("pageInfo", service.find(bean, pageNum, pageSize));
		return "/jsConfig/index";
	}
	
	@ApiOperation(value = "查询接口", response = PromotionChannel.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "分类名称", dataType = "string", required = false),})
	@RequestMapping(value = { "/findPage.json" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult findPage(String name,Integer pageNum, Integer pageSize) {
		String orgId = getOrgId();
		JsConfig bean = new JsConfig();
		bean.setOrgId(orgId);
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		return RestResult.buildSuccess(service.find(bean, pageNum, pageSize));
	}

	@ApiOperation(value = "新增网页样式配置")
	@RequestMapping(value = { "/add.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String add(ModelMap model) {
		String orgId = getOrgId();
		model.put("siteList", siteService.findByOrgId(orgId));
		model.put("promotionChannelList", promotionChannelService.findByOrgId(orgId));
		model.put("skillGroupList", skillGroupService.findByOrgId(orgId));
		
		
		return "/jsConfig/add";
	}
	@ApiOperation(value = "保存网页样式配置")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "分类名称", dataType = "string", required = false),
		@ApiImplicitParam(name = "priority", value = "排序", dataType = "int", required = false),
		@ApiImplicitParam(name = "parentId", value = "parentId", dataType = "string", required = false),
		@ApiImplicitParam(name = "depict", value = "备注", dataType = "string", required = false),})
	@RequestMapping(value = { "/save.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult save(JsConfig bean) {
		bean.setOrgId(getOrgId());
		if (service.save(bean))
			return RestResult.buildSuccess();
		else
			return RestResult.buildFail();
	}
	@ApiOperation(value = "新增网页样式配置")
	@RequestMapping(value = { "/edit.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String edit(ModelMap model) {
		String orgId = getOrgId();
		model.put("siteList", siteService.findByOrgId(orgId));
		model.put("promotionChannelList", promotionChannelService.findByOrgId(orgId));
		model.put("skillGroupList", skillGroupService.findByOrgId(orgId));
		
		
		return "/jsConfig/edit";
	}
	@ApiOperation(value = "修改网页样式配置信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "分类名称", dataType = "string", required = false),
		@ApiImplicitParam(name = "priority", value = "排序", dataType = "int", required = false),
		@ApiImplicitParam(name = "parentId", value = "parentId", dataType = "string", required = false),
		@ApiImplicitParam(name = "depict", value = "备注", dataType = "string", required = false),})
	@RequestMapping(value = { "/update.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult update(JsConfig bean) {
		if (service.update(bean))
			return RestResult.buildSuccess();
		else
			return RestResult.buildFail();
	}

	@ApiOperation(value = "查看网页样式配置信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "ids", value = "网页样式配置id", dataType = "string", required = false), })
	@RequestMapping(value = { "/read.json" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult read(String id) {
		return RestResult.buildSuccess(service.findById(id));
	}

	@ApiOperation(value = "删除网页样式配置信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "ids", value = "网页样式配置id列表", dataType = "string", required = false), })
	@RequestMapping(value = { "/delete.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult delete(String ids) {
		return service.deleteByIds(ids);
	}
}
