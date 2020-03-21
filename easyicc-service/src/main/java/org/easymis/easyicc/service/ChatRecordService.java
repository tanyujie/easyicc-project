package org.easymis.easyicc.service;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.ChatRecord;

import com.github.pagehelper.PageInfo;

public interface ChatRecordService {
	public boolean save(ChatRecord bean);

	public boolean update(ChatRecord bean);

	public ChatRecord findById(String id);


	public PageInfo<?> find(ChatRecord bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);
}
