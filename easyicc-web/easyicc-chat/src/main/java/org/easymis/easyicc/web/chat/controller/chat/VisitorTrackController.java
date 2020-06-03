package org.easymis.easyicc.web.chat.controller.chat;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.ChatRecord;
import org.easymis.easyicc.service.ChatRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/visitorTrack", description = "访客轨迹")
@Controller
@RequestMapping("/visitorTrack")
public class VisitorTrackController {
	@Autowired
	private ChatRecordService chatRecordService;
	@ApiOperation(value = "访客轨迹")
	@RequestMapping("/index.json")
	@ResponseBody
	public RestResult currentChat(String visitorId,Model model) throws NoSuchAlgorithmException {
		ChatRecord chatRecord = chatRecordService.findById(visitorId);
		HashMap map = new HashMap();
		return RestResult.buildSuccess(map);
	}
}
