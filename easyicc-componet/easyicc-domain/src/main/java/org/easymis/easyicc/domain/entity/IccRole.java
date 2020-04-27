package org.easymis.easyicc.domain.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class IccRole implements Serializable {

	private String roleId;
	private String orgId;
	// 名称", column = "role_name", id = true, length = 32, isnull = false)
	private String roleName;
	// ROLE_开头,用于security判断", column = "role_sn", length = 32, isnull = true)
	private String depict;
	// 模板名称", column = "template_name", length = 100, isnull = false)
	private Integer roleStatus;

}