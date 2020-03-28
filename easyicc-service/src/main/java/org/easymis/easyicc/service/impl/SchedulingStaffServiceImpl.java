package org.easymis.easyicc.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.easymis.easyicc.domain.entity.SchedulingUser;
import org.easymis.easyicc.domain.vo.SchedulingImportData;
import org.easymis.easyicc.service.SchedulingUserService;
import org.springframework.stereotype.Service;
@Service
public class SchedulingUserServiceImpl  implements SchedulingUserService{

	@Override
	public List<Map<String, Object>> getPersonelSchedulings(String orgId, String staffId, Date startTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SchedulingUser findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(SchedulingUser bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Map<String, Object>> getSchedulings(String companyId, Date startTime, String subjectId, String schoolId,
			String businessGroupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void importData(SchedulingImportData data) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
