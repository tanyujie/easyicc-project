package org.easymis.easyicc.web.chat.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatController {

	@RequestMapping("/chat.do")
	public String index(String o,String v,String u,String config,String chatUrl) throws NoSuchAlgorithmException {
		ModelAndView mav = new ModelAndView();  
		mav.addObject("orgId", "o");
		mav.addObject("visitorId", "v");
		mav.addObject("staffId", "u");
		mav.addObject("jsConfigId", "config");
		mav.addObject("chatUrl", "chatUrl");
		return "/chat";
	}
	@RequestMapping("/blank.html")
	public String blank() throws NoSuchAlgorithmException {
		return "/chatBlank";
	}
}
