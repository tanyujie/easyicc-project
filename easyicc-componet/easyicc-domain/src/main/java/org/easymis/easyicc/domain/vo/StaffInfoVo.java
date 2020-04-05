package org.easymis.easyicc.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@ApiModel(value="StaffInfoVo对象",description="用户客户端IM在线组织结构显示信息")
@Data
public class StaffInfoVo {
    @ApiModelProperty(value="员工Id|客服id")
	private String staffId;
    @ApiModelProperty(value="客服名称")
	private String name;
    @ApiModelProperty(value="客服昵称")
	private String nickname;
    @ApiModelProperty(value="客服所属组织")
	private String orgId;
    @ApiModelProperty(value="在线状态")
	private Integer onlineFlag;
}
