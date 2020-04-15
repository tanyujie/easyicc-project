package org.easymis.easyicc.web.clientapi.controller.console;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.Department;
import org.easymis.easyicc.domain.entity.PromotionChannel;
import org.easymis.easyicc.domain.entity.Site;
import org.easymis.easyicc.service.DepartmentService;
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

@Api(value = "/department", description = "部门管理")
@Controller
@RequestMapping("/department")
public class DepartmentController extends IdentityRepository {
	@Autowired
	private DepartmentService service;
	@ApiOperation(value = "部门管理首页")
	@RequestMapping(value = { "/index.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String find(String name,Integer pageNum, Integer pageSize,ModelMap model) {
		String orgId = getOrgId();
		Site bean = new Site();
		bean.setOrgId(orgId);
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		model.put("departmentList", service.findByOrgId(orgId));
		return "/console/department/index";
	}
	
	@ApiOperation(value = "查询接口", response = PromotionChannel.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "分类名称", dataType = "string", required = false),})
	@RequestMapping(value = { "/findPage.json" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult findByOrgId(String name,Integer pageNum, Integer pageSize) {
		String orgId = getOrgId();
		Department bean = new Department();
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
	public RestResult add(Department bean) {
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
	public RestResult update(Department bean) {
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
