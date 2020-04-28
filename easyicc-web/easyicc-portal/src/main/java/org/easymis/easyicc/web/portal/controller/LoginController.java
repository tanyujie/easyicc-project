package org.easymis.easyicc.web.portal.controller;

import java.security.NoSuchAlgorithmException;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.common.utils.MD5Util;
import org.easymis.easyicc.domain.entity.Member;
import org.easymis.easyicc.domain.vo.LoginOto;
import org.easymis.easyicc.domain.vo.LoginVo;
import org.easymis.easyicc.service.MemberService;
import org.easymis.easyicc.web.portal.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
@Api(description = "门户系统登录")
@Validated
@Controller
@Slf4j
public class LoginController {
	@Autowired
	private MemberService memberService;
	@RequestMapping("/login.html")
	public String login() throws NoSuchAlgorithmException {
		return "/login";
	}
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	@ResponseBody
	public RestResult login(LoginVo ar) {		
		Member member=memberService.findByMobile(ar.getLoginName());
		if(member!=null) {
			System.out.println(MD5Util.md5(ar.getPassword()));
			if (MD5Util.md5(ar.getPassword()).equals(member.getPassword())) {
				LoginOto loginOto= new LoginOto();
				loginOto.setEasysaasToken(JwtTokenUtil.generateToken(member.getMemberId(),"2016110202340575"));
		        return RestResult.buildSuccess(loginOto);
			} else {
				return RestResult.buildFail("密码错误");
			}
		}		
		return RestResult.buildFail();
	}
}
