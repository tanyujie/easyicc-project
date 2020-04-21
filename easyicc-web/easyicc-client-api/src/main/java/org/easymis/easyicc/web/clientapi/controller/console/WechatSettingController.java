package org.easymis.easyicc.web.clientapi.controller.console;

import org.easymis.easyicc.domain.entity.Site;
import org.easymis.easyicc.service.RewriteRuleService;
import org.easymis.easyicc.web.clientapi.controller.IdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/wechatSetting", description = "微信同步管理")
@Controller
@RequestMapping("/wechatSetting")
public class WechatSettingController  extends IdentityRepository{
	@Autowired
	private RewriteRuleService service;
	@ApiOperation(value = "微信同步管理页面")
	@RequestMapping(value = { "/index.html" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String find(String name,ModelMap model) {
		String orgId = getOrgId();
		Site bean = new Site();
		bean.setOrgId(orgId);

		//model.put("pageInfo", service.find(bean, pageNum, pageSize));
		return "/console/wechatSetting/index";
	}
}
