var table_1, table_2;

table_1 = function (params) {
	tableData(params, $.extend($("#taskForm").values(), {
		isProcessed: false
	}), "device/deviceList");
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


handle_1 = function (value, row, index) {
    return ["<a class='taskType' href='javascript:;'>" + row.currentNodeName + "</a>"].join("");
};

handle_2 = function (value, row, index) {
    return ["<a class='info' href='javascript:;'>查看详情</a>"].join("");
};



handle_4 = function (value, row, index) {
    if (value == "" || value == null) {
        return "--";
    } else {
        return value;
    }
};



$("#org").getOrg();
$("#ftCode").flowGet();
$(document).on("change", "#ftCode", function() {
    var flowValue = $(this).find("option:selected").val();
    $("#flowNode").getFlowNode(flowValue);
    return;
});
$("#btn-search").click(function () {
    $("#table1").bootstrapTable("refresh", {url: "..."});
});



