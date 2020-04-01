package org.easymis.easyicc.domain.vo.jsconfig;

import lombok.Data;

/**
 * 
 * <p>
 * Title: 工具栏设置
 * </p>
 * <p>
 * Description:
 * </p>
 * @author 谭宇杰 @date 2020年4月1日
 */
@Data
public class ToolbarSet {
	// 工具栏设置{toolEmoticons表情，toolScreen截图，toolOpinion评价，toolFile文件，toolQuiet静音}
	private Integer toolEmoticons;
	private Integer toolScreen;
	private Integer toolOpinion;
	private Integer toolFile;
	private Integer toolQuiet;
}
