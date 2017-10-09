var table_1;

table_1 = function (params) {
	tableData(params, $.extend($("#taskForm").values(), {
		isProcessed: false
	}), "device/deviceList");
};

$("#btn-search").click(function () {
    $("#table1").bootstrapTable("refresh", {url: "..."});
});

function handeltime(date){
	if(date>=0&&date<10)return "0"+date;
	return date
}

