package org.easymis.easyicc.service.impl;

import java.util.List;
import java.util.Map;

import org.easymis.easyicc.domain.entity.CardLog;
import org.easymis.easyicc.mybatis.mapper.CardLogMapper;
import org.easymis.easyicc.service.CardLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
@Service
public class CardLogServiceImpl implements CardLogService {
	@Autowired
	private CardLogMapper mapper;
	@Override
	public void save(CardLog log) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PageInfo<Map<String, Object>> pageQuery2(String companyId, Page page, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CardLog> find(String cardId, Integer allocationType) {
		// TODO Auto-generated method stub
		return null;
	}

}
