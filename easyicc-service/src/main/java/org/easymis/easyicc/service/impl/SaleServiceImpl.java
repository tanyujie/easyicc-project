package org.easymis.easyicc.service.impl;

import java.util.List;
import java.util.Map;

import org.easymis.easyicc.service.SaleService;
import org.springframework.stereotype.Service;
@Service
public class SaleServiceImpl implements SaleService {

	@Override
	public List<Map<String, Object>> getUsers(String companyId) {
		//		String sql = "select user_id, real_name, nick_name from js_user where company_id=?";
		return null;
	}
}
