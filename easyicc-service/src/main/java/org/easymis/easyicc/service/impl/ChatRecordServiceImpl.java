package org.easymis.easyicc.service.impl;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.ChatRecord;
import org.easymis.easyicc.domain.entity.ChatRecordDetail;
import org.easymis.easyicc.mybatis.mapper.ChatRecordMapper;
import org.easymis.easyicc.service.ChatRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

@Service
public class ChatRecordServiceImpl implements ChatRecordService{
	@Autowired
	private ChatRecordMapper mapper;
	@Override
	public boolean save(ChatRecord bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ChatRecord bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ChatRecord findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<?> find(ChatRecord bean, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;// mapper.getList(bean);
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChatRecordDetail> getChatRecordDetail(String companyId, String visitorStaticId, String createTime)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
