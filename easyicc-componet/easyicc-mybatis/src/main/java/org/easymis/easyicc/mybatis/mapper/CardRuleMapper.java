package org.easymis.easyicc.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.easymis.easyicc.domain.entity.CardRule;

public interface CardRuleMapper {
	 @Select("select * from card_rule t WHERE t.org_id = #{orgId}")  
	 CardRule findByOrgId(@Param("orgId") String orgId);
	 @Select("select * from card_rule t WHERE t.use_Allocation = 1 and t.org_id = #{orgId}")  
	 public List<CardRule> findByServerName(@Param("serverName") String serverName);
	 @Select("select * from card_rule t WHERE use_Allocation = 1 and expired_Recover = 1 and t.org_id = #{orgId}")  
	public List<CardRule> findExpiredCards(@Param("serverName") String serverName);
}
