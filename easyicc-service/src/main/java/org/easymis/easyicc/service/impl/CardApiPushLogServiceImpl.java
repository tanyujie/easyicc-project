package org.easymis.easyicc.service.impl;

import java.util.List;
import java.util.Map;

import org.easymis.easyicc.domain.entity.ApiPushLog;
import org.easymis.easyicc.domain.entity.Card;
import org.easymis.easyicc.mybatis.mapper.CardPushLogMapper;
import org.easymis.easyicc.service.CardApiPushLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
@Service
public class CardApiPushLogServiceImpl implements CardApiPushLogService {
	@Autowired
	private CardPushLogMapper mapper;
	@Override
	public Card getCard(String cardId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<ApiPushLog> pageApiPushVisitorInfo(Page page, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ApiPushLog> downApiPushVisitorInfo(Page page, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

}
