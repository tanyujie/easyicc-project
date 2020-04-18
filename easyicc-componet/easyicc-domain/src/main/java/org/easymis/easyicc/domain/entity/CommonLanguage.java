package org.easymis.easyicc.domain.entity;

import lombok.Data;

@Data
public class CommonLanguage {
	private String id;
	private String orgId;
	private String categoryId;
	private String staffId;
	private String content;
	private Integer type;
	private String priority;
	private String title;
	private String hotKey;
	private String name;

}
