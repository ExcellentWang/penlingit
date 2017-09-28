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
		<input type="hidden" id="detailId" name="detailId" value="${sDetail.detailId}">
		<input type="hidden" id="dictId" name="dictId" value="${sDetail.dictId}">
		<table class="editTable">
		<tr>
			<td><span class="required">*</span>字段名称：</td>
			<td><input type="text" id="detailName" name="detailName" value="${sDetail.detailName}"></td>
		</tr>
		<tr>
			<td><span class="required">*</span>字段值：</td>
			<td><input type="text" id="detailValue" name="detailValue" value="${sDetail.detailValue}"></td>
			<td>字典状态：</td>
			<td>
			<select id="detailStatus" name="detailStatus">
				<option value="1" <c:if test="${1==sDetail.detailStatus}">selected="selected"</c:if>>启用</option>
				<option value="2" <c:if test="${2==sDetail.detailStatus}">selected="selected"</c:if>>禁止</option>
			</select>
			</td>
		</tr>
		<tr>
			<td>字典描述：</td>
			<td colspan="3">
			<textarea name="detailDesc" id="detailDesc" cols="58" rows="3" maxlength="256">${sDetail.detailDesc}</textarea>
			</td>
		</tr>
	   </table>
	   <div class="editBtn">
			<button id ="btnSubmit" type="button" class="button">&nbsp;保存&nbsp;</button>
			<button type="button" onclick="javascript:art.dialog.close();" class="button">&nbsp;关闭&nbsp;</button>
		</div>
	</div>
</div>
</form>
</body>
<script type="text/javascript">
$(function(){
	$('#detailName').validatebox({
		required:true,
		validType:['isExist["checkDetailName","detailName,dictId,detailId"]','maxLength[32]']  
	}); 
	$('#btnSubmit').click(function(){
		if($('#form1').form('validate')){
			$('#form1').submit();
			 /* $.ajax({
					url: 'update',
					data: $('#form1').serialize(),
					type: "POST",
					success: function(data)	{
						var win=art.dialog.open.origin; 
						win.refreshGrid('grid');
						art.dialog.close();
					}
			  }); */
		}
	})
});
</script>
</html>