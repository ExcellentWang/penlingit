<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../taglibs.jsp" %>
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
    		<li><a href="#" onclick="showAdd('/system/role/showAdd',600,240);"><span class="menu1"></span>添加</a></li>
    		<li><a href="#" onclick="showEdit('/system/role/showEdit','roleId',600,270);"><span class="menu13"></span>修改</a></li>
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
var jsonStr="";

$.ajax({
	type : "post",
	dataType : "text",
	async : false,
	url : '/Kairui_admin/system/role/dictStatusMap',
	data : {
	},
	success : function(msg1) {
		jsonStr = msg1;
	}
});

$(function() {      
	$('#grid').datagrid({   
	    url:'/Kairui_admin/system/role/list', 
	    pageSize :10,
		pageList : [ 10, 30, 40, 50, 100, 200,   500, 1000 ],
		striped : true,
		//rownumbers : true,
		pagination : true,
		toolbar : '#toolbar',
	    columns : [[ {width : '150', field : 'ck',checkbox:true},
	                 {width : '100',title : '角色名',field : 'roleName'},
	                 {width : '100',title : '地区',field : 'province',formatter:function(v,r){ return JSON.parse(JSON.parse(jsonStr)["districtMap"])[v]}},
	                 {width : '100',title : '备注',field : 'bz'},
	                 {width : '150',title : '角色描述',field : 'roleDesc'},
	                 {width : '80',title : '状态',field : 'roleStatus',formatter:function(v,r){return JSON.parse(JSON.parse(jsonStr)["statusMap"])[v]}},
/* 	                 {width : '50',title : '排序',field : 'roleOrder'}, */
	                 {width : '150',title : '创建时间',field : 'createTime'},
	                 {width : '200',title : '操作',field : 'opt',formatter:function(v,r){ var roleId = r.roleId;var roleStatus = r.roleStatus; return '<a href="javascript:showEdit(\'/system/role/showEdit\',\'roleId\',600,270);" >编辑</a>'+'&nbsp&nbsp'+'<a href="javascript:bindMenu();" >分配权限</a>'+'&nbsp&nbsp'+'<a href="javascript:disableRole('+roleId+','+roleStatus+');" >角色停用</a>'+'&nbsp&nbsp'+'<a href="javascript:removeRow(\'roleId\',\'/Kairui_admin/system/role/delete\');" >删除</a>'}}
					]
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
	$.dialog.open(ctx+'/system/role/menuTree?roleId='+id, {
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
	$.dialog.open(ctx+'/system/role/userDialog?roleId='+id, {
		lock: true,
		width:820,
		height:460
	});
}

//角色停用
function disableRole(roleId,roleStatus){
	$.ajax({
			type : "post",
			dataType : "text",
			url : '/Kairui_admin/system/role/update',
			data : {
				roleId : roleId,
				roleStatus : '2'
			},
			success : function(msg1) {
				//refreshGrid();
				$("#grid").datagrid('reload');
				refleshTree&&($("#navtree").tree('reload'));//刷新菜单树，默认false不删除，只有菜单功能会刷新
			}
		})
	}
</script>
</html>