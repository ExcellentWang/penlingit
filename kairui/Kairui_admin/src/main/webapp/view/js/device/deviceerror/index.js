var table_1, table_2;

table_1 = function (params) {
	tableData(params, $.extend($("#taskForm").values(), {
		isProcessed: false
	}), "device/getDeviceError");
};


var handle_1, handle_2, tableEvent_1, tableEvent_2, handle_3, handle_4 ,handle_5,workStatus;

tableEvent_1 = {
    "click .taskType": function (e, a, item, index) {
    	
    }
};

tableEvent_2 = {
    "click .info": function (e, a, item, index) {
    	
    }
};


handle_1 = function (value, row, index) {
    return ["<a class='taskType' href='javascript:;'>" + row.currentNodeName + "</a>"].join("");
};

workStatus=function(value, row, index){
	return ["在线","在线","在线","在线","离线"][value]
}




$("#btn-search").click(function () {
    $("#table1").bootstrapTable("refresh", {url: "..."});
});


