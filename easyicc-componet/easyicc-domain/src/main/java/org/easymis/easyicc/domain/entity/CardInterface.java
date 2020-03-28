package org.easymis.easyicc.domain.entity;

import java.util.Date;

import lombok.Data;

@Data
public class CardInterface {
	private String id;
	private String token;
	private String orgId;
	private String param;
	private String url;
	private Date createTime;
	private Date updateTime;
	private Integer deleteFlag;
	private Integer isUseSubject;
	private Integer isUseSchool;

}
