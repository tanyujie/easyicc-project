package org.easymis.easyicc.domain.vo;

public class SchedulingWeek {
	
	private String userId;
	
	private String[] schedulingNames;
	
	public SchedulingWeek(String userId, String[] schedulingNames){
		if(schedulingNames == null || schedulingNames.length != 7){
			throw new RuntimeException("排班数据长度不对");
		}
		this.userId = userId;
		this.schedulingNames = schedulingNames;
	}
	

	public String getUserId() {
		return userId;
	}

	public String[] getSchedulingNames() {
		return schedulingNames;
	}

	
	
}
