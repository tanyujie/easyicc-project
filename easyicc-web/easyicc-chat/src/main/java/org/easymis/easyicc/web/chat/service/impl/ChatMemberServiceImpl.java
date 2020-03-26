package org.easymis.easyicc.web.chat.service.impl;

import java.util.Date;
import java.util.List;

import org.easymis.easyicc.domain.entity.FriendsRequest;
import org.easymis.easyicc.domain.entity.Member;
import org.easymis.easyicc.domain.entity.MyFriends;
import org.easymis.easyicc.domain.vo.FriendRequestVO;
import org.easymis.easyicc.domain.vo.MyFriendsVO;
import org.easymis.easyicc.mybatis.mapper.ChatMsgMapper;
import org.easymis.easyicc.mybatis.mapper.FriendsRequestMapper;
import org.easymis.easyicc.mybatis.mapper.MemberMapper;
import org.easymis.easyicc.mybatis.mapper.MyFriendsMapper;
import org.easymis.easyicc.mybatis.mapper.UsersMapperCustom;
import org.easymis.easyicc.web.chat.config.netty.ChatMsg;
import org.easymis.easyicc.web.chat.config.netty.DataContent;
import org.easymis.easyicc.web.chat.config.netty.UserChannelRel;
import org.easymis.easyicc.web.chat.enums.MsgActionEnum;
import org.easymis.easyicc.web.chat.enums.MsgSignFlagEnum;
import org.easymis.easyicc.web.chat.enums.SearchFriendsStatusEnum;
import org.easymis.easyicc.web.chat.n3r.idworker.Sid;
import org.easymis.easyicc.web.chat.service.MemberService;
import org.easymis.easyicc.web.chat.utils.FileUtils;
import org.easymis.easyicc.web.chat.utils.JsonUtils;
import org.easymis.easyicc.web.chat.utils.QRCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import springfox.documentation.schema.Example;

@Service("memberServiceImpl")  
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper userMapper;
	
	@Autowired
	private UsersMapperCustom usersMapperCustom;
	
	@Autowired
	private MyFriendsMapper myFriendsMapper;
	
	@Autowired
	private FriendsRequestMapper friendsRequestMapper;
	
	@Autowired
	private ChatMsgMapper chatMsgMapper;
	
	@Autowired
	private Sid sid;
	
	@Autowired
	private QRCodeUtils qrCodeUtils;
	
	
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public boolean queryUsernameIsExist(String phoneNumber) {
		Member result = userMapper.findByPhoneNumber(phoneNumber);
		
		return result != null ? true : false;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Member queryUserForLogin(String phoneNumber, String pwd) {
		Member result = userMapper.get(phoneNumber,pwd);
		
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Member saveUser(Member user) {
		
		String userId = sid.nextShort();
		
		// 为每个用户生成一个唯一的二维码
		String qrCodePath = "C://user" + userId + "qrcode.png";
		// muxin_qrcode:[username]
		qrCodeUtils.createQRCode(qrCodePath, "muxin_qrcode:" + user.getUsername());
		MultipartFile qrCodeFile = FileUtils.fileToMultipart(qrCodePath);
		
		String qrCodeUrl = "";
		try {
			qrCodeUrl = null;//FastDFSFile.uploadQRCode(qrCodeFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		user.setQrcode(qrCodeUrl);
		
		user.setId(userId);
		userMapper.insertByBean(user);
		
		return user;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Member updateUserInfo(Member user) {
		//userMapper.updateByPrimaryKeySelective(user);
		return queryUserById(user.getId());
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	private Member queryUserById(String userId) {
		return userMapper.findById(userId);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Integer preconditionSearchFriends(String myUserId, String friendUsername) {

		Member user = queryUserInfoByUsername(friendUsername);
		
		// 1. 搜索的用户如果不存在，返回[无此用户]
		if (user == null) {
			return SearchFriendsStatusEnum.USER_NOT_EXIST.status;
		}
		
		// 2. 搜索账号是你自己，返回[不能添加自己]
		if (user.getMemberId().equals(myUserId)) {
			return SearchFriendsStatusEnum.NOT_YOURSELF.status;
		}
		
		// 3. 搜索的朋友已经是你的好友，返回[该用户已经是你的好友]
		MyFriends myFriendsRel = myFriendsMapper.find(myUserId, user.getId());
		if (myFriendsRel != null) {
			return SearchFriendsStatusEnum.ALREADY_FRIENDS.status;
		}
		
		return SearchFriendsStatusEnum.SUCCESS.status;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Member queryUserInfoByUsername(String phoneNumber) {
		return userMapper.findByPhoneNumber(phoneNumber);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void sendFriendRequest(String myUserId, String friendUsername) {

		// 根据用户名把朋友信息查询出来
		Member friend = queryUserInfoByUsername(friendUsername);

		// 1. 查询发送好友请求记录表
		Example fre = new Example(FriendsRequest.class);
		FriendsRequest friendRequest = friendsRequestMapper.find(myUserId, friend.getId());
		if (friendRequest == null) {
			// 2. 如果不是你的好友，并且好友记录没有添加，则新增好友请求记录
			String requestId = sid.nextShort();

			FriendsRequest request = new FriendsRequest();
			request.setId(requestId);
			request.setSendUserId(myUserId);
			request.setAcceptUserId(friend.getId());
			request.setRequestDateTime(new Date());
			friendsRequestMapper.insertByBean(request);
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<FriendRequestVO> queryFriendRequestList(String acceptUserId) {
		return usersMapperCustom.queryFriendRequestList(acceptUserId);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void deleteFriendRequest(String sendUserId, String acceptUserId) {
		friendsRequestMapper.delete(sendUserId, acceptUserId);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void passFriendRequest(String sendUserId, String acceptUserId) {
		saveFriends(sendUserId, acceptUserId);
		saveFriends(acceptUserId, sendUserId);
		deleteFriendRequest(sendUserId, acceptUserId);
		
		Channel sendChannel = UserChannelRel.get(sendUserId);
		if (sendChannel != null) {
			// 使用websocket主动推送消息到请求发起者，更新他的通讯录列表为最新
			DataContent dataContent = new DataContent();
			dataContent.setAction(MsgActionEnum.PULL_FRIEND.type);
			
			sendChannel.writeAndFlush(
					new TextWebSocketFrame(
							JsonUtils.objectToJson(dataContent)));
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	private void saveFriends(String sendUserId, String acceptUserId) {
		MyFriends myFriends = new MyFriends();
		String recordId = sid.nextShort();
		myFriends.setId(recordId);
		myFriends.setMyFriendUserId(acceptUserId);
		myFriends.setMyUserId(sendUserId);
		myFriendsMapper.insertByBean(myFriends);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<MyFriendsVO> queryMyFriends(String userId) {
		List<MyFriendsVO> myFirends = usersMapperCustom.queryMyFriends(userId);
		return myFirends;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public String saveMsg(ChatMsg chatMsg) {
		
		org.easymis.easyicc.domain.entity.ChatMsg msgDB = new org.easymis.easyicc.domain.entity.ChatMsg();
		String msgId = sid.nextShort();		
		msgDB.setId(msgId);
		msgDB.setOrgId(chatMsg.getOrgId());
		msgDB.setAcceptMemberId(chatMsg.getReceiverId());
		msgDB.setSendMemberId(chatMsg.getSenderId());
		msgDB.setCreateTime(new Date());
		msgDB.setSignFlag(MsgSignFlagEnum.unsign.type);
		msgDB.setMsg(chatMsg.getMsg());
		
		chatMsgMapper.insertByBean(msgDB);
		
		return msgId;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void updateMsgSigned(List<String> msgIdList) {
		usersMapperCustom.batchUpdateMsgSigned(msgIdList);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<org.easymis.easyicc.domain.entity.ChatMsg> getUnReadMsgList(String acceptUserId) {
		
		List<org.easymis.easyicc.domain.entity.ChatMsg> result = chatMsgMapper.find(0, acceptUserId);
		
		return result;
	}
}
