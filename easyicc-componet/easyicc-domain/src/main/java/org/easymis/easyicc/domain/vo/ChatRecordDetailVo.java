package org.easymis.easyicc.domain.vo;

import org.easymis.easyicc.domain.entity.ChatRecordDetail;

import lombok.Data;
@Data
public class ChatRecordDetailVo extends ChatRecordDetail {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private Integer start;
	private Integer vStart;

}
