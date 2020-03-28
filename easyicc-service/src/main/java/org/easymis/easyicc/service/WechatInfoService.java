package org.easymis.easyicc.service;

import java.util.List;

import org.easymis.easyicc.domain.entity.WechatInfo;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

public interface WechatInfoService {
	public WechatInfo findById(String id);
	public void save(WechatInfo bean);
	public void Update(WechatInfo bean);
	public void delete(String id);
	public List<WechatInfo> findByOrgId(String orgId);
	public PageInfo findPage(WechatInfo bean,Page page);
}
