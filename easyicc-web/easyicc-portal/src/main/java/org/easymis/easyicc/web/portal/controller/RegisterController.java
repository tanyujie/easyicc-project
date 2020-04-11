package org.easymis.easyicc.web.portal.controller;

import java.security.NoSuchAlgorithmException;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.vo.RegisterVo;
import org.easymis.easyicc.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class RegisterController {
	@Autowired
	private MemberService memberService;

	@RequestMapping("/register.html")
	public String registerHtml() throws NoSuchAlgorithmException {
		return "/register";
	}

	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	public RestResult registerDo(RegisterVo vo) {		
		return memberService.saveRegister(vo);
	}
}
