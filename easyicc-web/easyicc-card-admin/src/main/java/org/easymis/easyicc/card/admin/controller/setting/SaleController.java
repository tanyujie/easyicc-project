package org.easymis.easyicc.card.admin.controller.setting;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.easymis.easyicc.card.admin.controller.IdentityRepository;
import org.easymis.easyicc.service.BusinessGroupService;
import org.easymis.easyicc.service.SaleService;
import org.easymis.easyicc.service.SchoolService;
import org.easymis.easyicc.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.eutils.web.platform.permission.user.OnLine;
import cn.jesong.webcall.cuour.dao.HibernateDAO;
import cn.jesong.webcall.cuour.entity.Sales;
import cn.jesong.webcall.cuour.service.setting.SaleTypeService;
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

	@Override
	protected Object getQueryParams(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyId", OnLine.getCurrentUserDetails().getCompanyId());
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

	@Override
	protected HibernateDAO<String, Sales> getHibernateService() {
		return saleService;
	}
	
	
	
	@Override
	protected void createPageInit(HttpServletRequest request, ModelMap model) {
		model.put("users", this.saleService.getCouldSettingUser(OnLine.getCurrentUserDetails().getCompanyId()));
	}

	@Override
	protected void beforeCreateOrUpdate(Sales entity) {
		entity.setCompanyId(OnLine.getCurrentUserDetails().getCompanyId());
	}

	@Override
	protected void indexInit(ModelMap model) {
		Map<String, Object> params = new HashMap<String, Object>();
		int companyId = OnLine.getCurrentUserDetails().getCompanyId();
		params.put("companyId", companyId);
		model.put("users", this.saleService.getUsers(companyId));
		model.put("schools", this.schoolService.getListByTemplate(params));
		model.put("subjects", this.subjectService.getListByTemplate(params));
		model.put("saleTypes", this.saleTypeService.getListByTemplate(params));
		model.put("businessGroups", this.businessGroupService.getListByTemplate(params));
	}

	@Override
	protected String getEditPage() {
		return "edit";
	}
	

}
