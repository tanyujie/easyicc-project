package org.easymis.easyicc.web.portal.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class RegisterController {
	@RequestMapping("/register.html")
	public String blank() throws NoSuchAlgorithmException {
		return "/register";
	}
}
