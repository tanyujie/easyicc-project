package org.easymis.easyicc.web.clientapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

@Api(value = "/rewriteRule", description = "客服分组管理")
@Controller
@RequestMapping("/skillGroup")
public class SkillGroupController extends IdentityRepository{

}
