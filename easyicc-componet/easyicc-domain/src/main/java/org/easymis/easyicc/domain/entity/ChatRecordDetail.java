package org.easymis.easyicc.domain.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class ChatRecordDetail implements Serializable {

	private String id;

	private String recordId;

	private String orgId;

}