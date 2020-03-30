package org.easymis.easyicc.service;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.Organize;

import com.github.pagehelper.PageInfo;

public interface OrganizeService {
	public List<Organize> findList();

	public boolean save(Organize bean);

	public boolean update(Organize bean);

	public Organize findById(String id);

	public PageInfo<?> find(Organize bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);
}
