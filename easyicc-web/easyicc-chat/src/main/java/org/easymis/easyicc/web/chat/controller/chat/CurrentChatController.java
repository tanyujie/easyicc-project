package org.easymis.easyicc.web.chat.controller.chat;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.ChatRecord;
import org.easymis.easyicc.service.ChatRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 
　 * <p>Title: 桌面端当前对话</p>
　 * <p>Description: </p>
　 * @author 谭宇杰
　 * @date 2020年6月2日
 */
@Api(value = "/currentChat", description = "桌面端当前对话")
@Controller
@RequestMapping("/currentChat")
public class CurrentChatController {
	@Autowired
	private ChatRecordService chatRecordService;

	@ApiOperation(value = "当前对话")
	@RequestMapping("/index.json")
	@ResponseBody
	public RestResult currentChat(String chatId, Model model) throws NoSuchAlgorithmException {
		ChatRecord chatRecord = chatRecordService.findById(chatId);
		HashMap map = new HashMap();
		map.put("searchWord", chatRecord.getSearchWord());
		map.put("keywordWord", chatRecord.getKeyword());
		// 首次访问
		map.put("firstActiveUrl", chatRecord.getFirstActiveUrl());
		// 流量渠道
		map.put("promotionName", "");
		// 当前浏览网页
		map.put("promotionName", chatRecord.getRefer());
		// 本次来访时间
		map.put("refer", chatRecord.getRefer());
		// 当前网页标题
		map.put("title", chatRecord.getRefer());
		// 本次停留时长
		map.put("span", chatRecord.getSpan());
		// 访问静态ID
		map.put("chatId", chatRecord.getChatId());
		// 地区
		map.put("iplocation", chatRecord.getChatId());
		// 来访总次数
		map.put("viewPageCount", chatRecord.getViewPageCount());
		// IPV4
		map.put("ipv4", chatRecord.getVisitorIp());

		// 访客类型
		map.put("chatType", chatRecord.getChatType());

		// 浏览器类型
		map.put("browser", chatRecord.getBrowser());

		// 对话明细
		return RestResult.buildSuccess(map);
	}
}
