package org.easymis.easyicc.service.impl;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.ChatRecordDetail;
import org.easymis.easyicc.mybatis.mapper.ChatRecordDetailMapper;
import org.easymis.easyicc.service.ChatRecordDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

@Service
public class ChatRecordDetailServiceImpl implements ChatRecordDetailService{
	@Autowired
	private ChatRecordDetailMapper mapper;

	@Override
	public boolean save(ChatRecordDetail bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ChatRecordDetail bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ChatRecordDetail findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<?> find(ChatRecordDetail bean, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}
}
