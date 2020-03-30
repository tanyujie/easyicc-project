package org.easymis.easyicc.web.card.pushdata.service;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.easymis.easyicc.domain.entity.CardPushConfig;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RocketMQMessageListener(topic = "api-topic", consumerGroup = "api-group")
public class PushAPIService implements RocketMQListener<CardPushConfig> {
	@Override
	public void onMessage(CardPushConfig message) {
		// TODO Auto-generated method stub

	}
}
