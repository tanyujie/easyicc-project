package org.easymis.easyicc.card.admin.controller.sale;

import java.util.HashMap;
import java.util.Map;

import org.easymis.easyicc.card.admin.controller.IdentityRepository;
import org.easymis.easyicc.service.SchoolService;
import org.easymis.easyicc.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
@Api(value = "/sale/url", description = "销售地址")
@Controller
@RequestMapping("/sale/url")
public class SaleURLController extends IdentityRepository{

	private final static String PREFIX = "/sale/url";
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private SchoolService schoolService;
	
	@RequestMapping("/index")
	public String index(ModelMap model) throws Exception{
		String orgId = this.getOrgId();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyId", orgId);
		model.put("subjects", subjectService.findByOrgId(orgId));
		model.put("schooles", schoolService.findByOrgId(orgId));
		return PREFIX + "/index";
	}
	
}
