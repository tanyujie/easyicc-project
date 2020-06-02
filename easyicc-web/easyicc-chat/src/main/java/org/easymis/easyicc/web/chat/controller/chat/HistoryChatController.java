package org.easymis.easyicc.web.chat.controller.chat;

import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 
　 * <p>Title: 历史记录</p>
　 * <p>Description: </p>
　 * @author 谭宇杰
　 * @date 2020年6月2日
 */
@Api(value = "/historyChat", description = "历史记录")
@Controller
@RequestMapping("/historyChat")
public class HistoryChatController {
	
	@ApiOperation(value = "历史记录")
	@RequestMapping("/index.json")
	public String currentChat(String o,String v,String u,String config,String chatUrl,Model model) throws NoSuchAlgorithmException {
		model.addAttribute("orgId", o);
		model.addAttribute("visitorId", v);
		model.addAttribute("staffId", u);
		model.addAttribute("jsConfigId", config);
		model.addAttribute("chatUrl", chatUrl);
		//ChatRecord chatRecord=chatRecordService.findByVisitorId(v);
		//model.addAttribute("chatId", chatRecord.getChatId());
		return "/chat";
	}
}
