
$(function(){
	var args=comn.getArgs();
	console.log(args['id'])
	$.ajax({
		url: "/selGuaranteeId",
		data:{"id":args['id']},
		success: function(item){
			$("#guaa").values(item.data)
		}
	});
	if(args['type']==2){
		$("#shenhe").removeClass("hide")
	}
	//通过
	$("#pass").click(function(){
		$.ajax({
			url: "/addOrUpdateGuarantee",
			data:{"guaranteeId":args['id'],"status":2},
			success: function(item){
				if(item.code==0){
					tip({
						content:"操作成功！"
					})
					comn.closeTab()
				}else{
					tip({
						content:"操作失败！"
					})
				}
			}
		});
	})
	//拒绝
	$("#refu").click(function(){
		$.ajax({
			url: "/addOrUpdateGuarantee",
			data:{"guaranteeId":args['id'],"status":3},
			success: function(item){
				if(item.code==0){
					tip({
						content:"操作成功！"
					})
					comn.closeTab()
				}else{
					tip({
						content:"操作失败！"
					})
				}
			}
		});
	})
	
})

