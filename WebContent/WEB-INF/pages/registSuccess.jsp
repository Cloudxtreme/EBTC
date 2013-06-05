<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
</head>
	<body style="background:white;">
		<div style="margin-left:10px; margin-top:10px;margin-right:10px;">
			<fieldset>
				<legend>感谢您注册易比特,我们将竭诚为您提供最优质的服务!</legend>
				<div style="margin-left:10px;">
					<c:if test="${not empty requestScope.info }">
						<div class="box box-info">${requestScope.info }</div>
					</c:if>
					<c:if test="${not empty requestScope.error }">
						<div class="box box-error">${requestScope.error }</div>
					</c:if>
					<c:if test="${not empty requestScope.warn }">
						<div class="box box-warning">${requestScope.warn }</div>
					</c:if>
					<H4>
						注册成功！我们已经向您的电子邮箱发送了激活邮件，请查收并激活账号即可登陆本站！
					</H4>
				</div>
			</fieldset>
		</div>
	</body>
</html>
