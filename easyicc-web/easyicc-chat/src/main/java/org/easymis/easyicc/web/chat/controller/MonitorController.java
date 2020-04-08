package org.easymis.easyicc.web.chat.controller;

import java.security.NoSuchAlgorithmException;

import org.easymis.easyicc.domain.entity.ChatRecord;
import org.easymis.easyicc.service.ChatRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MonitorController {
	@Autowired
    private ChatRecordService chatRecordService;
	@RequestMapping("/i")
	public String index(Model model,String cmd,String o,String v,String u,String p,String ref,String h,String w,String scn,String t,String vc,String ac,String ic,String dc,String sid,String promotionId,String x) throws NoSuchAlgorithmException {
		if(cmd.equals("init"))
			return init(model,"orgId");
		else if(cmd.equals("add"))
			return add(model,o,v,u,p,ref,h,w,scn,t,vc,ac,ic,dc,sid,promotionId,x);
		else if(cmd.equals("getEvent"))
			return getEvent(model,"orgId");
		
		return "/customerService";
	}
	
	private String init(Model model,String orgId) throws NoSuchAlgorithmException {

		return "/monitor/init";
	}
	private String add(Model model,String orgId,String visitorId,String u,String p,String ref,String h,String w,String scn,String t,String vc,String ac,String ic,String dc,String sid,String promotionId,String x) throws NoSuchAlgorithmException {
		ChatRecord bean= new ChatRecord();
		bean.setOrgId(orgId);
		bean.setVisitorId(visitorId);
		chatRecordService.save(bean);
		model.addAttribute("chatId", bean.getChatId());
		model.addAttribute("visitorId", visitorId);
		return "/monitor/add";
	}	
	private String getEvent(Model model,String orgId)throws NoSuchAlgorithmException {

		return "/monitor/event";
	}	


}
