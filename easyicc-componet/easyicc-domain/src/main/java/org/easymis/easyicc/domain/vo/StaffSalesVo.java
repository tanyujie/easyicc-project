package org.easymis.easyicc.domain.vo;

import java.util.ArrayList;
import java.util.List;

import org.easymis.easyicc.domain.entity.Card;
import org.easymis.easyicc.domain.entity.HrmStaffInfo;

import lombok.Data;
@Data
public class StaffSalesVo extends HrmStaffInfo{
	private String schoolName;
	private String subjectName;
	
	private String businessGroupName;
	
	private String salesTypeName;
	
	private List<Card> cards = new ArrayList<Card>();
	
	private boolean weixinPushStatus = false;
	private String weiXinOpenId = null;

	/**
	 * 有效量
	 */
	private int validCount;
	
	/**
	 * 退回量
	 */
	private int backCount;
	
	/**
	 * 已分配量
	 */
	private int allocationCount;
	
	/**
	 * 当天已分配量
	 */
	private int actualAllocationCount;
	
	/**
	 * 当天有效量
	 */
	private int actualValidCount;
	
	/**
	 * 已处理量
	 */
	private int finishedCount;
	
	/**
	 * 超期未处理量
	 */
	private int expiredCount;
	
	/**
	 * 分配权重
	 */
	private int allocationWeight;
	
	/**
	 * 退回率
	 */
	private double backRatio;
	
	/**
	 * 合理比率
	 */
	private double fairRatio;
	
	/**
	 * 实际比率
	 */
	private double actualRatio;
	
	/**
	 * 最大分配量
	 */
	private int maxCardSize;
	
	/**
	 * 高意向名片分配量
	 */
	private int saleAllocationCount;
	
	/**
	 * 高意向名片处理量
	 */
	private int saleFinishedCount;
	
	/**
	 * 业务分组ID
	 */
	//@Column(name = "business_group_id", nullable = false)
	private String businessGroupId;
	/**
	 * 校区ID
	 */
	//@Column(name = "school_id", nullable = false)
	private String schoolId;
	/**
	 * 项目ID
	 */
	//@Column(name = "subject_id", nullable = false)
	private String subjectId;
	
	
}
