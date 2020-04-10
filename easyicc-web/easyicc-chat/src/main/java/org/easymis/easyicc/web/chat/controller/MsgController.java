package org.easymis.easyicc.web.chat.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.easymis.easyicc.domain.entity.ChatRecord;
import org.easymis.easyicc.domain.entity.ChatRecordDetail;
import org.easymis.easyicc.domain.entity.RobotQuestion;
import org.easymis.easyicc.domain.entity.VisitorInfo;
import org.easymis.easyicc.domain.vo.ChatRecordDetailVo;
import org.easymis.easyicc.service.ChatRecordDetailService;
import org.easymis.easyicc.service.ChatRecordService;
import org.easymis.easyicc.service.RobotChatDetailService;
import org.easymis.easyicc.service.RobotQuestionService;
import org.easymis.easyicc.service.VisitorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;

@Controller
public class MsgController {
	@Autowired
	private ChatRecordService chatRecordService;
	@Autowired
	private ChatRecordDetailService chatRecordDetailService;
	@Autowired
	private VisitorInfoService visitorInfoService;
	
	@Autowired
	private RobotChatDetailService robotChatDetailService;
	
	@Autowired
	private RobotQuestionService robotQuestionService;
	
	@RequestMapping("/msg.do")
	@ResponseBody
	public String index(String cmd, String v, String u, String userId, String c, String ext, String keys[],
			String values[], String promotionId, String tag, String ref, Integer ocpcPlatform, Integer ocpcCondition,
			String ocpcConfigId, String g, String chatType, String sid, String cId, Integer start, Integer vstart,
			String msg, Integer force,String op,String b_op,String desp,String uId,String callback) throws NoSuchAlgorithmException {
		HashMap prinMap= new HashMap();
		if (cmd.equals("getMessage"))
			prinMap= getMessage(c, v, u, cId, start, vstart);
		else if (cmd.equals("addEvent"))
			prinMap= addEvent("orgId");
		else if (cmd.equals("addMessage"))
			prinMap= addMessage(c, cId, v, msg, force);
		else if (cmd.equals("chat"))
			prinMap= chat(v, u, userId, c, ext, keys, values, promotionId, tag, ref, ocpcPlatform, ocpcCondition,
					ocpcConfigId, g, chatType, sid, cId);
		else if (cmd.equals("opinion"))
			prinMap= opinion(cId, c, op, b_op, desp, v, u);
		else if (cmd.equals("isChatExist")) {
			prinMap= isChatExist(c, uId,force);			
		}
		JSONObject jsonObj = new JSONObject(prinMap);//转化为json格式
		if(StringUtil.isNotEmpty(callback)) {
			return callback+'('+jsonObj.toJSONString()+");";
		}

		return jsonObj.toJSONString();
		// return "/customerService";
	}
	/**
	 * 
	*<p>Title: 发送消息</p>
	*<p>Description: </p>
	　 * @param orgId orgId,组织id
	　 * @param chatId 对话id
	　 * @param fromId|visitorId 访客id
	　 * @param message 消息内容
	　 * @param force
	　 * @return
	 */
	private HashMap addMessage(String orgId,String chatId,String fromId,String message,Integer force) {
		ChatRecord chatRecord=chatRecordService.findById(chatId);
		ChatRecordDetail bean= new ChatRecordDetail();
		bean.setOrgId(orgId);
		bean.setChatId(chatId);
		bean.setMessage(transformEmoticon(message));
		bean.setFromUserId(fromId);
		bean.setType("RECORD_RECORD");
		bean.setToUserId("AI-ylkj");//客服 id
		chatRecordDetailService.save(bean);
		
		RobotQuestion robotQuestion = new RobotQuestion();
		robotQuestion.setOrgId(orgId);
		
		robotQuestion.setQuestion(message);
		List<RobotQuestion>  answerList=robotQuestionService.findByQuestion(robotQuestion);
		//机器人回复
		if(answerList!=null) {
			for(int i=0;i<answerList.size();i++) {
				RobotQuestion robotAnswer= answerList.get(i);				
				ChatRecordDetail answer= new ChatRecordDetail();
				answer.setOrgId(orgId);
				answer.setChatId(chatId);
				answer.setMessage(robotAnswer.getAnswer());
				answer.setFromUserId("AI-ylkj");
				answer.setType("RECORD_RECORD");
				answer.setToUserId(fromId);//客服 id
				chatRecordDetailService.save(answer);
		
			}

		}
		
		
		return new HashMap();
		
		//return "/msg/addMessage";
	}
/** 
*<p>Title: getMessage</p>
*<p>Description: </p>
　 * @param orgId
　 * @param visitorId 
　 * @param u
　 * @param chatId 对话id
　 * @param start 开始明细
　 * @param vStart
　 * @return
　 * @throws NoSuchAlgorithmException
 */
	
	private HashMap getMessage(String orgId, String visitorId, String u, String chatId, Integer start, Integer vstart)
			throws NoSuchAlgorithmException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		ChatRecordDetailVo vo = new ChatRecordDetailVo();
		vo.setChatId(chatId);
		List<ChatRecordDetail> list = new ArrayList();
		if (start == 0 && vstart == 0) {
			vo.setStart(start);
			list = chatRecordDetailService.findList(vo);
		}else if (start > vstart) {
			vo.setToUserId(visitorId);
			vo.setStart(start);
			list = chatRecordDetailService.findList(vo);
		} else {
			vo.setFromUserId(visitorId);
			vo.setStart(vstart);
		}
		map.put("result", "success");
		map.put("msgs", chatRecordDetailService.findList(vo));
		return map;
		// return "/msg/getMessage";
	}

	private HashMap addEvent(String orgId) throws NoSuchAlgorithmException {
		return new HashMap();
		//return "/msg/addEvent";
	}
	//添加评论
	private HashMap opinion(String chatId,String orgId,String op,String b_op,String desp,String visitorId,String u) throws NoSuchAlgorithmException {
		return new HashMap();
	}

	private HashMap isChatExist(String orgId, String uId, Integer force)
			throws NoSuchAlgorithmException {
		HashMap map = new HashMap();
		map.put("result", "success");
		map.put("exist", true);
		map.put("chatId", "343076868");
		map.put("customerId", "AI-ylkj");
		map.put("visitorId", "01000000012990630423857861418213");
		return map;
	}
	private HashMap chat(String visitorId, String u, String userId, String orgId, String ext, String keys[],
			String values[], String promotionId, String tag, String ref, Integer ocpcPlatform, Integer ocpcCondition,
			String ocpcConfigId, String g, String chatType, String sid,String chatId) throws NoSuchAlgorithmException {
		HashMap map = new HashMap();
		ChatRecord bean= new ChatRecord();
		bean.setOrgId(orgId);
		bean.setChatId(chatId);
		bean.setVisitorId(visitorId);
		chatRecordService.saveOrUpdate(bean);
		
		VisitorInfo visitorInfo= new VisitorInfo();
		visitorInfo.setId(visitorId);
		visitorInfo.setOrgId(orgId);
		visitorInfo.setChatId(chatId);
		//visitorInfoService.saveOrUpdate(visitorInfo);
		map.put("result", "success");
		map.put("isChatExists", true);
		HashMap userInfo= new HashMap();
		userInfo.put("timesOfToVisitorMsg", "2");
		userInfo.put("replyMsgAtConnected", "我是今天的值班客服，请问您需要咨询哪方面的问题？为了更好的服务效果，请在对话结束后对我的服务满意度进行评价，非常感谢您的支持。");
		userInfo.put("phoneNumber", null);
		userInfo.put("replyMsgOfCustToVisitor", "已经很久没有收到您的消息了，请问您还在电脑前吗？如果没有其他的问题我将主动关闭该对话。随时欢迎您再次向我咨询，祝您今天好心情。");
		userInfo.put("chatAutoCloseMsg", "非常感谢您的光临，因长时间未对话，系统将自动关闭该对话！");
		userInfo.put("nickName", "在线客服");
		userInfo.put("customerCardImg", "");
		userInfo.put("cycleTimeOfToVisitorMsg", "60");
		userInfo.put("department", "客服部");
		userInfo.put("userId", "AI-ylkj");
		userInfo.put("chatAutoCloseTime", "30");
		userInfo.put("email", null);
		map.put("userInfo", userInfo);
		
		map.put("chatId", chatId);
		map.put("waitIndex", null);
		map.put("groupId", "24135");
		map.put("type", "CHATING");
		map.put("waitSendMessage", "");
		map.put("visitorId", visitorId);
		
		
		
		
		return map;
		//return "/msg/chat";
	}

	private String transformEmoticon(String message) {
		message=message.replace("[01]", "<img src='emoticon/01.png' alt='' border='0' />");
		message=message.replace("[02]", "<img src='emoticon/02.png' alt='' border='0' />");
		message=message.replace("[03]", "<img src='emoticon/03.png' alt='' border='0' />");
		message=message.replace("[04]", "<img src='emoticon/04.png' alt='' border='0' />");
		message=message.replace("[05]", "<img src='emoticon/05.png' alt='' border='0' />");
		message=message.replace("[06]", "<img src='emoticon/06.png' alt='' border='0' />");
		message=message.replace("[07]", "<img src='emoticon/07.png' alt='' border='0' />");
		message=message.replace("[08]", "<img src='emoticon/08.png' alt='' border='0' />");
		message=message.replace("[09]", "<img src='emoticon/09.png' alt='' border='0' />");
		message=message.replace("[10]", "<img src='emoticon/10.png' alt='' border='0' />");
		message=message.replace("[11]", "<img src='emoticon/11.png' alt='' border='0' />");
		message=message.replace("[12]", "<img src='emoticon/12.png' alt='' border='0' />");
		message=message.replace("[13]", "<img src='emoticon/13.png' alt='' border='0' />");
		message=message.replace("[14]", "<img src='emoticon/14.png' alt='' border='0' />");
		message=message.replace("[15]", "<img src='emoticon/15.png' alt='' border='0' />");
		message=message.replace("[16]", "<img src='emoticon/16.png' alt='' border='0' />");
		message=message.replace("[17]", "<img src='emoticon/17.png' alt='' border='0' />");
		message=message.replace("[18]", "<img src='emoticon/18.png' alt='' border='0' />");
		return message;
	}
}
