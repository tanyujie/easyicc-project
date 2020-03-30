package org.easymis.easyicc.service;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.ChatRecordDetail;

import com.github.pagehelper.PageInfo;

public interface ChatRecordDetailService {
	public boolean save(ChatRecordDetail bean);

	public boolean update(ChatRecordDetail bean);

	public ChatRecordDetail findById(String id);

	public PageInfo<?> find(ChatRecordDetail bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);
}
