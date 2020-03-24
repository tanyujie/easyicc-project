package org.easymis.easyicc.domain.vo;

import java.util.ArrayList;
import java.util.List;

public class SchedulingImportData {
	private String companyId;

	private String[] dates;
	
	private List<SchedulingWeek> schedulings = new ArrayList<SchedulingWeek>();
	
	public SchedulingImportData(String companyId,  String[] dates){
		if(dates == null || dates.length != 7){
			throw new RuntimeException("排班数据长度不对");
		}
		this.companyId = companyId;
		this.dates = dates;
	}
	
	public void addSchedulingWeek(SchedulingWeek week){
		this.schedulings.add(week);
	}


	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String[] getDates() {
		return dates;
	}

	public List<SchedulingWeek> getSchedulings() {
		return schedulings;
	}
}
