package org.easymis.easyicc.web.clientapi.controller.crm;

import org.easymis.easyicc.web.clientapi.controller.IdentityRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
@Api(value = "/leaveMessageMan", description = "im客户端营销诊断")
@Controller
@RequestMapping("/visitor/analyse/index")
public class VisitorAnalyseController extends IdentityRepository {

}
