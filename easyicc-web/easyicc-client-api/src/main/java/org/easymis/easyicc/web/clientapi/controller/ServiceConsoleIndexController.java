package org.easymis.easyicc.web.clientapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

@Api(value = "/service/Console/Index", description = "设置中心")
@Controller
@RequestMapping("/service/Console/Index")
public class ServiceConsoleIndexController  extends IdentityRepository{

}
