var table_1;

table_1 = function (params) {
	tableData(params, $.extend($("#taskForm").values(), {
		isProcessed: false
	}), "/selectFirmVersionExample");
};

$("#btn-search").click(function () {
    $("#table1").bootstrapTable("refresh", {url: "..."});
});


$("#addDevice").click(function(){
	$("#addDeviceModal").modal("show")
	$("#confirm").unbind("click").click(function () { 
		$("#addDeviceModal").modal("hide")
		
		$("#table1").bootstrapTable("refresh", {url: "..."});
    })
})

