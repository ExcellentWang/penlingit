
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set  var="ctx"  value="<%= request.getContextPath() %>" />
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>在路上APP管理后台</title>
    <script type="text/javascript" src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
 
 </head>
<body>

<form id="form1" method="post">
    <div class="content">
        <div class="login-div">
            <h1 class="log_img"></h1>
            <h4 class="log_title">测试</h4>
            <div class="form_ipt">
                <p><i class="user"></i><input type="text" placeholder="请输入用户名" id="username" name="username"></p>
                <p><i class="user"></i><input type="text" placeholder="请输入性别" id="sex" name="sex"></p>
                <p><i class="user"></i><input type="text" placeholder="请输入头像" id="headPortrait" name="headPortrait"></p>
                <p><i class="pwd"></i><input type="password" placeholder="请输入密码" id="password" name="password"></p>
                <p><i class="user"></i><input type="text" placeholder="请输入手机" id="phone" name="phone"></p>
                <p><i class="user"></i><input type="text" placeholder="用户id" id="user_id" name="user_id"></p>
                 <p><i class="user"></i><input type="text" placeholder="旧密码" id="oldPassword" name="oldPassword"></p> 
                <p><i class="user"></i><input type="text" placeholder="新密码" id="newPassword" name="newPassword"></p>
              <p><i class="user"></i><input type="text" placeholder="新手机号" id="newPhone" name="newPhone"></p>
            </div>
            <input type="button" value="注册" id="button1" />
            <input type="button" value="登录" id="button2" />
            <input type="button" value="获取设备列表" id="button3" />
             <input type="button" value="修改密码" id="button4" />
             <input type="button" value="修改手机号" id="button5" />
             <input type="button" value="修改用户资料" id="button6" />
             <input type="button" value="获取售后列表" id="button7" />
             <input type="button" value="申请售后" id="button8" />
        </div>
    </div>
</form>
<script>
    $(function () {
    	//alert("${ctx}");
        $('#button1').click(function () {  
        	var params ={
        			phone:$("#phone").val(),
        			password:$("#password").val()
    		};	
        	
        	
        	
        	
            $.ajax({
        		url:'${ctx}/app/user/appUserRegister',
        		type : "post",
        		dataType:'json',
        	//	data : $("#form1").serialize(),
        		data:params,
        		async: true,
        		success:function(data){
        			console.log(data);
        		},
        		error:function(req,err,ecp){
        		}
        	});       
        });
        
        $('#button2').click(function () {  
            $.ajax({
        		url:'${ctx}/app/user/appUserLogin',
        		type : "post",
        		dataType:'json',
        		data : $("#form1").serialize(),
        		async: true,
        		success:function(data){
        			console.log(data);
        		},
        		error:function(req,err,ecp){
        		}
        	});       
        });
        
        
        $('#button3').click(function () {  
            $.ajax({
        		url:'${ctx}/device/deviceList',
        		type : "post",
        		dataType:'json',
        		data : $("#form1").serialize(),
        		async: true,
        		success:function(data){
        			console.log(data);
        		},
        		error:function(req,err,ecp){
        		}
        	});       
        });
        
        $('#button4').click(function () {  
            $.ajax({
        		url:'${ctx}/app/user/appUserUpdatePassword',
        		type : "post",
        		dataType:'json',
        		data : $("#form1").serialize(),
        		async: true,
        		success:function(data){
        			console.log(data);
        		},
        		error:function(req,err,ecp){
        		}
        	});       
        });
        
        $('#button5').click(function () {  
            $.ajax({
        		url:'${ctx}/app/user/appUserUpdatePhone',
        		type : "post",
        		dataType:'json',
        		data : $("#form1").serialize(),
        		async: true,
        		success:function(data){
        			console.log(data);
        		},
        		error:function(req,err,ecp){
        		}
        	});       
        });
        
        
        
        $('#button6').click(function () {  
            $.ajax({
        		url:'${ctx}/app/user/appUserUpdateData',
        		type : "post",
        		dataType:'json',
        		data : $("#form1").serialize(),
        		async: true,
        		success:function(data){
        			console.log(data);
        		},
        		error:function(req,err,ecp){
        		}
        	});       
        });
        
        
        $('#button7').click(function () {  
            $.ajax({
        		url:'${ctx}/app/user/getCustomerList',
        		type : "post",
        		dataType:'json',
        		data : $("#form1").serialize(),
        		async: true,
        		success:function(data){
        			console.log(data);
        		},
        		error:function(req,err,ecp){
        		}
        	});       
        });
        
        
        $('#button8').click(function () {  
            $.ajax({
        		url:'${ctx}/app/user/applyCustomer',
        		type : "post",
        		dataType:'json',
        		data : $("#form1").serialize(),
        		async: true,
        		success:function(data){
        			console.log(data);
        		},
        		error:function(req,err,ecp){
        		}
        	});       
        });
        
    });
</script>
</body>
</html>