var table_1, table_2,equipmentNum;
var args=comn.getArgs();
table_1 = function (params) {
	tableData(params, $.extend($("#taskForm").values(), {
		deviceId:args['deviceId']
	}), "device/getUseWaterByDeviceId");
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
equipmentNum=function(value, row, index){
	return args['equipmentNum']
}

deviceType=function(value, row, index){
	
	return [null,"燃气热水器","储水式电热水器","空气能热水器","壁挂炉","太阳能"][args['type'].substring(1)]
}
var phone
phone=function(value, row, index){
	if(args['phone']=="undefined")return ""
	return args['phone']
}


$("#btn-search").click(function () {
    $("#table1").bootstrapTable("refresh", {url: "..."});
});



