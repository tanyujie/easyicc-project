package org.easymis.easyicc.web.clientapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

@Api(value = "/visitorFilter", description = "总结标签管理")
@Controller
@RequestMapping("/summaryCategory")
public class SummaryCategoryController extends IdentityRepository{

}
