package org.easymis.easyicc.domain.vo.question;

import lombok.Data;
//地理位置信息
@Data
public class Location {
	private String city;//所在城市
	private String province;//省份
	private String street;//街道
}
