package org.easymis.easyicc.card.admin.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/index.html")
	public String index() throws NoSuchAlgorithmException {
		return "/index";
	}
	
}
