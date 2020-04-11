package org.easymis.easyicc.domain.vo;

import java.util.List;

import lombok.Data;
/**
 * 
　 * <p>Title: 访客对话树 数据组装列表</p>
　 * <p>Description: </p>
　 * @author 谭宇杰
　 * @date 2020年4月11日
 */
@Data
public class VisitorTreeVo {
	private List<ChatOnlineVo> chatWaitList;//排队访客
	private List<StaffOnlineTreeVo>  staffOnlineTree;//员工接待访客
	private List<ChatOnlineVo> browseWebsiteList;//浏览网站访客
}
