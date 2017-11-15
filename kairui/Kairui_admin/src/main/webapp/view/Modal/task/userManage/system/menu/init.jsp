<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../taglibs.jsp" %>
<html>
<head> <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%-- <link rel="stylesheet" href="${js}/scrollbar/jquery.mCustomScrollbar.min.css">
<script src="${js}/scrollbar/jquery.mCustomScrollbar.concat.min.js"></script> --%>
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
                  <label class="col-xs-3 col-sm-3 col-md-3 control-label">菜单名：</label>
                  <div class="col-xs-5 col-sm-5 col-md-5">
                    <input type="text" id="menuName" name="menuName" placeholder="" class="form-control">
                  </div>
                </div>
                <div class="input-tip">
                  <label class="col-xs-3 col-sm-3 col-md-3 control-label">菜单级别：</label>
                  <div class="col-xs-5 col-sm-5 col-md-5">
                    <input type="text" id="menuLevel" name="menuLevel" placeholder="" class="form-control">
                  </div>
                </div>
              </div>
              <div class="form-group form-group-sm">
                <div class="input-tip">
                  <label class="col-xs-3 col-sm-3 col-md-3 control-label">状态：</label>
                 <div class="col-xs-5 col-sm-5 col-md-5">
                    <input id="userStatus" name="userStatus" type="combo" placeholder="" class="form-control">
                  </div>
                </div>
                <div class="input-tip">
                  <label class="col-xs-3 col-sm-3 col-md-3 control-label">创建时间：</label>
                  <div class="col-xs-5 col-sm-5 col-md-5">
                    <input id="updateTimeStart" name="updateTimeStart" type="datetime" placeholder="" class="form-control easyui-datebox"/>
                  </div>
                </div>
              </div>
              <div class="form-group form-group-sm">
               <div class="input-tip">
                 <label class="col-xs-3 col-sm-3 col-md-3 control-label">结束时间：</label>
                  <div class="col-xs-5 col-sm-5 col-md-5">
					 <input id="updateTimeEnd" name="updateTimeEnd" type="datetime" placeholder="" class="form-control easyui-datebox"/>
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
    		<li><a href="#" onclick="showMenuAdd();"><span class="menu1"></span>添加</a></li>
	    		<li><a href="#" onclick="showEdit('/system/menu/showEdit','menuId',600,240);"><span class="menu13"></span>修改</a></li>
	    		<li><a href="#" onclick="removeRow('menuId','/system/menu/delete');"><span class="menu11"></span>删除</a></li>
		</ul>
	</div>
</div>
</div>
                <table id="grid" data-options=""></table>
      </div>
    </div>
  </div>
</body>
<script type="text/javascript">
$(function() {	
	 refleshTree=true;
     $('#grid').datagrid({   
 	    url:'/system/menu/list', 
 	    pageSize :10,
 		pageList : [10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000 ],
 		striped : true,
 		//rownumbers : true,
 		pagination : true,
 		toolbar : '#toolbar',
 	    columns : [[ {width : '50', field : 'ck',checkbox:true},
 	                 {width : '100',title : '菜单名',field : 'menuName'},
 	                 {width : '200',title : '菜单URL',field : 'menuUrl'},
 	                 {width : '100',title : '菜单层级',field : 'menuLevel'},
 	                 {width : '100',title : '菜单类型',field : 'menuType',
 	                	                formatter:function(v,r){
 	                	                     return JSON.parse('{"1":"菜单文件夹","2":"页面菜单","3":"功能按钮"}')[v]
 	                	                }
 	                 },
 	                 {width : '50',title : '排序',field : 'menuOrder'},
 					 {width : '50',title : '状态',field : 'menuStatus',formatter:function(v,r){return JSON.parse('${statusMap}')[v]}},
 					 {width : '150',title : '修改时间',field : 'updateTime'}]
 	    		]
 	}); 
    //加载下拉树
    loadTree(); 
    $('#menuStatus').combobox({  
    	data:JSON.parse('${statusCombo}'),
    	editable:false,
    	panelHeight:'auto'
    });  
});
function loadTree(){
	 $('.panel.datagrid.easyui-fluid').css({position:'absolute',left:'200px'});  
	 $('body').append('<div class="treeDiv" style="display:block;"><ul id="navtree"></ul></div>');
	//$('.panel-tool').css('margin-right','200px');
	 //$('.operate').css('margin-right','200px');
	 $('#navtree').tree({   
	    url:'tree',
	    onClick: function(node){
	    	$("#menuId").val(node.id);
      		$("#queryBtn").click();
		},
		onLoadSuccess:function(){
			$('#navtree').height($('.treeDiv').height());
			/* $('#navtree').mCustomScrollbar({
				theme:"minimal-dark"
			}); */
		}
	});
}

function showMenuAdd(){
	var menuPid = $("#menuId").val();
	if(menuPid==''){		
		$.messager.alert('提示:','请在左边节点树上选择父节点！'); 
		return
	}
	$.post("getMenu", {"menuId":menuPid},
	   function(msg){		
			if(msg.menuLevel>=3 && msg.menuType==1 ){
				$.messager.alert('提示:','菜单级数不能超过3级！');   
			}
			else if(msg.menuType==3){			
				$.messager.alert('提示:','不能在功能按钮上添加菜单！');  
			}else{
				showAdd('/system/menu/showAdd?menuPid='+menuPid,600,220);
			}
      }, "json");
}
</script>
</html>