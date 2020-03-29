package org.easymis.easyicc.service;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.RobotChatDetail;

import com.github.pagehelper.PageInfo;

public interface RobotChatDetailService {
	public boolean save(RobotChatDetail bean);

	public boolean update(RobotChatDetail bean);

	public RobotChatDetail findById(String id);
	
	public List<RobotChatDetail> findByQuestion(RobotChatDetail bean);

	public PageInfo<?> find(RobotChatDetail bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);
}
