package org.easymis.easyicc.web.chat.controller.chat;

import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/visitorTrack", description = "访客轨迹")
@Controller
@RequestMapping("/visitorTrack")
public class VisitorTrackController {
	@ApiOperation(value = "访客轨迹")
	@RequestMapping("/index.json")
	public String currentChat(String o,String v,String u,String config,String chatUrl,Model model) throws NoSuchAlgorithmException {
		model.addAttribute("orgId", o);
		model.addAttribute("visitorId", v);
		model.addAttribute("staffId", u);
		model.addAttribute("jsConfigId", config);
		model.addAttribute("chatUrl", chatUrl);
		//ChatRecord chatRecord=chatRecordService.findByVisitorId(v);
		//model.addAttribute("chatId", chatRecord.getChatId());
		return "/chat";
	}
}
