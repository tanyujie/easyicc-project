package org.easymis.easyicc.mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.easymis.easyicc.domain.entity.Member;

public interface MemberMapper {
	@Select("<script>" + "SELECT * FROM member WHERE mobile_no=#{phoneNumber}" + "</script>")
	Member findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

	@Select("select * from member t WHERE t.mobile_no = #{phoneNumber}")
	Member get(@Param("phoneNumber") String phoneNumber, @Param("password") String password);

	 @Insert("insert into member(member_id,member_no,org_id,sex,age,company_name,department,position,password,head_url,mobile_no,email,modify_time,create_time,name,enabled)values(#{memberId},#{memberNo},#{orgId},#{sex},#{age},#{companyName},#{department},#{position},#{password},#{headUrl},#{mobileNo},#{email},#{modifyTime},#{createTime},#{name},#{enabled})")  
	 public boolean save(Member bean); 

	@Select("select * from member t WHERE t.member_id = #{memberId}")
	Member findById(@Param("memberId") String memberId);
}
