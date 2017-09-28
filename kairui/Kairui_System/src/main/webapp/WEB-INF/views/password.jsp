<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head> <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
#errorMessage{position:absolute;left:0;right:0;margin:0 auto;bottom:60px;color:red;text-align:center;width:100%;}
</style>
</head>
<body>
<form id="form1" action="update">
<div class="dialogPage">
	<div class="om-panel-header">密码修改</div>
	<div class="editDiv">
		<table class="editTable">
		<tr>
			<td>原始密码：</td>
			<td><input type="password" id="password" name="password"></td>
		</tr>
		<tr>			
			<td>新密码：</td>
			<td><input type="password" id="newPwd" name="newPwd"></td>
		</tr>
		<tr>
			<td>确认密码：</td>
			<td><input type="password" id="confirmPwd" name="confirmPwd"></td>
		</tr>
	   </table>
	   <div id="errorMessage"></div>
	   <div class="editBtn">
			<button type="button" class="button" onclick="doSubmit()">&nbsp;保存&nbsp;</button>
			<button type="button" class="button" onclick="javascript:art.dialog.close();">&nbsp;关闭&nbsp;</button>
		</div>
	</div>
</div>
</form>
<script>
function doSubmit(){
	var password = $('#password').val();
	var newPwd = $('#newPwd').val();
	var confirmPwd = $('#confirmPwd').val();
	if(password==''){$('#errorMessage').html('原始密码不能为空！');return}
	$.post(ctx+"/index/checkUserPwd", {"password":password},
	   function(data){
			if(data.success){
				$('#errorMessage').html(data.msg);
			}else{
				if(newPwd==''){$('#errorMessage').html('新密码不能为空！');return}
				if(confirmPwd==''){$('#errorMessage').html('确认密码不能为空！');return}
				if(newPwd!=confirmPwd){$('#errorMessage').html('两次输入的密码不一致！');return}
				$('#form1').submit();
			}
	   }, "json");
}
</script>
</body>
</html>