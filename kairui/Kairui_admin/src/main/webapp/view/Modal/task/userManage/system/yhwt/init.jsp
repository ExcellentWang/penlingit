<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../taglibs.jsp" %>
<html>
<head> <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
a{text-decoration:underline;}
a:hover{text-decoration:underline;color:red;cursor:pointer}
#detailTitle{color:red}
</style>
</head>
<%

String dictId = request.getParameter("dictId");
request.setAttribute("dictId", dictId);

%>
<body>
<div id="toolbar">

<div class="operate">
	<div class="om-panel-header">常见问题管理</div>
	<div class="icon">
		<ul>
	   		<li><a href="#" onclick="showDetailAdd();"><span class="menu1"></span>添加</a></li>
	   		<li><a href="#" onclick="showEdit('/system/detail/showEditYhwt','detailId',600,260);"><span class="menu13"></span>修改</a></li>
	   		<li><a href="#" onclick="removeRow('detailId','/Kairui_admin/system/detail/delete');"><span class="menu11"></span>删除</a></li>
		</ul>
	</div>
</div>
</div>
<table id="grid" data-options="fit:true,border:false"></table>
</body>
<script type="text/javascript">
$(function() {
	$('#grid').datagrid({   
	    url:'${ctx}/system/detail/list?dictId=6', 
	    pageSize :10,
		pageList : [10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000 ],
		striped : true,
		rownumbers : true,
		pagination : true,
		toolbar : '#toolbar',
	    columns : [[ {width : '50', field : '序号',checkbox:true},
	                 {width : '150',title : '问题',field : 'detailName'},
	                 {width : '150',title : '问题编号',field : 'detailValue'},
	                 {width : '250',title : '问题答案',field : 'detailDesc'},
	                 {width : '100',title : '状态',field : 'detailStatus',formatter:function(v,r){return JSON.parse('${statusMap}')[v]}},
	                 {width : '150',title : '发布日期',field : 'updateTime'}	                
	    		  ]]
	}); 
});
function showDetailAdd(){
	$.dialog.open(ctx+'/system/detail/showAddYhwt?dictId=+${dictId}', {
		lock: true,
		width:600,
		height:230
	});
} 


</script>
</html>