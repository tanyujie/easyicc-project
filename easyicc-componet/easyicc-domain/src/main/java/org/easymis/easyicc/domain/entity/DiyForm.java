package org.easymis.easyicc.domain.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class DiyForm implements Serializable {

	private String formId;

	private String orgId;
	// 注册表单名称",column="name",length=255,isnull=true)
	private String name;
	// ",column="depict",length=255,isnull=true)
	private String depict;
	// ",column="template_id",length=200,isnull=true)
	private String templateId;
	// 提交成功页面",column="success_url",length=255,isnull=true)
	private String successUrl;
	// ",column="create_id",length=255,isnull=true)
	private String createId;
	// ",column="create_time",isnull=true)
	private Date createTime;
	// 注册页面嵌入代码",column="js_config_id",length=40,isnull=true)
	private String jsConfigId;

}