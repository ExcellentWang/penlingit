var table_1;var isUpload=false;

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

$("#file").change(function(){
		isUpload=true;
	})
//点确定按钮
$("#confirmUpload").click(function(){
	console.log(isUpload)
	if(isUpload==false){
		tip({
			content:"请先上传设备编号文件!"
		})
		return;
	}
	var formData = new FormData();
    formData.append("file", document.getElementById("file").files[0]);
    $.ajax({
		url: "/device/uploadDevices",
		data:formData,
		type: "POST",
        contentType: false,
        processData: false,
		success: function(item){
			if(item.code!=0){
 				tip({
 					content:item.msg
 				})
			}else{
 				tip({
 					content:"成功！"
 				})
			}
			$("#addDeviceModal").modal('hide')
		} ,beforeSend: function(){
			 $("#qx").addClass("hide");
			 $("#confirmUpload").html("请等待...")
	    },
	    complete: function(){
	    	$("#qx").removeClass("hide");
	    	 $("#confirmUpload").html("确定");
	    }
		
	});
})
//下载文件模板
$("#upmuban").click(function(){
	 var $eleForm = $("<form method='get'></form>");
     $eleForm.attr("action","/device/upmuban");
     $(document.body).append($eleForm);
     //提交表单，实现下载
     $eleForm.submit();
})
