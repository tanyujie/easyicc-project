package org.easymis.easyicc.web.openapi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.easymis.easyicc.domain.entity.RobotQuestion;
import org.easymis.easyicc.domain.vo.answer.AnswerIntent;
import org.easymis.easyicc.domain.vo.answer.AnswerResult;
import org.easymis.easyicc.domain.vo.answer.AnswerVo;
import org.easymis.easyicc.domain.vo.question.QuestionVo;
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

@Api(value = "/chat", description = "组织全局配置")
@Controller
@RequestMapping("/chat")
public class ChatController extends IdentityRepository{

	@Autowired
	private RobotQuestionService service;
	
	@ApiOperation(value = "查询接口", response = AnswerVo.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "分类名称", dataType = "string", required = false),})
	@RequestMapping(value = { "" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public AnswerVo index(QuestionVo questionVo) {
		String orgId = getOrgId();
		RobotQuestion bean = new RobotQuestion();
		bean.setOrgId(orgId);
		
		bean.setQuestion(questionVo.getPerception().getInputText().getText());
/*		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;*/
		List<RobotQuestion>  questionList= service.findByQuestion(bean);
		AnswerVo answerVo=new AnswerVo();
		List<AnswerResult> answerResultList=new ArrayList();
		if(questionList!=null) {
			for(int i=0;i<questionList.size();i++) {
				AnswerResult answerResult= new AnswerResult();
				RobotQuestion robotQuestion= questionList.get(i);
				answerResult.setGroupType(0);
				answerResult.setResultType(robotQuestion.getResultType());
				HashMap values = new HashMap();
				values.put(robotQuestion.getResultType(), robotQuestion.getAnswer());
				answerResult.setValues(values);
				answerResultList.add(answerResult);				
			}

		}
		answerVo.setResults(answerResultList);
		answerVo.setIntent(new AnswerIntent());
		return new AnswerVo();
	}
}
