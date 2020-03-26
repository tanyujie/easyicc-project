package org.easymis.easyicc.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.easymis.easyicc.domain.entity.Card;

public interface CardMapper {	
	@Select("<script> " + "SELECT * FROM visitor_info a " + 
            "inner join card_rule b on a.org_id=b.org_id "
			+ "where b.use_allocation=1 and b.server_name = #{serverName} "
			+ "and a.create_time is not null and a.create_time between (sysdate() - 2) and (sysdate() - b.allocation_delay/24/60) "
			+ "and allocation_status = 0 " + "order by a.org_id, a.ext_column7, a.create_time"
			+ " </script>")
	public List<Card> getAllWaitForAllocationCards(String serverName);
}
