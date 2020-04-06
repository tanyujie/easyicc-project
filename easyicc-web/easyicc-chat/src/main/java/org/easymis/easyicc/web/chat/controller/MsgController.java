package org.easymis.easyicc.web.chat.controller;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import org.easymis.easyicc.domain.entity.ChatRecord;
import org.easymis.easyicc.domain.entity.ChatRecordDetail;
import org.easymis.easyicc.domain.entity.VisitorInfo;
import org.easymis.easyicc.domain.vo.ChatRecordDetailVo;
import org.easymis.easyicc.service.ChatRecordDetailService;
import org.easymis.easyicc.service.ChatRecordService;
import org.easymis.easyicc.service.VisitorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MsgController {
	@Autowired
	private ChatRecordService chatRecordService;
	@Autowired
	private ChatRecordDetailService chatRecordDetailService;
	@Autowired
	private VisitorInfoService visitorInfoService;
	@RequestMapping("/msg.do")
	@ResponseBody
	public HashMap index(String cmd, String v, String u, String userId, String c, String ext, String keys[],
			String values[], String promotionId, String tag, String ref, Integer ocpcPlatform, Integer ocpcCondition,
			String ocpcConfigId, String g, String chatType, String sid,String cId,Integer start,Integer vStart,String msg,Integer force) throws NoSuchAlgorithmException {
		if (cmd.equals("getMessage"))
			return getMessage(c,v,u,cId,start,vStart);
		else if (cmd.equals("addEvent"))
			return addEvent("orgId");
		else if (cmd.equals("addMessage"))
			return addMessage(c,cId,v,msg,force);
		else if (cmd.equals("chat"))
			return chat( v,  u,  userId,  c,  ext,  keys,
					 values,  promotionId,  tag,  ref,  ocpcPlatform,  ocpcCondition,
					 ocpcConfigId,  g,  chatType,  sid,cId);
		return null;
		//return "/customerService";
	}
	/**
	 * 
	*<p>Title: addMessage</p>
	*<p>Description: </p>
	　 * @param orgId orgId,组织id
	　 * @param chatId 对话id
	　 * @param fromId|visitorId 访客id
	　 * @param message 消息内容
	　 * @param force
	　 * @return
	 */
	private HashMap addMessage(String orgId,String chatId,String fromId,String message,Integer force) {
		ChatRecordDetail bean= new ChatRecordDetail();
		bean.setOrgId(orgId);
		bean.setChatId(chatId);
		bean.setMessage(message);
		bean.setFromUserId(fromId);
		bean.setToUserId("AI-ylkj");
		chatRecordDetailService.save(bean);
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
	
	private HashMap getMessage(String orgId,String visitorId,String u,String chatId,Integer start,Integer vStart) throws NoSuchAlgorithmException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		ChatRecordDetailVo vo = new ChatRecordDetailVo();
		vo.setChatId(chatId);
		if(start==null)
			start=0;
		if(vStart==null)
			vStart=0;
		if (start > vStart)
			vo.setStart(start);
		else
			vo.setStart(vStart);
		map.put("result", "success");
		map.put("msgs", chatRecordDetailService.findList(vo));
		return map;
		//return "/msg/getMessage";
	}

	private HashMap addEvent(String orgId) throws NoSuchAlgorithmException {
		return new HashMap();
		//return "/msg/addEvent";
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
}
