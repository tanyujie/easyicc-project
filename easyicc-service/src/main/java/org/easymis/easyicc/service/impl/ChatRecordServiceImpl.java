package org.easymis.easyicc.service.impl;

import java.util.Date;
import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.ChatRecord;
import org.easymis.easyicc.domain.entity.ChatRecordDetail;
import org.easymis.easyicc.mybatis.mapper.ChatRecordMapper;
import org.easymis.easyicc.service.ChatRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ChatRecordServiceImpl implements ChatRecordService{
	@Autowired
	private ChatRecordMapper mapper;
	@Override
	public boolean save(ChatRecord bean) {
		// TODO Auto-generated method stub
		return mapper.save(bean);
	}

	@Override
	public boolean update(ChatRecord bean) {
		return mapper.update(bean);
	}

	@Override
	public boolean saveOrUpdate(ChatRecord bean) {
		if (null != findById(bean.getChatId())) {
			bean.setEndTime(new Date());
			return update(bean);
		}

		else {
			bean.setCreateTime(new Date());
			return save(bean);
		}

	}
	@Override
	public ChatRecord findById(String chatId) {
		// TODO Auto-generated method stub
		return mapper.findById(chatId);
	}

	@Override
	public PageInfo<?> find(ChatRecord bean, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<ChatRecord> dataList = mapper.getList(bean);
		PageInfo<ChatRecord> p = new PageInfo<ChatRecord>(dataList);
		return p;
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
