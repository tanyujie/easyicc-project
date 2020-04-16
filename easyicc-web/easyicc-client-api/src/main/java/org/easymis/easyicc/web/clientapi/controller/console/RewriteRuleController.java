package org.easymis.easyicc.web.clientapi.controller.console;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.RewriteRule;
import org.easymis.easyicc.domain.entity.Site;
import org.easymis.easyicc.service.RewriteRuleService;
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

@Api(value = "/rewriteRule", description = "接入策略管理")
@Controller
@RequestMapping("/rewriteRule")
public class RewriteRuleController extends IdentityRepository{
	@Autowired
	private RewriteRuleService service;
	@ApiOperation(value = "接入策略页面")
	@RequestMapping(value = { "/index.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String find(String name,ModelMap model) {
		String orgId = getOrgId();
		Site bean = new Site();
		bean.setOrgId(orgId);

		//model.put("pageInfo", service.find(bean, pageNum, pageSize));
		return "/console/rewriteRule/index";
	}
	@ApiOperation(value = "查询接口", response = RewriteRule.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "content", value = "常用语内容", dataType = "string", required = false),
			@ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int", required = false),
			@ApiImplicitParam(name = "pageSize", value = "每页显示记录", dataType = "int", required = false), })
	@RequestMapping(value = { "/findPage.json" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult findByOrgId(String content, Integer pageNum, Integer pageSize) {
		String orgId = getOrgId();
		RewriteRule bean = new RewriteRule();
		bean.setOrgId(orgId);

		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		//service.find(bean, pageNum, pageSize)
		return RestResult.buildSuccess();
	}

	@ApiOperation(value = "保存访客屏蔽")
	@ApiImplicitParams({ @ApiImplicitParam(name = "content", value = "常用语内容", dataType = "string", required = false),
			@ApiImplicitParam(name = "type", value = "类型", dataType = "string", required = false),
			@ApiImplicitParam(name = "priority", value = "排序", dataType = "int", required = false),
			@ApiImplicitParam(name = "title", value = "常用语标题", dataType = "string", required = false),
			@ApiImplicitParam(name = "hotKey", value = "hotKey", dataType = "string", required = false), })
	@RequestMapping(value = { "/save.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult add(RewriteRule bean) {
		bean.setOrgId(getOrgId());
		if (service.save(bean))
			return RestResult.buildSuccess();
		else
			return RestResult.buildFail();
	}

	@ApiOperation(value = "修改访客屏蔽信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "content", value = "常用语内容", dataType = "string", required = false),
			@ApiImplicitParam(name = "type", value = "类型", dataType = "string", required = false),
			@ApiImplicitParam(name = "priority", value = "排序", dataType = "int", required = false),
			@ApiImplicitParam(name = "title", value = "常用语标题", dataType = "string", required = false),
			@ApiImplicitParam(name = "hotKey", value = "hotKey", dataType = "string", required = false), })
	@RequestMapping(value = { "/update.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult update(RewriteRule bean) {
		if (service.update(bean))
			return RestResult.buildSuccess();
		else
			return RestResult.buildFail();
	}

	@ApiOperation(value = "查看访客屏蔽信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "ids", value = "访客屏蔽id", dataType = "string", required = false), })
	@RequestMapping(value = { "/read.json" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult read(String id) {
		return RestResult.buildSuccess(service.findById(id));
	}

	@ApiOperation(value = "删除访客屏蔽信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "ids", value = "访客屏蔽id列表", dataType = "string", required = false), })
	@RequestMapping(value = { "/delete.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult delete(String ids) {
		return service.deleteByIds(ids);
	}
}
