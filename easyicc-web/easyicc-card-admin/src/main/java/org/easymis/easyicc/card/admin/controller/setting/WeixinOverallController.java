package org.easymis.easyicc.card.admin.controller.setting;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymis.easyicc.card.admin.controller.IdentityRepository;
import org.easymis.easyicc.domain.entity.OrganizeConfig;
import org.easymis.easyicc.service.OrganizeConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jesong.webcall.core.client.CoreClient;
import io.swagger.annotations.Api;

/**
 * 
 * 全局微信推送
 *
 */
@Api(value = "/weChatOverall", description = "全局微信推送")
@Controller
@RequestMapping("/weChatOverall")
public class WeixinOverallController extends IdentityRepository{
	
	private final static Log logger = LogFactory.getLog(WeixinOverallController.class);
	
	private final static String PREFIX = "/setting/weixinOverall";
	
	@RequestMapping("/index")
	public String indexThirdParty(ModelMap model) throws Exception {
		String orgId = getOrgId();
		OrganizeConfig config = CoreClient.getUserMgr(orgId).getCompanyGlobalConfig(orgId);
		if(config != null) {
			String notifyOpenId = config.getProps2().getProperty("notifyOpenId");
			String notifyUserNickName = config.getProps2().getProperty("notifyUserNickName");
			String notifyIsOpen = config.getProps2().getProperty("notifyIsOpen");
			String notifySendOpportunity = config.getProps2().getProperty("notifySendOpportunity");
			
			if(notifyOpenId != null && !"".equals(notifyOpenId)) {
				model.put("notifyOpenId", notifyOpenId);
			}
			
			if(notifyUserNickName != null && !"".equals(notifyUserNickName)) {
				model.put("notifyUserNickName", notifyUserNickName);
			}
			
			if(notifyIsOpen != null && !"".equals(notifyIsOpen)) {
				model.put("notifyIsOpen", notifyIsOpen);
			}
			
			if(notifySendOpportunity != null && !"".equals(notifySendOpportunity)) {
				model.put("notifySendOpportunity", notifySendOpportunity);
			}
		}
		return PREFIX + "/index";
	}
	
	/**
	 * 新增
	 * 
	 * @param ci
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/submitConfig", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> submitConfig(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			String orgId = getOrgId();
			String notifyOpenId = request.getParameter("notifyOpenId");
			String notifyUserNickName = request.getParameter("notifyUserNickName");
			String notifyIsOpen = request.getParameter("notifyIsOpen");
			String notifySendOpportunity = request.getParameter("notifySendOpportunity");
			
			OrganizeConfig config = CoreClient.getUserMgr(orgId).getCompanyGlobalConfig(orgId);
			config.getProps2().setProperty("notifyIsOpen", notifyIsOpen);
			config.getProps2().setProperty("notifyOpenId", notifyOpenId);
			config.getProps2().setProperty("notifyUserNickName", notifyUserNickName);
			config.getProps2().setProperty("notifySendOpportunity", notifySendOpportunity);
			
			CoreClient.getUserMgr(orgId).updateCompanyGlobalConfig(config);
			
			map.put("result", "success");
		} catch (Exception e) {
			map.put("result", "error");
			logger.error(e.getMessage(),e);
		}
		
		return map;
	}

}
