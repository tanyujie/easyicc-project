package org.easymis.easyicc.service.impl;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.PromotionChannel;
import org.easymis.easyicc.mybatis.mapper.PromotionChannelMapper;
import org.easymis.easyicc.service.PromotionChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

@Service
public class PromotionChannelServiceImpl implements PromotionChannelService{
	@Autowired
	private PromotionChannelMapper mapper;
	@Override
	public boolean save(PromotionChannel bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(PromotionChannel bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PromotionChannel findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<?> find(PromotionChannel bean, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
