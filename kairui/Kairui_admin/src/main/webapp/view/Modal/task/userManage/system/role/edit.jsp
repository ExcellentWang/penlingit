<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../taglibs.jsp" %>
<html>
<head> <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<form id="form1" action="update">
<div class="dialogPage">
	<div class="om-panel-header">编辑</div>
	<div class="editDiv">
		<input type="hidden" id="roleId" name="roleId" value="${sRole.roleId}">
		<table class="editTable">
		<tr>
			<td><span class="required">*</span>角色名称：</td>
			<td><input type="text" id="roleName" name="roleName" value="${sRole.roleName}"></td>
		</tr>
		<tr>
			<td>角色状态：</td>
			<td><input id="roleStatus" name="roleStatus" type="combo"></td> 
			<td>角色顺序：</td>
			<td><input type="text" id="roleOrder" name="roleOrder" value="${sRole.roleOrder}"></td>
		</tr>
		<tr>
			<!-- <td>角色地区：</td>
			<td><input id="province" name="province" type="combo"></td>  -->
			<td>备注：</td>
			<td><input type="text" id="bz" name="bz" value="${sRole.bz}"></td>
		</tr>
		<tr>
			<td>角色描述：</td>
			<td colspan="3">
			<textarea name="roleDesc" id="roleDesc" cols="58" rows="3" maxlength="256">${sRole.roleDesc}</textarea>
			</td>
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
	$('#roleName').validatebox({
		required:true,
		validType:['isExist["checkRoleName","roleName,roleId"]','maxLength[32]']  
	}); 
	$('#roleOrder').validatebox({
		validType:['number','maxLength[180]']
	});
    $('#roleStatus').combobox({  
    	data:JSON.parse('${statusCombo}'),
    	panelHeight:'auto',
    	editable:false,
    	value:'${sRole.roleStatus}'
    }); 
    $('#province').combobox({  
    	data:JSON.parse('${districtMapCombo}'),
    	panelHeight:'auto',
    	editable:false,
    	value:'${sRole.province}'
    }); 
    
	$('#btnSubmit').click(function(){
		if($('#form1').form('validate')){
			$('#form1').submit();
		}
	})
});
</script>
</html>