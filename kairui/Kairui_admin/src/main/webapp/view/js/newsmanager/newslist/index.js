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
		modifyMenu+="<li><a class='view'>审核</a></li>"
	}
	if(row.status==2){
		modifyMenu+="<li><a class='cancelview'>取消审核</a></li><li><a class='send'>推送</a></li>"
	}
	if(row.status==3){
		modifyMenu+="<li><a class='send'>再次推送</a></li>"
	}
    return ["<div class='btn-group btn-group-xs'>", "<button type='button' class='btn btn-primary dropdown-toggle' data-toggle='dropdown'>操作", "<span class='caret'></span>", "<span class='sr-only'>下拉切换</span>", "</button>", "<ul class='dropdown-menu' role='menu'>", "<li><a class='update'>修改</a></li>", modifyMenu, "</ul>", "</div>"].join("");
};

tableEvent = {
	"click .update" : function(e, a, item, index) {
		comn.addTab({
			title: '修改资讯',
			href: 'Modal/newsmanager/newslist/add.html?id='+item.id
		});
	},
	"click .del" : function(e, a, item, index) {
		oppSureModal("确定删除该资讯？");
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
		oppSureModal("确定审核并发布该条资讯？");
	   	 $("#sureOption").unbind("click").click(function () {
	   		 $("#sureModal").modal('hide')
	   		changeStatus(item.id,2);
	   	 })
	},
	"click .cancelview" : function(e, a, item, index) {
		oppSureModal("确定取消审核？取消后该条资讯将不会显示在app中！");
	   	 $("#sureOption").unbind("click").click(function () {
	   		 $("#sureModal").modal('hide')
	   		changeStatus(item.id,1);
	   	 })
	},
	"click .send" : function(e, a, item, index) {
		oppSureModal("确定推送消息到app？");
	   	 $("#sureOption").unbind("click").click(function () {
	   		 $("#sureModal").modal('hide')
	   		changeStatus(item.id,3);
	   	 })
	}
};



$("#btn-search").click(function () {
    $("#table1").bootstrapTable("refresh", {url: "..."});
});
//添加资讯
$("#addDeviceType").click(function(){
	comn.addTab({
		title: '添加资讯',
		href: 'Modal/newsmanager/newslist/add.html'
	});
})
handle_pic = function (value, row, index) {
	return "<img style='height:50px;width:100px;' src='"+value+"'/>";
};
handle_status= function (value, row, index) {
	return [null,"待审核","已审核","已推送"][value];
};

//改变状态共用
function changeStatus(id,status){
	$.ajax({
			url: "/changeStatus",
			data: {
				"id":id,
				"status":status
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