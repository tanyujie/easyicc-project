package org.easymis.easyicc.service;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.IccRole;

import com.github.pagehelper.PageInfo;

public interface IccRoleService {
	public boolean save(IccRole bean);

	public boolean update(IccRole bean);

	public IccRole findById(String id);
	public List findByOrgId(String orgId);

	public PageInfo<?> find(IccRole bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);
}
