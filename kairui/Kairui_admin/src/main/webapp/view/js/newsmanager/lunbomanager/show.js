$(function(){
	var args=comn.getArgs();
	if(args['id']!=""&&args['id']!=null&&args['id']!=undefined){
		$.ajax({
			url: "/getLunboId",
			data:{"id":args['id']},
			success: function(item){
				$("#container").html(item.data.content)
			}
		});
	}else{
		alert(1)
		$("#container").html(args['content'])
	}
})
