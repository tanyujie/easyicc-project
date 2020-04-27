package org.easymis.easyicc.web.clientapi.controller.console;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.Robot;
import org.easymis.easyicc.domain.entity.RobotQuestion;
import org.easymis.easyicc.service.RobotQuestionService;
import org.easymis.easyicc.service.RobotService;
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

@Api(value = "/rewriteRule", description = "知识库配置")
@Controller
@RequestMapping("/robotQuestion")
public class RobotQuestionController  extends IdentityRepository {
	@Autowired
	private RobotQuestionService service;
	@Autowired
	private RobotService robotService;
	@ApiOperation(value = "知识库配置首页")
	@RequestMapping(value = { "/index.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index(String name, Integer pageNum, Integer pageSize, ModelMap model) {
		String orgId = getOrgId();
		RobotQuestion bean = new RobotQuestion();
		bean.setOrgId(orgId);
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		
		model.put("robotList", robotService.findByOrgId(orgId));
		model.put("pageInfo", service.find(bean, pageNum, pageSize));
		return "/console/robotQuestion/index";
	}
	@ApiOperation(value = "保存知识")
	@RequestMapping(value = { "/save.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult save(RobotQuestion bean) {
		bean.setOrgId(getOrgId());
		if (service.save(bean))
			return RestResult.buildSuccess();
		else
			return RestResult.buildFail();
	}
	@ApiOperation(value = "删除知识")
	@ApiImplicitParams({ @ApiImplicitParam(name = "ids", value = "知识id", dataType = "string", required = false), })
	@RequestMapping(value = { "/delete.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RestResult delete(String ids) {
		return service.deleteByIds(ids);
	}
}
