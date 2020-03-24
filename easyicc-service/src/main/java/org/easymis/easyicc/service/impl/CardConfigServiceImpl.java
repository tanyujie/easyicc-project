package org.easymis.easyicc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.easymis.easyicc.domain.entity.Card;
import org.easymis.easyicc.domain.entity.CardLog;
import org.easymis.easyicc.domain.entity.VisitorColSelf;
import org.easymis.easyicc.mybatis.mapper.VisitorColSelfMapper;
import org.easymis.easyicc.mybatis.mapper.VisitorInfoMapper;
import org.easymis.easyicc.service.CardConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class CardConfigServiceImpl implements CardConfigService {
	@Autowired
	private VisitorColSelfMapper visitorColSelfMapper;
	
	@Autowired
	private VisitorInfoMapper visitorInfoMapper;
	public List<VisitorColSelf> getShowVisitorCols(String orgId) throws Exception {
		// TODO Auto-generated method stub
		List<VisitorColSelf> list= visitorColSelfMapper.findByOrgId(orgId);
		List<VisitorColSelf> rtnList = new ArrayList<VisitorColSelf>();
		for(VisitorColSelf col : list){
			if(col.getHidden() == 1){
				rtnList.add(col);
			}
		}
		return rtnList;
	}
	public PageInfo<Card> pageVisitorCard(Page bean, Map<String, Object> map, int status) throws Exception {
    	PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
    	map.put("status", status);
    	List<Card> cardList = visitorInfoMapper.getList(map);
		PageInfo<Card> p = new PageInfo<Card>(cardList);
        return p;
	}
	@Override
	public PageInfo<Card> pageCardByCreateId(String createUserId, Page pageConfig, Map<String, Object> map)
			throws Exception {
		// select a.* from js_visitor_info a where a.create_user_id = 
		//传入参数创建人，开始时间，结束时间，项目，校区
		return null;
	}
	@Override
	public PageInfo<Card> pageCanDisposeVisitorCard(Page pageConfig, Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
