package org.easymis.easyicc.web.clientapi.controller;

import org.easymis.easyicc.service.ChatRecordDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

@Api(value = "/chatRecordDetail", description = "对话记录明细")
@Controller
@RequestMapping("/chatRecordDetail")
public class ChatRecordDetailController {
	@Autowired
	private ChatRecordDetailService service;
}