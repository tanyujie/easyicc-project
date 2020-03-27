package org.easymis.easyicc.card.admin.controller.setting;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.easymis.easyicc.card.admin.common.CmdConstant;
import org.easymis.easyicc.card.admin.controller.IdentityRepository;
import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.Organize;
import org.easymis.easyicc.domain.entity.WechatInfo;
import org.easymis.easyicc.service.OrganizeService;
import org.easymis.easyicc.service.WechatInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;

import io.swagger.annotations.Api;

/**
 * 
 * 微信接口配置
 *
 */
@Api(value = "/weChatParty", description = "微信接口配置")
@Controller
@RequestMapping("/weChatParty")
public class WeixinPartyController extends IdentityRepository {

	private final static String PREFIX = "/setting/weixinParty";

	@Autowired
	private OrganizeService cService;

	@Autowired
	private WechatInfoService wxService;

	@RequestMapping("/indexWeixinParty")
	public String indexThirdParty(ModelMap model) throws Exception {
		return PREFIX + "/index";
	}

	/**
	 * 微信接口列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryPage")
	@ResponseBody
	public RestResult queryPage(HttpServletRequest request) {
		Page<WechatInfo> page = new Page<WechatInfo>();
		WechatInfo bean = new WechatInfo();
		bean.setOrgId(this.getOrgId());
		bean.setDeleteFlag(CmdConstant.IS_DELETE_NO);
		return RestResult.buildSuccess(wxService.findPage(bean, new Page()));

	}

	@RequestMapping(value = "/createWechatInfo", method = RequestMethod.GET)
	public String createWechatInfo(ModelMap model) {
		List<Organize> list = cService.findList();
		model.put("companys", list);
		return PREFIX + "/form";
	}

	/**
	 * 新增名片接口
	 * 
	 * @param ci
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/createWechatInfo", method = RequestMethod.POST)
	@Transactional
	public RestResult createWechatInfo(WechatInfo weixin, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		if (weixin != null) {
			String orgId = getOrgId();
			weixin.setOrgId(orgId);
			weixin.setDeleteFlag(CmdConstant.IS_DELETE_NO);
			// weixin.setUserName(OnLine.getCurrentUserDetails().getRealName());
			// weixin.setCompanyName(cService.getCompanyName(orgId));
			wxService.save(weixin);
		}
		return RestResult.buildSuccess();

	}

	/**
	 * 更新页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateWeixin", method = RequestMethod.GET)
	public String updateWeixin(HttpServletRequest request, ModelMap model) {
		String id = request.getParameter("id") == null ? "0" : request.getParameter("id");
		WechatInfo entity = wxService.findById(id);

		model.put("entity", entity);
		model.put("id", "" + id);

		return this.getPrefix() + "/editPage";
	}

	/**
	 * 更新
	 * 
	 * @param entity
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/updateWeixin", method = RequestMethod.POST)
	public RestResult updateWeixin(WechatInfo weixin, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String id = request.getSession().getAttribute("id") == null ? ""
				: (String) request.getSession().getAttribute("id");

		// String hql = "update WechatInfo set status=?,sendOpportunity=? where id=?";
		// this.getHibernateService().executeUpdate(hql, weixin.getStatus(),
		// weixin.getSendOpportunity(), Integer.parseInt(id));
		wxService.Update(weixin);
		return RestResult.buildSuccess();

	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteWeixin")
	@ResponseBody
	public RestResult deleteWeixin(HttpServletRequest request) {

		String id = request.getParameter("id") == null ? "0" : request.getParameter("id");
		wxService.delete(id);
		return RestResult.buildSuccess();

	}

	protected String getPrefix() {
		return "/setting/weixinParty";
	}

	protected Object getQueryParams(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isDelete", CmdConstant.IS_DELETE_NO);
		String orgId = getOrgId();
		params.put("orgId", orgId);
		return params;
	}
}
