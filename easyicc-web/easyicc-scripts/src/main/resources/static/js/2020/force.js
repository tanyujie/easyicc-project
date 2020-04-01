(function(){
	String.prototype.replaceAll = function (AFindText,ARepText){
		raRegExp = new RegExp(AFindText,"g");
		return this.replace(raRegExp,ARepText);
	};
	function replacelinkHtml(s){
		var reg = /(http:\/\/|https:\/\/|ftp:\/\/|www)((?:\&amp;|[\w\.\:\/=%_\-,~\?\&\*])*)/g;
	    s = s.replace(reg, function($0, $1, $2) {
	        $2 = $2.replace(/\&amp;/g, function(s) {
	            return '&';
	        });
	        var u = ($1 == 'www' ? 'http://www' : $1) + $2;
	        return '<a class="linkText" href="' + u + '" target="_blank">' + u + '</a>';
	    });
	    return s;
	}
	function replaceEmoticon(msg){
		var reg = /\[\d\d\]/g;
		return msg.replace(reg, function(a, b, c){
			return "<img src=\""+jesong.env.server.file+"/emoticon/"+a.substring(1, 3)+".png\"></img>";
		});
	}
	function replaceHtml(msg){
		var string = msg+"";
		string = string.replaceAll("&", "&amp;" );
		string = string.replaceAll("<", "&lt;" );
		string = string.replaceAll(">", "&gt;" );
		string = string.replaceAll("\r", "" );
		string = string.replaceAll("\n", " " );
		string = string.replaceAll("\"", "&quot;" );
		return string;
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
	
	//判断手机号 ，清空格特殊字符
	function getValue(str) {
	    str = str.replace(/[\-\，\,\s]*/g,"");
	    var m = str.match(/\d{11}/g);
	    return (m && m[0])||'';
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
	
	//清空特殊字符和空格 判断是否包含5~11位数字
	function getFiveEle(str) {
		str = str.replace(/[\-\，\,\s]*/g,"");
		var regexp = /[0-9]{5,11}/gi;
		var rs = regexp.test(str);
		return rs;
	}
	
	//保存手机号
	function savePhone(phone){
		JS.ajax(Chat.screenUrl+'verify.do?c='+ Chat.companyId +'&command=saveMobile&m='+ phone +'&vsid='+ Chat.visitorId +'&cid='+ Chat.chatId +'&state=0','',function(){})
	}
	var JS = function(selector) { 
		if ( window == this ){return new JS(selector);};
		this.dom = document.getElementById(selector);
		return this;
	};
	JS.prototype = {
		exist : function(){
			if(this.dom){
				return true;
			}else{
				return false;
			}
		}, html : function(html){
			if(html && this.dom){
				this.dom.innerHTML = html;
			}else if(this.dom){
				return this.dom.innerHTML;
			}else{
				return "";
			}
		}, bind : function(event, fn){
			if(this.dom){
				if(fn){
					if(this.dom.attachEvent){
						this.dom.attachEvent("on"+event, function(e){
							return fn.apply(Chat, [e]);
						});
					}else if(this.dom.addEventListener){
						this.dom.addEventListener(event, function(e){
							return fn.apply(Chat, [e]);
						});
					}
				}else{
					if(document.all){    
						this.dom.click();    
					}else{    
						var evt = document.createEvent("MouseEvents");    
						evt.initEvent(event, true, true);    
						this.dom.dispatchEvent(evt);    
					}    
				}
			}
			return this;
		}, unbind : function(event){
			if(this.dom){
				if(this.dom.attachEvent){
					this.dom.attachEvent("on"+event, null);
				}else if(this.dom.addEventListener){
					this.dom.addEventListener(event, null);
				}
			}
		}, click : function(fn){
			return this.bind("click", fn);
		}, focus : function(fn){
			if(fn){
				return this.bind("focus", fn);
			}else{
				this.dom.focus();
				return this;
			}
		}, hover : function(fn1, fn2){
			this.bind("mouseover", fn1);
			this.bind("mouseout", fn2);
			return this;
		}, blur : function(fn){
			if(fn){
				return this.bind("blur", fn);
			}else{
				this.dom.blur();
				return this;
			}
		}, keydown : function(fn){
			return this.bind("keydown", fn);
			/*this.dom.onkeydown = function(e){
				return fn.apply(Chat, [e]);
			};
			return this;*/
		}, hasClass : function (cls) { 
			if(this.dom){
				return this.dom.className.match(new RegExp('(\\s|^)'+cls+'(\\s|$)')); 
			}else{
				return false;
			}
		}, addClass : function(cls) { 
			if(this.dom){
				if (!this.hasClass(cls)){
					this.dom.className += " "+cls; 
				}
				return this;
			}
		}, css : function(key, value){
			if(this.dom){
				this.dom.style[key] = value;
				return this;
			}
		}, width : function(){
			if(this.dom){
				return this.dom.offsetWidth;
			}else{
				return 0;
			}
		}, height : function(){
			if(this.dom){
				return this.dom.offsetHeight;
			}else{
				return 0;
			}
		}, removeClass : function(cls) { 
			if (this.hasClass(cls)) { 
				var reg = new RegExp('(\\s|^)'+cls+'(\\s|$)'); 
				this.dom.className=this.dom.className.replace(reg,' '); 
			}
		}, scrollTop : function(top){
			if(top != undefined){
				this.dom.scrollTop = top;
			}else{
				return this.dom.scrollTop;
			}
		}, hide : function(){
			if(this.dom){
				this.dom.style.display="none";
			}
			return this;
		}, isHidden : function(){
			if(this.dom){
				return this.dom.style.display == "none";
			}
		}, show : function(){
			if(this.dom){
				this.dom.style.display="block";
			}
			return this;
		}, remove : function(){
			if(this.dom){
				this.dom.parentNode.removeChild(this.dom);
			}
		}, val : function(data){
			if(this.dom){
				if(data != undefined){
					this.dom.value = data;
				}else{
					return this.dom.value;
				}
			}
		}, attr : function(data){
			if(this.dom){
				var m = this.dom.attributes[data];
				if(m){
					return m.value;
				}
			}
		}, append : function(html){
			var div = document.createElement("div"); 
			div.innerHTML = html;
			this.dom.appendChild(div);
		}, empty : function(){
			this.dom.innerHTML = "";
			return this;
		},	ajax:function(url, data, func, error){	
			var parseData = function(data) {
				var ret = "";
				if(typeof data === "string") {
					ret = data;
				}
				else if(typeof data === "object") {
					for(var key in data) {
						ret += "&" + key + "=" + encodeURIComponent(data[key]);
					}
					ret = ret.substr(1);
				}
				ret += "&_t=" + new Date().getTime();
				return ret;
			};
			url = url + (url.indexOf("?") == -1 ? "?" : "&") + parseData(data);
			var callback = "jsonp_"+new Date().getTime()+"_"+Math.random().toString().substr(2);
			url = url + "&callback="+callback;
			var script = document.createElement("script");
			script.type = "text/javascript";
			script.src = url;
			script.id = "id_" + callback;
			script.onerror = error;
			window[callback] = function(json) {
				window[callback] = undefined;
				var elem = document.getElementById("id_" + callback);
				var parent = elem.parentNode;
				if(parent && parent.nodeType != 11) {
					parent.removeChild(elem);
				}
				if(func){
					func.apply(this, [json]);
				}
				//func(json);
			};
			
			// 在head里面插入script元素
			var head = document.getElementsByTagName("head");
			if(head && head[0]) {
				head[0].appendChild(script);
			}
		}
	};
	JS.ajax = JS.prototype.ajax;
	JS.getCookie = function(name){ 
		var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
		arr=document.cookie.match(reg);
		if(arr){
			return unescape(arr[2]);
		}else{
			return null;
		}
	};
	JS.setCookie = function(name,value,t){
		if(typeof t =='undefined' ||t==null) t =60*30*24*60*60*1000;  
		var exp  = new Date(); 
		exp.setTime(exp.getTime() + t); 
		document.cookie = name + "="+ escape (value)+ ";domain="+jesong.util.getCookieDomain()+ ";expires=" + exp.toGMTString()+";path=/";
	};
	
	function getIOSVersion() {
		var ua = navigator.userAgent;
		var match = ua.match(/OS ((\d+_?){2,3})\s/i);
		return match ? match[1].replace(/_/g, '.') : 'unknown';
	}
	function needScroll(){
		var iosVsn = getIOSVersion().split('.');
		return +iosVsn[0] == 11 && +iosVsn[1] >= 0 && +iosVsn[1] < 3;
	}
	
	if(isNullOrEmpty(jesong.visitorPhoneVerification)){
		jesong.visitorPhoneVerification = 0;
	}
	
	var browser = {
		ios : navigator.userAgent.indexOf('iPhone') > -1,
		android : navigator.userAgent.indexOf('Android') > -1,
		baiduBrowser : navigator.userAgent.indexOf('baidubrowser') > -1,
		ucBrowser : navigator.userAgent.indexOf('UCBrowser') > -1,
		huaweiBrowser : navigator.userAgent.indexOf('HuaweiBrowser') > -1
	};
	
	//var phone_user_tpl = "<li>{msg}</li>";
	var tpl = function (s, obj, escape) {
        return s.replace(/\{([a-zA-z0-9]*)\}/ig, function ($1, $2) {
            var v = jsonValue(obj, $2);
            return v;
        });
    };
	var jsonValue = (function () {
        var existProp = function (obj, t) {
            return typeof obj[t] != 'undefined';
        };
        var ignoreCase = function (obj, t) {
            if (existProp(obj, t)) return obj[t];
            if (existProp(obj, t.toLowerCase())) return obj[t.toLowerCase()];
            return obj[t.toUpperCase()];
        };
        return function (obj, key, def) {
            var tokens = key.split(".");
            for (var i = 0; i < tokens.length; i++) {
                var t = tokens[i];
                obj = ignoreCase(obj, t);
                if (obj == undefined) break;
            }
            return obj == undefined ? def : obj;
        };
    })();
	
	var JSLang={
			en:{
				softLogo : "EASYLIAO Software",
				newMessage : "New Message",
				online : "Online Service",
				welcome : "Welcome",
				you : "You",
				transchatTo : "Transfering the dialogue to ",
				transchatSuccess : "Transfering the dialogue successfully",
				closeChatConfirm : " Confirm to fininsh the dialogue?",
				opinionText : "Please rate our service, maximum 5 points",
				opinionLevel5 : "5&nbsp;",
				opinionLevel4 : "4&nbsp;",
				opinionLevel3 : "3&nbsp;",
				opinionLevel2 : "2&nbsp;",
				opinionLevel1 : "1&nbsp;",
				cancel : "Cancel",
				chatOpinioned : "You have rate this service, repeated ratings are not available.",
				chatOpinionedSuccess : "Thanks for your rating, if has the deficiency, we will try our best to improve it.",
				chatOpinionedDescription : "Please leave your valuable comments if has any deficiency. Thank you!",
				textareaDefault : "Please input the question you want to consult…",
				send : "Send",
				name : "Name",
				mobile : "Contact",
				telephone : "Landline telephone:",
				verifying : "Verifying code:",
				namePrompt : "Please input your title",
				contactsPrompt : "Please input your contact information",
				contentPrompt : "Please input the content of message",
				submit : "Submit",
				nameWarning : "Name can not be empty with no more than 20 characters",
				content : "Content",
				contractsWarning : "Please enter the correct contact information",
				contentWarning : "Please input the content of message",
				leaveMsgSuccess : "Your question has been submitted successfully, we will process it asap, please wait for our customer service reply.",
				uploadWarning : "Please select a file you want to upload!",
				screenWarning1 : "You haven’t set up the screenshot plug-in as detected, please ",
				screenWarning2 : "download",
				screenWarning3 : " the screenshot plug-in and set up now.",
				queuePrompt1 : "Sorry, the online customer service is busy now, still ",
				queuePrompt2 : " client waiting ahead of you.",
				newMessage : "You have a new message",
				sameChat : "A new dialogue is established and the current dialogue has finished",
				downloadFile1 : "The customer service representative has sent a file to you , please click to ",
				downloadFile2 : "download",
				opinion1 : "Please rate this service. ",
				opinion2 : "Rate",
				chatClosed : "Your connection with any customer service representative can not be made currently!",
				inputting : "Inputting...",
				msgSendError : "Message sending failed. Please check your network connection, or try again later.",
				chatToolFace : "Expression",
				chatToolFile : "Send file",
				chatToolScreen : "Screenshot",
				chatToolOpinion : "Rate customer service",
				chatToolQuiet : "Quiet",
				
				phoneNotNull : "Mobile number cannot be empty!",
				codeNotNull : "Verification code cannot be empty!",
				codeError : "Verification code error. Please re-enter.",
				timeOut : "Verification code timeout, please resend!",
				codeUndefind : "Verification code does not exist, please resend!",
				formatError : "Wrong mobile number, please re-enter",
				sendCode : "Send",
				resetSend : "Resend in seconds",
				checkSuccess : "Verification succeeded, please continue the conversation",
				confirm : "Determine",
				cancel : "cancel",
				tips : "Please input the verification code you received before sending the mobile number",
				codeSuccess : "Verification code sent successfully",
				codeSendError : "Failed to send verification code, please resend",
				exceed : "Try again in a minute",
				placeholder : "Please enter the verification code"
			},
			sc:{
				softLogo : "\u6613\u804A\u79D1\u6280\u63D0\u4F9B\u6280\u672F\u652F\u6301",
				newMessage : "\u60a8\u6709\u65b0\u7684\u6d88\u606f",
				online : "\u5728\u7ebf\u5ba2\u670d",
				welcome : "\u6b22\u8fce\u54a8\u8be2",
				you : "\u60a8",
				transchatTo : "\u6b63\u5728\u8f6c\u79fb\u5bf9\u8bdd\u7ed9",
				transchatSuccess : "\u8f6c\u79fb\u5bf9\u8bdd\u6210\u529f",
				closeChatConfirm : "\u786e\u5b9a\u7ed3\u675f\u5f53\u524d\u5bf9\u8bdd\uff1f",
				opinionText : "\u8bf7\u5bf9\u6211\u4eec\u7684\u670d\u52a1\u8fdb\u884c\u8bc4\u4ef7",
				opinionLevel5 : "\u975e\u5e38\u6ee1\u610f",
				opinionLevel4 : "\u6ee1\u610f",
				opinionLevel3 : "\u4e00\u822c\u6ee1\u610f",
				opinionLevel2 : "\u4e0d\u6ee1\u610f",
				opinionLevel1 : "\u975e\u5e38\u4e0d\u6ee1\u610f",
				cancel : "\u53d6\u6d88",
				chatOpinioned : "\u60a8\u5df2\u7ecf\u5bf9\u5ba2\u670d\u8fdb\u884c\u4e86\u8bc4\u4ef7,\u4e0d\u80fd\u518d\u8fdb\u884c\u8bc4\u4ef7",
				chatOpinionedSuccess : "\u611f\u8c22\u60a8\u5bf9\u672c\u6b21\u670d\u52a1\u505a\u51fa\u8bc4\u4ef7\uff0c\u5982\u6709\u4e0d\u8db3\uff0c\u6211\u4eec\u4f1a\u5c3d\u529b\u5b8c\u5584\u63d0\u9ad8\u3002",
				chatOpinionedDescription : "\u60A8\u8BA4\u4E3A\u6211\u4EEC\u8FD8\u6709\u54EA\u4E9B\u4E0D\u8DB3\uFF0C\u8BF7\u7559\u4E0B\u60A8\u7684\u5B9D\u8D35\u610F\u89C1\uFF0C\u8C22\u8C22\uFF01",
				textareaDefault : "\u8bf7\u8f93\u5165\u60a8\u8981\u54a8\u8be2\u7684\u5185\u5bb9...",
				send : "\u53d1\u9001",
				name : "\u60a8\u7684\u59d3\u540d",
				mobile : "\u8054\u7cfb\u65b9\u5f0f",
				telephone : "\u56fa\u5b9a\u7535\u8bdd\uff1a",
				verifying : "\u9a8c\u8bc1\u7801\uff1a",
				namePrompt : "\u8bf7\u8f93\u5165\u60a8\u7684\u59d3\u540d",
				content : "\u7559\u8a00\u5185\u5bb9",
				contactsPrompt : "\u8bf7\u8f93\u5165\u60a8\u7684\u624b\u673a\u53f7\u7801\u6216\u90ae\u4ef6\u5730\u5740",
				contentPrompt : "\u8bf7\u8f93\u5165\u60a8\u7684\u7559\u8a00\u5185\u5bb9",
				submit : "\u63d0\u4ea4",
				nameWarning : "\u59d3\u540d\u4e0d\u80fd\u4e3a\u7a7a\u4e14\u4e0d\u80fd\u8d85\u8fc720\u4e2a\u5b57\u7b26",
				contractsWarning : "\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u624b\u673a\u53f7\u7801\u6216\u5ea7\u673a\u7535\u8bdd, \u5750\u673a\u8bf7\u4f7f\u7528\u4ee5\u4e0b\u683c\u5f0f:\u533a\u53f7-\u5ea7\u673a\u53f7-\u5206\u673a\u53f7",
				contentWarning : "\u8bf7\u8f93\u5165\u7559\u8a00\u5185\u5bb9",
				leaveMsgSuccess : "\u60a8\u7684\u95ee\u9898\u5df2\u7ecf\u6210\u529f\u63d0\u4ea4\uff0c \u6211\u4eec\u5c06\u5c3d\u5feb\u5904\u7406\u5b8c\u6bd5\uff0c\u8bf7\u60a8\u7b49\u5019\u5ba2\u670d\u7684\u6d88\u606f\u901a\u77e5\u3002",
				uploadWarning : "\u8bf7\u9009\u62e9\u4e00\u4e2a\u8981\u4e0a\u4f20\u7684\u6587\u4ef6\uff01",
				screenWarning1 : "\u7cfb\u7edf\u63d0\u793a\uff1a\u7cfb\u7edf\u68c0\u6d4b\u5230\u60a8\u5c1a\u672a\u5b89\u88c5\u622a\u5c4f\u63d2\u4ef6\uff0c \u8bf7\u5148",
				screenWarning2 : "\u4e0b\u8f7d",
				screenWarning3 : "\u622a\u5c4f\u63d2\u4ef6\u5e76\u5b89\u88c5",
				queuePrompt1 : "\u5bf9\u4e0d\u8d77\uff0c\u76ee\u524d\u5728\u7ebf\u5ba2\u670d\u7e41\u5fd9\uff0c\u60a8\u524d\u9762\u6709",
				queuePrompt2 : "\u4f4d\u5ba2\u6237\u7b49\u5f85\u3002",
				newMessage : "\u60a8\u6709\u65b0\u7684\u6d88\u606f",
				sameChat : "\u60a8\u4e0e\u5ba2\u670d\u53c8\u5efa\u7acb\u4e86\u4e00\u4e2a\u5bf9\u8bdd\uff0c \u5f53\u524d\u5bf9\u8bdd\u5df2\u7ed3\u675f",
				downloadFile1 : "\u5bf9\u65b9\u7ed9\u60a8\u4f20\u9001\u4e86\u4e00\u4e2a\u6587\u4ef6\uff0c\u8bf7\u70b9\u51fb",
				downloadFile2 : "\u4e0b\u8f7d",
				opinion1 : "\u5bf9\u65b9\u8bf7\u4f60\u5bf9\u6b64\u6b21\u670d\u52a1\u8fdb\u884c",
				opinion2 : "\u8bc4\u4ef7",
				chatClosed : "\u5bf9\u8bdd\u5df2\u7ecf\u7ed3\u675f\uff01",
				inputting : "\u6b63\u5728\u8f93\u5165...",
				msgSendError : "\u6d88\u606f\u53d1\u9001\u5931\u8d25\uff0c\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\u8fde\u63a5\uff0c\u6216\u8005\u7a0d\u5019\u518d\u8bd5",
				chatToolFace : "\u8868\u60C5",
				chatToolFile : "\u4F20\u9001\u6587\u4EF6",
				chatToolScreen : "\u622A\u56FE",
				chatToolOpinion : "\u8BC4\u4EF7\u5BA2\u670D",
				chatToolQuiet : "\u9759\u97F3",
					
				phoneNotNull : "\u624b\u673a\u53f7\u4e0d\u80fd\u4e3a\u7a7a\uff01",
				codeNotNull : "\u9a8c\u8bc1\u7801\u4e0d\u80fd\u4e3a\u7a7a",
				codeError : "\u9a8c\u8bc1\u7801\u9519\u8bef\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165",
				timeOut : "\u9a8c\u8bc1\u7801\u8d85\u65f6\uff0c\u8bf7\u91cd\u65b0\u53d1\u9001\uff01",
				codeUndefind : "\u9a8c\u8bc1\u7801\u4e0d\u5b58\u5728\uff0c\u8bf7\u91cd\u65b0\u53d1\u9001\uff01",
				formatError : "\u624b\u673a\u53f7\u9519\u8bef\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165",
				sendCode : "\u53d1\u9001\u9a8c\u8bc1\u7801\uff01",
				resetSend : "\u0073\u540e\u91cd\u65b0\u53d1\u9001",
				checkSuccess : "\u9a8c\u8bc1\u6210\u529f\uff0c\u8bf7\u7ee7\u7eed\u5bf9\u8bdd",
				confirm : "\u786e\u5b9a",
				cancel : "\u53d6\u6d88",
				tips : "\u53d1\u9001\u624b\u673a\u53f7\u524d\uff0c\u8bf7\u5148\u8f93\u5165\u4f60\u6536\u5230\u7684\u9a8c\u8bc1\u7801",
				codeSuccess : "\u9a8c\u8bc1\u7801\u53d1\u9001\u6210\u529f",
				codeSendError : "\u9a8c\u8bc1\u7801\u53d1\u9001\u5931\u8d25\uff0c\u8bf7\u91cd\u65b0\u53d1\u9001",
				exceed : "\u4e00\u5206\u949f\u540e\u518d\u8bd5",
				placeholder : "\u8bf7\u8f93\u5165\u9a8c\u8bc1\u7801"
			}
		};
	
	var user_tpl = "<div class=\"jesong-service-bubble\">"+
	    "<div class=\"jesong-service-bubble-icon\">"+
	        "<img src=\""+jesong.autochat.userHead+"\" alt=\"\"/>"+
	    "</div>"+
	    "<div class=\"jesong-service-bubble-con\">"+
	        "<div class=\"jesong-service-session\"><span>{nickName}</span><span>{time}</span></div>"+
	        "<div class=\"jesong-service-session-con jesong-user-msg\">{msg}</div>"+
	    "</div>"+
	"</div>";
	
	var user_tpl_phone =  user_tpl;
	
	var user_tpl_inputing_phone = "<div id=\"jesong_inputing\" class=\"jesong-service-bubble\">"+
						"<div class=\"jesong-service-bubble-icon\"><img src=\""+jesong.autochat.userHead+"\"/></div>" +
						"<div class=\"jesong-service-bubble-con\">"+
							"<div class=\"jesong-service-session\"><span>{nickName}</span><span></span></div>"+
							"<div class=\"jesong-service-session-con\"><img src=\""+jesong.env.server.file+"static/2019/image/inputting.gif\" alt=\"\" style=\"width:14px;height:14px;\"/><span style=\"line-height:14px;padding-left:4px;\">"+JSLang[jesong.language].inputting+"</span></div>"+
						"</div>" +
					"</div>";
	
	var system_tpl = "<div class=\"jesong-finish-bubble\">{msg}</div>";
	
	var visitor_tpl =  "<div class=\"jesong-service-bubble\">"+
	        "<div class=\"jesong-service-bubble-icon\" style=\"visibility:hidden;\"></div>"+
	        "<div class=\"jesong-service-bubble-con\">"+
	            "<div class=\"jesong-service-session jesong-float-right\"><span>"+JSLang[jesong.language].you+"</span><span>{time}</span></div>"+
	            "<div class=\"jesong-service-session-con jesong-visitor-session-con jesong-float-right\">{msg}</div>"+
	        "</div>"+
	    "</div>";
	
	var visitor_tpl_phone =  visitor_tpl;
	var welcome_tpl = 	system_tpl;/*"<div class=\"jesong_msg_welcome\" style=\"border-left: "+((jesong.autochat.width - 100)/2)+"px solid #e0e0e0;border-right: "+((jesong.autochat.width - 100)/2)+"px solid #e0e0e0;\">{welcome}</div>" +
		"<div class=\"jesong_msg_welcome_text\">{msg}</div>";*/
	var Chat = {
		url : jesong.env.server.chat+"/msg.do",
		screenUrl : jesong.env.server.chat,
		companyId : jesong.env.compId,
		uiReady : false,
		sound : true, //开启声音
		requesting : false,//正在请求对话
		firstGetMessage : true, //第一次请求对话
		//groupId : 1,
		customerId : "",
		customerNick : "",
		jsConfig : jesong.env.confId,
		style : 4,
		visitorId : jesong.env.vId,
		visitorStaticId : jesong.env.uId,
		siteId : jesong.env.sid,
		chatId : null,
		receiveMessageInterval : null,
		screenActiveX : false,
		screenActiveXTime : null,
		//接收消息数
		receivemessageNum : 0,
		receiveSelfMessageNum : 0,
		lastGetMessageTime : null,
		lastGetFocusTime : null,
		leaveMsgFlag : false,
		transHidden : false,
		inputtingTime : -1,
		autoPopMWinTimeout : -1,
		//输入框焦点定时器
		scrollToViewInterval : -1,
		opinionValue : 4,
		//手机页面滚动高度
		wapScrollTop : 0,
		//是否首次输入
		firstMessageFocus : true,
		
		time : 60,
		t:null,
		
		phoneArr : [],
		
		words : {
			disconnect : jesong.words.disconnect,
			welcome : jesong.words.welcome,
			welcomeMin : jesong.words.welcomeMin,
			greeting : jesong.words.greeting,
			transchat : jesong.words.transchat, 
			transchatTo : JSLang[jesong.language].transchatTo,//正在转移对话给
			transchatSuccess : JSLang[jesong.language].transchatSuccess//转移对话成功
		},
		
		/**
		 * 0未建立对话， 1排队， 2建立对话， 3对话结束
		 */
		status : 0, 
		isOpinioned : function(){
			return  "true" == JS.getCookie("jesong_chat_opinioned_"+this.chatId);
		},
		setOpinioned : function(){
			JS.setCookie("jesong_chat_opinioned_"+this.chatId, "true", 60*60*1000);
		},
		resetOpinioned : function(){
			//JS.setCookie("jesong_chat_opinioned_"+this.companyId, "");
		},
		ajax : function(cmd, data, fun, error){
			if(typeof data == 'object'){
				data.force = "1";
			}else if(typeof data == "string"){
				data+="&force=1";
			}
			JS.ajax(this.url+"?cmd="+cmd, data, function(resp){
				fun.apply(Chat, [resp]);
			}, function(){
				if(error){
					error.apply(Chat, []);
				}
			});
		},
		setReady : function(){
			/*if(!jesong.forceReady){
				jesong.forceReady = true;
				jesong.initUI();
			}*/
		},
		setAutoPopMWinTimes : function(){
			if(jesong.jsType == 1 && jesong.env.isPhone && this.status == 2 && jesong.autochat.autoPopMWinTime > 0 && jesong.autochat.autoPopMWinPeroid > 0){
				var _key = "jesong_autopopMWin_"+this.chatId;
				var autoPopMWinTimes =  JS.getCookie(_key);
				if(autoPopMWinTimes){
					autoPopMWinTimes = parseInt(autoPopMWinTimes);
				}else{
					autoPopMWinTimes = 0;
				}
				if(autoPopMWinTimes  < jesong.autochat.autoPopMWinTime){
					if(jesong.env.min == 1){
						this.autoPopMWinTimeout = window.setTimeout(function(){
							JS.setCookie(_key, autoPopMWinTimes+1, 60*60*1000);
							Chat.showChatLayout.apply(Chat, []);
						}, jesong.autochat.autoPopMWinPeroid * 1000);
					}else{
						this.autoPopMWinTimeout = window.setTimeout(function(){
							jesong.util.openChat({});
						}, jesong.autochat.autoPopMWinPeroid * 1000);
					}
				}
			}
		},
		init : function(){
			
			if(jesong.autochat.width > jesong.util.getBody().clientWidth){
				jesong.autochat.width = jesong.util.getBody().clientWidth;
			}
			
			if(jesong.autochat.width < 270){
				jesong.autochat.width = 270;
			}
			
			if(jesong.autochat.height > jesong.util.getBody().clientHeight){
				jesong.autochat.height = jesong.util.getBody().clientHeight;
			}
			
			if(jesong.autochat.height < 300){
				jesong.autochat.height = 300;
			}
			
			if(jesong.autochat.bgcolor == ""){
				var st = parseInt(jesong.config.frameChatStyle);
				if(st < 11){
					st = 14;
				}
				var colorArray = ["#000000", "#db4a36", "#eed423", "#3097ef", "#85b92c", "#5669c4"];
				jesong.autochat.bgcolor = colorArray[st-11];
			}
			var style = "4";
			var logoStyle = "";
			/*if(jesong.config.forceChatLogo != ""){
				logoStyle = "background:url("+jesong.config.forceChatLogo+") no-repeat";
			}*/
			if(jesong.jsType == 1 && jesong.env.isPhone){
				JS("jesong_chat_layout").addClass("jesong-container-full");
				jesong.autochat.height = parseInt(jesong.util.getBody().clientHeight * jesong.autochat.phoneHeight / 100);
				jesong.autochat.width = jesong.util.getBody().clientWidth;
				JS("jesong_chat_layout").css("height", jesong.autochat.height + "px").html(
						"<div id=\"jesong_mask\" style=\"width:"+jesong.autochat.width+"px;height:"+jesong.autochat.height+"px;position: absolute; bottom: 0px; right: 0px; z-index: 100; opacity: 0.5; filter:alpha(opacity=50); background: rgb(0, 0, 0);background:#000000;display:none;\"></div>" +
						
						"<div class=\"jesong-full-top\" style=\"position:relative;background-color:"+jesong.autochat.bgcolor+";border-color:"+jesong.autochat.bgcolor+";\">" +
							"<div style=\"position:absolute;top:0px;left:60px;right:60px;height:100%;line-height:45px;font-size:16px;text-align:center;color:#ffffff;\">"+(jesong.autochat.pageTitle == "" ? JSLang[jesong.language].online : jesong.autochat.pageTitle)+"</div>"+
							"<div id=\"jesong_chat_min_btn\" class=\"jesong-min-btn\"></div>"+
						"</div>"+
						"<div id=\"jesong_chat_body\" class=\"jesong-full-main\" style=\"height:"+(jesong.autochat.height-45)+"px;\">" +
						"</div>"
				);
			}else{
				var defaultLogo = jesong.env.server.file+"images/2020/icon.png";//"/images/chat/201805/head-user.png";
				if(jesong.autochat.pcMinLogo != ""){
					defaultLogo = jesong.autochat.pcMinLogo;
				}
				JS("jesong_chat_layout").addClass("jesong-container-mini");
				JS("jesong_chat_layout").html(
						"<div id=\"jesong_mask\" style=\"width:"+jesong.autochat.width+"px;height:"+jesong.autochat.height+"px;position: absolute; bottom: 0px; right: 0px; z-index: 100; opacity: 0.5; filter:alpha(opacity=50); background: rgb(0, 0, 0);background:#000000;display:none;\"></div>" +
							"<div class=\"jesong-container-mini-inner\">"+
								"<div class=\"jesong-container-mini-main\">" +
									"<div class=\"jesong-mini-main-top\" style=\"background-color:"+jesong.autochat.bgcolor+";\">" +
						                "<div class=\"jesong-brand\">" +
						                    "<img src=\""+defaultLogo+"\" alt=\"\"/>" +
						                "</div>" +
						                "<div class=\"jesong-abstract\">" +
						                    "<p class=\"jesong-abstract-con\" style=\"margin-top:15px;font-size: 14px;\">"+(jesong.autochat.pageTitle == "" ? JSLang[jesong.language].online : jesong.autochat.pageTitle)+"</p>" +
						                "</div>" +
						                "<div class=\"jesong-custom\">" +
						                    "<div id=\"jesong_chat_close\"></div>" +
						                    "<div id=\"jesong_chat_max_btn\"></div>" +
						                    "<div id=\"jesong_chat_min_btn\"></div>" +
						                "</div>" +
						            "</div>" +
						            "<div id=\"jesong_chat_body\"></div>"+
								"</div>"+
							"<div>"+
							/*
							"<div class=\"jesong_chat_head\" style=\"background-color:"+jesong.autochat.bgcolor+";border-color:"+jesong.autochat.bgcolor+"\">" +
								"<div id=\"jesong_chat_logo\" class=\"jesong_logo\">" +
									"<img src=\""+(jesong.autochat.pcMinLogo == "" ? defaultLogo : jesong.autochat.pcMinLogo)+"\"/>" +
									"<div>"+(jesong.autochat.pageTitle == "" ? JSLang[jesong.language].online : jesong.autochat.pageTitle)+"</div>" +
								"</div>" +
								"<div id=\"jesong_chat_close\"></div>" +
								"<div id=\"jesong_chat_min_btn\"></div>" +
								"<div id=\"jesong_chat_sound\"></div>" +
							"</div>" +
							"<div id=\"jesong_chat_body\" class=\"jesong_chat_body\"></div>" +*/
							"<div style=\"display:none;\"><iframe id=\"jesong_frame\" src = \"about:blank\"></iframe></div>");
				/*JS("jesong_chat_min").html("<div id=\"jesong_chat_min_logo\" class=\"jesong_logo\"><img src=\""+(jesong.autochat.pcMinLogo == "" ? defaultLogo : jesong.autochat.pcMinLogo)+"\"/>" +
						"						<div id=\"jesong_chat_min_text\">"+(jesong.autochat.pageTitle == "" ? JSLang[jesong.language].online : jesong.autochat.pageTitle)+"</div></div><div id=\"jesong_chat_min_close\"></div><div id=\"jesong_chat_min_max\"></div><div id=\"jesong_chat_min_sound\"></div>");
				JS("jesong_chat_min").css("backgroundColor",  jesong.autochat.bgcolor)
									 .css("borderColor", jesong.autochat.bgcolor)
									 .css("width", jesong.autochat.width+"px");*/
				JS("jesong_chat_min").addClass("jesong-container-mini-badge").css("backgroundColor", jesong.autochat.bgcolor).html(
									            "<div class=\"jesong-badge-warning\" id=\"jesong_unread\">0</div>"+
									            "<div class=\"jesong-badge-brand\"><img src=\""+defaultLogo+"\" alt=\"\"/></div>"+
									            "<div class=\"jesong-badge-con\" id=\"jesong_chat_min_text\">"+(jesong.autochat.pageTitle == "" ? JSLang[jesong.language].online : jesong.autochat.pageTitle)+"</div>"+
									            "<span class=\"jesong-badge-icon\"></span>");
				JS("jesong_chat_min").hide();
			}
			
			/*JS("jesong_pop_msg").css("width", jesong.autochat.width+"px")
								.css("borderColor", jesong.autochat.bgcolor)
								.html("<div class=\"jesong_pop_msg_arrow\">" +
											"<em style=\"border-color:"+jesong.autochat.bgcolor+" transparent transparent transparent;\"></em>" +
											"<span style=\"border-color:#ffffff transparent transparent; top:-3px;\"></span>" +
									   "</div>" +
									   "<div class=\"jesong_pop_msg_context\" style=\"width:"+(jesong.autochat.width-36)+"px\">" +
											"<div id=\"jesong_pop_msg_user\"></div>" +
											"<div class=\"jesong_pop_msg_user_line\"></div>" +
											"<div id=\"jesong_pop_msg_text\"></div>" +
										"</div>");
			JS("jesong_pop_msg").hide();*/
			
			if(jesong.autochat.closeBtn == "0"){
				JS("jesong_chat_close").hide();
				JS("jesong_chat_min_close").hide();
			}
			
			if(jesong.autochat.minBtn == "0"){
				JS("jesong_chat_min_btn").hide();
			}
			
			this.initChat();
			JS("jesong_chat_min_btn").click(function(){
				this.hideChatLayout();
				this.showUnReadMessage();
				//if(this.status == 2){
				if(!(jesong.jsType == 1 && jesong.env.isPhone)){
					if(this.status == 2){
						JS("jesong_chat_min").show();
					}else if(jesong.env.min == 1){
						var times = JS.getCookie("jesong_autochat_"+this.companyId+"_time");
						if(times != null && (jesong.autochat.times == -1 || parseInt(times) < jesong.autochat.times) && jesong.autochat.delay > 0){
							window.setTimeout(function(){
								Chat.showChatLayout.apply(Chat, []);
							}, jesong.autochat.delay * 1000);
							JS.setCookie("jesong_autochat_"+this.companyId+"_time", (times == null ? "1" : parseInt(times)+1), 1*60*60*1000);
						}
					}
				}else{
					this.setAutoPopMWinTimes();
					//JS.setCookie("jesong_rec_"+this.chatId, this.receivemessageNum, 60*60*1000);
				}
				//}
			});
			JS("jesong_chat_max_btn").click(function(){
				var url = jesong.util.getChatUrl();
				url = url + "&sound="+ (this.sound ? "1" : "0");
				jesong.util.openWindowChat(url);
			});
			var closeFun = function(){
				if(this.status == 2){
					if(window.confirm(JSLang[jesong.language].closeChatConfirm)){//确定结束当前对话？
						this.closeChat();
						this.addMessage(this.words.disconnect, "system");
						if(this.isOpinioned()){
							this.hideChatLayout();
							JS("jesong_chat_min_close").hide();
						}else{
							JS("jesong_chat_min_close").hide();
							this.showChatLayout();
							JS("jesong_file_layout").hide();
							JS("jesong_emoticon_layout").hide();
							if(!(jesong.autochat.tools && jesong.autochat.tools.opinion == "0") && jesong.callerOpinion == "1"){
								JS("jesong_mask").show();
								JS("jesong_opinion_layout").show().css("left", (jesong.autochat.width - 220)/2+"px").css("top", (jesong.autochat.height - 330)/2+"px");
							}else{
								this.hideChatLayout();
								JS("jesong_chat_min_close").hide();
							}
						}
					}
				}else{
					this.hideChatLayout();
					JS("jesong_chat_min_close").hide();
					if(this.status == 1){
						this.exitQueue();
					}
				}
				//JS("jesong_panel").show();
			};
			JS("jesong_chat_close").click(closeFun);
			JS("jesong_chat_min_close").click(closeFun);
			if(jesong.jsType != 1 && !jesong.env.isPhone && !(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion .split(";")[1].replace(/[ ]/g,"")=="MSIE6.0")){
				JS("jesong_chat_logo").bind("mousedown", function(e){
					var e = e || window.event;
			        var disX = e.clientX;
			        var disY = e.clientY;
			        var right = JS("jesong_chat_layout").dom.style.right.replace("px", "");
			        var bottom = JS("jesong_chat_layout").dom.style.bottom.replace("px", "");
			        var left = JS("jesong_chat_layout").dom.style.left.replace("px", "");
			        if(right == ""){right = 1;}
			        if(bottom == ""){bottom = 1;}
			        if(left == ""){left = 1;}
			        document.onmousemove = function (e){
			            var e = e || window.event;
			            var r = disX - e.clientX + parseInt(right);
			            var b = disY - e.clientY + parseInt(bottom);
			            var l = -disX + e.clientX + parseInt(left);
			            if(r<0){
			            	r = 0;
			            }else if(r > jesong.util.getBody().clientWidth - jesong.autochat.width){
			            	r = jesong.util.getBody().clientWidth - jesong.autochat.width;
			            }
			            if(b < 0){
			            	b = 0;
			            }else if(b > jesong.util.getBody().clientHeight - jesong.autochat.height){
			            	b = jesong.util.getBody().clientHeight - jesong.autochat.height;
			            }
			            if(l < 0){
			            	l = 0;
			            }else if(l > jesong.util.getBody().clientWidth - jesong.autochat.width){
			            	l = jesong.util.getBody().clientWidth - jesong.autochat.width;
			            }
			            
			            if(jesong.env.pos == "2" || jesong.env.pos == "3"){
			            	JS("jesong_chat_layout").css("left", l + "px");
			            }else{
			            	JS("jesong_chat_layout").css("right", r + "px");
			            }
			            JS("jesong_chat_layout").css("bottom", b + "px")
			            					    .css("_top", "expression(eval(document.documentElement.scrollTop+document.documentElement.clientHeight-this.offsetHeight-(parseInt(this.currentStyle.marginTop,10)||0)-"+b+"-(parseInt(this.currentStyle.marginBottom,10)||0)))");
			            return false;
			        };
			        document.onmouseup = function (){
			            document.onmousemove = null;
			            document.onmouseup = null;
			        };
			        return false;
				});
			}
			
		}, initChat : function(){
			if(this.status != 2){
				//var cId = JS.getCookie("jesong_chat_"+this.companyId+"_id");
				//var cUserId = JS.getCookie("jesong_chat_user_"+this.companyId+"_id");
				//if(jesong.env.min == 1 || (jesong.jsType == 1 && jesong.env.isPhone)){//|| !jesong.env.isPhone
					this.ajax("isChatExist", {c : this.companyId, vId : this.visitorId, uId : this.visitorStaticId}, function(resp){
						if(resp.exist){
							if(resp.visitorId){
								this.visitorId = resp.visitorId;
							}
							this.connect(resp.chatId, resp.customerId);
						}else{
							this.firstGetMessage = false;
							this.initWaitQueue();
						}
						this.setReady();
					});
				//}else{
				//	this.initWaitQueue();
				//}
			}else{
				if(jesong.autochat.connect == "1"){
					jesong.util.openChat({});
				}
			}
		}, initWaitQueue : function(){
			var waitGroupId = JS.getCookie("jesong_queue_"+this.companyId+"_id");
			if(waitGroupId && waitGroupId > 0){
				this.groupId = waitGroupId;
				this.waitQueue(true);
			}else{
				
				var execFun = function(){
					var flag = Chat.initAutochat.apply(Chat, []);
					if(flag && jesong.autochat.connect == "1"){
						jesong.util.openChat({});
					}else{
						Chat.sendkeyWord.apply(Chat, [jesong.autochat.keyWord]);
					}
				};
				
				if(jesong.autochat.delay > 0){
					window.setTimeout(execFun, jesong.autochat.delay * 1000);
				}else{
					execFun();
				}
				
				/*var flag = this.initAutochat();
				if(flag && jesong.autochat.connect == "1"){
					jesong.util.openChat({});
				}else{
					this.sendkeyWord(jesong.autochat.keyWord);
				}*/
				
				this.setReady();
				/*if(!flag){
					JS("jesong_chat_layout").hide();
				}else{
					JS("jesong_chat_layout").show();
				}*/
			}
		}, initAutochat : function(){
			if(/*!jesong.env.isPhone && */jesong.autochat && jesong.autochat.use == 1 && jesong.monitor.config && jesong.monitor.config.group){
				var now = new Date();var nh=now.getHours(),nm=now.getMinutes();		
				function tonum(s){ var s1=s.replace(/:/g,'');
					while(s1.charAt(0)=='0' && s1.length>1) s1=s1.substring(1); return parseInt(s1);}
				var h1=tonum(jesong.autochat.start);var h2=tonum(jesong.autochat.end);var n=tonum(nh+":"+((nm<10)?('0'+nm):nm));
				var _flag = false;
				if(h1 > h2 && ((h1 <= n && n < 2359) || (n > 0 && n <= h2))){
					_flag = true;
				}else if(h1<=n && h2>=n){
					_flag = true;
				}
				var times = JS.getCookie("jesong_autochat_"+this.companyId+"_time");
				if((times == null || jesong.autochat.times == -1 || parseInt(times) < jesong.autochat.times) && _flag){
					if(jesong.env.min == 1){
						//jesong.autochat.show = true;
						this.initChatUI();
						//JS("jesong_panel").hide();
						this.addTopImage();
						this.showChatLayout();
						if(this.words.welcome != "" || this.words.welcomeMin != ""){
							this.addMessage(this.words.welcomeMin ? this.words.welcomeMin : this.words.welcome, "welcome");
						}
						//
						JS.setCookie("jesong_autochat_"+this.companyId+"_time", (times == null ? "1" : parseInt(times)+1), 1*60*60*1000);
						return true;
					}else if(jesong.env.min == 0  && jesong.jsType == 1 && jesong.env.isPhone && jesong.autochat.connect == "1"){
						jesong.util.openChat("g="+jesong.monitor.config.group);
						JS.setCookie("jesong_autochat_"+this.companyId+"_time", (times == null ? "1" : parseInt(times)+1), 1*60*60*1000);
						return false;
					}else{
						return false;
					}
				}else{
					return false;
				}
			}else{
				return false;
			}
		}, setFocus : function(id){
			var rel = JS(id).attr("rel");
			JS(id).focus(function(){
				if(JS(id).attr("rel") == JS(id).val()){
					JS(id).val("");
				}
				JS(id).removeClass("jesong_text_blur");
				if(id == "jesong_message"){
					if(jesong.jsType == 1 && jesong.env.isPhone){
						JS("jesong_chat_phone_bottom").css("position", "absolute").css("bottom", "0px").css("left", "0px").css("right", "0px");
						if(browser.ios && (browser.baiduBrowser || (browser.ucBrowser /*&& appver >= 12*/))){
							if(this.firstMessageFocus){
								window.setTimeout(function(){
									JS("jesong_message").blur();
								}, 100);
								this.firstMessageFocus = false;
							}
						} else if(browser.android && (browser.huaweiBrowser)){
							//JS("jesong_chat_phone_bottom").css("height", "60px")
						}
						window.setTimeout(function(){
							if(!needScroll()){
								document.getElementById("jesong_message").scrollIntoView();
							}
						}, 600);
					}
				}
			}).blur(function(){
				if(id == "jesong_message"){
					if(jesong.jsType == 1 && jesong.env.isPhone){
						JS("jesong_chat_layout").css("bottom", "0px");
						JS("jesong_chat_phone_bottom").css("position", "absolute").css("bottom", "0px").css("left", "0px").css("right", "0px");
						document.body.scrollTop = this.wapScrollTop;
						document.documentElement.scrollTop = this.wapScrollTop;
						if(browser.android && (browser.huaweiBrowser)){
							JS("jesong_chat_phone_bottom").css("height", "130px")
						}
					}
				}
				if(JS(id).val() == ""){
					var _rel = JS(id).attr("rel");
					JS(id).val(_rel);
					JS(id).addClass("jesong_text_blur");
				}
			});
			JS(id).val(rel);
			JS(id).addClass("jesong_text_blur");
		}, initChatUI : function(){
			/*if(jesong.autochat.hideMonitor){
				jesong.monitor.hide();
			}*/
			if(this.uiReady){
				return;
			}
			
			var emoticonHTML = "<div id=\"jesong_emoticon_layout\" class=\"jesong-emoticon-layout jesong-pop-window\">"+
					       			"<div id=\"jesong_emoticons\" class=\"jesong-emoticons\"></div>"+
						        "</div>";
			
			var opinionHTML = "<div id=\"jesong_opinion_layout\" class=\"jesong-opinion-layout jesong-pop-window\">"+
								"<div id=\"jesong_opinion_reason_cancel\" class=\"jesong-opinion-close\"></div>"+
								"<div class=\"jesong-opinion-icon\"></div>"+
								"<div id=\"jesong_opinion_score\" class=\"jesong-score\">"+
									"<div class=\"jesong-desp\">"+JSLang[jesong.language].opinionText+"</div>"+
									"<div style=\"width:100%;text-align:center;margin-top:20px;height:40px;\">"+
										"<span id=\"jesong_op0\" class=\"jesong-star jesong-star-1\"></span>"+
										"<span id=\"jesong_op1\" class=\"jesong-star jesong-star-1\"></span>"+
										"<span id=\"jesong_op2\" class=\"jesong-star jesong-star-1\"></span>"+
										"<span id=\"jesong_op3\" class=\"jesong-star jesong-star-1\"></span>"+
										"<span id=\"jesong_op4\" class=\"jesong-star jesong-star-1\"></span>"+
									"</div>"+
									"<div id=\"jesong_opinion_result\" class=\"jesong-result\">"+
										"<span class=\"jesong-op5\" style=\"display:block;\">"+JSLang[jesong.language].opinionLevel5+"</span>"+
										"<span class=\"jesong-op4\">"+JSLang[jesong.language].opinionLevel4+"</span>"+
										"<span class=\"jesong-op3\">"+JSLang[jesong.language].opinionLevel3+"</span>"+
										"<span class=\"jesong-op2\">"+JSLang[jesong.language].opinionLevel2+"</span>"+
										"<span class=\"jesong-op1\">"+JSLang[jesong.language].opinionLevel1+"</span>"+
									"</div>"+
								"</div>"+
								"<div id=\"jesong_opinion_reason\" class=\"jesong-reason\">"+
									 "<textarea id=\"jesong_opinion_reason_text\" placeholder=\""+JSLang[jesong.language].chatOpinionedDescription+"\"></textarea>"+
								"</div>"+
								"<div style=\"width:100%;text-align:center;margin-top:20px;\">"+
									"<button id=\"jesong_opinion_reason_commit\" class=\"jesong-commit\" style=\"background-color:"+jesong.autochat.bgcolor+";border-color:"+jesong.autochat.bgcolor+";\">"+JSLang[jesong.language].submit+"</button>"+
								"</div>"+
							 "</div>";
			var soundHTML = "<div id=\"jesong_sound\" style=\"display:none;\"><audio id=\"jesong_audio\" src=\""+jesong.env.server.file + "wav/sound.wav"+"\"></audio></div>";
			
			var softLogo =  JSLang[jesong.language].softLogo;
			if(jesong.autochat.softLogoLicense){
				softLogo = jesong.autochat.softLogo;
			}
			
			//初始布局 -- 手机端工具条
			if(jesong.jsType == 1 && jesong.env.isPhone){
				JS("jesong_chat_body").empty().html(
						"<div id=\"jesong_chat_record\" class=\"jesong-mini-main-middle test-1\" style=\"height:"+(jesong.autochat.height-45-130)+"px\">"+
			            "</div>"+  emoticonHTML + opinionHTML +
			            "<div id=\"jesong_chat_phone_bottom\" class=\"jesong-mini-main-footer\" style=\"border:0px;height:130px;\">"+
			                "<div class=\"jesong-operation-header\">"+
			                    "<div id=\"jesong_tools_emoticons\" title=\""+JSLang[jesong.language].chatToolFace+"\"></div>"+
			                    "<div id=\"jesong_tools_opinion\" title=\""+JSLang[jesong.language].chatToolOpinion+"\"></div>"+
			                    "<div id=\"jesong_tools_sound\" title=\""+JSLang[jesong.language].chatToolQuiet+"\"></div>"+
			                    "<div id=\"jesong_tools_quiet\" title=\""+JSLang[jesong.language].chatToolQuiet+"\"></div>"+
			                "</div>"+
			                "<div class=\"jesong-operation-main\" style=\"position:relative;\">"+
			                    "<div class=\"jesong-main-import\" >"+
			                        "<textarea id=\"jesong_message\" style=\"width:"+(jesong.autochat.width-90)+"px;\" class=\"test-1\" placeholder=\""+JSLang[jesong.language].textareaDefault+"\"></textarea>"+
			                    "</div>"+
			                    "<button id=\"jesong_chat_send_btn\" class=\"jesong-btn-info\" style=\"background-color:"+jesong.autochat.bgcolor+";border:1px solid "+jesong.autochat.bgcolor+";line-height: 15px;border-radius:3px;color: #fff;position:absolute;top:20px;right:10px;width:60px;height:30px;font-size:14px;\">"+JSLang[jesong.language].send+"</button>"+
			                "</div>"+
			                "<div class=\"jesong-operation-footer\" style=\"background:#fafaf8;border-top:1px solid #eee;text-align:center;\">"+
			                    "<span class=\"jesong-help-block\">"+softLogo+"</span>"+
			                "</div>"+
			            "</div>"+ soundHTML
			            
				);
			}else{ //PC端工具条
				
				JS("jesong_chat_layout").css("width", jesong.autochat.width+"px")
										.css("height", jesong.autochat.height+"px")
										.show();
				JS("jesong_chat_body").css("height", (jesong.autochat.height-50)+"px");
				
				JS("jesong_chat_body").empty().html(
						"<div id=\"jesong_chat_record\" class=\"jesong-mini-main-middle test-1\" style=\"height:"+(jesong.autochat.height-180)+"px\">"+
			            "</div>"+ emoticonHTML + opinionHTML +
				        "<div id=\"jesong_file_layout\" class=\"jesong-file-layout jesong-pop-window\" style=\"width:"+jesong.autochat.width+"px;\">"+
				        	"<iframe id=\"jesong_file_frame\" border=\"0\" marginwidth=\"0\" marginheight=\"0\" scrolling=\"no\" style=\"width:100%;height:28px;margin-top:6px;border:0px;overflow:hidden;\"></iframe>"+
						"</div>"+
			            "<div class=\"jesong-mini-main-footer\">"+
			                "<div class=\"jesong-operation-header\">"+
			                    "<div id=\"jesong_tools_emoticons\" title=\""+JSLang[jesong.language].chatToolFace+"\"></div>"+
			                    "<div id=\"jesong_tools_file\" title=\""+JSLang[jesong.language].chatToolFile+"\"></div>"+
			                    "<div id=\"jesong_tools_screen\" title=\""+JSLang[jesong.language].chatToolScreen+"\"></div>"+
			                    "<div id=\"jesong_tools_opinion\" title=\""+JSLang[jesong.language].chatToolOpinion+"\"></div>"+
			                    "<div id=\"jesong_tools_sound\" title=\""+JSLang[jesong.language].chatToolQuiet+"\"></div>"+
			                    "<div id=\"jesong_tools_quiet\" title=\""+JSLang[jesong.language].chatToolQuiet+"\"></div>"+
			                "</div>"+
			                "<div class=\"jesong-operation-main\">"+
			                    "<div class=\"jesong-main-import\">"+
			                        "<textarea id=\"jesong_message\" class=\"test-1\" placeholder=\""+JSLang[jesong.language].textareaDefault+"\"></textarea>"+
			                    "</div>"+
			                "</div>"+
			                "<div class=\"jesong-operation-footer\">"+
			                    "<span class=\"jesong-help-block\">"+softLogo+"</span>"+
			                    "<button id=\"jesong_chat_send_btn\" class=\"jesong-btn-info\" style=\"background-color:"+jesong.autochat.bgcolor+";border-color:"+jesong.autochat.bgcolor+"\">"+JSLang[jesong.language].send+"</button>"+
			                "</div>"+
			            "</div>" + soundHTML
				);
			}
			
			var emoticonHTML = "";
			for(var i=1; i<16; i++){
				var e = jesong.env.server.file+"/emoticon/"+(i>9 ? i : "0"+i)+".png";
				var style="";
				if((i-1)%5==0){
					style="clear:both;";
				}
				emoticonHTML += "<div id=\"jesong_emoticon_"+i+"\" class=\"jesong-emot\" style=\""+style+"\"><img src=\""+e+"\"></img></div>";
			}
			JS("jesong_emoticons").html(emoticonHTML);
			var setEvent = function(id, i){
				JS(id).hover(function(){
					JS(id).addClass("hover");
				}, function(){
					JS(id).removeClass("hover");
				}).click(function(){
					var message = JS("jesong_message").val();
					var rel = JS("jesong_message").attr("rel");
					if(message == rel){
						message = "";
					}
					JS("jesong_message").val(message+i);
					//JS("jesong_emoticon_layout").hide();
					JS("jesong_message").focus();
					//this.setFocus("jesong_message");
				});
			};
			for(var i=1; i<16; i++){
				setEvent("jesong_emoticon_"+i, "["+(i>9?i:"0"+i)+"]");
			}
			
			//初始对话界面UI事件
			this.initChatEvent();
			this.setFocus("jesong_message");
			this.setFocus("jesong_opinion_reason_text");
			this.uiReady = true;
			if(jesong.jsType != 1 || !jesong.env.isPhone){
				if(jesong.env.pos == "2"){//居中
					var left = -jesong.autochat.width/2 +jesong.util.getBody().clientWidth/2;
					var top = -jesong.autochat.height/2+ jesong.util.getBody().clientHeight/2;
					JS("jesong_chat_layout").css("left", left+"px").css("bottom", top+"px");
					JS("jesong_chat_min").css("left", left+"px");
					//JS("jesong_pop_msg").css("left", left+"px");
				}else if(jesong.env.pos == "3"){//左下角
					JS("jesong_chat_layout").css("left", "1px");
					JS("jesong_chat_min").css("left", "1px");
					//JS("jesong_pop_msg").css("left", "1px");
				}else{//右下角 
					
				}
			}
			
			//this.addMessage(this.words.welcome, "user");
			//开始创建对话
			//this.connect();
		}/*, startCall : function(){
			JS("jesong_chat_body").empty().html(
				//"<div id=\"jesong_call_layout\">"+
					"<div class=\"jesong_call_center\">"+
						"<div class=\"jesong_call_field\">"+
							"<div class=\"jesong_call_head\">"+JSLang[jesong.language].name+"</div>"+
							"<div class=\"jesong_call_context\"><input type=\"text\" id=\"jesong_call_field_name\"/></div>"+
						"</div>"+
						"<div class=\"jesong_call_field\">"+
							"<div class=\"jesong_call_head jesong_select\">"+JSLang[jesong.language].mobile+"</div>"+
							"<div class=\"jesong_call_context\"><input type=\"text\" id=\"jesong_call_field_name\"/></div>"+
						"</div>"+
						"<div class=\"jesong_call_field\">"+
							"<div class=\"jesong_call_head jesong_unselect\">"+JSLang[jesong.language].telephone+"</div>"+
							"<div class=\"jesong_call_context\"><input type=\"text\" id=\"jesong_call_field_name\"/></div>"+
						"</div>"+
						"<div class=\"jesong_call_field\">"+
							"<div class=\"jesong_call_head\">"+JSLang[jesong.language].verifying+"</div>"+
							"<div class=\"jesong_call_context\"><input type=\"text\" id=\"jesong_call_field_name\"/></div>"+
						"</div>"+
						"<div class=\"jesong_call_field\">"+
							"<div class=\"jesong_call_head\">&nbsp;</div>"+
							"<div class=\"jesong_call_context\"><div id=\"jesong_commit\"></div></div>"+
						"</div>"+
					"</div>"
				//"</div>"
			);
		}*/, initLeaveMsgUI : function(){
			var _w = jesong.autochat.width - 110;
			var _h = 140;
			if(!(jesong.jsType == 1 && jesong.env.isPhone)){
				_h = jesong.autochat.height - 270;
			}
			var _w2 = jesong.autochat.width - 20;
			JS("jesong_chat_layout")//.css("backgroundColor",  jesong.autochat.bgcolor)
									.css("width", jesong.autochat.width+"px")
									.css("height", jesong.autochat.height+"px").show();
			JS("jesong_chat_body").css("background", "#f5f8fa")
								  .css("height", (jesong.autochat.height-48)+"px");
			
			JS("jesong_chat_body").empty().html(
					"<div class=\""+(jesong.jsType == 1 && jesong.env.isPhone ? "jesong-leave-phone" : "jesong-leave-pc")+"\">"+
						"<div class=\"jesong-leave-field\" style=\"height:30px;width:"+_w2+"px\">"+
							"<div class=\"jesong-leave-title\">"+JSLang[jesong.language].name+"</div>"+//您的姓名：\u60a8\u7684\u59d3\u540d\uff1a
							"<div class=\"jesong-leave-content\"><input type=\"text\" id=\"jesong_lm_name\" style=\"width:"+_w+"px;\" rel=\""+JSLang[jesong.language].namePrompt+"\"/></div>"+//请输入您的姓名
						"</div>"+
						"<div class=\"jesong-leave-field\" style=\"height:30px;width:"+_w2+"px\">"+
							"<div class=\"jesong-leave-title\">"+JSLang[jesong.language].mobile+"</div>"+//联系方式：\u8054\u7cfb\u65b9\u5f0f\uff1a
							"<div class=\"jesong-leave-content\"><input type=\"text\" id=\"jesong_lm_contact\" style=\"width:"+_w+"px;\" rel=\""+JSLang[jesong.language].contactsPrompt+"\"/></div>"+//请输入您的手机号码或邮件地址
						"</div>"+
						"<div class=\"jesong-leave-field\" style=\"height:"+_h+"px;width:"+_w2+"px\">"+
							"<div class=\"jesong-leave-title\">"+JSLang[jesong.language].content+"</div>"+//留言内容：\u7559\u8a00\u5185\u5bb9\uff1a
							"<div class=\"jesong-leave-content\"><textarea id=\"jesong_lm_content\" style=\"width:"+_w+"px;height:"+_h+"px;\" rel=\""+JSLang[jesong.language].contentPrompt+"\"></textarea></div>"+//请输入您的留言内容
						"</div>"+
						"<div class=\"jesong-leave-btn\"  style=\"width:"+_w2+"px\">"+
							"<button id=\"jesong_commit\" class=\"jesong-commit\" style=\"background-color:"+jesong.autochat.bgcolor+";border-color:"+jesong.autochat.bgcolor+";\">"+JSLang[jesong.language].submit+"</button>"+
						"</div>"+
					"</div>"
				);
			this.setFocus("jesong_lm_name");
			this.setFocus("jesong_lm_contact");
			this.setFocus("jesong_lm_content");
			JS("jesong_commit").hover(function(){
				JS("jesong_commit").addClass("hover");
			}, function(){
				JS("jesong_commit").removeClass("hover");
			}).click(function(){
				if(this.leaveMsgFlag == false){
					this.leaveMsgFlag = true;
					function _getValue(id){
						var value = JS(id).val();
						var rel = JS(id).attr("rel");
						if(value != rel){
							return value;
						}else{
							return "";
						}
					}
					var name = _getValue("jesong_lm_name");
					var contact = _getValue("jesong_lm_contact");
					var content = _getValue("jesong_lm_content");
					if(name =="" || name.length>20){
						alert(JSLang[jesong.language].nameWarning);//姓名不能为空且不能超过20个字符
						JS("jesong_lm_name").focus();
						this.leaveMsgFlag = false;
						return false;
					}
					var category = 0;
					var email = "";
					var phone = "";
					var isPhone = /^(13|14|15|16|17|18|19)\d{9}$/i.test(contact) || /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(contact);
					var isEmail = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test(contact);
					if(isPhone){
						phone = contact;
					}else if(isEmail){
						category = 1;
						email = contact;
					}else{
						alert(JSLang[jesong.language].contractsWarning);//请输入正确的手机号码或邮件地址
						JS("jesong_lm_contact").focus();
						this.leaveMsgFlag = false;
						return false;
					}
					if(content == ""){
						alert(JSLang[jesong.language].contentWarning);//请输入留言内容
						JS("jesong_lm_content").focus();
						this.leaveMsgFlag = false;
						return false;
					}
					this.ajax("leaveWord", {
						companyId:this.companyId,
						groupId:this.groupId,
						phone:phone,
						category:category,
						name:name,
						email:email,
						subject:"",
						message:content,
						v:this.visitorId,
						u:this.visitorStaticId,
						sid:jesong.env.sid,
						promotionId : jesong.env.promotionId,
						chatUrl:window.location.href,
						ref:jesong.env.refer?jesong.env.refer:"",
						first:jesong.env.firstPage,
						sf:jesong.env.spreadFlag ? jesong.env.spreadFlag:""
					},function(resp){
						alert(JSLang[jesong.language].leaveMsgSuccess);//您的问题已经成功提交， 我们将尽快处理完毕，请您等候客服的消息通知。
						this.hideChatLayout();
						this.leaveMsgFlag = false;
					});
				}
			});
		},
		/**
		 * 初始对话界面UI事件
		 */
		initChatEvent : function(){
			var setHover = function(id){
				JS(id).hover(function(){
					JS(id).addClass("hover");
				}, function(){
					JS(id).removeClass("hover");
				});
			};
			setHover("jesong_tools_emoticons");
			setHover("jesong_tools_screen");
			setHover("jesong_tools_opinion");
			setHover("jesong_tools_file");
			setHover("jesong_chat_send_btn");
			var soundFlag = JS.getCookie("jesong_chat_sound");
			if(soundFlag == null || soundFlag == "1"){
				this.sound = true;
			}else{
				this.sound = false;
			}
			if(this.sound){
				JS("jesong_tools_sound").show();
				JS("jesong_tools_quiet").hide();
			}else{
				JS("jesong_tools_quiet").show();
				JS("jesong_tools_sound").hide();
			}
			var setSound = function(){
				if(this.sound){
					this.sound = false;
					JS.setCookie("jesong_chat_sound", "-1");
					JS("jesong_tools_quiet").show();
					JS("jesong_tools_sound").hide();
				}else{
					this.sound = true;
					JS.setCookie("jesong_chat_sound", "1");
					JS("jesong_tools_sound").show();
					JS("jesong_tools_quiet").hide();
				}
			};
			if(jesong.autochat.tools && jesong.autochat.tools.quiet == "0"){
				JS("jesong_tools_quiet").hide();
				JS("jesong_tools_sound").hide();
			}else{
				JS("jesong_tools_sound").click(setSound);
				JS("jesong_tools_quiet").click(setSound);
			}
			var maxFun = function(){
				/*if(jesong.config.forceChatLogo != ""){
					JS("jesong_chat_min_logo").css("backgroundImage", "url('"+jesong.config.forceChatLogo+"')");
				}else{
					if(jesong.language == "en"){
						JS("jesong_chat_min_logo").css("backgroundImage", "url('"+jesong.env.server.file+"/images/auto/logo-en.png')");
					}else{
						JS("jesong_chat_min_logo").css("backgroundImage", "url('"+jesong.env.server.file+"/images/auto/logo-cn.png')");
					}
				}
				JS("jesong_chat_min_logo").empty().removeClass("jesong_message");*/
				
				if(jesong.jsType == 1 && jesong.env.isPhone && jesong.env.min == 0){
					jesong.util.openChat();
				}else{
					JS("jesong_chat_min").hide();
					this.showChatLayout();
					JS("jesong_chat_min").css("backgroundColor",  jesong.autochat.bgcolor).css("borderColor", jesong.autochat.bgcolor);
					JS("jesong_chat_min_text").html(jesong.autochat.pageTitle == "" ? JSLang[jesong.language].online : jesong.autochat.pageTitle);
					
					JS("jesong_pop_msg").hide();
					JS("jesong_chat_record").scrollTop(JS("jesong_chat_record").scrollTop()+9999);
				}
			};
			JS("jesong_pop_msg").click(maxFun);
			JS("jesong_chat_min").click(maxFun);
			JS("jesong_chat_min_max").click(maxFun);
//			JS("jesong_chat_send_btn").click(function(){
//				Chat.sendMessage.apply(Chat, []);
//			});
			
			//发送事件
			var Button = document.getElementById("jesong_chat_send_btn");
			var clickEvent = (function() {
				if ('ontouchstart' in document.documentElement === true){
					 return 'touchstart';
				}else{
					return 'click';
				}
			})();
			
			Button.addEventListener(clickEvent, function(e) {
				Chat.sendMessage.apply(Chat, []);
			})
			
			
			JS("jesong_message").keydown(function(e){
				if(e.keyCode == 13){
					this.sendMessage();
					var event=arguments.callee.caller.arguments[0]||e;
                    if (event && event.preventDefault) {
                            event.preventDefault();
                    } else if (window.event) {
                            window.event.cancelBubble = true;
                    }
					return false;
				}else{
					this.getfocus();
				}
				return true;
			}).focus(function(e){
				JS("jesong_opinion_layout").hide();
				JS("jesong_file_layout").hide();
				JS("jesong_emoticon_layout").hide();
			});
			
			var choose = function(opinion){
				var ch = opinion;
				if(ch < 0){
					ch = 0;
				}
				for(var i=0; i<5; i++){
					JS("jesong_op"+i).removeClass("jesong-star-1");
					JS("jesong_op"+i).removeClass("jesong-star-2");
					if(i<=ch){
						JS("jesong_op"+i).addClass("jesong-star-1");
					}else{
						JS("jesong_op"+i).addClass("jesong-star-2");
					}
				}
				Chat.opinionValue = opinion;
			};
			JS("jesong_op4").click(function(){
				choose(4);
			});
			JS("jesong_op3").click(function(){
				choose(3);
			});
			JS("jesong_op2").click(function(){
				choose(2);
			});
			JS("jesong_op1").click(function(){
				choose(1);
			});
			JS("jesong_op0").click(function(){
				choose(-1);
			});
			
			var hiddenOpinionLayout = function(){
				JS("jesong_mask").hide();
				JS("jesong_opinion_score").show();
				JS("jesong_opinion_reason").hide();
				JS("jesong_opinion_layout").hide();
			};
			JS("jesong_op_cancel").click(hiddenOpinionLayout);
			JS("jesong_opinion_reason_cancel").click(hiddenOpinionLayout);
			
			JS("jesong_opinion_reason_commit").click(function(){
				if(this.opinionValue != undefined){
					var desp = JS("jesong_opinion_reason_text").val();
					var rel = JS("jesong_opinion_reason_text").attr("rel");
					if(desp == rel){
						desp = "";
					}
					if(desp == ""){
						if(this.opinionValue == 1 || this.opinionValue == -1){
							JS("jesong_opinion_score").hide();
							JS("jesong_opinion_reason").show();
						}else{
							Chat.commitOpinion.apply(Chat, [this.opinionValue, desp]);
						}
					}else{
						Chat.commitOpinion.apply(Chat, [this.opinionValue, desp]);
					}
				}
			});
			
			
			if(jesong.autochat.tools && jesong.autochat.tools.opinion == "0"){
				JS("jesong_tools_opinion").hide();
			}else{
				JS("jesong_tools_opinion").click(function(){
					if(!this.isOpinioned() && this.chatId != null && this.chatId >0){
						if(JS("jesong_opinion_layout").isHidden()){
							JS("jesong_file_layout").hide();
							JS("jesong_emoticon_layout").hide();
							JS("jesong_mask").show();
							
							JS("jesong_opinion_layout").show();
							var _w = JS("jesong_opinion_layout").width();
							var _h = JS("jesong_opinion_layout").height();
							JS("jesong_opinion_layout").css("left", (jesong.autochat.width - _w)/2+"px")
													   .css("top", (jesong.autochat.height - _h)/2+"px");
						}else{
							JS("jesong_opinion_layout").hide();
						}
					}else if(this.chatId != null && this.chatId >0 && this.isOpinioned()){
						alert(JSLang[jesong.language].chatOpinioned);
					}
				});
			}
			JS("jesong_opinion_layout").hide();
			if(jesong.autochat.tools && jesong.autochat.tools.emoticons == "0"){
				JS("jesong_tools_emoticons").hide();
			}else{
				JS("jesong_tools_emoticons").click(function(){
					if(JS("jesong_emoticon_layout").isHidden()){
						JS("jesong_opinion_layout").hide();
						JS("jesong_file_layout").hide();
						JS("jesong_emoticon_layout").show();
					}else{
						JS("jesong_emoticon_layout").hide();
					}
				});
			}
			if(jesong.autochat.tools && jesong.autochat.tools.file == "0"){
				JS("jesong_tools_file").hide();
			}else{
				JS("jesong_tools_file").click(function(){
					if(this.chatId != null && this.chatId >0){
						if(JS("jesong_file_layout").isHidden()){
							JS("jesong_opinion_layout").hide();
							JS("jesong_emoticon_layout").hide();
							JS("jesong_file_layout").show();
							document.getElementById("jesong_file_frame").src= jesong.env.server.chat + "file.jsp?chatId="+this.chatId+"&cId="+this.companyId+"&vId="+this.visitorId+"&lang="+jesong.language;
						}else{
							JS("jesong_file_layout").hide();
						}
					}
				});
			}
			
			JS("jesong_file_layout").hide();
			JS("jesong_emoticon_layout").hide();
			
			JS("jesong_file_btn").click(function(){
				if(this.chatId != null && this.chatId >0){
					var fileFullPath = JS("jesong_file_input").val();
				    if(fileFullPath==''){
				    	alert(JSLang[jesong.language].uploadWarning);//请选择一个要上传的文件！
				    	return false;
				    }
				    var fileType = fileFullPath.substring(fileFullPath.lastIndexOf('.'));
				    var action = jesong.env.server.chat +'/receive.jsp?fileType='+fileType+'&chatId='+this.chatId+'&cId='+this.companyId;
				    var uploadForm = document.getElementById("jesong_file_form");
				    uploadForm.action=action;
				    uploadForm.submit();
				}
			});
			
			if(jesong.autochat.tools && jesong.autochat.tools.screen == "0"){
				JS("jesong_tools_screen").hide();
			}else{
				JS("jesong_tools_screen").click(function(){
					if(this.status == 2){
						var url = "ScreenCat://" + this.screenUrl
							+"/screenUpload.jsp?c="+this.companyId+"&cId="+this.chatId+"&v="+this.visitorId+"&n="+this.customerId;
						document.getElementById("jesong_frame").src= url;
						if(this.screenActiveX == false && this.screenActiveXTime == null){
							this.screenActiveXTime = window.setTimeout(function(){
								Chat.screenActiveXTime = null;
								//var msg="系统提示：系统检测到您尚未安装截屏插件， 请先<a href=\""+Chat.screenUrl+"/jesong-screen-1.0.rar\" target=\"jesong_frame\">下载</a>截屏插件并安装";
								var msg=JSLang[jesong.language].screenWarning1+"<a href=\""+Chat.screenUrl+"/jesong-screen-1.1.rar\" target=\"jesong_frame\">"
									+JSLang[jesong.language].screenWarning2+"</a>"+JSLang[jesong.language].screenWarning3;
								Chat.addMessage.apply(Chat, [msg, "system"]);
							}, 15000);
						}
					}
				});
			}
		}, 
		
		commitOpinion : function(op, desp){
			var data = {
				cId:this.chatId, c:this.companyId, op:op,b_op:4, desp:desp, v:this.visitorId, u:this.visitorStaticId
			};
			this.ajax("opinion", data, function(resp){
				this.ajax(
					'addEvent', 
					{c: this.companyId, cId: this.chatId, v: this.visitorId, cusId: this.customerId, msg: "finn", type:"EVENT_OPINION"}, 
					function(){
						this.addMessage(JSLang[jesong.language].chatOpinionedSuccess, "system");
					}
				);
				this.setOpinioned();
				JS("jesong_opinion_layout").hide();
				JS("jesong_mask").hide();
			});
			if(this.status != 2){
				JS("jesong_chat_min").hide();
				this.hideChatLayout();
			}
		},
		
		openchat : function(params){
			if(this.requesting == false){
				this.requesting = true;
				
				if(typeof params == "object"){
					params["_CR"] = "1";
					if(jesong.ocpc){
						params.ocpcPlatform = jesong.ocpc.platform;
						params.ocpcCondition = jesong.ocpc.condition;
						params.ocpcConfigId = jesong.ocpc.configId;
					}
				}else if(typeof params == "string"){
					params += "&_CR=1";
					if(jesong.ocpc){
						params += "&ocpcPlatform=" + jesong.ocpc.platform;
						params += "&ocpcCondition=" + jesong.ocpc.condition;
						params += "&ocpcConfigId=" + jesong.ocpc.configId;
					}
				}
				
				
				this.ajax("chat", params, function(rs){
					this.groupId = rs.groupId;
					if(rs.type == "WAIT_QUEUE"){
						this.initChatUI();
						this.status = 1;
						JS.setCookie("jesong_queue_"+this.companyId+"_id", this.groupId, 60*10*1000);
						if(rs.waitSendMessage && rs.waitSendMessage != ""){
							jesong.autochat.waitSendMsg = rs.waitSendMessage;
						}
						this.handlerWaitQueue(rs);
					}else if(rs.type == "CHATING"){
						//对话建立成功
						if(rs.visitorId){
							visitorId = rs.visitorId;
						}
						if(!rs.isChatExists && rs.waitSendMessage != ""){
							jesong.autochat.waitSendMsg = rs.waitSendMessage;
						}
						this.initChatUI();
						this.connectSuccess(rs);
						window.clearTimeout(this.autoPopMWinTimeout);
						
						this.sendkeyWord(rs.keyWord);
						//
					}else if(rs.type == "ERROR"){
						alert("\u914d\u7f6e\u9519\u8bef\u5bfc\u81f4\u521b\u5efa\u5bf9\u8bdd\u5931\u8d25\uff0c\u8bf7\u68c0\u67e5\u76f8\u5e94\u7684\u914d\u7f6e");
					}else{
						//if(jesong.autochat && jesong.autochat.use == 1 && jesong.autochat.show)
						//不在线， 转留言
						this.initLeaveMsgUI();
						JS("jesong_lm_content").val(jesong.autochat.waitSendMsg);
						jesong.autochat.waitSendMsg = "";
						JS("jesong_lm_content").focus();
					}
					this.showChatLayout();
					this.requesting = false;
				});
			}
		},
		sendkeyWord : function(keyWord){
			if(document.referrer && jesong.env.min == 1 && jesong.autochat.sendkeyWord == 1 && keyWord){
				var reg = /[\u4e00-\u9fa5]/g;
				if(reg.test(keyWord)){
					jesong.autochat.sendkeyWord = 0;
					this.sendMessage(keyWord);
				}
			}
		},
		waitQueue : function(first){
			this.ajax("waitQueue", {
				c : this.companyId,
				v : this.visitorId,
				u : this.visitorStaticId,
				g : this.groupId
			}, function(rs){
				this.setReady();
				this.handlerWaitQueue(rs, first);
			});
		},
		
		/**
		 * 处理排队
		 * @param rs
		 */
		handlerWaitQueue : function(rs, first){
			if(first && (rs.type == "CHATING" || rs.type == "WAIT_QUEUE")){
				this.initChatUI();
				this.showChatLayout();
			}
			if(rs.type == "CHATING"){
				//JS("jesong_chat_record").empty();
				this.connectSuccess(rs);
			}else if(rs.type == "WAIT_QUEUE"){
				this.status = 1;
				var i=rs.waitIndex;
				//JS("jesong_chat_record").empty();
				//对不起，目前在线客服繁忙，您前面有 位客户等待
				if(JS("jesong_wait_text").exist()){
					JS("jesong_wait_text").html(JSLang[jesong.language].queuePrompt1+'<font id="jesong_wait_num" color="red">'+ i + '</font>'+JSLang[jesong.language].queuePrompt2);
				}else{
					this.addMessage('<span id="jesong_wait_text">'+JSLang[jesong.language].queuePrompt1+'<font id="jesong_wait_num" color="red">'+ i + '</font>'+JSLang[jesong.language].queuePrompt2+'</span>', "system");
				}
				this.waitQueueTimeout = window.setTimeout(function(){
					if(Chat.status == 1){
						Chat.showChatLayout.apply(Chat, []);
						Chat.waitQueue.apply(Chat, []);
					}else{
						Chat.hideChatLayout.apply(Chat, []);
					}
				}, 3000);
			}else if(rs.type == "OFFLINE"){
				//此时退出排队或者建立了对话
				JS.setCookie("jesong_queue_"+this.companyId+"_id", "", -1);
			}
		},
		exitQueue : function(){
			this.status = 0;
			window.clearTimeout(this.waitQueueTimeout);
			JS.setCookie("jesong_queue_"+this.companyId+"_id", "",  -1);
			this.ajax("exitQueue", {
				c : this.companyId,
				v : this.visitorId,
				g : this.groupId
			}, function(rs){
			});
		},
		
		/**
		 * 开始创建对话
		 */
		connect : function(chatId, customerId){
			this.firstGetMessage = true;
			this.initChatUI();
			if(jesong.jsType != 1 || !jesong.env.isPhone){
				this.showChatLayout();
			}
			if(this.requesting == true){
				return;
			}
			this.requesting = true;
			JS("jesong_chat_record").empty();
			this.visitorId = jesong.env.vId;
			this.visitorStaticId = jesong.env.uId;
			var tag = JS.getCookie("jesong_chat_tag_"+chatId);
			var params = {
				chatUrl : window.location.href,
				c : this.companyId,
				v : this.visitorId,
				u : this.visitorStaticId,
				sid : this.siteId,
				cId : chatId,
				n : customerId,
				promotionId : jesong.env.promotionId,
				sf : jesong.env.spreadFlag,
				tag : tag == null || tag == "" ? 0 : tag,
				_CR: "1"
			};
			
			if(jesong.env.spreadFlag){
				params["sf"] = jesong.env.spreadFlag;
			}
			if(jesong.env.firstPage){
				params["first"] = jesong.env.firstPage;
			}else{
				params["first"] = window.location.href;
			}
			if(jesong.util.getCookie('JESONG_VC')){
				params["vc"] = jesong.util.getCookie("JESONG_VC");
			}
			
			if(jesong.env.refer && jesong.env.refer !=""){
				params["ref"] = jesong.env.refer;
			}else if(document.referrer){
				try{
					var refer = document.referrer;
					if(refer){
						var referDomain = jesong.util.getDomain(refer);
						var currDomain = window.location.host;
						if(referDomain && referDomain == currDomain){
							refer = "";
						}
					}
					if(refer != ""){
						params["ref"] = refer;
					}
				}catch(e){};
			}
			var ext = jesong.util.getReseveKey();
			if(ext != null && ext != ""){
				params["ext"] = ext;
			}
			this.ajax("chat", params, function(rs){
				if(rs.type == "CHATING"){
					//对话建立成功
					this.connectSuccess(rs);
				}/*else{
					JS.setCookie("jesong_chat_"+this.companyId+"_id", "", -1);
					JS.setCookie("jesong_chat_user_"+this.companyId+"_id", "", -1);
				}*/
				this.requesting = false;
			});
		},
		/**
		 * 创建对话成功
		 */
		connectSuccess : function(rs){
			this.chatId = rs.chatId;
			if(rs.groupId){
				this.groupId = rs.groupId;
			}
			this.customerId = rs.userInfo.userId;
			this.customerNick = rs.userInfo.nickName;
			JS.setCookie("jesong_queue_"+this.companyId+"_id", "", -1);
			//发送对话接通语
			this.receivemessageNum = 0;
			this.receiveSelfMessageNum = 0;
			this.status = 2;
			if(rs.visitorId){
				this.visitorId = rs.visitorId;
			}
			//JS.setCookie("jesong_chat_"+this.companyId+"_id", this.chatId);
			//JS.setCookie("jesong_chat_user_"+this.companyId+"_id", this.customerId);
			this.resetOpinioned();
			this.firstGetMessage = true;
			this.startReceiveMessage();
			//JS("jesong_chat_record").append("<div style=\"width:100%;height:22px;line-height:22px;text-align:center;color:#aaaaaa;clear:both;\">2015-12-15 12:22:33</div>");
			JS("jesong_chat_record").empty();
			//if(jesong.jsType != 1 || !jesong.env.isPhone){
				this.addTopImage();
				if(rs.userInfo.tagMessage){
					this.addMessage(rs.userInfo.tagMessage, "welcome");
				}else{
					var welcome = this.words.welcome;
					if((jesong.jsType == 1 && jesong.env.isPhone) || jesong.env.min == 1){
						welcome = this.words.welcomeMin ? this.words.welcomeMin : this.words.welcome;
					}
					if(welcome != ""){
						this.addMessage(welcome, "welcome");
					}
				}
				if(rs.userInfo.tag){
					JS.setCookie("jesong_chat_tag_"+this.chatId, rs.userInfo.tag, 60*60*1000);
				}else{
					JS.setCookie("jesong_chat_tag_"+this.chatId, "", -1);
				}
			//}
			if(jesong.autochat.waitSendMsg != ""){
				this.sendMessage(jesong.autochat.waitSendMsg);
				jesong.autochat.waitSendMsg="";
			}
			
			if(jesong.autochat.mask == "1"){
				if(!JS("jesong_chat_mask").exist()){
					var _div = document.createElement("div");
					_div.id = "jesong_chat_mask";
					_div.className = "jesong_chat_mask";
					document.body.appendChild(_div);
				}
				
				var b = jesong.util.getBody();	
				JS("jesong_chat_mask").show()
									  .css("width", Math.max(b.scrollWidth,b.clientWidth)+ "px")
									  .css("height", Math.max(b.scrollHeight,b.clientHeight) + "px");
			}
			
			/*if(this.words.greeting != ""){
				this.addMessage(this.words.greeting, "user");
			}*/
			this.setAutoPopMWinTimes();
		},
		/**
		 * 获取聊天窗口参数
		 */
		getWinChatParam : function(){
			if(jesong.env.min != 1){
				this.hideChat();
			}
			if(this.status == 2){
				//this.status = 0;
				//this.stopReceiveMessage();
				return {
					chatting : true,
					customerId : this.customerId,
					chatId : this.chatId,
					sound : (this.sound ? "1" : "0")
				};
			}else{
				return {
					chatting : false,
					sound : (this.sound ? "1" : "0")
				};
			}
		},
		showPopMessage : function(content){
			if(content != null/* && this.status == 2*/){
				if(content.length > 20){
					content = content.substring(0, 18) + "...";
				}
				var jesongPopMsgHead = jesong.autochat.popMsg.head;
				if(jesongPopMsgHead == ""){
					jesongPopMsgHead = jesong.env.server.file+"/images/chat/201805/head.png";
				}
				var jesongPopMsgTitle = jesong.autochat.popMsg.title;
				if(jesongPopMsgTitle == ""){
					jesongPopMsgTitle = "\u60a8\u6709\u65b0\u7684\u5ba2\u6237\u6d88\u606f\uff0c\u8bf7\u6ce8\u610f\u67e5\u770b\uff01";
				}
				var jesongPopMsgBgColor = jesong.autochat.popMsg.bgColor;
				var jesongPopMsgColor = jesong.autochat.popMsg.color;
				var jesongPopMsgOpacity = jesong.autochat.popMsg.opacity;
				JS("jesong_pop_msg").show()/*.css("opacity", jesongPopMsgOpacity/100).css("filter", "alpha(opacity:"+jesongPopMsgOpacity+")")
									.css("backgroundColor", jesongPopMsgBgColor).css("color", jesongPopMsgColor)*/
								    .html("<div class=\"jesong-top-bubble\">" +
									    		"<div class=\"jesong-top-bubble-icon\">" +
									                "<img src=\""+jesongPopMsgHead+"\" alt=\"\">"+
									            "</div>"+
									            "<div id=\"jesong_unread\" class=\"jesong-top-badge-warning\">0</div>"+
									            "<div class=\"jesong-top-bubble-con\">"+
									                "<div class=\"title\">"+
									                    "<h4>"+jesongPopMsgTitle+"</h4><span>"+this.date2Text(new Date(), "hh:mm")+"</span>"+
									                "</div>"+
									                "<p>"+content+"</p>"+
									            "</div>"+
									        "</div>"
								    );
			}
		},
		hideChat : function(){
			JS("jesong_chat_layout").hide();
			JS("jesong_chat_min").hide();
			//JS("jesong_chat_record").empty();
		},
		flashtitleInterval : -1,
		windowFocus : true,
		unReadMsgStep : 1,
		unReadMsgTitle : JSLang[jesong.language].newMessage,//【您有新的消息】
		commonTitle : document.title,
		flashTitle : function(){
			window.focus();
			if(this.windowFocus==false){
				this.unReadMsgStep++;
				if (this.unReadMsgStep==3) {this.unReadMsgStep=1;}   
			    if (this.unReadMsgStep==1) {document.title=this.unReadMsgTitle;}   
			    if (this.unReadMsgStep==2) {document.title=this.commonTitle;} 
			    if(this.flashtitleInterval == -1){
			      	this.flashtitleInterval=window.setInterval(function(){
			      		try{
			      		Chat.flashTitle.apply(Chat, []);
			      		}catch(e){}
			      	}, 500); 
			    }
			}
		},
		playSound : function(msg){
			if(this.sound){
				var wav = jesong.env.server.file + "wav/sound.wav";
				var borswer = window.navigator.userAgent.toLowerCase();  
				try{
					if (borswer.indexOf( "ie" ) >= 0 ){
						JS("jesong_sound").empty().html('<bgsound src="'+wav+'"/>'); 
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
			if(jesong.jsType == 1 && jesong.env.isPhone){
				if(!this.isChatLayoutShow()){
					this.showPopMessage(msg);
					this.showUnReadMessage();
				}
			}else{
				if(!JS("jesong_chat_min").isHidden()){
					this.showUnReadMessage();
					/*JS("jesong_chat_min_logo").css("backgroundImage", "url('"+jesong.env.server.file+"/images/auto/icon-message-high.png')")
										      .addClass("jesong_message").html(JSLang[jesong.language].newMessage);*/
					JS("jesong_chat_min").css("backgroundColor", "#FF7E00").css("borderColor", "#FF7E00");
					JS("jesong_chat_min_text").html(JSLang[jesong.language].newMessage);
					//JS("jesong_pop_msg").show();
					//JS("jesong_pop_msg_user").html(this.customerNick);
					//JS("jesong_pop_msg_text").html(msg);
				}
			}
		},
		clearFlashTitle : function(){
			document.title=this.commonTitle;
			if(this.flashtitleInterval==-1){
				return;
			}
			window.clearInterval(this.flashtitleInterval);
			this.flashtitle = -1;
		},
		/**
		 * 接受消息
		 */
		startReceiveMessage : function(){
			Chat.getMessage.apply(Chat, []);
			if(!this.receiveMessageInterval){
				this.receiveMessageInterval = window.setInterval(function(){
					Chat.getMessage.apply(Chat, []);
				}, 3000);
			}
		},
		getMessage : function(){
			var now = new Date().getTime();
			//避免由于上一次请求未在本次请求前返回，而出现重复消息的问题
			if(this.lastGetMessageTime != null && now - this.lastGetMessageTime < 6000){
				return;
			}
			//窗口最大化（也有可能是因为清理了cookie）, 此时将窗口隐藏掉
			/*var chatId = JS.getCookie("jesong_chat_"+this.companyId+"_id");
			if(!chatId){
				this.hideChat();
			}*/
			var s = this.receivemessageNum;
			var s1 = this.receiveSelfMessageNum;
			this.lastGetMessageTime = now;
			var params = {
				c: this.companyId,
				v: this.visitorId,
				u: this.visitorStaticId,
				cId: this.chatId,
				start:this.receivemessageNum,
				vstart:this.receiveSelfMessageNum
			};
			this.ajax("getMessage", params, function(resp){
					if(s == this.receivemessageNum && s1 == this.receiveSelfMessageNum){
						this.lastGetMessageTime = null;
						this.processMessage(resp.msgs || []);
						this.firstGetMessage = false;
					}else{
						this.getMessage();
					}
				}
			);
		},
		/**
		 * 处理接收到的消息
		 */
		processMessage : function(msgs){
			for (var i=0; i<msgs.length; i++) {
				var msg = msgs[i];
				var target = "user";
				if(msg.fromUserId == this.visitorId){
					target = "visitor";
					this.receiveSelfMessageNum++;
				}else{
					this.receivemessageNum++;
				}
				/*try{
					if(jesong.jsType == 1 && jesong.env.isPhone){
						var lastMessage = JS.getCookie("jesong_rec_"+this.chatId);
						if(target == "visitor" || (lastMessage != null && lastMessage != "" && this.receivemessageNum <= parseInt(lastMessage))){
							continue;
						}
					}
				}catch(e){}*/
				
				if(msg.type == "EVENT_CLOSE" || msg.type == "EVENT_END"){
					if(this.status == 2){
						this.status = 3;
						this.addMessage(this.words.disconnect, target, msg.createTime);
						this.closeChat();
					}else{
						this.receivemessageNum--;
					}
				}else if(msg.type == "EVENT_SAMEVISITOR"){
					//this.addMessage("您与客服又建立了一个对话， 当前对话已结束", target);
					this.addMessage(JSLang[jesong.language].sameChat, target, msg.createTime);
					this.closeChat();
					this.status = 3;
				}else if (msg.type == "EVENT_TRANSCHAT") {				
					this.customerId = msg.exts.customerId;    
					this.chatId = msg.exts.chatId;  
					//transHidden
					
					JS.setCookie("jesong_chat_"+this.companyId+"_id", this.chatId);
					
					if(this.customerId.indexOf("/")>0){
						this.customerId = this.customerId.substring(0, this.customerId.indexOf("/"));
					}
					JS.setCookie("jesong_chat_"+this.companyId+"_id", this.chatId);
					JS.setCookie("jesong_chat_user_"+this.companyId+"_id", this.customerId);
					
					var hidden = msg.exts.hidden;
			        if(!hidden){
			        	this.customerNick = msg.exts.nickName;  
						if(this.words.transchat){
							this.addMessage(this.words.transchat, "system", msg.createTime);
						}
						this.addMessage(this.words.transchatTo + this.customerNick, "system", msg.createTime);
						this.transHidden = false;
			        }else{
			        	this.transHidden = true;
			        }
					this.status = 3;
					this.receivemessageNum = 0;
					this.receiveSelfMessageNum = 0;
				} else if (msg.type == "EVENT_TRANSCHAT_SUCCESS") {
					if(this.status != 2){
						if(!this.transHidden){
							this.addMessage(this.words.transchatSuccess, "system", msg.createTime);
						}
						this.status = 2;
						this.resetOpinioned();
						//Customer_getCustomer(getCustomer_do,customerId,companyId);
					}
				} else if (msg.type == "RECORD_FILE") {            
					//this.addMessage("对方给您传送了一个文件， 请点击<a href=\""+msg.message+"\" target=\"_blank\">下载</a>。", target); 
					var url = msg.message;
					if(!url.indexOf("https") == 0){
						url = url.replace("http", jesong.env.scheme);
						url = url.replace(":80", ":"+jesong.env.schemePort);
					}
					this.addMessage(JSLang[jesong.language].downloadFile1 + "<a href=\""+url+"\" target=\"_blank\">"+JSLang[jesong.language].downloadFile2+"</a>\u3002", target, msg.createTime); 
				} else if(msg.type == "RECORD_SCREENSHOTS" || msg.type == "EVENT_SCREENSHOTS_SELF"){		
					var url = msg.message;
					if(!url.indexOf("https") == 0){
						url = url.replace("http", jesong.env.scheme);
						url = url.replace(":80", ":"+jesong.env.schemePort);
					}
					var msgHtml = '<a href="'+ url + '" target="_blank"><img src="' + url + '" width="' + 100 + '" ';
					msgHtml += '  border="0"></a>';
					if(msg.type == "EVENT_SCREENSHOTS_SELF"){
						this.screenActiveX = true;
						if(this.screenActiveXTime){
							window.clearTimeout(this.screenActiveXTime);
							this.screenActiveXTime = null;
						}
						target = "visitor";
					}
					this.addMessage(msgHtml, target, msg.createTime);	
				} else if(msg.type == "EVENT_OPINION"){      				
					//this.addMessage("对方请你对此次服务进行<a href=\"javascript:void(0);\" onclick=\"document.getElementById('jesong_opinion_layout').style.display='block';\" target=\"_blank\">评价</a>。", target); 
					this.addMessage(JSLang[jesong.language].opinion1 + "<a href=\"javascript:void(0);\" onclick=\"document.getElementById('jesong_opinion_layout').style.display='block';\" target=\"_blank\">"+JSLang[jesong.language].opinion2+"</a>", target, msg.createTime); 
				} else if(msg.type == "RECORD_RECORD"){ //普通信息查收
					var m = msg.message;
					m = m.replaceAll("/live/emoticon/new/", "/emoticon/");
					this.addMessage(m, target, msg.createTime); 
				} else if(msg.type == "RECORD_RICH_TEXT"){
					var m = msg.message;
					m = m.replaceAll("/live/emoticon/new/", "/emoticon/");
					this.addMessage(m, target, msg.createTime); 
				}else if(msg.type == "EVENT_GETFOCUS"){
					/*if(!this.firstGetMessage){
						this.showCustInputing();
					}*/
				}
			}
		},
		clearCustInputing : function(){
			JS("jesong_inputing").remove();
		},
		showCustInputing : function(){
			this.clearCustInputing();
			JS("jesong_chat_record").append(tpl(user_tpl_inputing_phone, {msg: "\u6b63\u5728\u8f93\u5165...", nickName:this.customerNick}));
			JS("jesong_chat_record").scrollTop(JS("jesong_chat_record").scrollTop()+100000);
		},
		getfocus : function(){
			var message = JS("jesong_message").val();
			var now = new Date().getTime();
			var rel = JS("jesong_message").attr("rel");
			if(message == rel){
				message = "";
			}
			if((this.lastGetFocusTime == null || now - this.lastGetFocusTime > 2000) && this.status == 2){
				if(jesong.inputText == 1){
					if(jesong.visitorPhoneVerification == 1 && !getFiveEle(message) || jesong.visitorPhoneVerification == 0){
						this.sendFocus(message);
					}
				}
			}
			if(!this.focusTimeout){
				this.focusTimeout = window.setTimeout(function(){
					Chat.getfocus.apply(Chat, []);
				}, 3000);
			}
		},
		sendFocus : function(msg){
				this.ajax(
						'addEvent', 
						{c: this.companyId, cId: this.chatId, v: this.visitorId, cusId: this.customerId, msg: msg, type:"EVENT_GETFOCUS"}, 
						function(){}
					);
			if(this.focusTimeout){
				window.clearInterval(this.focusTimeout);
				this.focusTimeout = null;
			}
			this.lastGetFocusTime = new Date().getTime();
		},
		
		//弹窗内操作
		windowFun:function(){
			//取消按钮
			JS("msgCancel").click(function(){
				var cancelMsg = JS("jesong_message").val();
				var phoneM = JS("msgPhone").val();
				this.clearTime();
				if(isphone(phoneM)){
					savePhone(phoneM);
					JS("jesong_message").val(cancelMsg);
				}
				JS("yl_msgBox").remove();
			})
			
			//确认按钮
			JS("msgConfirm").click(function(){
				var phoneM = JS("msgPhone").val();
				var code = JS("codeVal").val();
				JS("tips").addClass("red").removeClass("green");
				if(isNullOrEmpty(phoneM)){
					JS("tips").html(JSLang[jesong.language].phoneNotNull);
				}
				if(isNullOrEmpty(code)){
					JS("tips").html(JSLang[jesong.language].codeNotNull);
				}else if(code.length != 4){
					JS("tips").html(JSLang[jesong.language].codeError);
				}else{
					JS.ajax(Chat.screenUrl+'verify.do?c='+ Chat.companyId +'&command=verifyMobileCode&m='+ phoneM +'&vsid='+ Chat.visitorId +'&cid='+ Chat.chatId +'&state=0&code='+ code,
							'',
							function(data){
								var list = data.data;
						    	msg=JS("jesong_message").val();
						    	if(data.code == 200 && list.verify == true){
						    		var getPhone = sessionStorage.getItem('phone');
						    		Chat.phoneArr.push(list.mobile);
						    		JS("yl_msgBox").remove();
						    		sessionStorage.setItem('phone',JSON.stringify(Chat.phoneArr));
						    		Chat.clearTime();
						    		JS("tips").html("");
						    		JS("jesong_message").val("");
						    		Chat.sendMessage(msg);
//						    		Chat.sendMessage(list.mobile);
						    	    Chat.addMessage(JSLang[jesong.language].checkSuccess, "system");
						    	    
						    	    
						    	}else{
						    		if(list.state == 1000){
						    			JS("tips").html(JSLang[jesong.language].timeOut);
						    		}else if(list.state == 1001){
						    			JS("tips").html(JSLang[jesong.language].codeUndefind);
						    		}else if(list.state == 1002){
						    			JS("tips").html(JSLang[jesong.language].codeError);
						    		}
						    	}
							})
				}
			})
			
			//验证码按钮触发
			JS("sedMsg").click(function(){
				var phoneNum = JS("msgPhone").val();
				if(isphone(phoneNum)){
					JS.ajax(Chat.screenUrl+'verify.do?c='+ Chat.companyId +'&command=sendVerificationCode&m='+ phoneNum +'&cid='+ Chat.chatId,'',
					function(data){
						if(data.code == 200){
							document.getElementById('tips').innerHTML = '';
				    		getRandomCode();
				    	}else{
				    		JS("tips").addClass("red").removeClass("green");
				    		JS("tips").html(JSLang[jesong.language].exceed);
				    	}
					})
				}else{
					JS("tips").addClass("red").removeClass("green");
					JS("tips").html(JSLang[jesong.language].formatError);
				}
			})
			
			
			//倒计时
			function getRandomCode() {
				JS("tips").html("");
			   if (Chat.time === 0) { 
			       Chat.time = 60;
			       JS("sedMsg").removeClass("gray");
			       JS("sedMsg").html(JSLang[jesong.language].sendCode);
			       return;
			   } else { 
				   JS("sedMsg").addClass("gray");
				   Chat.time--;
			       JS("sedMsg").html(Chat.time+JSLang[jesong.language].resetSend);
			   } 
			   Chat.t = setTimeout(function() { 
			       getRandomCode();
			   },1000);
			}
		},
		
		clearTime:function(){//清空计时器
			clearTimeout(Chat.t);
			Chat.time = 60;
			JS("sedMsg").removeClass("gray");
			JS("sedMsg").html(JSLang[jesong.language].sendCode);
		},
		
		//模拟点击取消
		timeClick:function(){
			JS("msgCancel").click();
		},
		
		//发送消息手机号验证
		msgCheck:function(msg){
			var t;
			var msg = JS("jesong_message").val();
//			var phoneNum = msg.replace(/[^0-9]?/ig,"");
			var phoneNum = getValue(msg);
			var html = '<div id="yl_msgBox"><div id="msgCheck">'+
							'<div class="msgCon">'+
								'<p><b><img src="'+ jesong.env.server.file +'static/2019/image/pcWarning.png" alt=""/></b>'+ JSLang[jesong.language].tips +'</p>'+
								'<input type="text" placeholder="'+ JSLang[jesong.language].placeholder +'" id="msgPhone"/>'+
								'<div class="vcodeBox">'+
									'<input id="codeVal" type="text" />'+
									'<a href="javascript:void(0)" id="sedMsg">'+ JSLang[jesong.language].sendCode +'</a>'+
								'</div>'+
								'<div class="tips green" id="tips"></div>'+
							'</div>'+
							'<div class="msgBtn">'+
								'<a href="javascript:void(0)" id="msgCancel">'+ JSLang[jesong.language].cancel +'</a>'+
								'<a href="javascript:void(0)" id="msgConfirm">'+ JSLang[jesong.language].confirm +'</a>'+
							'</div>'+
						'</div></div>';
			JS("yl_msgBox").remove();
			this.clearTime();
			JS("jesong_chat_record").append(html);
			JS("msgPhone").val(phoneNum);
			clearTimeout(t);
			t = setTimeout(this.timeClick(),300000);
			this.windowFun();
			JS("jesong_chat_record").scrollTop(JS("jesong_chat_record").scrollTop()+100000);
			return false;
		},
		
		sendMessage : function(message, times){
			if(!message){
				message = JS("jesong_message").val();
			}
			if(!times){
				times = 1;
			}
			if(times > 3){
				alert(JSLang[jesong.language].msgSendError);
				return;
			}
			
			var rel = JS("jesong_message").attr("rel");
			if(!isNullOrEmpty(message) && message != rel){
				JS("jesong_chat_min").hide();
				this.showChatLayout();
				//链接成功
				if(this.status == 2){
					//如果开启了短信验证功能
					var msg =JS("jesong_message").val();
					var phoneNum = getValue(msg);
					var sessionBox = sessionStorage.getItem('phone');
					if(isNullOrEmpty(sessionBox)){
						sessionBox = [];
					}

					if(jesong.visitorPhoneVerification == 1 && isphone(phoneNum) && sessionBox.indexOf(phoneNum) == -1){
						this.msgCheck(msg);
						return false;
					}
					
					//接收到的消息  提交给后台  验证是否链接中
					this.ajax('addMessage', {c: this.companyId, cId: this.chatId, v: this.visitorId, msg: replaceHtml(message)}, function(resp){
						if(resp.res == 0){ //如果当前对话存在
							this.receiveSelfMessageNum++;
							this.addMessage(replaceEmoticon(replacelinkHtml(message)), "visitor");
						}else if(resp.res == 1){ //
							if(this.status == 2){
								this.status = 3;
								this.addMessage(this.words.disconnect, "system");
								this.closeChat();
							}
						}
					}, function(){ //失败 自动请求
						window.setTimeout(function(){
							Chat.sendMessage.apply(Chat, [message, times+1]);
							JS("jesong_message").val(message);
						}, 500);
					});
					
					this.sendFocus(""); //更新对话状态
					JS("jesong_message").val("");
					if(jesong.jsType == 1 && jesong.env.isPhone){
					}else{
						JS("jesong_message").focus();
					}
					window.setTimeout(function(){
						JS("jesong_message").val("");
					}, 100);
					flag = true;
				}else if(this.status == 1){
					//正在排队
					alert(JSLang[jesong.language].chatClosed);
					//alert("对话已经结束！");
				}else{
					//重新创建对话
					jesong.util.openChat("g="+jesong.monitor.config.group);
					jesong.autochat.waitSendMsg = message;
					//jesong.autochat.show = false;
				}	
				
			}
		},
		/**
		 * 结束对话
		 */
		closeChat : function(){
			if(this.status == 2){
				if(this.chatId != null && this.chatId!=''){
					this.ajax("endChat", {
						c : this.companyId, 
						cId : this.chatId, 
						v : this.visitorId,
						u : this.visitorStaticId,
						type : "force"
					}, function(){
					});
				}
			}
			JS.setCookie("jesong_chat_"+this.companyId+"_id", "", -1);
			JS.setCookie("jesong_chat_user_"+this.companyId+"_id", "", -1);
			this.status = 3;
			this.stopReceiveMessage();
			JS("jesong_chat_mask").hide();
		},
		/**
		 * 停止接受消息
		 */
		stopReceiveMessage : function(){
			if(this.receiveMessageInterval){
				window.clearInterval(this.receiveMessageInterval);
				this.receiveMessageInterval = null;
			}
		},
		date2Text : function(v, fmt) {
			var fv = function(s){
				if(s<10){
					return '0'+s;
				}else{
					return s;
				}
			};
			if(typeof v=='string'){
				return v;
			}
			if (typeof v == 'number'){
				var v1 = new Date();
				v1.setTime(v);
				v = v1;
			} 
			if(v&&typeof v == 'object'){
				var fmtItem = ['yyyy','MM','dd','hh','mm','ss'];
				if(!fmt){
					fmt = 'yyyy-MM-dd';
				}
				var exp = new RegExp('(' + fmtItem.join('|') + ')', "g");
	            var fv = function(v) {
	                return v < 10 ? ('0' + v) : v;
	            };
	            return fmt.replace(exp, function($1, $2) {
	                switch ($2) {
	                    case 'yyyy':return v.getFullYear();
	                    case 'MM':return fv(v.getMonth() + 1);
	                    case 'dd':return fv(v.getDate());
	                    case 'hh':return fv(v.getHours());
	                    case 'mm':return fv(v.getMinutes());
	                    case 'ss':return fv(v.getSeconds());
	                }
	            });
			}else{
				return '';
			}
		}, getTimestamp : function(v){
			var now = new Date();
			if (typeof v == 'number'){
				now.setTime(v);
			}
			var h = now.getHours();
		    var mm = now.getMinutes();
		    return (h>9 ? h : "0"+h)+":"+(mm > 9 ? mm : "0"+mm );
		},
		showChatLayout : function(){
			if(jesong.autochat && jesong.autochat.hideMonitor){
				jesong.monitor.hide();
			}
			if(jesong.jsType == 1 && jesong.env.isPhone){
				var _mask = document.getElementById("jesong_phone_mask");
				if(!_mask || _mask.length == 0){
					var _div = document.createElement("div");
					_div.id = "jesong_phone_mask";
					_div.className = "jesong_phone_mask";
					document.body.appendChild(_div);
				}
				this.wapScrollTop = document.documentElement.scrollTop || document.body.scrollTop;//document.body.scrollTop;
				var bodyclass = document.getElementsByTagName("body")[0].className;
				if(bodyclass.indexOf("jesong_full_body") == -1){
					document.getElementsByTagName("body")[0].className += " jesong_full_body"; 
				}
				JS("jesong_chat_layout").css("position", "fixed");
			}
			JS("jesong_chat_layout").show();
			this.resetUnReadMessage();
		},
		increaseUnReadMessage : function(){
			var num = JS.getCookie("jesong_chat_unread_"+this.chatId);
			if(num && num != ""){
				JS.setCookie("jesong_chat_unread_"+this.chatId, parseInt(num)+1, 60*60*1000);
			}
		},
		resetUnReadMessage : function(){
			JS.setCookie("jesong_chat_unread_"+this.chatId, "0", 60*60*1000);
			JS("jesong_unread").hide();
		},
		showUnReadMessage : function(){
			var num = JS.getCookie("jesong_chat_unread_"+this.chatId);
			if(num && num != ""){
				num = parseInt(num);
			}else{
				num = 0;
			}
			if(num == 0){
				JS("jesong_unread").hide();
			}else{
				JS("jesong_unread").show().html(num > 9 ? "9+" : num);
			}
		},
		hideChatLayout : function(){
			if(jesong.jsType == 1 && jesong.env.isPhone){
				var cls = "jesong_full_body";
				var reg = new RegExp('(\\s|^)'+cls+'(\\s|$)'); 
				document.getElementsByTagName("body")[0].className = document.getElementsByTagName("body")[0].className.replace(reg,' '); 
				document.body.scrollTop = this.wapScrollTop;
				document.documentElement.scrollTop = this.wapScrollTop;
				JS("jesong_chat_layout").css("position", "fixed");
				document.body.removeChild(document.getElementById("jesong_phone_mask"));
			}
			JS("jesong_chat_layout").hide();
			/*if(jesong.autochat && jesong.autochat.hideMonitor){
				jesong.monitor.show();
			}*/
		},
		isChatLayoutShow : function(){
			return !JS("jesong_chat_layout").isHidden();
		},
		addTopImage : function(){
			var img = jesong.autochat.topImage;
			if((jesong.jsType == 1 && jesong.env.isPhone) || jesong.env.min == 1){
				img = jesong.autochat.topImageMin ? jesong.autochat.topImageMin : jesong.autochat.topImage;
			}
			if(img != ""){
				JS("jesong_chat_record").append("<div class=\"jesong-top-image\"><img src=\""+img+"\"></div>");
			}
		},
		initCallEvent : function(){	
		}, addMessage : function(msg, type, time){
			var flag = true;
			var _tpl;
			if(time){
				time = this.getTimestamp(time);
			}else{
				time = this.getTimestamp();
			}
			
			if(!this.firstGetMessage /*&& jesong.jsType == 1 && jesong.env.isPhone*/){
		    	if (type == "visitor") {
		    		window.setTimeout(function(){
		    			Chat.showCustInputing.apply(Chat, []);
		    		}, 2000);
		    	}else{
		    		this.clearCustInputing();
		    	}
		    }
			
			msg = msg.replaceAll("\n", "<br>");
			var m = {msg: msg, nickName:this.customerNick, time:time, path:jesong.env.server.file};
			
			if(jesong.jsType == 1 && jesong.env.isPhone){
				if(type == "user"){
					_tpl = user_tpl_phone;
				}else if(type == "system"){
					_tpl = system_tpl;
				}else if(type == "welcome"){
					_tpl = user_tpl_phone;
					m["welcome"] = JSLang[jesong.language].welcome;
				} else{
					_tpl = visitor_tpl_phone;
					flag = false;
				}
			}else{
				if(type == "user"){
					_tpl = user_tpl;
				}else if(type == "system"){
					_tpl = system_tpl;
				}else if(type == "welcome"){
					_tpl = user_tpl;
					m["welcome"] = JSLang[jesong.language].welcome;
				} else{
					_tpl = visitor_tpl;
					flag = false;
				}
			}
			if(this.firstGetMessage){
				flag = false;
			}
			var lazy = !this.isChatLayoutShow();
			/*if(jesong.jsType == 1 && jesong.env.isPhone){
				if(!this.firstGetMessage || jesong.newChat || (jesong.autochat && jesong.autochat.use == 1)){
					Chat.showChatLayout.apply(Chat, []);
					//lazy = true;
				}
			}*/
			
			if(lazy && !this.firstGetMessage){
				this.increaseUnReadMessage();
			}
			
			var execFun = function(){
				JS("jesong_chat_record").append(tpl(_tpl,m));
				if(flag){
					Chat.flashTitle.apply(Chat, []);
					Chat.playSound.apply(Chat, [msg]);
				}
				JS("jesong_chat_record").scrollTop(JS("jesong_chat_record").scrollTop()+100000);
			};
			
			if(!this.firstGetMessage && type == "user" && lazy){
				window.setTimeout(execFun, 1000);
			}else{
				execFun();
			}
			if(type == "user"){
				this.flashLastUserMessage();
			}else{
				this.stopFlashLastUserMessage();
			}
			
		}, 
		userMessageStep : 0,
		flashUserMessageColor:["#000000", "#190707", "#3B0B0B", "#8A0808", "#DF0101", "#FE2E2E"],
		flashUserMessageInterval : -1,
		stopFlashLastUserMessage : function(){
			if(this.flashUserMessageInterval != -1){
				window.clearInterval(this.flashUserMessageInterval);
				this.flashUserMessageInterval = -1;
			}
		},
		flashLastUserMessage : function(){
			if(this.userMessageStep >= this.flashUserMessageColor.length){
				this.userMessageStep = 0;
			}
			var getChildrenByClassName = function(parent, selects, parentClassName){
				var arr = [];
				for(var i=0; i<parent.children.length; i++){
					var child = parent.children[i];
					var className = parentClassName;
					if(child.className != ""){
						if(parentClassName != ""){
							className = parentClassName + " ";
						}
						className += child.className;
					}
					if(className == selects){
						arr.push(child);
					}else{
						arr = arr.concat(getChildrenByClassName(child, selects, className));
					}
				}
				return arr;
			};
			this.userMessageStep++;
			var records = getChildrenByClassName(document.getElementById("jesong_chat_record"), "jesong-service-bubble jesong-service-bubble-con jesong-service-session-con jesong-user-msg", "");
			for(var i=0; i<records.length; i++){
				if(i == records.length - 1){
					records[i].style.color = this.flashUserMessageColor[this.userMessageStep];
				}else{
					records[i].style.color = "#000000";
				}
			}
			if(this.flashUserMessageInterval == -1){
				this.flashUserMessageInterval = window.setInterval(function(){
					Chat.flashLastUserMessage.apply(Chat, []);
				}, 300);
			}
		}
	};
	
	
	
	Chat.init();
	
	jesong.force = Chat;
	
	window.onblur=function(){
		Chat.windowFocus=false;
	};

	window.onfocus= function(){
		Chat.windowFocus=true;
		Chat.clearFlashTitle.apply(Chat, []);
	};
	
})();

