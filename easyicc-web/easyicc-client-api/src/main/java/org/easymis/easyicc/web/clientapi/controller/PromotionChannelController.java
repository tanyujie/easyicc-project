package org.easymis.easyicc.web.clientapi.controller;

import org.easymis.easyicc.service.PromotionChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

@Api(value = "/promotionChannel", description = "推广渠道")
@Controller
@RequestMapping("/promotionChannel")
public class PromotionChannelController {
	@Autowired
	private PromotionChannelService service;
}
