package org.easymis.easyicc.web.clientapi.controller;

import org.easymis.easyicc.service.CommonLanguageCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

@Api(value = "/commonLanguageCategory", description = "常用语类别")
@Controller
@RequestMapping("/commonLanguageCategory")
public class CommonLanguageCategoryController {
	@Autowired
	private CommonLanguageCategoryService service;
}
