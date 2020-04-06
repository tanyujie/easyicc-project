package org.easymis.easyicc.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.ChatRecord;
import org.easymis.easyicc.domain.entity.ChatRecordDetail;
import org.easymis.easyicc.domain.vo.ChatRecordDetailVo;
import org.easymis.easyicc.mybatis.mapper.ChatRecordDetailMapper;
import org.easymis.easyicc.service.ChatRecordDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ChatRecordDetailServiceImpl implements ChatRecordDetailService{
	@Autowired
	private ChatRecordDetailMapper mapper;

	@Override
	public boolean save(ChatRecordDetail bean) {
		bean.setCreateTime(new Date());
		return mapper.insertByBean(bean);
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
	public List<ChatRecordDetail> findList(ChatRecordDetailVo vo) {

		return mapper.findList(vo);
	}
	@Override
	public PageInfo<?> find(ChatRecordDetail bean, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<ChatRecordDetail> dataList = mapper.getList(bean);
		PageInfo<ChatRecordDetail> p = new PageInfo<ChatRecordDetail>(dataList);
		return p;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChatRecordDetail> findByChatId(String chatId) {
		// TODO Auto-generated method stub
		return null;
	}


}
