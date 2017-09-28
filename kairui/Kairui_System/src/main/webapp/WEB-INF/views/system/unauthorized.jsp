<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
    <title>Nothing found for 404</title>
    <style>
        body {
            background: #fff;
            margin: 0;
            padding: 0;
            text-align: center;
            font-family: "Microsoft YaHei" ! important;
            font-size: 14px;
            color: #666666;
            overflow: hidden
        }

        .error_page {
            width: 600px;
            height: 400px;
            padding: 50px;
            margin: auto;
        }

        .error_page h1 {
            margin: 20px 0 0;
        }

        .error_page p {
            margin: 10px 0;
            padding: 0;
        }

        a {
            color: #9caa6d;
            text-decoration: none;
        }

        a:hover {
            color: #9caa6d;
            text-decoration: underline;
        }

        b {
            color: red;
        }
    </style>
</head>
<body>
<div class="error_page">
    <img src='<c:url value="/static/images/401.png"/>' width="400">
    <h1>对不起</h1>
    <p>
        您<b>暂无权限</b>访问该页面。
        <a href="javascript:history.go(-1)">返回上一页</a>
    </p>
</div>
</body>
</html>