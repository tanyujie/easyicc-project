(function(){
	console.log("begin webcall.js");
	var userAgent = navigator.userAgent.toLowerCase();
	//jeasy.version="1.0.0.1";
	jeasy.isReady = false;
	jeasy.domReady = false;
	jeasy.resp = null;
	jeasy.util={
				$:function(id){return document.getElementById(id);},
				isEmpty:function(s){if(typeof s=='undefined'||s==null||s.length==0)return true;return false;},
				browser:{version: (userAgent.match( /.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/ ) || [])[1],safari: /webkit/.test( userAgent ),opera: /opera/.test( userAgent ),msie: /msie/.test( userAgent ) && !/opera/.test( userAgent ),	mozilla: /mozilla/.test( userAgent ) && !/(compatible|webkit)/.test( userAgent )},
				getCookie:function(name){ 
					var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
					arr=document.cookie.match(reg);
					if(arr){
						return unescape(arr[2]);
					}else{
						return null;
					}
				},
				getCookieDomain:function() {					
					var host = location.hostname;
					var ip = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
					if (ip.test(host) == true || host == 'localhost') return host;
					var ds = host.split("\.");
					if(ds.length >= 3){
						return host.substring(host.indexOf("\."));
					}else{
						return host;
					}
				},
				setCookie:function(name,value,t){
					console.log("begin webcall:JS.setCookie:function：设置："+name+"值:"+value+"有效时间:"+t);
					if(typeof t =='undefined' ||t==null) t =60*30*24*60*60*1000;  
					var exp  = new Date(); exp.setTime(exp.getTime() + t); 
					document.cookie = name + "="+ escape (value)+ ";domain="+jeasy.util.getCookieDomain()+";expires=" + exp.toGMTString()+";path=/";
				},jsonRequest:function(url,cb){					
					jeasy.resp=null;
					var u=url + '&x='+(new Date()).getTime();	
					h = document.getElementsByTagName('head')[0];	
					var c=document.createElement('script');	
					c.setAttribute("charset", "utf-8");
					c.setAttribute("type","text/javascript");
					h.appendChild(c);
					c.setAttribute("src",u);
					console.log("begin webcall:jsonRequest:function:"+u);
					c.onreadystatechange =function(){	
						if(c.readyState=='loaded'){
							cb(c);
							if(this && this.parentNode){
								this.parentNode.removeChild(this);
							}
						}
					}; 	
					c.onload=function(){
						cb(c);
						if(this && this.parentNode){
							this.parentNode.removeChild(this);
						}
					};
				},show:function(v){
					if(typeof v == 'string') 
						v=jeasy.util.$(v); 
					v.style.display='block'; 
					console.log("begin webcall:show:function");
				},
				hide:function(v){
					if(typeof v == 'string') 
					v=jeasy.util.$(v); 
					(v==null)||(v.style.display='none');
					console.log("begin webcall:hide:function");
				},							
				log:function(s){
					var l=jeasy.util.$('log'); 
					l.innerHTML += s + '</br>';
					l.scrollTop+=8000;
				},
				attach:function(t,e,f){
					if(t.addEventListener) 
						t.addEventListener(e,f,false);
					else if(t.attachEvent) 
						t.attachEvent('on'+e,f);
				},			
				getBody:function(){return(document.compatMode!="CSS1Compat")?document.body:document.documentElement;},
				getDomain:function(url){
					var domain = url.match(/(\w+):\/\/([^\:|\/]+)(\:\d*)?(.*\/)([^#|\?|\n]+)?(#.*)?(\?.*)?/i);
					if(domain != null && domain.length>2){
						var d = domain[2];
						var ds = d.split("\.");
						if(ds.length >= 3){
							d = d.substring(d.indexOf("\.")+1);
						}
						return d;
					}else{
						return null;
					}
					console.log("begin webcall:getDomain:function");
				}, loadJS : function(src){
					console.log("begin webcall:loadJS:function");
					var _ss = document.getElementsByTagName("script");
					for(var i = 0 ; i < _ss.length ; i++){
						if(_ss[i].src==src){
							return;
				   		}
					}
				    var sga = document.createElement("script"); sga.type = "text/javascript";sga.async = true;
				    sga.src = src;
				    sga.charset = "utf-8";
				    var s = _ss[0]; 
				    s.parentNode.insertBefore(sga, s);
				},
				upvid:function(){
					console.log("begin webcall:upvid:function");
					var u = jeasy.util,ev=jeasy.env;
					var v = u.getCookie('JESONG_VISITOR_ID');
					if( v != null && v.length !=0)
					     ev.vId = v;	
					u.setCookie('JESONG_VISITOR_ID',ev.vId, 60*60*1000);
					
					var autoTimes = u.getCookie('JESONG_AUTO_MON_TIMES');
					if(autoTimes){
						autoTimes = parseInt(autoTimes);
					}else{
						autoTimes = 0;
					}
					u.setCookie('JESONG_AUTO_MON_TIMES', autoTimes, 60000);
				},
				getRequestParam:function(key){
					console.log("begin webcall:getRequestParam:function");
					var h = window.location.href;
					var args = h.split("?");
					if(args[0] != h && args.length>1){
						var parmas = args[1].split("&");
						for(var i=0; i<parmas.length; i++){
							var kv = parmas[i].split("=");
							if(kv.length == 2){
								if(kv[0] == key){
									return decodeURIComponent(kv[1]);
								}
							}
						}
					}
					return null;
				},
				getReseveKey:function(){
					console.log("begin webcall:getReseveKey:function");
					var exts = null;
					if(typeof reseveKey != 'undefined' && reseveKey!=null){
						exts = reseveKey;
					}else if(typeof(JESONG_EXT_DATA) != "undefined" && JESONG_EXT_DATA != null){
						exts = JESONG_EXT_DATA;
					}else{
						var cKey = this.getCookie("reseveKey") || this.getCookie("JESONG_EXT_DATA");
						if(cKey!=null&&cKey.length!=0){
							exts = cKey;
						}else{
							var rKey = this.getRequestParam("reseveKey");
							if(rKey != null){
								exts = rKey;
							}
						}
					}
					if(exts != null && typeof exts == "object"){
						var _ext = "#params:";
						var _i=0;
						for(var key in exts){
							if(_i > 0){
								_ext += ",";
							}
							_ext = _ext + key + ","+exts[key];
							_i++;
						}
						exts = _ext;
					}
					return exts;
				},
				parseReseveKey:function(query){
					var pQuery = query;	
	     	        if(pQuery==null)  return ;
		            var perfix='#params:';
		            var pIdx = query.indexOf(perfix);	
		            if(pIdx==0)
			           pQuery = query.substring(perfix.length);
				     var args=new Array();//定义一数组 
				     args=pQuery.split(",");//字符分割
				     return args;
				},
				floatDiv:function(){
					var me = this, u = jeasy.util;
					this.init=function(v,g,w,h){										
						this.obj=v;					
						this.handle= (typeof g == 'string')?jeasy.util.$(g):g;
						this.width=(typeof w == 'undefined')?v.offsetWidth:w;
						this.height=(typeof h == 'undefined')?v.offsetHeight:h;					
						this.html = v.innerHTML;
						this.lastLeft = u.getBody().scrollLeft;
						this.lastTop = u.getBody().scrollTop;		
						if(this.handle){
							this.handle.style.cursor = 'move';
							this.handle.onmousedown=  function(e){
								e = e||event;
								if(e.layerX)
									me.obj.oOffset={x:e.layerX,y:e.layerY};
								else
									me.obj.oOffset={x:e.offsetX,y:e.offsetY};	
								document.onmousemove = me.drag;
								document.onmouseup = me.drop;
								document.onselectstart = function(){return false;};
							};
						}
					};	
					this.isMoved=function(newX,newY){							
						var b=u.getBody();
						return {x:newX>0&&newX<b.clientWidth-me.width+ b.scrollLeft,y:newY>0&&newY<b.clientHeight-me.height+b.scrollTop};		
					};
					this.drag=function(e){
						e = e||event;									
						var b=u.getBody();
						var mv = me.isMoved(e.clientX-me.obj.oOffset.x+  b.scrollLeft,e.clientY-me.obj.oOffset.y +b.scrollTop);					
						if(mv.x)me.obj.style.left = e.clientX-me.obj.oOffset.x+ b.scrollLeft + 'px';
						if(mv.y)me.obj.style.top = e.clientY-me.obj.oOffset.y+ b.scrollTop+ 'px';
					};
					this.drop=function(e){								
						e=e||event;
						document.onmouseup=document.onmousemove=document.onselectstart=null;			
					};
					this.show=function(){
						var st = me.obj.style;
						if(st.visibility != 'visible')
							st.visibility = 'visible';
						try{
						var nleft = u.getBody().scrollLeft || document.body.scrollLeft, ntop = u.getBody().scrollTop || document.body.scrollTop;				
						var dx = nleft - me.lastLeft, dy = ntop - me.lastTop;
						if(st.top){
							st.top = parseInt(st.top) + dy + 'px' ;
							me.lastTop = ntop;
						}else if(me.height){
							st.top = (u.getBody().clientHeight) - me.height + ntop +'px';
							me.lastTop = ntop;
						}
						if(dx != 0){
							try{st.left = parseInt(st.left) + dx + 'px'; }catch(e){ st.right = parseInt(st.right) +dx +'px';}
							me.lastLeft = nleft; 
						}	
						}catch(e){}
											
					};
					this.start=function(){
						jeasy.timer.addListener(me.show);
						jeasy.timer.start(250);
					};
				},	
				float:function(v,g,w,h){
					var t = (typeof v);
					if(t == 'undefined' || v == null) return;
					if(t == 'string') v=jeasy.util.$(v);
					var f = new jeasy.util.floatDiv();
					f.init(v,g,w,h);
					f.start();
				},
				bindReady:function(){
					console.log("begin webcall:bindReady:function");
					jeasy.domReady = true;
					var browser = jeasy.util.browser;				
					if ( document.addEventListener && !browser.opera) document.addEventListener( "DOMContentLoaded", jeasy.ready, false );
					if(browser.msie &&window==top)(function(){try{document.documentElement.doScroll('left');}catch(e){setTimeout(arguments.callee,0);return;}jeasy.ready();})();
					if(browser.opera)
						document.addEventListener( "DOMContentLoaded", function () {if (jeasy.isReady) return;
							for (var i = 0; i < document.styleSheets.length; i++)
							if (document.styleSheets[i].disabled) {
								setTimeout( arguments.callee, 0 );
								return;
							}			
						jeasy.ready();
					}, false);				
					if (browser.safari ||(browser.msie && window != top) ) {
						var numStyles;(function(){if (jeasy.isReady) return;
							if ( document.readyState != "loaded" && document.readyState != "complete" ) {
								setTimeout( arguments.callee, 0 );
								return;
							}		
							jeasy.ready();
						})();
					}		
				},
			closeForceChat:function(){
				var frame = document.getElementById("jesong_chat_frame");
				if(frame){
					frame.src="/blank.html";
					document.getElementById("jesong_chat").style.display="none";
				}
				jeasy.util.show('jesong_panel');
			},
			forceChat:function(c){
				var ev = jeasy.env;
				var url = ev.server.chat + 'chat.do?';
				url += jeasy.util.commonURL();
				if(typeof c == 'object'){
					if(c.cId != null){
						url +='&command=applyChat&cId='+c.cId; url+='&t='+c.type+'&n='+c.sId;
					}else if(jeasy.monitor && jeasy.monitor.config && jeasy.monitor.config.group != '' && jeasy.monitor.config.group !='0')	url += '&g='+jeasy.monitor.config.group;
				}else if( typeof c == 'string' &&c.length !=0 ){
					url += '&'+c;
				}
				if(jeasy.force != null){
					jeasy.force.connect(c.cId, c.sId);
					if(jeasy.panel && jeasy.panel.config && jeasy.panel.config.panelWhenInvite == "1"){
						jeasy.util.hide('jesong_mon_mask');
						jeasy.util.hide('jesong_monitor');
						jeasy.util.hide('jesong_panel');
					}
				}else{
					window.location = url;
				}
			},
			openChatRoom:function(cg){
				console.log("begin webcall:openChatRoom:function");
				var ev =jeasy.env;			
				var url = jeasy.util.commonURL();
				url = url + "&cg="+cg;
				var p = "height=525,width=800,directories=no,location=no,menubar=no,resizeable=no,status=no,toolbar=no,top=100,left=200";
				try{
					var cw = window.open(ev.server.chat +'group.do?'+ url,'chat_room_'+ev.compId,p);cw.focus();
				}catch(e){
				}
			},
			getChatUrl:function(c){
				console.log("begin webcall:getChatUrl:function");
				var ev = jeasy.env;
				var u = jeasy.util;
				var url = jeasy.util.commonURL();
				url = url +"&chatUrl="+window.encodeURIComponent(window.location.href);
				if(ev.spreadFlag){
					url = url +"&sf="+window.encodeURIComponent(ev.spreadFlag);
				}
				url = url + "&promotionId="+ev.promotionId;
				url = url + "&sid=" + ev.sid;
				if(jeasy.autochat.searchMode > 0){
					url = url + "&guide="+jeasy.autochat.searchMode;
				}
				var command = null;
				if(jeasy.config.firstToRebot == 1){
					command = "robotChat";
				}
				if(ev.firstPage){
					url = url +"&first="+window.encodeURIComponent(ev.firstPage);
				}else{
					url = url +"&first="+window.encodeURIComponent(window.location.href);
				}
				if(u.getCookie('JESONG_VC')){
					url = url + "&vc="+window.encodeURIComponent(u.getCookie("JESONG_VC"));
				}
				
				if(typeof c == 'object'){
					if(c.cId != null){
						command = null;
						url +='&command=applyChat&cId='+c.cId; url+='&t='+c.type+'&n='+c.sId;
					}else if(jeasy.monitor && jeasy.monitor.config && jeasy.monitor.config.group != '' && jeasy.monitor.config.group !='0')	url += '&g='+jeasy.monitor.config.group;
				}else if( typeof c == 'string' &&c.length !=0 ){
					url += '&'+c;
				}
				if(command != null){
					url += "&command="+command;
				}
				if(ev.refer && ev.refer !=""){
					url = url +"&ref="+window.encodeURIComponent(ev.refer);
				}else if(document.referrer){
					try{
						var refer = document.referrer;
						if(refer != ""){
							var domain = jeasy.util.getDomain(refer);
							if(domain && domain.indexOf("easyliao") == -1 && domain.indexOf("jesong") == -1){
								url = url +"&ref="+window.encodeURIComponent(refer);
							}
						}
					}catch(e){};
				}
				var clickText = "";
				try{
					var e = e|| window.event || arguments.callee.caller.arguments[0];
					if(e){
						var target = e.target || e.srcElement;
						if(target){
							var text = "";
							if(target.id == "jesong_panel" ||  (target.parentNode && target.parentNode.id == "jesong_panel")){
								text = "\u6613\u804a\u56fe\u6807";
							}else if(target.nodeName == "IMG"){
								text = target.src;
							}else{
								text = target.innerHTML.replace(/<[^>]+>/g,"");
								clickText = text;
							}
							if(text != ""){
								url = url + "&clickText="+window.encodeURIComponent(text);
							}
						}
					}
				}catch(e){}
				return url;
			},
			openWindowChat:function(url){
				console.log("begin webcall:openWindowChat:function");
				var ev = jeasy.env;
				var u = jeasy.util;
				var p = "height=600,width=800,directories=no,location=no,menubar=no,resizeable=no,status=no,toolbar=no,top=100,left=200";
				try{
					var cw = window.open(ev.server.chat +'chat.do?'+ url,'chat_'+ev.compId,p);cw.focus();
				}catch(e){
					window.location = url;				
				}
			},
			openChat:function(c){
				console.log("begin webcall:openChat:function");
				var ev = jeasy.env;
				var url = jeasy.util.getChatUrl(c);
				try{
					if(ev.min == 1 && jeasy.force && jeasy.force.openchat){
						jeasy.force.openchat(url);
					}else if(jeasy.jsType == 1 && jeasy.env.isPhone){
						if(jeasy.force != null){
							params = jeasy.force.getWinChatParam();
							if(params && params.chatting){
								window.location = ev.server.chat +'chat.do?'+url+"&command=applyChat&cId="+params.chatId+"&n="+params.customerId;
								return;
							}
						}
						window.location = ev.server.chat +'chat.do?'+url;
					}else{
						jeasy.util.openWindowChat(url);
					}
				}catch(e){
					var img = document.createElement("img");
					img.src= ev.server.monitor + "i?cmd=clickError&o="+ev.compId+'&v='+ev.vId+'&u='+ev.uId + "&j="+ev.confId
						+"&url="+window.encodeURIComponent(window.location.href);
						+"&error="+(e.message ? window.encodeURIComponent(e.message) : "");
					img.style="border:0px;width:0px;height:0px;";
					document.body.appendChild(img);
				}
			},
			commonURL:function(){
				var ev =jeasy.env;			
				var u='o='+ev.compId+'&v='+ev.vId+'&u='+ev.uId+'&config='+ev.confId;
				
				var uKey = jeasy.util.getReseveKey();
				if(uKey!=null)
					u+='&ext='+encodeURIComponent(uKey);
					
				if(ev.lang != null && ev.lang.length!=0)
					u+='&lang='+ev.lang;
				return u;				
			}
	};
	jeasy.timer={
		id:0,
		listeners:[],
		addListener:function(f){
			if(typeof f == 'function')
				this.listeners[this.listeners.length++] = f;
		},
		execute:function(){
			for(var v=0; v<this.listeners.length;v++){
				if(this.listeners[v] != null)
					this.listeners[v]();
			}
		},
		start:function(t){
			if(this.id != 0) this.stop();
			this.id = setInterval("jeasy.timer.execute()",t);		
		},
		stop:function(){
			if(this.id !=0)	clearInterval(this.id);
			this.id = 0;
		},
		started:function(){return this.id!=0;}
	};
	jeasy.monitor.timer = 0;
	jeasy.monitor.chat = {
		build:function(e){
		this.type= (e.e == '11')?1:0;
		this.force = (e.m&&e.m.indexOf('<FORCE>') != -1);
		this.cId = e.c;
		this.sId = e.s;
		this.dId = e.d;
		this.m = e.m;
		var ev = jeasy.env;
		var url = ev.server.monitor + 'i?cmd=monitorSuccess&o='+ev.compId+'&v='+ev.vId +"&t="+e.c+"&n="+e.s;
		jeasy.util.jsonRequest(url,jeasy.monitor.pump);
		
		}
	};
	jeasy.monitor.driver=function(){	
		var ev = jeasy.env;
		var url = ev.server.monitor + 'i?cmd=getEvent&o='+ev.compId+'&v='+ev.vId + '&p='+ev.pId;
		jeasy.util.upvid();
		jeasy.util.jsonRequest(url,jeasy.monitor.pump);
	};
	function showGuideAlert(msg)
	{
		var cmds = msg.split("<m>");
		if(cmds.length==2)
		if(typeof markGruideObj == 'function')
		{
			markGruideObj('#'+cmds[0],0,cmds[1],0,0,0);
		} 
	}
	function tryToStartGuide(msg)
	{
		if(typeof startGuide == 'function')
		{
			startGuide(msg);
		}
	}
	jeasy.monitor.hideInviteLayout = function(){
		document.getElementById("jesong_pop_msg").style.display = "none";
		document.getElementById("jesong_invite_layout").style.display = "none";
		jeasy.monitor.accept();
	};
	jeasy.monitor.doEvent=function(e){
		console.log("begin webcall:jeasy.monitor.doEvent function");
		var mon = jeasy.monitor;
		switch(e.e){
			case '11': case '1':
				mon.chat.build(e);
				try{window.focus();}catch(e){};
				if( mon.chat.force){
					jeasy.util.forceChat(mon.chat);	
					return;	
				}else{
					if(e.m && e.m != "" && jeasy.jsType == 1 && jeasy.env.isPhone && jeasy.force){
						var inviteLayout = document.getElementById("jesong_invite_layout");
						if(!inviteLayout){
							inviteLayout = document.createElement("div");
							inviteLayout.id = "jesong_invite_layout";
							inviteLayout.className = "jesong_invite_layout";
							document.body.appendChild(inviteLayout);
							
							function bindClickEvent(dom, fn){
								if(dom.attachEvent){
									dom.attachEvent("onclick", function(e){
										return fn.apply(this, [e]);
									});
								}else if(dom.addEventListener){
									dom.addEventListener("click", function(e){
										return fn.apply(this, [e]);
									});
								}else{
									target["onclick"] = fn;
								}
							}
							bindClickEvent(inviteLayout, jeasy.monitor.hideInviteLayout);
						}
						inviteLayout.style.display = "block";
						jeasy.force.showPopMessage.apply(jeasy.force, [e.m]);
						document.getElementById("jesong_unread").style.display="none";
					}else{
						mon.show();
					}
				}
			break;
			case '8':
				mon.chat.build(e);
				mon.show();
				try{window.focus();}catch(e){};
			break;
			case '12':
				showGuideAlert(e.m);
			break;
			case '13':
				tryToStartGuide(e.m);
			break;
			case '9':
				jeasy.util.$('jesong_mon_main').innerHTML = jeasy.env.mId?(jeasy.env.mId + '&nbsp;' + e.m):e.m;
				mon.show();
				try{window.focus();}catch(e){};
			break;		
		}

	};
	jeasy.monitor.pump=function(){
		if(jeasy.resp == null)
			return;	
		var r = jeasy.resp,mon = jeasy.monitor;
		var u = jeasy.util;
		switch(r.cmd){
			case 'init':
				mon.cust=r.p.l;
				mon.report=r.p.r;
				/*if(r.vId){
					jeasy.env.vId = r.vId;
					u.setCookie('JESONG_VISITOR_ID', jeasy.env.vId,60000);
				}*/
				if(r.p.l == 1){
					if( mon.cust && mon.timer==0){ 
						mon.timer = window.setInterval("jeasy.monitor.driver()", 10*1000);
						jeasy.monitor.driver();
					}	
				}
				jeasy.env.aiReady = r.p.ai;
				break;
			case 'add':
				jeasy.env.pId=jeasy.resp.p;
				if(jeasy.resp.promotionId){
					jeasy.env.promotionId = jeasy.resp.promotionId;
				}
				jeasy.env.recordReady = true;
				jeasy.env.refer = jeasy.resp.refer;
				jeasy.env.firstPage = jeasy.resp.firstPage;
				jeasy.env.spreadFlag = jeasy.resp.spreadFlag;
				
				jeasy.autochat.keyWord = jeasy.resp.keyword;
				
				jeasy.env.vId = jeasy.resp.visitorId;
				
				u.setCookie('JESONG_VISITOR_ID', jeasy.env.vId, 60*60*1000);
				//必须是手机特定的对话
				if(/*jeasy.jsType == 1 && jeasy.config.phoneChatPop==1 && jeasy.env.isPhone &&*/ jeasy.resp && jeasy.resp.chatId && jeasy.resp.customerId){
					if(jeasy.force){
						jeasy.newChat = true;
						jeasy.force.connect(jeasy.resp.chatId, jeasy.resp.customerId);
					}else{
						u.setCookie("jesong_chat_"+jeasy.env.compId+"_id", jeasy.resp.chatId,60000);
						u.setCookie("jesong_chat_user_"+jeasy.env.compId+"_id", jeasy.resp.customerId,60000);
					}
				}
				if(jeasy.isReady){
					jeasy.loadForceJS();
				}
				window.onbeforeunload = function(){//
					var imgTag = document.createElement('img'); 
					imgTag.src = jeasy.env.server.monitor+'i?cmd=leave&o=' + jeasy.env.compId + '&v=' + jeasy.env.vId + '&p=' + jeasy.env.pId;
				};
				if(jeasy.autochat.keyWord && jeasy.autochat.searchMode > 0 && jeasy.env.min == 1){
					jeasy.util.loadJS(jeasy.env.server.file+"js/guide.js?ver="+jeasy.version);
				}
				//var le = jeasy.util.$('jesong_leave');
				//if(le == null){ le = document.createElement('iframe');le.setAttribute('id','jesong_leave');document.body.appendChild(le);le.src=jeasy.env.server.monitor+'l.jsp?'+jeasy.util.commonURL()+'&p='+jeasy.env.pId;}				
				break;
			case 'getEvent':
				var es = jeasy.resp.p;
				if(jeasy.resp.vc != undefined) u.setCookie('JESONG_VC',jeasy.resp.vc);
				if(jeasy.resp.ac != undefined) u.setCookie('JESONG_AC',jeasy.resp.ac);
				if(jeasy.resp.ic != undefined) u.setCookie('JESONG_IC',jeasy.resp.ic);
				if(jeasy.resp.dc != undefined) u.setCookie('JESONG_DC',jeasy.resp.dc);
				for(var v in es) mon.doEvent(es[v]);
				if(/*jeasy.jsType == 1 && jeasy.config.phoneChatPop==1 && jeasy.env.isPhone && */jeasy.resp && jeasy.resp.chatId && jeasy.resp.customerId){
					if(jeasy.force && jeasy.force.status != 2){
						jeasy.newChat = true;
						jeasy.force.connect(jeasy.resp.chatId, jeasy.resp.customerId);
					}
				}
				break;
			case 'refuse':
				break;
		}
	};
	jeasy.monitor.accept=function(){
		
		var u = jeasy.util;
		
		u.hide('jesong_mon_mask');
		u.hide('jesong_monitor');
		u.show('jesong_panel');
		
		jeasy.util.winChat(function(){
			u.openChat(jeasy.monitor.chat);
		});
	};
	
	jeasy.monitor.hide = function(){
		jeasy.util.hide('jesong_mon_mask');
		jeasy.util.hide('jesong_monitor');	
		jeasy.util.show('jesong_panel');
	};
	
	jeasy.monitor.refuse=function(){
		jeasy.monitor.hide();
		var c =jeasy.monitor.chat.cId; 
		if(jeasy.monitor.config.showInviteAgain != -1){
			window.setTimeout(function(){
				jeasy.util.show('jesong_monitor');
				if(jeasy.monitor.config.mask){
					jeasy.util.show("jesong_mon_mask");
				}
			}, jeasy.monitor.config.showInviteAgain*1000);
		}
		if(typeof c =='undefined' || c== null)
			return;
		var chatFrame = document.getElementById("jesong_chat");
		if(chatFrame && chatFrame.style.display=="block"){
			return;
		}
		var ev = jeasy.env;
		var url = ev.server.monitor + 'i?cmd=refuse&o='+ev.compId+'&v='+ev.vId +'&t='+jeasy.monitor.chat.cId;
		jeasy.util.jsonRequest(url,jeasy.monitor.pump);
		
	};
	jeasy.monitor.buildMask=function(){
		var v=document.createElement('div');
		v.setAttribute('id','jesong_mon_mask');
		document.body.appendChild(v);
		var b = jeasy.util.getBody();	
		v.style.width = Math.max(b.scrollWidth,b.clientWidth)+ 'px';
		v.style.height = Math.max(b.scrollHeight,b.clientHeight) +'px';
		return v;
	};

	jeasy.monitor.show=function(){
		if(jeasy.force && jeasy.force.isChatLayoutShow()){
			return;
		}
		
		var u = jeasy.util;
		if(jeasy.monitor.config.mask){
			var m=u.$('jesong_mon_mask');
			(m!=null) ||( m = jeasy.monitor.buildMask());
			u.show(m);
		}
		u.show('jesong_monitor');
		if(jeasy.panel && jeasy.panel.config && jeasy.panel.config.panelWhenInvite == "1"){
			u.hide('jesong_panel');
		}
		window.focus();
		if(typeof jeasy.monitor.autoTimer != 'undefined' && jeasy.monitor.autoTimer !=0){
			clearTimeout(jeasy.monitor.autoTimer);
			jeasy.monitor.autoTimer = 0;
		}
	};
	jeasy.monitor.autoShow = function(){
		
		if(jeasy.force && jeasy.force.isChatLayoutShow() && jeasy.autochat.hideMonitor){
			return;
		}
		var u = jeasy.util;
		var autoInviteTimes = jeasy.monitor.config.autoInviteTimes;
		var autoTimes = u.getCookie('JESONG_AUTO_MON_TIMES');
		if(autoTimes){
			autoTimes = parseInt(autoTimes);
		}else{
			autoTimes = 0;
		}
		if(autoInviteTimes ==-1 || autoInviteTimes>autoTimes){
			u.setCookie('JESONG_AUTO_MON_TIMES', autoTimes+1, 60000);
			jeasy.monitor.show();
		}else{
			if(typeof jeasy.monitor.autoTimer != 'undefined' && jeasy.monitor.autoTimer !=0){
				clearTimeout(jeasy.monitor.autoTimer);
				jeasy.monitor.autoTimer = 0;
			}
		}
	};
	//设置监听器
	jeasy.monitor.start=function(){	
		var con=jeasy.monitor.config;
		if(con.auto >= 0){
			var now = new Date();
			var nh=now.getHours(),nm=now.getMinutes();		
			function tonum(s){ 
				var s1=s.replace(/:/g,'');
				while(s1.charAt(0)=='0' && s1.length>1) s1=s1.substring(1); 
				return parseInt(s1);
			}
			var h1=tonum(con.start);var h2=tonum(con.end);var n=tonum(nh+":"+((nm<10)?('0'+nm):nm));
			var _flag = false;
			if(h1 > h2 && ((h1 <= n && n < 2359) || (n > 0 && n <= h2))){
				_flag = true;
			}else if(h1<=n && h2>=n){
				_flag = true;
			}
			if(_flag){
				var autoTimes = jeasy.util.getCookie('JESONG_AUTO_MON_TIMES');
				var t = con.auto*1000;
				jeasy.monitor.autoTimer = setTimeout('jeasy.monitor.autoShow()', t);
			}
		}
		var ev = jeasy.env;
		var v = jeasy.util.getCookie('JESONG_VISITOR_ID');
		if( v != null && v.length !=0){
			ev.vId = v;	
		}
		var url = ev.server.monitor + 'i?cmd=init&o='+ev.compId+'&u='+ev.uId+"&v="+ev.vId;
		alert("jeasy.monitor.chat4");
		jeasy.util.jsonRequest(url,jeasy.monitor.pump);
	};
	jeasy.monitor.record=function(){
		var ev = jeasy.env;
		var h = document.title;if(h.length>100)h=h.substring(0,100);
		var refer = document.referrer ? document.referrer : "";
		/*if(refer){
			try{
			var referDomain = jeasy.util.getDomain(refer);
			var currDomain = jeasy.util.getDomain(window.location.href);
			if(referDomain && currDomain && referDomain == currDomain){
				refer = "";
			}
			}catch(e){};
		}*/
		var url = ev.server.monitor+'i?cmd=add&o='+ev.compId+'&v='+ev.vId+'&u='+ev.uId+'&p='+ev.pId
			+'&ref='+encodeURIComponent(refer)+'&h='+encodeURIComponent(h)+'&w='+encodeURIComponent(document.location.href)
			+'&scn='+screen.width+'*'+screen.height+'&t='+(jeasy.monitor.cust+2*jeasy.monitor.report);
		
		var uKey = jeasy.util.getReseveKey();
		if(uKey!=null)
			url+='&ext='+encodeURIComponent(uKey);
		if(jeasy.util.getCookie('JESONG_VC')){
			url+="&vc="+jeasy.util.getCookie('JESONG_VC');
		}
		if(jeasy.util.getCookie('JESONG_AC')){
			url+="&ac="+jeasy.util.getCookie('JESONG_AC');
		}
		if(jeasy.util.getCookie('JESONG_IC')){
			url+="&ic="+jeasy.util.getCookie('JESONG_IC');
		}
		if(jeasy.util.getCookie('JESONG_DC')){
			url+="&dc="+jeasy.util.getCookie('JESONG_DC');
		}
		if(jeasy.util.getCookie('JESONG_S_FLAG')){
			url+="&sf="+jeasy.util.getCookie('JESONG_S_FLAG');
		}
		url+="&sid="+ev.sid;
		url+="&promotionId="+ev.promotionId;
		jeasy.util.jsonRequest(url,jeasy.monitor.pump);
	};
	jeasy.freecall.hide = function(){
		jeasy.util.$("jesong_freecall").style.display = "none";
	};
	jeasy.freecall.commit = function(){
		console.log("begin webcall:jeasy.freecall.commit function");
		var phone = jeasy.util.$("jesong_freecall_text").value;
		if(/^(13|14|15|17|18)\d{9}$/i.test(phone) || /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(phone)){
			//保存
			jeasy.freecall.hide();
			var ev = jeasy.env;
			var url = ev.server.chat + "msg.do?cmd=savePhone";
			var params = [];
			params.push("c="+jeasy.env.compId);
			params.push("v="+jeasy.env.vId);
			params.push("u="+jeasy.env.uId);
			params.push("sid="+jeasy.env.sid);
			params.push("phone="+window.encodeURIComponent(phone));
			if(jeasy.freecall.config.groupId != -1){
				params.push("g="+jeasy.freecall.config.groupId);
			}
			params.push("promotionId="+jeasy.env.promotionId);
			
			if(ev.spreadFlag){
				url = url +"&sf="+window.encodeURIComponent(ev.spreadFlag);
			}
			
			if(ev.refer){
				url = url +"&ref="+window.encodeURIComponent(ev.refer);
			}
			
			var exts = jeasy.util.getReseveKey();
			
			if(exts != null && exts != ""){
				params.push("ext="+window.encodeURIComponent(exts));
			}
			params.push("url="+window.encodeURIComponent(window.location.href));
			alert("jeasy.monitor.chat6");
			jeasy.util.jsonRequest(url+"&"+params.join("&"), function(){});
		}else{
			alert("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u7535\u8bdd\u53f7\u7801\n\u5750\u673a\u8bf7\u4f7f\u7528\u4ee5\u4e0b\u683c\u5f0f:\u533a\u53f7-\u5ea7\u673a\u53f7-\u5206\u673a\u53f7");
			jeasy.util.$("jesong_freecall_text").focus();
		}
	};
	jeasy.freecall.init = function(){
		console.log("begin webcall:jeasy.freecall.init function");
		var con = jeasy.freecall.config;
		if(con && con.show){
			var m = document.createElement('div');
			document.body.appendChild(m);
			m.setAttribute('id','jesong_freecall');	
			var csstext = "width:"+con.panelWidth+"px;height:"+con.panelHeight+"px;background:url("+con.panelBg+") no-repeat center center;";//_background:none;_filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+con.panelBg+"');";
			if(con.panelPos == 0){
				var _t = -parseInt(con.panelHeight/2)+ parseInt(jeasy.util.getBody().clientHeight/2);
				var _l = -parseInt(con.panelWidth/2)+ parseInt(jeasy.util.getBody().clientWidth/2);
				csstext+="top:"+_t+"px;left:"+_l+"px;";
				csstext+="_buttom:auto;_top:expression(eval(document.documentElement.scrollTop+"+_t+"));";
			}else{
				if(con.panelTop != -1 && con.panelTop > 0){
					csstext+="top:"+con.panelTop+"px;";
					csstext+="_top: expression(eval(document.documentElement.scrollTop+"+con.panelTop+"));";
				}
				if(con.panelLeft != -1 && con.panelLeft > 0){
					csstext+="left:"+con.panelLeft+"px;";
				}
				if(con.panelBottom != -1 && con.panelBottom > 0){
					csstext+="bottom:"+con.panelBottom+"px;";
					csstext+="bottom:1px;_position: absolute;_bottom:auto;_top:expression(eval(document.documentElement.scrollTop+document.documentElement.clientHeight-this.offsetHeight-"+con.panelBottom+"));";
				}
				if(con.panelRight != -1 && con.panelRight > 0){
					csstext+="right:"+con.panelRight+"px;";
				}
			}
			m.style.cssText = csstext;
			
			var setPosition = function(tar, w, h, t, l){
				tar.style.width = w+"px";
				tar.style.height = h+"px";
				tar.style.top = t+"px";
				tar.style.left = l+"px";
			};
			
			var promptText = "\u8bf7\u8f93\u5165\u60a8\u7684\u7535\u8bdd\u53f7\u7801";
			m.innerHTML = "<div id=\"jesong_freecall_close\" onclick=\"jeasy.freecall.hide();return false;\"></div><div id=\"jesong_freecall_textarea\"><input id=\"jesong_freecall_text\" value=\""+promptText+"\"/></div><div id=\"jesong_freecall_btn\"  onclick=\"jeasy.freecall.commit();return false;\"></div>";
			setPosition(jeasy.util.$("jesong_freecall_close"), con.closeWidth, con.closeHeight, con.closeTop, con.closeLeft);
			setPosition(jeasy.util.$("jesong_freecall_btn"), con.callWidth, con.callHeight, con.callTop, con.callLeft);
			setPosition(jeasy.util.$("jesong_freecall_textarea"), con.textWidth, con.textHeight, con.textTop, con.textLeft);
			
			var input = jeasy.util.$("jesong_freecall_text");
			input.style.width = con.textWidth+"px";
			input.style.height = (con.textHeight)+"px";
			input.style.lineHeight = (con.textHeight)+"px";
			input.style.color = "#AE9F9F";
			jeasy.util.attach(input,"blur", function(){
				if(input.value == ""){
					input.value = promptText;
					input.style.color = "#AE9F9F";
				}
			});
			jeasy.util.attach(input,"focus", function(){
				if(input.value == promptText){
					input.value = "";
					input.style.color = "#000000";
				}
			});
		}
	};
	jeasy.monitor.init=function(){
		console.log("begin webcall:jeasy.monitor.init function");
		var u = jeasy.util,mon=jeasy.monitor;
		var m = document.createElement('div');
		document.body.appendChild(m);
		m.setAttribute('id','jesong_monitor');	
		var csstext = "";
		if(mon.config.pos == "1" || mon.config.pos == "4"){
			csstext+="top:1px;_position: absolute;_top: expression(eval(document.documentElement.scrollTop));";
		}else if(mon.config.pos == "2" || mon.config.pos == "5"){
			csstext+="bottom:1px;_position: absolute;_bottom:auto;_top:expression(eval(document.documentElement.scrollTop+document.documentElement.clientHeight-this.offsetHeight-(parseInt(this.currentStyle.marginTop,10)||0)-(parseInt(this.currentStyle.marginBottom,10)||0)));";
		}
		if(mon.config.pos == "1" || mon.config.pos == "2"){
			csstext+="left:1px;";
		}else if(mon.config.pos == "4" || mon.config.pos == "5"){
			csstext+="right:1px;";
		}
		if(mon.config.type == 1){
			m.innerHTML='<div id="jesong_mon_head" style="width:0px;height:0px;"></div><div id="jesong_mon_accept" style="'+mon.config.acceptStyle+'"  onclick="javascript:jeasy.monitor.accept()"></div><div id="jesong_mon_refuse" style="'+mon.config.refuseStyle+'" onclick="javascript:jeasy.monitor.refuse()"></div><div style="overflow:hidden;'+mon.config.textStyle+'">'+mon.config.text+'</div>';	
			csstext += "background:"+mon.config.mainBg+";background-size:100%;";
			csstext += "height:"+mon.config.mainHeight+"px;";
			csstext += "width:"+mon.config.mainWidth+"px;";
		}else{
			var className="jesong_win_monitor_"+jeasy.monitor.config.index;
			m.innerHTML='<div class="title" id="jesong_mon_head">'+mon.config.title+'</div><div class="context">'+mon.config.text+'</div><div id="jesong_mon_accept" class="accept" onclick="javascript:jeasy.monitor.accept()"></div><div class="refuse" onclick="jeasy.monitor.refuse()"></div>';
			m.className = className;
		}
		if(mon.config.pos == "3"){
			var _t = -226/2+ u.getBody().clientHeight/2;
			var _l = -419/2 +u.getBody().clientWidth/2;
			if(jeasy.jsType == 1){
				_t = -300/2+ u.getBody().clientHeight/2;
				_l = -281/2 +u.getBody().clientWidth/2;
			}
			if(mon.config.type == 1){
                _t = -parseInt(mon.config.mainHeight)/2 + u.getBody().clientHeight/2;
                _l = -parseInt(mon.config.mainWidth)/2 +u.getBody().clientWidth/2;
			}
			csstext+="top:"+_t+"px;left:"+_l+"px;";
			csstext+="_position: absolute;_buttom:auto;_top:expression(eval(document.documentElement.scrollTop+"+_t+"));";
		}
		m.style.cssText = csstext;
	};
	jeasy.panel.init=function(){
		console.log("begin webcall:jeasy.panel.init function");
		var u=jeasy.util,pan=jeasy.panel,con=pan.config;
		var p = u.$('jesong_panel');
		
		var po = con.category=='win'?jeasy.win:(con.category=='icon'?jeasy.icon:jeasy.text);
		if(con.position != -1){
			var cssText = "position:fixed;_position:absolute;";
			if(con.position == 4 || con.position == 5){
				cssText = cssText + "right:0px;left:0px;";
			}else{
				cssText = cssText +((con.position==0 || con.position==2)?'left':'right')+":"+con.horizon+"px;";
			}
			
			if(con.position == 0 || con.position == 1){
				cssText = cssText + "top:"+con.vertical+"px;_buttom:auto;_top:expression(eval(document.documentElement.scrollTop+"+con.vertical+"));";
			}else if(con.position == 2 || con.position == 3){
				cssText = cssText + "bottom:"+con.vertical+"px;_buttom:auto;_top:expression(eval(document.documentElement.scrollTop+document.documentElement.clientHeight-this.clientHeight -1 - "+con.vertical+"));";
			}else if(con.position == 4 || con.position == 5){
				cssText = cssText + "bottom:0px;";
			}
			p.style.cssText = cssText;
		}
		po.builder();
		u.show(p);	
	};
	jeasy.win.builder=function(){
		console.log("begin webcall:jeasy.win.builder function");
			var con = jeasy.win.config,u=jeasy.util,pan=u.$('jesong_panel');
			if(con.mode == 0 ){ con.width += 8;}
			if(con.mode == 1){		
				if(con.index == undefined){
					con.index = 0;
				}
				pan.className = "jesong_win_pan_"+con.index;
				var h ='<div class="head" id="jesong_head"></div><div class="center"><div id="jesong_ocontent" class="ocontent"></div></div><div class="bottom"></div><div class="bottom2"></div>';
				pan.innerHTML = h;
			}else if(con.mode == 0){	
				pan.style.width=((con.width==null)?144:con.width)+'px';	
				var h = '<div id="jesong_nhead"><div id="jesong_ncls" onclick="javascript:jeasy.win.close()">X</div><div id="jesong_ntitle"></div></div><div id="jesong_ncontent" ></div>';
				h+='<div id="nfoot" onclick="return false">';
				if(con.phone.length !=0){ h+='<div class=".tel" style="height:20px;line-height:20px;text-align:center;color:#000000;">'+con.phone+'</div>';};	
				if(con.qccode.length !=0){ h+='<div class=".qccode"><img style="width:'+(con.width-2)+'px;height:'+(con.width-2)+'px;" src="'+con.qccode+'"></img></div>';}
				h+='</div>';
				pan.innerHTML = h;pan.className = 'jesong_pan_flat';
	            try{
		            u.$('jesong_nhead').style.backgroundColor=con.headBgClr; }catch(e){}
		            try{u.$('jesong_nhead').style.color = con.headClr; }catch(e){}
		            try{pan.style.borderColor = con.borderClr;}catch(e){}
		            try{    u.$('jesong_ntitle').innerHTML = con.title;
	            }catch(e){}		
			}else if(con.mode == 2){
				pan.style.backgroundImage = "url("+con.winBg+")";
				pan.style.height = con.height+"px";
				pan.style.width = con.width+"px";
				var contentStyle = "position:absolute;top:"+con.winMTop+"px;left:"+con.winMLeft+"px;height:"+con.winMHeight+"px;width:"+con.winMWidth+"px;";
				pan.innerHTML = "<div style=\""+contentStyle+"\">"+jeasy.win.buildList(con.mode)+"</div>";
			}
			if(con.mode == 0 || con.mode == 1){
				var main = u.$((con.mode == 1)?'jesong_ocontent':'jesong_ncontent');
				(con.mode!=1) ||(main.style.height = con.height+'px');		
				main.innerHTML = '<div id="jesong_allcontent">'+jeasy.win.buildList(con.mode)+'</div>';
				if(con.mode != 1){pan.style.width = con.width+'px';if(jeasy.win.cols > 20){main.style.height=350+'px';main.style.overflowY='auto';pan.style.width=con.width+20+'px';}}
			}
		};
	jeasy.win.openChat=function(et,tar){
		console.log("begin webcall:jeasy.win.openChat function");
		jeasy.util.winChat(function(){
			var p = '';
			if( et == 'c')
				p = 'n='+tar;
			else if(et == 'g'&&tar!=null&&tar!='0')
				p = 'g=' + tar;
			jeasy.util.openChat(p);
		});
	};
	jeasy.win.scroll=function(p){	
		var u = jeasy.util;	
		var a = jeasy.win.config.height/2;
		u.$('jesong_allcontent').scrollTop = parseInt(u.$('jesong_allcontent').scrollTop) + (p==1?-a:a) ;
	};
	jeasy.win.buildList=function(mode){
		console.log("begin webcall:jeasy.win.buildList function");
		var ele = function(et,sn,tar,stat,off){
			var mo = jeasy.win.config.mode;
			var sc = ['jesong_offline','jesong_online','jesong_offline','jesong_offline','jesong_offline','jesong_other','jesong_other','jesong_other'];	
			var sofft = ['\u7559\u8a00','\u81ea\u52a9','\u7535\u8bdd','\u77ed\u4fe1'],sont=['\u5728\u7ebf','\u5fd9\u788c','\u79bb\u5f00'];	
			var clk = (et !='gw' && et != 'd');
			var c='';
			if(clk)	c+= (stat==0?sc[stat+4]:sc[stat]);				
			var h = '<div class="' + (et=='c'?'group_content':'group_title') +'" ';
			if( clk ) h += 'onclick=javascript:jeasy.win.openChat("' + et + '","' + tar + '") ';
			if( (et=='d' || et =='gw'))
				h +='id="jesong_hand_' + tar +'" onclick=javascript:jeasy.win.folder("'+tar+'")';
			
			h += '>';	
			var cls=(stat==0?sc[off+4]:sc[stat]);
			if(clk)	h+='<div class="'+cls+'">';
			if( clk ){var s = (stat==0?sofft[off]:sont[stat-1]);  h += '<div class="jesong_status">'+s+'</div>';}
			h += '<div class="jesong_link" ';
			
			h += '>' +((mo==1 && et!='c')?'<span>&gt;&nbsp;</span>':'') + sn+'</div></div>';	
			if(clk) h+='</div>';	
			return h;
			
		};
		var html ='';
		var depts = jeasy.win.config.customers.groups;
		var m = jeasy.win.config.customers.mode;
		var cols = 0;
		var onlineStyle;
		var offlineStyle;
		if(mode == 2){
			var conf = jeasy.win.config;
			onlineStyle = "background:url("+conf.winGOnlineBg+");height:"+conf.winGHeight+"px;margin-top:"
				+conf.winGTop+"px;margin-bottom:"+conf.winGBottom+"px;line-height:"+conf.winGHeight
				+"px;color:"+conf.winGOnlineColor+";cursor:pointer;font-size:"+conf.winGOnlineWeight+"px;font-family:"+conf.winGOnlineFont+";padding-left:"+conf.winGTextleft+"px;";
			offlineStyle = "background:url("+conf.winGOfflineBg+");height:"+conf.winGHeight+"px;margin-top:"
				+conf.winGTop+"px;margin-bottom:"+conf.winGBottom+"px;line-height:"+conf.winGHeight
				+"px;color:"+conf.winGOfflineColor+";cursor:pointer;font-size:"+conf.winGOnlineWeight+"px;font-family:"+conf.winGOnlineFont+";padding-left:"+conf.winGTextleft+"px;";
		}
		for(var v=0;v<depts.length;v++){
			var dept = depts[v];		
			var elet = 'g';
			var stat = (dept.active > 0?1:0);
			var off = null;cols++;
			if(elet == 'g')
				off = 0;
			if(mode==0){
				html += ele('g',dept.name,dept.id,stat,off);
				/*if(elet == 'd' || elet == 'gw'){	
					for( var v1=0;v1<dept.customers.length;v1++){
						var cust = dept.customers[v1];cols++;
						html += ele('c',cust.name==null?cust.id:cust.name,cust.id,cust.status,cust.offline);
					}	
				}*/
			}else if(mode == 1){
				var className="offline";
				if(stat == 1){
					className="online";
				}
				html += '<div class="'+className+'" onclick=javascript:jeasy.win.openChat("g","' + dept.id + '")>'+dept.name+'</div>';
			}else if(mode == 2){
				var style = offlineStyle;
				if(stat == 1){
					style = onlineStyle;
				}
				html += '<div style="'+style+'" onclick=javascript:jeasy.win.openChat("g","' + dept.id + '")>'+dept.name+'</div>';
			}
		}	
		jeasy.win.cols = cols;
		return html;
	};
	jeasy.win.close=function(){	
		jeasy.util.hide('jesong_panel');	
	};
	jeasy.win.folder=function(id){
		
	};
	jeasy.icon.hide = function(e){
		console.log("begin webcall:jeasy.icon.hide function");
		document.getElementById('jesong_panel').style.display='none';
		if (e && e.stopPropagation){
	        e.stopPropagation();
		}else{
	        window.event.cancelBubble=true;
		}
		return false;
	};
	//
	jeasy.icon.builder=function(){
		console.log("begin webcall:jeasy.icon.builder function");
			function efs(s){
				var ev = jeasy.env.server,s1='http://localhost/';
				if(s.indexOf(s1) != -1)
					s = ev.file + s.substring(s1.length);
				return s;
			}
			var con = jeasy.icon.config,u=jeasy.util;
			var pan=u.$('jesong_panel');
			pan.className = 'jesong_pan_icon';
			con.online = efs(con.online);
			con.offline = efs(con.offline);
			pan.style.height=con.height+'px';
			var bgcss = "";
			if(jeasy.panel.config.position == 4){
				pan.style.width=jeasy.util.getBody().clientWidth+'px';
				bgcss = "background:url("+ (con.status==1?con.online:con.offline) + ") no-repeat center center;";
			}else if(jeasy.panel.config.position == 5){
				pan.style.width=jeasy.util.getBody().clientWidth+'px';
				bgcss = "background:url("+ (con.status==1?con.online:con.offline) + ") no-repeat center center;background-size:100%;";
			}else{
				pan.style.width=con.width+'px';
				bgcss = "background:url("+ (con.status==1?con.online:con.offline) + ") no-repeat center center;background-size:100%;";
			}
			var html = "<div style=\"width:100%;height:100%;"+bgcss+"\"></div>";
			if(con.closeWidth>0 && con.closeHeight>0){
				html+="<div onclick=\"return jeasy.icon.hide(event);\" style=\"position:absolute;z-index:99999;width:"+con.closeWidth+"px;height:"+con.closeHeight+"px;top:"+con.closeTop+"px;left:"+con.closeLeft+"px\"></div>";
			}
			pan.innerHTML=html;
			//绑定打开事件
			pan.onclick=jeasy.icon.openChat;		
		};
	jeasy.util.winChat = function(commonFun){ 
		var params;
		if(jeasy.force != null){
			params = jeasy.force.getWinChatParam();
		}
		if(params && params.chatting){
			//if(jeasy.env.min != 1){
				jeasy.util.openChat("command=applyChat&cId="+params.chatId+"&n="+params.customerId);
			/*}else if(jeasy.force.status != 2){
				jeasy.force.connect(params.chatId, params.customerId);
			}*/
		}else{
			commonFun();
		}
	};
	//打开聊天窗口
	jeasy.icon.openChat=function(){
		jeasy.util.winChat(function(){
			var p = '';
			var con = jeasy.icon.config;
			if( con.mode == 0){ if(con.target !='' && con.target != '0') p+='n='+con.target;}
			else if(con.mode == 1){if(con.target !='' && con.target != "0") p+='g='+con.target;}
			else if(con.target!=null&&con.target!='0') p+='g='+con.target;
			jeasy.util.openChat(p);
		});
	};

	jeasy.text.builder=function(){};
	
	
	jeasy.commonReady = function(){
		jeasy.ready();
	};
	jeasy.loadForceJS = function(){
		jeasy.util.loadJS(jeasy.env.server.file+"/js/2020/force.js?ver="+jeasy.version);
	},
	/*jeasy.initUI=function(){
		if(jeasy.config.monitor){
			jeasy.monitor.init();
			jeasy.monitor.start();
		}
	};*/
	jeasy.initProbe = function(){
		if(jeasy.probe.texts != "" && jeasy.probe.groupIds !=""){
			var ids=jeasy.probe.texts.split(','),gids =jeasy.probe.groupIds.split(',');
			var regs={};
	        var snifferNode=function(nd){
	            var o_p = nd.parentNode;
	            for(var i=0;i<ids.length;i++){
	                if(nd.nodeType==3 && o_p.nodeName!='A'){
	                    var re =  regs[ids[i]];
	                    if(!re){
	                       re = regs[ids[i]] = new RegExp('('+ids[i]+')',"ig");
	                    }
	                    if( nd.data.search(re) == -1) continue;
	                    var span = document.createElement('span');
	                    span.innerHTML =nd.data.replace(re,'<a href="javascript:void(0);" onclick="jeasy.util.openChat(\'g=' + gids[i]+'\');return false">$1</a>');
	                    o_p.replaceChild(span,nd);
	                }else if(nd.nodeType==1){
	                    if(nd.id == ids[i] || nd.className == ids[i] || nd.nodeName=='IMG'&&nd.src.indexOf(ids[i])>0){
	                    	var groupId = gids[i];
	                    	jeasy.util.attach(nd,'click', function(){
	                    		jeasy.util.openChat.apply(this, ['g='+groupId]);
	                    	});
	                        nd.style.cursor="pointer";
	                    }
	                }
	
	            }
	        };
	        var processNode=function(nd){
	            var o =nd.childNodes;
	            snifferNode(nd);
	            if(o&&o.length>0)
	                for(var i=0;i<o.length;i++){
	                   processNode(o[i]);
	                }
	        };
	        if(ids.length){
	            processNode(document.body);
	        }
		}
	};
	jeasy.initId = function(){
		var u = jeasy.util,ev=jeasy.env;
		u.upvid();
		ev.uId = u.getCookie('JESONG_USER_ID');
		if(u.isEmpty(ev.uId)||ev.uId=='undefined'){
		    ev.uId = ev.vId;
	      	u.setCookie('JESONG_USER_ID',ev.uId);
   		}
		var vId = u.getCookie('JESONG_VISITOR_ID');
		if(!u.isEmpty(vId) && vId!='undefined'){
			ev.vId = vId;
		}
	};
	jeasy.ready=function(){
		console.log("begin webcall:jeasy.ready function");
		if(!jeasy.isReady){
			jeasy.isReady = true;
			
			if(jeasy.config.panel){jeasy.panel.init();}	
			function _setTop(id){
				var p=jeasy.util.$(id);var par = p.parentNode;if(par!=null&&par.nodeName != 'body'){par.removeChild(p);document.body.appendChild(p);}
			}
			if(jeasy.config.panel&&jeasy.panel.config.position !=-1){
				_setTop('jesong_panel');
			}
			_setTop('jesong_chat_layout');
			
			if(jeasy.config.monitor){
				jeasy.monitor.init();
				jeasy.monitor.start();
			}
			
			if(jeasy.visitorReady){
				jeasy.loadForceJS();
			}
			
			if(jeasy.jsType != 1 && !jeasy.env.isPhone){
				jeasy.freecall.init();
			}
			jeasy.initProbe();
		}		
	};
	try{
		jeasy.initId();
		jeasy.monitor.record();
		
		/*if(!jeasy.lazy){
			jeasy.util.bindReady();
		}else{*/
			jeasy.ready();
		/*}*/
		
		var extCode = JEASYICC_MESSAGE_TEXT.extCode;
		if(extCode != ""){
			eval(extCode);
		}
	}catch(e){
		window.sendJeasyiccError(1, e.message);
	}
	//clearTimeout方法可取消由 setTimeout() 方法设置的定时操作
	window.clearTimeout(window.jeasyiccErrorTimeout);
})();





