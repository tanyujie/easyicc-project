package org.easymis.easyicc.mybatis.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.easymis.easyicc.domain.entity.OrganizeConfig;

public interface OrganizeConfigMapper {
	@Select("select * from organize_config")
	public List<OrganizeConfig> getList(HashMap<String, Object> params);

	@Insert("insert into organize_config(org_id,show_transfer_message,spending_type,server_type,show_robot,save_record,robot_name,robot_welcome_message,robot_unknown_message,modify_time,customer_timeout,chat_close_message,chat_transfer_message,chat_keyword_filter,phone_overtime_switch,validate,only_chat,leave_message_type,visitor_phone_verification)values(#{orgId},#{showTransferMessage},#{spendingType},#{serverType},#{showRobot},#{saveRecord},#{robotName},#{robotWelcomeMessage},#{robotUnknownMessage},#{modifyTime},#{customerTimeout},#{chatCloseMessage},#{chatTransferMessage},#{chatKeywordFilter},#{phoneOvertimeSwitch},#{validate},#{onlyChat},#{leaveMessageType},#{visitorPhoneVerification})")
	public void save(OrganizeConfig bean);

	@Insert("insert into organize_config(org_id,show_transfer_message,spending_type,server_type,show_robot,save_record,robot_name,robot_welcome_message,robot_unknown_message,modify_time,customer_timeout,chat_close_message,chat_transfer_message,chat_keyword_filter,phone_overtime_switch,validate,only_chat,leave_message_type,visitor_phone_verification)values(#{orgId},#{showTransferMessage},#{spendingType},#{serverType},#{showRobot},#{saveRecord},#{robotName},#{robotWelcomeMessage},#{robotUnknownMessage},#{modifyTime},#{customerTimeout},#{chatCloseMessage},#{chatTransferMessage},#{chatKeywordFilter},#{phoneOvertimeSwitch},#{validate},#{onlyChat},#{leaveMessageType},#{visitorPhoneVerification})")
	public void saveBatch(List<OrganizeConfig> beans);

	@Update("UPDATE organize_config SET org_id= #{orgId},show_transfer_message= #{showTransferMessage},spending_type= #{spendingType},server_type= #{serverType},show_robot= #{showRobot},save_record= #{saveRecord},robot_name= #{robotName},robot_welcome_message= #{robotWelcomeMessage},robot_unknown_message= #{robotUnknownMessage},modify_time= #{modifyTime},customer_timeout= #{customerTimeout},chat_close_message= #{chatCloseMessage},chat_transfer_message= #{chatTransferMessage},chat_keyword_filter= #{chatKeywordFilter},phone_overtime_switch= #{phoneOvertimeSwitch},validate= #{validate},only_chat= #{onlyChat},leave_message_type= #{leaveMessageType},visitor_phone_verification= #{visitorPhoneVerification} WHERE org_id= #{orgId}")
	public void update(OrganizeConfig bean);

	@Delete(" DELETE FROM organize_config WHERE org_id = #{orgId}")
	public void delete(String org_id);

	public void removeBatch(List<String> list);

	public void restoreBatch(List<String> list);

	public void deleteBatch(List<String> list);

	@Select("select * from organize_config t WHERE t.org_id = #{orgId}")
	public OrganizeConfig findById(String orgId);

	@Select(" SELECT t.* FROM organize_config t }")
	public List<OrganizeConfig> findByIds(List<String> list);
}
