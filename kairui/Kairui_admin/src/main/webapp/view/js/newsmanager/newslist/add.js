
$(function(){
	var ue = UE.getEditor('container');
	var args=comn.getArgs();
	var isUpload=false;
	$.ajax({
		url: "/getTbNewsinformationId",
		data:{"id":args['id']},
		success: function(item){
			$("#lunboForm").values(item.data)
			$("#lst").attr("src",item.data.picture)
			ue.ready(function(){
				ue.setContent(item.data.content);
			});
		}
	});
	$("[name='file']").change(function(){
		isUpload=true;
		previewFile();
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
	
		//预览
$("#yulan").click(function(){
	if(lenFor(ue.getContent())>2000){
		tip({
				content:"消息内容过多，浏览器不支持预览！"
			})
	}
	comn.addTab({
		title: '预览',
		href: 'Modal/newsmanager/newslist/show.html?content='+ue.getContent()
	});
})
function lenFor(str){
		var byteLen=0,len=str.length;
		if(str){
			for(var i=0; i<len; i++){
				if(str.charCodeAt(i)>255){
					byteLen += 2;
				}
				else{
					byteLen++;
				}
			}
			return byteLen;
		}
		else{
			return 0;
		}
	}
	
})
//图片预览
function previewFile() {
	var preview = document.querySelector('#lst');
	var file  = document.querySelector('input[type=file]').files[0];
	var reader = new FileReader();
	reader.onloadend = function () {
		preview.src = reader.result;
	}
	if (file) {
		reader.readAsDataURL(file);
	} else {
		preview.src = "";
	}
}
