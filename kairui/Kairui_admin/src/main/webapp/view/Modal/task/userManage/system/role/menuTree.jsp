<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../taglibs.jsp" %>
<html>
<head> <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%-- <link rel="stylesheet" href="${js}/scrollbar/jquery.mCustomScrollbar.min.css">
<script src="${js}/scrollbar/jquery.mCustomScrollbar.concat.min.js"></script> --%>
</head>
<body>
<div class="dialogPage">
	
	<div class="om-panel-header">绑定菜单</div>
	
	<div class="treeDiv" style="width:238px;border:1px solid #86a3c4;">
			<button type="button"  onclick="treeChecked()">全选</button>
		<button type="button"  onclick="treeUnAllChecked()">全不选</button>
		<ul id="navtree" style="  overflow-y: scroll;"></ul>

	</div>
	<form id="form1" action="bindMenu">
	<div class="editBtn" style="position:absolute;bottom:0px;margin:10px auto;">
		<input type="hidden" name="roleId" value="${roleId}" />
		<input type="hidden" name="menuIds" id="menuIds"/>
		<button type="button" class="button" onclick="doSubmit()">保存</button>
		<button type="button" class="button" onclick="javascript:art.dialog.close();">关闭</button>
	</div>
	</form>
</div>
<script>
$(function(){
  	$(".treeDiv").css("height","420px");  
    $('#navtree').tree({   
	    url:ctx+'/system/menu/tree',
	    checkbox:true,
	    cascadeCheck: false,
	    onLoadSuccess:function(){
	    	var sRoleMenuJson = ${SRoleMenuJson};
	    	for(var i=0;i<sRoleMenuJson.length;i++){
	    		var n = $("#navtree").tree('find',sRoleMenuJson[i].menuId);
	            if(n){
	                $("#navtree").tree('check',n.target);
	            }
	    	}
	    	$('#navtree').height($('.treeDiv').height());
    		/* 
    		$('#navtree').mCustomScrollbar({
	    		theme:"minimal-dark"
	    	});
    		*/
	    	
	    },       
	    onCheck: function (node, checked) {
            if (checked) {
                var parentNode = $("#navtree").tree('getParent', node.target);
                if (parentNode != null) {
                    $("#navtree").tree('check', parentNode.target);
                }
            } else {
                var childNode = $("#navtree").tree('getChildren', node.target);
                if (childNode.length > 0) {
                    for (var i = 0; i < childNode.length; i++) {
                        $("#navtree").tree('uncheck', childNode[i].target);
                    }
                }
            }
        }
	}); 
});
function doSubmit(){
	var nodes = $('#navtree').tree('getChecked');
	var menuIds=[];
    for(var i=0;i <nodes.length; i++){
    	menuIds.push(nodes[i].id);
	}
	$("#menuIds").val(menuIds.toString());
	$("#form1").submit();
}

//反选
/* function treeChecked(selected, treeMenu) {  
	//返回tree的所有根节点数组  
    var roots = $('#navtree').tree('getRoots');
    if (selected.checked) {  
        for ( var i = 0; i < roots.length; i++) {  
            var node = $('#' + treeMenu).tree('find', roots[i].id);//查找节点  
            $('#' + treeMenu).tree('check', node.target);//将得到的节点选中  
        }  
    } else {  
        for ( var i = 0; i < roots.length; i++) {  
            var node = $('#' + treeMenu).tree('find', roots[i].id);  
            $('#' + treeMenu).tree('uncheck', node.target);  
        }  
    }  
}  */

//全选
function treeChecked() {  
	//返回tree的所有根节点数组  
    var roots = $('#navtree').tree('getChecked','unchecked');
    var treeMenu = "navtree";

        for ( var i = 0; i < roots.length; i++) {  
            var node = $('#' + treeMenu).tree('find', roots[i].id);//查找节点  
            $('#' + treeMenu).tree('check', node.target);//将得到的节点选中  
        }  
 
} 

//全不选
function treeUnAllChecked() {  
	//返回tree的所有根节点数组  
    var roots = $('#navtree').tree('getChecked');
    var treeMenu = "navtree";

        for ( var i = 0; i < roots.length; i++) {  
            var node = $('#' + treeMenu).tree('find', roots[i].id);//查找节点  
            $('#' + treeMenu).tree('uncheck', node.target);//将得到的节点选中  
        }  
 
} 
</script>
</body>
</html>