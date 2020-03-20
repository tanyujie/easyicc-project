package org.easymis.easyicc.web.clientapi.controller;

import org.easymis.easyicc.service.OrganizeConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

@Api(value = "/organizeConfig", description = "组织全局配置")
@Controller
@RequestMapping("/organizeConfig")
public class OrganizeConfigController {
	@Autowired
	private OrganizeConfigService service;
}
