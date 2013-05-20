<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>
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
		function opernRegister() { 
			$("#register_div").OpenDiv(); 
			var url = "${ctx}/user/toRegist";
			$('#openRegist_form').attr('target','regist_iframe').attr('action',url).submit();
		} 
		function closeRegister() { 
			$("#register_div").CloseDiv(); 
		} 
		
		$(function(){
			/* setup navigation, content boxes, etc... */
			Administry.setup();
		});
	</script>
</head>
<body>
<!-- 注册页面用的模态层 -->
	<div id="register_div" class="model_div corners">
		<div class="close_btn" onclick="closeRegister()">关闭</div>
		<form target="regist_iframe" id="openRegist_form" action=""></form>
		<iframe style="width:100%;height:510px;" id="regist_iframe" name="regist_iframe"></iframe>
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
					<form action="">
						用户名<input style="margin:5px;width:100px"/>
						密码<input style="margin:5px;width:100px" /><br>
						<a href="#">忘记密码</a>
						<span>|</span>
						<a href="#">没有账号？</a></br>
						<a style="padding:4px;margin:5px" class="btn btn-blue" onclick="">点我登陆</a>
						<a style="padding:4px;margin:5px" class="btn btn-green" onclick="opernRegister()">点我注册</a>
					</form>
				</div>
<!-- 				欢迎回来! <b>Admin</b> -->
<!-- 				<span>|</span> <a href="#">退出</a><br /> -->
<!-- 				<small>您有<a href="#" class="high"><b>1</b> 新信息!</a></small> -->
			</div>
			<!-- End of Top navigation -->
			
			<!-- Main navigation -->
			<nav id="menu">
				<ul class="sf-menu">
					<li class="current"><a href="dashboard.html">Dash1board</a></li>
					<li>
						<a href="styles.html">Styles</a>
						<ul>
							<li>
								<a href="styles.html">Basic Styles</a>
							</li>
							<li>
								<a href="#">Sample Pages...</a>
								<ul>
									<li><a href="samples-files.html">Files</a></li>
									<li><a href="samples-products.html">Products</a></li>
								</ul>
							</li>
						</ul>
					</li>
					<li><a href="tables.html">Tables</a></li>
					<li><a href="forms.html">Forms</a></li>	
					<li><a href="graphs.html">Graphs</a></li>	
				</ul>
			</nav>
			<!-- End of Main navigation -->
			<!-- Aside links -->
<!-- 			<aside><b>English</b> &middot; <a href="#">Spanish</a> &middot; <a href="#">German</a></aside> -->
			<!-- End of Aside links -->
		</div>
	</header>
	<!-- End of Header -->
	<!-- End of Page title -->
	
	