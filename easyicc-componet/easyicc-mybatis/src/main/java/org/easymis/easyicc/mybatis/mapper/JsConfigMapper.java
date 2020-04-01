package org.easymis.easyicc.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.easymis.easyicc.domain.entity.JsConfig;

public interface JsConfigMapper {
	@Select("select * from js_config")
	public List<JsConfig> getList(JsConfig params);

	@Insert("insert into js_config(config_id,org_id,description,bind_hosts,device_type,site_id,promotion_id,showIcon,with_monitor,category,floatPos,margin_y,margin_x,panel_when_invite,icon,icon_close,rel_group_id,monitor_type,invite_pos,mon_index,invite_text,mask,auto_invite,show_invite_again,auto_invite_times,auto_invite_data,auto_invite_group_id,phone_define,phone_position,lang,min_chat,pop_style,autochat_bg_color,pc_mini_window,toolbar_set,pc_message,top_image,top_image_min,page_title,send_keyword,search_mode,pc_mini_window_position,phone_minchat_size,min_chat_close_button,min_chat_min_button,min_chat_mask_button,auto_pop_Win_Time,auto_pop_Win_Peroid,first_to_rebot,chat_style,phone_chat_style,staff_head,mobile,can_back_page,phone_input_top,off_connect_type,mobile_message_remind,pop_msg_title,pop_msg_opacity,auto_chat,auto_connect,auto_chatDelay,auto_chatData,auto_chat_times,auto_chat_hide_monitor,pc_welcome_message,mobile_welcome_message,wait_text,log_image_url,pc_min_logo,log_image_link,ad2_image_url,ad2_image_link,staff_font,vist_font,count_code,ext_code,probe_text,probe_group,ocpc_platform,ocpc_condition,ocpc_config_id,update_id,update_time)values(#{configId},#{orgId},#{description},#{bindHosts},#{deviceType},#{siteId},#{promotionId},#{showIcon},#{withMonitor},#{category},#{floatPos},#{marginY},#{marginX},#{panelWhenInvite},#{icon},#{iconClose},#{relGroupId},#{monitorType},#{invitePos},#{monIndex},#{inviteText},#{mask},#{autoInvite},#{showInviteAgain},#{autoInviteTimes},#{autoInviteData},#{autoInviteGroupId},#{phoneDefine},#{phonePosition},#{lang},#{minChat},#{popStyle},#{autochatBgColor},#{pcMiniWindow},#{toolbarSet},#{pcMessage},#{topImage},#{topImageMin},#{pageTitle},#{sendKeyword},#{searchMode},#{pcMiniWindowPosition},#{phoneMinchatSize},#{minChatCloseButton},#{minChatMinButton},#{minChatMaskButton},#{autoPopWinTime},#{autoPopWinPeroid},#{firstToRebot},#{chatStyle},#{phoneChatStyle},#{staffHead},#{mobile},#{canBackPage},#{phoneInputTop},#{offConnectType},#{mobileMessageRemind},#{popMsgTitle},#{popMsgOpacity},#{autoChat},#{autoConnect},#{autoChatDelay},#{autoChatData},#{autoChatTimes},#{autoChatHideMonitor},#{pcWelcomeMessage},#{mobileWelcomeMessage},#{waitText},#{logImageUrl},#{pcMinLogo},#{logImageLink},#{ad2ImageUrl},#{ad2ImageLink},#{staffFont},#{vistFont},#{countCode},#{extCode},#{probeText},#{probeGroup},#{ocpcPlatform},#{ocpcCondition},#{ocpcConfigId},#{updateId},#{updateTime})")
	public boolean save(JsConfig bean);

	@Insert("insert into js_config(config_id,org_id,description,bind_hosts,device_type,site_id,promotion_id,showIcon,with_monitor,category,floatPos,margin_y,margin_x,panel_when_invite,icon,icon_close,rel_group_id,monitor_type,invite_pos,mon_index,invite_text,mask,auto_invite,show_invite_again,auto_invite_times,auto_invite_data,auto_invite_group_id,phone_define,phone_position,lang,min_chat,pop_style,autochat_bg_color,pc_mini_window,toolbar_set,pc_message,top_image,top_image_min,page_title,send_keyword,search_mode,pc_mini_window_position,phone_minchat_size,min_chat_close_button,min_chat_min_button,min_chat_mask_button,auto_pop_Win_Time,auto_pop_Win_Peroid,first_to_rebot,chat_style,phone_chat_style,staff_head,mobile,can_back_page,phone_input_top,off_connect_type,mobile_message_remind,pop_msg_title,pop_msg_opacity,auto_chat,auto_connect,auto_chatDelay,auto_chatData,auto_chat_times,auto_chat_hide_monitor,pc_welcome_message,mobile_welcome_message,wait_text,log_image_url,pc_min_logo,log_image_link,ad2_image_url,ad2_image_link,staff_font,vist_font,count_code,ext_code,probe_text,probe_group,ocpc_platform,ocpc_condition,ocpc_config_id,update_id,update_time)values(#{configId},#{orgId},#{description},#{bindHosts},#{deviceType},#{siteId},#{promotionId},#{showIcon},#{withMonitor},#{category},#{floatPos},#{marginY},#{marginX},#{panelWhenInvite},#{icon},#{iconClose},#{relGroupId},#{monitorType},#{invitePos},#{monIndex},#{inviteText},#{mask},#{autoInvite},#{showInviteAgain},#{autoInviteTimes},#{autoInviteData},#{autoInviteGroupId},#{phoneDefine},#{phonePosition},#{lang},#{minChat},#{popStyle},#{autochatBgColor},#{pcMiniWindow},#{toolbarSet},#{pcMessage},#{topImage},#{topImageMin},#{pageTitle},#{sendKeyword},#{searchMode},#{pcMiniWindowPosition},#{phoneMinchatSize},#{minChatCloseButton},#{minChatMinButton},#{minChatMaskButton},#{autoPopWinTime},#{autoPopWinPeroid},#{firstToRebot},#{chatStyle},#{phoneChatStyle},#{staffHead},#{mobile},#{canBackPage},#{phoneInputTop},#{offConnectType},#{mobileMessageRemind},#{popMsgTitle},#{popMsgOpacity},#{autoChat},#{autoConnect},#{autoChatDelay},#{autoChatData},#{autoChatTimes},#{autoChatHideMonitor},#{pcWelcomeMessage},#{mobileWelcomeMessage},#{waitText},#{logImageUrl},#{pcMinLogo},#{logImageLink},#{ad2ImageUrl},#{ad2ImageLink},#{staffFont},#{vistFont},#{countCode},#{extCode},#{probeText},#{probeGroup},#{ocpcPlatform},#{ocpcCondition},#{ocpcConfigId},#{updateId},#{updateTime})")
	public void saveBatch(List<JsConfig> beans);

	@Update("UPDATE js_config SET config_id= #{configId},org_id= #{orgId},description= #{description},bind_hosts= #{bindHosts},device_type= #{deviceType},site_id= #{siteId},promotion_id= #{promotionId},showIcon= #{showIcon},with_monitor= #{withMonitor},category= #{category},floatPos= #{floatPos},margin_y= #{marginY},margin_x= #{marginX},panel_when_invite= #{panelWhenInvite},icon= #{icon},icon_close= #{iconClose},rel_group_id= #{relGroupId},monitor_type= #{monitorType},invite_pos= #{invitePos},mon_index= #{monIndex},invite_text= #{inviteText},mask= #{mask},auto_invite= #{autoInvite},show_invite_again= #{showInviteAgain},auto_invite_times= #{autoInviteTimes},auto_invite_data= #{autoInviteData},auto_invite_group_id= #{autoInviteGroupId},phone_define= #{phoneDefine},phone_position= #{phonePosition},lang= #{lang},min_chat= #{minChat},pop_style= #{popStyle},autochat_bg_color= #{autochatBgColor},pc_mini_window= #{pcMiniWindow},toolbar_set= #{toolbarSet},pc_message= #{pcMessage},top_image= #{topImage},top_image_min= #{topImageMin},page_title= #{pageTitle},send_keyword= #{sendKeyword},search_mode= #{searchMode},pc_mini_window_position= #{pcMiniWindowPosition},phone_minchat_size= #{phoneMinchatSize},min_chat_close_button= #{minChatCloseButton},min_chat_min_button= #{minChatMinButton},min_chat_mask_button= #{minChatMaskButton},auto_pop_Win_Time= #{autoPopWinTime},auto_pop_Win_Peroid= #{autoPopWinPeroid},first_to_rebot= #{firstToRebot},chat_style= #{chatStyle},phone_chat_style= #{phoneChatStyle},staff_head= #{staffHead},mobile= #{mobile},can_back_page= #{canBackPage},phone_input_top= #{phoneInputTop},off_connect_type= #{offConnectType},mobile_message_remind= #{mobileMessageRemind},pop_msg_title= #{popMsgTitle},pop_msg_opacity= #{popMsgOpacity},auto_chat= #{autoChat},auto_connect= #{autoConnect},auto_chatDelay= #{autoChatDelay},auto_chatData= #{autoChatData},auto_chat_times= #{autoChatTimes},auto_chat_hide_monitor= #{autoChatHideMonitor},pc_welcome_message= #{pcWelcomeMessage},mobile_welcome_message= #{mobileWelcomeMessage},wait_text= #{waitText},log_image_url= #{logImageUrl},pc_min_logo= #{pcMinLogo},log_image_link= #{logImageLink},ad2_image_url= #{ad2ImageUrl},ad2_image_link= #{ad2ImageLink},staff_font= #{staffFont},vist_font= #{vistFont},count_code= #{countCode},ext_code= #{extCode},probe_text= #{probeText},probe_group= #{probeGroup},ocpc_platform= #{ocpcPlatform},ocpc_condition= #{ocpcCondition},ocpc_config_id= #{ocpcConfigId},update_id= #{updateId},update_time= #{updateTime} WHERE config_id= #{configId}")
	public boolean update(JsConfig bean);

	@Delete(" DELETE FROM js_config WHERE config_id = #{configId}")
	public void delete(String config_id);
	@Delete({"<script>",
        "DELETE FROM js_config",
        "WHERE config_id IN", 
          "<foreach item='item' index='index' collection='ids'",
            "open='(' separator=',' close=')'>",
            "#{item}",
          "</foreach>",
        "</script>"}) 
	public void removeBatch(@Param("ids") List<String> ids);

	public void restoreBatch(List<String> list);

	public void deleteBatch(List<String> list);

	@Select("select * from js_config t WHERE t.config_id = #{configId}")
	public JsConfig findById(String configId);

	@Select(" SELECT t.* FROM js_config t }")
	public List<JsConfig> findByIds(List<String> list);
}