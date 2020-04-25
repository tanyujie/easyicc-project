package org.easymis.easyicc.service.impl;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.Organize;
import org.easymis.easyicc.mybatis.mapper.OrganizeMapper;
import org.easymis.easyicc.service.OrganizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
@Service
public class OrganizeServiceImpl implements OrganizeService {
	@Autowired
	private OrganizeMapper mapper;
	@Override
	public List<Organize> findList() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean save(Organize bean) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean update(Organize bean) {
		// TODO Auto-generated method stub
		return mapper.update(bean);
	}
	@Override
	public Organize findById(String id) {
		// TODO Auto-generated method stub
		return mapper.findById(id);
	}
	@Override
	public PageInfo<?> find(Organize bean, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
