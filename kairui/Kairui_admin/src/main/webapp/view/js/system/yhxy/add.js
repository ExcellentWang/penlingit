
$(function(){
	var ue = UE.getEditor('container');
	var args=comn.getArgs();
	$.ajax({
		url: "/user/selectEula",
		data:{},
		success: function(item){
			ue.ready(function(){
				ue.setContent(item.data.content);
			});
		}
	});
	//保存
	$("#baocun").click(function(){
		$.ajax({
				url: "/user/addOrUpdateEula",
				data:{
					id:args['id'],
					content: ue.getContent()
				},
				type:"post",
				success: function(item){
					if(item.code!=0){
		 				tip({
		 					content:item.msg
		 				})
					}else{
		 				tip({
		 					content:"成功！"
		 				})
		 				comn.closeTab()
					}
				}
			});
	})
	//返回
	$("#fanhui").click(function(){
		alert(1)
		comn.closeTab();
	})
})
