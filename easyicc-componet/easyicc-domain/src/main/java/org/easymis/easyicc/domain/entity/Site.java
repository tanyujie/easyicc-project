package org.easymis.easyicc.domain.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Site implements Serializable {

	private String id;

	private String orgId;
	// 子站点名称
	private String name;
	// 子站点描述
	private String depict;

}