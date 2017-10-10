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
		<input type="hidden" id="dictId" name="dictId" value="${sDict.dictId}">
		<table class="editTable">
		<tr>
			<%-- <td><span class="required">*</span>协议名称：</td>
			<td><input type="text" id="dictName" name="dictName" value="${sDict.dictName}"></td> --%>
			<td>协议状态：</td>
			<td>
			<select id="dictStatus" name="dictStatus">
				<c:forEach items="${statusMap}" var="v">
					<option value="${v.key}" <c:if test="${v.key==sDict.dictStatus}">selected="selected"</c:if>>${v.value}</option>
				</c:forEach>
			</select>
			</td>
		</tr>
		<tr>
			<td>协议描述：</td>
			<td colspan="3">
			<textarea name="dictDesc" id="dictDesc" cols="58" rows="3" maxlength="256">${sDict.dictDesc}</textarea>
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
	$('#dictName').validatebox({
		required:true,
		validType:['isExist["checkDictName","dictName,dictId"]','maxLength[32]']  
	}); 
	$('#btnSubmit').click(function(){
		if($('#form1').form('validate')){
			$('#form1').submit();
		}
	})
});
</script>
</html>