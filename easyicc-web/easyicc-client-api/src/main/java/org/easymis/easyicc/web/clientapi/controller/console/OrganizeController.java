package org.easymis.easyicc.web.clientapi.controller.console;

import javax.validation.Valid;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.Organize;
import org.easymis.easyicc.service.OrganizeService;
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

@Api(value = "/organize", description = "公司信息配置")
@Controller
@RequestMapping("/organize")
public class OrganizeController extends IdentityRepository{
	@Autowired
	private OrganizeService service;
	@ApiOperation(value = "公司全局配置页面")
	@RequestMapping(value = { "/edit.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String edit(String name,ModelMap model) {
		model.put("organize", service.findById(getOrgId()));
		return "/console/organize/profile";
	}
	@ApiOperation(value = "查询接口", response = Organize.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "分类名称", dataType = "string", required = false),})
	@RequestMapping(value = { "/findPage.json" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult findByOrgId(String name,Integer pageNum, Integer pageSize) {
		String orgId = getOrgId();
		Organize bean = new Organize();
		bean.setOrgId(orgId);
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		return RestResult.buildSuccess(service.find(bean, pageNum, pageSize));
	}

	@ApiOperation(value = "保存组织全局配置")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "分类名称", dataType = "string", required = false),
		@ApiImplicitParam(name = "priority", value = "排序", dataType = "int", required = false),
		@ApiImplicitParam(name = "parentId", value = "parentId", dataType = "string", required = false),
		@ApiImplicitParam(name = "depict", value = "备注", dataType = "string", required = false),})
	@RequestMapping(value = { "/save.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult add(Organize bean) {
		if (service.save(bean))
			return RestResult.buildSuccess();
		else
			return RestResult.buildFail();
	}

	@ApiOperation(value = "修改组织全局配置信息")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "orgId", value = "分类名称", dataType = "string", required = false),
		@ApiImplicitParam(name = "orgName", value = "分类名称", dataType = "string", required = false),
		@ApiImplicitParam(name = "phone", value = "公司电话", dataType = "公司电话", required = false),
		@ApiImplicitParam(name = "fax", value = "公司传真", dataType = "string", required = false),
		@ApiImplicitParam(name = "url", value = "公司网址", dataType = "string", required = false),
		@ApiImplicitParam(name = "officeAddress", value = "公司地址", dataType = "string", required = false),
		@ApiImplicitParam(name = "depict", value = "备注", dataType = "string", required = false),})
	@RequestMapping(value = { "/update.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult update(@Valid Organize vBean) {
		Organize bean=service.findById(vBean.getOrgId());
		bean.setOrgName(vBean.getOrgName());
		bean.setPhone(vBean.getPhone());
		bean.setFax(vBean.getFax());
		bean.setUrl(vBean.getUrl());
		bean.setOfficeAddress(vBean.getOfficeAddress());
		bean.setDepict(vBean.getDepict());
		
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

	@ApiOperation(value = "删除组织全局配置信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "ids", value = "组织全局配置id列表", dataType = "string", required = false), })
	@RequestMapping(value = { "/delete.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult delete(String ids) {
		return service.deleteByIds(ids);
	}
}
