var table_1;

table_1 = function (params) {
	tableData(params, $.extend($("#taskForm").values(), {
		isProcessed: false
	}), "device/deviceList");
};


var handle,tableEvent,workStatus;

tableEvent = {
	//系统对时
    "click .duishi": function (e, a, item, index) {
    	var instructions;
    	//获取用户指令
    	oppSureModal("确定所选设备时间校准为服务器时间？");
    	 $("#sureOption").unbind("click").click(function () { 
    		 $.ajax({
    				url:"/device/deviceDuiShi",
    				data:{
    					"equipmentNum":item.equipmentNum
    				},
    				success:function(){
    					
    				}
    			})
    	 })
    },
    "click .wendu": function (e, a, item, index) {
    	var instructions;
    	$("#wendu").modal("show")   	
    	$("#confirm").unbind("click").click(function () {  	    		
    		sendOrder(instructions)
    	})
    },
    //心跳间隔
    "click .xintiao": function (e, a, item, index) {
    	console.log(item.equipmentNum)
    	var instructions;
    	$("#xintiao").modal("show")   	
    	$("#confirm").unbind("click").click(function () { 
    		instructions="<"+item.equipmentNum+":xtset,037,"
    			+handeltime($("[name='xintiao']").val())
    			+",OR>";
    		console.log("instructions--"+instructions)
    		sendOrder(instructions);
    	})
    },
    //使用记录间隔
    "click .useUpTime": function (e, a, item, index) {
    	var instructions;
    	$("#useUpTimeModal").modal("show")   	
    	$("#confirm").unbind("click").click(function () {  	
    		instructions="<"+item.equipmentNum+":scjg,032,00"
			+handeltime($("[name='useUpTime']").val())
			+",OR>";
    		console.log("使用记录间隔"+instructions)
    		sendOrder(instructions)
    	})
    },
    //固件升级
    "click .upgu": function (e, a, item, index) {
    	var instructions;
    	$("#upgu").modal("show")   	
    	$("#confirm").unbind("click").click(function () {  	
    		
    		sendOrder(instructions)
    	})
    },
    //文本信息下发
    "click .wenbenDown": function (e, a, item, index) {
    	var instructions;
    	
    	$("#wenbenDown").modal("show") 
    	$("#confirm").unbind("click").click(function () { 
    		//<LDCT01201704230001:stxt,128,01，0xaa,-------0xbb,OR>
    		instructions="<"+item.equipmentNum+":stxt,128,01"
			+$("[name='wenbenDown']").val()
			+",OR>";
    		sendOrder(instructions)
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
	return ["在线","在线","在线","在线","离线"][value]
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
		success:function(){
			
		}
	})
}

function handeltime(date){
	if(date>=0&&date<10)return "0"+date;
	return date
}

