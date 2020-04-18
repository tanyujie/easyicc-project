package org.easymis.easyicc.service.impl;

import java.util.Date;
import java.util.UUID;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.common.utils.MD5Util;
import org.easymis.easyicc.domain.entity.Department;
import org.easymis.easyicc.domain.entity.HrmStaffInfo;
import org.easymis.easyicc.domain.entity.Member;
import org.easymis.easyicc.domain.entity.Organize;
import org.easymis.easyicc.domain.vo.RegisterVo;
import org.easymis.easyicc.mybatis.mapper.DepartmentMapper;
import org.easymis.easyicc.mybatis.mapper.HrmStaffInfoMapper;
import org.easymis.easyicc.mybatis.mapper.MemberMapper;
import org.easymis.easyicc.mybatis.mapper.OrganizeMapper;
import org.easymis.easyicc.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberMapper mapper;
	@Autowired
	private OrganizeMapper organizeMapper;
	@Autowired
	private DepartmentMapper departmentMapper;
	@Autowired
	private HrmStaffInfoMapper staffInfoMapper;
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

	@Override
	public boolean save(Member bean) {		
		return mapper.save(bean);
	}


	public RestResult saveRegister(RegisterVo vo) {
		String orgId=UUID.randomUUID().toString().replace("-", "");
		String memberId=UUID.randomUUID().toString().replace("-", "");
		String staffId=UUID.randomUUID().toString().replace("-", "");
		String departmentId=UUID.randomUUID().toString().replace("-", "");
		//会员信息
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberNo(vo.getMobileNo());
		member.setPassword(MD5Util.md5(vo.getPassword()));
		member.setMobileNo(vo.getMobileNo());
		member.setCreateTime(new Date());
		member.setEnabled(true);
		
		//组织公司信息
		Organize organize = new Organize();
		organize.setOrgId(orgId);
		organize.setOrgNo(orgId);
		organize.setOrgName(vo.getOrgName());
		organize.setCreateTime(new Date());
		organize.setStatus(true);
		organize.setLockFlag(2);
		organize.setIpGateway(1);
		//部门信息
		Department department= new Department();
		department.setId(departmentId);
		department.setOrgId(orgId);
		department.setDepict("技术部");
		department.setName("技术部");
		department.setLevel(1);
		department.setStatus(1);
		
		//员工信息
		HrmStaffInfo hrmStaffInfo= new HrmStaffInfo();
		hrmStaffInfo.setStaffId(staffId);
		hrmStaffInfo.setOrgId(orgId);
		hrmStaffInfo.setDepartmentId(departmentId);
		hrmStaffInfo.setMemberId(memberId);
		hrmStaffInfo.setSetLanguage(1);
		//js jsconfig
		//组织全局配置 organize_config
		
		
		mapper.save(member);
		
		organizeMapper.save(organize);
		
		departmentMapper.save(department);
		
		staffInfoMapper.save(hrmStaffInfo);
		
		return RestResult.buildSuccess();
	}

}
