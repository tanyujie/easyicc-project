package org.easymis.easyicc.web.clientapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

@Api(value = "/rewriteRule", description = "策略配置管理")
@Controller
@RequestMapping("/rewriteRuleCondition")
public class RewriteRuleConditionController extends IdentityRepository{

}
