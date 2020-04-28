package org.easymis.easyicc.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.Robot;
import org.easymis.easyicc.mybatis.mapper.RobotMapper;
import org.easymis.easyicc.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class RobotServiceImpl implements RobotService {
	@Autowired
	private RobotMapper mapper;

	@Override
	public boolean save(Robot bean) {
		// TODO Auto-generated method stub
		bean.setRobotId(UUID.randomUUID().toString().replaceAll("-", ""));
		return mapper.save(bean);
	}

	@Override
	public boolean update(Robot bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Robot findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<?> find(Robot bean, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Robot> dataList = mapper.getList(bean);
		PageInfo<Robot> p = new PageInfo<Robot>(dataList);
		return p;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		List<String> idsList = Arrays.asList(ids.split(","));
		mapper.deleteBatch(idsList);
		return RestResult.buildSuccess();
	}

	@Override
	public List<Robot> findByOrgId(String orgId) {
		// TODO Auto-generated method stub
		return mapper.findByOrgId(orgId);
	}

}
