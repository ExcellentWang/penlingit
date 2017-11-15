<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">

	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="telephone=no" name="format-detection">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta name="full-screen" content="yes">
	<meta name="x5-fullscreen" content="true">
</head>
<body>
var userId = ${userId};
var share_link = window.location.href;
var share_title = $("title").text();
eval('var userInfo=<c:choose><c:when test="${empty user}">{}</c:when><c:otherwise>${user}</c:otherwise></c:choose>');
wx.config({
		debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId: '${appId}', // 必填，公众号的唯一标识
	    timestamp: ${timestamp}, // 必填，生成签名的时间戳
	    nonceStr: '${nonceStr}', // 必填，生成签名的随机串
	    signature: '${signature}',// 必填，签名，见附录1
	    jsApiList: ['checkJsApi',
	                'onMenuShareTimeline',
	                'onMenuShareAppMessage',
	                'onMenuShareQQ',
	                'onMenuShareWeibo'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});

	zhuge.identify('${openId}');
</body>
</html>
