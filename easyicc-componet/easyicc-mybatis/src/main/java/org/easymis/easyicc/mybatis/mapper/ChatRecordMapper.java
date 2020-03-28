package org.easymis.easyicc.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.easymis.easyicc.domain.entity.ChatRecord;

public interface ChatRecordMapper {
	@Select({"<script>",
        "SELECT * from chat_record",
        " <where> " +
        " <if test=\"orgId != null\"> AND org_id=#{orgId}</if> " +

        " </where> " +
        "</script>"}) 
	public List<ChatRecord> getList(ChatRecord params);
}
