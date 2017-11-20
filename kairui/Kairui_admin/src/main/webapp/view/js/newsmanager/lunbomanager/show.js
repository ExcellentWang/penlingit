$(function(){
	var args=comn.getArgs();
	console.log(args['id'])
	if(args['id']!=""&&args['id']!=null&&args['id']!=undefined){
		$.ajax({
			url: "/getLunboId",
			data:{"id":args['id']},
			success: function(item){
				console.log(item)
				$("#container").html(item.data.content)
			}
		});
	}else{
		$("#container").html(args['content'])
	}
})
