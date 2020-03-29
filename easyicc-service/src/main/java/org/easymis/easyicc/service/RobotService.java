package org.easymis.easyicc.service;


import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.Robot;

import com.github.pagehelper.PageInfo;

public interface RobotService {

	public boolean save(Robot bean);

	public boolean update(Robot bean);

	public Robot findById(String id);

	public PageInfo<?> find(Robot bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);

}
