
$(function(){
	var ue = UE.getEditor('container');
	var args=comn.getArgs();
	var isUpload=false;
	$.ajax({
		url: "/getTbNewsinformationId",
		data:{"id":args['id']},
		success: function(item){
			$("#lunboForm").values(item.data)
			ue.ready(function(){
				ue.setContent(item.data.content);
			});
		}
	});
	$("[name='file']").change(function(){
		isUpload=true;
	})
	//添加
	$("#addlunbo").click(function(){
		$("#lunboForm").validate()
		if(!$("#lunboForm").valid())return
		if(isUpload==false){
			tip({
				content:"请先上传缩略图！"
			})
			return;
		}
		console.log($("input[name='isSend']").is(':checked'))
		var formData = new FormData();
        formData.append("file", document.getElementById("file").files[0]);
        formData.append("title", $("[name='title']").val());
        formData.append("isSend", $("[name='isSend']").val());
        formData.append("content", ue.getContent());
        formData.append("id", $("[name='id']").val());
        formData.append("type",1);
        formData.append("summary",$("[name='summary']").val());
        formData.append("isSend",$("input[name='isSend']").is(':checked'))
		$.ajax({
				url: "/addTbNewsinformation",
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
