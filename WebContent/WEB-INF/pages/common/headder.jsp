<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ebtc.common.constants.Constants;" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8" />
<title>易比特 | 服务最好的比特币交易网站 | 最安全的比特币交易网站</title>
<meta name="description" content="Administry - Admin Template by www.865171.cn" />
<meta name="keywords" content="Admin,Template" />
<meta http-equiv="pragma" content="no-cache">
<!-- Favicons --> 
<link rel="shortcut icon" type="image/png" href="${ctx}/img/favicons/favicon.png"/>
<link rel="icon" type="image/png" href="${ctx}/img/favicons/favicon.png"/>
<link rel="apple-touch-icon" href="${ctx}/img/favicons/apple.png" />
<!-- Main Stylesheet --> 
<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css" />
<!-- Your Custom Stylesheet --> 
<link rel="stylesheet" href="${ctx}/css/custom.css" type="text/css" />
<!--swfobject - needed only if you require <video> tag support for older browsers -->
<%-- <script type="text/javascript" src="${ctx}/js/swfobject.js"></script> --%>
<!-- jQuery with plugins -->
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<!-- Could be loaded remotely from Google CDN : <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script> -->
<script type="text/javascript" src="${ctx}/js/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.ui.tabs.min.js"></script>
<!-- jQuery tooltips -->
<script type="text/javascript" src="${ctx}/js/jquery.tipTip.min.js"></script>
<!-- Superfish navigation -->
<script type="text/javascript" src="${ctx}/js/jquery.superfish.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.supersubs.min.js"></script>
<!-- jQuery form validation -->
<script type="text/javascript" src="${ctx}/js/jquery.validate_pack.js"></script>
<!-- jQuery popup box -->
<script type="text/javascript" src="${ctx}/js/jquery.nyroModal.pack.js"></script>
<!-- jQuery model Divbox -->
<script type="text/javascript" src="${ctx}/js/jquery.divbox.js"></script>
<!-- administry.js -->
<script type="text/javascript" SRC="js/administry.js"></script>
<!-- Internet Explorer Fixes --> 
<!--[if IE]>
<link rel="stylesheet" type="text/css" media="all" href="${ctx}/css/ie.css"/>
<script src="${ctx}/js/html5.js"></script>
<![endif]-->
<!--Upgrade MSIE5.5-7 to be compatible with MSIE8: http://ie7-js.googlecode.com/svn/version/2.1(beta3)/IE8.js -->
<!--[if lt IE 8]>
<script src="${ctx}/js/IE8.js"></script>
<![endif]-->
	<script type="text/javascript"> 
		
		$(function(){
			parent.autoWH(document.body.scrollWidth,document.body.scrollHeight);
		});
		
		function go_to(uri){
			
		}
		
		function openLoginBox(){
			$("#login_div").OpenDiv(); 
		}
	
		function openRegister() { 
			$("#register_div").OpenDiv(); 
			var url = "${ctx}/user/toRegist";
			$('#openRegist_form').attr('target','regist_iframe').attr('action',url).submit();
		} 
		
		function login(){
// 			var loginForm = $('#login_form');
// 			loginForm.attr('action','${ctx }/user/login');
// 			loginForm.attr('method','post');
// 			loginForm.submit();
			var username = $("input[name='username']")[0].value;
			var password = $("input[name='password']")[0].value;
			
			parent.login(username,password);
		}
		
		function closeRegister(tar) {
			$(tar).parent('.model_div').CloseDiv();
		} 
		
		$(function(){
			/* setup navigation, content boxes, etc... */
			Administry.setup();
		});
	</script>
</head>
<body>
<!-- 注册页面用的模态层 -->
	<div id="register_div" class="model_div register_div corners">
		<div class="close_btn" onclick="closeRegister(this)">关闭</div>
		<form target="regist_iframe" id="openRegist_form" action=""></form>
		<iframe style="width:100%;height:535px;" id="regist_iframe" name="regist_iframe"></iframe>
	</div> 
<!-- 	登陆窗口用的模态层 -->
	<div id="login_div" class="model_div login_div corners">
		<div class="close_btn" onclick="closeRegister(this)">关闭</div>
	</div>
<!-- 	弹出消息用模态层 -->
	<div id="message_div" class="model_div message_div corners">
		<div class="close_btn" onclick="closeRegister(this)">关闭</div>
	</div>
	<!-- Header -->
	<header id="top">
		<div class="wrapper">
			<!-- Title/Logo - can use text instead of image -->
			<div id="title"><img src="${ctx}/img/logo.png" alt="Administry" /><!--<span>Administry</span> demo--></div>
			<!-- Top navigation -->
			<div id="topnav">
<!-- 				<a href="#"><img class="avatar" src="${ctx}/img/user_32.png" alt="" /></a> -->
				<div>
					<c:choose>
						<c:when test="${empty sessionScope.login_user }">
							<fmt:message key="username"/><input style="margin:5px;width:100px" name="username" value="${sessionScope.username }"/>
							<c:remove var="username" scope="session" />
							<fmt:message key="password"/><input style="margin:5px;width:100px" name="password" type="password" /><br>
<%-- 							<c:if test="${not empty sessionScope.error }"> --%>
<%-- 								<small style="color:red;"><fmt:message key="${error }" /></br></small> --%>
<%-- 								<c:remove  var="error"  scope="session"  /> --%>
<%-- 							</c:if> --%>
							<a href="#"><fmt:message key="forgotPassword"/></a>
							<span>|</span>
							<a href="#"><fmt:message key="forgotUsername"/></a></br>
							<a style="padding:4px;margin:5px" class="btn btn-blue" onclick="login()"><fmt:message key="login"/></a>
							<a style="padding:4px;margin:5px" class="btn btn-green" onclick="openRegister()"><fmt:message key="signUp"/></a>
						</c:when>
						<c:otherwise>
							欢迎回来! <b id="login_user">${sessionScope.login_user.nickname }</b>
							<span>|</span> <a href="${ctx }/user/logout">退出</a><br />
							<small>您有<a href="#" ><b>1</b> 新信息!</a></small>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<!-- End of Top navigation -->
			
			<!-- Main navigation -->
			<nav id="menu">
				<ul class="sf-menu">
					<c:forEach items="${applicationScope.user_menu }" var="menu">
						<c:choose>
							<c:when test="${fn:contains(requestScope.uri,menu.uri) }">
								<li class="current">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
							<a onclick="parent.go('${ctx }${menu.uri }')"><fmt:message key="${menu.name }" /></a>
							<c:if test="${not empty menu.subMenus }">
								<ul>
									<c:forEach items="${ctx }${menu.subMenus }" var="subMenu">
										<li>
											<a onclick="parent.go('${ctx }${subMenu.uri }')"><fmt:message key="${subMenu.name }" /></a>
										</li>
									</c:forEach>
								</ul>
							</c:if>
						</li>
					</c:forEach>
				</ul>
			</nav>
			<!-- End of Main navigation -->
			<!-- Aside links -->
			<%=request.getLocale().toString() %>
			<%if(request.getLocale().equals(Locale.US)){%>
			<aside><b style="color:white">English</b> &middot; 
			<a href="?locale=zh_CN">简体中文</a> &middot; 
			<a href="?locale=zh_TW">繁体中文</a></aside>
			<%} else if(request.getLocale().equals(Locale.CHINA)){%>
			<aside><a href="?locale=en_US">English</a> &middot; 
			<b style="color:white">简体中文</b> &middot; 
			<a href="?locale=zh_TW">繁体中文</a></aside>
			<%}else if(request.getLocale().equals(Locale.TAIWAN)){ %>
			<aside><a href="?locale=en_US">English</a> &middot; 
			<a href="?locale=zh_CN">简体中文</a> &middot; 
			<b style="color:white">繁体中文</b></aside>
			<%} %>
			<!-- End of Aside links -->
		</div>
	</header>
	<!-- End of Header -->
	<!-- End of Page title -->
	