<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<!--
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.0.3
Version: 1.5.5
Author: KeenThemes
Website: http://www.keenthemes.com/
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8"/>
    <title>登录到 BusPj</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <meta name="MobileOptimized" content="320">
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="static/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="static/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="static/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" type="text/css" href="static/plugins/select2/select2_metro.css"/>
    <!-- END PAGE LEVEL SCRIPTS -->
    <!-- BEGIN THEME STYLES -->
    <link href="static/css/style-metronic.css" rel="stylesheet" type="text/css"/>
    <link href="static/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="static/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="static/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="static/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="static/css/pages/login-soft.css" rel="stylesheet" type="text/css"/>
    <link href="static/css/custom.css" rel="stylesheet" type="text/css"/>
    <!-- END THEME STYLES -->
    <link rel="shortcut icon" href="static/app/img/favicon.ico"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="login">
<!-- BEGIN LOGO -->
<div class="logo">
</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="content">
    <!-- BEGIN LOGIN FORM -->
    <form class="login-form" action="loginProcess" method="post">
        <h3 class="form-title">用户登录</h3>
        <div class="alert alert-danger display-hide">
            <button class="close" data-close="alert"></button>
            <span>
				 输入您的用户名和密码
			</span>
        </div>
        <div class="form-group">
            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
            <label class="control-label visible-ie8 visible-ie9">用户名</label>
            <div class="input-icon">
                <i class="fa fa-user"></i>
                <input name="username" id="username" size="25" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="用户名" />
            </div>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">密码</label>
            <div class="input-icon">
                <i class="fa fa-lock"></i>
                <input name="password" id="password" size="25" value="" class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="密码" />
            </div>
        </div>
        <div class="form-actions">
            <!-- 			<label class="checkbox"> -->
            <!-- 			<input type="checkbox" name="remember" value="1"/> 记住我 </label> -->
            <button id="loginBut" type="submit" class="btn blue pull-right">
                登录 <i class="m-icon-swapright m-icon-white"></i>
            </button>
        </div>
    </form>
    <!-- END LOGIN FORM -->

</div>
<!-- END LOGIN -->
<!-- BEGIN COPYRIGHT -->
<!-- END COPYRIGHT -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="static/plugins/respond.min.js"></script>
<script src="static/plugins/excanvas.min.js"></script>
<![endif]-->
<script src="static/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="static/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="static/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="static/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="static/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="static/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="static/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="static/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="static/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
<script src="static/plugins/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
<script type="text/javascript" src="static/plugins/select2/select2.min.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="static/scripts/app.js" type="text/javascript"></script>
<script src="static/scripts/login-soft.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    jQuery(document).ready(function() {
        App.init();
        Login.init();
    });
    if (window != top)
        top.location.href = location.href;
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>