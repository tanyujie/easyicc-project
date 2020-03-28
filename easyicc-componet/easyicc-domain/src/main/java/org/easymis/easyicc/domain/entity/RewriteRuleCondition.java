package org.easymis.easyicc.domain.entity;

import lombok.Data;

@Data
public class RewriteRuleCondition {

	private String id;

	private String orgId;
	// 决策名称",column="rule_id",length=40,isnull=true)
	private String ruleId;
	// 决策名称",column="condition_name",length=255,isnull=true)
	private String conditionName;
	// 数据来源1request参数2cookie3ip4ip所属区域5来源页面6扩展参数",column="parameter_type",isnull=true)
	private Integer parameterType;
	// 数据参数",column="parameter",length=255,isnull=true)
	private String parameter;
	// 对比方式1完全相等2正式表达式3模糊匹配",column="match_type",isnull=true)
	private Integer matchType;
	// 对比数据",column="data",length=255,isnull=true)
	private String data;
}
