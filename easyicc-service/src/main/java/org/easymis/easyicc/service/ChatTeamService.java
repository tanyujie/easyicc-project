package org.easymis.easyicc.service;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.ChatTeam;

import com.github.pagehelper.PageInfo;

public interface ChatTeamService {
	public boolean save(ChatTeam bean);

	public boolean update(ChatTeam bean);

	public ChatTeam findById(String id);


	public PageInfo<?> find(ChatTeam bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);
}
