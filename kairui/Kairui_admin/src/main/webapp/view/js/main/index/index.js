$(function(){
	$.ajax({
			url: "/admin/main/indexInfo",
			success: function(item){
				var b=item.data;
				//设备统计
				$("#allD").html(item.data.allSize)
				$("#nowOnline").html(item.data.onlineSize)
				//在线率
				if(b.zaixianlv){
					for(var i=0;i<b.zaixianlv.length;i++){
						var a=item.data.zaixianlv
						var div1="<div>"+a[i].province+"省："+a[i].lv*100+"%</div><div class='Bar'><div style='width:"+a[i].lv*100+"%'> <span>"+a[i].lv*100+"%</span> </div></div>";
						$("#paiming").append(div1)
					}
				}
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