	

	//登陆方法
	function login(username,password){
		var uuid = getSESSIONUUID();
		var message = {
				action:{
					command:"login",
					params:null
				},
				data:{
					username : username
					,password : password
					,uuid : uuid
				}
		}
		ws.send(JSON.stringify(message));
	}

	//登录成功
	function loginSuccess(action,data){
		go_to("/EBTC/myAcc");
	}

	function loginFail(action,data){
		var message = data.errorMessage;
		pageFrame.window.loginFail(message);
	}

	//处理action
	function doAction(message){
		if(message != undefined && message != null){
			var action = message.action;
			if(action != undefined && action != null){
				var command = action.command;
				if(command != undefined && command != null){
					//刷新
					if(command == "refresh"){
						var params = action.params;
						refresh(params,message.data);
					}else if(command == "loginSuccess"){
						loginSuccess(action,message.data);
					}else if(command == "loginFail"){
						loginFail(action,message.data);
					}
				}
			}
		}
	}
	
	//刷新
	function refresh(params,data){
		if(params != undefined && params[0] != null){
			if(params[0] == "currentRate"){
				if(data != undefined && data != null){
					//把数据缓存在客户端
					$('#currentRateData').text(JSON.stringify(data));
					//刷新买1-买5
					var highPriceBuy = data.highPriceBuy;
					var $highPriceBuy = $('#highPriceBuy');
					$highPriceBuy.empty();
					if(highPriceBuy != undefined && highPriceBuy != null){
						for(var i=highPriceBuy.length-1;i>=0;--i){
							var tradeOrderInfo = highPriceBuy[i];
							if(tradeOrderInfo != undefined && tradeOrderInfo != null){
								if(i==0){
									//写入最好价格
									$('#buyBestPrice').val(tradeOrderInfo.price);
									$('#buyPrice').val(tradeOrderInfo.price);
								}
								var line = "<tr><td>buy("+(i+1)+")</td><td>"+tradeOrderInfo.price+"</td><td>"+tradeOrderInfo.quantity+"</td></tr>"
								$highPriceBuy.append(line);
							}else{
								var line = "<tr><td>buy("+(i+1)+")</td><td>无数据</td><td>无数据</td></tr>"
								$highPriceBuy.append(line);
							}
						}
					}
					//刷新卖1-卖5
					var lowPriceSell = data.lowPriceSell;
					var $lowPriceSell = $('#lowPriceSell');
					$lowPriceSell.empty();
					if(lowPriceSell != undefined && lowPriceSell != null){
						for(var i=0; i<lowPriceSell.length; ++i){
							var tradeOrderInfo = lowPriceSell[i];
							if(tradeOrderInfo != undefined && tradeOrderInfo != null){
								if(i==0){
									$('#sellPrice').val(tradeOrderInfo.price);
									$('#sellBestprice').val(tradeorderInfo.price);
								}
								var line = "<tr><td>sell("+(i+1)+")</td><td>"+tradeOrderInfo.price+"</td><td>"+tradeOrderInfo.quantity+"</td></tr>"
								$lowPriceSell.append(line);
							}else{
								var line = "<tr><td>sell("+(i+1)+")</td><td>无数据</td><td>无数据</td></tr>"
								$lowPriceSell.append(line);
							}
						}
					}
				}
			}
		}
	}
	
	function getSESSIONUUID(){
		var uuid = $.cookie("SESSIONUUID");
		if(uuid == undefined || uuid == null || uuid == ""){
			uuid = new UUID().toString();
//			$.cookie('SESSIONUUID', uuid, {expires: 7*3600*24*1000, path: '',domain:'localhost', secure: true});
			$.cookie('SESSIONUUID',uuid);
		}
		return uuid;
	}
	
	var ws;
	//服务请求
	function serviceRequest(params){
		//处理单服务请求参数填写字符串
		if(typeof params == "string"){
			var tem = params;
			params = new Array();
			params[0] = tem;
		}
		var uuid = getSESSIONUUID();
		if (!window.WebSocket) {
			alert("FATAL: WebSocket not natively supported. This demo will not work!");
		}else{
			ws = new WebSocket("ws://127.0.0.1:22222");
			ws.onopen = function() {
				console.info("[WebSocket#onopen]");
				
				var locale = new Array();
				locale[0] = (navigator.language  ||  navigator.userLanguage).toString().toLowerCase();
				
				var setLocale = {
						action:{
							command:"setLocale"
							,params:locale
						}
						,data:{
							uuid:uuid
						}
				};
				ws.send(JSON.stringify(setLocale));
				
				var message = {
						action:{
							command:"service",
							params:params
						}
						,data:{
							uuid:uuid
						}
					};
				ws.send(JSON.stringify(message));
			};
			
			ws.onmessage = function(e) {
				if(e.data != undefined && e.data != null){
					var message = JSON.parse(e.data);
					if(message != undefined && message != null){
						doAction(message);
					}
				}
				
				console.info("<b style='color:red;'>[WebSocket#onmessage] 接收消息:" + e.data + "</b>");
			};
			ws.onclose = function() {
				console.info("[WebSocket#onclose]");
				ws = null;
			};
			
		}
		
	}
	
	function computePrice(type){
		var reg = new RegExp("^\\s*$");
		if(type == 'buy'){
			//变量
			var $buyUsableBalance = $('#buyUsableBalance');
			var $buyableQuantity = $('#buyableQuantity');
			var $buyPrice = $('#buyPrice');
			var $buyQuantity = $('#buyQuantity');
			var $buyTotalSpend = $('#buyTotalSpend');
			var $buyHandlingFee = $('#buyHandlingFee');
			var $buyHandlingFeeRate = $('#buyHandlingFeeRate');
			
			var buyPrice = parseFloat(reg.test($buyPrice.val()) ? "0" : $buyPrice.val());
			var buyQuantity = parseFloat(reg.test($buyQuantity.val()) ? "0" : $buyQuantity.val());
			
			var buyUsableBalance = parseFloat(reg.test($buyUsableBalance.text()) ? "0" : $buyUsableBalance.text());
			//计算可购买数量
			var buyableQuantity = isNaN((buyUsableBalance / buyPrice).toFixed(8)) ? "0" : (buyUsableBalance / buyPrice).toFixed(8);
			$buyableQuantity.text(buyableQuantity);
			//计算总额
			var buyTotalSpend = (buyPrice * buyQuantity).toFixed(8);
			$buyTotalSpend.text(buyTotalSpend);
			//计算手续费
			var buyHandlingFeeRate = parseFloat($buyHandlingFeeRate.val());
			buyHandlingFee = (buyTotalSpend * buyHandlingFeeRate).toFixed(8);
			$buyHandlingFee.text(buyHandlingFee);
			
		}else if(type == "sell"){
			var $sellHandlingFeeRate = $('#sellHandlingFeeRate');
//			var $sellUsableBalance = $('#sellUsableBalance');
			var $sellPrice = $('#sellPrice');
			var $sellQuantity = $('#sellQuantity');
			var $totalSellAmount = $('#totalSellAmount');
			var $sellHandlingFee = $('#sellHandlingFee');
			
			var sellPrice = parseFloat(reg.test($sellPrice.val()) ? "0" : $sellPrice.val());
			var sellQuantity = parseFloat(reg.test($sellQuantity.val()) ? "0" : $sellQuantity.val());
			
			//计算总额
			var totalSellAmount = (sellPrice * sellQuantity).toFixed(8);
			$totalSellAmount.text(totalSellAmount);
			//计算手续费
			var sellHandlingFeeRate = parseFloat($sellHandlingFeeRate.val());
			sellHandlingFee = (totalSellAmount * sellHandlingFeeRate).toFixed(8);
			$sellHandlingFee.text(sellHandlingFee);
		}
		
	}
	
	function isLogined(){
		var $login_user = $('#login_user');
		if($login_user != undefined && $login_user.text() != ""){
			return true;
		}
		return false;
	}
	
	function newOrder(type){
		
		var params = new Array();
		params[0] = type;
		
		//检查是否有登陆。
		if(!isLogined()){
		//弹出模态登陆窗口
			openLoginBox();
			return;
		}
		//有登陆
		
		//校验数据是否有足够的余额
		if(type == "buy"){
			var buyUsableBalance = $("#buyUsableBalance").text();
			var price = $("#buyPrice").text();
			var buyQuantity = $("#buyQuantity").text();
			var buyTotalSpend = $('#buyTotalSpend').text();
		}else if(type == "sell"){
			
		}
		
		
		
		//封装数据
		var data;
		data.price = "";
		data.quantity = "";
		
		//用sokect请求下单
		//下单结果写在message里边
		var message = {
			action:{
				command:"newOrder",
				params:params
			}
			,data:data
		};
		
		ws.send(JSON.stringify(message));
		
	}
	
	function addCookie(name,value,expireHours){ 
        var cookieString=name+"="+escape(value); 
        //判断是否设置过期时间 


        if(expireHours>0){ 
               var date=new Date(); 
               date.setTime(date.getTime+expireHours*3600*1000); 
               cookieString=cookieString+"; expire="+date.toGMTString(); 
        } 
        document.cookie=cookieString; 
	}
	
	function getCookie(name){ 
        var strCookie=document.cookie; 
        var arrCookie=strCookie.split("; "); 
        for(var i=0;i<arrCookie.length;i++){ 
              var arr=arrCookie[i].split("="); 
              if(arr[0]==name)return arr[1]; 
        } 
        return ""; 
	}
	
	function deleteCookie(name){ 
        var date=new Date(); 
        date.setTime(date.getTime()-10000); 
        document.cookie=name+"=v; expire="+date.toGMTString(); 
	} 
	
