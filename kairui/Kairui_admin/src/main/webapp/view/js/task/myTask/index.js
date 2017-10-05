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

handle_3 = function (value, row, index) {
    if (value == "" || value == null) {
        return "流程结束";
    } else {
        return value;
    }
};

handle_4 = function (value, row, index) {
    if (value == "" || value == null) {
        return "--";
    } else {
        return value;
    }
};

handle_5 = function (value, row, index) {
    if (row.dealer){
        var levelstr ='';
        switch (row.approvalLevel){
            case 1:
                levelstr = "<span class='layui-badge layui-badge-level1'>1级</span> "+row.dealer;
                break;
            case 2:
                levelstr = "<span class='layui-badge layui-badge-level2'>2级</span> "+row.dealer;
                break;
            case 3:
                levelstr = "<span class='layui-badge layui-badge-level3'>3级</span> "+row.dealer;
                break;
            default:
                levelstr = "<span class='layui-badge layui-badge-level1'>默认</span> "+row.dealer;
        }
    }else{
        levelstr = '--';
    }
    return [levelstr].join("");

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



