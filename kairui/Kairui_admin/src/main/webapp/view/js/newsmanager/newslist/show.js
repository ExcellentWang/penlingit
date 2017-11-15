$(function(){
	var args=comn.getArgs();
	if(args['id']!=""&&args['id']!=null&&args['id']!=undefined){
		$.ajax({
			url: "/getTbNewsinformationId",
			data:{"id":args['id']},
			success: function(item){
				$("#container").html(item.data.content)
			}
		});
	}else{
		console.log(args['content'])
		$("#container").html(args['content'])
	}
})
