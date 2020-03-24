package org.easymis.easyicc.card.admin.controller.setting;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.easymis.easyicc.card.admin.controller.IdentityRepository;
import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.SchedulingUser;
import org.easymis.easyicc.domain.vo.SchedulingImportData;
import org.easymis.easyicc.domain.vo.SchedulingWeek;
import org.easymis.easyicc.service.BusinessGroupService;
import org.easymis.easyicc.service.SchedulingService;
import org.easymis.easyicc.service.SchedulingUserService;
import org.easymis.easyicc.service.SchoolService;
import org.easymis.easyicc.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;

@Api(value = "/scheduling/user", description = "排班设置")
@Controller
@RequestMapping("/scheduling/user")
public class ScheduingUserController extends IdentityRepository{
	
	private final static String PREFIX = "/setting/schedulingUser";
	
	private final static SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	
	private final static SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private SchedulingService schedulingService;
	
	@Autowired
	private SchedulingUserService schedulingUserService;
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private BusinessGroupService businessGroupService;

	@RequestMapping("/index")
	public String index(ModelMap model){
		String orgId = this.getOrgId();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyId", orgId);
		model.put("schools", this.schoolService.findByOrgId(orgId));
		model.put("subjects", this.subjectService.findByOrgId(orgId));
		model.put("businessGroups", this.businessGroupService.findByOrgId(orgId));
		return PREFIX + "/index";
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public List<Map<String, Object>> query(@RequestParam(value = "startTime", required = true) String startTime, 
			@RequestParam(value = "subjectId", required = false) String subjectId,
			@RequestParam(value = "schoolId", required = false) String schoolId,
			@RequestParam(value = "businessGroupId", required = false) String businessGroupId) throws ParseException{
		return this.schedulingUserService.getSchedulings(getOrgId(),
				formatter2.parse(startTime) , subjectId, schoolId, businessGroupId);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public RestResult delete(@RequestParam("id") String id){
		try{
			this.schedulingUserService.delete(id);
			return RestResult.buildSuccess();
		}catch(Exception e){
			return RestResult.buildError();
		}
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(@RequestParam("userId") String userId, 
			@RequestParam("day") int day, ModelMap model){
		SchedulingUser entity = new SchedulingUser();
		entity.setSchedulingTime(String.valueOf(day));
		entity.setUserId(userId);
		entity.setSchedulingId("-1");
		model.put("entity", entity);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyId", getOrgId());
		model.put("schedulings", this.schedulingService.findByOrgId(getOrgId()));
		return PREFIX + "/form";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public RestResult create(SchedulingUser su, HttpServletRequest request, HttpServletResponse response) throws IOException{
		try{
			if(su.getSchedulingId() != null && !su.getSchedulingId().equals("-1")){
				su.setOrgId(getOrgId());
				//this.schedulingUserService.saveOrUpdate(su);
			}
			return RestResult.buildSuccess();
		}catch(Exception e){
			return RestResult.buildFail();
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam("id") String id, ModelMap model){
		model.put("entity", this.schedulingUserService.findById(id));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyId", getOrgId());
		model.put("schedulings", this.schedulingService.findById(getOrgId()));
		return PREFIX + "/form";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public RestResult edit(SchedulingUser su, HttpServletRequest request, HttpServletResponse response) throws IOException{
		if(su.getSchedulingId() == null || !su.getSchedulingId().equals("-1")){
			this.schedulingUserService.delete(su.getId());
			return RestResult.buildSuccess();
		}else{
			try{
				su.setOrgId(getOrgId());
				this.schedulingUserService.update(su);
				return RestResult.buildSuccess();
			}catch(Exception e){
				return RestResult.buildFail();
			}
		}
	}
	
	@RequestMapping(value = "/import", method = RequestMethod.GET)
	public String importPage(){
		return PREFIX + "/import";
	}
	
	
	@RequestMapping(value = "/import", method = RequestMethod.POST)
	public RestResult upload(@RequestParam(value="file", required = false) MultipartFile file, 
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		try {
			SchedulingImportData data = parseExcel(getOrgId(), file.getInputStream());
			this.schedulingUserService.importData(data);
			return RestResult.buildSuccess();
		} catch (Exception e) {
			e.printStackTrace();
			return RestResult.buildFail();
		}
	}
	
	
	
	private static SchedulingImportData parseExcel(String companyId, InputStream is) throws IOException{
		Workbook wb = new XSSFWorkbook(is);
		Sheet sheet = wb.getSheetAt(0);
		Row dateRow = sheet.getRow(0);
		String[] dates = new String[7];
		for(int i=0; i<7; i++){
			Date date = dateRow.getCell(2+i).getDateCellValue();
			dates[i] = formatter.format(date);
		}
		SchedulingImportData importData = new SchedulingImportData(companyId, dates);
		for(int i=2; i<= sheet.getLastRowNum(); i++){
			Row row = sheet.getRow(i);
			String userId = row.getCell(0).getStringCellValue().trim();
			String[] schedulingNames = new String[7];
			for(int j=0; j<7; j++){
				String scheduling = "";
				Cell cell = row.getCell(2+j);
				if(cell != null){
					scheduling = row.getCell(2+j).getStringCellValue().trim();
				}
				schedulingNames[j] = scheduling;
			}
			SchedulingWeek week = new SchedulingWeek(userId, schedulingNames);
			importData.addSchedulingWeek(week);
		}
		return importData;
	}
	
	
	public static void main(String[] args) throws IOException{
		InputStream is = new FileInputStream("C:\\Users\\Administrator\\Desktop\\scheduling.xlsx");
		parseExcel("1", is);
		is.close();
	}

}
