package org.easymis.easyicc.service;

import java.util.List;

public interface CardDockingService {
	/**
	 * 设置无效
	 * 
	 * @param companyId
	 * @param staticId
	 * @return
	 */
	public int setNotValidate(String companyId, String staticId);
	/**
	 * 重新分配
	 * 
	 * @param companyId
	 * @param staticId
	 * @return
	 */
	public int resetAllocation(String companyId, String staticId);
	/**
	 * 退回
	 * 
	 * @param companyId
	 * @param staticId
	 * @return
	 */
	public int returnAllocation(String companyId, String staticId);
	/**
	 * 超期回收
	 * 
	 * @param companyId
	 * @param staticId
	 * @return
	 */
	public int expiredAllocation(String companyId, String staticId);
	/**
	 * 等待分配
	 * 
	 * @param companyId
	 * @param staticId
	 * @return
	 */
	public int waitAllocation(String companyId, String staticId);
	/**
	 * 已处理
	 * 
	 * @param companyId
	 * @param staticId
	 * @return
	 */
	public int finishedAllocation(String companyId, String staticId);
	/**
	 * 重复线索
	 * 
	 * @param companyId
	 * @param staticId
	 * @return
	 */
	public int repeatAllocation(String companyId, String staticId);
	/**
	 * 设置无效-批量
	 * 
	 * @param companyId
	 * @param staticId
	 * @return
	 */
	public int setNotValidateBatch(String companyId, List<String> staticIds);
	/**
	 * 重新分配-批量
	 * 
	 * @param companyId
	 * @param staticId
	 * @return
	 */
	public int resetAllocationBatch(String companyId, List<String> staticIds);
	/**
	 * 退回-批量
	 * 
	 * @param companyId
	 * @param staticId
	 * @return
	 */
	public int returnAllocationBatch(String companyId, List<String> staticIds);
	/**
	 * 超期回收-批量
	 * 
	 * @param companyId
	 * @param staticId
	 * @return
	 */
	public int expiredAllocationBatch(String companyId, List<String> staticIds);
	/**
	 * 等待分配-批量
	 * 
	 * @param companyId
	 * @param staticId
	 * @return
	 */
	public int waitAllocationBatch(String companyId, List<String> staticIds);
	/**
	 * 已处理-批量
	 * 
	 * @param companyId
	 * @param staticId
	 * @return
	 */
	public int finishedAllocationBatch(String companyId, List<String> staticIds);
	/**
	 * 重复线索-批量
	 * 
	 * @param companyId
	 * @param staticId
	 * @return
	 */
	public int repeatAllocationBatch(String companyId, List<String> staticIds) ;
}
