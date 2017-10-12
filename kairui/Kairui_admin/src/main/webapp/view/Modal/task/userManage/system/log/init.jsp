<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../taglibs.jsp" %>
<html>
<head> <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
      <div class="col-xs-24 col-sm-24 col-md-24">
        <div class="ibox float-e-margins">
          <div class="ibox-content">
            <form class="form-horizontal" id="list" action="list">
			<input type="hidden" id="dictId" name="dictId" value="${dictId}">
              <div class="form-group form-group-sm">
                <div class="input-tip">
                  <label class="col-xs-3 col-sm-3 col-md-3 control-label">访问人：</label>
                  <div class="col-xs-5 col-sm-5 col-md-5">
                    <input type="text" id="userName" name="userName" placeholder="" class="form-control">
                  </div>
                </div>
              
			  <div class="input-tip">
                  <label class="col-xs-3 col-sm-3 col-md-3 control-label">访问IP：</label>
                  <div class="col-xs-5 col-sm-5 col-md-5">
                    <input type="text" id="userIp" name="userIp" placeholder="" class="form-control">
                  </div>
                </div>
              </div>
              <div class="form-group form-group-sm">
                
              </div>
              <div class="form-group form-group-sm">

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
    		
		</ul>
	</div>
</div>
</div>
                <table id="grid" data-options="" class="table"></table>
      </div>
    </div>
  </div>
</body>
<script type="text/javascript">
$(function() {
	$('#grid').datagrid({   
	    url:'/Kairui_admin/system/log/list', 
	    pageSize :10,
		pageList : [10, 20, 30, 40, 50, 100, 200, 300, 400, 800, 500 ],
		striped : true,
		rownumbers : true,
		pagination : true,
		singleSelect : true,
		toolbar : '#toolbar',
	    columns : [[{title : '访问人',field:'userName',width:80}, 
                    {title : '访问IP', field : 'userIp',width:150}, 
                    {title : '访问时间', field : 'logTime',width:150} , 
                    {title : '耗时', field : 'processTime',width:50} ,
                    {title : '访问URL',field:'actionUrl',width:400},
                    {title : '描述',field:'logDesc',width:400}
                   ]
	    		]
	}); 
});
function exportExcel(){
	window.location.href = ctx + '/log/exportExcel';
}

</script>
</html>