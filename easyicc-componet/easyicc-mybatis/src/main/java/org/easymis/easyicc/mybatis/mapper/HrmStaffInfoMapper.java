package org.easymis.easyicc.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.easymis.easyicc.domain.entity.HrmStaffInfo;
import org.easymis.easyicc.domain.vo.StaffOnlineTreeVo;

public interface HrmStaffInfoMapper {
	@Select(" SELECT staff_id,name,org_id,department_id FROM hrm_staff_info t where org_id=#{orgId}")
	public List<HrmStaffInfo> findByOrgId(@Param("orgId")String orgId);
	@Select(" SELECT staff_id,name,org_id,department_id FROM hrm_staff_info t where org_id=#{orgId}")
	public List<StaffOnlineTreeVo> findByOnlineTree(@Param("orgId")String orgId);
}
