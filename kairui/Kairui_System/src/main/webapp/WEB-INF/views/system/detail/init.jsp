<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head> <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
a{text-decoration:underline;}
a:hover{text-decoration:underline;color:red;cursor:pointer}
#detailTitle{color:red}
</style>
</head>
<body>
<div id="toolbar">
<form id="list" action="list">
<div id="search" class="easyui-panel" title="查询条件" data-options="fit:true,collapsible:true,border:0"> 
  <input type="hidden" id="dictId" name="dictId" value="${dictId}">
  <table class="searchTable">
	<tr>
		<td>字段名称：</td>
		<td><input type="text" id="detailName" name="detailName"></td>
		<td><button id="queryBtn" type="button" class="button">查询</button></td>
		<td><button id="clearBtn" type="button" class="button">清空</button></td>
	</tr>
   </table>
</div>
</form>
<div class="operate">
	<div class="om-panel-header">字典管理列表</div>
	<div class="icon">
		<ul>
	   		<li><a href="#" onclick="showDetailAdd();"><span class="menu1"></span>添加</a></li>
	   		<li><a href="#" onclick="showEdit('system/prg/detail/showEdit','detailId',600,260);"><span class="menu13"></span>修改</a></li>
	   		<li><a href="#" onclick="removeRow('detailId');"><span class="menu11"></span>删除</a></li>
	   		<li><a href="${ctx}system/prg/dict/init"><span class="menu6"></span>返回</a></li>
		</ul>
	</div>
</div>
</div>
<table id="grid" data-options="fit:true,border:false"></table>
</body>
<script type="text/javascript">
$(function() {
	$('#grid').datagrid({   
	    url:'${ctx}system/prg/detail/list?dictId=${dictId}', 
	    pageSize :10,
		pageList : [10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000 ],
		striped : true,
		rownumbers : true,
		pagination : true,
		toolbar : '#toolbar',
	    columns : [[ {width : '50', field : 'ck',checkbox:true},
	                 {width : '150',title : '字段名称',field : 'detailName'},
	                 {width : '150',title : '字段值',field : 'detailValue'},
	                 {width : '250',title : '字段描述',field : 'detailDesc'},
	                 {width : '100',title : '状态',field : 'detailStatus',formatter:function(v,r){return JSON.parse('${statusMap}')[v]}},
	                 {width : '150',title : '修改时间',field : 'updateTime'}	                
	    		  ]]
	}); 
});
function showDetailAdd(){
	$.dialog.open(ctx+'system/prg/detail/showAdd?dictId=+${dictId}', {
		lock: true,
		width:600,
		height:230
	});
} 


</script>
</html>