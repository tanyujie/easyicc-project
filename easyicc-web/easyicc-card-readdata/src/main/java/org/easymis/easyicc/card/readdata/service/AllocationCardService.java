package org.easymis.easyicc.card.readdata.service.impl;

import javax.annotation.PostConstruct;

import org.easymis.easyicc.card.readdata.service.AllocationCardService;
import org.springframework.stereotype.Service;
@Service
public class AllocationCardServiceImpl implements AllocationCardService {
	
	@PostConstruct
	public void init(){
		System.out.println("开始名片分配服务");
	}
}
