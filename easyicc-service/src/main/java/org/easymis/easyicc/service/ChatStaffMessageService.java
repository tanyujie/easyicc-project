package org.easymis.easyicc.service;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.ChatStaffMessage;

import com.github.pagehelper.PageInfo;

public interface ChatStaffMessageService {
	public boolean save(ChatStaffMessage bean);

	public boolean update(ChatStaffMessage bean);

	public ChatStaffMessage findById(String id);

	public List findByOrgId(String orgId);

	public PageInfo<?> find(ChatStaffMessage bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);
}
