package org.easymis.easyicc.mybatis.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.easymis.easyicc.domain.entity.PromotionChannel;

public interface PromotionChannelMapper {
	@Select("select * from promotion_channel")

	public List<PromotionChannel> getList(HashMap<String, Object> params);

	@Insert("insert into promotion_channel(id,org_id,channel_name,match_type,depict,match_rule,match_content,status)values(#{id},#{orgId},#{channelName},#{matchType},#{depict},#{matchRule},#{matchContent},#{status})")
	public void save(PromotionChannel bean);

	@Insert("insert into promotion_channel(id,org_id,channel_name,match_type,depict,match_rule,match_content,status)values(#{id},#{orgId},#{channelName},#{matchType},#{depict},#{matchRule},#{matchContent},#{status})")
	public void saveBatch(List<PromotionChannel> beans);

	@Update("UPDATE promotion_channel SET id= #{id},org_id= #{orgId},channel_name= #{channelName},match_type= #{matchType},depict= #{depict},match_rule= #{matchRule},match_content= #{matchContent},status= #{status} WHERE id= #{id}")
	public void update(PromotionChannel bean);

	@Delete(" DELETE FROM promotion_channel WHERE id = #{id}")
	public void delete(String id);

	public void removeBatch(List<String> list);

	public void restoreBatch(List<String> list);

	public void deleteBatch(List<String> list);

	@Select("select * from promotion_channel t WHERE t.id = #{id}")
	public PromotionChannel findById(String id);

	@Select(" SELECT t.* FROM promotion_channel t }")
	public List<PromotionChannel> findByIds(List<String> list);
}