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
	@ResponseBody
	public RestResult currentChat(String chatId,Model model) throws NoSuchAlgorithmException {
		HashMap map= new HashMap();
		map.put("searchWord", "");
		map.put("keywordWord", "");
		//当前浏览网页
		map.put("firstActiveUrl", "http://www.easymis.cn");
		
		return RestResult.buildSuccess(map);
	}
}
