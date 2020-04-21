package org.easymis.easyicc.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.easymis.easyicc.domain.entity.HtmlAlias;

public interface HtmlAliasMapper {
	@Select("select * from html_alias")
	public List<HtmlAlias> getList(HtmlAlias params);
}
