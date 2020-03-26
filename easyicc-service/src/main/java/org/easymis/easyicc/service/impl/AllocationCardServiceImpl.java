package org.easymis.easyicc.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymis.easyicc.domain.entity.Card;
import org.easymis.easyicc.domain.entity.CardLog;
import org.easymis.easyicc.domain.vo.StaffSalesVo;
import org.easymis.easyicc.mybatis.mapper.CardMapper;
import org.easymis.easyicc.mybatis.mapper.VisitorInfoMapper;
import org.easymis.easyicc.service.AllocationCardService;
import org.easymis.easyicc.service.CardLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AllocationCardServiceImpl implements AllocationCardService {
	private final static SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMdd");
	private final static Log _logger = LogFactory.getLog(AllocationCardService.class);

	@Autowired
	private CardMapper cardMapper;
	@Autowired
	private VisitorInfoMapper visitorInfoMapper;
	@Autowired
	private CardLogService cardLogService;
	@Override
	public List<StaffSalesVo> getCanAllocationSaleUser(String companyId, String subjectId, String schoolId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<String, String> getStatus(String companyId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean updateCard(String cardId, String modifyIdentity, String subjectId, String schoolId) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean setNotValidate(String companyId, String cardId) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean userAllocationCard(String companyId, String cardId, String userId, String operatorId) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean allocation(String cardId) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void clearCache(String companyId, String userId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void clearAlternateCache(String companyId, String userId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<StaffSalesVo> getSaleUser(String orgId, Date startTime, Date endTime) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean back(String orgId, String cardId, String staffId, int backType, String desp) throws Exception {
		List<CardLog> logs = cardLogService.find(cardId, CardLog.ALLOCATION_TYPE_BACK);
		if(logs != null && logs.size() > 0){
			throw new Exception("该名片已经被退回过一次， 不能再退回!");
		}else{
/*			Date now = new Date();
			String time = formatter2.format(now);
			cacheFactory.checkInit(orgId, this, time);
			Card c = this.getCard(cardId);
			if(c.isSaleCard()){
				throw new Exception("该名片为高意向名片， 不能退回!");
			}
			if(c != null && c.getUserId().equals(userId) && c.getCompanyId() == companyId){
				String sql = "update js_visitor_info set allocation_status = ?, is_back = 1, is_expired = 0, back_type = ?, back_desp = ?,  user_id = ?, modify_identity = ?, back_user_id = ?, back_time = ? where id = ? and modify_identity= ? ";
				int count = this.jdbcTemplate.update(sql, Card.STATUS_WAIT_USE_ALLOCATION, backType, desp, "", UUID.randomUUID().toString(), c.getUserId(), now, cardId, c.getModifyIdentity());
				if(count > 0){
					this.cacheFactory.getSubjectUserCache().backCard(orgId, cardId, staffId);
					this.createCardLog(orgId, cardId, staffId, CardLog.ALLOCATION_TYPE_BACK, staffId, now);
					this.notifyService.notifyWaitAllocation(c.getCompanyId(), "您有一个名片需要分配");
				}
				return true;
			}*/
			return false;
		}
}
	@Override
	public boolean finished(String orgId, String cardId, String staffId, String desp) {
		String time = formatter2.format(new Date());
		//cacheFactory.checkInit(orgId, this, time);
/*		Card c = this.getCard(cardId);
		if(c != null && c.getUserId().equals(userId) && c.getCompanyId() == companyId){
			String sql = "update js_visitor_info set allocation_status = ?, is_back = 0, is_expired = 0, finish_desp = ?,  user_id = ?, modify_identity = ? where id = ? and modify_identity= ? ";
			int count = 1;//this.jdbcTemplate.update(sql, Card.STATUS_FINISHED, desp, staffId, UUID.randomUUID().toString(), cardId, c.getModifyIdentity());
			
			if(count > 0){
				this.cacheFactory.getSubjectUserCache().finished(orgId, cardId, staffId, c.isSaleCard());
				if(c.isSaleCard()){
					this.createCardLog(orgId, cardId, staffId, CardLog.ALLOCATION_TYPE_SALE_FINIHSED, staffId, new Date());
				}else{
					this.createCardLog(orgId, cardId, staffId, CardLog.ALLOCATION_TYPE_FINISHED, staffId, new Date());
				}
			}
			return true;
		}*/
		return false;
}
	@Override
	public Card getCard(String companyId, String staticId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Card> getAllWaitForAllocationCards(String serverName) {
		// TODO Auto-generated method stub
		return cardMapper.getAllWaitForAllocationCards(serverName);
	}
	private void createCardLog(String companyId, String cardId, String userId, int allocationType, String operatorId, Date time){
		CardLog log = new CardLog();
		log.setAllocationType(allocationType);
		log.setCompanyId(companyId);
		log.setCardId(cardId);
		log.setUserId(userId);
		log.setOperatorUserId(operatorId);
		log.setAllocationTime(time);
		this.cardLogService.save(log);
	}
	
}
