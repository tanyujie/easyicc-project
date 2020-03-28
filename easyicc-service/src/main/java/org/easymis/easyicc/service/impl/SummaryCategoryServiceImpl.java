package org.easymis.easyicc.service.impl;

import org.easymis.easyicc.mybatis.mapper.SummaryCategoryMapper;
import org.easymis.easyicc.service.SummaryCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SummaryCategoryServiceImpl implements SummaryCategoryService{
	@Autowired
	private SummaryCategoryMapper mapper;
}
