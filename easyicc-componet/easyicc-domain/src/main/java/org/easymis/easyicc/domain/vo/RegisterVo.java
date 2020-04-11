package org.easymis.easyicc.domain.vo;

import lombok.Data;

@Data
public class RegisterVo {
	private String orgName;
	private String mobileNo;
	private String password;
	private String verificationCode;
	private String email;
}
