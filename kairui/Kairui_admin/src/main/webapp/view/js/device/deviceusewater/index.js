var table_1, table_2;

table_1 = function (params) {
	tableData(params, $.extend($("#taskForm").values(), {
		isProcessed: false
	}), "device/deviceList");
};


var handle, tableEvent,workStatus;
var deviceType;

tableEvent = {
    "click .info": function (e, a, item, index) {
    	comn.addTab({
			title: '用水量/节水量详情',
			href: 'Modal/device/deviceusewaterinfo/index.html?deviceId='+item.equipment_id+"&equipmentNum="+item.equipmentNum+"&type="+item.type+"&phone="+item.phone
		});
    }
};



handle= function (value, row, index) {
	return ["<div class='btn-group btn-group-xs'>", "<button type='button' class='btn btn-primary dropdown-toggle' data-toggle='dropdown'>操作", "<span class='caret'></span>", "<span class='sr-only'>下拉切换</span>", "</button>", "<ul class='dropdown-menu' role='menu'>", "<li><a class='info'>查看详情</a></li>", "</ul>", "</div>"].join("");
};


deviceType=function(value, row, index){
	
	return [null,"燃气热水器","储水式电热水器","空气能热水器","壁挂炉","太阳能"][value.substring(1)]
}



$("#btn-search").click(function () {
    $("#table1").bootstrapTable("refresh", {url: "..."});
});



