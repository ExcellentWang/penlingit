<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../taglibs.jsp" %>
<html>
<head> <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="../../../../../common/plugs/bootstrap-datepicker/css/bootstrap-datetimepicker.min.css" />
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
      <div class="col-xs-24 col-sm-24 col-md-24">
        <div class="ibox float-e-margins">
          <div class="ibox-content">
            <form class="form-horizontal" id="list" action="list">
              <div class="form-group form-group-sm">
                
              </div>
              <div class="form-group form-group-sm">
                <div class="input-tip">
                  <label class="col-xs-3 col-sm-3 col-md-3 control-label">角色名称：</label>
                 <div class="col-xs-5 col-sm-5 col-md-5">
                    <input type="text" id="roleName" name="roleName" placeholder="" class="form-control">
                  </div>
                </div>
                <div class="input-tip">
                  <label class="col-xs-3 col-sm-3 col-md-3 control-label">创建时间：</label>
                  <div class="col-xs-5 col-sm-5 col-md-5">
                    <input  name="updateTimeStart" type="text" placeholder="" class="form-control date"/>
                  </div>
                </div>
              </div>
              <div class="form-group form-group-sm">
               <div class="input-tip">
                 <label class="col-xs-3 col-sm-3 col-md-3 control-label">结束时间：</label>
                  <div class="col-xs-5 col-sm-5 col-md-5">
					 <input  name="updateTimeEnd"  type="text" placeholder="" class="form-control date"/>
                  </div>
                 </div>
                <div class="col-xs-24 col-sm-24 col-md-24 text-center">
                  <button type="button" class="btn btn-primary" id="queryBtn" modal="enter"><span class="glyphicon glyphicon-search"></span><span>&nbsp;查询&nbsp;</span>
                  </button>
                  <button type="button"  id="clearBtn" class="btn btn-white" modal="reset"><span class="glyphicon glyphicon-remove"></span><span>&nbsp;清除查询条件&nbsp;</span>
                  </button>
                </div>
              </div>
            </form>
          </div>
        </div>
         <div class="ibox-content">
        <div class="operate">
	<div class="om-panel-header"></div>
	<div class="icon">
		<ul>
    		<li><a href="#" onclick="showAdd('/system/role/showAdd',600,240);"><span class="menu1"></span>添加</a></li>
    		<li><a href="#" onclick="showEdit('/system/role/showEdit','roleId',600,270);"><span class="menu13"></span>修改</a></li>
    		<li><a href="#" onclick="removeRow('roleId','/system/role/delete');"><span class="menu11"></span>删除</a></li>
    		<li><a href="#" onclick="bindMenu()"><span class="menu4"></span>绑定权限</a></li>
    		<li><a href="#" onclick="bindUser()"><span class="menu4"></span>绑定用户</a></li>
		</ul>
	</div>
</div>
</div>
                <table id="grid" data-options=""></table>
      </div>
    </div>
  </div>
</body>
<script src="../../../../../common/plugs/bootstrap-datepicker/bootstrap-datetimepicker.min.js"></script>
<script src="../../../../../common/plugs/bootstrap-datepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
var jsonStr="";

$.ajax({
	type : "post",
	dataType : "text",
	async : false,
	url : '/system/role/dictStatusMap',
	data : {
	},
	success : function(msg1) {
		jsonStr = msg1;
	}
});

$(function() {      
	$('#grid').datagrid({   
	    url:'/system/role/list', 
	    pageSize :10,
		pageList : [ 10, 30, 40, 50, 100, 200,   500, 1000 ],
		striped : true,
		//rownumbers : true,
		pagination : true,
		toolbar : '#toolbar',
	    columns : [[ {width : '150', field : 'ck',checkbox:true},
	                 {width : '100',title : '角色名',field : 'roleName'},
	               /*   {width : '100',title : '地区',field : 'province',formatter:function(v,r){ return JSON.parse(JSON.parse(jsonStr)["districtMap"])[v]}},
	                 {width : '100',title : '备注',field : 'bz'}, */ 
	                 {width : '150',title : '角色描述',field : 'roleDesc'},
	                 {width : '80',title : '状态',field : 'roleStatus',formatter:function(v,r){return JSON.parse(JSON.parse(jsonStr)["statusMap"])[v]}},
/* 	                 {width : '50',title : '排序',field : 'roleOrder'}, */
	                 {width : '150',title : '创建时间',field : 'createTime'},
	                 /* {width : '200',title : '操作',field : 'opt',formatter:function(v,r){ var roleId = r.roleId;var roleStatus = r.roleStatus; return '<a href="javascript:showEdit(\'/system/role/showEdit\',\'roleId\',600,270);" >编辑</a>'+'&nbsp&nbsp'+'<a href="javascript:bindMenu();" >分配权限</a>'+'&nbsp&nbsp'+'<a href="javascript:disableRole('+roleId+','+roleStatus+');" >角色停用</a>'+'&nbsp&nbsp'+'<a href="javascript:removeRow(\'roleId\',\'/system/role/delete\');" >删除</a>'}}  */
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
			url : '/system/role/update',
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