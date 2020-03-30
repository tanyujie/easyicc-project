package org.easymis.easyicc.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymis.easyicc.service.NotifyService;
import org.springframework.stereotype.Service;
@Service  
public class NotifyServiceImpl implements NotifyService {
	private final static Log _logger = LogFactory.getLog(NotifyServiceImpl.class);
	private Map<String, Date> lastLoadTimes = new HashMap<String, Date>();
	private Map<String, Map<String, NotifyUser>> cache = new HashMap<String, Map<String, NotifyUser>>();
	public void notify(String companyId, String userId, String message, String url, String category, boolean closeable){
		try {
			Map<String, Object> exts = new HashMap<String, Object>();
			/*String loginURL = ConfigLoader.getInstance().load().getProperty("cn.jesong.webcall.cuour.loginURL");
			UserSession u = CoreClient.getUserMgr(companyId).getCustomerInfo(companyId, userId);
			if(u != null){
				exts.put("url", loginURL+"?userName="+userId+"&check="+URLEncoder.encode(u.getCheckStr(), "utf-8")+"&rurl="+URLEncoder.encode(url, "utf-8"));
				exts.put("title", "名片分配");
				exts.put("category", category);
				exts.put("closeable", closeable);
				CoreClient.getChatMgr(companyId).sendChatMessageTo(companyId, "system", userId, message, "EVENT_POP_MESSAGE", exts);
			}*/
		} catch (Exception e) {
			_logger.error(e.getMessage(), e);
		}
	}
	@Override
	public void clearNotifyTime(String orgId, String staffId) {
		NotifyUser user = this.getNotifyUser(orgId, staffId);
		if(user != null){
			user.clearNotifyTime();
		}
		
	}
	@Override
	public void notifyWaitAllocation(String orgId, String message) {
		Collection<NotifyUser> users = this.getNotifyUsers(orgId);
		Date now = new Date();
		for(NotifyUser user : users){
			if(user.getLastNotifyTime() == null || now.getTime() - user.getLastNotifyTime().getTime() > 5*60*1000){
				this.notify(orgId, user.getUserId(), message, "/setting/allocation/index", "cn.jesong.webcall.cuour.card.allocation", true);
			}
		}
	}
	private void init(String orgId){
		Date date = this.lastLoadTimes.get(orgId);
		Date now = new Date();
		if(date == null || now.getTime() - date.getTime() > 120*1000){
			this.lastLoadTimes.put(orgId, now);
			Map<String, NotifyUser> notifyUsers = this.cache.get(orgId);
			if(notifyUsers == null){
				notifyUsers = new HashMap<String, NotifyUser>();
				this.cache.put(orgId, notifyUsers);
			}
			List<String> userIds = this.getUserId(orgId);
			Set<String> notExists = new HashSet<String>();
			notExists.addAll(notifyUsers.keySet());
			for(String userId : userIds){
				if(notExists.contains(userId)){
					notExists.remove(userId);
				}else{
					NotifyUser user = new NotifyUser(userId);
					notifyUsers.put(userId, user);
				}
			}
			for(String userId : notExists){
				notifyUsers.remove(userId);
			}
		}
	}
	private Collection<NotifyUser> getNotifyUsers(String companyId){
		synchronized(this){
			this.init(companyId);
			return this.cache.get(companyId).values();
		}
	}
	private List<String> getUserId(String companyId){
		String sql = "select a.user_id from js_user a "+
					"	where a.company_id = ? and exists( "+
					"	      select 1 from js_user_role b "+
					"	      inner join js_sys_role_menu c on b.role_id = c.role_id "+
					"	      where a.user_id = b.user_id and (b.role_id = 'ADMIN_ROLE' or c.menu_id = 'cuour.setting.allocation.index') "+
					"	)";
		//return this.jdbcTemplate.query(sql, new Object[]{companyId}, new RowMapper<String>(){
		//	@Override
		//	public String mapRow(ResultSet rs, int rowNum) throws SQLException {
		//		return rs.getString("user_id");
		//	}
		//});
	return null;
	}

	private NotifyUser getNotifyUser(String orgId, String staffId){
		synchronized(this){
			this.init(orgId);
			return this.cache.get(orgId).get(staffId);
		}
	}
	class NotifyUser{
		
		private String userId;
		
		private Date lastNotifyTime;
		
		public NotifyUser(String userId){
			this.userId = userId;
		}
		
		public void clearNotifyTime(){
			this.lastNotifyTime = null;
		}
		
		public void setNotifyTime(){
			this.lastNotifyTime = new Date();
		}

		public String getUserId() {
			return userId;
		}

		public Date getLastNotifyTime() {
			return lastNotifyTime;
		}
		
	}

}
