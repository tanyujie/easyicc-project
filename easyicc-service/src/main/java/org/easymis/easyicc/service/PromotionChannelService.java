package org.easymis.easyicc.service;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.PromotionChannel;

import com.github.pagehelper.PageInfo;

public interface PromotionChannelService {
	public boolean save(PromotionChannel bean);

	public boolean update(PromotionChannel bean);

	public PromotionChannel findById(String id);


	public PageInfo<?> find(PromotionChannel bean, Integer pageNum, Integer pageSize);

	public RestResult deleteByIds(String ids);
}
