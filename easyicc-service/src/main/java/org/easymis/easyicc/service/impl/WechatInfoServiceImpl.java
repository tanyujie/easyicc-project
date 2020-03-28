package org.easymis.easyicc.service.impl;

import java.util.List;

import org.easymis.easyicc.domain.entity.WechatInfo;
import org.easymis.easyicc.mybatis.mapper.WechatInfoMapper;
import org.easymis.easyicc.service.WechatInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
@Service
public class WechatInfoServiceImpl implements WechatInfoService {
	@Autowired
	private WechatInfoMapper mapper;
	@Override
	public List<WechatInfo> findByOrgId(String orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WechatInfo findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(WechatInfo bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PageInfo findPage(WechatInfo bean, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(WechatInfo bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

}
