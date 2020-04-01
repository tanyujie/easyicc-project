package org.easymis.easyicc.web.chat.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MsgController {

	@RequestMapping("/msg")
	public String index(String cmd) throws NoSuchAlgorithmException {
		if(cmd.equals("getMessage"))
			return getMessage("orgId");
		return "/customerService";
	}
	public String getMessage(String orgId) throws NoSuchAlgorithmException {

		return "/customerService";
	}	

}
