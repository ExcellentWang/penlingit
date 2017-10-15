
$(function(){
	var ue = UE.getEditor('container');
	var args=comn.getArgs();
	$.ajax({
		url: "/getLunboId",
		data:{"id":args['id']},
		success: function(item){
			$("#lunboForm").values(item.data)
			ue.ready(function(){
				ue.setContent(item.data.content);
			});
		}
	});
	
	//添加
	$("#addlunbo").click(function(){
		$.ajax({
				url: "/addLunbo",
				data:$.extend($("#lunboForm").values(),{"content":ue.getContent()}),
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
					$("#sureModal").modal('hide')
				}
			});
	})
})
