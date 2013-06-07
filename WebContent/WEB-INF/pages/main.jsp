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
	<!-- JQUERY -->	
	<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
	<!-- JSON -->
	<script type="text/javascript" src="${ctx}/js/json/json2.min.js"></script>
	<!-- WS_OPERATIONS_COMMUNICATIONS -->
	<script type="text/javascript" src="${ctx}/js/ebtc/operations_communications.js"></script>
	<script type="text/javascript" src="${ctx}/js/uuid.js"></script>
	<script type="text/javascript">
		//调整宽和高
		function autoWH(w,h){
			$('#pageFrame').css("width",w);
			$('#pageFrame').css("height",h);
		}
		
		//跳转到
		function go(uri){
			var $pageForm = $('#pageForm');
			$pageForm.attr('action',uri);
			
			var uuid = getSESSIONUUID();
			if(uuid != undefined && uuid != null && uuid != ""){
				var $uuid = $('<input type="hidden" name="uuid"/>');
				$uuid.val(uuid);
				$pageForm.append($uuid);			
			}
			
			$pageForm.submit();
		}
		
		$(function(){
			var pageFrameWindow = document.getElementById('pageFrame').contentWindow;
			//绑定内容变动事件
			$('#currentRateData').bind('DOMNodeInserted', function(e) {
				pageFrameWindow.updateCurrentRate();
			});
			serviceRequest(["currentRate","transactionRecord"]);
			go("${ctx}/index");
		});
		
		function update(){
			alert(12);
		}
		
	</script>
	<style type="text/css">
		body{
			margin:0px;
			padding:0px;
		}
		
		#pageFrame{
		margin:0px;
		padding:0px;
		border:0px;
		width:100%;
		height:100%;
		}
	</style>
	</head>

	<body>	
		<div style="display:none;" id="datas">
			<div id="currentRateData"></div>
		</div>
		<form style="display:none;" id="pageForm" method="post" target="pageFrame">
		</form>
		<iframe id="pageFrame" name="pageFrame">
		</iframe>
	</body>
</html>