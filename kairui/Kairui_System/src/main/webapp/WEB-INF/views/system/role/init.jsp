<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head> <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<div id="toolbar">
<form id="list" action="list">
<div id="search" class="easyui-panel" title="查询条件" data-options="fit:true,collapsible:true,border:0"> 
  <table class="searchTable">
		<tr>
			<td>角色名称：</td>
			<td><input type="text" id="roleName" name="roleName"></td>
			<td>修改时间：</td>
			<td><input id="updateTimeStart" name="updateTimeStart" type="datetime" class="easyui-datebox"></input>
				至 <input id="updateTimeEnd" name="updateTimeEnd" type="datetime" class="easyui-datebox"/>
			<td><button id="queryBtn" type="button" class="button">查询</button></td>
			<td><button id="clearBtn" type="button" class="button">清空</button></td>
		</tr>
	   </table>
</div>
</form>
<div class="operate">
	<div class="om-panel-header">角色管理列表</div>
	<div class="icon">
		<ul>
    		<li><a href="#" onclick="showAdd('/system/prg/role/showAdd',600,240);"><span class="menu1"></span>添加</a></li>
    		<li><a href="#" onclick="showEdit('/system/prg/role/showEdit','roleId',600,270);"><span class="menu13"></span>修改</a></li>
    		<li><a href="#" onclick="removeRow('roleId');"><span class="menu11"></span>删除</a></li>
    		<li><a href="#" onclick="bindMenu()"><span class="menu4"></span>绑定权限</a></li>
    		<li><a href="#" onclick="bindUser()"><span class="menu4"></span>绑定用户</a></li>
		</ul>
	</div>
</div>
</div>
<table id="grid" data-options="fit:true,border:false"></table>
</body>
<script type="text/javascript">
$(function() {      
	$('#grid').datagrid({   
	    url:'list', 
	    pageSize :10,
		pageList : [10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000 ],
		striped : true,
		//rownumbers : true,
		pagination : true,
		toolbar : '#toolbar',
	    columns : [[ {width : '150', field : 'ck',checkbox:true},
	                 {width : '200',title : '角色名称',field : 'roleName'},
	                 {width : '200',title : '角色描述',field : 'roleDesc'},
	                 {width : '80',title : '状态',field : 'roleStatus',formatter:function(v,r){return JSON.parse('${statusMap}')[v]}},
	                 {width : '50',title : '排序',field : 'roleOrder'},
	                 {width : '150',title : '创建时间',field : 'createTime'},
					 {width : '150',title : '修改时间',field : 'updateTime'}]
	    		]
	}); 
});
function bindMenu(){
	menuTree = true;
	var selections = $('#grid').datagrid('getSelections');
	if (selections.length != 1) {		
		$.messager.alert('提示:','请选择一行记录!'); 
		return false;
	}
	var id = selections[0].roleId;
	$.dialog.open(ctx+'system/prg/role/menuTree?roleId='+id, {
		lock: true,
		width:240,
		height:500
	});
}
function bindUser(){
	var selections = $('#grid').datagrid('getSelections');
	if (selections.length != 1) {		
		$.messager.alert('提示:','请选择一行记录!'); 
		return false;
	}
	var id = selections[0].roleId;
	$.dialog.open(ctx+'system/prg/role/userDialog?roleId='+id, {
		lock: true,
		width:820,
		height:460
	});
}
</script>
</html>