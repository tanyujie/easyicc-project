package org.easymis.easyicc.card.admin.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

public class SendMessage {
	private final static Log logger = LogFactory
			.getLog(SendMessage.class);
	
	private static final String TEMPLATE_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
	private static final String ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
	private static final String DEFAULT_CHARSET = "UTF-8";

	/***
	 * 发送后 返回 acceesstoken 错误，则刷新 token
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	private static String sendResultProcess(JSONObject obj) throws Exception {
		String result = "";
		if (obj != null && obj.get("errmsg") != null
				&& obj.get("errmsg").equals("ok")) {
			result = "发送成功!";
		} else {
			int errcode = obj.getInteger("errcode");
			result = "发送失败，错误码：" + errcode;
		}
		/*if (obj != null && obj.get("msg") != null
				&& obj.get("msg").equals("success")) {
			result = "发送成功!";
		} else {
			int errcode = obj.getInteger("errcode");
			result = "发送失败，错误码：" + errcode;
		}*/
		return result;
	}

	/**
	 * 发送模板消息
	 * 
	 * @param accessToken
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	private static JSONObject templateSend(TemplateData data)
			throws IOException, ExecutionException, InterruptedException {
		
		String result = post("http://api.euyun.com/invoke/weixin/101/102/pushTemplateMsg?token=fdfd34123",
				JSONObject.toJSONString(data));
		
//		String result = post(TEMPLATE_SEND_URL.concat(accessToken),
//				JSONObject.toJSONString(data));
		logger.info("invoke weixin server return="+result);
		
		if (StringUtils.isNotEmpty(result)) {
			return JSONObject.parseObject(result);
		}
		return null;
	}

	public static JSONObject queryUserOpenId(String code)
			throws IOException, ExecutionException, InterruptedException {
		
		String result = post("http://api.euyun.com/invoke/weixin/101/102/queryUserOpenId?token=fdfd34123&code=" + code,"");
		
//		String result = post(TEMPLATE_SEND_URL.concat(accessToken),
//				JSONObject.toJSONString(data));
		logger.info("invoke weixin server return="+result);
		
		if (StringUtils.isNotEmpty(result)) {
			return JSONObject.parseObject(result);
		}
		return null;
	}
	
	public static JSONObject queryUserInfo(String openId)
			throws IOException, ExecutionException, InterruptedException {
		
		String result = post("http://api.euyun.com/invoke/weixin/101/102/queryUserInfo?token=fdfd34123&openId=" + openId,"");
		
//		String result = post(TEMPLATE_SEND_URL.concat(accessToken),
//				JSONObject.toJSONString(data));
		logger.info("invoke weixin server return="+result);
		
		if (StringUtils.isNotEmpty(result)) {
			return JSONObject.parseObject(result);
		}
		return null;
	}
	public static String getWeixinAuthUrl(String url) 
	{
		
		String authUrl;
		try {
			authUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf5f153db1f8a580e&redirect_uri=" 
			+ URLEncoder.encode(url,"UTF-8") 
			+ "&response_type=code&scope=snsapi_base&state=auth#wechat_redirect";
			return authUrl;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return url;
	}
	
	private static String post(String url, String s) throws IOException,
			ExecutionException, InterruptedException {
		AsyncHttpClient http = new AsyncHttpClient();
		AsyncHttpClient.BoundRequestBuilder builder = http.preparePost(url);
		builder.setBodyEncoding(DEFAULT_CHARSET);
		builder.setBody(s);
		Future<Response> f = builder.execute();
		String body = f.get().getResponseBody(DEFAULT_CHARSET);
		http.close();
		return body;
	}
	
	/**
     * @return 返回类型:
     * @throws IOException
     * @throws UnsupportedEncodingException
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @description 功能描述: get 请求
     */
    public static String get(String url) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, IOException, ExecutionException, InterruptedException {
        return get(url, null);
    }
    
    /**
     * @return 返回类型:
     * @throws IOException
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws UnsupportedEncodingException
     * @description 功能描述: get 请求
     */
    public static String get(String url, Map<String, String> params) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, IOException, ExecutionException, InterruptedException {
        return get(url, params, null);
    }
    
    /**
     * @return 返回类型:
     * @throws IOException
     * @throws UnsupportedEncodingException
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @description 功能描述: get 请求
     */
    public static String get(String url, Map<String, String> params, Map<String, String> headers) throws IOException, ExecutionException, InterruptedException {
        AsyncHttpClient http = new AsyncHttpClient();
        AsyncHttpClient.BoundRequestBuilder builder = http.prepareGet(url);
        builder.setBodyEncoding(DEFAULT_CHARSET);
        if (params != null && !params.isEmpty()) {
            Set<String> keys = params.keySet();
            for (String key : keys) {
                builder.addQueryParameter(key, params.get(key));
            }
        }

        if (headers != null && !headers.isEmpty()) {
            Set<String> keys = headers.keySet();
            for (String key : keys) {
                builder.addHeader(key, params.get(key));
            }
        }
        Future<Response> f = builder.execute();
        String body = f.get().getResponseBody(DEFAULT_CHARSET);
        http.close();
        return body;
    }
	
	/**
     * 获取access_token
     *
     * @return
     * @throws Exception
     */
//    private static String getAccessToken() throws Exception {
//        String appid = "wxf5f153db1f8a580e";
//        String secret = "44675093c997a637c14b572f9f90f09d";
//        String jsonStr = get(ACCESSTOKEN_URL.concat("&appid=") + appid + "&secret=" + secret);
//        Map<String, Object> map = JSONObject.parseObject(jsonStr);
//        return map.get("access_token").toString();
//    }
    
    public static String sendMessage(String openid, String customerName, String phone, 
    		String cuourName, String overdueTime, String url) throws Exception {
    	String message = "";
    	String templateId = "-Cy7-JwoTMXS3sUkgjlly7R03HaptG6GbbZ28B0Ba3c";
//    	String accessToken = getAccessToken();
    	String first = "您好，您有新的分配客户啦，快去追踪吧！";
    	String keyword1 = customerName;
		String keyword2 = phone;
		String keyword3 = cuourName;
		String keyword4 = overdueTime;
		String remark = "请尽快处理，谢谢。";
    	
		TemplateData data = new TemplateData(openid, templateId);
		data.setUrl(url);
		data.getData().addItem("first", first);
		data.getData().addItem("keyword1", keyword1);
		data.getData().addItem("keyword2", keyword2);
		data.getData().addItem("keyword3", keyword3);
		data.getData().addItem("keyword4", keyword4);
		data.getData().addItem("remark", remark);
		JSONObject obj = templateSend(data);
		message = sendResultProcess(obj);
		
    	return message;
    }

	public static void main1(String[] args) throws Exception {
//		String accessToken = getAccessToken();
//		System.out.println(accessToken);
		String keyword1 = "最高法:失信被执行人将被限制乘坐高铁";
		String keyword2 = "兰博";
		String openid = "ook-Ns9PeT1oYbE9QbiANJjHA3PU";
		String templateId = "4skI_1aZxsWNH9jY-Z4xipO35Mn0SrZ1isHSeVWWl4s";
		String url = "http://www.baidu.com";
		String first = "您好，你有一篇预警文章";
		String remark = "感谢您的使用";

		TemplateData data = new TemplateData(openid, templateId);
		data.setUrl(url);
		data.getData().addItem("first", first);
		data.getData().addItem("keyword1", keyword1);
		data.getData().addItem("keyword2", keyword2);
		data.getData().addItem("keyword3",
				DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm"));
		data.getData().addItem("remark", remark);
		JSONObject obj = templateSend( data);
		String result = sendResultProcess(obj);
		System.out.println(result);
	}
	
	public static void maint(String[] args) throws Exception {

		String keyword1 = "最高法:失信被执行人将被限制乘坐高铁";
		String keyword2 = "兰博";
		String openid = "ook-Ns9PeT1oYbE9QbiANJjHA3PU-11";
		String templateId = "4skI_1aZxsWNH9jY-Z4xipO35Mn0SrZ1isHSeVWWl4s";
		String url = "http://www.baidu.com";
		String first = "您好，你有一篇预警文章";
		String remark = "感谢您的使用";

		TemplateData data = new TemplateData(openid, templateId);
		data.setUrl(url);
		data.getData().addItem("first", first);
		data.getData().addItem("keyword1", keyword1);
		data.getData().addItem("keyword2", keyword2);
		data.getData().addItem("keyword3",
				DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm"));
		data.getData().addItem("remark", remark);
		
		String body = JSONObject.toJSONString(data);
		
		System.out.println(body);
		
		String result = post("http://api.eutils.cn/invoke/weixin/101/102/pushTemplateMsg?token=fdfd34123",
				body);
		
		System.out.println(result);
	}
	
	
	public static void main(String[] args) throws Exception {


		JSONObject result = SendMessage.queryUserOpenId("");
		System.out.println(result.toString());
	}
}

class TemplateData {

	private String openId;
	private String templateId;
	private String url;
	private String topcolor;
	private TemplateDataItem data = new TemplateDataItem();

	public TemplateData(String openId, String templateId) {
		this.openId = openId;
		this.templateId = templateId;
	}

	public TemplateData(String openId, String templateId, String url) {
		this.openId = openId;
		this.templateId = templateId;
		this.url = url;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTopcolor() {
		return topcolor;
	}

	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}

	public TemplateDataItem getData() {
		return data;
	}

	public void setData(TemplateDataItem data) {
		this.data = data;
	}

	public TemplateData push(String key, String value, String color) {
		this.data.addItem(key, value, color);
		return this;
	}

	public TemplateData push(String key, String value) {
		this.data.addItem(key, value);
		return this;
	}

	public class TemplateDataItem extends HashMap<String, Item> {
		private static final long serialVersionUID = 1L;

		public Item getItemInstance(String value) {
			return new Item(value);
		}

		public Item getItemInstance(String value, String color) {
			return new Item(value, color);
		}

		public TemplateDataItem() {
		}

		public TemplateDataItem addItem(String key, String value) {
			this.put(key, new Item(value));
			return this;
		}

		public TemplateDataItem addItem(String key, String value, String color) {
			this.put(key, new Item(value, color));
			return this;
		}
	}

	public class Item {
		private String value;
		private String color = "#000000";

		public Item(String value) {
			this.value = value;
		}

		public Item(String value, String color) {
			this.value = value;
			this.color = color;
		}

		public void setColor(String color) {
			this.color = color;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public String getColor() {
			return color;
		}
	}
}
