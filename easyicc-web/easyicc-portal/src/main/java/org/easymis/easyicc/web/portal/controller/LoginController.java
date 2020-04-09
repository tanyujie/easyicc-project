package org.easymis.easyicc.web.portal.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class LoginController {
	@RequestMapping("/login.html")
	public String blank() throws NoSuchAlgorithmException {
		return "/login";
	}
}
