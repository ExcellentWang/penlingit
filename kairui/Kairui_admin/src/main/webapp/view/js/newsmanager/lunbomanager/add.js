
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
		var formData = new FormData();
        formData.append("file", document.getElementById("file").files[0]);
        formData.append("title", $("[name='title']").val());
        formData.append("lunboOrder", $("[name='lunboOrder']").val());
        formData.append("content", ue.getContent());
        formData.append("isShow", $("[name='isShow']").val());
		$.ajax({
				url: "/addLunbo",
				data:formData,
				type: "POST",
	            contentType: false,
	            processData: false,
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
