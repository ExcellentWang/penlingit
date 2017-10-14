var table_1, table_2;

table_1 = function (params) {
	tableData(params, $.extend($("#taskForm").values(), {
		isProcessed: false
	}), "device/getDeviceError");
};


var handle, tableEvent,workStatus;
var deviceType;

tableEvent = {
    "click .info": function (e, a, item, index) {
    	
    }
};



handle= function (value, row, index) {
    return ["<a class='info' href='javascript:;'>" + "查看详情" + "</a>"].join("");
};


deviceType=function(value, row, index){
	
	return [null,"燃气热水器","储水式电热水器","空气能热水器","壁挂炉","太阳能"][value.substring(1)]
}



$("#btn-search").click(function () {
    $("#table1").bootstrapTable("refresh", {url: "..."});
});



