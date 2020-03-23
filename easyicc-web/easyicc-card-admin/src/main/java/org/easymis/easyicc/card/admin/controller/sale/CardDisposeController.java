package org.easymis.easyicc.card.admin.controller.sale;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.easymis.easyicc.card.admin.controller.IdentityRepository;
import org.easymis.easyicc.domain.entity.Card;
import org.easymis.easyicc.service.AllocationCardService;
import org.easymis.easyicc.service.BackTypeService;
import org.easymis.easyicc.service.CardConfigService;
import org.easymis.easyicc.service.SchoolService;
import org.easymis.easyicc.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.eutils.web.platform.permission.user.OnLine;
import cn.eutils.web.platform.ui.PageConfig;
import cn.eutils.web.platform.ui.RespResult;
import cn.jesong.webcall.cuour.user.CuourUserDetail;

@Controller
@RequestMapping("/sale/card")
public class CardDisposeController extends IdentityRepository{

	private final static String PREFIX = "/sale/card";
	
	@Autowired
	private CardConfigService cardConfigService;
	
	@Autowired
	private AllocationCardService allocationCardService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private BackTypeService backTypeService;
	
	private final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@RequestMapping("/index")
	public String index(ModelMap model) throws Exception{
		String orgId = getOrgId();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyId", orgId);
		model.put("cols", cardConfigService.getShowVisitorCols(orgId));
		model.put("subjects", subjectService.findByOrgId(orgId));
		model.put("schools", this.schoolService.findByOrgId(orgId));
		return PREFIX + "/index";
	}
	
	
	@RequestMapping("/query")
	@ResponseBody
	public Page<Card> query(HttpServletRequest request) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyId", getOrgId());
		params.put("userId", getStaffId());
		String startTime = request.getParameter("startTime");
		if(startTime != null){
			params.put("startTime", formatter.parse(startTime));//+" 00:00:00"
		}
		String endTime = request.getParameter("endTime");
		if(endTime != null){
			params.put("endTime", formatter.parse(endTime));//+" 23:59:59"
		}
		CuourUserDetail ud = (CuourUserDetail)OnLine.getCurrentUserDetails();
		Page<Card> page = this.cardConfigService.pageCanDisposeVisitorCard(PageConfig.createPageConfig(request), params);
		if(ud.hasDataPermission("3d5c4d88-032f-409f-bf74-1b2f429d1216", "hideTelephone", "1")){
			for(Card card : page.getRows()){
				String noteString=card.getNote();
				String mobileString=card.getMobile();
				String telString=card.getTel();
				String qqString=card.getQq();
				String msnString=card.getMsn();
				card.setMobile(hidePhoneRule(mobileString));
				card.setQq(hidePhoneRule(qqString));
				card.setTel(hidePhoneRule(telString));
				card.setNote(hideMSNRule(noteString));
				card.setMsn(hideMSNRule(msnString));
				
			}
		}
		return page;
	}
	
	@RequestMapping(value = "/back", method = RequestMethod.GET)
	public String back(@RequestParam("cardId") int cardId, ModelMap model){
		model.put("backTypes", this.backTypeService.findByOrgId(getOrgId()));
		return PREFIX + "/form";
	}
	
	@RequestMapping(value = "/back", method = RequestMethod.POST)
	public void back(@RequestParam("cardId") String cardId, @RequestParam("backType") int backType,
			@RequestParam("desp") String desp, HttpServletResponse response) throws IOException{
		try {
			this.allocationCardService.back(getOrgId(), cardId, 
					getStaffId(), backType, desp);
			RespResult.getSuccess().writeToResponse(response);
		} catch (Exception e) {
			RespResult.getError(e).writeToResponse(response);
		}
	}
	
	@RequestMapping(value = "/dispose", method = RequestMethod.GET)
	public String dispose(@RequestParam("cardId") int cardId, ModelMap model){
		return PREFIX + "/dispose";
	}
	
	@RequestMapping(value = "/dispose", method = RequestMethod.POST)
	public void dispose(@RequestParam("cardId") String cardId, @RequestParam("desp") String desp, HttpServletResponse response) throws IOException{
		this.allocationCardService.finished(getOrgId(), cardId, 
				getStaffId(), desp);
		RespResult.getSuccess().writeToResponse(response);
	}
	
	public static String hidePhoneRule(String str){
		if(str != null && !str.equals("")){
			return str.replaceAll("([0-9]{3}-?)[0-9]{4}([0-9]{3,6})","$1****$2");
		}else {
			return "";
		}
		
	}
	//微信号隐藏和备注的隐藏
	public static String hideMSNRule(String fullString){
		String returnString ="";
		StringBuilder sb = new StringBuilder();
		String reg = "([\\S]{3})(.*)([\\S]{2})";
		Pattern r = Pattern.compile(reg);
		if(fullString != null && fullString != ""){
			fullString = fullString.replace("，", ",");
			String [] atring =fullString.split(",");
			for(int i=0;i<atring.length;i++){
				Matcher m = r.matcher(atring[i]);
				String hideString = "";
				if(m.find()){
					for (int y = 0; y <m.group(2).length(); y++) {
						hideString=hideString+"*";
		            }
					returnString += m.group(1)+hideString+m.group(3);
		        }
				if(i<atring.length-1){
					returnString +=",";
				}
			}
		}
		return returnString;

	}	
	
	
}
