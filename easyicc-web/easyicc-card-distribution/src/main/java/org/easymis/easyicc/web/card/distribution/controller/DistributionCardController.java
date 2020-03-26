package org.easymis.easyicc.web.card.distribution.controller;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.Card;
import org.easymis.easyicc.web.card.distribution.service.DistributionCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

@Api(value = "/distributionCard", description = "名片分配")
@Controller
@RequestMapping("/distributionCard")
public class DistributionCardController {
	
	@Autowired
	private DistributionCardService service;
	RestResult allocationCard(List<Card> cardList, String serveName) {
		return service.allocationCard(cardList, serveName);
	}
}
