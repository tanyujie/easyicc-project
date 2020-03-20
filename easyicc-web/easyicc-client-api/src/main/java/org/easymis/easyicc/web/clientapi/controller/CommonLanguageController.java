package org.easymis.easyicc.web.clientapi.controller;

import org.easymis.easyicc.service.CommonLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

@Api(value = "/commonLanguage", description = "常用语")
@Controller
@RequestMapping("/commonLanguage")
public class CommonLanguageController {
	@Autowired
	private CommonLanguageService service;
}
