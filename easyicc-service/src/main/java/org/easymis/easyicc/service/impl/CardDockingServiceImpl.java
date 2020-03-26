package org.easymis.easyicc.service.impl;

import java.util.List;

import org.easymis.easyicc.service.CardDockingService;
import org.springframework.stereotype.Service;
@Service
public class CardDockingServiceImpl implements CardDockingService {

	@Override
	public int setNotValidate(String companyId, String staticId) {
		/*
		int count = 0;//is_valid = 0, is_expired = 0, is_back=0,  allocation_status = ?
		if (companyId > 0 && staticId != null && !"".equals(staticId)) {
			String sql = "update js_visitor_info set is_valid = 0, is_expired = 0, is_back=0,  allocation_status = ? where VISITOR_STATIC_ID = ? and company_id = ?";
			count = this.jdbcTemplate.update(sql, Card.STATUS_FINISHED, staticId, companyId);
		}
		return count;
		 */		return 0;
	}

	@Override
	public int resetAllocation(String companyId, String staticId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int returnAllocation(String companyId, String staticId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int expiredAllocation(String companyId, String staticId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int waitAllocation(String companyId, String staticId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int finishedAllocation(String companyId, String staticId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int repeatAllocation(String companyId, String staticId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setNotValidateBatch(String companyId, List<String> staticIds) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int resetAllocationBatch(String companyId, List<String> staticIds) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int returnAllocationBatch(String companyId, List<String> staticIds) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int expiredAllocationBatch(String companyId, List<String> staticIds) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int waitAllocationBatch(String companyId, List<String> staticIds) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int finishedAllocationBatch(String companyId, List<String> staticIds) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int repeatAllocationBatch(String companyId, List<String> staticIds) {
		// TODO Auto-generated method stub
		return 0;
	}

}
