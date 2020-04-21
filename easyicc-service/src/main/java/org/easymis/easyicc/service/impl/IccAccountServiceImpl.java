package org.easymis.easyicc.service.impl;

import java.util.List;
import java.util.UUID;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.config.datasource.DataSourceType;
import org.easymis.easyicc.config.datasource.EasymisDataSource;
import org.easymis.easyicc.domain.entity.IccAccount;
import org.easymis.easyicc.mybatis.mapper.IccAccountMapper;
import org.easymis.easyicc.service.IccAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class IccAccountServiceImpl implements IccAccountService {
	@Autowired
	private IccAccountMapper mapper;

	@EasymisDataSource(DataSourceType.Master)
	public boolean save(IccAccount bean) {
		bean.setId(UUID.randomUUID().toString().replace("-", ""));
		return mapper.save(bean);
	}

	@Override
	public boolean update(IccAccount bean) {
		// TODO Auto-generated method stub
		return mapper.update(bean);
	}

	@Override
	public IccAccount findById(String id) {
		// TODO Auto-generated method stub
		return mapper.findById(id);
	}

	@Override
	public PageInfo<?> find(IccAccount bean, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<IccAccount> dataList = mapper.getList(bean);
		PageInfo<IccAccount> p = new PageInfo<IccAccount>(dataList);
		return p;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
