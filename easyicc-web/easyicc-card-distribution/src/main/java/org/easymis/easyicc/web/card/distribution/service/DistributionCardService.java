package org.easymis.easyicc.web.card.distribution.service;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.Card;

public interface DistributionCardService {
    /**
     * 分配名片
     * @param cardList
     * @throws Exception
     */
    RestResult allocationCard(List<Card> cardList, String serveName);
}
