package org.easymis.easyicc.domain.vo;

import java.util.List;

import org.easymis.easyicc.domain.entity.CommonLanguage;

import lombok.Data;

@Data
public class CommonLanguageTreeVo {
	private String id;
	private String name;
	private List<CommonLanguage> languageList;
}
