package org.easymis.easyicc.service.impl;

import org.easymis.easyicc.mybatis.mapper.ChatRecordDetailMapper;
import org.easymis.easyicc.service.ChatRecordDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatRecordDetailServiceImpl implements ChatRecordDetailService{
	@Autowired
	private ChatRecordDetailMapper mapper;
}
