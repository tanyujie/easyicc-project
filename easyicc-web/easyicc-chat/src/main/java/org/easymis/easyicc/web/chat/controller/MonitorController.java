package org.easymis.easyicc.web.chat.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MonitorController {

	@RequestMapping("/i")
	public String index(String cmd) throws NoSuchAlgorithmException {
		if(cmd.equals("init"))
			return init("orgId");
		else if(cmd.equals("add"))
			return add("orgId");
		return "/customerService";
	}
	
	private String init(String orgId) throws NoSuchAlgorithmException {

		return "/monitor/init";
	}
	private String add(String orgId) throws NoSuchAlgorithmException {

		return "/monitor/add";
	}	
	private String getEvent(String orgId)throws NoSuchAlgorithmException {

		return "/monitor/event";
	}	


}
