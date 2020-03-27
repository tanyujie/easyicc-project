package org.easymis.easyicc.web.card.distribution.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.Card;
import org.easymis.easyicc.domain.entity.CardRule;
import org.easymis.easyicc.service.CardRuleService;
import org.easymis.easyicc.service.NotifyService;
import org.easymis.easyicc.web.card.distribution.service.DistributionCardService;
import org.easymis.easyicc.web.card.distribution.service.RocketMQSendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class DistributionCardServiceImpl implements DistributionCardService {
    private final static Log _logger = LogFactory.getLog(DistributionCardServiceImpl.class);
    private final static SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMdd");
    private final static String SYSTEMID = "system";
    private ExecutorService allocationExecutor = Executors.newCachedThreadPool();
    @Autowired
    private CardRuleService cardRuleService;
    @Autowired
    private RocketMQSendMessageService rocketMQSendMessageService;

    @Autowired
    private NotifyService notifyService;
    /**
     * 分配名片
     * @param cardList
     * @throws Exception
     */
    public RestResult allocationCard(List<Card> cardList, String serveName) {
        _logger.info("开始分配名片，名片数:" + cardList.size());
        try {
            String time = formatter2.format(new Date());
            long _start = System.currentTimeMillis();
            if (cardList.size() > 0) {
                for (Card card : cardList) {
                    //判断是否初始化, 此处是单线程， 不用考虑并发问题
                    //cacheFactory.checkInit(card.getCompanyId(), DistributionCardServiceImpl.this, time);
                  //  allocationExecutor.execute(new DistributionCardServiceImpl.AllocationTask(cacheFactory.getCardRuleCache().getCardRule(card.getCompanyId()), card));
                }
                _logger.info("分配任务执行完成， 一共分配" + cardList.size());
            }
            List<Card> expiredCards = getExpiredCards(serveName);
            if (expiredCards.size() > 0) {
                for (Card card : expiredCards) {
                    if (!card.isSaleCard()) {
                        allocationExecutor.execute(new DistributionCardServiceImpl.ExpiredTask(card));
                    }
                }
                _logger.info("回收超期数据:" + expiredCards.size());
            }

        } catch (Throwable e) {
            _logger.error(e.getMessage(), e);
            RestResult.buildError("分配失败");
        }
        return RestResult.buildSuccess("分配成功");
    }
    private List<Card> getExpiredCards(String serverName){
        List<CardRule> rules = cardRuleService.findExpiredCards(serverName);
        List<Card> list = new ArrayList<Card>();
        for(CardRule rule : rules){
            String sql = "select a.* from js_visitor_info a "+
                    "where a.company_id = ? and (a.allocation_status=1 or a.allocation_status=3) "+
                    "and a.allocation_time + "+rule.getExpiredHour()+"/24 < ? "; 

           // list.addAll(this.jdbcTemplate.query(sql, new Object[]{rule.getCompanyId(), new Date()}, new CardRowMapper()));
        }
        return list;
    }
    class AllocationTask implements Runnable{

        private CardRule rule;
        private Card card;
        //private CountDownLatch latch;

        AllocationTask(CardRule rule, Card card/*, CountDownLatch latch*/){
            this.rule = rule;
            this.card = card;
            //this.latch = latch;
        }

        @Override
        public void run() {
            try{
                rocketMQSendMessageService.before(card);
              /*  if(card.getSaleId() != null && !card.getSaleId().equals("")){
                    SaleUser sale = cacheFactory.getSubjectUserCache().getUser(card.getCompanyId(), card.getSaleId());
                    if(sale != null){
                        int i = updateCard(card.getId(), card.getSaleId(), Card.STATUS_SALE_ALLOCATIONED, card.getModifyIdentity());
                        if(i>0){
                            card.setUserId(card.getSaleId());
                            cacheFactory.getSubjectUserCache().allocationCard(sale, card);
                            createCardLog(card.getCompanyId(), card.getId(), card.getSaleId(), CardLog.ALLOCATION_TYPE_SALE, SYSTEMID, new Date());
                            card.setAllocationStatus(Card.STATUS_SALE_ALLOCATIONED);
                            rocketMQSendMessageService.after(card, true);
                            return;
                        }
                    }
                }

                if(card.getSubjectId() == 0 && rule.getDefaultSubjectId() > 0){
                    card.setSubjectId(rule.getDefaultSubjectId());
                }

                if(card.getSchooleId() == 0 && rule.getDefaultSchoolId() > 0){
                    card.setSchooleId(rule.getDefaultSchoolId());
                }

                //分配模式 0按校区/项目; 1 分配给创建者;
                if(this.rule.getAllocationModel() == 0){
                    if(card.getSubjectId() == 0 || card.getSchooleId() == 0){
                        //错误的数据， 转人工分配(人工分配前需要重新设置)
                        updateCard(card.getId(), "", Card.STATUS_WAIT_USE_ALLOCATION, card.getModifyIdentity());
                        notifyService.notifyWaitAllocation(card.getCompanyId(), "您有一个名片需要分配");
                    }else{
                        //需要判断是否重复数据， 如果重复，不进入分配
                        if(exists(card.getCompanyId(), card.getMobile(), card.getCreateTime())){
                            _logger.info(String.format("存在重复线索[%s]", card.getMobile()));
                            updateCard(card.getId(), "", Card.STATUS_REPEAT, card.getModifyIdentity());
                        }else{
                            autoAllocation(card);
                        }
                    }
                }else if(this.rule.getAllocationModel() == 1){
                    allocationToCreator(card);
                }*/
            }catch(Exception e){
                _logger.error(e.getMessage(), e);
            }finally{
                //this.latch.countDown();
            }
        }

    }
    class ExpiredTask implements Runnable{
        private Card card;
        ExpiredTask(Card card){
            this.card = card;
        }
        @Override
        public void run() {
            try{
                expired(card);
            }catch(Exception e){
                _logger.error(e.getMessage(), e);
            }finally{
            }
        }
    }
    @Transactional
    public boolean expired(Card c){
        Date now = new Date();
        String sql = "update js_visitor_info set allocation_status = ?, is_back = 0, is_expired = 1, user_id = ?, modify_identity = ?, back_user_id = ?, back_time = ? where id = ? and modify_identity= ? ";
        int count =1;// this.jdbcTemplate.update(sql, Card.STATUS_WAIT_USE_ALLOCATION, "", UUID.randomUUID().toString(), SYSTEMID, now, c.getId(), c.getModifyIdentity());
        if(count > 0){
            //this.createCardLog(c.getCompanyId(), c.getId(), c.getUserId(), CardLog.ALLOCATION_TYPE_EXPIRED, SYSTEMID, now);
           // this.cacheFactory.getSubjectUserCache().expired(c.getCompanyId(), c.getId(), c.getUserId());
            this.notifyService.notifyWaitAllocation(c.getCompanyId(), "您有一个名片需要分配");
            return true;
        }else{
            return false;
        }
    }
}
