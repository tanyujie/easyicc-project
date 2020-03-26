package org.easymis.easyicc.card.admin.controller.setting;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymis.easyicc.card.admin.controller.IdentityRepository;
import org.easymis.easyicc.domain.entity.Card;
import org.easymis.easyicc.domain.entity.ChatRecordDetail;
import org.easymis.easyicc.domain.entity.VisitorColSelfSon;
import org.easymis.easyicc.service.AllocationCardService;
import org.easymis.easyicc.service.CardConfigService;
import org.easymis.easyicc.service.CardDockingService;
import org.easymis.easyicc.service.ChatRecordService;
import org.easymis.easyicc.service.ThirdApiInvokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.eutils.web.platform.ui.PageConfig;
import cn.jesong.webcall.core.client.CoreClient;
import cn.jesong.webcall.cuour.common.CmdConstant;
import cn.jesong.webcall.cuour.util.SendMessage;
import io.swagger.annotations.Api;
import sun.security.util.KeyUtil;
@Api(value = "/cardDocking", description = "排班班次")
@Controller
@RequestMapping("/cardDocking")
public class CardDockingController extends IdentityRepository{
	
	private final static Log _logger = LogFactory.getLog(CardDockingController.class);
	
	private final static String PREFIX = "/setting/allocation";
	
	@Autowired
	private CardDockingService service;
	
	@Autowired
	private ThirdApiInvokeService pdService;
	
	@Autowired
	private CardConfigService cardConfigService;
	
	@Autowired
	private AllocationCardService allocationCardService;
	
	@Autowired
	private ChatRecordService chatRecordService;
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 更新名片状态信息
	 * 
	 * @param testUrl
	 * @return
	 */
	@RequestMapping("/updateCardStatus")
	@ResponseBody
	public void updateCardStatus(HttpServletRequest request, HttpServletResponse response) {
		String message = "";
		JSONObject json = new JSONObject();
		try {
			// 公司id
			String cId = request.getParameter("companyId") == null ? "" : request.getParameter("companyId");
			// 静态id
			String staticId = request.getParameter("staticId") == null ? "" : request.getParameter("staticId");
			// 退回：1：设置无效，2：重新分配
			String status = request.getParameter("status") == null ? "" : request.getParameter("status");
			String token = request.getParameter("token") == null ? "" : request.getParameter("token");
			String mobile = request.getParameter("mobile") == null ? "" : request.getParameter("mobile");
			
			
			_logger.info("updateCardStatusApi{companyId=" + cId + ",staticId=" + staticId + ",status=" + status + "}");
			// 判断参数是否有效
			if (mobile == null || "".equals(mobile)) {
//				json.put("error", -4);
//				message = "手机号码为空";
			}
			if (token == null || "".equals(token)) {
				json.put("error", -3);
				message = "token为空";
			} else if(!"1234567".equals(token)) {
				json.put("error", -3);
				message = "token错误";
			}
			if (cId == null || "".equals(cId)) {
				json.put("error", -2);
				message = "companyId为空";
			}
			if (staticId == null || "".equals(staticId)) {
				json.put("error", -1);
				message = "staticId为空";
			}
			if (status == null || "".equals(status)) {
				json.put("error", -5);
				message = "status为空";
			} else if(!"1".equals(status) && !"3".equals(status) 
					&& !"4".equals(status) && !"5".equals(status) && !"7".equals(status)) {
				json.put("error", -5);
				message = "status参数错误";
			}
			
			if(json.get("error") == null) {
				String companyId = cId;
				// 参数合法，修改名片状态
				if(status.equals("1")) {
					// 设置无效
					//int count = service.setNotValidate(companyId, staticId);
					Card card = allocationCardService.getCard(companyId, staticId);
					
					if(card!=null)
					{
						allocationCardService.setNotValidate(companyId, card.getId());
						
						message = "设置无效成功";
						json.put("success", 1);
					} else {
						message = "没有查找到对应数据";
						json.put("code", 0);
					}
					
				} else if(status.equals("2")) {
					// 重新分配
//					int count = service.resetAllocation(Integer.parseInt(companyId), staticId);
//					if(count > 0) {
//						message = "设置重新分配成功";
//						json.put("success", 1);
//					} else {
//						message = "没有查找到对应数据";
//						json.put("code", 0);
//					}
					
				} else if(status.equals("3")) {
					// 退回
//					int count = service.returnAllocation(, staticId);
					Card card = allocationCardService.getCard(companyId, staticId);
					
					if(card!=null)
					{
						Integer backType = 0;
						try
						{
							backType = request.getParameter("backType") == null ? 0:Integer.parseInt(request.getParameter("backType"));
						}
						catch(Exception e)
						{
							
						}
						String backUserId = request.getParameter("backUserId")==null?card.getUserId():request.getParameter("backUserId");
						
						_logger.info("backVCard{companyId=" + companyId + ",cardId="+card.getId()+",userId="+backUserId);
						allocationCardService.back(companyId, card.getId(), 
								backUserId, backType,request.getParameter("backDesc") == null ?"人工退回": request.getParameter("backDesc"));
					
						message = "设置退回成功";
						json.put("success", 1);
						
					} else {
						message = "没有查找到对应数据";
						json.put("code", 0);
					}
					
				} else if(status.equals("4")) {
					// 超期回收
					int count = service.expiredAllocation(companyId, staticId);
					if(count > 0) {
						message = "设置超期回收成功";
						json.put("success", 1);
					} else {
						message = "没有查找到对应数据";
						json.put("code", 0);
					}
					
				} else if(status.equals("5")) {
					// 等待分配
					int count = service.waitAllocation(companyId, staticId);
					if(count > 0) {
						message = "设置等待分配成功";
						json.put("success", 1);
					} else {
						message = "没有查找到对应数据";
						json.put("code", 0);
					}
					
				} else if(status.equals("6")) {
					// 已处理
//					int count = service.finishedAllocation(Integer.parseInt(companyId), staticId);
//					if(count > 0) {
//						message = "设置已处理成功";
//						json.put("success", 1);
//					} else {
//						message = "没有查找到对应数据";
//						json.put("code", 0);
//					}
					
				} else if(status.equals("7")) {
					// 重复线索
					int count = service.repeatAllocation(companyId, staticId);
					if(count > 0) {
						message = "设置重复线索成功";
						json.put("success", 1);
					} else {
						message = "没有查找到对应数据";
						json.put("code", 0);
					}
					
				}
			}
			
			json.put("message", message);
			response.setContentType("text/html;charset=UTF-8");
			if(json.toString().length() > 2){
				String rtntext = json.toString();
				response.getWriter().write(rtntext);
			}
		} catch (Exception e) {
			message = "服务器异常";
			json.put("error", -9);
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新名片状态信息-批量
	 * 
	 * @param testUrl
	 * @return
	 */
	@RequestMapping("/updateCardStatusBatch")
	@ResponseBody
	public void updateCardStatusBatch(HttpServletRequest request, HttpServletResponse response) {
		String message = "";
		JSONObject json = new JSONObject();
		try {
			// 公司id
			String companyId = request.getParameter("companyId") == null ? "" : request.getParameter("companyId");
			// 静态id
			String staticId = request.getParameter("staticId") == null ? "" : request.getParameter("staticId");
			// 退回：1：设置无效，2：重新分配
			String status = request.getParameter("status") == null ? "" : request.getParameter("status");
			String token = request.getParameter("token") == null ? "" : request.getParameter("token");
			String mobile = request.getParameter("mobile") == null ? "" : request.getParameter("mobile");
			String mKey = KeyUtil.getKey(token);
			String localKey = KeyUtil.getKey(CmdConstant.LOCAL_TOKEN_ID);
			// 判断参数是否有效
			if (mobile == null || "".equals(mobile)) {
//				json.put("error", -4);
//				message = "手机号码为空";
			}
			if (token == null || "".equals(token)) {
				json.put("error", -3);
				message = "token为空";
			} else if(!mKey.equals(localKey)) {
				json.put("error", -3);
				message = "token错误";
			}
			if (companyId == null || "".equals(companyId)) {
				json.put("error", -2);
				message = "companyId为空";
			}
			if (staticId == null || "".equals(staticId)) {
				json.put("error", -1);
				message = "staticId为空";
			}
			if (status == null || "".equals(status)) {
				json.put("error", -5);
				message = "status为空";
			} else if(!"1".equals(status) && !"3".equals(status) 
					&& !"4".equals(status) && !"5".equals(status) && !"7".equals(status)) {
				json.put("error", -5);
				message = "status参数错误";
			}
			
			if(json.get("error") == null) {
				String[] strArr = staticId.split(",");
				List<String> staticIds = new ArrayList<String>();
				if(strArr != null && strArr.length > 0) {
					staticIds = Arrays.asList(strArr);
				}
				
				if(staticIds != null && staticIds.size() > 0) {
					// 参数合法，修改名片状态
					if(status.equals("1")) {
						// 设置无效
						int count = service.setNotValidateBatch(companyId, staticIds);
						if(count > 0) {
							message = "设置无效成功" + count + "条数据!";
							json.put("success", 1);
						} else {
							message = "没有查找到对应数据";
							json.put("code", 0);
						}
						
					} else if(status.equals("2")) {
						// 重新分配
//						int count = service.resetAllocationBatch(Integer.parseInt(companyId), staticIds);
//						if(count > 0) {
//							message = "设置重新分配成功" + count + "条数据!";
//							json.put("success", 1);
//						} else {
//							message = "没有查找到对应数据";
//							json.put("code", 0);
//						}
						
					} else if(status.equals("3")) {
						// 退回
						
						Integer backType = 0;
						try
						{
							backType = request.getParameter("backType") == null ? 0:Integer.parseInt(request.getParameter("backType"));
						}
						catch(Exception e)
						{
							
						}
						
						int count = 0;
						for(int i=0;i<staticIds.size();i++)
						{
							
							Card card = allocationCardService.getCard(companyId, staticIds.get(i));
							
							String backUserId = request.getParameter("backUserId")==null?card.getUserId():request.getParameter("backUserId");
							
							_logger.info("backVCard{companyId=" + companyId + ",cardId="+card.getId()+",userId="+backUserId);
							
							boolean ret= true;
							try
							{
							  ret = allocationCardService.back(companyId, card.getId(), 
										backUserId, backType, request.getParameter("backDesc") == null ?"人工退回": request.getParameter("backDesc"));
							}
							catch(Exception e)
							{
								_logger.info("该名片已经被退回过一次， 不能再退回!  或者     该名片为高意向名片， 不能退回!");
								
							}
							
						
							if(ret)
								count++;
						}
						
						//service.returnAllocationBatch(Integer.parseInt(companyId), staticIds);
						if(count > 0) {
							message = "设置退回成功" + count + "条数据!";
							json.put("success", 1);
						} else {
							message = "没有查找到对应数据";
							json.put("code", 0);
						}
						
					} else if(status.equals("4")) {
						// 超期回收
						int count = service.expiredAllocationBatch(companyId, staticIds);
						if(count > 0) {
							message = "设置超期回收成功" + count + "条数据!";
							json.put("success", 1);
						} else {
							message = "没有查找到对应数据";
							json.put("code", 0);
						}
						
					} else if(status.equals("5")) {
						// 等待分配
						int count = service.waitAllocationBatch(companyId, staticIds);
						if(count > 0) {
							message = "设置等待分配成功" + count + "条数据!";
							json.put("success", 1);
						} else {
							message = "没有查找到对应数据";
							json.put("code", 0);
						}
						
					} else if(status.equals("6")) {
						// 已处理
//						int count = service.finishedAllocationBatch(Integer.parseInt(companyId), staticIds);
//						if(count > 0) {
//							message = "设置已处理成功";
//							json.put("success", 1);
//						} else {
//							message = "没有查找到对应数据";
//							json.put("code", 0);
//						}
						
					} else if(status.equals("7")) {
						// 重复线索
						int count = service.repeatAllocationBatch(companyId, staticIds);
						if(count > 0) {
							message = "设置重复线索成功" + count + "条数据!";
							json.put("success", 1);
						} else {
							message = "没有查找到对应数据";
							json.put("code", 0);
						}
						
					}
				} else {
					message = "没有查找到对应数据";
					json.put("code", 0);
				}
				
			}
			
			json.put("message", message);
			response.setContentType("text/html;charset=UTF-8");
			if(json.toString().length() > 2){
				String rtntext = json.toString();
				response.getWriter().write(rtntext);
			}
		} catch (Exception e) {
			message = "服务器异常";
			json.put("error", -9);
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/card")
	public String weixin(ModelMap model, HttpServletRequest request) throws Exception {
		try {
			String companyId = (request.getParameter("companyId") == null ? "0" : request.getParameter("companyId"));
			int cardId = Integer.parseInt(request.getParameter("cardId") == null ? "0" : request.getParameter("cardId"));
			
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("cardId", cardId);
			List<VisitorColSelfSon> list = cardConfigService.getShowVisitorCols(companyId);
			PageInfo<Card> page = this.cardConfigService.pageVisitorCardByCardId(PageConfig.createPageConfig(request), params,companyId, cardId);
			List<Card> c = page.getList();
			Card bean = c.get(0);
			

			
	
			
			request.setAttribute("cuourCard", bean);
			request.setAttribute("colSelfList", list);
			
			
			List<ChatRecordDetail> recordDetail;
			
			if(bean!=null&&bean.getChatId()!=null)
			{
				recordDetail = chatRecordService.getChatRecordDetail(companyId, bean.getChatId(), bean.getCreateTime());
			}
			else
			{
				recordDetail = chatRecordService.getChatRecordDetail(companyId, bean.getVisitorStaticId(), format.format(bean.getCreateTime()));
			}
			
			request.setAttribute("recordDetail", recordDetail);
			

			
		} catch(Exception e) {
			_logger.error(e.getMessage(),e);
		}
		return PREFIX + "/card";
	}
	
	/*
	 * 
	 * 后期统一升级，在调用该接口
	 * 
	 * 
	 * */
	
	@RequestMapping("/cardAuth")
	public String weixinAuth(ModelMap model, HttpServletRequest request) throws Exception {
		try {
			String companyId = request.getParameter("companyId") == null ? "0" : request.getParameter("companyId");
			int cardId = Integer.parseInt(request.getParameter("cardId") == null ? "0" : request.getParameter("cardId"));
			
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("cardId", cardId);
			List<VisitorColSelfSon> list = cardConfigService.getShowVisitorCols(companyId);
			PageInfo<Card> page = this.cardConfigService.pageVisitorCardByCardId(PageConfig.createPageConfig(request), params,companyId, cardId);
			List<Card> c = page.getList();
			Card bean = c.get(0);
			
			//获取userId
			String userId=bean.getUserId();
			_logger.info("获取的userId的值为:" + userId);
			if(userId != null){
				User user =  CoreClient.getUserMgr(companyId).getUser(userId);
				if(user!=null){
					//调用微信的接口获取code
					String code=request.getParameter("code") == null ? "" : request.getParameter("code");
					_logger.info("获取的code的值为:" + code);
					//根据code获取opendid；
					com.alibaba.fastjson.JSONObject jsonObject =SendMessage.queryUserOpenId(code);
					String openIdCode = jsonObject.getString("openid");
					_logger.info("返回的openId:" + openIdCode);
					 String opendIdUser=user.getExtendConfigProperty("notifyOpenId");
					_logger.info("绑定的openId:" + opendIdUser);
					 if(opendIdUser!=null && opendIdUser!=""){
						// if(jsonObject.getString("errcode") == null){//做一下code的处理
							 if(!opendIdUser.equals(openIdCode)){
								    request.setAttribute("departmentid", "1");
								    _logger.info("userOpenId != currentWeinxinOpenId" + userId);
								    return PREFIX + "/error";//当前用户和微信账号绑定不一致，请重新绑定微信账号
								}
						//	}
					 }else {
						 request.setAttribute("departmentid", "2");
						 _logger.info("unbind weixin=" + userId);
						 return PREFIX + "/error"; //当前用户未绑定微信账号，请先绑定微信账号
					 }
					
				}
			}else {
				request.setAttribute("departmentid", "3");
				_logger.info("user is null " + userId);
				return PREFIX + "/error";//当前用户无权查看该名片
			}
			
			_logger.info("添加名片信息: "+bean.getName());
			
			request.setAttribute("Card", bean);
			request.setAttribute("colSelfList", list);
			
			List<ChatRecordDetail> recordDetail;
			
			_logger.info("添加对话记录chatId: "+bean.getChatId() +"静态Id： "+ bean.getVisitorStaticId());
			
			if(bean!=null&&bean.getChatId()!=null)
			{
				recordDetail = chatRecordService.getChatRecordDetail(companyId, bean.getChatId(), format.format(bean.getCreateTime()));
			}
			else
			{
				recordDetail = chatRecordService.getChatRecordDetail(companyId, bean.getVisitorStaticId(), format.format(bean.getCreateTime()));
			}
			
			request.setAttribute("recordDetail", recordDetail);
			
			_logger.info("测试是否添加数据完成!!!");
			
		} catch(Exception e) {
			_logger.error(e.getMessage(),e);
		}
		_logger.info("数据添加完成!!!!");
		return PREFIX + "/card";
	}
	
	
}
