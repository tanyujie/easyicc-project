package org.easymis.easyicc.domain.vo.answer;

import java.util.List;

import lombok.Data;

@Data
public class AnswerVo {
	//请求意图
	private Intent intent;
	//输出结果集
	private List<Result> results;
}
