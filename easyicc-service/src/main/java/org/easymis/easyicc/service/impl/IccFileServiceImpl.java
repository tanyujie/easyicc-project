package org.easymis.easyicc.service.impl;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.IccFile;
import org.easymis.easyicc.mybatis.mapper.IccFileMapper;
import org.easymis.easyicc.service.IccFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class IccFileServiceImpl implements IccFileService {
	@Autowired
	private IccFileMapper mapper;
	@Override
	public boolean save(IccFile bean) {
		// TODO Auto-generated method stub
		return mapper.save(bean);
	}

	@Override
	public boolean update(IccFile bean) {
		// TODO Auto-generated method stub
		return mapper.update(bean);
	}

	@Override
	public IccFile findById(String id) {
		// TODO Auto-generated method stub
		return mapper.findById(id);
	}

	@Override
	public PageInfo<?> find(IccFile bean, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<IccFile> dataList = mapper.getList(bean);
		PageInfo<IccFile> p = new PageInfo<IccFile>(dataList);
		return p;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
