package org.easymis.easyicc.web.chat.controller;

import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;
import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.Member;
import org.easymis.easyicc.domain.vo.MemberVO;
import org.easymis.easyicc.web.chat.service.ChatMemberService;
import org.easymis.easyicc.web.chat.utils.MD5Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
@Api(description = "用户登录")
@RestController
public class LoginController {
	@Autowired
    private ChatMemberService userService;
    @PostMapping("/login")
    public RestResult registOrLogin(@RequestBody Member user) throws NoSuchAlgorithmException {
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
        	
            return RestResult.buildError("username or password can't be null");
        }
        boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());
        Member userResult = null;
        //存在则登录，否则注册
        if (usernameIsExist) {
            userResult = userService.queryUserForLogin(user.getUsername(), MD5Utils.getMD5Str(user.getPassword()));
            if (userResult == null) {
                return RestResult.buildError("username or password is illegal");
            }
        } else {
            user.setNickname(user.getUsername());
            user.setFaceImage("");
            user.setFaceImageBig("");
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
            userResult = userService.saveUser(user);
        }
        MemberVO userVO = new MemberVO();
        BeanUtils.copyProperties(userResult, userVO);
        userVO.setId(userResult.getMemberId());
        return RestResult.buildSuccess(userVO);
    }
}
