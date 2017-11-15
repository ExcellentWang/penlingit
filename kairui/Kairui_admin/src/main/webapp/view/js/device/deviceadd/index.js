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

var deviceType
deviceType=function(value, row, index){
	
	return [null,"燃气热水器","储水式电热水器","空气能热水器","壁挂炉","太阳能"][value.substring(1)]
}

$("#addDevice").click(function(){
	$("#addDeviceModal").modal("show")
	$("#confirm").unbind("click").click(function () { 
		$("#addDeviceModal").modal("hide")
		$.ajax({
			url:"/device/addDevice",
			data:{
				"product":$("#deviceForm").find("[name='product']").val(),
				"equipmentNum":$("#deviceForm").find("[name='equipmentNum']").val()
			},
			success:function(item){
				if(item.code==10001){
					tip({
						content:"设备已存在请勿重复添加！"
					})
				}
				$("#table1").bootstrapTable("refresh", {url: "..."});
			}
		})
    })
})

