<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head> <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<form id="form1" action="update" method="post">
<div class="dialogPage">
	<div class="om-panel-header">编辑</div>
	<div class="editDiv">
		<input type="hidden" id="menuId" name="menuId" value="${sMenu.menuId}">
		<table class="editTable">
		<tr>
			<td>父菜单：</td>
			<td>${sMenuP.menuName}</td>
			<td>菜单类型：</td>
			<td><c:choose>
			         <c:when test="${sMenu.menuType==1}">菜单文件夹</c:when>
			         <c:when test="${sMenu.menuType==2}">页面菜单</c:when>
			         <c:when test="${sMenu.menuType==3}">功能按钮</c:when>
			   </c:choose>
			</td>
		</tr>
		<tr>
			<td><span class="required">*</span>菜单名字：</td>
			<td><input type="text" id="menuName" name="menuName" value="${sMenu.menuName}"></td> 
			<td>菜单状态：</td>
			<td><input id="menuStatus" name="menuStatus" type="combo"></td>
		</tr>	
		<tr>
			<td>菜单顺序：</td>
			<td colspan="3">
			   <input type="text" id="menuOrder" name="menuOrder" value="${sMenu.menuOrder}">
			</td>
		</tr>	
		<tr>
			<td>菜单URL：</td>
			<td colspan="3"><input type="text" id="menuUrl" name="menuUrl" style="width:435px;" value="${sMenu.menuUrl}"></td>
		</tr>
	   </table>
	   <div class="editBtn">
			<button id ="btnSubmit" type="button" class="button">&nbsp;保存&nbsp;</button>
			<button type="button" class="button" onclick="javascript:art.dialog.close();">&nbsp;关闭&nbsp;</button>
		</div>
	</div>
</div>
</form>
</body>
<script type="text/javascript">
$(function(){
	$('#menuName').validatebox({
		required:true,
		validType:['isExist["checkMenuName","menuName,menuId"]','maxLength[32]']  
	}); 
	$('#menuOrder').validatebox({
		validType:['number','maxLength[8]']
	});
    $('#menuStatus').combobox({  
    	data:JSON.parse('${statusCombo}'),
    	panelHeight:'auto',
    	editable:false,
    	value:'${sMenu.menuStatus}'
    }); 
	$('#btnSubmit').click(function(){
		if($('#form1').form('validate')){
			$('#form1').submit();
		}
	})
});
</script>
</html>