package org.easymis.easyicc.web.card.pushdata.service;

import java.io.UnsupportedEncodingException;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Component;

@Component
public class PushCardService {
	private String consumerGroup = "pay_consumer_group";
	private String nameServerAddr = "127.0.0.1:9876";
	private static final String topic = "pay_topic";

	private DefaultMQPushConsumer consumer;

	public PushCardService() throws MQClientException {
		consumer = new DefaultMQPushConsumer(consumerGroup);
		consumer.setNamesrvAddr(nameServerAddr);
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
		consumer.subscribe(topic, "*");
		consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
			try {
				Message msg = msgs.get(0);
				System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(),
						new String(msgs.get(0).getBody()));
				String topic = msg.getTopic();
				String body = new String(msg.getBody(), "utf-8");
				String tags = msg.getTags();
				String keys = msg.getKeys();
				System.out.println("topic=" + topic + ", tags=" + tags + ", keys=" + keys + ", msg=" + body);
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return ConsumeConcurrentlyStatus.RECONSUME_LATER;
			}
		});
		System.out.println("启动消费者");
		consumer.start();
	}
}
