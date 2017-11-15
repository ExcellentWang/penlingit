<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../taglibs.jsp" %>
<html>
<head> <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%-- <link rel="stylesheet" href="${js}/scrollbar/jquery.mCustomScrollbar.min.css">
<script src="${js}/scrollbar/jquery.mCustomScrollbar.concat.min.js"></script> --%>
</head>
<body>
<form id="form1" action="bindUser">
<input type="hidden" name="roleId" value="${roleId}"/>
<input type="hidden" name="userIds" id="userIds"/>
<div class="dialogPage">
	<div class="om-panel-header">绑定用户
		<div class="bindUser"><a href="#" onclick="doSubmit()"><span class="menu4"></span>绑定</a></div>
	</div>
	<div id="content" class="editDiv"></div>
</div>
</form>
</body>
<script type="text/javascript">
$(function(){
	var sUserRoleList = ${sUserRoleList};
	var data = ${userData};
	$('.editDiv').height(415);
	$('#content').userChoose(data,sUserRoleList);	
	/* $('#content').mCustomScrollbar({theme:"minimal-dark"}); */
});
function doSubmit(){
	var userIds="";
	$('input[name="userId"]:checked').each(function(){ 
		 userIds+=$(this).val()+','; 
	}); 
	$("#userIds").val(userIds);	
	$("#form1").submit();
}
</script>
</html>