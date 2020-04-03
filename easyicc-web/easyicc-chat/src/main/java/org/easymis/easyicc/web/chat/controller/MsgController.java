package org.easymis.easyicc.web.chat.controller;

import java.security.NoSuchAlgorithmException;

import org.easymis.easyicc.domain.entity.ChatRecordDetail;
import org.easymis.easyicc.domain.vo.ChatRecordDetailVo;
import org.easymis.easyicc.service.ChatRecordDetailService;
import org.easymis.easyicc.service.ChatRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MsgController {
	@Autowired
	private ChatRecordService chatRecordService;
	@Autowired
	private ChatRecordDetailService chatRecordDetailService;
	@RequestMapping("/msg.do")
	public String index(String cmd, String v, String u, String userId, String c, String ext, String keys[],
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
					 ocpcConfigId,  g,  chatType,  sid);

		return "/customerService";
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
	private String addMessage(String orgId,String chatId,String fromId,String message,Integer force) {
		ChatRecordDetail bean= new ChatRecordDetail();
		bean.setOrgId(orgId);
		bean.setChatId(chatId);
		bean.setMessage(message);
		bean.setFromId(fromId);
		chatRecordDetailService.save(bean);
		return "/msg/addMessage";
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
	private String getMessage(String orgId,String visitorId,String u,String chatId,Integer start,Integer vStart) throws NoSuchAlgorithmException {
		ChatRecordDetailVo vo = new ChatRecordDetailVo();
		chatRecordDetailService.findList(vo);
		return "/msg/getMessage";
	}

	private String addEvent(String orgId) throws NoSuchAlgorithmException {

		return "/msg/addEvent";
	}

	private String chat(String v, String u, String userId, String c, String ext, String keys[],
			String values[], String promotionId, String tag, String ref, Integer ocpcPlatform, Integer ocpcCondition,
			String ocpcConfigId, String g, String chatType, String sid) throws NoSuchAlgorithmException {

		return "/msg/chat";
	}
}
