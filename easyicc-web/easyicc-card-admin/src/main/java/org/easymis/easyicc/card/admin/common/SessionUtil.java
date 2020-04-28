package org.easymis.easyicc.card.admin.common;

import javax.servlet.http.HttpServletRequest;

import org.easymis.easyicc.domain.entity.WechatInfo;

public class SessionUtil {
	
	private static String SESSION_OPENID_KEY = "sessionOpenId";
	private static String SESSION_NICKNAME_KEY = "sessionNickname";
	private static String SESSION_HEADIMGURL_KEY = "sessionHeadImgUrl";
	
	private static String SESSION_USER_KEY = "sessionWeixinInfo";

	public static void setOpenId(HttpServletRequest request, String token, String key) {
		request.getSession().getServletContext().setAttribute(SESSION_OPENID_KEY + key, token);
	}
	
	public static String getOpenId(HttpServletRequest request, String key) {
		if (request.getSession().getServletContext().getAttribute(SESSION_OPENID_KEY + key) != null) {
			return (String) request.getSession().getServletContext().getAttribute(SESSION_OPENID_KEY + key);
		} else {
			return null;
		}
	}
	
	public static void setNickname(HttpServletRequest request, String token, String key) {
		request.getSession().getServletContext().setAttribute(SESSION_NICKNAME_KEY+ key, token);
	}
	
	public static String getNickname(HttpServletRequest request, String key) {
		if (request.getSession().getServletContext().getAttribute(SESSION_NICKNAME_KEY+ key) != null) {
			return (String) request.getSession().getServletContext().getAttribute(SESSION_NICKNAME_KEY+ key);
		} else {
			return null;
		}
	}
	
	
	public static void setHeadImgUrl(HttpServletRequest request, String token, String key) {
		request.getSession().getServletContext().setAttribute(SESSION_HEADIMGURL_KEY+ key, token);
	}
	
	public static String getHeadImgUrl(HttpServletRequest request, String key) {
		if (request.getSession().getServletContext().getAttribute(SESSION_HEADIMGURL_KEY+ key) != null) {
			return (String) request.getSession().getServletContext().getAttribute(SESSION_HEADIMGURL_KEY+ key);
		} else {
			return null;
		}
	}
	
	
	public static void setWeixinInfo(HttpServletRequest request, WechatInfo wx) {
		request.getSession().setAttribute(SESSION_USER_KEY, wx);
	}
	
	
}
