package org.easymis.easyicc.web.robot.controller;





import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.Robot;
import org.easymis.easyicc.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
@Api(value = "/robot", description = "机器人管理")
@Controller
@RequestMapping("/robot")
public class RobotController extends IdentityRepository{
	@Autowired
	private RobotService service;
	
	@ApiOperation(value = "跳转首页")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "机器人名称", dataType = "string", required = false),})
	@RequestMapping(value = { "/index.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index(String name,Integer pageNum, Integer pageSize,Model model) {
		String orgId = getOrgId();
		Robot bean = new Robot();
		bean.setOrgId(orgId);
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		model.addAttribute("pageInfo",service.find(bean, pageNum, pageSize));
		return "/robot/index";
	}
	
	@ApiOperation(value = "查询接口", response = Robot.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "机器人名称", dataType = "string", required = false),})
	@RequestMapping(value = { "/findPage.json" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult findByOrgId(String name,Integer pageNum, Integer pageSize) {
		String orgId = getOrgId();
		Robot bean = new Robot();
		bean.setOrgId(orgId);
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		return RestResult.buildSuccess(service.find(bean, pageNum, pageSize));
	}

	@ApiOperation(value = "保存机器人")
	@RequestMapping(value = { "/add.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String add(Robot bean) {
		return "/robot/add";
	}
	
	@ApiOperation(value = "保存机器人")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "机器人名称", dataType = "string", required = false),
		@ApiImplicitParam(name = "priority", value = "排序", dataType = "int", required = false),
		@ApiImplicitParam(name = "parentId", value = "parentId", dataType = "string", required = false),
		@ApiImplicitParam(name = "depict", value = "备注", dataType = "string", required = false),})
	@RequestMapping(value = { "/save.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult save(Robot bean) {
		if (service.save(bean))
			return RestResult.buildSuccess();
		else
			return RestResult.buildFail();
	}

	@ApiOperation(value = "修改机器人信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "机器人名称", dataType = "string", required = false),
		@ApiImplicitParam(name = "priority", value = "排序", dataType = "int", required = false),
		@ApiImplicitParam(name = "parentId", value = "parentId", dataType = "string", required = false),
		@ApiImplicitParam(name = "depict", value = "备注", dataType = "string", required = false),})
	@RequestMapping(value = { "/update.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult update(Robot bean) {
		if (service.update(bean))
			return RestResult.buildSuccess();
		else
			return RestResult.buildFail();
	}

	@ApiOperation(value = "查看机器人信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "ids", value = "机器人id", dataType = "string", required = false), })
	@RequestMapping(value = { "/read.json" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult read(String id) {
		return RestResult.buildSuccess(service.findById(id));
	}

	@ApiOperation(value = "删除机器人信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "ids", value = "机器人id列表", dataType = "string", required = false), })
	@RequestMapping(value = { "/delete.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult delete(String ids) {
		return service.deleteByIds(ids);
	}
}
