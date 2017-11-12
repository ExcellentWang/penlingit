var table_1, table_2;

table_1 = function (params) {
	tableData(params, $.extend($("#taskForm").values(), {
	}), "selectByExampleGuaranteeType");
};


var handle,tableEvent;

handle = function (value, row, index) {
	var modifyMenu = "<li><a class='del'>删除</a></li>";
    return ["<div class='btn-group btn-group-xs'>", "<button type='button' class='btn btn-primary dropdown-toggle' data-toggle='dropdown'>操作", "<span class='caret'></span>", "<span class='sr-only'>下拉切换</span>", "</button>", "<ul class='dropdown-menu' role='menu'>", "<li><a class='update'>修改</a></li>", modifyMenu, "</ul>", "</div>"].join("");
};

tableEvent = {
	"click .update" : function(e, a, item, index) {
		$("#xintiao").modal("show")   	
		$.ajax({
   	 			url: "/selGuaranteeTypeById",
   	 			data: {
   	 				"id":item.id
   	 			},
   	 			success: function(item){
   	 				$("#modalForm").values(item.data)
   	 			}
   	 		});
		$("#confirm").unbind("click").click(function () {
			$("#xintiao").modal('hide')
			$.ajax({
   	 			url: "/addOrUpdateGuaranteeType",
   	 			data: $("#modalForm").values(),
   	 			success: function(item){
   	 			$("#table1").bootstrapTable("refresh", {url: "..."});
   	 				if(item.code!=0){
	   	 				tip({
	   	 					content:item.msg
	   	 				})
	 				}else{
	   	 				tip({
	   	 					content:"修改成功！"
	   	 				})
	 				}
   	 			}
   	 		});
		})
	},
	"click .del" : function(e, a, item, index) {
		oppSureModal("确定删除？");
   	 	$("#sureOption").unbind("click").click(function () {
   	 		$.ajax({
   	 			url: "/delGuaranteeType",
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
	}
	
	
};



$("#btn-search").click(function () {
    $("#table1").bootstrapTable("refresh", {url: "..."});
});
//添加
$("#addDeviceType").click(function(){
	$("#xintiao").modal("show")  
	$("#confirm").unbind("click").click(function () {
		$("#xintiao").modal('hide')
		$.ajax({
	 			url: "/addOrUpdateGuaranteeType",
	 			data: $("#modalForm").values(),
	 			success: function(item){
	 				if(item.code!=0){
   	 				tip({
   	 					content:item.msg
   	 				})
 				}else{
 					$("#table1").bootstrapTable("refresh", {url: "..."});
   	 				tip({
   	 					content:"添加成功！"
   	 				})
 				}
	 			}
	 		});
		
	})
})
var deviceType
deviceType=function(value, row, index){
	return [null,"燃气热水器","储水式电热水器","空气能热水器","壁挂炉","太阳能"][value.substring(1)]
}
var xuhao;
xuhao= function (value, row, index) {
	return index+1;
};
