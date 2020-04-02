var chatId;
var isConnected = false;
var opinioned = false;
var getMessageInterval = 3000;
var inputView = false;
var V_SEND_MSG_IN_AUTOCLOSE_PROCESS = false;
var autoCloseRepeat=0;
var SendTimesOfToVisitorMsg='';

var auto_closeChat_timer = null;

var notIEScreenTime = null;
var notIEScreenActiveX = false;

var needFocus = false;
var etext="";

var emots = new Array();

var waitingQueue = false;
var systemclose = false;
var shortcut = 'Enter';
var maxAllowLen = 500;
var lastMessageTime = new Date();
var sendACKTime = null;
var keepAlivePeriod = 110; 
var waitingQueueTimes = 0;

var flashtitle=-1;
var windowFocus=true;
var unReadMsgStep=1; //读取信息
var commonTitle=document.title; //页面标题

var lazyConnectStatus = 0;//0未建立对话， 1正在建立对话， 2建立对话， 3对话结束

var transHidden = false;

var tagMessage = null; //标记消息

var keyWord = null;

var mobile_flag = isMobile();

var time = 60;
var t;
var timer=null;
var browserType = myexplorer();
//tanyujie
var visitorPhoneVerification=0;
var popStyle=0;
var bgColor="#409EFF";
var showAboutUsTab=0;
var showCustomerInfoTab=1;
var showToolEmoticons=1;
var showToolFile=1;
var showToolScreen=1;
var showToolOpinion=1;
var showToolQuiet=1;
if(isNullOrEmpty(visitorPhoneVerification)){
	visitorPhoneVerification = 0;
}

/**
 * 初始化UI
 */
function initUI(){
	if(popStyle == "0" && !isPhone()){
		$(".container-screen").css("overflow", "hidden").addClass("container-fullScreen").removeClass("container-screen");
	}
	
	$(".container-fullScreen .header-img, .container-screen .header-img, .btn").css("backgroundColor", bgColor);
	$(".btn").css("border", "1px solid "+bgColor);
	
	 $('.nav-tabs').click(function () {
	    var i=$(this).index();
	    $(this).addClass('active').siblings().removeClass('active');
	    $('.tab-content div').eq(i).show().siblings().hide();
	  });
	
	if(showAboutUsTab == 0){
		$(".nav .nav-tabs:eq(1), .tab-content .tabs:eq(1)").hide();
	}else{
		$(".nav .nav-tabs:eq(1)").trigger("click");
	}
	
	if(showCustomerInfoTab == 0){
		$(".nav .nav-tabs:eq(0), .tab-content .tabs:eq(0)").hide();
	}else{
		$(".nav .nav-tabs:eq(0)").trigger("click");
	}
	
	
	document.onkeydown = function(){   
		//屏蔽   Alt+   方向键   ← 以及 屏蔽   Alt+   方向键   → 
		if((window.event.altKey)   &&    ((window.event.keyCode==37)||  (window.event.keyCode==39))) { 
			event.keyCode=0; 
		    event.returnValue=false; 
		}
		if(event.keyCode==116) {
			event.keyCode=0; 
			event.returnValue=false; 
	    } 
		return true;    
	};
	
	//输入区键盘事件
	$("#message").keydown(function(e){
		if(!e.ctrlKey && !e.shiftKey && shortcut == "Enter" && e.which==13){
			 sendMessage();
			 return false;
		}else if(e.ctrlKey && shortcut == "CtrlEnter" && e.which==13){
			 sendMessage();
	         return false;
		}
	    return true;
	}).focus(function(){
		$(".pop-window").hide();
		if(mobile_flag){
			if(browserType == "搜狗浏览器"){
				var n=0;
				timer=setInterval(function(){
				    if(n<3){
				        n++;
				    }else{
				        clearInterval(timer);
				    }
				    document.body.scrollTop = document.body.scrollHeight;
				},300);
			}else if(browserType == "华为"){
				$(".footer").height("560px");
			}
		}
		
		if(inputText == 1){ //开启客户端预知输入
			checkInput();
		}
		
	})
	
	//输入区失去焦点 输入框返回底部
	$("#message").blur(function(){
        window.scroll(0, 0);
        document.body.scrollTop = 0;
		document.documentElement.scrollTop = 0;
		
		if(mobile_flag){
			if(browserType == "QQ浏览器"){
				if (timer) {
				    clearInterval(timer);
				}
			}else if(browserType == "华为"){
				$(".footer").height("124px");
			}
		}
	})
	
	//结束对话
	$("#exitBtn").click(function(){
		//结束对话并且关闭对话
		if(isConnected && !confirm(getLabelByName("chatExit"))){//您确定要结束会话并关闭当前页面吗？
			return;
		}
		//结束对话
		if(isConnected){
			addMsg(getSysStyleDiv(getLabelByName("chatSystemMessage"), getLabelByName("chatDisconnect")));
		}
		endChat();
		if(chatId==null||chatId==""){
			closeWindow();
		}else if(opinioned){
			if(isPhone()){
				pageBack();
			}else{
				closeWindow();
			}
		}else if(callerOpinion=='1'){
			openOpinonWin();
		}
		
		//保存手机号
		var pressPhone = $("#msgPhone").val();
		var chatPhone = $("#message").val();
		if(!isNullOrEmpty(pressPhone) && isphone(pressPhone)){
			savePhone(pressPhone);
			return;
		}else if(!isNullOrEmpty(pressPhone) && isphone(chatPhone)){
			savePhone(chatPhone);
		}
		return;
	});
	
	//是否显示表情列表
	if(showToolEmoticons == "1"){
		console.log(staticFilePath)
		for(var i=1; i<=15; i++){
			var img =  i<10 ? ("0"+i) : i;
			var style = "";
			if((i-1)%5 == 0){
				style = "clear:both;";
			}
			$("<div class=\"emot\" style=\""+style+"\"><img src=\""+staticFilePath+"emoticon/new/"+img+".png\"></img></div>").data("img",img).appendTo($(".emoticon-layout .emoticons"));
		}
		
		$(".emoticon-layout .emot").hover(function(){
			$(this).css("backgroundColor", "#eeeeee");
		}, function(){
			$(this).css("backgroundColor", "#ffffff");
		}).click(function(){
			var img = $(this).data("img");
			appendEmot("["+img+"]", staticFilePath + "emoticon/new/"+img+".png");
			$(".emoticon-layout").hide();
		});
		$("#emoticonBtn").click(function(){
			$(".pop-window").hide();
			$(".emoticon-layout").show();
		});
	}else{
		$("#emoticonBtn").hide();
	}
	
	//上传文件
	if(showToolFile == "1"){
		if(!isPhone()){
			$("#fileBtn").click(function(){
				$(".pop-window").hide();
				$(".file-layout").show();
			});
		}
	}else{
		$("#fileBtn").hide();
	}
	
	//截屏
	if(showToolScreen == "1"){
		$("#screenShotBtn").click(function(){
			startScreenShot();
		});
	}else{
		$("#screenShotBtn").hide();
	}
	
	//评价
	if(showToolOpinion == "1"){
		$("#opinionBtn").click(function(){
			openOpinonWin();
		});
		$(".opinion-layout .close").click(function(){
			$(".pop-window").hide();
			$(".mask").hide();
			resetOpinionLayout();
		});
		$(".opinion-layout .star").click(function(){
			$(this).prevAll().removeClass("star-1").removeClass("star-2").addClass("star-1");
			$(this).nextAll().removeClass("star-1").removeClass("star-2").addClass("star-2");
			$(this).removeClass("star-1").removeClass("star-2").addClass("star-1");
			var ch = $(".opinion-layout .star-1").length;
			$(".opinion-layout p.result span").hide();
			$(".opinion-layout p.result span.op"+ch).show();
			var arr = ["-1", "1", "2", "3", "4"];
			$("#opinionValue").val(arr[ch-1]);
		});
		$(".opinion-layout .commit").click(commitOpinion);
	}else{
		$("#opinionBtn").hide();
	}
	//声音
	if(showToolQuiet == "1"){
		$("#quietBtn").click(function(){
			$("#quietBtn").hide();
			$("#soundBtn").show();
			quietFlag = false;
		});
		$("#soundBtn").click(function(){
			$("#soundBtn").hide();
			$("#quietBtn").show();
			quietFlag = true;
		});
	}else{
		$("#quietBtn, #soundBtn").hide();
	}
	
	if(quietFlag == true){
		$("#soundBtn").click();
	}
	
	if(chaturl == "" && !document.referrer && typeof(isConnected) != "undefined"){
		$(".historyBack").hide();
	}
	
	$(".historyBack").click(function(){
		if(typeof(isConnected) != "undefined" && isConnected && canBackPage == "1"){
			$(".mask").show();
			$(".inputingPromptLayout").show();
			var top = parseInt(($(window).height()-$(".inputingPromptLayout").height())/2);
			var left = parseInt(($(window).width()-$(".inputingPromptLayout").width())/2);
			$(".inputingPromptLayout").css({
				top:top,
				left:left
			});
			window.setTimeout(function(){
				closeInputingPromptLayout();
			}, 3000);
		}else{
			try{
				var backurl = null;
				if((typeof chaturl) != "undefined" && chaturl != "" && isURL(chaturl)){
					backurl = chaturl;
				}else if(document.referrer){
					backurl = document.referrer;
				}
				if((backurl != null) || typeof(isConnected) == "undefined"){
					window.onbeforeunload = function(){};
					window.onunload = function(){};
					if(backurl != null || chaturl != ""){
						window.location = backurl == null ? chaturl : backurl;
					}else{
						closeWindow();
					}
				}else if(!opinioned && callerOpinion=='1'){
					openOpinonWin();
				}else{
					pageBack();
				}
			}catch(e){
				closeWindow();
			}
		}
	});
	
	//发送消息
//	$("#sendBtn").click(function(){
//		sendMessage();
//		return false;
//	});
	var Button = document.getElementById("sendBtn");
	var clickEvent = (function() {
		if ('ontouchstart' in document.documentElement === true){
			 return 'touchstart';
		}else{
			return 'click';
		}
	})();
	
	Button.addEventListener(clickEvent, function(e) {
		sendMessage();
		return false;
	})
	
	//选择发送方式  
	$('.send-button .s1').click(sendMessage);//发送消息按钮
	$('.send-type').find("div").click(function(){//选择发送方式
		var val = $(this).attr('rel');
		shortcut = val;
		if(val == "Enter"){
			$(".send-type").removeClass("key-enter-ctrl").addClass("key-enter");
		}else{
			$(".send-type").removeClass("key-enter").addClass("key-enter-ctrl");
		}
		$(".send-type").hide();
	});
	
	$('.tabs').find('div').click(function(){//tab卡点击
		var rel = $(this).attr('rel');
		if(rel && rel!=''){
			onTabClicked(rel);
		}
	});
	
	$("#head").find("b").click(function(){
		var id = $(this).attr("id");
		if(!$(this).hasClass("active")){
			onTabClicked(id);
		}
	});
	
	
	$('#emotFrame').attr('src', 'emoticon.html');
	
	//工具栏hover事件
	$('.msg-toolbar div').hover(function(){
		$('.msg-toolbar div').each(function(){
			var _rC = $(this).attr('rel');
			$(this).removeClass().addClass(_rC);
		});
		var _cN = $(this).attr('rel');
		$(this).removeClass().addClass(_cN+'F');
	},function(){
		$('.msg-toolbar div').each(function(){
			var _rC = $(this).attr('rel');
			$(this).removeClass().addClass(_rC);
		});
		var _cN = $(this).attr('rel');
		$(this).removeClass().addClass(_cN);
	});
	
	//验证码验证  
	$(".image-code").find("img").click(function(){
		$(this).attr("src", "imageCode?_t="+new Date().getTime());
		$(".image-code").find("input[type=text]").focus();
	});
	
	$(".image-code").find(".button").click(function(){
		var code = $(".image-code").find("input[type=text]").val();
		if(code == null){
			alert(getLabelByName("chatVerifying"));
			$(".image-code").find("input[type=text]").focus();
		}else{
			chatRequest("validateCode", {
				c:companyId,
				code:code
			}, function(resp){
				if(resp.success){
					$(".image-code").hide();
					$(".mask").hide();
					connect();
				}else{
					alert(getLabelByName("chatVerifyingError"));
					$(".image-code").find("input[type=text]").focus();
				}
			});
		}
	});
}

//判断是否为手机浏览器 true为PC端，false为手机端
function isMobile() {
    var userAgentInfo = navigator.userAgent;

    var mobileAgents = [ "Android", "iPhone", "SymbianOS", "Windows Phone", "iPad","iPod"];

    var mobile_flag = false;

    //根据userAgent判断是否是手机
    for (var v = 0; v < mobileAgents.length; v++) {
        if (userAgentInfo.indexOf(mobileAgents[v]) > 0) {
            mobile_flag = true;
            break;
        }
    }

     var screen_width = window.screen.width;
     var screen_height = window.screen.height;    

     //根据屏幕分辨率判断是否是手机
     if(screen_width < 500 && screen_height < 800){
         mobile_flag = true;
     }

     return mobile_flag;
}

//判断浏览器类型
function myexplorer(){
  var explorer = window.navigator.userAgent;
  if (explorer.indexOf("QQBrowser")>=0 || explorer.indexOf("QQ")>=0){return myexplorer="腾讯QQ";
  }else if(explorer.indexOf("Safari")>=0 && explorer.indexOf("MetaSr")>=0){return myexplorer="搜狗";
  }else if (!!window.ActiveXObject || "ActiveXObject" in window){//IE
      if (!window.XMLHttpRequest){return myexplorer="IE6";
      }else if (window.XMLHttpRequest && !document.documentMode){return myexplorer="IE7";
      }else if (!-[1,] && document.documentMode && !("msDoNotTrack" in window.navigator)){return myexplorer="IE8";
     }else{//IE9 10 11
     var hasStrictMode=(function(){"use strict";return this===undefined;}()); 
     if (hasStrictMode){
         if (!!window.attachEvent){return myexplorer="IE10";}else{return myexplorer="IE11";}
     }else{
         return myexplorer="IE9";
     }
     }
 }else if(explorer.indexOf("HuaweiBrowser") >= 0){
	 return myexplorer = "华为";
 }
 // else{//非IE
 //     if (explorer.indexOf("LBBROWSER") >= 0){return myexplorer="猎豹";
 //     }else if(explorer.indexOf("360ee")>=0){return myexplorer="360极速浏览器";
 //     }else if(explorer.indexOf("360se")>=0){return myexplorer="360安全浏览器";
 //     }else if(explorer.indexOf("se")>=0){return myexplorer="搜狗浏览器";
 //     }else if(explorer.indexOf("aoyou")>=0){return myexplorer="遨游浏览器";
 //     }else if(explorer.indexOf("qqbrowser")>=0){return myexplorer="QQ浏览器";
 //     }else if(explorer.indexOf("baidu")>=0){return myexplorer="百度浏览器";
 //     }else if(explorer.indexOf("Firefox")>=0){return myexplorer="火狐";
 //     }else if(explorer.indexOf("Maxthon")>=0){return myexplorer="遨游";
 //     }else if(explorer.indexOf("Chrome")>=0){return myexplorer="谷歌（或360伪装）";
 //     }else if(explorer.indexOf("Opera")>=0){return myexplorer="欧朋";
 //     }else if (explorer.indexOf("TheWorld") >= 0){return myexplorer="世界之窗";
 //     }else if(explorer.indexOf("Safari")>=0){return myexplorer="苹果";
 //     }else{
	// 	 return myexplorer="其他";
 //     }
 // }
}

//判断当前元素是否在可是范围
function CheckIFView(id){ 
    var t=$("."+id).offset().top;    
    if (t >= $(window).scrollTop() && t < ($(window).scrollTop()+$(window).height())) {      
        return true;    
    }else{     
        return false;    
    }
}

//判断是否是url
function _isURL(str_url){
	var regexp = /^http[s]?:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/;
    if (regexp.test(str_url)){
        return true; 
    }else{ 
        return false; 
    }
};

//是否返回上一步
function pageBack(){
	var backurl = null;
	if((typeof chaturl) != "undefined" && chaturl != "" && _isURL(chaturl)){
		backurl = chaturl;
	}else if(document.referrer){
		backurl = document.referrer;
	}
	if(backurl != null){
		window.location = backurl;
	}else{
		closeWindow();
	}
}

//右侧内容切换事件
function onTabClicked(tabName){
	if(!confirm(getLabelByName('chatClosePageConfirm'))){
		return;
	}
	confirmed = true;
	exitQueue();
	
	changeTab(tabName);
	/*var params = "c=" + companyId;

	if(customerId== null || customerId=='' )
		customerId='';
		
	params = params +'&v=' + visitorId;
	params = params +'&u=' + userId;
	params = params +'&n=' + customerId;
	params = params + '&promotionId='+promotionId;
	
	params = params +'&f=' + jsConfigId;
	if(reseveKey != null&&reseveKey!='')
		params = params +'&r=' + encodeURIComponent(reseveKey);

	if(groupId!="")
		params = params +'&g=' + groupId;
		
	var cmd = '';
	if(tabName =='robot'){
		cmd = 'robotChat';
	}else if(tabName =='leaveMsg'){
		cmd = 'leaveMessage';
	}else if(tabName == 'online'){
		cmd = 'inviteChat';
	}else if(tabName == 'freecall'){
		cmd = 'freecall';
	}else{
		return;
	}
	var rpath = servletURL + "?command=" + cmd + '&' + params;
	self.location.href = rpath;*/
	
}

//清空特殊字符和空格 判断是否包含5~11位数字
function getFiveEle(str) {
	str = str.replace(/[\-\，\,\s]*/g,"");
	var regexp = /[0-9]{5,11}/gi;
	var rs = regexp.test(str);
	return rs;
}

function checkInput(){
	var stext=$('#message').val();
	if(stext == $("#message").attr("rel")){
		stext="";
	}
	if(stext!=etext){
		etext=stext;
		 if(chatId!=null){
		 	if(visitorPhoneVerification == 1 && !getFiveEle(stext) || visitorPhoneVerification == 0){
		 		sendEvent(chatId, customerId, "getfocus");
		 	}
		 }
	}
	window.setTimeout("checkInput()", 2000);
}

function chatRequest(command, params, callback, error){
	$.ajax({
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		url : "msg.do?cmd="+command+"&_t="+(new Date()).getTime(),
		type : "POST",
		data : params,
		dataType : "json",
		success : callback,
		error : error
	});
}

function sendACKResponse() {
    //addEvent("EVENT_ACK_RESPONSE", "", function(){});
}

function sendACKRequest() {
	//addEvent("EVENT_ACK_REQUEST", "", function(){});
}

function addEvent(type, message, callback){
	if(chatId){
		chatRequest('addEvent', {c: companyId, cId: chatId, v: visitorId, cusId: customerId, msg: message, type:type}, callback);
	}
}

function sendEvent(cId, vId, t) {
    if (t == "getfocus") {
	    addEvent("EVENT_GETFOCUS", etext);
    }
}

//添加翻译文字
function addEventMsg(msg) {
	addMsg(getSysStyleDiv(getLabelByName("chatSystemMessage"), msg));
}

//添加系统提示信息 （例：结束对话，断开链接）
function addMsg(msg) {
    $(msg).appendTo($('#chatDiv'));
    if(!inputView){
	    $('#chatDiv').scrollTop($('#chatDiv').scrollTop()+400);
    }else{
    	try{
 		    if(typeof onMessageTextTop != "undefined"){
 		    	onMessageTextTop();
 		    }
 	    }catch(e){}
    }
    
}
//转内容
function getLabelByName(label){
	return langConfig[label];
}
//关闭对话
function exitChat(){
	if(!isConnected){
		//window.close();
		return;
	}
	if(!confirm(getLabelByName('chatEndConfirm'))){
		return;
	}
	confirmed = true;
	endChat();
	if(isPhone()){
		addMsg(getSysStyleDiv(getLabelByName("chatSystemMessage"), getLabelByName("chatDisconnect")));
	}else{
		addMsg("<li><p class=\"cusName\">"+getLabelByName("chatDisconnect")+"</p></li>");
	}
	if(callerOpinion=='1'){
		openOpinonWin();
	}
}

//重置评价
function resetOpinionLayout(){
	$(".opinion-layout .reason").hide();
	$(".opinion-layout .score").show();
}
//提交评价
function commitOpinion(){
	if(chatId==null||chatId==""){
		alert(getLabelByName('chatNotConnect'));
		return;
	}
	if(opinioned){
		alert(getLabelByName('chatOpinioned'));
		return;
	}
	var opinion = $("#opinionValue").val();
	var desp = $("#opinionReason").val();
	if((opinion == "1" || opinion == "-1") && desp == ""){
		$(".opinion-layout .score").hide();
		$(".opinion-layout .reason").show();
	}else{
		commitOpinion2(opinion, desp, function(){
			if (!isConnected) {
				closeWindow();
			}
		});
		resetOpinionLayout();
		$(".opinion-layout").hide();
		$(".mask").hide();
	}
	
}

//提交数据至服务器
function commitOpinion2(opinion, desp, callback){
	$.getJSON('msg.do',{cmd:'opinion',cId:chatId, c:companyId, op:opinion,b_op:4, desp:desp, v:visitorId, u:userId},function(resp){
		addEvent("EVENT_OPINION", "finn");
		addMsg(getUserStyleDiv(realName, getLabelByName("chatOpinionedSuccess")));
		opinioned = true;
		if(callback){
			callback();
		}
	});
	opinioned = true;
}

//打开评价窗口
function openOpinonWin(){
	if(!opinioned && chatId!=null && chatId!='' && showToolOpinion == "1"){
		$('.pop-window').hide();
		$(".mask").show();
		$(".opinion-layout").show();
		var top = parseInt(($(window).height()-$(".opinion-layout").height())/2);
		var left = parseInt(($(window).width()-$(".opinion-layout").width())/2);
		$(".opinion-layout").css({
			top:top,
			left:left
		});
	}
}

/**
 * 开始截图
 */
function startScreenShot(){
	if (!isConnected) {
        alert(getLabelByName('chatNotConnected2'));
        return;
    }
	var p = "height=120,width=300,directories=no,location=no,menubar=no,resizeable=no,status=no,toolbar=no,top=0,left=0";
	var path = basePath;//.replace("http://", "").replace("https://", "");
	
	$("#uf").attr("src", "ScreenCat://"+path+"screenUpload.jsp?c="+companyId+"&cId="+chatId+"&v="+visitorId+"&n="+customerId);
	
	if(!notIEScreenActiveX && notIEScreenTime == null){
		notIEScreenTime = window.setTimeout(function(){
			
			notIEScreenTime = null;
		    var msg=getLabelByName("chatScreenPlugin1")+"<a href=\"jesong-screen-1.1.rar\" target=\"uf\">"+getLabelByName("chatScreenPlugin2")+"</a>"+getLabelByName("chatScreenPlugin3");
		    addEventMsg(msg);
		}, 15000);
	}
}

//判断输入是否为合法的手机号码
function isphone(inputString)
{
     var partten = /^(13[0-9]|14[5-9]|15[012356789]|166|17[0-8]|18[0-9]|19[8-9])[0-9]{8}$/;
     var fl=false;
     if(partten.test(inputString))
     {
          return true;
     }
     else
     {
          return false;
     }
}

//删除输入框前后空格
function trim(str){ 
  str=str+"";
  return str.replace(/(^\s*)|(\s*$)/g,"");
}

//判断是否为null或空
function isNullOrEmpty(prop) {
  if (prop == null || trim(prop) == "" || prop==undefined)
      return true;
  else 
      return false;
}

/**
 * 发送消息
 */
function sendMessage() {
	//存在parent页面的处理
	/*if(window.parent!=window && lazyConnectStatus == 0){
		lazyConnectStatus = 1;
		connect();
		return;
	}*/

	var msg =$("#message").val();alert(msg);
//	var phoneNum = msg.replace(/[^0-9]?/ig,"");
	var phoneNum = getValue(msg);
	var sessionBox = sessionStorage.getItem('phone');
	if(isNullOrEmpty(sessionBox)){
		sessionBox = [];
	}
	
	
	//如果开启了短信验证功能
	if(visitorPhoneVerification == 1 && isphone(phoneNum) && sessionBox.indexOf(phoneNum) == -1){
		msgCheck(msg);
		return false;
	}
	
    if (!isConnected) {
        return;
    }
    
    if(msg == $("#message").attr("rel")){
    	return;
    }
    
    var flag = sendWithMessage(msg);
    if(flag){
    	$("#message").val('');
    }
}

//判断手机号 ，清空格特殊字符
function getValue(str) {
    str = str.replace(/[\-\，\,\s]*/g,"");
    var m = str.match(/\d{11}/g);
    return (m && m[0])||'';
}

//发送消息手机号验证
var phoneArr = [];
function msgCheck(msg){
	var t;
	var msg =$("#message").val();
//	var phoneNum = msg.replace(/[^0-9]?/ig,"");
	var phoneNum = getValue(msg);
	var html = '<div class="msgBox"><div class="msgCheck">'+
					'<div class="msgCon">'+
						'<p><b><img src="'+ imgUrl +'" alt=""/></b>'+ getLabelByName("tips") +'</p>'+
						'<input type="text" placeholder="'+ getLabelByName("placeholder") +'" id="msgPhone"/>'+
						'<div class="vcodeBox">'+
							'<input type="text" />'+
							'<a href="javascript:void(0)" id="sedMsg">'+ getLabelByName("sendCode") +'</a>'+
						'</div>'+
						'<div class="tips green"></div>'+
					'</div>'+
					'<div class="msgBtn">'+
						'<a href="javascript:void(0)" id="msgCancel">'+ getLabelByName("cancel") +'</a>'+
						'<a href="javascript:void(0)" id="msgConfirm">'+ getLabelByName("confirm") +'</a>'+
					'</div>'+
				'</div></div>';
	$(".msgBox").remove();
	clearTime();
	addMsg(html);
	$("#msgPhone").val(phoneNum);
	clearTimeout(t);
	t = setTimeout(timeClick(),300000);
	windowFun();
	return false;
}

//模拟点击取消
function timeClick(){
	$("#msgCancel").click();
}

//保存手机号到待验证
function savePhone(phone){
	$.ajax({
		type: "post", 
	    async: false,
	    url: 'verify.do?c='+ companyId +'&command=saveMobile&m='+ phone +'&vsid='+ visitorId +'&cid='+ chatId +'&state=0',
	    success:function(data){
	    	var data = JSON.parse(data);
	    	if(data.code == 200){
	    		
	    	}
	    }
	});
}

//弹窗内操作
function windowFun(){
	//取消按钮
	$("#msgCancel").click(function(){
		var cancelMsg = $("#message").val();
		var phoneM = $("#msgPhone").val();
		clearTime();
		if(isphone(phoneM)){
			savePhone(phoneM);
			$("#message").val(cancelMsg);
		}
		$(".msgBox").remove();
	})
	
	//确认按钮
	$("#msgConfirm").click(function(){
		var phoneM = $("#msgPhone").val();
		var code = $(".vcodeBox input").val();
		$(".tips").addClass("red").removeClass("green");
		if(isNullOrEmpty(phoneM)){
			$(".tips").text(getLabelByName("phoneNotNull"));
		}
		if(isNullOrEmpty(code)){
			$(".tips").text(getLabelByName("codeNotNull"));
		}else if(code.length != 4){
			$(".tips").text(getLabelByName("codeError"));
		}else{
			$.ajax({
				type: "GET", 
			    async: false,
			    url: 'verify.do?c='+ companyId +'&command=verifyMobileCode&m='+ phoneM +'&vsid='+ visitorId +'&cid='+ chatId +'&state=0&code='+ code,
			    success:function(data){
			    	data = JSON.parse(data);
			    	var list = data.data;
			    	msg=$("#message").val();
			    	if(data.code == 200 && list.verify == true){
			    		var getPhone = sessionStorage.getItem('phone');
			    		phoneArr.push(list.mobile);
			    		$(".msgBox").remove();
			    		sessionStorage.setItem('phone',JSON.stringify(phoneArr));
			    		clearTime();
			    		$(".tips").text("");
			    		addMsg(getSysStyleDiv(getLabelByName("chatSystemMessage"), getLabelByName("checkSuccess")));
			    		
			    		//发送消息给客户端
			    		var flag = sendWithMessage(msg);
//			    		var flag = sendWithMessage(list.mobile);
			    	    if(flag){
			    	    	$("#message").val('');
			    	    }
			    	}else{
			    		if(list.state == 1000){
			    			$(".tips").text(getLabelByName("timeOut"));
			    		}else if(list.state == 1001){
			    			$(".tips").text(getLabelByName("codeUndefind"));
			    		}else if(list.state == 1002){
			    			$(".tips").text(getLabelByName("codeError"));
			    		}
			    	}
			    }
			});
		}
	})
	
	//验证码按钮触发
	$("#sedMsg").click(function(){
		var phoneNum = $("#msgPhone").val();
		if(isphone(phoneNum)){
			$.ajax({
				type: "GET", 
				async: true,
			    url: 'verify.do?c='+ companyId +'&command=sendVerificationCode&m='+ phoneNum + '&cid='+ chatId,
			    success:function(data){
			    	var list = JSON.parse(data);
			    	if(list.code == "200"){
			    		$(".tips").text("");
			    		getRandomCode();
			    	}else{
			    		$(".tips").removeClass("green").addClass("red").text(getLabelByName("exceed"));
			    	}
			    }
			});
		}else{
			$(".tips").addClass("red").removeClass("green");
			$(".tips").text(getLabelByName("formatError"));
		}
	})
}

//倒计时
function getRandomCode() {
   if (time === 0) { 
       time = 60;
       $("#sedMsg").removeClass("gray").text(getLabelByName("sendCode"));
       return;
   } else { 
	   $("#sedMsg").addClass("gray");
       time--;
       $("#sedMsg").text(time+getLabelByName("resetSend"));
   } 
   t = setTimeout(function() { 
       getRandomCode();
   },1000);
}

//清空计时器
function clearTime(){
	clearTimeout(t);
	time = 60;
	$("#sedMsg").removeClass("gray").text(getLabelByName("sendCode"));
}


//消息得各种验证
function sendWithMessage(msg, times){
	if(!times){
		times = 1;
	}
	if(times > 3){
		alert(getLabelByName("groupDisconnect"));
		return;
	}
	var len = msg.length;
	//超出字符限制
    if (len > maxAllowLen) {
        alert(getLabelByName('chatMsgOverLimitLen') +' '+len + '/' +  maxAllowLen );
        return false;
    }else{
    	var trim = function(str){
            return str.replace(/(^\s*)|(\s*$)/g, "");
        };
        if (msg == "" || msg == null || trim(msg) == "") {
            alert(getLabelByName('chatMsgEmptyMsg'));
            return false;
        }
        msg = replaceHtml(msg);
        var m = msg;
    	for(var e in emots){    
        	if(m.indexOf(e)!=-1){    	
        		while(m.indexOf(e)!=-1){    	
            		m = m.replace(e,emots[e]);
            	}   			  
        	}
        }
    	chatRequest('addMessage', {c: companyId, cId: chatId, v: visitorId, msg: msg, force:1}, function(resp){
    		if(resp.res == 0){
    			receiveSelfMessageNum++;
    			showChatMessage(new Date().getTime(), m, getLabelByName('you'), "visitor",null,'send');
    		}else if(resp.res == 1){
    			if(isConnected){
            		showEndMessage();
    	        	endChat();
            	}
            	receivemessageNum = 0;
            	receiveSelfMessageNum = 0;
    		}
    	}, function(XMLHttpRequest, textStatus, errorThrown){
    		$("#message").val(msg);
    		window.setTimeout(function(){
    			sendWithMessage(msg, times+1);
    		}, 500);
    	});
    	return true;
    }
}

function getCustomerName(){
	var showId = getShortId(customerId,realName);
	if(realName!=null&&!realName==""){
	  	showId=realName;
	} 
	return showId;
}

/**
 * 显示忙碌链接
 */
function showBuzyLink(){
	if($('.menu').length>0){
		$('.menu').remove();
	}else{
		if (noneWorkTime != null && noneWorkTime != "" && noneWorkTime == "true") {	
			var c = '<li id="menu"><p class="cusName">';
			c += NonWorkTimeTip;
			c += '</p>';
			c += "</li>";
	     	if(isPhone()){
	        	addMsg(getSysStyleDiv(getLabelByName("chatSystemMessage"), c));
	        }else{
	        	addMsg(c);
	        }
		}
		/*var c = '<li id="menu"><p class="cusName">';
		if (noneWorkTime != null && noneWorkTime != "" && noneWorkTime == "true") {	
			 c += NonWorkTimeTip;
		} else {
			 c += getLabelByName('chatCustomerOffline');
		}
        if(showRebot == 1){ c += '<a href="#" onclick="onTabClicked(\'robot\')">'+getLabelByName('chatToRebot')+'</a>&nbsp;&nbsp;';}
        if(showLeaveMsg ==1 ){c += '<a href="#" onclick="onTabClicked(\'leaveMsg\')">'+getLabelByName('chatToLeaveMsg')+'</a>';}
        c += '</p>';
        c += "</li>";
        if(waitingQueue){
           c='<li><p class="cusName">'+getLabelByName("chatContinueWait")+'&nbsp;&nbsp;';
           if(showRebot == 1){
        	   c+='<a href="#" onclick="onTabClicked(\'robot\')">'+getLabelByName('chatToRebot')+'</a>&nbsp;&nbsp;' ;
           }
           if(showLeaveMsg ==1){
        	   c += '<a href="#" onclick="onTabClicked(\'leaveMsg\')">'+getLabelByName('chatToLeaveMsg')+'</a>';
           }
           c += "</p></li>";
        }
        if(isPhone()){
        	addMsg(getSysStyleDiv(getLabelByName("chatSystemMessage"), c));
        }else{
        	addMsg(c);
        }*/
    }
}

//检查确认时间   暂未用到------------------------------------------------------------------------
function checkACKTime() {
    var now = new Date();
    if (isConnected&&sendACKTime == null && (now.getTime() - lastMessageTime.getTime()) / 1000 > keepAlivePeriod) {
        sendACKRequest();
        sendACKTime = new Date();
    }
    if (sendACKTime != null && (now.getTime() - sendACKTime.getTime()) / 1000 > maxReplyWaitTime) {
       sendACKTime = null;
       lastMessageTime = new Date();
        return;
    }
}

function logger(msg){
	/*if(typeof console != "undefined" && console.log){
		console.log(msg);
	}*/
}

var lastGetMessageTime;
var receivemessageNum = 0;
var receiveSelfMessageNum = 0;
var firstGetMessage = true;
var sendWaitSendMessage = false;

/**
 * 获取消息
 */
function getMessage(){
	var params = {
	   c: companyId,
	   v: visitorId,
	   u: userId,
	   cId: chatId
    };
	var now = new Date().getTime();
	if(lastGetMessageTime != null && now - lastGetMessageTime < 6000){
		return;
	}
	params["start"] = receivemessageNum;
	params["vstart"] = receiveSelfMessageNum;
	var s = receivemessageNum;
	var s1 = receiveSelfMessageNum;
	lastGetMessageTime = now;
	//checkACKTime();
	chatRequest('getMessage', params, function(resp){
		lastGetMessageTime = null;
		if(s == receivemessageNum && s1 == receiveSelfMessageNum){
			parseMessages(resp.msgs || [], resp.fromId);
			//receivemessageNum = receivemessageNum + resp.msgs.length;
		}else{
			getMessage();
		}
		
		if(firstGetMessage && sendWaitSendMessage){
			sendWithMessage(waitSendMessage);
		}
		
		firstGetMessage = false;
		
    });
	
}

/**
 * 开始获取消息
 */
function startGetMessage(){
	window.getMessageInterval = window.setInterval("getMessage()", 3000);
	window.setTimeout(function(){
		getMessage();
	}, 1000);
}

function showEndMessage(){
	 if(isConnected){
	        if(!msgOfDisConnected || msgOfDisConnected == ""){
	        	addEventMsg(getLabelByName('chatCustomerEndChat'));
	        }else{
				addEventMsg(msgOfDisConnected);
			}
	        if(isPhone()){
	        	addEventMsg(getLabelByName('continueChat1') + "<a href=\"javascript:void(0);return false;\" onclick=\"window.location.reload();return false;\">"+getLabelByName('continueChat2') +"</a>"+ getLabelByName('continueChat3'));
	        }
     }
}
//解析消息
function parseMessages(msgs,id){
    needFocus = false;
    var playSound = false;
    lastMessageTime = new Date();
    sendACKTime = null;
    for (var i=0; i<msgs.length; i++) {
    	var msg = msgs[i];
    	var target = "servicer";
    	var showId = "";
    	if(msg.fromUserId == visitorId){
    		receiveSelfMessageNum++;
			showId = getLabelByName('you');
			target = "visitor";
		}else{
			receivemessageNum++;
			showId = getShortId(customerId,realName);
			if(realName!=null&&realName!=""){
		    	showId=realName;
		    } 
		}
        if (msg.type == "EVENT_END") {
        	if (isConnected) {
        		showEndMessage();
        		endChat();
                needFocus = true;
                playSound = true;
            }
        	receivemessageNum = 0;
        	receiveSelfMessageNum = 0;
        } else if(msg.type == "EVENT_CLOSE") {
        	if(isConnected){
        		showEndMessage();
	        	endChat();
        	}
        	receivemessageNum = 0;
        	receiveSelfMessageNum = 0;
        } else if(msg.type == "EVENT_SAMEVISITOR"){
            addEventMsg(getLabelByName("chatAnother"));
            endChat();
    	    isConnected = false; 
        } else if (msg.type == "EVENT_ACCEPT") {
        } else if (msg.type == "EVENT_TRANSCHAT") {				
            customerId = msg.exts.customerId;    
            chatId = msg.exts.chatId;  
             
            receivemessageNum = 0;
        	receiveSelfMessageNum = 0;
            if(customerId.indexOf("/")>0){
            	customerId = customerId.substring(0,customerId.indexOf("/"));
            }
            
            var hidden = msg.exts.hidden;
            if(!hidden){
	            loadCustomerInfo();
	            realName = msg.exts.nickName; 
	            if(msgOfTrans) addEventMsg(msgOfTrans);
	            addEventMsg(getLabelByName('chatTransChatTo')+'<span class="svcName">' + realName + "</span>");
	            needFocus = true;
	            transHidden = false;
	            playSound = true;
            }else{
            	transHidden = true;
            }
            isConnected = false;
         } else if (msg.type == "EVENT_TRANSCHAT_SUCCESS") {
        	if(!isConnected){
        		if(!transHidden){
        			addEventMsg(getLabelByName('chatTransSuccess'));
        			needFocus = true;
        			 playSound = true;
        		}
                isConnected = true;
                //Customer_getCustomer(getCustomer_do,customerId,companyId);
          	}
        } else if (msg.type == "RECORD_FILE") {            
        	if(target == "visitor"){
        		if((msg.message.lastIndexOf("png") != -1 || msg.message.lastIndexOf("jpg") != -1 || msg.message.lastIndexOf("gif") != -1) || isPhone()){
        			showChatMessage(msg.createTime, msg.message, getLabelByName('you') , 'visitor',"image","send");//访客端显示图片
        		}else{
        			addEventMsg(getLabelByName('chatUploadFileSuccess'));
        		}
        	}else{
        		showChatMessage(msg.createTime, msg.message,showId, target,'file');   
        	}
            needFocus = true;
            playSound = true;
        } else if (msg.type == "EVENT_ACK_REQUEST") {
            sendACKResponse();
        } else if (msg.type == "EVENT_ACK_RESPONSE") {
        } else if(msg.type == "EVENT_GETFOCUS") {
//        	if(!firstGetMessage && inputText == 1){
        		showCustInputing();
//        	}
        } else if(msg.type == "RECORD_SCREENSHOTS"){
     		showChatMessage(msg.createTime, msg.message,showId, target,'image');
     		needFocus = true;
     		playSound = true;
        } else if(msg.type == "EVENT_SCREENSHOTS_SELF"){
        	notIEScreenActiveX = true;
        	if(notIEScreenTime){
        		window.clearTimeout(notIEScreenTime);
        	}
        	var imgUrl = msg.message;
        	showChatMessage(msg.createTime, imgUrl, getLabelByName('you') , 'visitor',"image","send");//访客端显示图片
        } else if(msg.type == "EVENT_OPINION"){   
        	var m = '<a href="javascript:void(0)" onclick="openOpinonWin();return false;">'+getLabelByName('chatOpinionReq') +'</a>';
        	needFocus=true;
        	playSound = true;
        	addEventMsg(m);
        }else if(msg.type == "RECORD_RICH_TEXT"){
        	var mode = 'receive';
            showChatMessage(msg.createTime, msg.message,showId, target,null,mode);
            needFocus = true;
            playSound = true;
            if (document.hasFocus) {
            	if (document.hasFocus()) {
             		needFocus=false;
              	}
            }
        }else if(msg.type == "RECORD_RECORD"){ //普通信息查收
        	var mode = 'receive';
            showChatMessage(msg.createTime, msg.message,showId, target,null,mode);
            needFocus = true;
            playSound = true;
            if (document.hasFocus) {
            	if (document.hasFocus()) {
             		needFocus=false;
              	}
            }
         }
    }
    if (needFocus) {
        window.focus();       
        flash_title();
    }
    if(playSound && !firstGetMessage && !quietFlag){
    	playAudio();
    }
}

//来消息声音
function playAudio(){
	var wav = "./wav/sound.wav";
	var borswer = window.navigator.userAgent.toLowerCase();  
	try{
		if (borswer.indexOf( "ie" ) >= 0 ){
			$("#jesong_sound").empty().html('<bgsound src="'+wav+'"/>'); 
		}else{
			if(document.getElementById("jesong_audio").paused){
				document.getElementById("jesong_audio").play();
			}
		}
	}catch(e){
	}
	try{
		 if(navigator.vibrate) {
			 navigator.vibrate(500);
		 }else if(navigator.webkitVibrate) {
		 	navigator.webkitVibrate(500);
		 }
	}catch(e){
	}
}

var custInputingTimeout = -1;

function clearCustInputing(){
	$("#jesong_inputing").remove();
}

function showCustInputing(){
	clearCustInputing();
	var msgHtml = getInputingStyleDiv(realName);
	addMsg(msgHtml);
}



/**
 * 自动关闭对话
 */
function autoCloseChat(){
	autoCloseRepeat++;
	if(V_SEND_MSG_IN_AUTOCLOSE_PROCESS==false && autoCloseRepeat>CycleTimeOfToVisitorMsg*(SendTimesOfToVisitorMsg+1) && SendTimesOfToVisitorMsg<TimesOfToVisitorMsg){
		SendTimesOfToVisitorMsg++;
	    var clsTag = null;
	    var clsMsgText = null;
	    var clsName = null;   
	  	
	    clsMsgText = "cusTxt";
	    clsName = "cusName";      
	    var msg=ReplyMsgOfCustToVisitor;
		var say=getLabelByName('say');
		
		var msgHtml = getUserStyleDiv(getLabelByName("chatSystemMessage"), replacelinkHtml(msg));
		
	   	addMsg(msgHtml);
	}
	if(SendTimesOfToVisitorMsg>=TimesOfToVisitorMsg && autoCloseRepeat-CycleTimeOfToVisitorMsg*TimesOfToVisitorMsg>ChatAutoCloseTime){
		var msgHtml = "";
        var msg=ChatAutoCloseMsg;
        addEventMsg(msg);
	    systemclose=true;
	   	isConnected=false;
	   	endChat();
	   	window.clearTimeout(auto_closeChat_timer);
	}
}

//接受展示图片
function showImage(target){
	$(".mask").show();
	var url = target.src;
	var _div=$("<div style=\"position:absolute;top:0px;left:0px;z-index:99999;background-color:#ffffff;\"></div>").appendTo($("body")).click(function(){
		$(this).remove();
		$(".mask").hide();
	});
	var _img = $("<img/>").attr("src", url);
	$(_img).load(function(){
		var imgW = $(this).width();
		var imgH = $(this).height();
		var windowW = $(window).width();
		var windowH = $(window).height();
		$(_div).css({
			"width": imgW>windowW ? windowW-20 : imgW,
			"height": imgH>windowH ? windowH-20 : imgH,
			"left":10,
			"top":imgH>windowH ? 10 : (windowH-imgH)/2,
			"overflowX": imgW>windowW ? "auto" : "hidden",
			"overflowY": imgH>windowH ? "auto" : "hidden"
		});
		$(_div).animate({
			"left":imgW>windowW ? 10 : (windowW-imgW)/2,
		}, 300);
		/*$(this).css({
			"width":imgW,
			"height":imgH
		});*/
	}).appendTo($(_div));
}

var imageGenId = 1;

//========================================================================================================================================

/**
 * 显示消息
 */
function showChatMessage(time,msg,who,r,type,mode){
	
	if(msg==null || msg==''){
		return;
	}
	msg = msg.replaceAll("\n", "<br>");
	time = (time ? date2Text(time, "yyyy-MM-dd hh:mm:ss") : getTimeString());
	if(who==getLabelByName('you') && mode=='send' && (autoCloseRepeat<CycleTimeOfToVisitorMsg || autoCloseRepeat-CycleTimeOfToVisitorMsg*TimesOfToVisitorMsg<ChatAutoCloseTime)) {
	   V_SEND_MSG_IN_AUTOCLOSE_PROCESS=true;
	   autoCloseRepeat=0;
	   SendTimesOfToVisitorMsg=0;
	   window.clearTimeout(auto_closeChat_timer);
	   auto_closeChat_timer = null;
	}
	if(msg==ReplyMsgOfCustToVisitor){  
	    //alert("启动访客长时间不回复处理机制。。");
	    V_SEND_MSG_IN_AUTOCLOSE_PROCESS=false;
	    if(auto_closeChat_timer == null){
	    	auto_closeChat_timer = window.setInterval("autoCloseChat()", 1000);
	    }
	}

	var msgHtml = "";
    var clsMsgText = null;
    var clsName = null;   
    if (r == "visitor") {
        clsMsgText = "sTxt";
        clsName = "sName";
    } else {    	
        clsMsgText = "cusTxt";
        clsName = "cusName";        
    }
    //访客端预知输入
	if (r == "visitor") {
		window.setTimeout(function(){
			showCustInputing(); //展示正在输入
		}, 2000);
	}else{
		clearCustInputing();
	}
    var say = (type=='image')?getLabelByName('chatSendImage'):((type=='file')?getLabelByName('chatSendFile'):getLabelByName('say'));
	if(type == 'image'){
		var id = "screen_"+ imageGenId++;
		var img = new Image();
		if(!msg.indexOf("https") == 0){
			msg = msg.replace("http", scheme);
			msg = msg.replace(":80", ":"+schemePort);
		}
		img.onload=function(){
			var w = img.width;
			var h = img.height;
			var m = w>h?w:h;
			var step = 1;			
			if(m>100){
				while(m/(++step)>100);
			}
			var preW = w/step;
			var preH = h/step;	
			$("#"+id).css({
				width:parseInt(preW),
				height:parseInt(preH)
			});
		};
		var msgHtml = '<img id=\"'+id+'\" src="' + msg + '" width="100px" height="100px" border="0" onclick="showImage(this)"/>';
		if (r == "visitor") {
			msgHtml =getVisitorStyleDiv((msgHtml));
		}else{
			msgHtml =getUserStyleDiv(who,(msgHtml));
		}
		addMsg(msgHtml);
		
		img.src = msg;
		return;			
	}else if(type=='file'){
		if(!msg.indexOf("https") == 0){
			msg = msg.replace("http", scheme);
			msg = msg.replace(":80", ":"+schemePort);
		}
		var msgHtml = getLabelByName("chatCustomerFile")+"<a href=\"" + msg + "\" target=\"_blank\">" + getLabelByName('chatFileDownload') + "</a>";
		if (r == "visitor") {
			msgHtml =getVisitorStyleDiv((msgHtml));
		}else{
			msgHtml =getUserStyleDiv(who,(msgHtml));
		}
		addMsg(msgHtml);
		
        return;
	}
	var tarName = who;
	
	var msgHtml = "";
	if (r == "visitor") {
		msgHtml =getVisitorStyleDiv(replacelinkHtml(msg));
	}else{
		msgHtml =getUserStyleDiv(tarName,replacelinkHtml(msg));
	}
	addMsg(msgHtml);
	
}

function getPhoneTime(v){
	var now = new Date();
	if (typeof v == 'number'){
		now.setTime(v);
	}
	var h = now.getHours();
    var mm = now.getMinutes();
    return (h>9 ? h : "0"+h)+":"+(mm > 9 ? mm : "0"+mm );
}

//获取用户样式
function getUserStyleDiv(userName, msg){
	return "<div class=\"service-bubble\">"+
				(popStyle == "1" || isPhone() ? "<div class=\"service-bubble-icon\"><img src=\""+userHead+"\"/></div>" : "")+
				"<div class=\"service-bubble-con\">"+
					"<div class=\"service-session\"><span>"+userName+"</span><span>"+getPhoneTime()+"</span></div>"+
			    		"<div class=\"service-session-con\">"+msg+"</div>"+
			    	"</div>" +
			    "</div>";
}

//获取输入区默认样式
function getInputingStyleDiv(userName){
	return 	"<div id=\"jesong_inputing\" class=\"service-bubble\">"+
				(popStyle == "1" || isPhone() ? "<div class=\"service-bubble-icon\"><img src=\""+userHead+"\"/></div>" : "")+
    			"<div class=\"service-bubble-con\">"+
	    			"<div class=\"service-session\"><span>"+userName+"</span><span>"+getPhoneTime()+"</span></div>"+
		    		"<div class=\"service-session-con\"><img src=\"static/2019/images/inputting.gif\" alt=\"\" style=\"width:14px;height:14px;\"/><span style=\"line-height:14px;padding-left:4px;\">"+langConfig["inputting"]+"</span></div>"+
		    	"</div>" +
		    "</div>";
}

//获取系统提示样式
function getSysStyleDiv(who, msg){
	return  "<div class=\"finish-bubble\"><div>"+msg+"</div></div>";
}

//获取客服信息
function getVisitorStyleDiv(msg){
	return "<div class=\"service-bubble\">" +
				(popStyle == "1" || isPhone() ? "<div class=\"service-bubble-icon\" style=\"visibility:hidden;\"></div>" : "")+
                "<div class=\"service-bubble-con\">"+
                	"<div class=\"service-session "+(popStyle == "1" || isPhone() ? "float-right" : "")+"\"><span>您</span><span>"+getPhoneTime()+"</span></div>"+
                    "<div class=\"service-session-con visitor-session-con "+(popStyle == "1" || isPhone() ? "float-right" : "")+"\" style=\"background-color:"+bgColor+";border:1px solid "+bgColor+";\">"+msg+"</div>"+
                "</div>"+
            "</div>";
}
/**
 * 结束对话
 */
function endChat() { 
	exitQueue();
	if(window.getMessageInterval) window.clearInterval(window.getMessageInterval);
	if(chatId != null && chatId!=''){
		/*var params = {c: companyId, cId: chatId, v: visitorId};
		if(systemclose == true){
			params =  $.extend({auto:1}, params);
		}*/
		var params = "c="+companyId+"&g="+groupId+"&v="+visitorId+"&cId="+chatId+"&u="+userId;
		if(systemclose == true){
			params += "&auto=1";
		}
		var img = $("<img style=\"width:0px;height:0px;border:0;\"/>").appendTo($("body"));
		$(img).attr("src", "msg.do?cmd=endChat&"+params+"&_t="+new Date().getTime());
		var phoneM = $("#msgPhone").val();
		savePhone(phoneM);
		/*var params = {
			"phone":phoneM
		};
		$.ajax({
			type: "post", 
		    async: false,
		    url: 'msg.do?cmd=endChat&_t='+(new Date()).getTime(),
		    data: params,
		    success:function(){}
		});*/
	}
	isConnected = false;
}

function exitQueue(){
	if(waitingQueue){
		var img = $("<img style=\"width:0px;height:0px;border:0;\"/>").appendTo($("body"));
		$(img).attr("src", "msg.do?cmd=exitQueue&c="+companyId+"&g="+groupId+"&v="+visitorId+"&_t="+new Date().getTime());
	}
}

//显示访客信息
function showCustomerInfo(resp){
	$("#custName").html(resp.nickName);
	$("#custDept").html(resp.department);
	if(resp.phoneNumber){
		$("#custTel").html(resp.phoneNumber);
	}
	if(resp.mail){
		$("#custMail").html(resp.email);
	}
	if(resp.customerCardImg && resp.customerCardImg != ""){
		$("#custHead").attr("src", resp.customerCardImg);
	}
}

function isPhone(){
	if(typeof msgStyle != 'undefined' && msgStyle == "phone"){
		return true;
	}else{
		return false;
	}
}

/**
 * 开始对话
 */
function startChat(cId){
	
	if(!isPhone()){
		addEventMsg('<span class="svcName">' + getShortId(customerId,realName) + '</span>&nbsp;'+getLabelByName('chatConnectSuccess'));
	}
	chatId = cId;
	connectSuccess();
	startGetMessage();
}

function getFocus() {
    if (needFocus) {
        needFocus = false;
        return;
    }
    if(chatId!=null){
  	  	sendEvent(chatId, customerId, "getfocus");
    }
}

//文件传输
function transferFile() {
	if (!isConnected) {
        alert(getLabelByName('chatNotConnected2'));
        return;
    }
	var fileFullPath = uploadForm.file.value;
    if(fileFullPath==''){
    	alert(msg_select_trans_file);
    	return false;
    }
    var fileType = fileFullPath.substring(fileFullPath.lastIndexOf('.'));
    var url = './receive.jsp?fileType='+fileType+'&chatId='+chatId+'&cId='+companyId;
   	$(uploadForm).attr('action',url);
    uploadForm.submit();
}

function uploadfinish(fname) {
	sendFileReq(fname);
	/*if(isPhone()){
		//showChatMessage(new Date(), fname, getLabelByName('you') , 'visitor',"image","send");//访客端显示图片
	}else{
		//addEventMsg(getLabelByName('chatUploadFileSuccess'));
	}*/
	//$.js.hideSinglePop();
	$("#uploadMsgDiv").html(getLabelByName('chatUploadFileSuccess'));
	$(".pop-window").hide();
}

function uploadErr(errorstr) {
    addEventMsg(getLabelByName('chatUploadErrorSize')+ '<p class=\"cusTxt\">'+getLabelByName('chatUploadErrorType'));
}

function sendFileReq(fileName) {
    addEvent("RECORD_FILE", fileName);
}

/**
 * 创建对话成功
 */
function connectSuccess(){
	isConnected = true;
	hideLayer('menu','hide');
	var img = topImage;
	var greeting = welcomeMsgOfConnected;
	if(isPhone()){
		greeting = welcomeMsgOfConnectedMin ? welcomeMsgOfConnectedMin : welcomeMsgOfConnected;
		img = topImageMin ? topImageMin : topImage;
	}
	
	if(topImage != ""){
		addMsg('<div class="logo-bubble" style="'+(isPhone() ? "margin-left:10px;margin-right:10px;margin-top:0px;margin-bottom:0px;" : "")+'"><img src="'+img+'" alt=""/></div>');
	}
	if(greeting != ""){
		if(tagMessage){
			addMsg(getUserStyleDiv(realName, tagMessage));
		}else{
			addMsg(getUserStyleDiv(realName, greeting));
		}
    }
	if(sendkeyWord == 1 && keyWord){
		var reg = /[\u4e00-\u9fa5]/g;
		if(reg.test(keyWord)){
			showChatMessage(new Date().getTime(), keyWord, getLabelByName('you'), "visitor",null,'send');
			chatRequest('addMessage', {c: companyId, cId: chatId, v: visitorId, msg: keyWord, force:1}, function(){
				receiveSelfMessageNum++;
			});
		}
	}
    /*if(replyMsgAtConnected && replyMsgAtConnected != ""){
    	showChatMessage(replyMsgAtConnected, getShortId(customerId,realName), "servicer",null, "receive");
    }*/
}

//等待列队
function waitQueue(){
	waitingQueue = true;
	chatRequest('waitQueue', {
		c:companyId,
		v:visitorId,
		u:userId,
		g:groupId,
		tag:tag
	}, handlerWaitQueue);
}

//处理等待列队
function handlerWaitQueue(rtn){
	if(rtn==null){
		alert(getLabelByName('chatGroupNoCustomer'));
		waitingQueue = false;
		return;
	}
	if(rtn.type == "OFFLINE"){
		addEventMsg(getLabelByName("chatCustomerOffline"));
		waitingQueue = false;
		return;
	}
	if(rtn.type == "CHATING"){
		waitingQueue = false;
		$("#chatDiv").empty();
		setChatInfo(rtn);
		startChat(chatId);
	}else{
		if($('#waitQueueDiv').length==0){
			addEventMsg('<p class="cusName" id="waitQueueDiv"></p>');
		}
		var i=rtn.waitIndex;
		if(waitingQueueTimes<1){i--;}
		if(waitText){
			$('#waitQueueDiv').html(waitText.replace('{queueIndex}', i)+waitingQueueTimes);
		}else{
			$('#waitQueueDiv').html(getLabelByName("chatWaitQueue1")+'<font color="red">'+ i + '</font>'+getLabelByName("chatWaitQueue2")+waitingQueueTimes);
		}
		waitingQueueTimes ++;
		if(waitingQueueTimes==2) showBuzyLink();
		window.setTimeout('waitQueue()', 1000);
	}
}

//获取客服信息
function loadCustomerInfo(){
	chatRequest('getCustomer', {
		c:companyId,
		cId:customerId
	}, function(rtn){
		showCustomerInfo(rtn.userInfo);
		
		replyMsgAtConnected = rtn.userInfo.replyMsgAtConnected;
		ReplyMsgOfCustToVisitor = rtn.userInfo.replyMsgOfCustToVisitor;
		ChatAutoCloseTime = rtn.userInfo.chatAutoCloseTime;
		TimesOfToVisitorMsg = rtn.userInfo.timesOfToVisitorMsg;
		CycleTimeOfToVisitorMsg = rtn.userInfo.cycleTimeOfToVisitorMsg;
		ChatAutoCloseMsg = rtn.userInfo.chatAutoCloseMsg;
	});
}

//设置聊天信息
function setChatInfo(rtn){
	chatId=rtn.chatId;
	
	if(rtn.groupId){
		groupId = rtn.groupId;
	}
	
	customerId = rtn.userInfo.userId;
	realName = rtn.userInfo.nickName;
	
	replyMsgAtConnected = rtn.userInfo.replyMsgAtConnected;
	ReplyMsgOfCustToVisitor = rtn.userInfo.replyMsgOfCustToVisitor;
	
	ChatAutoCloseTime = rtn.userInfo.chatAutoCloseTime;
	TimesOfToVisitorMsg = rtn.userInfo.timesOfToVisitorMsg;
	CycleTimeOfToVisitorMsg = rtn.userInfo.cycleTimeOfToVisitorMsg;
	ChatAutoCloseMsg = rtn.userInfo.chatAutoCloseMsg;
	
	tagMessage = rtn.userInfo.tagMessage;
	
	showCustomerInfo(rtn.userInfo);
	
	keyWord = rtn.keyWord;
	
	if(!rtn.isChatExists && waitSendMessage != ""){
		sendWaitSendMessage = true;
	}
}

var connecting = false;

/**
 * 开始对话
 */
function connect(){
	if(connecting == true || isConnected){
		return;
	}
	connecting = true;
	receivemessageNum = 0;
	if (noneWorkTime != null && noneWorkTime != "" && noneWorkTime == "true") {						
		showBuzyLink();
		return;
	}
	
	var keys = ["chat_url"];
	var vals = [document.referrer];
	var params = {
		v:visitorId,
		u:userId,
		n:customerId,
		userId:userId,
		c:companyId,
		ext:reseveKey,
		keys: keys,
		values: vals,
		promotionId : promotionId,
		tag:tag,
		ref:referPage,
		ocpcPlatform:ocpc.platform,
		ocpcCondition:ocpc.condition,
		ocpcConfigId:ocpc.configId
	};
	if(chatId != null && chatId != ''){
		params.cId = chatId;
	}
	if(groupId){
		params["g"] = groupId;
	}
	if(typeof chatType != undefined){
		params["chatType"] = chatType;
	}
	params["sid"] = siteId;
	chatRequest("chat", params, function(rs){
		if(rs.groupId){
			groupId = rs.groupId;
		}
		if(rs.type == "WAIT_QUEUE"){
			if($('#waitTime').length==0){
				addEventMsg(getLabelByName('chatInWaitQueue') + '<span id="waitTime"></span>');
			}
			handlerWaitQueue(rs);
		}else if(rs.type == "CHATING"){
			if(rs.visitorId){
				visitorId = rs.visitorId;
			}
			chatId=rs.chatId;
			setChatInfo(rs);
			startChat(chatId);
		}else if(rs.type == "chatExists"){
			addEventMsg(getLabelByName('chatExists'));
		}else if(rs.type == "validateError"){
			showImageCode();
		}else if(rs.type == "ERROR"){
			alert("配置错误导致创建对话失败，请检查相应的配置");
		}else{
			addEventMsg('<li><p class="cusName">'+getLabelByName("chatCustomerOffline")+'</p></li>');
			if($('#waitAgain').length>0){
		     	$('#waitAgain').hide();
			}
		}
		connecting = false;
	});
}

//图层显示或隐藏
function hideLayer(layerName,args){
	if(args == 'show'){
		$('#'+layerName).show();
	}else if(args == 'hide'){
		$('#'+layerName).hide();
	}
}

function initEmot(key,img)
{

}

/**
 * 添加表情到输入框
 * @param key
 * @param img
 */
function appendEmot(key,img){
	if(emots[key]==null)
		emots[key] = '<img src="' + img + '" border="0" />';
	if($("#message").val() == $("#message").attr("rel")){
		$("#message").val(key);
	}else{
		$('#message').val($('#message').val()+key);
	}
	//$.js.hideSinglePop();
	var obj = document.getElementById('message');
	obj.focus();
	var len = obj.value.length;
	if (document.selection) {
		var sel = obj.createTextRange();
		sel.moveStart('character', len);
		sel.collapse();
		sel.select();
	} else if (typeof obj.selectionStart == 'number'
			&& typeof obj.selectionEnd == 'number') {
		obj.selectionStart = obj.selectionEnd = len;
	}
}

function flash_title(){  
	if(windowFocus==false){
      unReadMsgStep++;
      if (unReadMsgStep==3) {unReadMsgStep=1;}   
      if (unReadMsgStep==1) {document.title=getLabelByName("chatNewMessage");}   
      if (unReadMsgStep==2) {document.title=commonTitle;} 
      if(flashtitle==-1){
      	flashtitle=window.setInterval("flash_title()", 500); 
      }
     }
}
function clear_title(){
	document.title=commonTitle;
	if(flashtitle==-1){
		return;
	}
	window.clearInterval(flashtitle);
	flashtitle=-1;
}

//显示验证码  
function showImageCode(){
	$(".mask").show();
	_wH = $(window).height(),
	$(".image-code").css({
		left: ($(window).width() - $(".image-code").width())/2,
		top: ($(window).height() - $(".image-code").height())/2
	}).show();
	$(".image-code").find("img").click();
	$(".image-code").find("input[type=text]").focus();
}

$(function(){
	var oHeight = $(document).height(); //浏览器当前的高度   
   $(window).resize(function(){
        if($(document).height() < oHeight){
        $("#footer").css("position","static");
    }else{
        $("#footer").css("position","absolute");
    }
   });

	
	initUI();
	//无父窗口
	//if((window.parent==window || ((typeof frameFlag) != "undefined" && frameFlag == true)) && !validateFlag){
		connect();
	/*}else{
		showImageCode();
	}*/
	
	
	var backurl = null;
	if((typeof chaturl) != "undefined" && chaturl != "" && _isURL(chaturl) && isPhone()){
		backurl = chaturl;
	}else if(document.referrer && isPhone()){
		backurl = document.referrer;
	}
	
	window.onbeforeunload = function(){};
	window.onunload = function(){};
	
	
	window.onblur=function(){
		windowFocus=false;
	};

	window.onfocus= function(){
		windowFocus=true;
		clear_title();
	};
});