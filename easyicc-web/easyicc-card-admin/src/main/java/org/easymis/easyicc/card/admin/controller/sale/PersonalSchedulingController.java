package org.easymis.easyicc.card.admin.controller.sale;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.easymis.easyicc.card.admin.controller.IdentityRepository;
import org.easymis.easyicc.service.SchedulingUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
//个人排班
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
@Api(value = "/sale/scheduling", description = "个人排班")
@Controller
@RequestMapping("/sale/scheduling")
public class PersonalSchedulingController extends IdentityRepository{
	private final static String PREFIX = "/sale/scheduling";
	
	private final static SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	private SchedulingUserService schedulingUserService;
	@RequestMapping("/index")
	public String index(ModelMap model){
		return PREFIX + "/index";
	}
	@RequestMapping("/query")
	@ResponseBody
	public List<Map<String, Object>> query(@RequestParam(value = "startTime", required = true) String startTime) throws ParseException{
		return this.schedulingUserService.getPersonelSchedulings(getOrgId(), 
				getStaffId(), formatter2.parse(startTime));
	}
}
