package org.easymis.easyicc.service;

import org.easymis.easyicc.domain.entity.Member;

public interface MemberService {

	public Member findById(String memberId);

	public Member findByMobile(String mobile);

	Member findByEmail(String email);

	Member saveQuickRegister(String phoneNumber);
}
