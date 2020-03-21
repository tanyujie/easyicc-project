package org.easymis.easyicc.web.clientapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

@Api(value = "/site", description = "子站管理")
@Controller
@RequestMapping("/site")
public class SiteController extends IdentityRepository{

}
