
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
		if(isUpload==false){
			tip({
				content:"请先上传缩略图！"
			})
			return;
		}
		var formData = new FormData();
        formData.append("file", document.getElementById("file").files[0]);
        formData.append("title", $("[name='title']").val());
        formData.append("content", ue.getContent());
        formData.append("type",2);
        formData.append("id", $("[name='id']").val());
        formData.append("summary",$("[name='summary']").val());
        formData.append("timeSend",$("input[name=timeSend]:checked").val());
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
	
	//预览
$("#yulan").click(function(){
	comn.addTab({
		title: '预览',
		href: 'Modal/newsmanager/newslist/show.html?content='+ue.getContent()
	});
})
})

