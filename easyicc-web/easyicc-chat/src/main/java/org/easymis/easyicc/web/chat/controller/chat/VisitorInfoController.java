package org.easymis.easyicc.web.chat.controller.chat;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import org.easymis.easyicc.common.result.RestResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/visitorInfo", description = "访客信息")
@Controller
@RequestMapping("/visitorInfo")
public class VisitorInfoController {
	
	@ApiOperation(value = "访客信息")
	@RequestMapping("/index.json")
	@ResponseBody
	public RestResult currentChat(String o, String v, String u, String config, String chatUrl, Model model)
			throws NoSuchAlgorithmException {
		HashMap map = new HashMap();

		return RestResult.buildSuccess(map);
	}
}
