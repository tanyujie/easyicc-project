package org.easymis.easyicc.card.admin.controller.report;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.easymis.easyicc.card.admin.controller.IdentityRepository;
import org.easymis.easyicc.domain.entity.Card;
import org.easymis.easyicc.domain.entity.CustomerReport;
import org.easymis.easyicc.domain.vo.StaffSalesVo;
import org.easymis.easyicc.service.AllocationCardService;
import org.easymis.easyicc.service.BusinessGroupService;
import org.easymis.easyicc.service.CardConfigService;
import org.easymis.easyicc.service.SchoolService;
import org.easymis.easyicc.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;

import cn.eutils.web.platform.permission.user.OnLine;
import cn.eutils.web.platform.ui.PageConfig;
import cn.jesong.webcall.cuour.cache.entity.CompanyTotal;
import cn.jesong.webcall.cuour.cache.entity.SaleUser;
import cn.jesong.webcall.cuour.user.CuourUserDetail;
import io.swagger.annotations.Api;
@Api(value = "/report", description = "报表菜单")
@Controller
@RequestMapping("/report")
public class ReportController extends IdentityRepository{
	private final static String PREFIX = "/report";
	private final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private AllocationCardService allocationCardService;
	@Autowired
	private BusinessGroupService businessGroupService;
	@Autowired
	private CardConfigService cardConfigService;
	//名片分配监控
	@RequestMapping("/monitor/allocation/index")
	public String monitorIndex(ModelMap model){
		String orgId = getOrgId();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgId", orgId);
		model.put("subjects", subjectService.findByOrgId(orgId));
		model.put("schools", this.schoolService.findByOrgId(orgId));
		model.put("groups", this.businessGroupService.findByOrgId(orgId));
		return PREFIX + "/monitor/allocation";
	}
	//名片分配监控
	@RequestMapping("/monitor/allocation/query")
	@ResponseBody
	public List<StaffSalesVo> getAllSaleUser(@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime, 
			@RequestParam(value = "school", required = false) Integer school, 
			@RequestParam(value = "subject", required = false) Integer subject,
			@RequestParam(value = "group", required = false) Integer group) throws ParseException{
		List<StaffSalesVo> list = this.allocationCardService.getSaleUser(getOrgId(), 
				formatter.parse(startTime), formatter.parse(endTime));
		//+" 00:00:00"  +" 23:59:59"
		List<StaffSalesVo> tempList = new ArrayList<StaffSalesVo>();
		CompanyTotal total = new CompanyTotal();
		for(SaleUser user : list){
			total.addWeight(user.getBusinessGroupId(), user.getAllocationWeight());
			total.addAllocation(user.getBusinessGroupId(), user.getAllocationCount());
			if(school != null && user.getSchoolId().intValue() != school.intValue()){
				continue;
			}
			if(subject != null && user.getSubjectId().intValue() != subject.intValue()){
				continue;
			}
			if(group != null && user.getBusinessGroupId().intValue() != group.intValue()){
				continue;
			}
			tempList.add(user);
		}
		list = tempList;
		for(StaffSalesVo u : list){
			if(total.getTotalWeight(u.getBusinessGroupId())>0){
				u.setFairRatio(this.getRatio(u.getAllocationWeight(), total.getTotalWeight(u.getBusinessGroupId())));
			}
			if(total.getAllocationTotal(u.getBusinessGroupId()) > 0){
				u.setActualRatio(this.getRatio(u.getAllocationCount(), total.getAllocationTotal(u.getBusinessGroupId())));
			}
		}
		return list;
	}
	//咨询师考核
	@RequestMapping("/customer/index")
	public String customerIndex(ModelMap model){
		String orgId = getOrgId();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyId", orgId);
		model.put("subjects", subjectService.findByOrgId(orgId));
		model.put("schools", this.schoolService.findByOrgId(orgId));
		return PREFIX + "/customer/index";
	}
	//咨询师考核
	@RequestMapping("/customer/query")
	@ResponseBody
	public List<CustomerReport> getCustomerReport(@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime, @RequestParam(value="subjectId", required = false) String subjectId, 
			@RequestParam(value="schoolId", required = false) String schoolId) throws ParseException{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyId", getOrgId());
		params.put("startTime", formatter.parse(startTime));//+" 00:00:00"
		params.put("endTime", formatter.parse(endTime));//+" 23:59:59"
		if(subjectId != null && !"".equals(subjectId)){
			params.put("subjectId", subjectId);
		}
		if(schoolId != null && !"".equals(schoolId)){
			params.put("schoolId", schoolId);
		}
		List<CustomerReport> list = this.reportService.getCustomerReport(params,startTime,endTime);
		int effectTotal = 0; int chatTotal = 0; int cardTotal = 0; int validTotal = 0; int backTotal = 0;
		for(CustomerReport cr : list){
			effectTotal += cr.getEffectTotal();
			chatTotal += cr.getChatTotal();
			cardTotal += cr.getCardTotal();
			validTotal += cr.getValidTotal();
			backTotal += cr.getBackTotal();
		}
		CustomerReport total = new CustomerReport("", "合计", effectTotal, chatTotal, cardTotal, validTotal, backTotal);
		list.add(total);
		return list;
	}
	//我录入的名片
	@RequestMapping("/mycard/index")
	public String mycardIndex(ModelMap model) throws Exception{
		String orgId = getOrgId();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyId", orgId);
		model.put("cols", cardConfigService.getShowVisitorCols(orgId));
		model.put("subjects", subjectService.findByOrgId(orgId));
		model.put("schools", this.schoolService.findByOrgId(orgId));
		return PREFIX + "/mycard/index";
	}
	//我录入的名片
	@RequestMapping("/mycard/query")
	@ResponseBody
	public Page<Card> query(HttpServletRequest request) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyId", getOrgId());
		String startTime = request.getParameter("startTime");
		if(startTime != null && startTime != ""){
			params.put("startTime", formatter.parse(startTime+" 00:00:00"));
		}else {
			params.put("startTime", new Date());
		}
		String endTime = request.getParameter("endTime");
		if(endTime != null && endTime != ""){
			params.put("endTime", formatter.parse(endTime+" 23:59:59"));
		}else{
			params.put("endTime", new Date());
		}
		params.put("extColumn8", request.getParameter("subjectId"));
		params.put("extColumn9", request.getParameter("schoolId"));
		Page<Card> page = this.cardConfigService.pageCardByCreateId(getStaffId(), PageConfig.createPageConfig(request), params);
		CuourUserDetail ud = (CuourUserDetail)OnLine.getCurrentUserDetails();
		if(ud.hasDataPermission("3d5c4d88-032f-409f-bf74-1b2f429d1216", "hideTelephone", "1")){
			for(Card card : page.getRows()){
					String mobile = card.getMobile();
					if(mobile != null && !mobile.equals("")){
						if(mobile.length() == 11){
							mobile = mobile.substring(0, 3) + "****" + mobile.substring(7, mobile.length());
						}else if(mobile.length() > 4){
							mobile = mobile.substring(0, mobile.length() - 4) + "****";
						}else{
							mobile = "****";
						}
						card.setMobile(mobile);
					}
			}
		}
		return page;
	}
	//校区分配监控
	@RequestMapping("/school/allocation/index")
	public String schoolIndex(ModelMap model){
		String orgId = getOrgId();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyId", orgId);
		model.put("subjects", subjectService.findByOrgId(orgId));
		model.put("schools", this.schoolService.findByOrgId(orgId));
		model.put("groups", this.businessGroupService.findByOrgId(orgId));
		return PREFIX + "/school/allocation";
	}
	//校区分配监控
	@RequestMapping("/school/allocation/query")
	@ResponseBody
	public List<StaffSalesVo> getSchoolSaleUser(@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime, 
			@RequestParam(value = "school", required = false) Integer school, 
			@RequestParam(value = "subject", required = false) Integer subject,
			@RequestParam(value = "group", required = false) Integer group) throws ParseException{
		List<StaffSalesVo> list = this.allocationCardService.getSaleUser(getOrgId(), 
				formatter.parse(startTime), formatter.parse(endTime));
		List<StaffSalesVo> tempList = new ArrayList<StaffSalesVo>();
		CompanyTotal total = new CompanyTotal();
		for(StaffSalesVo user : list){
			total.addWeight(user.getBusinessGroupId(), user.getAllocationWeight());
			total.addAllocation(user.getBusinessGroupId(), user.getAllocationCount());
			if(school != null && user.getSchoolId().intValue() != school.intValue()){
				continue;
			}
			if(subject != null && user.getSubjectId().intValue() != subject.intValue()){
				continue;
			}
			if(group != null && user.getBusinessGroupId().intValue() != group.intValue()){
				continue;
			}
			tempList.add(user);
		}
		list = tempList;
		for(int i = 0; i < list.size(); i++) {
			StaffSalesVo ui = list.get(i);
			double fairRatio = 0;
			double actualRatio = 0;
			int allocationCount = ui.getAllocationCount();
			int validCount = ui.getValidCount();
			int backCount = ui.getBackCount();
			double backRatio = ui.getBackRatio();
			int finishedCount = ui.getFinishedCount();
			int expiredCount = ui.getExpiredCount();
			int saleFinishedCount = ui.getSaleFinishedCount();
			int saleAllocationCount = ui.getSaleAllocationCount();
			
			if(total.getTotalWeight(ui.getBusinessGroupId()) > 0) {
				fairRatio = this.getRatio(ui.getAllocationWeight(), total.getTotalWeight(ui.getBusinessGroupId()));
			}
			if(total.getAllocationTotal(ui.getBusinessGroupId()) > 0){
				actualRatio = this.getRatio(ui.getAllocationCount(), total.getAllocationTotal(ui.getBusinessGroupId()));
			}
			for(int j = i + 1; j < list.size(); j++) {
				StaffSalesVo uj = list.get(j);
				if(ui.getBusinessGroupId().intValue() == uj.getBusinessGroupId().intValue()) {
					if(total.getTotalWeight(uj.getBusinessGroupId()) > 0) {
						fairRatio += this.getRatio(uj.getAllocationWeight(), total.getTotalWeight(uj.getBusinessGroupId()));
						fairRatio = fairRatio / 2;
					}
					
					if(total.getAllocationTotal(uj.getBusinessGroupId()) > 0) {
						actualRatio += this.getRatio(uj.getAllocationCount(), total.getAllocationTotal(uj.getBusinessGroupId()));
						actualRatio = actualRatio / 2;
					}
					
					allocationCount += uj.getAllocationCount();
					validCount += uj.getValidCount();
					backCount += uj.getBackCount();
					backRatio += uj.getBackRatio();
					backRatio = backRatio / 2;
					finishedCount += uj.getFinishedCount();
					expiredCount += uj.getExpiredCount();
					saleFinishedCount += uj.getSaleFinishedCount();
					saleAllocationCount += uj.getSaleAllocationCount();
					
					list.remove(uj);
				}
			}
			ui.setAllocationCount(allocationCount);
			ui.setValidCount(validCount);
			ui.setBackCount(backCount);
			ui.setBackRatio(backRatio);
			ui.setFinishedCount(finishedCount);
			ui.setExpiredCount(expiredCount);
			ui.setSaleFinishedCount(saleFinishedCount);
			ui.setSaleAllocationCount(saleAllocationCount);
			
			ui.setFairRatio(m1(fairRatio));
			ui.setActualRatio(m1(actualRatio));
		}
		return list;
	}
	private static double m1(double f) {
        BigDecimal bg = new BigDecimal(f);
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }
}
