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
			<td>访问人：</td>
			<td><input type="text" id="userName" name="userName"></td>
			<td>访问IP：</td>
			<td><input type="text" id="userIp" name="userIp"></td>
			<td><button id="queryBtn" type="button" class="button">查询</button></td>
			<td><button id="clearBtn" type="button" class="button">清空</button></td>
		</tr>
	   </table>
	</div>
	</form>
	<div class="operate">
		<div class="om-panel-header">系统日志管理列表</div>
		<div class="icon">
			<ul>
				<li><a href="#" onclick="exportExcel();"><span class="menu2"></span>导出</a></li>
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
		rownumbers : true,
		pagination : true,
		singleSelect : true,
		toolbar : '#toolbar',
	    columns : [[{title : '访问人',field:'userName',width:120}, 
                    {title : '访问IP', field : 'userIp',width:150}, 
                    {title : '访问时间', field : 'logTime',width:150} , 
                    {title : '耗时', field : 'processTime',width:100} ,
                    {title : '访问URL',field:'actionUrl',width:300},
                    {title : '描述',field:'logDesc',width:600}
                   ]
	    		]
	}); 
});
function exportExcel(){
	window.location.href = ctx + 'system/prg/log/exportExcel';
}

</script>
</html>