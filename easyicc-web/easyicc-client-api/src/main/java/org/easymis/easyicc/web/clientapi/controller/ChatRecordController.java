package org.easymis.easyicc.web.clientapi.controller;

import org.easymis.easyicc.service.ChatRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

@Api(value = "/chatRecord", description = "对话记录")
@Controller
@RequestMapping("/chatRecord")
public class ChatRecordController {
	@Autowired
	private ChatRecordService service;
}
