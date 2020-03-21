package org.easymis.easyicc.web.clientapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

@Api(value = "/site", description = "部门管理")
@Controller
@RequestMapping("/site")
public class DepartmentController extends IdentityRepository {

}
