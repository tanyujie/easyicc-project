package org.easymis.easyicc.web.card.distribution.service;

import org.easymis.easyicc.domain.entity.Card;

public interface RocketMQSendMessageService {
    String before(Card card);


    void after(Card card, boolean allocation);
}
