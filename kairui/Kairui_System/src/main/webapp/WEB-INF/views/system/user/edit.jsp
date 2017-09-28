<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head> <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<form id="form1" action="update">
<div class="dialogPage">
	<div class="om-panel-header">编辑</div>
	<div class="editDiv">
		<input type="hidden" id="userId" name="userId" value="${sUser.userId}">
		<table class="editTable">
		<tr>
			<td><span class="required">*</span>登录名：</td>
			<td><input type="text" id="userName" name="userName" value="${sUser.userName}"></td>
			<td>真实姓名：</td>
			<td><input type="text" id="realName" name="realName" value="${sUser.realName}"></td>
		</tr>
		<tr>
			<td>手机号：</td>
			<td><input type="text" id="mobile" name="mobile" value="${sUser.mobile}"></td>
			<td>邮箱地址：</td>
			<td><input type="text" id="email" name="email" value="${sUser.email}"></td>
		</tr>
		<tr>
			<td>姓别：</td>
			<td><input id="sex" name="sex" type="combo"></td> 
			<td>用户状态：</td>
			<td><input id="userStatus" name="userStatus" type="combo"></td> 
		</tr>
		<tr>
			<td>排序：</td>
			<td><input type="text" id="userOrder" name="userOrder" value="${sUser.userOrder}"></td>
		</tr>
	   </table>
	   <div class="editBtn">
			<button id ="btnSubmit" type="button" class="button" >&nbsp;保存&nbsp;</button>
			<button type="button" class="button" onclick="javascript:art.dialog.close();">&nbsp;关闭&nbsp;</button>
		</div>
	</div>
</div>
</form>
</body>
<script type="text/javascript">
$(function(){
	//验证长度不超过32位，不能重命，不能为空
	$('#userName').validatebox({
		required:true,
		validType:['isExist["checkUserName","userName,userId"]','maxLength[32]']  
	}); 
	$('#realName').validatebox({required: true}); 
	$('#userOrder').validatebox({
		validType:['number','maxLength[8]']
	});
	$('#email').validatebox({validType:'email'});
	$('#mobile').validatebox({validType:'mobile'});
    $('#sex').combobox({  
    	data:JSON.parse('${sexCombo}'),
    	panelHeight:'auto',
    	value:'${sUser.sex}'
    }); 
    $('#userStatus').combobox({  
    	data:JSON.parse('${statusCombo}'),
    	panelHeight:'auto',
    	editable:false,
    	value:'${sUser.userStatus}'
    }); 
	$('#btnSubmit').click(function(){
		if($('#form1').form('validate')){
			$('#form1').submit();
		}
	})
});
</script>
</html>