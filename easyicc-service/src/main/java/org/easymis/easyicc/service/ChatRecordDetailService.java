package org.easymis.easyicc.service;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.ChatRecordDetail;
import org.easymis.easyicc.domain.vo.ChatRecordDetailVo;
import org.easymis.easyicc.domain.vo.VisitorChatMsg;

import com.github.pagehelper.PageInfo;

public interface ChatRecordDetailService {
	public boolean save(ChatRecordDetail bean);

	public boolean save(VisitorChatMsg chatMsg);

	public boolean update(ChatRecordDetail bean);

	public ChatRecordDetail findById(String id);

	public List<ChatRecordDetail> findByChatId(String chatId);

	public List<ChatRecordDetail> findList(ChatRecordDetailVo vo);

	public PageInfo<?> find(ChatRecordDetail bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);
}
