package org.easymis.easyicc.service;

import org.easymis.easyicc.domain.entity.AutoReply;

import com.github.pagehelper.PageInfo;

public interface AutoReplyService {
	public boolean save(AutoReply bean);

	public boolean update(AutoReply bean);

	public AutoReply findById(String id);

	public PageInfo<?> find(AutoReply bean, Integer pageNum, Integer pageSize);
}
