package org.easymis.easyicc.web.card.pushdata.service;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RocketMQMessageListener(topic = "wechat-topic", consumerGroup = "wechat-group")
public class PushWechatService {

}
