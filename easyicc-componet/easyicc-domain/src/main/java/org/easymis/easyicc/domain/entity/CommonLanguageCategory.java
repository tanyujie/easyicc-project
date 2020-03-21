package org.easymis.easyicc.domain.entity;

import lombok.Data;

@Data
public class CommonLanguageCategory {

	private String id;

	private String orgId;

	private String name;

	private String depict;

	private String parentId;

	private Integer priority;
}
