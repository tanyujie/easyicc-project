package org.easymis.easyicc.domain.entity;

import lombok.Data;
@Data
public class SchedulingUser {
	private String id;
	
	/**
	 * 所属公司ID
	 */
	private String orgId;
	
	/**
	 * 排班时间
	 */
	//@Column(name = "scheduling_time", nullable = false)
	private String schedulingTime;
	
	/**
	 * 排班班次ID
	 */
	//@Column(name = "scheduling_id", nullable = false)
	private String schedulingId;
	
	/**
	 * 排班用户
	 */
	//@Column(name = "user_id", nullable = false)
	private String userId;
	
	/**
	 * 排班类型(1 销售排班 2客服排班)
	 */
	//@Column(name = "scheduling_type", nullable = false)
	private int schedulingType = 1;
}
