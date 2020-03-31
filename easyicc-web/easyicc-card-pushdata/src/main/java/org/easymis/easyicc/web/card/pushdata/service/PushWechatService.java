package org.easymis.easyicc.web.card.pushdata.service;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.easymis.easyicc.domain.entity.WechatInfo;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RocketMQMessageListener(topic = "wechat-topic", consumerGroup = "wechat-group")
public class PushWechatService implements RocketMQListener<WechatInfo> {
	@Override
	public void onMessage(WechatInfo wechatInfo) {
		// TODO Auto-generated method stub
		log.info("received message: " + wechatInfo);
	}

}
