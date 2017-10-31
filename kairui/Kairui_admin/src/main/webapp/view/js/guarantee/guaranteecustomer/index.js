var table_1, table_2,handle_pic;

table_1 = function (params) {
	tableData(params, $.extend($("#taskForm").values(), {
		isProcessed: false
	}), "selectByExampleGuaranteeCustomer");
};


var handle,tableEvent;

handle = function (value, row, index) {
	var modifyMenu = "";
	/*if(row.status==0){
		modifyMenu+="<li><a class='send'>安排检修</a></li>"
	}
	if(row.status==1){
		modifyMenu+="<li><a class='send'>维修完成</a></li>"
	}*/
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
		console.log(item)
		comn.addTab({
			title: '用户报修详情',
			href: 'Modal/guarantee/guaranteecustomer/add.html?id='+item.customer_id+"&status="+item.status+"&type="+item.type
		});
	}
	
};



$("#btn-search").click(function () {
    $("#table1").bootstrapTable("refresh", {url: "..."});
});

var bao_status
bao_status=function(value, row, index){
	return ["待处理","已受理","完成"][value]
}
var deviceType
deviceType=function(value, row, index){
	if(value==null)return
	return [null,"燃气热水器","储水式电热水器","空气能热水器","壁挂炉","太阳能"][value.substring(1)]
}
