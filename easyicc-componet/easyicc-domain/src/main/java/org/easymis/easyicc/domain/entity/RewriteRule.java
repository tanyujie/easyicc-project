package org.easymis.easyicc.domain.entity;

import lombok.Data;
@Data
public class RewriteRule {
	private String id;

	private String orgId;

	private String priority;
	// 分组名称",column="rule_name",length=255,isnull=true)
	private String ruleName;
	// 分组信息",column="depict",length=255,isnull=true)
	private String depict;
	// 客户分组",column="group_id",length=40,isnull=true)
	private String groupId;
}
