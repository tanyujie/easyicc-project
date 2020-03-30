package org.easymis.easyicc.service;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.HrmStaffInfo;

import com.github.pagehelper.PageInfo;

public interface HrmStaffInfoService {
	public boolean save(HrmStaffInfo bean);

	public boolean update(HrmStaffInfo bean);

	public HrmStaffInfo findById(String id);


	public PageInfo<?> find(HrmStaffInfo bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);
}
