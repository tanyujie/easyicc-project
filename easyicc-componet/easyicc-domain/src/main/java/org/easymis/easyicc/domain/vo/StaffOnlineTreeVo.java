package org.easymis.easyicc.domain.vo;

import java.util.List;

import lombok.Data;
@Data
public class StaffOnlineTreeVo {
	private String staffId;
	private String name;
	private List<ChatOnlineVo> chatOnlineList;
}
