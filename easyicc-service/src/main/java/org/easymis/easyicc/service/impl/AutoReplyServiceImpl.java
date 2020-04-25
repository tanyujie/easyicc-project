package org.easymis.easyicc.service.impl;

import org.easymis.easyicc.domain.entity.AutoReply;
import org.easymis.easyicc.mybatis.mapper.AutoReplyMapper;
import org.easymis.easyicc.service.AutoReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

@Service
public class AutoReplyServiceImpl implements AutoReplyService {
	@Autowired
	private AutoReplyMapper mapper;

	@Override
	public boolean save(AutoReply bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(AutoReply bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AutoReply findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<?> find(AutoReply bean, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
}
