package org.easymis.easyicc.web.clientapi.controller.crm;

import org.easymis.easyicc.web.clientapi.controller.IdentityRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

@Api(value = "/cardsMan", description = "imCRM首页")
@Controller
@RequestMapping("/im/crm/home")
public class CrmHomeController extends IdentityRepository{

}
