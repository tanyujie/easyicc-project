package org.easymis.easyicc.service;

import java.util.List;
import java.util.Map;

import org.easymis.easyicc.domain.entity.CardLog;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

public interface CardLogService {
	void save(CardLog log);
	List<CardLog> find(String cardId,Integer allocationType);
	public PageInfo<Map<String, Object>> pageQuery2(String companyId, Page page, Map<String, Object> params);
}
