package org.easymis.easyicc.web.clientapi.controller.console;

import org.easymis.easyicc.domain.entity.Site;
import org.easymis.easyicc.service.IccRoleService;
import org.easymis.easyicc.web.clientapi.controller.IdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value = "/role", description = "角色管理")
@Controller
@RequestMapping("/role")
public class RoleController extends IdentityRepository {
	@Autowired
	private IccRoleService service;
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
		Site bean = new Site();
		bean.setOrgId(orgId);
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		//model.put("pageInfo", service.find(bean, pageNum, pageSize));
		return "/console/role/assignRole";
	}
	
}
