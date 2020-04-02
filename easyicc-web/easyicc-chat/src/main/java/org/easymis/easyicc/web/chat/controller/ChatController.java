package org.easymis.easyicc.web.chat.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {

	@RequestMapping("/chat.do")
	public String index() throws NoSuchAlgorithmException {
		return "/chat";
	}
	@RequestMapping("/blank.html")
	public String blank() throws NoSuchAlgorithmException {
		return "/chatBlank";
	}
}
