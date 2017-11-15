<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../taglibs.jsp" %>
<html>
<head> <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<form id="form1" action="insert" method="post">
<div class="dialogPage">
	<div class="om-panel-header">新增</div>
	<div class="editDiv">
		<input type="hidden" id="menuPid" name="menuPid" value="${sMenuP.menuId}">
		<input type="hidden" id="menuLevel" name="menuLevel" value="${sMenuP.menuLevel}">
		<input type="hidden" id="parentMenuType" name="parentMenuType" value="${sMenuP.menuType}">
		<table class="editTable">
		<tr>
			<td>父菜单：</td>
			<td>${sMenuP.menuName}</td>
			<td><span class="required">*</span>菜单类型：</td>
			<td>
			   <input id="menuType" name="menuType" type="combo"  />
			</td>
		</tr>		
		<tr>
			<td><span class="required">*</span>菜单名字：</td>
			<td><input type="text" id="menuName" name="menuName"  > </td>
			<td>菜单排序：</td>
			<td><input type="text" id="menuOrder" name="menuOrder"></td>
			
		</tr>
		<tr>
			<td>菜单URL：</td>
			<td colspan="3"><input type="text" id="menuUrl" name="menuUrl" style="width:435px;"></td>
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
		validType:['isExist["checkMenuName","menuName"]','maxLength[32]']  
	}); 
	$('#menuOrder').validatebox({
		validType:['number','maxLength[8]']
	});
	var pMenuLevel=$("#menuLevel").val();
	var pMenuType=$("#parentMenuType").val();
	var defaultMenuType='2';
	if(pMenuLevel=='0' || pMenuLevel==0){
		defaultMenuType='1';
	}
	else if(pMenuType=='1' && pMenuLevel>=1){
		defaultMenuType='2';
	}
	else if(pMenuType=='1' && pMenuLevel>=1){
		defaultMenuType='2';
	}
	else if(pMenuType=='2'){
		defaultMenuType='3';
	}
	$('#menuType').combobox({  
    	data:JSON.parse('${menuTypeCombo}'), 
    	panelHeight:'auto',
    	editable:false,
    	value:defaultMenuType,
    	onSelect:function(record){    		
    		var menuType=record.value;
    		if(pMenuLevel=='0' && (menuType=='2' || menuType=='3') ){ //不让在根节点下添加页面或按钮 
    			$("#menuType").combobox("setValue", '1');
    		}
    		else if(pMenuLevel>='1' && pMenuType=='1' && menuType=='3'){    //不让在文件夹下添加按钮 
    			$("#menuType").combobox("setValue", '2');
    		}    		
    		else if(pMenuType=='1' && pMenuLevel=='2' && menuType=='1'){ //不让在二级文件夹下添加三级文件夹（否则页面就成了四级菜单）
    			$("#menuType").combobox("setValue", '2');
    		}	
    		else if(pMenuType=='2' &&  menuType!='3'){  //不让在页面下添加文件夹或页面，只让添加按钮 
    			$("#menuType").combobox("setValue", '3');
    		}
    	}
    }); 
	$('#btnSubmit').click(function(){
		if($('#form1').form('validate')){			
			$('#form1').submit();
			return true;
		}
	})
});

</script>
</html>