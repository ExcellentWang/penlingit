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
                <div class="input-tip">
                  <label class="col-xs-3 col-sm-3 col-md-3 control-label">系统登录名：</label>
                  <div class="col-xs-5 col-sm-5 col-md-5">
                    <input type="text" id="userName" name="userName" placeholder="" class="form-control">
                  </div>
                </div>
                <div class="input-tip">
                  <label class="col-xs-3 col-sm-3 col-md-3 control-label">真实姓名：</label>
                  <div class="col-xs-5 col-sm-5 col-md-5">
                    <input type="text" id="realName" name="realName" placeholder="" class="form-control">
                  </div>
                </div>
              </div>
              <div class="form-group form-group-sm">
                <div class="input-tip">
                  <label class="col-xs-3 col-sm-3 col-md-3 control-label">创建时间：</label>
                  <div class="col-xs-5 col-sm-5 col-md-5">
                    <input id="createTimeStart" name="createTimeStart" type="text" placeholder="" class="form-control date"/>
                  </div>
                </div>
              </div>
                <div class="col-xs-24 col-sm-24 col-md-24 text-center">
                  <button type="button" class="btn btn-primary" id="queryBtn" modal="enter"><span class="glyphicon glyphicon-search"></span><span>&nbsp;查询&nbsp;</span>
                  </button>
                  <button type="button"  id="clearBtn" class="btn btn-white" modal="reset"><span class="glyphicon glyphicon-remove"></span><span>&nbsp;清除查询条件&nbsp;</span>
                  </button>
                </div>
            </form>
          </div>
        </div>
         <div class="ibox-content">
        <div class="operate">
	<div class="om-panel-header"></div>
	<div class="icon">
		<ul>
    		<li><a href="#" onclick="showAdd('/system/user/showAdd',600,250);"><span class="menu1"></span>添加</a></li>
    		<li><a href="#" onclick="showEdit('/system/user/showEdit','user_id',600,250);"><span class="menu13"></span>修改</a></li>
    		<li><a href="#" onclick="removeRow('user_id','/system/user/delete');"><span class="menu11"></span>删除</a></li>
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
$(function() {
	var jsonStr="";
	
	$.ajax({
		type : "post",
		dataType : "text",
		async : false,
		url : '/system/user/dictStatusMap',
		data : {
		},
		success : function(msg1) {
			jsonStr = msg1;
		}
	})
	
	$('#grid').datagrid({   
	    url:'/system/user/list', 
	    pageSize :10,
		pageList : [10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000],
		striped : true,
		//rownumbers : true,
		pagination : true,
		toolbar : '#toolbar',
	    columns : [[ {width : '50', field : 'ck',checkbox:true},
	                 {width : '120',title : '系统登录名',field : 'user_name'},
	                 /* {width : '100',title : '真实姓名',field : 'realName'}, */
	                 /* {width : '150',title : '手机号码',field : 'mobile'}, */
	                 /* {width : '50',title : '姓别',field : 'sex',formatter:function(v,r){return JSON.parse('${sexMap}')[v]}}, */
	                 /* {width : '150',title : '邮箱',field : 'email'}, */
	                 {width : '80',title : '用户角色',field : 'role_name'},
	                 {width : '80',title : '状态',field : 'user_status',formatter:function(v,r){ return JSON.parse(JSON.parse(jsonStr)["statusMap"])[v]}},
	                /*  {width : '50',title : '排序',field : 'userOrder'}, */
	                 {width : '150',title : '创建时间',field : 'create_time'},
					 {width : '150',title : '最后登录时间',field : 'update_time'},
					 {width : '200',title : '操作',field : 'xx',formatter:function(v,r){ var userId = r.user_id;var userStatus = r.user_status; return '<a href="javascript:showEdit(\'/system/user/showEdit\',\'user_id\',600,270);" >修改</a>'+'&nbsp&nbsp'+'<a href="javascript:disableUser('+userId+','+userStatus+');" >用户停用</a>'+'&nbsp&nbsp'+'<a href="javascript:removeRow(\'user_id\',\'/system/user/delete\');" >删除</a>'}}]
	    		]
	}); 
    $('#userStatus').combobox({  
    	data:JSON.parse('${statusCombo}'),
    	editable:false,
    	panelHeight:'auto'
    }); 
});
function initPwd(deleteId){
	var selections = $('#grid').datagrid('getSelections');
	if (selections.length == 0) {
		$.messager.alert('提示:','请至少选择一行记录'); 
		return false;
	}
	$.messager.confirm('提示:','确定对所选数据进行初始化密码(<span style="color:red">${initPassword}</span>)？',function(e){ 
		if(e){ 
		   var ids = [];
  		   for(var i=0;i<selections.length;i++){ids.push(selections[i][deleteId]);}
  		   $.post('initPwd',{"ids":ids.toString()}, function(data) {
  			 if(data.success){
  					$("#grid").datagrid('reload'); 	
  					$.messager.show({ 
  						title:'温馨提示:', 
  						msg:'初始化密码成功!', 
  						timeout:1500, 
  						showType:'slide'
  					}); 
  				} else {
  					$.messager.alert('提示:',data.msg,'warning'); 
  				}
  			}, 'json');
		}
	}); 	
}

//用户停用
function disableUser(roleId,roleStatus){
	$.ajax({
			type : "post",
			dataType : "text",
			url : '/system/user/update',
			data : {
				userId : roleId,
				userStatus : '2'
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