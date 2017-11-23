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
		<input type="hidden" id="dictId" name="dictId" value="${dictId}">
		<table class="editTable">
		<tr>
			<td>问题编号：</td>
			<td><input type="text" id="detailValue" name="detailValue"></td>
		</tr>
		<tr>
			<td><span class="required">*</span>问题名称：</td>
			<!-- <td><input type="text" id="detailName" name="detailName"></td> -->
			<td colspan="3">
			<textarea name="detailName" id="detailName" cols="58" rows="3" maxlength="256"></textarea>
			</td>
		</tr>
		<tr>
			<td>问题答案：</td>
			<td colspan="3">
			<textarea name="detailDesc" id="detailDesc" cols="58" rows="3" maxlength="256"></textarea>
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
		validType:['isExist["checkDetailName","detailName,dictId"]']  
	}); 
	$('#btnSubmit').click(function(){
		if($('#form1').form('validate')){
			 $.ajax({
					url: 'insert',
					data: $('#form1').serialize(),
					type: "POST",
					success: function(data)	{
						var win=art.dialog.open.origin; 
						win.refreshGrid('grid');
						art.dialog.close();
					}
			  });
		}
	})
});
</script>
</html>