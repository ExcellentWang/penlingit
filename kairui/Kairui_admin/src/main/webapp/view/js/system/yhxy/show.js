//用户协议展示
$(function(){
	var args=comn.getArgs();
	var id
	$.ajax({
		url: "/user/selectEula",
		data:{},
		success: function(item){
			$("#container").html(item.data.content)
			id=item.data.id
		}
	});
	//修改按钮
	$("#updateYHXY").click(function(){
		comn.addTab({
			title: '修改用户协议',
			href: 'Modal/task/userManage/system/yhxy/add.html?content='+$("#container").html()+'&id='+id
		});
	})
})
