(function($){
	//拖拽插件
	$.fn.drag = function(){
		return this.each(function() {
			var than = $(this);
			than.mousedown(function(e){ 
				$(this).css("cursor","move");//改变鼠标指针的形状 
	
				var offset = $(this).offset();//DIV在页面的位置 
				var x = e.pageX - offset.left;//获得鼠标指针离DIV元素左边界的距离 
				var y = e.pageY - offset.top;//获得鼠标指针离DIV元素上边界的距离 
				$(document).bind("mousemove",function(ev)//绑定鼠标的移动事件，因为光标在DIV元素外面也要有效果，所以要用doucment的事件，而不用DIV元素的事件 
				{ 
					than.stop();//加上这个之后 
	
					var _x = ev.pageX - x;//获得X轴方向移动的值 
					var _y = ev.pageY - y;//获得Y轴方向移动的值 
	
					than.animate({left:_x+"px",top:_y+"px"},10); 
				}); 
	
			}); 
	
			$(document).mouseup(function(){
				than.css("cursor","default"); 
				$(this).unbind("mousemove"); 
			});
		});
	}
})(jQuery)

$(function(){
	//查询条件展开收缩方法
	$('.easyui-panel').panel({   
	    onCollapse:function(){   
	    	$('#grid').datagrid('resize');
	    },
	    onExpand:function(){
	    	$('#grid').datagrid('resize');
	    },
	    onBeforeExpand:function(){
	    	$('#search').height('auto');
	    }
	}); 
	//查询按钮点击事件
	$('#queryBtn').click(function(){
		if ($.isFunction(window.checkDate)){
			if(checkDate()){
				$('#grid').datagrid('load',getFormData(document.forms[0]));
			}
		}else{
			$('#grid').datagrid('load',getFormData(document.forms[0]));
		}
	});
	//清空按钮点击事件
	$('#clearBtn').click(function(){
		clearForm(document.forms[0]);
	});
	
	//回车查询
	document.onkeydown = function(e){
	    var ev = document.all ? window.event : e;
	    if(ev.keyCode==13) {
	    	if ($.isFunction(window.checkDate)){
				if(checkDate()){
					$('#grid').datagrid('load',getFormData(document.forms[0]));
				}
			}else{
				$('#grid,#grid1,#grid2').datagrid('load',getFormData(document.forms[0]));
			}
	     }
	};
});

//封装常用的工具方法，如搜索框数据格式化、清空,add edit remove showTip..等
(function(){
	var init = {
		//格式化搜索框中的数据
		getFormData:function(form){
			var obj = {};
			//文本框
			$("#"+form.id+" :input[type='text']").each(function() {
				if($(this).val()){
					obj[$(this).attr("name")] = $(this).val();
				}
			});
			// 隐藏框
			$("#"+form.id+" :input[type='hidden']").each(function() {
				if($(this).val()){
					obj[$(this).attr("name")] = $(this).val();
				}
			});
			//date
			$("#" + form.id + " :input[type='date']").each(function() {
				if($(this).val()){
					obj[$(this).attr("name")] = $(this).val();
				}
			});
			//select
			$("#" + form.id + " select").each(function() {
				if($(this).val()){
					obj[$(this).attr("name")] = $(this).val();
				}
			});
			return obj;
		},
		//清除搜索框中的数据，注意会清空隐藏域
		clearForm:function(form){
			for(var i=0;i<form.elements.length;i++){
				var field=form.elements[i];
				var fieldType=form.elements[i].type.toLowerCase();
				if(fieldType!="submit" && fieldType!="reset" && fieldType!="button"){  
					if(fieldType=="radio" || fieldType=="checkbox"){
						field.checked=false;
					}else if(fieldType=="select"){
						field.selected=false;
					}else{
						field.value="";
					}
				}
				var dataName = form.elements[i].name;
				if(dataName=='createTimeStart'||dataName=='createTimeEnd'||dataName=='updateTimeStart'||dataName=='updateTimeEnd'){
					field.value="";
				}
			}
		},
		list:{
			tip:function(optionResult,faildMessage){
				if (optionResult && optionResult == 2) {//1成功，2失败
					if(faildMessage.length<=0){
						faildMessage="操作失败，请重试！";
					}
					$.messager.alert('错误:',faildMessage,'error'); 
				}else{//默认成功
					$("#grid").datagrid('reload');
					refleshTree&&($("#navtree").tree('reload'));//刷新菜单树，默认false不删除，只有菜单功能会刷新
					$.messager.show({ 
						title:'温馨提示:', 
						msg:'操作成功!', 
						timeout:1500, 
						showType:'slide'
					}); 
				}
			},
			add:function(url,w,h){
				$.dialog.open(ctx+url, {
					lock: true,
					width:w,
					height:h
				});
			},
			edit:function(url,updateId,w,h){
				var selections = $('#grid').datagrid('getSelections');
				if (selections.length != 1) {		
					$.messager.alert('提示:','请选择一行记录!'); 
					return false;
				}
				var id = selections[0][updateId];
				$.dialog.open(ctx+url+'?'+updateId+'='+id, {
					lock: true,
					width:w,
					height:h
				});
			},
			editParam:function(url,updateId,w,h,param){
				var selections = $('#grid').datagrid('getSelections');
				if (selections.length != 1) {		
					$.messager.alert('提示:','请选择一行记录!'); 
					return false;
				}
				var id = selections[0][updateId];
				$.dialog.open(ctx+url+'?'+updateId+'='+id + param, {
					lock: true,
					width:w,
					height:h
				});
			},
			instructions:function(field,element){
				var instructionsType = element==7?"7":$(element).attr("val");
				var title = element==7?"关闭视频":$(element).text();
				var selections = $('#grid').datagrid('getSelections');
				if (selections.length == 0) {
					$.messager.alert('提示:','请至少选择一行记录'); 
					return false;
				}else if (selections.length>1){
					$.messager.alert('提示:','只能选择一行记录'); 
					return false;
				}else{
					$.messager.confirm('提示:','你确认要操作吗?',function(e){ 
						if(e){ 
						   var macs = [];
						   var videoTile = selections[0].shop+"-"+title;
				  		   for(var i=0;i<selections.length;i++){
				  			 macs.push(selections[i][field]);
				  		   }
				  		   $.post('instructions',{"instructionsType":instructionsType,"macs":macs.toString()}, function(data) {
				  			   if(data.success){
				  				   switch (instructionsType) {
									case "6":
										 $("#videoWarp").showVideo({
						  						videoUrl: "http://live.daboowifi.net/bodyfat/"+macs+".m3u8",
						  						title : videoTile
						  					   });
										break;
									case "7":
										$("#videoWarp").css("display","none").html("");
					  					//清除选中项
						  				$("#grid").datagrid('reload');
						  				refleshTree&&($("#navtree").tree('reload'));
										break;
	
									default:
										//清除选中项
						  				$("#grid").datagrid('reload');
						  				refleshTree&&($("#navtree").tree('reload'));
										break;
									}
					  				 $.messager.show({
					  						title:'温馨提示:', 
					  						msg:title+'!', 
					  						timeout:1500, 
					  						showType:'slide'
					  					});
				  				 
				  				} else {
				  					if(data.msg){
				  						$.messager.alert('提示:',data.msg,'warning');
				  					}
				  				}
				  			}, 'json');
						}
					}); 
				}
			},
			reviewRow:function(id,status){
				var selections = $('#grid').datagrid('getSelections');
				if (selections.length == 0) {
					$.messager.alert('提示:','请至少选择一行记录'); 
					return false;
				}
				var type = status==1?'审核通过':'审核未通过';
				//当只有2个参数时,为审核
				$.messager.confirm('提示:','你确认要'+type+'吗?',function(e){
					if(e){ 
					   var ids = [];
			  		   for(var i=0;i<selections.length;i++){ids.push(selections[i][id]);}
			  		   $.post('review',{"ids":ids.toString(),"status":status}, function(data) {
			  			   if(data.success){
			  					$("#grid").datagrid('reload');
			  					refleshTree&&($("#navtree").tree('reload'));
			  					$.messager.show({ 
			  						title:'温馨提示:', 
			  						msg:type+'成功!', 
			  						timeout:1500, 
			  						showType:'slide'
			  					}); 
			  				} else {
			  					if(data.msg){
			  						$.messager.alert('提示:',data.msg,'warning');
			  					}
			  				}
			  			}, 'json');
					}
				}); 
			},
			updateRow:function(id,status){
				var selections = $('#grid').datagrid('getSelections');
				if (selections.length == 0) {
					$.messager.alert('提示:','请至少选择一行记录'); 
					return false;
				}
				var type = status==1?'发货':'取消发货';
				//当只有2个参数时,为审核
				$.messager.confirm('提示:','你确认要操作吗?',function(e){
					if(e){ 
					   var ids = [];
			  		   for(var i=0;i<selections.length;i++){ids.push(selections[i][id]);}
			  		   $.post('updateReceive',{"ids":ids.toString(),"status":status}, function(data) {
			  			   if(data.success){
			  					$("#grid").datagrid('reload');
			  					refleshTree&&($("#navtree").tree('reload'));
			  					$.messager.show({ 
			  						title:'温馨提示:', 
			  						msg:type+'成功!', 
			  						timeout:1500, 
			  						showType:'slide'
			  					}); 
			  				} else {
			  					if(data.msg){
			  						$.messager.alert('提示:',data.msg,'warning');
			  					}
			  				}
			  			}, 'json');
					}
				}); 
			},
			remove:function(deleteId,url){
				var selections = $('#grid').datagrid('getSelections');
				if (selections.length == 0) {
					$.messager.alert('提示:','请至少选择一行记录'); 
					return false;
				}
				$.messager.confirm('提示:','你确认要删除吗?',function(e){ 
					debugger;
					if(e){ 
					   var ids = [];
			  		   for(var i=0;i<selections.length;i++){ids.push(selections[i][deleteId]);}
			  		   $.post(url,{"ids":ids.toString()}, function(data) {
			  			   if(data.success){
			  					$("#grid").datagrid('reload');
			  					refleshTree&&($("#navtree").tree('reload'));
			  					$.messager.show({ 
			  						title:'温馨提示:', 
			  						msg:'删除数据成功!', 
			  						timeout:1500, 
			  						showType:'slide'
			  					}); 
			  				} else {
			  					if(data.msg){
			  						$.messager.alert('提示:',data.msg,'warning');
			  					}
			  				}
			  			}, 'json');
					}
				}); 
			},
			deleteRow:function(deleteId){
				var selections = $('#grid').datagrid('getSelections');
				if (selections.length == 0) {
					$.messager.alert('提示:','请至少选择一行记录'); 
					return false;
				}
				$.messager.confirm('提示:','你确认要删除吗?',function(e){ 
					if(e){ 
					   var ids = [];
			  		   for(var i=0;i<selections.length;i++){ids.push(selections[i][deleteId]);}
			  		   $.post('deleteRecive',{"ids":ids.toString()}, function(data) {
			  			   if(data.success){
			  					$("#grid").datagrid('reload');
			  					refleshTree&&($("#navtree").tree('reload'));
			  					$.messager.show({ 
			  						title:'温馨提示:', 
			  						msg:'删除数据成功!', 
			  						timeout:1500, 
			  						showType:'slide'
			  					}); 
			  				} else {
			  					if(data.msg){
			  						$.messager.alert('提示:',data.msg,'warning');
			  					}
			  				}
			  			}, 'json');
					}
				}); 
			},
			refreshGrid:function(id){
				$('#'+id).datagrid('reload');// 刷新当前页数据
				$.messager.show({ 
					title:'温馨提示:', 
					msg:'操作成功!', 
					timeout:1500, 
					showType:'slide'
				}); 	
			}
		}
	};
	//提供一些全局方法
	window.getFormData = init.getFormData;
	window.clearForm = init.clearForm;
	window.showTip = init.list.tip;
	window.showAdd = init.list.add;
	window.showEdit = init.list.edit;
	window.instructions = init.list.instructions;
	window.showEditParam = init.list.editParam;
	window.reviewRow = init.list.reviewRow;
	window.updateRow = init.list.updateRow;
	window.removeRow = init.list.remove;
	window.deleteRow = init.list.deleteRow;
	window.refreshGrid = init.list.refreshGrid;
	
	window.refleshTree=false;
}(window));
