$(function(){
	var args=comn.getArgs();
	$.ajax({
		url: "/getTbNewsinformationId",
		data:{"id":args['id']},
		success: function(item){
			$("#container").html(item.data.content)
		}
	});
})
