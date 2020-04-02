package org.easymis.easyicc.web.chat.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MsgController {

	@RequestMapping("/msg.do")
	public String index(String cmd, String v, String u, String userId, String c, String ext, String keys[],
			String values[], String promotionId, String tag, String ref, Integer ocpcPlatform, Integer ocpcCondition,
			String ocpcConfigId, String g, String chatType, String sid) throws NoSuchAlgorithmException {
		if (cmd.equals("getMessage"))
			return getMessage("orgId");
		else if (cmd.equals("addEvent"))
			return addEvent("orgId");
		else if (cmd.equals("chat"))
			return chat( v,  u,  userId,  c,  ext,  keys,
					 values,  promotionId,  tag,  ref,  ocpcPlatform,  ocpcCondition,
					 ocpcConfigId,  g,  chatType,  sid);

		return "/customerService";
	}

	private String getMessage(String orgId) throws NoSuchAlgorithmException {

		return "/msg/getMessage";
	}

	private String addEvent(String orgId) throws NoSuchAlgorithmException {

		return "/msg/addEvent";
	}

	private String chat(String v, String u, String userId, String c, String ext, String keys[],
			String values[], String promotionId, String tag, String ref, Integer ocpcPlatform, Integer ocpcCondition,
			String ocpcConfigId, String g, String chatType, String sid) throws NoSuchAlgorithmException {

		return "/msg/chat";
	}
}
