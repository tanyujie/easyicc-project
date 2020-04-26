package org.easymis.easyicc.web.clientapi.controller.console;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.IccAccount;
import org.easymis.easyicc.domain.entity.IccRole;
import org.easymis.easyicc.service.IccAccountService;
import org.easymis.easyicc.service.IccRoleService;
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
@Api(value = "/role", description = "角色管理")
@Controller
@RequestMapping("/role")
public class RoleController extends IdentityRepository {
	@Autowired
	private IccRoleService service;
	@Autowired
	private IccAccountService accountService;
	@ApiOperation(value = "角色管理首页")
	@RequestMapping(value = { "/index.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String find(ModelMap model) {
		String orgId = getOrgId();
		model.put("roleList", service.findByOrgId(orgId));
		return "/console/role/index";
	}
	@ApiOperation(value = "公司角色分配")
	@RequestMapping(value = { "/assignRole.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String assignRole(String name,Integer pageNum, Integer pageSize,ModelMap model) {
		String orgId = getOrgId();
		IccAccount bean = new IccAccount();
		bean.setOrgId(orgId);
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		model.put("pageInfo", accountService.find(bean, pageNum, pageSize));
		return "/console/role/assignRole";
	}
	@ApiOperation(value = "保存或修改角色")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "子站点名称", dataType = "string", required = false),
			@ApiImplicitParam(name = "depict", value = "子站点描述", dataType = "string", required = false), })
	@RequestMapping(value = { "/saveOrUpdate.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult saveOrUpdate(@Valid IccRole bean) {
		if (StringUtils.isEmpty(bean.getRoleId())) {
			bean.setOrgId(getOrgId());
			service.save(bean);
			return RestResult.buildSuccess();
		} else if (StringUtils.isNotBlank(bean.getRoleId())) {
			IccRole vBean=service.findById(bean.getRoleId());
			vBean.setRoleName(bean.getRoleName());
			vBean.setRoleStatus(bean.getRoleStatus());
			vBean.setDepict(bean.getDepict());
			service.update(vBean);
			return RestResult.buildSuccess();
		} else
			return RestResult.buildFail();

	}
	@ApiOperation(value = "保存子站点")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "子站点名称", dataType = "string", required = false),
			@ApiImplicitParam(name = "depict", value = "子站点描述", dataType = "string", required = false), })
	@RequestMapping(value = { "/save.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult save(@Valid IccRole bean) {
		bean.setOrgId(getOrgId());
		if (service.save(bean))
			return RestResult.buildSuccess();
		else
			return RestResult.buildFail();
	}

	@ApiOperation(value = "修改子站点信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "子站点名称", dataType = "string", required = false),
		@ApiImplicitParam(name = "depict", value = "子站点描述", dataType = "string", required = false), })
	@RequestMapping(value = { "/update.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult update(@Valid IccRole bean) {
		if (service.update(bean))
			return RestResult.buildSuccess();
		else
			return RestResult.buildFail();
	}
	@ApiOperation(value = "读取角色信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "ids", value = "��֯ȫ������id", dataType = "string", required = false), })
	@RequestMapping(value = { "/read.json" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult read(String id) {
		return RestResult.buildSuccess(service.findById(id));
	}
	@ApiOperation(value = "删除角色")
	@ApiImplicitParams({ @ApiImplicitParam(name = "ids", value = "角色id", dataType = "string", required = false), })
	@RequestMapping(value = { "/delete.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult delete(String ids) {
		return service.deleteByIds(ids);
	}
}
