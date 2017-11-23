<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="./taglibs.jsp" %>
<html>
<head> <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="../../../../common/plugs/bootstrap-3.3.5/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../../../common/css/style.min.css"/>
    <link rel="stylesheet" href="../../../../common/plugs/layer/skin/layer.css?rev=433a1dd8df8c4851be764e907f6d758a"/>
    <link rel="stylesheet" href="../../../../common/plugs/bootstrap-table/dist/bootstrap-table.min.css">
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
	if(confirmPwd!=newPwd){$('#errorMessage').html('两次输入密码不一致！');return}
	$.post(ctx+"/user/updateUserPwd", {
		"password":password,
		"newPwd":newPwd
	},
	   function(data){
			if(data.code==0){
				$.ajax({
					url: "/user/logOut",
					success: function(data, textStatus, jqXHR) {
						tip({
							content:"操作成功，请注销后重新登录！"
						})
					}
				  });
			}else{
				tip({
					content:data.msg
				})
			}
	   }, "json");
}
</script>
</body>
<script src="../../../../common/js/jquery-2.1.1.min.js?rev=6631a779321bc03f4a5281d3ff526254"></script>
<script src="../../../../common/plugs/bootstrap-3.3.5/js/bootstrap.min.js?rev=4becdc9104623e891fbb9d38bba01be4"></script>
<script src="../../../../common/plugs/layer/layer.js?rev=8962f047eeb03c06cf8200de1bf8ab99"></script>
<script src="../../../../common/js/mock.js"></script>
<script src="../../../../common/js/jquery.values.js"></script>
<script src="../../../../common/plugs/bootstrap-table/dist/bootstrap-table.min.js"></script>
<script src="../../../../common/plugs/bootstrap-table/dist/extensions/cookie/bootstrap-table-cookie.min.js"></script>
<script src="../../../../common/plugs/bootstrap-table/dist/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="../../../../common/plugs/bootstrap-datepicker/bootstrap-datetimepicker.min.js"></script>
<script src="../../../../common/plugs/bootstrap-datepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" charset="utf-8">
  $("head").append([
    '<script src="../../../../common/js/URL.js"><\/script>',
    '<script src="../../../../common/js/iframeCommon.js"><\/script>',
    '<script src="../../../../js/guarantee/guaranteetype/index.js"><\/script>'
  ].join(""));
</script>
</html>