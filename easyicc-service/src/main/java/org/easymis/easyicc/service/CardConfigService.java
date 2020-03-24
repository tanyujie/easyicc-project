package org.easymis.easyicc.service;

import java.util.List;
import java.util.Map;

import org.easymis.easyicc.domain.entity.Card;
import org.easymis.easyicc.domain.entity.VisitorColSelf;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

public interface CardConfigService {
	public List<VisitorColSelf> getShowVisitorCols(String orgId) throws Exception;

	public PageInfo<Card> pageVisitorCard(Page pageConfig, Map<String, Object> map, int status) throws Exception;

	public PageInfo<Card> pageCardByCreateId(String createUserId, Page pageConfig, Map<String, Object> map)
			throws Exception;

	public PageInfo<Card> pageCanDisposeVisitorCard(Page pageConfig, Map<String, Object> map) throws Exception;
}
