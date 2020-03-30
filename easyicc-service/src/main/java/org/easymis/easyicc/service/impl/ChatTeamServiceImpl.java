package org.easymis.easyicc.service.impl;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.ChatTeam;
import org.easymis.easyicc.service.ChatTeamService;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
@Service
public class ChatTeamServiceImpl implements ChatTeamService {

	@Override
	public boolean save(ChatTeam bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ChatTeam bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ChatTeam findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<?> find(ChatTeam bean, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
