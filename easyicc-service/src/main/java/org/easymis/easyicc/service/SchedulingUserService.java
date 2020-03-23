package org.easymis.easyicc.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SchedulingUserService {
	public List<Map<String, Object>> getPersonelSchedulings(String orgId, String staffId, Date startTime);
}
