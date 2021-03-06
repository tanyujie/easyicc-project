package org.easymis.easyicc.web.scripts.controller;

import org.easymis.easyicc.domain.entity.JsConfig;
import org.easymis.easyicc.domain.vo.answer.AnswerVo;
import org.easymis.easyicc.service.JsConfigService;
import org.easymis.easyicc.service.RobotChatDetailService;
import org.easymis.easyicc.service.RobotQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "/", description = "客户端聊天javaScripts")
@Controller
@RequestMapping("/")
public class ScriptsController extends IdentityRepository{
	@Autowired
	private JsConfigService jsConfigService;
	@Autowired
	private RobotQuestionService robotQuestionService;
	@Autowired
	private RobotChatDetailService chatDetailService;
	
	@ApiOperation(value = "查询接口", response = AnswerVo.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "分类名称", dataType = "string", required = false),})
	@RequestMapping(value = { "/{pageId}.js" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index(@PathVariable String pageId,ModelMap model) {
		JsConfig jsConfig=jsConfigService.findById(pageId);
		model.put("jsConfig", jsConfig);
		return "/index";
	}
}
