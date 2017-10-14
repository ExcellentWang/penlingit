var table_1, table_2;

table_1 = function (params) {
	tableData(params, $.extend($("#taskForm").values(), {
		isProcessed: false
	}), "device/deviceTypeList");
};


var handle_1, handle_2, tableEvent_1, tableEvent_2, handle_3, handle_4 ,handle_5;

tableEvent_1 = {
    "click .taskType": function (e, a, item, index) {
    	
    }
};

tableEvent_2 = {
    "click .info": function (e, a, item, index) {
    	
    }
};


var deviceType
deviceType=function(value, row, index){
	
	return [null,"燃气热水器","储水式电热水器","空气能热水器","壁挂炉","太阳能"][value]
}

$("#btn-search").click(function () {
    $("#table1").bootstrapTable("refresh", {url: "..."});
});
//添加设备类型
$("#addDeviceType").click(function(){
	$("#myModal").modal("show");
	$("#addDeviceType1").click(function(){
		$.ajax({
			  url: "/device/addDeviceType",
			  data: $("#typeForm").values(),
			  success: function(){
				  $("#table1").bootstrapTable("refresh", {url: "..."});
				  tip({
					  content:"添加成功！"
				  })
				  $("#myModal").modal("hide");
				  
			  }
			});
	})
})

