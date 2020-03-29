package org.easymis.easyicc.web.scripts.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.easymis.easyicc.domain.entity.RobotChatDetail;
import org.easymis.easyicc.domain.entity.RobotQuestion;
import org.easymis.easyicc.domain.vo.answer.AnswerIntent;
import org.easymis.easyicc.domain.vo.answer.AnswerResult;
import org.easymis.easyicc.domain.vo.answer.AnswerVo;
import org.easymis.easyicc.domain.vo.question.QuestionVo;
import org.easymis.easyicc.service.RobotChatDetailService;
import org.easymis.easyicc.service.RobotQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "/chat", description = "客户端聊天javaScripts")
@Controller
@RequestMapping("/")
public class ScriptsController extends IdentityRepository{

	@Autowired
	private RobotQuestionService service;
	@Autowired
	private RobotChatDetailService chatDetailService;
	
	@ApiOperation(value = "查询接口", response = AnswerVo.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "分类名称", dataType = "string", required = false),})
	@RequestMapping(value = { "/{orgId}/{pageId}.js" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index(QuestionVo questionVo) {

		return "/index";
	}
}
