var table_1;
var handle,tableEvent
table_1 = function (params) {
	tableData(params, $.extend($("#taskForm").values(), {
		isProcessed: false
	}), "selectFirmVersionExample");
};
handle = function (value, row, index) {
    return ["<div class='btn-group btn-group-xs'>", "<button type='button' class='btn btn-primary dropdown-toggle' data-toggle='dropdown'>操作", "<span class='caret'></span>", "<span class='sr-only'>下拉切换</span>", "</button>", "<ul class='dropdown-menu' role='menu'>", "<li><a class='del'>删除</a></li>", "</ul>", "</div>"].join("");
};

tableEvent = {
		//删除
	    "click .del": function (e, a, item, index) {
	    	var instructions;
	    	//获取用户指令
	    	oppSureModal("确定删除该固件？");
	    	 $("#sureOption").unbind("click").click(function () {
	    		 $("#sureModal").modal('hide')
	    		 $.ajax({
	    				url:"/delFirmVersionExample",
	    				data:{
	    					"id":item.id
	    				},
	    				success:function(){
	    					 $("#table1").bootstrapTable("refresh", {url: "..."});
	    					 tip({
	    						 content:"删除成功"
	    					 })
	    				}
	    			})
	    	 })
	    }
}
$("#btn-search").click(function () {
    $("#table1").bootstrapTable("refresh", {url: "..."});
});


$("#addDevice").click(function(){
	$("#addDeviceModal").modal("show")
	$("#confirm").unbind("click").click(function () { 
		$("#addDeviceModal").modal("hide")
		 var formData = new FormData();
         formData.append("file", document.getElementById("file").files[0]); 
         formData.append("product", $("#product").val()); 
         formData.append("type", $("#type").val()); 
         formData.append("version", $("[name='version']").val()); 
         formData.append("remark", $("[name='remark']").val()); 
		console.log(formData)
		$.ajax({
			url:"/addFirmVersion",
			data:$.extend(formData,$("#deviceForm").values()),
			type: "POST",
            /**
            *必须false才会自动加上正确的Content-Type
            */
            contentType: false,
            /**
            * 必须false才会避开jQuery对 formdata 的默认处理
            * XMLHttpRequest会对 formdata 进行正确的处理
            */
            processData: false,
			success:function(){
				$("#table1").bootstrapTable("refresh", {url: "..."});
			}
		})
		
	})
})
var deviceType
deviceType=function(value, row, index){
	
	return [null,"燃气热水器","储水式电热水器","空气能热水器","壁挂炉","太阳能"][value.substring(1)]
}

