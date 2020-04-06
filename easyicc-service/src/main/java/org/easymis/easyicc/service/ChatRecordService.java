package org.easymis.easyicc.service;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.ChatRecord;
import org.easymis.easyicc.domain.entity.ChatRecordDetail;
import org.easymis.easyicc.domain.vo.StaffOnlineTreeVo;

import com.github.pagehelper.PageInfo;

public interface ChatRecordService {
	public boolean save(ChatRecord bean);

	public boolean update(ChatRecord bean);
	public boolean saveOrUpdate(ChatRecord bean);
	public List<StaffOnlineTreeVo> findOnline(String orgId);	
	public ChatRecord findById(String id);
	public ChatRecord findByVisitorId(String visitorId);
	
	public PageInfo<?> find(ChatRecord bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);

	public List<ChatRecordDetail> getChatRecordDetail(String companyId, String visitorStaticId, String createTime)
			throws Exception;
}
