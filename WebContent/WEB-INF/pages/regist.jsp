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
			<script type="text/javascript">
				$(document).ready(function(){
					// 手机号码验证
					jQuery.validator.addMethod("isMobile", function(value, element) {
					var length = value.length;
					return this.optional(element)
					|| (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/
					.test(value));
					}, "<font color='red'>请正确填写您的手机号码</font>");
					
					// 字母字符验证
					jQuery.validator.addMethod("stringCheck", function(value, element) {
					return this.optional(element)
					|| /^[\w]+$/.test(value);
					}, "<font color='red'>只能包括英文字母、数字和下划线</font>");

					// 中文字符验证
					jQuery.validator.addMethod("chineseCheck", function(value, element) {
					return this.optional(element)
					|| /^[\u0391-\uFFE5\w]+$/.test(value);
					}, "<font color='red'>只能包括中文、英文字母、数字和下划线</font>");
					
					// validate signup form on keyup and submit
					var validator = $("#registForm").validate({
						rules: {
							username: {
								required:true,
								stringCheck:true,
								rangelength:[6,30],
								remote:{
									url: "${ctx}/user/uniqueChecker", //后台处理程序    
			                   		type: "get",  //数据发送方式   
			                   		dataType: "json",       //接受数据格式       
			                    	data: {                     //要传递的数据   
			                      		value: function () {
			                            	return $("#username").val();
			                 			},
			       						type:"username"
			                        }
								}
							},
							password:{
								required:true,
								rangelength:[6,30]
							},
							passwordConfirm:{
								required:true,
								equalTo:'#password'
							},
							nickname:{
								required:true,
								chineseCheck:true,
								rangelength:[2,20]
							},
							mobile:{
								required:true,
								isMobile:true,
								remote:{
									url: "${ctx}/user/uniqueChecker", //后台处理程序    
			                   		type: "get",  //数据发送方式   
			                   		dataType: "json",       //接受数据格式       
			                    	data: {                     //要传递的数据   
			                      		value: function () {
			                            	return $("#mobile").val();
			                 			},
			       						type:"mobile"
			                        }
								}
							},
							email:{
								required:true,
								email:true,
								remote:{
									url: "${ctx}/user/uniqueChecker", //后台处理程序    
			                   		type: "get",  //数据发送方式   
			                   		dataType: "json",       //接受数据格式       
			                    	data: {                     //要传递的数据   
			                      		value: function () {
			                            	return $("#email").val();
			                 			},
			       						type:"email"
			                        }
								}
							},
							captcha:{
								required:true,
								remote:{
									url: "${ctx}/CatptchaValidator", //后台处理程序    
			                   		type: "get",  //数据发送方式   
			                   		dataType: "json",       //接受数据格式       
			                    	data: {                     //要传递的数据   
			                    		captcha: function () {
			                            	return $("#captcha").val();
			                 			}
			                        }
								}
							}
							
						},
						messages: {
							username: {
								required:"请填写用户名!",
								stringCheck:"只能有字母、数字或下划线组成",
								rangelength:"用户名的长度必须为6-30个字符!",
								remote:"该用户名已被注册!"
							},
							password:{
								required:"请填写密码!",
								rangelength:"密码的长度必须为6-30个字符!",
							},
							passwordConfirm:{
								required:"密码确认不能空!",
								equalTo:'两次密码输入不一致!'
							},
							nickname:{
								required:"请填写昵称!",
								chineseCheck:"只能包括中文、字母、数字、下划线!",
								rangelength:"昵称的长度必须为2-20个字符!"
							},
							mobile:{
								required:"请填写手机号码!",
								isMobile:"手机号码不合法!",
								remote:"该手机号码已被注册!"
							},
							email:{
								required:"请填写您的电子邮箱!",
								email:"电子邮箱地址不合法!",
								remote:"该电子邮箱已被注册!"
							},
							captcha:{
								required:"请填写验证码!",
								remote:"验证码不正确!"
							}
						},
						// the errorPlacement has to take the layout into account
						errorPlacement: function(error, element) {
							error.insertAfter(element.parent().find('label:first'));
						},
						// set new class to error-labels to indicate valid fields
						success: function(label) {
							// set &nbsp; as text for IE
							label.html("&nbsp;").addClass("ok");
						}
					});
				});
				
				function refreshCaptcha(){
					var time = new Date().getTime();
					$('#captchaImage').attr('src','${ctx }/CaptchaServlet?'+time);
				}
				
			</script>
		
			<form id="registForm" method="post" action="${ctx }/user/register">
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
							<p>
								<label class="required" for="username">用户名:</label><br/>
								<input type="text" id="username" class="half" value="" name="username"/>
							</p>
		
							<p>
								<label class="required" for="password">密码:</label><br/>
								<input type="password" id="password" class="half" value="" name="password"/>
							</p>
		
							<p>
								<label class="required" for="passwordConfirm">确认密码:</label><br/>
								<input type="password" id="passwordConfirm" class="half" value="" name="passwordConfirm"/>
							</p>
							
							<p>
								<label class="required" for="nickname">昵称:</label><br/>
								<input type="text" id="nickname" class="half" value="" name="nickname"/>
							</p>
							
							<p>
								<label class="required" for="mobile">手机号码:</label><br/>
								<input type="text" id="mobile" class="half" value="" name="mobile"/>
							</p>
		
							<p>
								<label class="required" for="email">邮箱地址:</label><br/>
								<input type="text" id="email" class="half" value="" name="email"/>
							</p>
							
							<div style="float:left">
								<label class="required" for="captcha">验证码:</label><br/>
								<input type="text" id="captcha" class="quarter" value="" name="captcha"/>
							</div>
							<div>
								<img id="captchaImage" src="${ctx }/CaptchaServlet">&nbsp;<a href="javascript:refreshCaptcha();" >看不清楚</a>
							</div>
							
							<p class="box"><input type="submit" class="btn btn-green big" value="注 册"/> or <input type="reset" class="btn" value="重置"/></p>
						</div>
					</fieldset>
				</div>
			</form>
	</body>
</html>
