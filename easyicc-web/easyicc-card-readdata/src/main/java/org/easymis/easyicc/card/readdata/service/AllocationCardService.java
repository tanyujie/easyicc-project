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
import org.easymis.easyicc.domain.entity.Card;
import org.easymis.easyicc.domain.entity.CardRule;
import org.easymis.easyicc.service.CardRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service
public class AllocationCardService {
	private final static Log _logger = LogFactory.getLog(AllocationCardService.class);
	private ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(2);
	private ExecutorService allocationExecutor = Executors.newCachedThreadPool();
	private final static SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");

    @Value(value = "${clusterName}")
	private String serverName;
	@Autowired
	private CardRuleService cardRuleService;
	
	@PostConstruct
	public void init() {
		_logger.info("名片分配serverName:" + this.serverName);
		scheduledExecutor.scheduleWithFixedDelay(() -> {
			synchronized (AllocationCardService.this) {
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
			/*
			 * DistributionResult result = distributionCardService.allocationCard(cards,
			 * serverName); if(result.getCode() != ResultTypeEnum.SUCCESS.getCode()){
			 * _logger.error("分配失败 code "+ result.getCode() +" cards, " + cards.toString());
			 * }
			 */
        }, 120, 120, TimeUnit.SECONDS);
	}
	/**
	 * 获取当天所有待分配的名片
	 * @return
	 */
	public List<Card> getAllWaitForAllocationCards(){

		String sql = "select a.* from js_visitor_info a " +
						"inner join js_cuour_card_rule b on a.company_id=b.company_id "+
						"where b.use_allocation=1 and b.server_name = ? " +
						"and a.create_time is not null and a.create_time between (sysdate - 2) and (sysdate - b.allocation_delay/24/60) "+
						"and allocation_status = 0 "+
						"order by a.company_id, a.ext_column7, a.create_time";
		//return this.jdbcTemplate.query(sql, new Object[]{this.serverName}, new CardRowMapper());
		return null;
	}
}
