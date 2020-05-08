package org.easymis.easyicc.web.portal.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/index.html")
	public String index() throws NoSuchAlgorithmException {
		return "/index";
	}
	@RequestMapping("/introduce.html")
	public String introduce() throws NoSuchAlgorithmException {
		return "/introduce";
	}
	@RequestMapping("/solve.html")
	public String solve() throws NoSuchAlgorithmException {
		return "/solve";
	}
	@RequestMapping("/price.html")
	public String price() throws NoSuchAlgorithmException {
		return "/price";
	}
	@RequestMapping("/customer.html")
	public String customer() throws NoSuchAlgorithmException {
		return "/customer";
	}
	@RequestMapping("/demo.html")
	public String demo() throws NoSuchAlgorithmException {
		return "/demo";
	}
	@RequestMapping("/download.html")
	public String download() throws NoSuchAlgorithmException {
		return "/download";
	}
	@RequestMapping("/about-us.html")
	public String aboutUs() throws NoSuchAlgorithmException {
		return "/aboutUs";
	}
	
}
