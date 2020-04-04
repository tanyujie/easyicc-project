package org.easymis.easyicc.web.clientapi.controller.crm;

import org.easymis.easyicc.web.clientapi.controller.IdentityRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

@Api(value = "/cardsMan", description = "im查看表单")
@Controller
@RequestMapping("/im/formManage")
public class FormManageController extends IdentityRepository{

}
