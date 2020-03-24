package org.easymis.easyicc.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.easymis.easyicc.domain.entity.SchedulingUser;
import org.easymis.easyicc.domain.vo.SchedulingImportData;

public interface SchedulingUserService {
	SchedulingUser findById(String id);
	void delete(String id);
	void update(SchedulingUser bean);
	public List<Map<String, Object>> getPersonelSchedulings(String orgId, String staffId, Date startTime);
	public List<Map<String, Object>> getSchedulings(String companyId, Date startTime, String subjectId, String schoolId, String businessGroupId);
	public void importData(final SchedulingImportData data) throws Exception;
}
