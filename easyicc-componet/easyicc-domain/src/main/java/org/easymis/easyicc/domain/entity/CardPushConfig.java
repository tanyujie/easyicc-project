package org.easymis.easyicc.domain.entity;

import lombok.Data;

@Data
public class CardPushConfig {
	private String id;
	private String orgId;
	private String config;
	private String tpl;
	private String PARAM_TYPE;
	private Integer status;
	private String INTERFACE_URL;
	private String COMPANY_NAME;
	private String BODY_TYPE;
	private String IS_DATA_SIGN;
	private String SIGN_ALGORITHM;
	private String SIGN_BODY;
	private String CONTAIN_TYPE;
	private String CONTAIN_RULE;
	private String THIRD_PLATFORM;

}
