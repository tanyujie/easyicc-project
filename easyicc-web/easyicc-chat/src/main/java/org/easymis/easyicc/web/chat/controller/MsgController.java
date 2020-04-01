package org.easymis.easyicc.web.chat.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MsgController {

	@RequestMapping("/index")
	public String index() throws NoSuchAlgorithmException {
		return "/customerService";
	}

}
