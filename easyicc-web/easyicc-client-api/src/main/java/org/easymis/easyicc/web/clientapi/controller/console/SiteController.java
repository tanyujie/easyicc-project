package org.easymis.easyicc.web.clientapi.controller.console;

import javax.validation.Valid;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.Site;
import org.easymis.easyicc.service.SiteService;
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


@Api(value = "/site", description = "站点管理")
@Controller
@RequestMapping("/site")
public class SiteController extends IdentityRepository{
	@Autowired
	private SiteService service;
	@ApiOperation(value = "站点管理首页")
	@RequestMapping(value = { "/index.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String find(String name,Integer pageNum, Integer pageSize,ModelMap model) {
		String orgId = getOrgId();
		Site bean = new Site();
		bean.setOrgId(orgId);
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		model.put("pageInfo", service.find(bean, pageNum, pageSize));
		return "/console/site/index";
	}
	@ApiOperation(value = "站点查询", response = Site.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "��������", dataType = "string", required = false),})
	@RequestMapping(value = { "/findPage.json" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult findByOrgId(String name,Integer pageNum, Integer pageSize) {
		String orgId = getOrgId();
		Site bean = new Site();
		bean.setOrgId(orgId);
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		return RestResult.buildSuccess(service.find(bean, pageNum, pageSize));
	}
	@ApiOperation(value = "新增子站点")
	@RequestMapping(value = { "/add.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String add(ModelMap model) {
		String orgId = getOrgId();
		
		return "/site/add";
	}
	@ApiOperation(value = "保存子站点")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "子站点名称", dataType = "string", required = false),
			@ApiImplicitParam(name = "depict", value = "子站点描述", dataType = "string", required = false), })
	@RequestMapping(value = { "/save.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult save(@Valid Site bean) {
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
	public RestResult update(@Valid Site bean) {
		if (service.update(bean))
			return RestResult.buildSuccess();
		else
			return RestResult.buildFail();
	}

	@ApiOperation(value = "�鿴��֯ȫ��������Ϣ")
	@ApiImplicitParams({ @ApiImplicitParam(name = "ids", value = "��֯ȫ������id", dataType = "string", required = false), })
	@RequestMapping(value = { "/read.json" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult read(String id) {
		return RestResult.buildSuccess(service.findById(id));
	}

	@ApiOperation(value = "ɾ����֯ȫ��������Ϣ")
	@ApiImplicitParams({ @ApiImplicitParam(name = "ids", value = "��֯ȫ������id�б�", dataType = "string", required = false), })
	@RequestMapping(value = { "/delete.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult delete(String ids) {
		return service.deleteByIds(ids);
	}
}
