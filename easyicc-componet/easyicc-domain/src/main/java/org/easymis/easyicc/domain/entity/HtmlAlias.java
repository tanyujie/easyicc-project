package org.easymis.easyicc.domain.entity;

import java.util.Date;

import lombok.Data;
@Data
public class HtmlAlias {

	private String id;

	private String orgId;
	// URL",column="url",length=255,isnull=true)
	private String url;
	// 网页别名",column="alias",length=255,isnull=true)
	private String alias;
	// 描述",column="depict",length=255,isnull=true)
	private String depict;
	// ",column="create_staff_id",length=40,isnull=true)
	private String createStaffId;
	// ",column="create_time",isnull=true)
	private Date createTime;
	// ",column="modify_staff_id",length=40,isnull=true)
	private String modifyStaffId;
	// ",column="modify_time",isnull=true)
	private Date modifyTime;
}
