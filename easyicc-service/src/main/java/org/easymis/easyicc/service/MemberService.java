package org.easymis.easyicc.service;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.Member;
import org.easymis.easyicc.domain.vo.RegisterVo;

public interface MemberService {
	public boolean save(Member bean);

	public Member findById(String memberId);

	public Member findByMobile(String mobile);

	Member findByEmail(String email);

	Member saveQuickRegister(String phoneNumber);

	public RestResult saveRegister(RegisterVo vo);
}
