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
			<input type="hidden" id="dictId" name="dictId" value="${dictId}">
              <div class="form-group form-group-sm">
                <div class="input-tip">
                  <label class="col-xs-3 col-sm-3 col-md-3 control-label">字典名称：</label>
                  <div class="col-xs-5 col-sm-5 col-md-5">
                    <input type="text" id="dictName" name="dictName" placeholder="" class="form-control">
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
    		<li><a href="#" onclick="showAdd('/system/dict/showAdd',600,230);"><span class="menu1"></span>添加</a></li>
    		<li><a href="#" onclick="showEdit('/system/dict/showEdit','dictId',600,230);"><span class="menu13"></span>修改</a></li>
    		<li><a href="#" onclick="removeRow('dictId','/system/dict/delete');"><span class="menu11"></span>删除</a></li>
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
	    url:'/system/dict/list', 
	    pageSize :10,
		pageList : [10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000 ],
		striped : true,

		pagination : true,
		toolbar : '#toolbar',
	    columns : [[ {width : '50', field : 'ck',checkbox:true},
	                 {width : '200',title : '字典名称',field : 'dictName',sortable:true},
	                 {width : '350',title : '字典描述',field : 'dictDesc',sortable:true},
	                 /* {width : '100',title : '状态',field : 'dictStatus',sortable:true,formatter:function(v,r){return JSON.parse('${statusMap}')[v]}}, */
	                 {width : '150',title : '修改时间',field : 'updateTime',sortable:true},
	              	/*  {width : '50',title : '操作',field : 'action',sortable:true,formatter:function(value,row){
	                	 return '<a href="<c:url value='/system/detail/init'/>?dictId='+row.dictId+'"><img border=0 src="<c:url value='/static/images/childs.gif'/>" /></a>';
	    			 }
	    			}  */]] 
	}); 
});



</script>
</html>