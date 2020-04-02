package org.easymis.easyicc.web.chat.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MsgController {

	@RequestMapping("/msg")
	public String index(String cmd) throws NoSuchAlgorithmException {
		if (cmd.equals("getMessage"))
			return getMessage("orgId");
		else if (cmd.equals("addEvent"))
			return addEvent("orgId");
		else if (cmd.equals("chat"))
			return chat("orgId");

		return "/customerService";
	}

	private String getMessage(String orgId) throws NoSuchAlgorithmException {

		return "/msg/getMessage";
	}

	private String addEvent(String orgId) throws NoSuchAlgorithmException {

		return "/msg/addEvent";
	}

	private String chat(String orgId) throws NoSuchAlgorithmException {

		return "/msg/chat";
	}
}
