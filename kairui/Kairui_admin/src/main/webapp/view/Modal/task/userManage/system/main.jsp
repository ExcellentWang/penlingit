<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ include file="taglibs.jsp"%>
<html>
<head> <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>监测仪广告后台管理</title>
<link href="${css}/main.css" rel="stylesheet" type="text/css"/>
</head>
<body class="easyui-layout">
<div id="north-panel" class="north-panel" data-options="region:'north',border:false" style="height:64px;">
	<div class="north-top">
		<span class="logo"> </span>
		<span class="top-ico logout" onclick="logout()">退出</span>
		<span class="top-ico password" onclick="changePasWord()">修改密码</span>
		<span class="top-ico home" onclick="showOmtabs()">主页</span>
		<span class="top-ico user"><shiro:principal/></span>
    </div>
</div>

<div data-options="region:'west'" style="width:200px;background:#269c79;oveflow-x:hidden;">
	<div id="westMenu" class="west-menu" style="overflow-y:scroll; width:230px;padding-bottom:50px;"></div>
</div>

<div id="center-panel" class="center-panel" data-options="region:'center'">
	<div id="eTab" class="easyui-tabs" data-options="border:false" style="width:100%;">
		<div title="我的主页" id="myIndex">
			<iframe id="indexF" width='100%' style="border:0;background:#fff url(${img}/bj.jpg) no-repeat center center;"></iframe>
		</div> 
	</div>
</div>
<div data-options="region:'south'" style="height:36px;display:none;"><div id="footer">优乐（武汉）健康科技有限公司版权所有</div></div>
<script>
$(function(){
	resize();
	loadMenuTree();
	showRightMenu();
});

function resize(){
 	$('#eTab').height($('#center-panel').height());	
	tabH = $('#eTab').height() - $(".tabs-wrap").height();
	$('#indexF').height(tabH);	
}
function loadMenuTree(){
	$.post(ctx+"/index/tree",
	   function(data){
			$('#westMenu').menuTree(data);
			$('#westMenu').height($('#center-panel').height());
	},"json");
}

function openTab(menuId){
	$.post(ctx+"/system/menu/getMenu", {"menuId":menuId},
	   function(msg){
			var url = ctx+msg.menuUrl;
			var text = msg.menuName;
			var opts = {
	            title : text, 
	            tabId :'menu'+menuId,
	            content : "<iframe id='"+menuId+"' frameBorder='no' width='100%' height='"+tabH+"' src='"+url+"' ></iframe>",
	            closable : true
	        };
			var tabs = $('#eTab');
			if(tabs.tabs('exists', text)){
				tabs.tabs('select', text);
				var tab = tabs.tabs('getSelected');  // 获取选择的面板
				tabs.tabs('update', {tab:tab,options:opts});
			}else{
				tabs.tabs('add', opts);
			}

	   }, "json");
}

//选项卡面板被鼠标右键单击后触发
function showRightMenu(){
	if($("#tabRightMenu").length <=0){
		//菜单创建完毕，它是隐藏而不可见的，调用'show'方法可以显示菜单
		var menu=$("<div></div>"),closeMenu=$("<div></div>"),
			closeOther=$("<div></div>"),closeAll=$("<div></div>");
		menu.attr("id","tabRightMenu").addClass("easyui-menu");
		closeMenu.attr("name","closeMenu").attr("iconCls","icon-clear").html("关闭");
		closeOther.attr("name","closeOther").attr("iconCls","icon-remove").html("关闭其他");	
		closeAll.attr("name","closeAll").attr("iconCls","icon-no").html("关闭所有");	
		menu.append(closeMenu);
		menu.append(closeOther);
		menu.append(closeAll);
		menu.appendTo("body");
	}
	
	$('#eTab').tabs({
		onContextMenu : function(e,title){
			e.preventDefault();//阻止默认行为
			if(title === "我的主页"){
				return;
			}
			
			$("#tabRightMenu").menu("show",{
				left: e.pageX,
				top : e.pageY
				
			}).data("currentTitle", title);
		}
	});
	
	$("#tabRightMenu").menu({
        onClick : function (item) {//实例化menu的onClick事件
        	closeTab(this, item.name);
        }
    });
}

function closeTab(menu,type){
	var current=$(menu).data("currentTitle");//选中当前的选项卡面板
	var tabs = $('#eTab');
	var allTabs=tabs.tabs("tabs");
	var closableTab=[];
	if(type === "closeMenu"){//关闭自己
		tabs.tabs("close",current);
		return ;		
	}	
	
	$.each(allTabs,function(){
		var opt=$(this).panel("options");
		if(opt.closable && opt.title != current && type === "closeOther"){
			closableTab.push(opt.title);
		}else if(opt.closable && type === "closeAll"){
			closableTab.push(opt.title);
		}
	});
	
	for(var i in closableTab){
		tabs.tabs("close",closableTab[i]);
	}
	
	 if(type === "closeOther"){
		$(".tabs li:first").hide();//隐藏我的主页，不然有BUG
		$(".tabs-panels .panel:first").hide();
		tabs.tabs("select",current);
	} else{
		$(".tabs li:first").show();//隐藏我的主页，不然有BUG
		$(".tabs-panels .panel:first").show();
	}
	
	/*  var select=tabs.tabs("getSelected");
		//关闭其他时，如果在选中面板上点击“关闭其他”，会关闭其他，但是会显示我的主页面板  bug
		if(type === "closeOther" && select.panel("options").title === current){
			tabs.tabs("select",current);
		}  */
}

function showOmtabs(){document.location.reload();} 
function logout(){
	window.location.href=ctx+'/security/logout';
}
function changePasWord(){
	$.dialog.open(ctx+'/index/password', {
		lock: true,
		width:600,
		height:220
	});
 }

function openChildTab(url,menuId,text){
	var opts = {
        title : text, 
        tabId :'menu'+menuId,
        content : "<iframe id='"+menuId+"' frameBorder='no' width='100%' height='"+tabH+"' src='"+url+"' ></iframe>",
        closable : true
    };
	var tabs = $('#eTab');
	if(tabs.tabs('exists', text)){
		tabs.tabs('select', text);
		var tab = tabs.tabs('getSelected');  // 获取选择的面板
		tabs.tabs('update', {tab:tab,options:opts});
	}else{
		tabs.tabs('add', opts);
	}
} 
function closeChildTab(menuId){
	$('#eTab').tabs('close',menuId);
}
</script>
</body>
</html>