package org.easymis.easyicc.web.clientapi.controller.console;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.HrmStaffInfo;
import org.easymis.easyicc.domain.entity.PromotionChannel;
import org.easymis.easyicc.service.HrmStaffInfoService;
import org.easymis.easyicc.web.clientapi.controller.IdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "/hrmStaffInfo", description = "客服账号管理")
@Controller
@RequestMapping("/hrmStaffInfo")
public class HrmStaffInfoController extends IdentityRepository{
	@Autowired
	private HrmStaffInfoService service;
	@ApiOperation(value = "客服账号管理首页")
	@RequestMapping(value = { "/index.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String find(String name,Integer pageNum, Integer pageSize,ModelMap model) {
		String orgId = getOrgId();
		 HrmStaffInfo bean = new  HrmStaffInfo();
		bean.setOrgId(orgId);
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		model.put("pageInfo", service.find(bean, pageNum, pageSize));
		return "/hrmStaff/index";
	}
	@ApiOperation(value = "查询接口", response = PromotionChannel.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "分类名称", dataType = "string", required = false),})
	@RequestMapping(value = { "/findPage.json" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult findByOrgId(String name,Integer pageNum, Integer pageSize) {
		String orgId = getOrgId();
		HrmStaffInfo bean = new HrmStaffInfo();
		bean.setOrgId(orgId);
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		return RestResult.buildSuccess(service.find(bean, pageNum, pageSize));
	}
	@ApiOperation(value = "新增网页样式配置")
	@RequestMapping(value = { "/add.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String add() {
		return "/HrmStaffInfo/add";
	}
	@ApiOperation(value = "保存客服账号管理")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "分类名称", dataType = "string", required = false),
		@ApiImplicitParam(name = "priority", value = "排序", dataType = "int", required = false),
		@ApiImplicitParam(name = "parentId", value = "parentId", dataType = "string", required = false),
		@ApiImplicitParam(name = "depict", value = "备注", dataType = "string", required = false),})
	@RequestMapping(value = { "/save.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult save(HrmStaffInfo bean) {
		if (service.save(bean))
			return RestResult.buildSuccess();
		else
			return RestResult.buildFail();
	}

	@ApiOperation(value = "修改客服账号管理信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "分类名称", dataType = "string", required = false),
		@ApiImplicitParam(name = "priority", value = "排序", dataType = "int", required = false),
		@ApiImplicitParam(name = "parentId", value = "parentId", dataType = "string", required = false),
		@ApiImplicitParam(name = "depict", value = "备注", dataType = "string", required = false),})
	@RequestMapping(value = { "/update.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult update(HrmStaffInfo bean) {
		if (service.update(bean))
			return RestResult.buildSuccess();
		else
			return RestResult.buildFail();
	}

	@ApiOperation(value = "查看客服账号管理信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "ids", value = "客服账号管理id", dataType = "string", required = false), })
	@RequestMapping(value = { "/read.json" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult read(String id) {
		return RestResult.buildSuccess(service.findById(id));
	}

	@ApiOperation(value = "删除客服账号管理信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "ids", value = "客服账号管理id列表", dataType = "string", required = false), })
	@RequestMapping(value = { "/delete.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult delete(String ids) {
		return service.deleteByIds(ids);
	}
	@ApiOperation(value = "查询所有员工", notes = "JSON ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "/list.json" }, method = { RequestMethod.GET, RequestMethod.POST })
	public RestResult listJson(String orgId) throws Exception {
		return RestResult.buildSuccess(service.getListByDepartment("2018012402340575"));
	}
}
