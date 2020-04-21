package org.easymis.easyicc.web.clientapi.controller.console;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.IccAccount;
import org.easymis.easyicc.service.IccAccountService;
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

@Api(value = "/dataReportConfig", description = "第三方数据转换上报")
@Controller
@RequestMapping("/dataReportConfig")
public class DataReportConfigController extends IdentityRepository{
	@Autowired
	private IccAccountService service;
	@ApiOperation(value = "图片素材管理首页")
	@RequestMapping(value = { "/index.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String find(String name, Integer pageNum, Integer pageSize, ModelMap model) {
		String orgId = getOrgId();
		IccAccount bean = new IccAccount();
		bean.setOrgId(orgId);
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		model.put("pageInfo", service.find(bean, pageNum, pageSize));
		return "/console/dataReportConfig/index";
	}
	
	@ApiOperation(value = "保存图片素材")
	@ApiImplicitParams({ @ApiImplicitParam(name = "code", value = "客服ID", dataType = "string", required = false),
			@ApiImplicitParam(name = "nick", value = "对内显示名称", dataType = "string", required = false),
			@ApiImplicitParam(name = "nickName", value = "对外显示名称", dataType = "string", required = false),
			@ApiImplicitParam(name = "maxAcceptCount", value = "客服最大接待量", dataType = "string", required = false),
			@ApiImplicitParam(name = "maxChatCount", value = "客服最大对话数", dataType = "string", required = false),})
	@RequestMapping(value = { "/save.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult save(@Valid IccAccount bean) {
		bean.setOrgId(getOrgId());
		if (service.save(bean))
			return RestResult.buildSuccess();
		else
			return RestResult.buildFail();
	}
	@ApiOperation(value = "保存图片素材")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "分类名称", dataType = "string", required = false),
		@ApiImplicitParam(name = "priority", value = "排序", dataType = "int", required = false),
		@ApiImplicitParam(name = "parentId", value = "parentId", dataType = "string", required = false),
		@ApiImplicitParam(name = "depict", value = "备注", dataType = "string", required = false),})
	@RequestMapping(value = { "/saveOrUpdate.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public RestResult saveOrUpdate(IccAccount bean) {
		if (StringUtils.isEmpty(bean.getId())) {
			bean.setOrgId(getOrgId());
			service.save(bean);
			return RestResult.buildSuccess();
		} else if (StringUtils.isNotBlank(bean.getId())) {
			IccAccount vBean=service.findById(bean.getId());
			service.update(vBean);
			return RestResult.buildSuccess();
		} else
			return RestResult.buildFail();
	}
}
