package org.easymis.easyicc.card.readdata.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymis.easyicc.card.readdata.provider.DistributionCardProvider;
import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.common.result.ResultCode;
import org.easymis.easyicc.domain.entity.Card;
import org.easymis.easyicc.domain.entity.CardRule;
import org.easymis.easyicc.service.AllocationCardService;
import org.easymis.easyicc.service.CardRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service
public class AllocationService {
	private final static Log _logger = LogFactory.getLog(AllocationCardService.class);
	private ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(2);
	private ExecutorService allocationExecutor = Executors.newCachedThreadPool();
	private final static SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");

    @Value(value = "${clusterName}")
	private String serverName;
	@Autowired
	private CardRuleService cardRuleService;
	@Autowired
	private AllocationCardService allocationCardService;
	
	@Autowired
	private DistributionCardProvider distributionCardProvider;
	
	
	@PostConstruct
	public void init() {
		_logger.info("名片分配serverName:" + this.serverName);
		scheduledExecutor.scheduleWithFixedDelay(() -> {
			synchronized (AllocationService.this) {
				String time = formatterTime.format(new Date());
                List<CardRule> list = cardRuleService.findByServerName(serverName);
                for(CardRule cr: list){
                    if(cr.getResetTime() != null && !cr.getResetTime().equals("") && time.equals(cr.getResetTime())){
                        _logger.info("重置["+cr.getOrgId()+"]");
                      // distributionCardService.removeLocalCacheByCompanyId(cr.getCompanyId());
                       //pushdataService.removeLocalCacheByCompanyId(cr.getCompanyId());
                    }
                }
			}
		}, 60, 60, TimeUnit.SECONDS);
		scheduledExecutor.scheduleWithFixedDelay(() -> {
            List<Card> cards = getAllWaitForAllocationCards();
            RestResult result = distributionCardProvider.allocationCard(cards,serverName);
            if(result.getCode()!=ResultCode.SUCCESS)
            {
            	_logger.error("分配失败 code "+ result.getCode() +" cards, " + cards.toString());
            }
        }, 120, 120, TimeUnit.SECONDS);
	}
	/**
	 * 获取当天所有待分配的名片
	 * @return
	 */
	public List<Card> getAllWaitForAllocationCards(){
		return allocationCardService.getAllWaitForAllocationCards(serverName);
	}
}
