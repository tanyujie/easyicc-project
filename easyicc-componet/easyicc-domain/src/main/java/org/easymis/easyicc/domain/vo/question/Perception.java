package org.easymis.easyicc.domain.vo.question;

import lombok.Data;

@Data
public class Perception {
	//文本信息
	private InputText inputText;
	//图片信息
	private InputImage inputImage;
	private InputMedia inputMedia;
}
