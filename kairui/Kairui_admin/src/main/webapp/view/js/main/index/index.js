$(function(){
	$.ajax({
			url: "/admin/main/indexInfo",
			success: function(item){
				$("#shenhe").html(item.data.guaranteeSize)
				$("#baoxiu").html(item.data.customerserviceSize)
			}
		});
	$("#shenhediv").click(function(){
		comn.addTab({
			title: '保修卡',
			href: 'Modal/guarantee/guaranteecard/index.html'
		});
	})
	$("#baoxiudiv").click(function(){
		comn.addTab({
			title: '用户报修',
			href: 'Modal/guarantee/guaranteecustomer/index.html'
		});
	})
	$("#zxdiv").click(function(){
		comn.addTab({
			title: '资讯列表',
			href: 'Modal/newsmanager/newslist/index.html'
		});
	})
	$("#xxdiv").click(function(){
		comn.addTab({
			title: '消息列表',
			href: 'Modal/xiaoximanager/xiaoxi/index.html'
		});
	})
})