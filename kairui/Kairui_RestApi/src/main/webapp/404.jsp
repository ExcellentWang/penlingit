<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<title>Nothing found for 404</title>
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<style>
body{background:#fff;margin:0 auto;padding:0;text-align:center;font-family: "Microsoft YaHei" ! important;font-size:14px;color:#666666;overflow:hidden;min-width:320px;max-width:640px;}
.error_page{width:100%;height:300px;padding:50px 0;margin:auto;}
.error_page h1{margin:20px 0 0;}
.error_page p{margin:10px 0;padding:0;}
a{color:#9caa6d;text-decoration:none;}
a:hover{color:#9caa6d;text-decoration:underline;}
</style>
</head>
<body>
<div class="error_page">
	<img src="<c:url value="/static/images/404.png"/>" width="60%" />
	<h1>对不起</h1>
	<p>无法找到您正在浏览的页面。</p>
</div>
</body>
</html>