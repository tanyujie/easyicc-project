package org.easymis.easyicc.web.chat.controller;

import java.security.NoSuchAlgorithmException;

import org.easymis.easyicc.domain.entity.ChatRecord;
import org.easymis.easyicc.service.ChatRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatController {
	@Autowired
    private ChatRecordService chatRecordService;
	@RequestMapping("/chat.do")
	public String index(String o,String v,String u,String config,String chatUrl,Model model) throws NoSuchAlgorithmException {
		model.addAttribute("orgId", o);
		model.addAttribute("visitorId", v);
		model.addAttribute("staffId", u);
		model.addAttribute("jsConfigId", config);
		model.addAttribute("chatUrl", chatUrl);
		ChatRecord chatRecord=chatRecordService.findByVisitorId(v);
		model.addAttribute("chatId", chatRecord.getChatId());
		return "/chat";
	}
	@RequestMapping("/blank.html")
	public String blank() throws NoSuchAlgorithmException {
		return "/chatBlank";
	}
}
