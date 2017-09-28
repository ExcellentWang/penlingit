<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>在路上APP管理后台</title>
    <link href="<c:url value="/static/css/login.css"/>" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<c:url value='/static/js/easyui1.4.1/jquery.min.js'/>"></script>
</head>
<body>
<form id="form1" method="post">
    <div class="content">
        <div class="login-div">
            <h1 class="log_img"></h1>
            <h4 class="log_title">在路上APP管理平台11</h4>
            <div class="form_ipt">
                <p><i class="user"></i><input type="text" placeholder="请输入用户名" id="userName" name="username"></p>
                <p><i class="pwd"></i><input type="password" placeholder="请输入密码" id="passWord" name="passWord"></p>
            </div>
            <div id="loginBtn" class="login-btn">立即登录</div>
            <div id="errorMessages" class="errorMessages"></div>
        </div>
    </div>
</form>
<script>
    $(function () {
        $('#loginBtn').click(function () {
            var userName = $.trim($('#userName').val());
            var passWord = $('#passWord').val();
            if (userName.length == 0 && passWord.length == 0) {
                $('#errorMessages').html("请输入用户名与密码！");
                return false;
            }
            if (userName.length == 0) {
                $('#errorMessages').html("请输入用户名！");
                return false;
            }
            if (passWord.length == 0) {
                $('#errorMessages').html("请输入密码！");
                return false;
            }
            $.post("login", {"userName": userName, "passWord": passWord}, function (data) {
            	console.log(data);
                if (data.success) {
                    window.location.href = 'main';
                } else {
                    $('#errorMessages').html(data.msg);
                }
            }, "json");
        });
        $(document).keydown(function (e) {
            //13等于回车键(Enter)键值,ctrlKey 等于 Ctrl
            if (e.which == 13) {
                $('#loginBtn').click();
            }
        });
    });
</script>
</body>
</html>
