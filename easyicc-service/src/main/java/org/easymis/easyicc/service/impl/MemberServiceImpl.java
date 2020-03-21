package org.easymis.easyicc.service.impl;

import org.easymis.easyicc.domain.entity.Member;
import org.easymis.easyicc.mybatis.mapper.MemberMapper;
import org.easymis.easyicc.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberMapper mapper;

	@Override
	public Member findById(String memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member findByMobile(String mobile) {
		return mapper.findByPhoneNumber(mobile);
	}

	@Override
	public Member findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member saveQuickRegister(String phoneNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
