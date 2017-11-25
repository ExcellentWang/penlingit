var table_1;

table_1 = function (params) {
	tableData(params, $.extend($("#taskForm").values(), {
		isProcessed: false
	}), "device/deviceList");
};


var handle,tableEvent,workStatus,deviceType;

tableEvent = {
	//系统对时
    "click .duishi": function (e, a, item, index) {
    	var instructions;
    	//获取用户指令
    	oppSureModal("确定所选设备时间校准为服务器时间？");
    	 $("#sureOption").unbind("click").click(function () {
    		 $("#sureModal").modal('hide')
    		 $.ajax({
    				url:"/device/deviceDuiShi",
    				data:{
    					"equipmentNum":item.equipmentNum
    				},
    				success:function(item){
    					if(item.code==0){
    						tip({
    							content:"操作成功！"
    						})
    					}else{
    						tip({
    							content:item.msg
    						})
    					}
    				}
    			})
    	 })
    },
    //温度计，流量计校准
    "click .wendu": function (e, a, item, index) {
    	var instructions;
    	//查询最新的一条记录
    	$.ajax({
			url:"/device/getTbwNew",
			data:{
				"deviceNum":item.equipmentNum
			},
			async:false,
			success:function(item){
				if(item.code==0){
					if(item.data){
						$("[name='re1']").val(item.data.hotWaterDe);
						$("[name='re2']").val(item.data.codeWaterDe);
						$("[name='re3']").val(item.data.cWaterDe);
						$("[name='re4']").val(item.data.hWaterDe);
						$("[name='re5']").val(item.data.mixingValve);
						$("[name='re6']").val(item.data.dischargeCoefficient);
					}
				}else{
					tip({
						content:item.msg
					})
				}
			}
		})
    	$("#wendu").modal("show")   	
    	$("#con").unbind("click").click(function () {	
    		$("#wendu").modal('hide')
    		instructions=item.equipmentNum+":wcal,len,"
			+$("[name='re1']").val()+","
			+$("[name='re2']").val()+","
			+$("[name='re3']").val()+","
			+$("[name='re4']").val()+","
			+$("[name='re5']").val()+","
			+$("[name='re6']").val()+","
			+"3950";
    		sendOrder(instructions)
    	})
    },
    //心跳间隔
    "click .xintiao": function (e, a, item, index) {
    	console.log(item.equipmentNum)
    	var instructions;
    	$("#xintiao").modal("show")   	
    	$("#xinConfirm").unbind("click").click(function () {
    		$("#xintiao").modal('hide')
    		instructions=item.equipmentNum+":xtset,len,"
    			+handeltime($("[name='xintiao']").val());
    		console.log("instructions--"+instructions)
    		sendOrder(instructions);
    	})
    },
    //使用记录间隔
    "click .useUpTime": function (e, a, item, index) {
    	var instructions;
    	$("#useUpTimeModal").modal("show")   	
    	$("#confirm").unbind("click").click(function () {
    		$("#useUpTimeModal").modal('hide')
    		instructions=item.equipmentNum+":scjg,len,00"
			+handeltime($("[name='useUpTime']").val())
    		console.log("使用记录间隔"+instructions)
    		sendOrder(instructions)
    	})
    },
    //固件升级
    "click .upgu": function (e, a, item, index) {
    	var instructions;
    	$("#upgu").modal("show")   	
    	$("#confirm").unbind("click").click(function () {  	
    		$("#upgu").modal("hide") 
    		$.ajax({
				url:"/device/upgu",
				data:{
					"deviceNum":item.equipmentNum
				},
				success:function(item){
					if(item.code==0){
						tip({
							content:"操作成功！"
						})
					}else{
						tip({
							content:item.msg
						})
					}
				}
			})
    	})
    },
    //文本信息下发
    "click .wenbenDown": function (e, a, item, index) {
    	var instructions;
    	console.log(1)
    	$("#wenbenDown").modal("show") 
    	$("#confirmw").click(function () {
    		$("#wenbenDown").modal("hide") 
    		 $.ajax({
 				url:"/device/wenbenDown",
 				data:{
 					"wenbenDown":$("[name='wenbenDown']").val(),
 					"deviceNum":item.equipmentNum
 				},
 				success:function(item){
 					if(item.code==0){
 						tip({
 							content:"操作成功！"
 						})
 					}else{
 						tip({
 							content:item.msg
 						})
 					}
 				}
 			})
    	})
    }
};

handle = function (value, row, index) {
	var modifyMenu = "<li><a class='wendu'>温度计、流量计校准</a></li>"+"<li><a class='xintiao'>设置心跳间隔时间</a></li>"+"<li><a class='useUpTime'>设置使用记录上传间隔时间</a></li>"
	+"<li><a class='upgu'>固件升级</a></li>"+
	"<li><a class='wenbenDown'>文本信息下发</a></li>"
    return ["<div class='btn-group btn-group-xs'>", "<button type='button' class='btn btn-primary dropdown-toggle' data-toggle='dropdown'>操作", "<span class='caret'></span>", "<span class='sr-only'>下拉切换</span>", "</button>", "<ul class='dropdown-menu' role='menu'>", "<li><a class='duishi'>对时</a></li>", modifyMenu, "</ul>", "</div>"].join("");
};

workStatus=function(value, row, index){
	return ["离线","在线","在线","在线","离线"][value]
}

deviceType=function(value, row, index){
	return [null,"燃气热水器","储水式电热水器","空气能热水器","壁挂炉","太阳能"][value.substring(1)]
}

$("#btn-search").click(function () {
    $("#table1").bootstrapTable("refresh", {url: "..."});
});
//发送指令
function sendOrder(instructions){
	$.ajax({
		url:"/device/deviceSendInstruction",
		data:{
			"instructions":instructions
		},
		success:function(item){
			if(item.code==0){
				tip({
					content:"操作成功！"
				})
			}else{
				tip({
					content:item.msg
				})
			}
		}
	})
}

function handeltime(date){
	if(date>=0&&date<10)return "0"+date;
	return date
}

