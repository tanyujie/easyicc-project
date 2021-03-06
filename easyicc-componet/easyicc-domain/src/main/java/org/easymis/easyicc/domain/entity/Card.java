package org.easymis.easyicc.domain.entity;

import lombok.Data;

@Data
public class Card extends VisitorInfo{


	/**
	 * 默认， 等待分配
	 */
	public final static int STATUS_DEFAULT = 0;
	
	/**
	 * 系统分配
	 */
	public final static int STATUS_SYSTEM_ALLOCATIONED = 1;
	
	/**
	 * 等待人工分配
	 */
	public final static int STATUS_WAIT_USE_ALLOCATION = 2;
	
	/**
	 * 人工分配
	 */
	public final static int STATUS_USE_ALLOCATIONED = 3;
	
	/**
	 * 高意向名片分配
	 */
	public final static int STATUS_SALE_ALLOCATIONED = 6;
	
	/**
	 * 已处理
	 */
	public final static int STATUS_FINISHED = 4;
	/**
	 * 重复线索
	 */
	public final static int STATUS_REPEAT = 5;

	//
	private String backDesp;
    
    //回电说明
    private String finishDesp;
/*   
    private int Valid = 1;
    
    private int isBack = 0;
    
    private int isExpired = 0;
       
    //退回类型
    private int backType = 0;
    
    private int allocationStatus = 0;
    
    private String modifyIdentity = "0";
    
    private Date allocationTime;
    
    private String backUserId;
    
    private Date backTime;
	/**
	 * 销售ID
	 */
	private String saleId;
	/**
	 * 分处销售ID
	 */
	private String userId;
    /**
     * 新增字段，这个字段来判断是否分配，mq after方法里会用到
     */
/*    private boolean allocation;
	public boolean isSaleCard(){
		return this.saleId != null && this.saleId.equals(this.userId);
	}*/


}
