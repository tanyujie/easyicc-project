package org.easymis.easyicc.card.admin.controller.setting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.easymis.easyicc.card.admin.controller.IdentityRepository;
import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.CardInterface;
import org.easymis.easyicc.domain.entity.CardItem;
import org.easymis.easyicc.service.CardInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

import io.swagger.annotations.Api;
@Api(value = "/cardInterface", description = "排班班次")
@Controller
@RequestMapping("/cardInterface")
public class CardInterfaceController extends IdentityRepository{

	@Autowired
	private CardInterfaceService service;

	private final static String PREFIX = "/setting/cardInterface";

	@RequestMapping(value = "/createCard", method = RequestMethod.GET)
	public String create(ModelMap model) {
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
	@RequestMapping(value = "/createCard", method = RequestMethod.POST)
	@Transactional
	public RestResult create(CardItem ci, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			if (ci != null && ci.getToken() != null) {
				CardInterface cif = new CardInterface();
				Date date = new Date();
				cif.setOrgId(getOrgId());
				cif.setToken(ci.getToken());
				cif.setCreateTime(date);
				cif.setUpdateTime(date);
				cif.setIsUseSubject(ci.getIsUseSubject());
				cif.setIsUseSchool(ci.getIsUseSchool());

				// cif.setIsDelete(0);
				JSONObject object = JSONTool.getCardInterface(ci);

				String param = object.toString();
				cif.setParam(param);

				this.service.save(cif);
			}
			return RestResult.buildSuccess();
		} catch (Exception e) {
			return RestResult.buildError();
		}
	}

	/**
	 * 名片接口列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/query/pageCard")
	@ResponseBody
	public Page<CardItem> pageQueryCard(HttpServletRequest request) {
		Page<CardItem> pageView = new Page<CardItem>();
		try {
			Object params = this.getQueryParams(request);
			if (params == null) {
				params = new HashMap<String, Object>();
			}
			Page<CardInterface> page = this.getHibernateService()
					.pageQueryByTemplate(PageConfig.createPageConfig(request),
							params);
			List<CardInterface> list = page.getRows();
			pageView.setTotal(page.getTotal());
			List<CardItem> pageList = new ArrayList<CardItem>();
			if (list != null && list.size() > 0) {
				CardItem item = null;
				for (CardInterface bean : list) {
					String param = bean.getParam();
					JSONObject jsonObject = JSONObject.fromObject(param);
					item = (CardItem) JSONObject.toBean(jsonObject,
							CardItem.class);
					item.setToken(bean.getToken());
					item.setId(bean.getId());
					item.setIsUseSchool(bean.getIsUseSchool());
					item.setIsUseSubject(bean.getIsUseSubject());
					item.setUrl(bean.getUrl());
					item.setOrgId(bean.getOrgId());
					pageList.add(item);
				}
			}
			pageView.setRows(pageList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pageView;
	}

	/**
	 * 更新页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateCard", method = RequestMethod.GET)
	public String updateCard(HttpServletRequest request, ModelMap model) {
		String id = request.getParameter("id") == null ? "0"
				: request.getParameter("id");
		CardInterface entity = service.findById(id);
		CardItem item = new CardItem();
		if (entity != null) {
			String param = entity.getParam();
			JSONObject jsonObject = JSONObject.fromObject(param);
			item = (CardItem) JSONObject.toBean(jsonObject, CardItem.class);
			item.setToken(entity.getToken());
			item.setId(id);
			item.setUrl(entity.getUrl());
			item.setIsUseSchool(entity.getIsUseSchool());
			item.setIsUseSubject(entity.getIsUseSubject());
			item.setOrgId(entity.getOrgId());
		}

		model.put("entity", item);
		model.put("id", "" + id);
		this.updatePageInit(entity, request, model);
		return this.getPrefix() + "/" + this.getEditPage();
	}

	/**
	 * 更新
	 * 
	 * @param entity
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/updateCard", method = RequestMethod.POST)
	public RestResult updateCard(CardItem entity, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			// int id = Integer.parseInt(request.getParameter("id") == null ?
			// "0" : request.getParameter("id"));
			String id = request.getSession().getAttribute("id") == null ? ""
					: (String) request.getSession().getAttribute("id");
			CardInterface cif = new CardInterface();
			Date date = new Date();
			// cif.setCompanyId(entity.getCompanyId());
			cif.setToken(entity.getToken());
			cif.setUpdateTime(date);
			cif.setIsUseSubject(entity.getIsUseSubject());
			cif.setIsUseSchool(entity.getIsUseSchool());

			JSONObject object = JSONTool.getCardInterface(entity);

			String param = object.toString();
			cif.setParam(param);
			String url = "http://api.jswebcall.easyliao.com/data/api/invoke?companyId="
					+ entity.getOrgId() + "&interfaceId=" + id + "&token="
					+ entity.getToken() + "&cmd=insert";
			String hql = "update CardInterface set token=?,updateTime=?,url=?,param=?,isUseSubject=?,isUseSchool=? where id=?";
			this.getHibernateService().executeUpdate(hql, entity.getToken(),
					date, url, param, entity.getIsUseSubject(),
					entity.getIsUseSchool(), Integer.parseInt(id));
			request.getSession().removeAttribute("id");
			return RestResult.buildSuccess();
		} catch (Exception e) {
			return RestResult.buildError();
		}
	}

	/**
	 * 预览页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/preview", method = RequestMethod.GET)
	public String preview(HttpServletRequest request, ModelMap model) {
		String id = request.getParameter("id") == null ? "0" : request.getParameter("id");
		try {
			CardInterface entity = service.findById(id);
			model.put("entity", entity);
			if (entity != null && entity.getParam() != null) {
				String param = entity.getParam();
				JSONObject jsonObject = JSONObject.fromObject(param);
				CardItem item = (CardItem) JSONObject.toBean(jsonObject,
						CardItem.class);
				JSONObject object = JSONTool.getCardInterfaceForPreview(item);
				
				String jsonData = object.toString();
				model.put("param", jsonData);
				model.put("token", entity.getToken());
				model.put("interfaceId", "" + id);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return this.getPrefix() + "/preview";
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteCard")
	@ResponseBody
	public RestResult deleteCard(HttpServletRequest request) {
		try {
			int id = Integer.parseInt(request.getParameter("id") == null ? "0"
					: request.getParameter("id"));
			String hql = "update CardInterface set isDelete=1 where id=?";
			this.getHibernateService().executeUpdate(hql, id);
			return RestResult.buildSuccess();
		} catch (Exception e) {
			return RestResult.buildError(e.toString());
		}
	}

	/**
	 * 测试
	 * 
	 * @param testUrl
	 * @return
	 */
	@RequestMapping("/testCard")
	@ResponseBody
	public String testCard(HttpServletRequest request) {
		JSONObject res = null;
		try {
			String testUrl = request.getParameter("testUrl") == null ? "" : request.getParameter("testUrl");
			String interfaceId = request.getParameter("interfaceId") == null ? "" : request.getParameter("interfaceId");
			String token = request.getParameter("token") == null ? "" : request.getParameter("token");
			String jsonData = request.getParameter("jsonData") == null ? "" : request.getParameter("jsonData");
			
			if (testUrl != null && !"".equals(testUrl)) {
				JSONArray jsonArray = new JSONArray();
				JSONObject jsonObject = JSONObject.fromObject(jsonData);
				jsonArray.add(jsonObject);
				
//				testUrl = testUrl.replace("api.jswebcall.com",
//						"115.28.170.190:9080");
				testUrl += "&interfaceId=" + interfaceId;
				testUrl += "&token=" + token;
				testUrl += "&cmd=test";
				
				res = httpPost(testUrl, jsonData);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res.toString();
	}
	
	private JSONObject httpPost(String testUrl, String json) {
		JSONObject jsonObject = null;
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(testUrl);
		
			StringEntity s = new StringEntity(json,"UTF-8");
			s.setContentEncoding("UTF-8");
			
			s.setContentType("text/html;charset=UTF-8");
			post.setEntity(s);
			HttpResponse rsp;
			try {
				rsp = client.execute(post);
				if(rsp.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
					HttpEntity entity = rsp.getEntity();
					String res = EntityUtils.toString(entity);
					jsonObject = JSONObject.fromObject(res);
				} else {
					jsonObject = JSONObject.fromObject("");
				}
				logger.info("消息推送模板连接接口成功：返回结果："+rsp.getStatusLine().getStatusCode());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonObject;
	}



	protected String getPrefix() {
		return "/setting/cardInterface";
	}


	protected Object getQueryParams(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyId", getOrgId());
		params.put("isDelete", 0);
		return params;
	}


	protected HibernateDAO<Integer, CardInterface> getHibernateService() {
		return service;
	}
}
