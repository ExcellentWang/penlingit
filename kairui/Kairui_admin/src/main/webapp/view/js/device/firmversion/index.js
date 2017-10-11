var table_1;
var handle,tableEvent
table_1 = function (params) {
	tableData(params, $.extend($("#taskForm").values(), {
		isProcessed: false
	}), "selectFirmVersionExample");
};
handle = function (value, row, index) {
    return ["<div class='btn-group btn-group-xs'>", "<button type='button' class='btn btn-primary dropdown-toggle' data-toggle='dropdown'>操作", "<span class='caret'></span>", "<span class='sr-only'>下拉切换</span>", "</button>", "<ul class='dropdown-menu' role='menu'>", "<li><a class='del'>删除</a></li>", "</ul>", "</div>"].join("");
};

tableEvent = {
		//删除
	    "click .del": function (e, a, item, index) {
	    	var instructions;
	    	//获取用户指令
	    	oppSureModal("确定删除该固件？");
	    	 $("#sureOption").unbind("click").click(function () {
	    		 $("#addDeviceModal").modal("hide")
	    		 $.ajax({
	    				url:"/delFirmVersionExample",
	    				data:{
	    					"id":item.id
	    				},
	    				success:function(){
	    					 $("#table1").bootstrapTable("refresh", {url: "..."});
	    					 tip({
	    						 content:"删除成功"
	    					 })
	    				}
	    			})
	    	 })
	    }
}
$("#btn-search").click(function () {
    $("#table1").bootstrapTable("refresh", {url: "..."});
});


$("#addDevice").click(function(){
	$("#addDeviceModal").modal("show")
	$("#confirm").unbind("click").click(function () { 
		$("#addDeviceModal").modal("hide")
	/*	$.ajax({
			url:"/addFirmVersion",
			data:$.extend($("#deviceForm").values(),{"file":$("[name='file']").get(0).files[0]}),
			type:"post",
			success:function(){

			}
		})*/
		$("#deviceForm").submit();
		$("#table1").bootstrapTable("refresh", {url: "..."});
	})
})

