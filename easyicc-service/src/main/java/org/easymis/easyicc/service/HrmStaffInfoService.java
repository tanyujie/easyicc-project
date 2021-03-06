package org.easymis.easyicc.service;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.HrmStaffInfo;
import org.easymis.easyicc.domain.vo.StaffOnlineTreeVo;

import com.github.pagehelper.PageInfo;

public interface HrmStaffInfoService {
	public boolean save(HrmStaffInfo bean);

	public boolean update(HrmStaffInfo bean);

	public HrmStaffInfo findById(String id);

	public PageInfo<?> find(HrmStaffInfo bean, Integer pageNum, Integer pageSize);

	public List<StaffOnlineTreeVo> findByOnlineTree(String orgId);

	public RestResult deleteByIds(String ids);

	/**
	 * 
	 * <p>
	 * Title: 按部门获取所用员工信息
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param orgId
	 * 			@return
	 */
	public RestResult getListByDepartment(String orgId);
}
