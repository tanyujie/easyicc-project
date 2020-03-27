package org.easymis.easyicc.web.card.distribution.controller;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.Card;
import org.easymis.easyicc.web.card.distribution.service.DistributionCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(value = "/distributionCard", description = "名片分配")
@RestController
@RequestMapping("/distribution")
public class DistributionCardController {
	
	@Autowired
	private DistributionCardService service;
	@RequestMapping(value = { "/allocationCard" }, method = { RequestMethod.GET, RequestMethod.POST })
	RestResult allocationCard(List<Card> cardList, String serveName) {
		return service.allocationCard(cardList, serveName);
	}
}
