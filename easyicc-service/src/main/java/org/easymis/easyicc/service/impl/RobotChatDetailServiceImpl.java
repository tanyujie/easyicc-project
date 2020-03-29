package org.easymis.easyicc.service.impl;

import java.util.List;
import java.util.UUID;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.RobotChatDetail;
import org.easymis.easyicc.mybatis.mapper.RobotChatDetailMapper;
import org.easymis.easyicc.service.RobotChatDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
@Service
public class RobotChatDetailServiceImpl implements RobotChatDetailService {
	@Autowired
	private RobotChatDetailMapper mapper;
	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(RobotChatDetail bean) {
		bean.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		return mapper.save(bean);
	}

	@Override
	public boolean update(RobotChatDetail bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RobotChatDetail findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RobotChatDetail> findByQuestion(RobotChatDetail bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<?> find(RobotChatDetail bean, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
