package org.easymis.easyicc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.ChatRecord;
import org.easymis.easyicc.domain.entity.ChatRecordDetail;
import org.easymis.easyicc.domain.vo.ChatOnlineVo;
import org.easymis.easyicc.domain.vo.StaffOnlineTreeVo;
import org.easymis.easyicc.mybatis.mapper.ChatRecordMapper;
import org.easymis.easyicc.service.ChatRecordService;
import org.easymis.easyicc.service.HrmStaffInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("chatRecordService") 
public class ChatRecordServiceImpl implements ChatRecordService{
	@Autowired
	private ChatRecordMapper mapper;
	@Autowired
	private HrmStaffInfoService hrmStaffInfoService;
	@Override
	public boolean save(ChatRecord bean) {
		bean.setChatId(UUID.randomUUID().toString().replace("-", ""));
		return mapper.save(bean);
	}

	@Override
	public boolean update(ChatRecord bean) {
		return mapper.update(bean);
	}

	@Override
	public boolean saveOrUpdate(ChatRecord bean) {
		if (null != findById(bean.getChatId())) {
			bean.setEndTime(new Date());
			return update(bean);
		}

		else {
			bean.setCreateTime(new Date());
			return save(bean);
		}

	}
	@Override
	public ChatRecord findById(String chatId) {
		// TODO Auto-generated method stub
		return mapper.findById(chatId);
	}

	@Override
	public PageInfo<?> find(ChatRecord bean, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<ChatRecord> dataList = mapper.getList(bean);
		PageInfo<ChatRecord> p = new PageInfo<ChatRecord>(dataList);
		return p;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChatRecordDetail> getChatRecordDetail(String companyId, String visitorStaticId, String createTime)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StaffOnlineTreeVo> findOnline(String orgId) {
		List<StaffOnlineTreeVo> voList =hrmStaffInfoService.findByOnlineTree(orgId);
		List<StaffOnlineTreeVo> treeList= new ArrayList();
		List<ChatRecord> chatRecordList = mapper.findOnline(orgId);
		for(int i=0;i<voList.size();i++) {
			StaffOnlineTreeVo vo=voList.get(i);
			vo.setChatOnlineList(makeChatOnlineList(vo.getStaffId(),chatRecordList));
			treeList.add(vo);
		}
		return treeList;
	}
	private List<ChatOnlineVo> makeChatOnlineList(String staffId, List<ChatRecord> chatRecordList) {
		List<ChatOnlineVo> list = new ArrayList<ChatOnlineVo>();
		for (int i = 0; i < chatRecordList.size(); i++) {
			if (staffId.equals(chatRecordList.get(i).getStaffId())) {
				ChatOnlineVo chatOnlineVo = new ChatOnlineVo();
				BeanUtils.copyProperties(chatRecordList.get(i), chatOnlineVo);
				list.add(chatOnlineVo);
			}

		}
		return list;

	}

	@Override
	public ChatRecord findByVisitorId(String visitorId) {
		return mapper.findByVisitorId(visitorId).get(0);
	}

}
