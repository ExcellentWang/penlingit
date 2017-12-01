<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../taglibs.jsp" %>
<html>
<head> <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<form id="form1" action="insert">
<div class="dialogPage">
	<div class="om-panel-header">新增</div>
	<div class="editDiv">
		<table class="editTable">
		<tr>
			<td><span class="required">*</span>登录名：</td>
			<td><input type="text" id="userName" name="userName"></td>
			<td><span class="required">*</span>初始密码：</td>
			<td><input type="text" id="userPwd" name="userPwd" value="${initPassword}" readonly="readonly"></td>
		</tr>
		<tr>
			<td><span class="required">*</span>真实姓名：</td>
			<td><input type="text" id="realName" name="realName"></td>
			<td>手机号：</td>
			<td><input type="text" id="mobile" name="mobile"></td>
		</tr>
		<tr>
			<td>邮箱地址：</td>
			<td><input type="text" id="email" name="email"></td>
			<td>姓别：</td>
			<td><input id="sex" name="sex" type="combo"></td> 
		</tr>
		<!-- <tr>
			<td>排序：</td>
			<td><input type="text" id="userOrder" name="userOrder"></td>
		</tr> -->
	   </table>
	   <div class="editBtn">
			<button type="submit" class="button">&nbsp;保存&nbsp;</button>
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
		validType:['isExist["checkUserName","userName","用名户已经存在,请重新输入"]','maxLength[32]']
	}); 
	$('#realName').validatebox({required:true}); 
	$('#userOrder').validatebox({
		validType:['number','maxLength[8]']
	});
	$('#email').validatebox({
		validType:'email',
		invalidMessage:'输入的邮箱格式不对'
	});
	$('#mobile').validatebox({validType:'mobile'});
    $('#sex').combobox({  
    	data:JSON.parse('${sexCombo}'),
    	panelHeight:'auto',
    	editable:false,
    	value:0
    }); 
	$(":submit").click(function(){
		if(!$("#form1").form('validate')){return false;}
	});
});
</script>
</html>