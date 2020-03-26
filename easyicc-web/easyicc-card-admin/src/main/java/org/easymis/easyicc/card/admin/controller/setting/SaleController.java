package org.easymis.easyicc.card.admin.controller.setting;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.easymis.easyicc.card.admin.controller.IdentityRepository;
import org.easymis.easyicc.service.BusinessGroupService;
import org.easymis.easyicc.service.SaleService;
import org.easymis.easyicc.service.SaleTypeService;
import org.easymis.easyicc.service.SchoolService;
import org.easymis.easyicc.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
@Api(value = "/scheduling", description = "排班班次")
@Controller
@RequestMapping("/scheduling")
public class SaleController extends IdentityRepository{
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private SaleTypeService saleTypeService;
	
	@Autowired
	private BusinessGroupService businessGroupService;

	
	protected String getPrefix() {
		return "setting/sale";
	}

	
	protected Object getQueryParams(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyId", getOrgId());
		this.initIntegerParam("subjectId", params, request);
		this.initIntegerParam("schoolId", params, request);
		this.initIntegerParam("businessGroupId", params, request);
		this.initIntegerParam("salesTypeId", params, request);
		return params;
	}
	
	private void initIntegerParam(String key, Map<String, Object> params, HttpServletRequest request){
		String value = request.getParameter(key);
		if(value != null){
			params.put(key, Integer.parseInt(value));
		}
	}
	
	protected void indexInit(ModelMap model) {
		Map<String, Object> params = new HashMap<String, Object>();
		String orgId = getOrgId();
		params.put("companyId", orgId);
		model.put("users", this.saleService.getUsers(orgId));
		model.put("schools", this.schoolService.findByOrgId(orgId));
		model.put("subjects", this.subjectService.findByOrgId(orgId));
		model.put("saleTypes", this.saleTypeService.findByOrgId(orgId));
		model.put("businessGroups", this.businessGroupService.findByOrgId(orgId));
	}

	
	protected String getEditPage() {
		return "edit";
	}
	

}
