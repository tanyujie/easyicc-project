package org.easymis.easyicc.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.easymis.easyicc.domain.entity.MyFriends;

public interface MyFriendsMapper {
	int insertByBean(MyFriends bean);
	
	
	@Select("<script>" + "SELECT * FROM my_friends WHERE member_id=#{memberId} and friend_id=#{friendId}" + "</script>")
	MyFriends find(@Param("memberId") String memberId,@Param("friendId") String friendId);
}
