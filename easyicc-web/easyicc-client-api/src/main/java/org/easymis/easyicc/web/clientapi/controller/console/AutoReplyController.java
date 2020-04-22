package org.easymis.easyicc.web.clientapi.controller.console;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.AutoReply;
import org.easymis.easyicc.service.AutoReplyService;
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
@Api(value = "/autoReply", description = "自动回复设置")
@Controller
@RequestMapping("/autoReply")
public class AutoReplyController extends IdentityRepository{
	@Autowired
	private AutoReplyService service;
	@ApiOperation(value = "自动回复设置首页")
	@RequestMapping(value = { "/edit.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String find(String name, Integer pageNum, Integer pageSize, ModelMap model) {
		String orgId = getOrgId();
		AutoReply bean = new AutoReply();
		//bean.setOrgId(orgId);
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		model.put("pageInfo", service.find(bean, pageNum, pageSize));
		return "/console/hrmStaffInfo/autoReply";
	}
	@ApiOperation(value = "保存自动回复设置")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "分类名称", dataType = "string", required = false),
		@ApiImplicitParam(name = "priority", value = "排序", dataType = "int", required = false),
		@ApiImplicitParam(name = "parentId", value = "parentId", dataType = "string", required = false),
		@ApiImplicitParam(name = "depict", value = "备注", dataType = "string", required = false),})
	@RequestMapping(value = { "/save.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult add(AutoReply bean) {
		if (service.save(bean))
			return RestResult.buildSuccess();
		else
			return RestResult.buildFail();
	}

	@ApiOperation(value = "修改自动回复设置信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "分类名称", dataType = "string", required = false),
		@ApiImplicitParam(name = "priority", value = "排序", dataType = "int", required = false),
		@ApiImplicitParam(name = "parentId", value = "parentId", dataType = "string", required = false),
		@ApiImplicitParam(name = "depict", value = "备注", dataType = "string", required = false),})
	@RequestMapping(value = { "/update.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult update(AutoReply bean) {
		if (service.update(bean))
			return RestResult.buildSuccess();
		else
			return RestResult.buildFail();
	}

	@ApiOperation(value = "查看自动回复设置信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "ids", value = "自动回复设置id", dataType = "string", required = false), })
	@RequestMapping(value = { "/read.json" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult read(String id) {
		return RestResult.buildSuccess(service.findById(id));
	}

	@ApiOperation(value = "删除自动回复设置信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "ids", value = "自动回复设置id列表", dataType = "string", required = false), })
	@RequestMapping(value = { "/delete.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult delete(String ids) {
		return null;//service.deleteByIds(ids);
	}
}
