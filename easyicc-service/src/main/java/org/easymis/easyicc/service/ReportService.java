package org.easymis.easyicc.service;

import java.util.List;
import java.util.Map;

import org.easymis.easyicc.domain.entity.CustomerReport;

public interface ReportService {
	public List<CustomerReport> getCustomerReport(Map<String, Object> params, String startTime, String endTime);
}
