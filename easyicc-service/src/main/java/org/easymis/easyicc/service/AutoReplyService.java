package org.easymis.easyicc.service;

import org.easymis.easyicc.domain.entity.AutoReply;

public interface AutoReplyService {
	public boolean save(AutoReply bean);

	public boolean update(AutoReply bean);

	public AutoReply findById(String id);

}
