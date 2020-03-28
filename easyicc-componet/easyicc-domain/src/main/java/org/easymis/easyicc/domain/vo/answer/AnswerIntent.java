package org.easymis.easyicc.domain.vo.answer;

import java.util.HashMap;

import lombok.Data;

//请求意图
@Data
public class AnswerIntent {
	/*5000无解析结果|6000暂不支持该功能
	 * 4007|apikey不合法
4100|userid获取失败
0|上传成功

	 */
	private String code;
	//输出类别100000文本类|200000链接类|302000新闻类|308000菜谱类
	//private String category;
	private String intentName;//意图名称
	private String actionName;//意图动作名称
	private HashMap<?, ?> parameters;//功能相关参数
}
