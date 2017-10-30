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
               
              </div>
              <div class="form-group form-group-sm">
               
                <div class="col-xs-24 col-sm-24 col-md-24 text-center">
                 
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
    		<li><a href="#" onclick="showEdit('/system/dict/showEditYhxy','dictId',600,230);"><span class="menu13"></span>修改</a></li>
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
	$('#grid').datagrid({   
	    url:'/system/dict/listyhxy', 
	    pageSize :10,
		pageList : [10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000 ],
		striped : true,
		//rownumbers : true,
		pagination : true,
		toolbar : '#toolbar',
	    columns : [[ {width : '50', field : 'ck',checkbox:true},
	                 {width : '200',title : '用户协议名称',field : 'dictName',sortable:true},
	                 {width : '350',title : '用户协议描述',field : 'dictDesc',sortable:true},
	                 {width : '100',title : '状态',field : 'dictStatus',sortable:true,formatter:function(v,r){return JSON.parse('${statusMap}')[v]}},
	                 {width : '150',title : '修改时间',field : 'updateTime',sortable:true}/* ,
	                 {width : '50',title : '操作',field : 'action',sortable:true,formatter:function(value,row){
	                	 return '<a href="<c:url value='/system/detail/init'/>?dictId='+row.dictId+'"><img border=0 src="<c:url value='/static/images/childs.gif'/>" /></a>';
	    			 }
	    			} */]]
	}); 
});



</script>
</html>