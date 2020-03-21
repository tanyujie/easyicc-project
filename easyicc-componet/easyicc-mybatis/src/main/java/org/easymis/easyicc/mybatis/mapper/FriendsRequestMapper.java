package org.easymis.easyicc.mybatis.mapper;

import org.easymis.easyicc.domain.entity.FriendsRequest;

public interface FriendsRequestMapper {
	int insertByBean(FriendsRequest bean);
	FriendsRequest find(String sendUserId,String acceptUserId);

	void delete(String sendUserId,String acceptUserId);
}
