$(function(){
	$.ajax({
			url: "/admin/main/indexInfo",
			data: {
			},
			success: function(item){
				$("#shenhe").html(item.data.guaranteeSize)
				$("#baoxiu").html(item.data.customerserviceSize)
			}
		});
	$("#shenhe").click(function(){
		comn.addTab({
			title: '保修卡',
			href: 'Modal/guarantee/guaranteecard/index.html'
		});
	})
})