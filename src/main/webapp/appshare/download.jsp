<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.mobian.listener.Application" %>
<%
	String downloadUrl = Application.getString("SV500");
%>
<!DOCTYPE html>
<html>
<head>
<title>Diving Download</title>
<meta name="viewport" content="width=device-width, initial-scale=0.66, minimum-scale=0.66, maximum-scale=0.66, user-scalable=yes">
<script type="text/javascript">
	function download() {
		window.location.href="<%=downloadUrl %>";
		/*
		if(isWeiXin()) {
// 	        alert('微信扫描无法下载，请点击右上角切换其他浏览器打开.')
	    } else {
	        switch(getDevice()) {
	            case 'Android':
	            	window.location.href="${pageContext.request.contextPath}/";
	                break;
	            case 'iOS':
	               window.location.href="";
// 	               alert("暂不提供IOS下载！");
	                break;
	            default:
// 	            	alert("暂无官网地址！");
	            	window.location.href="${pageContext.request.contextPath}/";
	                break;
	        }
	    }
		 */
	}
	
	function isWeiXin() {   
	    var ua = window.navigator.userAgent.toLowerCase();  
	    if(ua.match(/MicroMessenger/i) == 'micromessenger') {   
	        return true;  
	    } else {   
	        return false;  
	    }   
	}  
	  
	function getDevice() {  
	    var u = navigator.userAgent;  
	    if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {  
	        return 'Android';  
	    } else if (u.indexOf('iPhone') > -1) {  
	        return 'iOS';  
	    } else {  
	        return 'none';  
	    }  
	}  
</script>
</head>
<body onload="download();">
</body>
</html>