var table_1, table_2,handle_pic;

table_1 = function (params) {
	tableData(params, $.extend($("#taskForm").values(), {
		isProcessed: false
	}), "selectTbNewsinformationExample");
};


var handle,tableEvent;

handle = function (value, row, index) {
	var modifyMenu = "<li><a class='del'>删除</a></li>";
	if(row.status==1){
		modifyMenu+="<li><a class='send'>立即推送</a></li>"
	}
	/*if(row.status==2){
		modifyMenu+="<li><a class='cancelview'>取消审核</a></li><li><a class='send'>推送</a></li>"
	}*/
	if(row.status==4){
		modifyMenu+="<li><a class='send'>重新推送</a></li>"
	}
    return ["<div class='btn-group btn-group-xs'>", "<button type='button' class='btn btn-primary dropdown-toggle' data-toggle='dropdown'>操作", "<span class='caret'></span>", "<span class='sr-only'>下拉切换</span>", "</button>", "<ul class='dropdown-menu' role='menu'>", "<li><a class='view'>查看详情</a></li>", modifyMenu, "</ul>", "</div>"].join("");
};

tableEvent = {
	"click .update" : function(e, a, item, index) {
		comn.addTab({
			title: '修改消息',
			href: 'Modal/xiaoximanager/xiaoxi/add.html?id='+item.id
		});
	},
	"click .del" : function(e, a, item, index) {
		oppSureModal("确定删除该消息？");
   	 	$("#sureOption").unbind("click").click(function () {
   	 		$.ajax({
   	 			url: "/delTbNewsinformation",
   	 			data: {
   	 				"id":item.id
   	 			},
   	 			success: function(item){
   	 				$("#table1").bootstrapTable("refresh", {url: "..."});
   	 				if(item.code!=0){
	   	 				tip({
	   	 					content:item.msg
	   	 				})
   	 				}else{
	   	 				tip({
	   	 					content:"删除成功！"
	   	 				})
   	 				}
   	 				$("#sureModal").modal('hide')
   	 			}
   	 		});
   	 	})
	},
	"click .view" : function(e, a, item, index) {
		comn.addTab({
			title: '查看详情',
			href: item.address
		});
	},
	"click .send" : function(e, a, item, index) {
		oppSureModal("确定推送消息到app？");
	   	 $("#sureOption").unbind("click").click(function () {
	   		 $("#sureModal").modal('hide')
	   		send(item.id);
	   	 })
	}
};



$("#btn-search").click(function () {
    $("#table1").bootstrapTable("refresh", {url: "..."});
});
//添加消息
$("#addDeviceType").click(function(){
	comn.addTab({
		title: '添加消息',
		href: 'Modal/xiaoximanager/xiaoxi/add.html'
	});
})
handle_pic = function (value, row, index) {
	return "<img style='height:50px;width:100px;' src='"+value+"'/>";
};
handle_status= function (value, row, index) {
	return [null,"等待推送",null,"推送完成","推送失败","推送中"][value];
};

//推送
function send(id){
	$.ajax({
			url: "/sendToApp",
			data: {
				"id":id
			},
			success: function(item){
				$("#table1").bootstrapTable("refresh", {url: "..."});
				if(item.code!=0){
	 				tip({
	 					content:item.msg
	 				})
				}else{
	 				tip({
	 					content:"操作成功！"
	 				})
				}
				$("#sureModal").modal('hide')
			}
		});
}