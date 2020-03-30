package org.easymis.easyicc.service;

public interface NotifyService {
	public void clearNotifyTime(String orgId, String staffId);

	public void notifyWaitAllocation(String orgId, String message);
}
