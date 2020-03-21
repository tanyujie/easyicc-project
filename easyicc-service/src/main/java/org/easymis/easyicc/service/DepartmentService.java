package org.easymis.easyicc.service;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.Department;

import com.github.pagehelper.PageInfo;

public interface DepartmentService {
	public boolean save(Department bean);

	public boolean update(Department bean);

	public Department findById(String id);


	public PageInfo<?> find(Department bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);
}
