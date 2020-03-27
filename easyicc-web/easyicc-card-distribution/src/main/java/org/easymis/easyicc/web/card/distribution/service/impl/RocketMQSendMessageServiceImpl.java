package org.easymis.easyicc.web.card.distribution.service.impl;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.easymis.easyicc.domain.entity.Card;
import org.easymis.easyicc.web.card.distribution.service.RocketMQSendMessageService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

public class RocketMQSendMessageServiceImpl implements RocketMQSendMessageService {
    private final static Log _logger = LogFactory.getLog(RocketMQSendMessageServiceImpl.class);

    private final static String TOPIC_BEFORE = "Global.webcall.cuour.rocketmq.topic.before";
    private final static String TAGS_BEFORE = "Global.webcall.cuour.rocketmq.tags.before";

    private final static String TOPIC_AFTER = "Global.webcall.cuour.rocketmq.topic.after";
    private final static String TAGS_AFTER = "Global.webcall.cuour.rocketmq.tags.after";

    @Autowired(required=false)
    private  DefaultMQProducer defaultMQProduce;

    public String before(Card card){
        String jsonJson = JSON.toJSONString(card);
        _logger.info("before同步发送给mq，card的详细信息====="+jsonJson);
        Properties prop =null;// ConfigLoader.getInstance().load();
        String topicBefore = prop.getProperty(TOPIC_BEFORE, "");
        String tagsBefore = prop.getProperty(TAGS_BEFORE, "");
        Message msg= this.getCard(topicBefore,tagsBefore,card);
        String resultCode = "";
        try {
            SendResult result=defaultMQProduce.send(msg);
            resultCode = result.getSendStatus().name();
            if(resultCode.equals(SendStatus.SEND_OK.name())){
                resultCode = "";
            }
            _logger.info("before同步发送给mq返回结果"+result.getSendStatus().name());
        } catch (MQClientException e) {
            _logger.error("mq-客户端异常,card详细信息"+jsonJson,e);
        } catch (RemotingException e) {
            _logger.error("mq-远程调用异常异常,card详细信息"+jsonJson,e);
        } catch (MQBrokerException e) {
            _logger.error("mq-Broker异常,card详细信息"+jsonJson,e);
        } catch (InterruptedException e) {
            _logger.error("mq-InterruptedException异常,card详细信息"+jsonJson,e);
        }
        return resultCode;
    }


    public void after(Card card,boolean allocation){

        String jsonJson = JSON.toJSONString(card);
        _logger.info("after异步发送给mq，card的详细信息====="+jsonJson);
        Properties prop =null;// ConfigLoader.getInstance().load();
        String topicAfter = prop.getProperty(TOPIC_AFTER, "");
        String tagsAfter = prop.getProperty(TAGS_AFTER, "");
        card.setAllocation(allocation);
        Message msg= this.getCard(topicAfter,tagsAfter,card);
        try {
            defaultMQProduce.sendOneway(msg);
        } catch (MQClientException e) {
            _logger.error("after异步发送给mq,mq-客户端异常,card详细信息"+jsonJson,e);
        } catch (RemotingException e) {
            _logger.error("after异步发送给mq,mq-远程调用异常异常,card详细信息"+jsonJson,e);
        }catch (InterruptedException e) {
            _logger.error("after异步发送给mq,mq-InterruptedException异常,card详细信息"+jsonJson,e);
        }
        _logger.info("after异步发送给mq，card的详细信息====="+jsonJson);
    }

    /**
     * 封装Message
     * @param topic
     * @param tags
     * @param card
     * @return
     */
    public Message getCard(String topic,String tags,Card card){
        Message msg= new Message();
        msg.setTopic(topic);
        msg.setTags(tags);
        String cardJson = JSON.toJSONString(card);
        msg.setBody(cardJson.getBytes());
        return msg;
    }
}
