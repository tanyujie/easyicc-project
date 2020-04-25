package org.easymis.easyicc.service.impl;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.ChatStaffMessage;
import org.easymis.easyicc.mybatis.mapper.ChatStaffMessageMapper;
import org.easymis.easyicc.service.ChatStaffMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service("chatStaffMessageService") 
public class ChatStaffMessageServiceImpl implements ChatStaffMessageService {
	@Autowired
	private ChatStaffMessageMapper mapper;
	@Override
	public boolean save(ChatStaffMessage bean) {
		// TODO Auto-generated method stub
		return mapper.save(bean);
	}

	@Override
	public boolean update(ChatStaffMessage bean) {
		// TODO Auto-generated method stub
		return mapper.update(bean);
	}

	@Override
	public ChatStaffMessage findById(String id) {
		// TODO Auto-generated method stub
		return mapper.findById(id);
	}

	@Override
	public List findByOrgId(String orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<?> find(ChatStaffMessage bean, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<ChatStaffMessage> dataList = mapper.getList(bean);
		PageInfo<ChatStaffMessage> p = new PageInfo<ChatStaffMessage>(dataList);
		return p;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
